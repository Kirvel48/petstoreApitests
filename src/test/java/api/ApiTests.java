package api;

import models.CreateOrderModel;
import models.LoginTestsResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static spec.Spec.*;
import static org.assertj.core.api.Assertions.assertThat;


public class ApiTests {


    public static void createOrder(int id, int petId, int quantity, String shipDate, String status, boolean complete) {
        CreateOrderModel createOrderModel = new CreateOrderModel();
        createOrderModel.setId(id);
        createOrderModel.setPetId(petId);
        createOrderModel.setStatus(status);
        createOrderModel.setComplete(complete);
        createOrderModel.setQuantity(quantity);
        createOrderModel.setShipDate(shipDate);

        given(requestSpec)
                .when()
                .body(createOrderModel)
                .post("store/order")
                .then()
                .spec(responseSpec)
                .extract().as(CreateOrderModel.class);
    }

    public static void checkOrder(int id, int petId) {
        CreateOrderModel response = step("Отправка запроса", () ->
                given(requestSpec)
                        .when()
                        .get("store/order/" + id)
                        .then()
                        .spec(responseSpec)
                        .extract().as(CreateOrderModel.class));

        step("Проверка ответа", () -> {
            assertThat(response.getPetId()).isEqualTo(petId);
            assertThat(response.getId()).isEqualTo(id);
        });

    }

    public static void checkDeleteOrder(int id) {
        LoginTestsResponseModel response = step("Отправка запроса", () ->
                given(requestSpec)
                        .when()
                        .get("store/order/" + id)
                        .then()
                        .spec(responseError404Spec)
                        .extract().as(LoginTestsResponseModel.class));

        step("Проверка ответа", () -> {
            assertThat(response.getMessage()).isEqualTo("Order not found");
            assertThat(response.getType()).isEqualTo("error");
        });
    }
}