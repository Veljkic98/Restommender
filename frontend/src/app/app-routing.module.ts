import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './componenents/dashboard/dashboard.component';
import { RestaurantsComponent } from './componenents/features/restaurant/restaurants/restaurants.component';
import { LoginComponent } from './componenents/features/users/login/login.component';
import { UsersComponent } from './componenents/features/users/users/users.component';
import { HomePageComponent } from './componenents/home-page/home-page.component';
import { LoginGuard } from './guards/login.guard';

const routes: Routes = [
  {
    path: '',
    component: HomePageComponent,
    children: [
      {
        path: 'dashboard',
        component: DashboardComponent,
        canActivate: [LoginGuard],
        data: { expectedRoles: 'ADMIN' }
      },
      {
        path: 'login',
        component: LoginComponent,
      },
      {
        path: 'users',
        component: UsersComponent,
        canActivate: [LoginGuard],
        data: { expectedRoles: 'ADMIN' }
      },
      {
        path: 'restaurants',
        component: RestaurantsComponent,
        canActivate: [LoginGuard],
        data: { expectedRoles: 'ADMIN' }
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
