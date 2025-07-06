package tests;

import api.LoginApi;
import io.qameta.allure.Owner;
import models.LoginTestsModel;
import models.LoginTestsResponseModel;
import models.UserInfoTestResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.LoginTestDataFactory;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("Login")
@DisplayName("Тесты учетной записи пользователя")
@Owner("Тётушкин К.И.")
public class LoginTests extends TestBase {
    LoginTestsModel userModel = LoginTestDataFactory.userModel();


    @Test
    @DisplayName("Успешная регистрация пользователя")
    void userRegistrationTest() {

        LoginTestsResponseModel response = step("Регистрация пользователя", () ->
                LoginApi.registerUser(userModel));

        step("Проверка успешного ответа", () -> {
            assertThat(response.getCode()).isEqualTo(200);
        });
    }


    @Test
    @DisplayName("Получение информации о пользователе")
    void getUserInfoTest() {
        step("Регистрация пользователя", () -> LoginApi.registerUser(userModel));

        UserInfoTestResponseModel response = step("Получение информации о пользователе", () ->
                LoginApi.getUserInfo(userModel.getUsername()));

        step("Проверка данных пользователя", () -> {
            assertThat(response.getId()).isEqualTo(userModel.getId());
            assertThat(response.getUsername()).isEqualTo(userModel.getUsername());
            assertThat(response.getFirstName()).isEqualTo(userModel.getFirstName());
            assertThat(response.getLastName()).isEqualTo(userModel.getLastName());
            assertThat(response.getEmail()).isEqualTo(userModel.getEmail());
            assertThat(response.getPhone()).isEqualTo(userModel.getPhone());
            assertThat(response.getUserStatus()).isEqualTo(userModel.getUserStatus());
        });
    }

    @Test
    @DisplayName("Удаление пользователя")
    void deleteUserTest() {
        step("Регистрация пользователя", () -> LoginApi.registerUser(userModel));

        LoginTestsResponseModel response = step("Удаление пользователя", () ->
                LoginApi.deleteUser(userModel.getUsername()));

        step("Проверка успешного ответа", () -> {
            assertThat(response.getCode()).isEqualTo(200);
        });
        step("Проверка, что пользователь удален", () -> LoginApi.checkUserDeleted(userModel.getUsername()));
    }
}
