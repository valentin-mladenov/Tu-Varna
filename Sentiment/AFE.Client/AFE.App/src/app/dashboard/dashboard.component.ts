import { Component, AfterContentInit, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { FeedbackService } from '../services/feedback/feedback.service';

@Component({
  selector: 'dashboard', // tslint:disable-line
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'
  ]
})
export class DashboardComponent implements AfterContentInit, OnInit {
  today: string;
  services: Array<any>;
  isLoading: boolean;

  view: any[] = [700, 300];

  // options
  gradient = true;
  showLegend = true;
  showLabels = true;
  isDoughnut = true;

  singleData = [];

  constructor(
    private translate: TranslateService,
    private feedbackService: FeedbackService
  ) {
    this.translate.get('DASHBOARD').subscribe(lang => {
      // this.appService.pageTitle = lang;
    });

    this.today = (new Date()).toLocaleDateString(
      navigator.language ? navigator.language : 'de',
      { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' });
  }

  ngOnInit() {
    this.isLoading = true;
    this.feedbackService.getChartFeedback().subscribe(data => {
        // let feedbackDisplayName;
        // let feedbackResultDisplayName;

        // this.translate.get('FEEDBACK').subscribe((res: string) => {
        //   feedbackDisplayName = res;
        // });
        // this.translate.get('FEEDBACK-RESULT').subscribe((res: string) => {
        //   feedbackResultDisplayName = res;
        // });
        // const positive = data.filter(f => f.sentiment === true && f.score > 0.05);
        // const negative = data.filter(f => f.sentiment === false && f.score < -0.05);
        // const neutral = data.filter(f => f.score >= -0.05 && f.score <= 0.05);
        // const temp = [];

        // temp.push({ name: 'Positive', value: positive.length });
        // temp.push({ name: 'Negative', value: negative.length });
        // temp.push({ name: 'Neutral', value: neutral.length });

        this.singleData = [...data];
        this.isLoading = false;
      }, error => {
        this.isLoading = false;
      });
  }

  ngAfterContentInit(): void {
  }
}
