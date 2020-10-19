import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { FeedbackService } from '../services/feedback/feedback.service';
import { Feedback } from '../models/feedback';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { error } from 'console';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styles: []
})
export class FeedbackComponent implements OnInit {
  isLoading = false;
  feedback: Feedback;

  sexControl = new FormControl('', Validators.required);
  maritalStatusControl = new FormControl('', Validators.required);
  feedbackControl = new FormControl('', Validators.required);
  ageControl = new FormControl('', Validators.required);

  ages: string[] = [
    '10-15',
    '16-19',
    '20-25',
    '26-35',
    '36-45',
    '46-45',
    '56-45',
    '66-∞'
  ];

  feedbackForm: FormGroup;

  constructor(
    private feedbackService: FeedbackService,
    private translate: TranslateService,
    private spinner: NgxSpinnerService,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    this.feedback = new Feedback();

    this.feedbackForm = new FormGroup({
      sex: this.sexControl,
      maritalStatus: this.maritalStatusControl,
      feedback: this.feedbackControl,
      age: this.ageControl
    });
  }

  sendFeedback() {
    this.spinner.show();
    this.feedbackService.postFeedback(this.feedback).subscribe(res => {
      this.spinner.hide();
      this.feedback = new Feedback();

        this.translate.getTranslation(res.language).subscribe(lang => {
          if (-0.15 > res.sentimentScore || res.sentimentScore < 0.15) {
            this.toastr.info(lang['NEUTRAL_FEEDBACK']);
          } else if (res.sentiment) {
            this.toastr.success(lang['GOOD_FEEDBACK']);
          } else {
            this.toastr.error(lang['BAD_FEEDBACK']);
          }
        });
    },
    err => {
      this.spinner.hide();
      this.toastr.error(err.error.message);
    });
  }
}
