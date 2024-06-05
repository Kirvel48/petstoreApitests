package models;

import lombok.Data;

@Data
public class CreateOrderModel {
    String shipDate, status;
    int id, petId, quantity;
    boolean complete;
}
