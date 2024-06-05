package models;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class LoginTestsModel {
    String username, firstName, lastName, email, password, phone;
    int id, userStatus;
}

