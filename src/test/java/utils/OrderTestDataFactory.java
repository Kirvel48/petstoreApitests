package utils;

import models.CreateOrderModel;

public class OrderTestDataFactory {
    public static CreateOrderModel orderModel() {
        FakerOrderModelsMethods fakeOrder = new FakerOrderModelsMethods();

        return CreateOrderModel.builder()
                .id(fakeOrder.getId())
                .petId(fakeOrder.getPetId())
                .quantity(fakeOrder.getQuantity())
                .shipDate(fakeOrder.getShipDate())
                .status(fakeOrder.getStatus())
                .complete(fakeOrder.isComplete())
                .build();
    }
}
