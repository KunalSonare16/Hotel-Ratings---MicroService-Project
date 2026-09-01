package com.Ratings.service;

import com.Ratings.entities.Rating;

import java.util.List;

public interface RatingService {
    Rating create(Rating rating);
    List<Rating> getall();
    List<Rating> getallUsers(String userId);
    List<Rating> getAllHotels(String hotelId);
}
