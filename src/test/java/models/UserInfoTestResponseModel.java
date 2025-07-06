package models;

import lombok.Data;

@Data
public class UserInfoTestResponseModel {
    String username;
    String firstName;
    String lastName;
    String email;
    String password;
    String phone;
    int id;
    int userStatus;
}
