import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { SweetAlert2Module, SwalComponent } from '@sweetalert2/ngx-sweetalert2';
import { ToastrModule } from 'ngx-toastr';
import { NgSelectModule } from '@ng-select/ng-select';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { NgxChartsModule } from '@swimlane/ngx-charts';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';

import { TranslateLoader, TranslateModule, TranslateService } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { FeedbackService } from './services/feedback/feedback.service';

import { FeedbackComponent } from './feedback/feedback.component';
import { FeedbackResultsComponent } from './feedback-results/feedback-results.component';
import { NgxSpinnerModule } from 'ngx-spinner';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

export function createTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}


@NgModule({
  declarations: [
    AppComponent,
    FeedbackComponent,
    FeedbackResultsComponent,
  ],
  imports: [
    NgxChartsModule,
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    SweetAlert2Module.forRoot(),
    ToastrModule.forRoot({
      newestOnTop: true
    }),

    MatTableModule,
    MatPaginatorModule,
    MatInputModule,
    MatSelectModule,
    MatFormFieldModule,

    NgSelectModule,
    TranslateModule.forRoot({
      defaultLanguage: navigator.language || navigator['userLanguage'],
      loader: {
        provide: TranslateLoader,
        useFactory: (createTranslateLoader),
        deps: [HttpClient]
      }
    }),
    NgxSpinnerModule,
    NgbModule,
  ],
  providers: [
    TranslateService,
    FeedbackService,
    SwalComponent
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }