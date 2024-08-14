package com.daewon.xeno_z1.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductsSearchDTO {

    private Long productColorId;

    private String brandName;

    private String name;

    private String category;

    private String categorySub;

    private long price;

    private long priceSale;

    private boolean isSale;

    private boolean isLike;

    private byte[] productImage;

}
