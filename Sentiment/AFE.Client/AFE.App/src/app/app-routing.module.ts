import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


// *******************************************************************************
// Layouts


import { DashboardComponent } from './dashboard/dashboard.component';
import { FeedbackComponent } from './feedback/feedback.component';
import { FeedbackResultsComponent } from './feedback-results/feedback-results.component';


// *******************************************************************************
// Routes

/* tslint:disable */
const routes: Routes = [
  {
    path: 'dashboard',
    component: DashboardComponent
  },
  {
    path: 'feedback',
    component: FeedbackComponent,
  },
  {
    path: 'feedback-results',
    component: FeedbackResultsComponent,
  },
  {
    path: 'error',
    loadChildren: './shared/error-wrapper/error-wrapper.module#ErrorWrapperModule',
  },
  {
    path: '**',
    redirectTo: '/404'
  }
];
/* tslint:enable */

// *******************************************************************************
//

@NgModule({
  imports: [RouterModule.forRoot(
    routes,
    {
      enableTracing: false
    }
  )],
  exports: [RouterModule]
})
export class AppRoutingModule {}
