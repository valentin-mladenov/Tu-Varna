import { Component, OnInit, ViewChild } from '@angular/core';
import { FeedbackService } from '../services/feedback/feedback.service';
import { Feedback } from '../models/feedback/feedback';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { NgOption } from '@ng-select/ng-select';
import { ToastrService } from 'ngx-toastr';
import { SwalComponent } from '@sweetalert2/ngx-sweetalert2';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-feedback-results',
  templateUrl: './feedback-results.component.html',
  styleUrls: ['./feedback-results.component.scss']
})
export class FeedbackResultsComponent implements OnInit {
  usersFeedback = [] as Feedback[];
  displayedColumns: string[] = ['userName', 'text', 'sentiment', 'score', 'probability', 'Actions'];
  dataSource: MatTableDataSource<Feedback>;
  @ViewChild('retrainStart') retrainStart: SwalComponent;
  @ViewChild('retrainError') retrainError: SwalComponent;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  isLoading = false;

  constructor(
    public feedbackService: FeedbackService,
    private toastr: ToastrService,
    public translate: TranslateService,
  ) { }

  ngOnInit() {
    this.isLoading = true;

    this.feedbackService.getAllFeedback().subscribe(result => {
      console.log(result);

      result.forEach(r => {
        r.probability = Math.round(r.probability * 100);
        r.score = +r.score.toFixed(2);
      });

      this.usersFeedback = result;
      this.dataSource = new MatTableDataSource<Feedback>(this.usersFeedback);

      setTimeout(() => this.dataSource.paginator = this.paginator);

      this.isLoading = false;
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
      // this.retrainStart.show();
    }, error => {
      // this.retrainError.show();
    });
  }
}
