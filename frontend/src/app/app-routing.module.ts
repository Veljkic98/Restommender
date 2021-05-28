import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './componenents/dashboard/dashboard.component';
import { HomePageComponent } from './componenents/home-page/home-page.component';

const routes: Routes = [
  {
    path: '',
    component: HomePageComponent,
    children: [
      // {
      //   path: 'login',
      //   component: LoginComponent,
      //   canActivate: [LoginGuard]
      // },
      {
        path: 'dashboard',
        component: DashboardComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
