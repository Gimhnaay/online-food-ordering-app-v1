package org.example.requests;

import lombok.Data;
import org.example.model.Address;

@Data
public class OrderRequest {
    private Long restaurantId;
    private Address deliveryAddress;
}
