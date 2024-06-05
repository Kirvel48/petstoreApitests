package models;

import lombok.Data;

@Data
public class UserInfoTestResponseModel {
    String username, firstName, lastName, email, password, phone;
    int id, userStatus;
}
