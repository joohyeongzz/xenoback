package com.daewon.xeno_z1.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductsStarRankListDTO {

    private Long productColorId;

    private String brandName;

    private String name;
    
    private Long price;

    private boolean isSale;

    private Long priceSale;

    private String category;

    private String categorySub;

    private boolean isLike;

    private byte[] productImage;
    
}
