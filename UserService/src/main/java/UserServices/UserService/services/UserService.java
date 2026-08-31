package UserServices.UserService.services;

import UserServices.UserService.entities.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getall();
    User getUser(String userId);
    User deleteUser(String userID);
    User updateUser(User user , String userId);
}
