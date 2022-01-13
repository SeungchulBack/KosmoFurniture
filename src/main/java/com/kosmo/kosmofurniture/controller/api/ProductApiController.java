package com.kosmo.kosmofurniture.controller.api;

import com.kosmo.kosmofurniture.domain.Cart;
import com.kosmo.kosmofurniture.domain.MemberPrincipal;
import com.kosmo.kosmofurniture.domain.Product;
import com.kosmo.kosmofurniture.mapper.CartMapper;
import com.kosmo.kosmofurniture.mapper.ProductMapper;
import com.kosmo.kosmofurniture.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductApiController {

    private final ProductService productService;
    private final CartMapper cartMapper;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        log.debug("Authentication : {}", SecurityContextHolder.getContext().getAuthentication().toString());
        log.debug("authority : {}", SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/categories/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        return ResponseEntity.ok().body(productService.getProductsByCategory(category));
    }

    @PostMapping("/cart")
    public ResponseEntity<String> addCart(@AuthenticationPrincipal MemberPrincipal principal, @RequestParam Long productId) {
        Long memberId = principal.getMemberId();
        Cart cart = Cart.builder()
                .memberId(memberId)
                .productId(productId)
                .quantity(1)
                .build();

        int success = cartMapper.save(cart);

        boolean result = (success == 1) ? true : false;

        return ResponseEntity.ok().body("{\"cartAdded\" : \"" + result + "\"}");
    }
}
