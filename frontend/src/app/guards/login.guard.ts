import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class LoginGuard implements CanActivate {
  
  constructor(
    public authService: AuthService,
    public router: Router
  ) { }

  canActivate(): boolean {
    if (this.authService.isLoggedIn()) {
      return true;
    }

    this.router.navigate(['/login']);

    //TODO: baciti neki alert koji kaze da nismo ulogovani za tu opciju i da se moramo ulogovati
    // ovo vr nece ni trebati jer korisnik ne moze da prostupi opcijama koje su mu nedostupne

    return false;
  }
  
}
