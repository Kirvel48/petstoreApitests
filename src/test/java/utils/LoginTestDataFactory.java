package utils;

import com.github.javafaker.Faker;
import models.LoginTestsModel;

public class LoginTestDataFactory {
    private static final FakeLoginModelsMethods fakeLogin = new FakeLoginModelsMethods();


    public static LoginTestsModel userModel() {

        return LoginTestsModel.builder()
                .username(fakeLogin.getUsername())
                .firstName(fakeLogin.getFirstName())
                .lastName(fakeLogin.getLastName())
                .email(fakeLogin.getEmail())
                .password(fakeLogin.getPassword())
                .phone(fakeLogin.getPhoneNumber())
                .userStatus(fakeLogin.getStatus())
                .id(fakeLogin.getId())
                .build();
    }
}
