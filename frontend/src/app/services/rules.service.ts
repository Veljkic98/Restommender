import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Rule } from '../model/Rule.model';

@Injectable({
  providedIn: 'root'
})
export class RulesService {
  private headers = new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': "Bearer " + JSON.parse(localStorage.getItem('user')).token });

  constructor(private http: HttpClient) { }

  async addRule(rule: Rule) {
    let rulePromise = this.http.post(`https://localhost:8080/api/rules`, rule, {headers: this.headers}).toPromise();
    return rulePromise;
  }
}
