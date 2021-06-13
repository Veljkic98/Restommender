import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatToolbarModule } from "@angular/material/toolbar";
import { MatButtonModule } from '@angular/material/button';
import { RouterModule } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { HomePageComponent } from './home-page/home-page.component';
import { MatTableModule } from '@angular/material/table';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDialogModule } from '@angular/material/dialog';

import { DashboardComponent } from './dashboard/dashboard.component';
import {MatChipsModule} from '@angular/material/chips';
import { SearchComponent } from './search/search.component';
import {MatRadioModule} from '@angular/material/radio';

import { LoginComponent } from './features/users/login/login.component';
import { UsersComponent } from './features/users/users/users.component';
import { RestaurantsComponent } from './features/restaurant/restaurants/restaurants.component';

@NgModule({
    declarations: [
        ToolbarComponent,
        HomePageComponent,
        DashboardComponent,
        SearchComponent,
        LoginComponent,
        UsersComponent,
        RestaurantsComponent,

    ],
    imports: [
        CommonModule,
        MatToolbarModule,
        MatButtonModule,
        RouterModule,
        MatIconModule,
        MatCardModule,
        MatFormFieldModule,
        FormsModule,
        ReactiveFormsModule,
        MatInputModule,
        MatSnackBarModule,
        MatTableModule,
        MatSelectModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatCheckboxModule,
        MatDialogModule,
        MatChipsModule,
        MatRadioModule
    ],
    providers: [
        MatDatepickerModule,
    ],
})
export class MaterialModule { }