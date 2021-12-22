package com.kosmo.kosmofurniture.controller.admin;

import com.kosmo.kosmofurniture.domain.MapMarker;
import com.kosmo.kosmofurniture.domain.Product;
import com.kosmo.kosmofurniture.domain.ProductImage;
import com.kosmo.kosmofurniture.mapper.MapMarkerMapper;
import com.kosmo.kosmofurniture.mapper.ProductMapper;
import com.kosmo.kosmofurniture.service.ProductImageService;
import com.kosmo.kosmofurniture.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final MapMarkerMapper mapMarkerMapper;
    private final ProductMapper productMapper;
    private final ProductImageService productImageService;
    private final ProductService productService;

    /** 지점등록 뷰페이지 */
    @GetMapping("/map")
    public ModelAndView mapMarker() {
        return new ModelAndView("admin/add_map");
    }

    /** 지점등록 POST */
    @PostMapping("/map")
    public ResponseEntity<MapMarker> mapMarkerAdd(MapMarker mapMarker) {

        log.debug("MapMarker : branchName {}, address {}", mapMarker.getBranchName(), mapMarker.getCity());

        Long mapMarkerId = mapMarkerMapper.save(mapMarker);

        return ResponseEntity.ok().body(mapMarkerMapper.findById(mapMarkerId));
    }

//    @GetMapping("/products")
//    public ModelAndView showProducts() {
//
//        ModelAndView mav = new ModelAndView()
//        return mav;
//    }

    /** 전체상품 뷰페이지 */
    @GetMapping("/products")
    public ModelAndView showProducts() {

        ModelAndView mav = new ModelAndView("admin/product_list");

        List<Product> products = productMapper.findAll();
        List<ProductImage> images = productImageService.findEachImage(products);
        mav.addObject("products", products);
        mav.addObject("images", images);

        return mav;
    }

    /** 단일상품 뷰페이지 */
    @GetMapping("/products/{productId}")
    public ModelAndView showProduct(@PathVariable Long productId) {

        ModelAndView mav = new ModelAndView("admin/product_detail");

        List<Product> products = productMapper.findAll();
        List<ProductImage> images = productImageService.findEachImage(products);
        mav.addObject("products", products);
        mav.addObject("images", images);

        return mav;
    }

    /** 상품등록 뷰페이지 */
    @GetMapping("/products/form")
    public ModelAndView ProductFormView() {
        return new ModelAndView("admin/product_form");
    }

    /** 상품등록 POST */
    @PostMapping("/products")
    public ModelAndView addProduct(Product product, List<MultipartFile> uploadfiles) {

        product.setCreatedAt(LocalDateTime.now());
        productMapper.save(product);

        log.debug("savedProductId : {}", product.getProductId());

        productImageService.save(uploadfiles, product.getProductId());
        return new ModelAndView("redirect:/admin/products");
    }
}
