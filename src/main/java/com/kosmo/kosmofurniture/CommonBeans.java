package com.kosmo.kosmofurniture;

import com.kosmo.kosmofurniture.domain.*;
import com.kosmo.kosmofurniture.mapper.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.*;

@Configuration
@RequiredArgsConstructor
public class CommonBeans implements ApplicationListener<ContextClosedEvent> {

    private final MemberMapper memberMapper;
    private final ProductMapper productMapper;
    private final OrderMapper orderMapper;
    private final OrderProductMapper orderProductMapper;
    private final ProductImageMapper productImageMapper;

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();

        Map<String, List<String>> roleHierarchyMap = new HashMap<>();
        roleHierarchyMap.put("ROLE_ADMIN", Arrays.asList("ROLE_MANAGER"));
        roleHierarchyMap.put("ROLE_MANAGER", Arrays.asList("ROLE_USER"));
        roleHierarchyMap.put("ROLE_USER", Arrays.asList("ROLE_ANONYMOUS"));

        String roles = RoleHierarchyUtils.roleHierarchyFromMap(roleHierarchyMap);
        roleHierarchy.setHierarchy(roles);

        return roleHierarchy;
    }

    @Bean(name = "roleExpressionHandler")
    public SecurityExpressionHandler<FilterInvocation> expressionHandler() {
        DefaultWebSecurityExpressionHandler webSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
        webSecurityExpressionHandler.setRoleHierarchy(roleHierarchy());
        return webSecurityExpressionHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public CommandLineRunner initDB() {
//
//        return (args) -> {
//
//            String fileLocation;
//
//            for (int i = 30; i < 52; i++) {
//                fileLocation = "C:\\temp\\images\\bed" + (i - 30) + ".png";
//                File file = new File(fileLocation);
//                byte[] bytes = Files.readAllBytes(file.toPath());
//
//                Calendar c = Calendar.getInstance();
//                int year = c.get(Calendar.YEAR);
//                int month = c.get(Calendar.MONTH) + 1;
//                int date = c.get(Calendar.DATE);
//
//                //파일이름생성 코드
//                Random r = new Random();
//                int random = r.nextInt(1000000);
//                int index = file.getName().lastIndexOf(".");
//                String fileExtension = file.getName().substring(index + 1); /*확장자추출*/
//                String dbFileName = "product_image_" + year + month + date + random + "." + fileExtension;
//                new FileOutputStream("C:\\Users\\user\\projects\\KosmoFurniture\\files" + "\\" + dbFileName).write(bytes);
//
//                ProductImage productImage = new ProductImage();
//
//                productImage.setProductId(Long.valueOf(i));
//                productImage.setOriginalFileName(file.getName());
//                productImage.setDbFileName(dbFileName);
//                productImageMapper.save(productImage);
//            }
//
//
//
//        // 일반사용자 데이터
//        for (int i = 1; i < 40; i++) {
//            Member member = new Member();
//            member.setAccount("kim" + i);
//            member.setPwd("1234");
//            member.setFullName("김찬기" + i);
//            member.setEmail("cl" + i + "@tt.com");
//            member.setAddress("서울 금천구 시흥대로" + i);
//            member.setPhone("01033334443");
//            member.setRole("ROLE_USER");
//            if (i < 10) member.setSsn("930202-111220" + i);
//            if (i >= 10) member.setSsn("930202-11122" + i);
//            member.setCreatedAt(LocalDateTime.now());
//
//            memberMapper.save(member);
//        }
//
//        //관리자 데이터
//        Member member = new Member();
//        member.setAccount("park");
//        member.setPwd("1234");
//        member.setFullName("박상준");
//        member.setEmail("cl@tt.com");
//        member.setAddress("서울 금천구 시흥대로");
//        member.setPhone("01033334444");
//        member.setRole("ROLE_ADMIN");
//        member.setSsn("930202-1112111");
//        member.setCreatedAt(LocalDateTime.now());
//
//        memberMapper.save(member);
//
//
//        //상품 데이터
//        for (int i = 0; i < 3; i++) {
//            String[] categories = {"bed", "desk", "chair"};
//
//            for (int j = 0; j < 20; j++) {
//                Product product = new Product();
//                product.setName(categories[i] + j);
//                product.setCategory(categories[i]);
//                product.setDescription("Comfort " + categories[i] + j);
//                product.setPrice(30000L + (j * 1000));
//                product.setStock(10 + j);
//                product.setCreatedAt(LocalDateTime.now());
//
//                productMapper.save(product);
//            }
//        }
//
//        //주문 데이터
//        for (int i = 1; i < 6; i++) {
//
//            Order order = new Order();
//            order.setOrderStatus("processing");
//            order.setMemberId((long) i);
//            order.setCreatedAt(LocalDateTime.now());
//
//            orderMapper.save(order);
//
//            for (int j = 1; j < 4; j++) {
//                OrderProduct orderProduct = new OrderProduct();
//                orderProduct.setOrderId((long) i);
//                orderProduct.setProductId((long) i);
//                orderProduct.setQuantity(j);
//
//                orderProductMapper.save(orderProduct);
//            }
//        }

//    }
//
//    ;
//}

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
