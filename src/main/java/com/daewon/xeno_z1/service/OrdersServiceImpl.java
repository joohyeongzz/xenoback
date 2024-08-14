package com.daewon.xeno_z1.service;

import com.daewon.xeno_z1.domain.*;
import com.daewon.xeno_z1.dto.auth.GetOneDTO;
import com.daewon.xeno_z1.dto.order.*;
import com.daewon.xeno_z1.dto.page.PageInfinityResponseDTO;
import com.daewon.xeno_z1.dto.page.PageRequestDTO;
import com.daewon.xeno_z1.dto.product.ProductHeaderDTO;
import com.daewon.xeno_z1.dto.review.ReviewCardDTO;
import com.daewon.xeno_z1.exception.UserNotFoundException;
import com.daewon.xeno_z1.repository.*;
import io.jsonwebtoken.io.IOException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.weaver.ast.Or;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final UserRepository userRepository;
    private final OrdersRepository ordersRepository;
    private final ProductsColorSizeRepository productsColorSizeRepository;
    private final ProductsImageRepository productsImageRepository;
    private final ProductsSellerRepository productsSellerRepository;
    private final ReviewRepository reviewRepository;


    @Value("${org.daewon.upload.path}")
    private String uploadPath;

    public byte[] getImage(String uuid, String fileName) throws IOException, java.io.IOException {
        String filePath = uploadPath + uuid + "_" + fileName;
        // 파일을 바이트 배열로 읽기
        Path path = Paths.get(filePath);
        byte[] image = Files.readAllBytes(path);
        return image;
    }


    @Override
    public List<OrdersListDTO> getAllOrders(Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        log.info("user: " + userId);
        List<Orders> orders = ordersRepository.findByUser(user);
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public String getLatestReqForUser(String email) {
        return ordersRepository.findTopByUserEmailOrderByCreateAtDesc(email)
                .map(Orders::getReq)
                .orElse(null);
    }

    @Transactional
    @Override
    public List<OrdersDTO> createOrders(List<OrdersDTO> ordersDTO, String email) {


        Users users = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없음"));

        String orderPayId = ordersDTO.get(0).getOrderPayId();

        Long orderNumber = generateOrderNumber();

        List<Orders> savedOrders = new ArrayList<>();

        for(OrdersDTO dto : ordersDTO) {
            Orders orders  = Orders.builder()
                .orderPayId(orderPayId)
                .orderNumber(orderNumber)
                .productsColorSize(findProductColorSize(dto.getProductColorSizeId()))
                .user(users)
                .status("결제 완료")
                .req(dto.getReq())
                .quantity(dto.getQuantity())
                .amount(dto.getAmount())
                .build();
            savedOrders.add(ordersRepository.save(orders));
        }

        // 저장된 주문들을 DTO로 변환하여 반환
        return savedOrders.stream()
                .map(this::convertToDT1)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    // 데이터 일관성 보장(address, phoneNumber 둘 다 맞는 값이어야지 저장이 돼야 함)
    // 예외 처리(처리 중 예외가 발생하면 DB를 rollBack 시키기 위해)
    // Transactinal을 사용함
    public void updateUserDeliveryInfo(String email, String address, String phoneNumber) {
        Users users = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        users.setAddress(address);
        users.setPhoneNumber(phoneNumber);

        userRepository.save(users);
    }

    @Transactional(readOnly = true)
    @Override
    public OrdersConfirmDTO confirmOrder(Long orderId, String email) {

        // ** 주의사항 ** 주문한 사람의 토큰값이 아니면 Exception에 걸림.
        Orders orders = ordersRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("주문내역을 찾을 수 없습니다."));
        log.info("orders: " + orders);
        log.info("email: " + email);

        // 주문한 사용자와 현재 인증된 사용자가 일치하는지 확인
        if (!orders.getUser().getEmail().equals(email)) {
            throw new UserNotFoundException("User not found");
        }

        return new OrdersConfirmDTO(
                orders.getOrderId(),
                orders.getOrderPayId(),
                String.valueOf(orders.getOrderNumber()),
                orders.getUser().getName(),
                orders.getUser().getAddress(),
                orders.getAmount(),
                orders.getQuantity()
        );
    }

    @Override
    public OrdersListDTO convertToDTO(Orders orders) {
        OrdersListDTO ordersListDTO = new OrdersListDTO();

        ordersListDTO.setReq(orders.getReq());
        ordersListDTO.setProductColorSizeId(orders.getProductsColorSize().getProductColorSizeId());
        ordersListDTO.setOrderNumber(orders.getOrderNumber());
        ordersListDTO.setOrderDate(orders.getCreateAt());
        ordersListDTO.setBrandName(orders.getProductsColorSize().getProductsColor().getProducts().getBrandName());
        ordersListDTO.setStatus(orders.getStatus());
        ordersListDTO.setAmount(orders.getAmount());
        ordersListDTO.setQuantity(orders.getQuantity());

        // GetOneDTO 리스트 생성 및 설정
        List<GetOneDTO> getOneList = new ArrayList<>();
        getOneList.add(createGetOneDTO(orders.getUser()));
        ordersListDTO.setGetOne(getOneList);

        return ordersListDTO;
    }

    private GetOneDTO createGetOneDTO(Users users) {
        return new GetOneDTO(users.getPhoneNumber(), users.getAddress());
    }

    // 주문번호 orderNumber 랜덤생성
    private Long generateOrderNumber() {
        long timestamp = System.currentTimeMillis();
        long random = new Random().nextInt(1000000); // 6자리 랜덤 숫자

        // timestamp를 왼쪽으로 20비트 시프트하고 랜덤 값을 더함
        return (timestamp << 20) | random;
    }

    // 영문 대소문자, 숫자, 특수문자 -, _, =로 이루어진 6자 이상 64자 이하의 문자열 이어야함.
    // 위 조건에 해당하는 랜덤 orderPayId값 생성
    private String generateOrderPayId(String ord) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_=";
        String specialChars = "-_=";
        StringBuilder stringBuilder = new StringBuilder(specialChars);
        Random random = new Random();
        int length = random.nextInt(59) + 6; // 6 to 64 characters

        for (int i = specialChars.length(); i < length; i++) {
            stringBuilder.append(chars.charAt(random.nextInt(chars.length())));
        }

        List<Character> charList = new ArrayList<>();
        for (char c : stringBuilder.toString().toCharArray()) {
            charList.add(c);
        }
        Collections.shuffle(charList);

        StringBuilder orderPayId = new StringBuilder();
        for (char c : charList) {
            orderPayId.append(c);
        }
        return orderPayId.toString();
    }

    private ProductsColorSize findProductColorSize(Long productColorSizeId) {
        return productsColorSizeRepository.findById(productColorSizeId)
                .orElseThrow(() -> new EntityNotFoundException("ProductsColorSize not found with id: " + productColorSizeId));
    }


    @Override
    public PageInfinityResponseDTO<OrdersCardListDTO> getOrderCardList(PageRequestDTO pageRequestDTO,String email) {


        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPageIndex() <= 0 ? 0 : pageRequestDTO.getPageIndex() - 1,
                pageRequestDTO.getSize(),
                Sort.by("orderId").ascending());

        Users users = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found"));

        Page<Orders> orders = ordersRepository.findPagingOrdersByUser(pageable,users);

        List<OrdersCardListDTO> dtoList = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");




                for(Orders order : orders.getContent()) {
                OrdersCardListDTO dto = new OrdersCardListDTO();
                Review reviews = reviewRepository.findByUsersAndOrders(users,order);
                if(reviews != null) {
                    dto.setReview(true);
                    dto.setReviewId(reviews.getReviewId());
                } else{
                    dto.setReview(false);
                }

                dto.setOrderId(order.getOrderId());
                dto.setOrderDate(order.getCreateAt().format(formatter));
                dto.setStatus(order.getStatus());
                dto.setAmount(order.getAmount());
                dto.setQuantity(order.getQuantity());
                dto.setColor(order.getProductsColorSize().getProductsColor().getColor());
                dto.setSize(order.getProductsColorSize().getSize().name());
                dto.setBrandName(order.getProductsColorSize().getProductsColor().getProducts().getBrandName());
                dto.setProductName(order.getProductsColorSize().getProductsColor().getProducts().getName());
                dto.setProductColorId(order.getProductsColorSize().getProductsColor().getProductColorId());
                ProductsImage productsImage = productsImageRepository.findFirstByProductColorId(order.getProductsColorSize().getProductsColor().getProductColorId());
                if (productsImage != null) {
                    try {
                        byte[] data = getImage(productsImage.getUuid(), productsImage.getFileName());
                        dto.setProductImage(data);
                    } catch (java.io.IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    dto.setProductImage(null);
                }
                dtoList.add(dto);
            }


        return PageInfinityResponseDTO.<OrdersCardListDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .totalIndex((int) orders.getTotalElements())
                .build();
    }

    @Override
    public ProductHeaderDTO getProductHeader(Long orderId, String email) {

        Users users = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found"));

        log.info(email);
        log.info(orderId);

        log.info(users.getUserId());
        Orders orders = ordersRepository.findByOrderIdAndUserId(orderId,users);
        log.info(orders);
        ProductHeaderDTO dto = new ProductHeaderDTO();
        dto.setProductColorId(orders.getProductsColorSize().getProductsColor().getProductColorId());
        dto.setName(orders.getProductsColorSize().getProductsColor().getProducts().getName());
        dto.setColor(orders.getProductsColorSize().getProductsColor().getColor());

        return dto;
    }

    private OrdersDTO convertToDT1(Orders order) {
        return new OrdersDTO(
                order.getOrderPayId(),
                order.getProductsColorSize().getProductColorSizeId(),
                order.getReq(),
                order.getQuantity(),
                order.getAmount()
        );
    }

    @Override
    public List<OrderInfoBySellerDTO> getOrderListBySeller(String email) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        Users user = userRepository.findByEmail(email).orElse(null);

        List<ProductsSeller> productsSellerList = productsSellerRepository.findByUsers(user);
        List<OrderInfoBySellerDTO> list = new ArrayList<>();
        for(ProductsSeller productsSeller : productsSellerList){
            List<Orders> orders = ordersRepository.findByProductId(productsSeller.getProducts().getProductId());
            for(Orders order : orders) {
                OrderInfoBySellerDTO dto = new OrderInfoBySellerDTO();
                dto.setOrderID(order.getOrderId());
                dto.setOrderNumber(order.getOrderNumber());
                dto.setQuantity(order.getQuantity());
                dto.setSize(order.getProductsColorSize().getSize().name());
                dto.setColor(order.getProductsColorSize().getProductsColor().getColor());
                dto.setStatus(order.getStatus());
                dto.setProductName(order.getProductsColorSize().getProductsColor().getProducts().getName());
                dto.setOrderDate(order.getCreateAt().format(formatter));
                dto.setReq(order.getReq());
                dto.setAmount(order.getAmount());
                dto.setCustomerName(order.getUser().getName());
                list.add(dto);
            }
        }

        return list;
    }

    @Override
    public void updateOrderStatusBySeller(OrdersStatusUpdateDTO dto) {
        Orders orders = ordersRepository.findById(dto.getOrderId()).orElse(null);

        assert orders != null;
        orders.setStatus(dto.getStatus());

        ordersRepository.save(orders);

        log.info(orders);



    }
}
