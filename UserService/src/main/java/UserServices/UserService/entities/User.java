package UserServices.UserService.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "microp_users")
@Getter
@Setter
public class User {
     @Id
     private String userId;
     @Column(length = 20)
     private String name;
     private String email;
     private String about;
     @Transient
     private List<Rating> ratings;
}
