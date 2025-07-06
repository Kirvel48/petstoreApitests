package api;

import models.CreateOrderModel;
import models.LoginTestsResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static spec.Specs.*;
import static org.assertj.core.api.Assertions.assertThat;


public class OrderApi {


    public static CreateOrderModel createOrder(CreateOrderModel orderModel) {
        return step("Создание заказа", () ->
                given(requestSpec)
                        .when()
                        .body(orderModel)
                        .post("store/order")
                        .then()
                        .spec(responseSpec200)
                        .extract().as(CreateOrderModel.class));
    }

    public static void checkOrder(int id) {
        CreateOrderModel response = step("Отправка запроса", () ->
                given(requestSpec)
                        .when()
                        .get("store/order/" + id)
                        .then()
                        .spec(responseSpec200)
                        .extract().as(CreateOrderModel.class));

        step("Проверка ответа", () -> {
            assertThat(response.getId()).isEqualTo(id);
        });

    }

    public static void checkDeleteOrder(int id) {
        LoginTestsResponseModel response = step("Отправка запроса", () ->
                given(requestSpec)
                        .when()
                        .get("store/order/" + id)
                        .then()
                        .spec(responseSpec404)
                        .extract().as(LoginTestsResponseModel.class));

        step("Проверка ответа", () -> {
            assertThat(response.getMessage()).isEqualTo("Order not found");
            assertThat(response.getType()).isEqualTo("error");
        });
    }
}