package tests;

import data.TestsData;
import io.qameta.allure.Owner;
import models.LoginTestsModel;
import models.LoginTestsResponseModel;
import models.UserInfoTestResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static spec.Spec.*;

@Tag("Login")
@DisplayName("Тесты учетной записи пользователя")
public class LoginTests extends TestBase {
    TestsData testsData = new TestsData();
    LoginTestsModel loginTestsModel = new LoginTestsModel();


    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Owner("Тётушкин К.И.")
    void UserRegistrationTest() {
        loginTestsModel.setId(testsData.id);
        loginTestsModel.setUsername(testsData.username);
        loginTestsModel.setPassword(testsData.password);
        loginTestsModel.setEmail(testsData.email);
        loginTestsModel.setUserStatus(testsData.userStatus);
        loginTestsModel.setFirstName(testsData.firstName);
        loginTestsModel.setLastName(testsData.lastName);
        loginTestsModel.setPhone(testsData.phone);
        LoginTestsResponseModel response = step("Отправка запроса на ренистрацию", () -> given(requestSpec)
                .body(loginTestsModel)
                .when()
                .post("user")
                .then()
                .spec(responseSpec)
                .extract().as(LoginTestsResponseModel.class));
        step("Проверка ответа", () ->
                assertEquals(200, response.getCode()));
    }


    @Test
    @DisplayName("Получение информации о пользователе")
    @Owner("Тётушкин К.И.")
    void getUserInfoTest() {
        UserInfoTestResponseModel response = step("Отправка запроса", () -> given(requestSpec)
                .when()
                .get("user/" + testsData.username)
                .then()
                .spec(responseSpec)
                .extract().as(UserInfoTestResponseModel.class));
        step("Проверка ответа", () -> {
            assertEquals(testsData.id, response.getId());
            assertEquals(testsData.userStatus, response.getUserStatus());
            assertEquals(testsData.username, response.getUsername());
            assertEquals(testsData.firstName, response.getFirstName());
            assertEquals(testsData.lastName, response.getLastName());
            assertEquals(testsData.email, response.getEmail());
            assertEquals(testsData.phone, response.getPhone());
        });


    }

    @Test
    @DisplayName("Удаление пользователя")
    @Owner("Тётушкин К.И.")
    void deleteUserTest() {
        UserRegistrationTest();
        LoginTestsResponseModel response = step("Отправка запроса", () -> given(requestSpec)
                .when()
                .delete("user/" + testsData.username)
                .then()
                .spec(responseSpec)
                .extract().as(LoginTestsResponseModel.class));
        step("Проверка ответа", () ->
                assertEquals(200, response.getCode()));


    }
}
