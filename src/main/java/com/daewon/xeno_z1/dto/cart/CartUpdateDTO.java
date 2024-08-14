package com.daewon.xeno_z1.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartUpdateDTO {

    private Long cartId;
    private Long quantity;
    private Long price;
}
