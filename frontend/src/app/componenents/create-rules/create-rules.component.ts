import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Rule } from 'src/app/model/Rule.model';
import { RulesService } from 'src/app/services/rules.service';

@Component({
  selector: 'app-create-rules',
  templateUrl: './create-rules.component.html',
  styleUrls: ['./create-rules.component.css']
})
export class CreateRulesComponent implements OnInit {
  spinnerActive = false;

  content = `package sbnz;

import pro.restommender.model.Restaurant;
import pro.restommender.model.RelevantRestaurants;
import pro.restommender.dto.Search;
import java.util.List;

rule "Example rule - Classify Restaurant - In Center"
  agenda-group "location"
  when
    $search: Search(location <= 1)
    $restaurants: RelevantRestaurants()
    $filteredRestaurants: List() from collect(Restaurant(location <= 1) from $restaurants.getRelevantRestaurants())
  then 
    modify( $restaurants) { setRelevantRestaurants($filteredRestaurants); }
    System.out.println( "in center------------------" );
end
`;
    constructor(
    private rulesService: RulesService,
    private snackBar: MatSnackBar,
  ) { }


  ngOnInit(): void {
  }

  extractRuleName(): string {
    let ruleName = this.content.match(/rule\s+"(.*)"/);
    // console.log(ruleName)
    return ruleName?.[1];
  }
  async createRule() {
    let rule: Rule = {content: this.content, name: this.extractRuleName()} ;
    this.spinnerActive = true;
    try {
      await this.rulesService.addRule(rule);
      this.openSnackBar('successfully added rule')
    } catch (error) {
      console.log(error)
      this.openSnackBar(error.error);
    }
    finally {
      this.spinnerActive = false;
    }
  }
  openSnackBar(message: string): void {
    this.snackBar.open(message, 'Dismiss', {
      verticalPosition: 'top',
      duration: 4000,
    });
  }

}
