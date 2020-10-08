import { Component, OnInit } from '@angular/core';
import { FeedbackService } from '../services/feedback/feedback.service';
import { Feedback } from '../models/feedback/feedback';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styles: []
})
export class FeedbackComponent implements OnInit {
  isLoading = false;
  feedback: Feedback;
  constructor(
    private feedbackService: FeedbackService,
    private translate: TranslateService,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    this.feedback = new Feedback();
  }
  sendFeedback() {
    this.isLoading = true;
    this.feedbackService.postFeedback(this.feedback).subscribe(res => {
      this.isLoading = false;

      if (res) {
        this.translate.get('GOOD_FEEDBACK').subscribe(translatation => {
          this.toastr.success(translatation);
        });

      } else {
        this.translate.get('BAD_FEEDBACK').subscribe(translatation => {
          this.toastr.success(translatation);
        });

      }
    });
    this.feedback.text = '';
  }
}
