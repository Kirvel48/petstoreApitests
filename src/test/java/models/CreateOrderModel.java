package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderModel {
    int id;
    int petId;
    int quantity;
    String shipDate;
    String status;
    boolean complete;
}
