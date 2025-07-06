package models;

import lombok.*;

@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginTestsModel {
    String username;
    String firstName;
    String lastName;
    String email;
    String password;
    String phone;
    int id;
    int userStatus;
}

