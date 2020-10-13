import { Component, OnInit, ViewChild } from '@angular/core';
import { FeedbackService } from '../services/feedback/feedback.service';
import { Feedback } from '../models/feedback';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { NgOption } from '@ng-select/ng-select';
import { ToastrService } from 'ngx-toastr';
import { SwalComponent } from '@sweetalert2/ngx-sweetalert2';
import Swal from 'sweetalert2/dist/sweetalert2.js';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-feedback-results',
  templateUrl: './feedback-results.component.html',
  styleUrls: ['./feedback-results.component.scss']
})
export class FeedbackResultsComponent implements OnInit {
  usersFeedback = [] as Feedback[];
  displayedColumns: string[] = ['text', 'sentiment', 'score', 'probability', 'Actions'];
  dataSource: MatTableDataSource<Feedback>;
  @ViewChild('retrainStart') retrainStart: SwalComponent;
  @ViewChild('retrainError') retrainError: SwalComponent;
  @ViewChild('infoSwal') private infoSwal: SwalComponent;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  isLoading = false;
  isLoadingPie = false;

  view: any[] = [700, 300];

  // options
  gradient = true;
  showLegend = true;
  showLabels = true;
  isDoughnut = true;

  singleData = [];

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
      });

      this.singleData = [...data];
      this.isLoadingPie = false;

      this.feedbackService.getAllFeedback().subscribe(result => {
        setTimeout(function() {
            result.forEach(r => {
              r.probability = Math.round(r.probability * 100);
              r.score = +r.score.toFixed(2);
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
    element.confirmedSentiment = change ? !element.sentiment : element.sentiment;

    this.feedbackService.updateUserFeedback(element).subscribe(result => {
      console.log(result);
      if (result.success) {
        this.toastr.success('Sentiment updated!');
        element.sentToML = result.success;
      }
    });
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  retrainModel() {
    this.feedbackService.retrainUserFeedbackModel().subscribe(result => {
      this.retrainStart.fire();
    }, error => {
      this.retrainError.fire();
    });
  }

  getRecord(row: Feedback) {
    console.log(row);

    Swal.fire('Any fool can use a computer' + row.text);
  }
}
