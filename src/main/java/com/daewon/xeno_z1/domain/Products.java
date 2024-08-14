package com.daewon.xeno_z1.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Products {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment와 같은
  private long productId;

  private String brandName;

  private String name;

  private String category;

  private String categorySub;

  private long price;

  private long priceSale;

  private boolean isSale;

  private String productNumber;

  private String season;

  public boolean getIsSale() {
    return isSale;
  }

  public void setIsSale(boolean isSale) {
      this.isSale = isSale;
  }
}
