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
import static spec.Spec.*;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("Login")
@DisplayName("Тесты учетной записи пользователя")
@Owner("Тётушкин К.И.")
public class LoginTests extends TestBase {
    TestsData testsData = new TestsData();
    LoginTestsModel loginTestsModel = new LoginTestsModel();


    @Test
    @DisplayName("Успешная регистрация пользователя")
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
                assertThat(response.getCode())).isEqualTo(200);
    }


    @Test
    @DisplayName("Получение информации о пользователе")
    void getUserInfoTest() {
        UserInfoTestResponseModel response = step("Отправка запроса", () -> given(requestSpec)
                .when()
                .get("user/" + testsData.username)
                .then()
                .spec(responseSpec)
                .extract().as(UserInfoTestResponseModel.class));
        step("Проверка ответа", () -> {
            assertThat(response.getId()).isEqualTo(testsData.id);
            assertThat(response.getUserStatus()).isEqualTo(testsData.userStatus);
            assertThat(response.getUsername()).isEqualTo(testsData.username);
            assertThat(response.getFirstName()).isEqualTo(testsData.firstName);
            assertThat(response.getLastName()).isEqualTo(testsData.lastName);
            assertThat(response.getEmail()).isEqualTo(testsData.email);
            assertThat(response.getPhone()).isEqualTo(testsData.phone);
        });
    }

    @Test
    @DisplayName("Удаление пользователя")
    void deleteUserTest() {
        UserRegistrationTest();
        LoginTestsResponseModel response = step("Отправка запроса", () -> given(requestSpec)
                .when()
                .delete("user/" + testsData.username)
                .then()
                .spec(responseSpec)
                .extract().as(LoginTestsResponseModel.class));
        step("Проверка ответа", () ->
                assertThat(response.getCode())).isEqualTo(200);
    }
}
