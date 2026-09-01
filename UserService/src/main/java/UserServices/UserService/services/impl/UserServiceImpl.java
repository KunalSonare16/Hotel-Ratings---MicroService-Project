package UserServices.UserService.services.impl;

import UserServices.UserService.entities.Hotel;
import UserServices.UserService.entities.Rating;
import UserServices.UserService.entities.User;
import UserServices.UserService.exceptions.ResourceNotFoundException;
import UserServices.UserService.external.services.HotelService;
import UserServices.UserService.repositories.UserRepository;
import UserServices.UserService.services.UserService;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User saveUser(User user) {
       String userId =  UUID.randomUUID().toString();
       user.setUserId(userId);
       return userRepository.save(user);
    }

    @Override
    public List<User> getall() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
       User user =  userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with this user id is not found on the Server!!!! userId : " + userId));
             Rating[] ratingsOfUser = restTemplate.getForObject("http://RATINGS/ratings/users/"+user.getUserId(), Rating[].class);
       List<Rating> ratings =  Arrays.stream(ratingsOfUser).toList();
       List<Rating> ratingList = ratings.stream().map(rating ->{
         //  ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL/hotel/"+rating.getHotelId(),Hotel.class);
           Hotel hotel = hotelService.getHotel(rating.getHotelId());
           rating.setHotel(hotel);
           return rating;
       }).collect(Collectors.toList());
             user.setRatings(ratingList);
       return user;
    }

    @Override
    public User deleteUser(String userID) {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User not found to be deleted " + userID));
         userRepository.deleteById(userID);
         return user;
    }

    @Override
    public User updateUser(User user, String userID) {
        User existingUser = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User not found to be deleted " + userID));
        existingUser.setEmail(user.getEmail());
        existingUser.setName(user.getName());
        existingUser.setAbout(user.getAbout());
        existingUser.setUserId(user.getUserId());
        userRepository.save(existingUser);
        return existingUser;
    }
}
