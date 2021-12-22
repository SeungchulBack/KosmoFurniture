package com.kosmo.kosmofurniture;

import com.kosmo.kosmofurniture.domain.Member;
import com.kosmo.kosmofurniture.domain.Order;
import com.kosmo.kosmofurniture.domain.OrderProduct;
import com.kosmo.kosmofurniture.domain.Product;
import com.kosmo.kosmofurniture.mapper.MemberMapper;
import com.kosmo.kosmofurniture.mapper.OrderMapper;
import com.kosmo.kosmofurniture.mapper.OrderProductMapper;
import com.kosmo.kosmofurniture.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class CommonBeans implements ApplicationListener<ContextClosedEvent> {

    private final MemberMapper memberMapper;
    private final ProductMapper productMapper;
    private final OrderMapper orderMapper;
    private final OrderProductMapper orderProductMapper;

//    @Bean
//    public CommandLineRunner initDB() {
//
//        return (args) -> {
//
//            // 일반사용자 데이터
//            for (int i = 1; i < 40; i++) {
//                Member member = new Member();
//                member.setAccount("kim" + i);
//                member.setPwd("1234");
//                member.setFullName("김찬기" + i);
//                member.setEmail("cl" + i + "@tt.com");
//                member.setAddress("서울 금천구 시흥대로" + i);
//                member.setPhone("01033334443");
//                member.setRole("ROLE_USER");
//                if (i<10) member.setSsn("930202-111220" + i);
//                if (i>=10) member.setSsn("930202-11122" + i);
//                member.setCreatedAt(LocalDateTime.now());
//
//                memberMapper.save(member);
//            }
//
//            //관리자 데이터
//            Member member = new Member();
//            member.setAccount("park");
//            member.setPwd("1234");
//            member.setFullName("박상준");
//            member.setEmail("cl@tt.com");
//            member.setAddress("서울 금천구 시흥대로");
//            member.setPhone("01033334444");
//            member.setRole("ROLE_ADMIN");
//            member.setSsn("930202-1112111");
//            member.setCreatedAt(LocalDateTime.now());
//
//            memberMapper.save(member);
//
//
//            //상품 데이터
//            for (int i = 0; i < 4; i++) {
//                String[] categories = {"chair", "desk", "bed", "sofa"};
//
//                for (int j = 0; j < 30; j++) {
//                    Product product = new Product();
//                    product.setName(categories[i] + j);
//                    product.setCategory(categories[i]);
//                    product.setDescription("Comfort " + categories[i] + j);
//                    product.setPrice(30000L + (j * 1000));
//                    product.setStock(10 + j);
//                    product.setCreatedAt(LocalDateTime.now());
//
//                    productMapper.save(product);
//                }
//            }
//
//            //주문 데이터
//            for (int i = 1; i < 6; i++) {
//
//                Order order = new Order();
//                order.setOrderStatus("processing");
//                order.setMemberId((long) i);
//                order.setCreatedAt(LocalDateTime.now());
//
//                orderMapper.save(order);
//
//                for (int j = 1; j < 4; j++) {
//                    OrderProduct orderProduct = new OrderProduct();
//                    orderProduct.setOrderId((long)i);
//                    orderProduct.setProductId((long)i);
//                    orderProduct.setQuantity(j);
//
//                    orderProductMapper.save(orderProduct);
//                }
//            }
//
//        };
//    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {

//        orderProductMapper.deleteAll();
//        orderProductMapper.setAutoIncToZero();
//
//        orderMapper.deleteAll();
//        orderMapper.setAutoIncToZero();
//
//        memberMapper.deleteAll();
//        memberMapper.setAutoIncToZero();
//
//        productMapper.deleteAll();
//        productMapper.setAutoIncToZero();
    }
}
