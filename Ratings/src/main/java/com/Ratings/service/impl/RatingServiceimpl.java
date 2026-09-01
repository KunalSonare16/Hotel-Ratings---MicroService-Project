package com.Ratings.service.impl;

import com.Ratings.entities.Rating;
import com.Ratings.repositories.RatingRepository;
import com.Ratings.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceimpl  implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating create(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getall() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getallUsers(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getAllHotels(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
