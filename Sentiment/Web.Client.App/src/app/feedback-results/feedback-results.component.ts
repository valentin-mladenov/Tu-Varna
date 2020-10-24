import { Component, OnInit, ViewChild } from '@angular/core';
import { FeedbackService } from '../services/feedback/feedback.service';
import { Feedback } from '../models/feedback';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { ToastrService } from 'ngx-toastr';
import { SwalComponent } from '@sweetalert2/ngx-sweetalert2';
import Swal from 'sweetalert2/dist/sweetalert2.js';
import { TranslateService } from '@ngx-translate/core';
import { Ages, MaritalStatus, Sex } from '../models/enums';

@Component({
  selector: 'app-feedback-results',
  templateUrl: './feedback-results.component.html',
  styleUrls: ['./feedback-results.component.scss']
})
export class FeedbackResultsComponent implements OnInit {
  usersFeedback = [] as Feedback[];
  displayedColumns: string[] = ['text', 'lang', 'langScore', 'sentiment', 'score', 'probability', 'Actions'];
  dataSource: MatTableDataSource<Feedback>;
  @ViewChild('retrainStart') retrainStart: SwalComponent;
  @ViewChild('retrainError') retrainError: SwalComponent;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  isLoading = false;
  isLoadingPie = false;

  view: any[] = [700, 200];
  viewAges: any[] = [800, 200];

  // options
  gradient = true;
  showLegend = true;
  showLabels = true;
  isDoughnut = true;

  totalData = [];
  maritalStatusData = [];
  sexTypeData = [];
  ageRangeData = [];

  constructor(
    public feedbackService: FeedbackService,
    private toastr: ToastrService,
    public translate: TranslateService,
  ) { }

  ngOnInit() {
    this.isLoading = this.isLoadingPie = true;

    this.feedbackService.getChartFeedback().subscribe(data => {
      data.forEach(element => {
        this.translate.get(element.name).subscribe((trans) => {
          element.name = trans;
        });
        element.value.forEach(chart => {
          this.translate.get(chart.name).subscribe((trans) => {
            chart.name = trans;
          });
        });
      });

      this.totalData = [...data[0].value];
      this.maritalStatusData = [...data[1].value];
      this.sexTypeData = [...data[2].value];
      this.ageRangeData = [...data[3].value];

      this.isLoadingPie = false;

      this.feedbackService.getAllFeedback().subscribe(result => {
        setTimeout(function() {
            result.forEach(r => {
              r.sentimentProbability = Math.round(r.sentimentProbability * 100);
              r.sentimentScore = +r.sentimentScore.toFixed(2);
            });

            this.usersFeedback = result;
            this.dataSource = new MatTableDataSource<Feedback>(this.usersFeedback);

            setTimeout(() => this.dataSource.paginator = this.paginator);

            this.isLoading = false;
        }.bind(this), 1000);
      }, error => {
        this.isLoading = false;
      });
    }, error => {
      this.isLoadingPie = false;
    });
  }

  confirmSentiment(element: Feedback, change: boolean) {
    if (!element.confirmedLanguage) {
      this.translate.get('CONFIRM_LANG').subscribe(res => {
        this.toastr.error(res);
      });

      return false;
    }

    element.confirmedSentiment = change;

    this.feedbackService.updateUserFeedback(element).subscribe(result => {
      if (result) {
        element.sentToML = result;
        this.translate.get('FEEDBACK_CONFIRMED').subscribe(res => {
          this.toastr.success(res);
        });
      }
    },
    err => {
      this.toastr.error(err.error.message);
    });
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  retrainModel() {
    this.feedbackService.retrainUserFeedbackModel().subscribe(result => {
      this.retrainStart.fire();
    }, err => {
      this.toastr.error(err.error.message);
      this.retrainError.fire();
    });
  }

  getRecord(row: Feedback) {
    const deepClone = JSON.parse(JSON.stringify(row));

    deepClone.ageRangeString = Ages[row.ageRange];
    deepClone.MaritalStatusString = this.translate.instant(`maritalStatus.${row.sex}.${row.maritalStatus}`);
    deepClone.SexString = this.translate.instant('sex.' + row.sex);

    this.translate.get('FEEDBACK_DISPLAY', deepClone).subscribe(text => {
      Swal.fire( {
          title: this.translate.instant('FEEDBACK_DISPLAY_TITLE'),
          html: text,
          width: '40vw'
      });
    });
  }
}
