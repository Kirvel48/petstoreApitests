package tests;

import api.OrderApi;
import io.qameta.allure.Owner;
import models.CreateOrderModel;
import models.LoginTestsResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.OrderTestDataFactory;


import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static spec.Specs.*;

@DisplayName("Тесты оформления заказов в магазине")
@Tag("Store")
@Owner("Тётушкин К.И.")
public class StoreTests extends TestBase {
    CreateOrderModel orderModel = OrderTestDataFactory.orderModel();

    @Test
    @DisplayName("Создание заказа")
    void createOrderTest() {

        CreateOrderModel response = step("Создание заказе", () ->
                OrderApi.createOrder(orderModel));
        step("Проверка параметров в созданном заказе", () -> {
            assertThat(response.getId()).isEqualTo(orderModel.getId());
            assertThat(response.getPetId()).isEqualTo(orderModel.getPetId());
            assertThat(response.getQuantity()).isEqualTo(orderModel.getQuantity());
            assertThat(response.isComplete()).isEqualTo(orderModel.isComplete());
        });
    }

    @Test
    @DisplayName("Получение информации о заказе")
    void checkCreateOrderTest() {
        step("Создание заказа", () -> OrderApi.createOrder(orderModel));
        CreateOrderModel response = step("Отправка запроса информации о заказе", () ->
                given(requestSpec)
                        .when()
                        .get("store/order/" + orderModel.getId())
                        .then()
                        .spec(responseSpec200)
                        .extract().as(CreateOrderModel.class));
        step("Проверка информации о заказе", () -> {
            assertThat(response.getId()).isEqualTo(orderModel.getId());
            assertThat(response.getStatus()).isEqualTo(orderModel.getStatus());
            assertThat(response.isComplete()).isEqualTo(orderModel.isComplete());
        });
    }

    @Test
    @DisplayName("Удаление заказа")
    void deleteOrderTest() {
        step("Создание заказа", () -> OrderApi.createOrder(orderModel));
        step("Проверка заказа", () -> OrderApi.checkOrder(orderModel.getId()));
        step("Удаление заказа", () -> given(requestSpec)
                .when()
                .delete("store/order/" + orderModel.getId())
                .then()
                .spec(responseSpec200)
                .extract().as(LoginTestsResponseModel.class));
        step("Заказ удален", () -> OrderApi.checkDeleteOrder(orderModel.getId()));
    }

}
