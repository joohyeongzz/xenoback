package com.daewon.xeno_z1.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Orders extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long orderId;

  @Column(length = 64, nullable = false)
  private String orderPayId;

  @ManyToOne()  // fetch = FetchType.LAZY
  @JoinColumn(name = "productColorSizeId", referencedColumnName = "productColorSizeId")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private ProductsColorSize productsColorSize;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userId", referencedColumnName = "userId")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Users user;

  @Column(nullable = false)
  private Long orderNumber;

  private String status;

  // 고객의 요청사항
  private String req;

  private int quantity;

  // 총 합 가격
  private Long amount;

}
