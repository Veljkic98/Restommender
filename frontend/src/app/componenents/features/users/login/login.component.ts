import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  loading = false;
  submitted = false;
  error: string;
  success = false;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authService: AuthService,
    private snackBar: MatSnackBar,
  ) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: ['mail2@gmail.com', [Validators.email, Validators.required]],
      password: ['123', [Validators.required, Validators.minLength(3)]],
    });
  }

  get f(): any { return this.loginForm.controls; }

  onSubmit(): void {
    this.submitted = true;

    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.success = false;
    this.error = '';

    this.authService.login(this.loginForm.value.email, this.loginForm.value.password)
      .subscribe(
        data => {
          const payload = JSON.parse(window.atob(data.accessToken.split('.')[1]));
          console.log(payload)
          
          this.loading = false;
          this.success = true;

          localStorage.setItem('user', JSON.stringify({
            username: this.loginForm.value.email,
            password: this.loginForm.value.password,  // password je zbog logovanja na hospital app
            token: data.accessToken,
            id: payload.id,
            role: payload.role
          }));

          if (payload.role === "USER") {
            this.router.navigate(['/dashboard']);
          } else {
            this.router.navigate(['']);
          }
        },
        error => {
          this.error = error.error ? error.error.message : 'Your account is not verified';
          this.loading = false;
          
          if (error.error) {
            this.openSnackBar(error.error);
          }
        }
      );
  }

  openSnackBar(message: string): void {
    this.snackBar.open(message, 'Dismiss', {
      verticalPosition: 'top',
      duration: 4000,
    });
  }

}
