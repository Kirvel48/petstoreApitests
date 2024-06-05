package api;

import data.TestsData;
import models.CreateOrderModel;
import models.LoginTestsResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static spec.Spec.*;

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
            assertEquals(petId, response.getPetId());
            assertEquals(id, response.getId());
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
            assertEquals("Order not found", response.getMessage());
            assertEquals("error", response.getType());
        });
    }
}