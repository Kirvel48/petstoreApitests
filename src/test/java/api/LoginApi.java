package api;

import io.qameta.allure.Step;
import models.LoginTestsModel;
import models.LoginTestsResponseModel;
import models.UserInfoTestResponseModel;

import static io.restassured.RestAssured.given;
import static spec.Specs.*;

public class LoginApi {

    @Step("Регистрация пользователя")
    public static LoginTestsResponseModel registerUser(LoginTestsModel userModel) {
        return given(requestSpec)
                .body(userModel)
                .when()
                .post("user")
                .then()
                .spec(responseSpec200)
                .extract().as(LoginTestsResponseModel.class);
    }

    @Step("Получение информации о пользователе")
    public static UserInfoTestResponseModel getUserInfo(String username) {
        return given(requestSpec)
                .when()
                .get("user/" + username)
                .then()
                .spec(responseSpec200)
                .extract().as(UserInfoTestResponseModel.class);
    }

    @Step("Удаление пользователя")
    public static LoginTestsResponseModel deleteUser(String username) {
        return given(requestSpec)
                .when()
                .delete("user/" + username)
                .then()
                .spec(responseSpec200)
                .extract().as(LoginTestsResponseModel.class);
    }

    @Step("Проверка, что пользователь '{username}' удален")
    public static void checkUserDeleted(String username) {
        given(requestSpec)
                .when()
                .get("user/" + username)
                .then()
                .spec(responseSpec404);
    }
}