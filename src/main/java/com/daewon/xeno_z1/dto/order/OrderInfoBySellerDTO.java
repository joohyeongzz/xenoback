package com.daewon.xeno_z1.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoBySellerDTO {
    private Long orderID;
    private Long orderNumber;
    private int quantity;
    private String productName;
    private String color;
    private String size;
    private String status;
    private long amount;
    private String orderDate;
    private String req;
    private String customerName;
}
