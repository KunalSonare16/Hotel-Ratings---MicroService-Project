package com.Ratings.controller;

import com.Ratings.entities.Rating;
import com.Ratings.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating){
      Rating rating1 =  ratingService.create(rating);
      return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getall(){
        List<Rating> listOfAll = ratingService.getall();
        return ResponseEntity.ok(listOfAll);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> findByUserID(@PathVariable String userId){
        List<Rating> listOfAll = ratingService.getallUsers(userId);
        return ResponseEntity.ok(listOfAll);
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> findByHotelID(@PathVariable String hotelId){
        List<Rating> listOfAll = ratingService.getAllHotels(hotelId);
        return ResponseEntity.ok(listOfAll);
    }


}
