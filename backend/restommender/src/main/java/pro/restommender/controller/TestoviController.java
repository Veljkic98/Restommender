package pro.restommender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pro.restommender.service.Testovi;

@RestController
@RequestMapping("testovi")
public class TestoviController {

  @Autowired
  Testovi testovi;

  @GetMapping
  public ResponseEntity<?> runtests() {
    try {
      // testovi.testRestourantsCategory();
      // testovi.testLocation();
      testovi.testReservationDiscount();
      // testovi.testRestourantMusic();
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
