package UserServices.UserService.services.impl;

import UserServices.UserService.entities.User;
import UserServices.UserService.exceptions.ResourceNotFoundException;
import UserServices.UserService.repositories.UserRepository;
import UserServices.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

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
        return userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with this user id is not found on the Server!!!! userId : " + userId));
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
