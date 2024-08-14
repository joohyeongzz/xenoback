package com.daewon.xeno_z1.service;

import com.daewon.xeno_z1.domain.Orders;
import com.daewon.xeno_z1.dto.order.*;
import com.daewon.xeno_z1.dto.page.PageInfinityResponseDTO;
import com.daewon.xeno_z1.dto.page.PageRequestDTO;
import com.daewon.xeno_z1.dto.product.ProductHeaderDTO;

import java.util.List;

public interface OrdersService {

    List<OrdersListDTO> getAllOrders(Long userId);

    List<OrdersDTO> createOrders(List<OrdersDTO> ordersDTO, String email);

    void updateUserDeliveryInfo(String email, String address, String phoneNumber);

    OrdersConfirmDTO confirmOrder(Long orderId, String email);

    OrdersListDTO convertToDTO(Orders orders);

    PageInfinityResponseDTO<OrdersCardListDTO> getOrderCardList(PageRequestDTO pageRequestDTO,String email);

    ProductHeaderDTO getProductHeader(Long orderId, String email);

   List<OrderInfoBySellerDTO> getOrderListBySeller(String email);

    void updateOrderStatusBySeller(OrdersStatusUpdateDTO dto);

    String getLatestReqForUser(String email);


}
