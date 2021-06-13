package pro.restommender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pro.restommender.model.MyRule;
import pro.restommender.service.MyRuleService;

@RestController
@RequestMapping(path = "/api/rules")
public class MyRuleController {
  
  @Autowired
  MyRuleService myRuleService;

  @PostMapping
  public ResponseEntity<?> add(@RequestBody MyRule rule) {
    try {
      myRuleService.add(rule);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}