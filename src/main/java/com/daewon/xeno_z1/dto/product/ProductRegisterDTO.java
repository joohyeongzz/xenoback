package com.daewon.xeno_z1.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRegisterDTO {



    private String brandName;

    private String category;

    private String categorySub;

    private String name;
    
    private Long price;

    private boolean sale;

    private Long priceSale;

    private String productNumber;

    private String season;

    private String colors; // 색상

    private List<ProductSizeDTO> size; // size 리스트

//    @JsonProperty("isSale")
//    public boolean isSale() {
//        return isSale;
//    }
//
//    @JsonProperty("isSale")
//    public void setSale(boolean sale) {
//        isSale = sale;
//    }


}
