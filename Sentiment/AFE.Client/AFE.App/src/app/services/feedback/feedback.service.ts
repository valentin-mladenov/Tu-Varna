import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Feedback } from '../../models/feedback/feedback';

@Injectable()
export class FeedbackService {
  private url = 'http://localhost:5000/api/';

  constructor(private httpClient: HttpClient) {  }

  postFeedback(feedback: Feedback) {
    const url = this.url + 'feedback?api-version=1.0';
    return this.httpClient.post(url, feedback, {
      headers: this.getHeader()
    });
  }

  getChartFeedback() {
    const url = this.url + 'feedback/chart?api-version=1.0';

    return this.httpClient.get<Array<Feedback>>(url, {
      headers: this.getHeader()
    });
  }

  getAllFeedback() {
    const url = this.url + 'feedback?api-version=1.0';

    return this.httpClient.get<Array<Feedback>>(url, {
      headers: this.getHeader()
    });
  }

  updateUserFeedback(feedback: Feedback) {
    const url = this.url + 'feedback?api-version=1.0';

    return this.httpClient.put<any>(url, feedback, {
      headers: this.getHeader()
    });
  }

  retrainUserFeedbackModel() {
    const url = this.url + 'mlinputfb/retrain?api-version=1.0';

    return this.httpClient.post<any>(url, {}, {
      headers: this.getHeader()
    });
  }

  private getHeader() {
    return new HttpHeaders({
      // 'Authorization': `Bearer ${this.auth.accessToken}`
    });
  }
}
