package com.kosmo.kosmofurniture.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kosmo.kosmofurniture.domain.*;
import com.kosmo.kosmofurniture.mapper.MapMarkerMapper;
import com.kosmo.kosmofurniture.mapper.MemberMapper;
import com.kosmo.kosmofurniture.mapper.NoticeMapper;
import com.kosmo.kosmofurniture.mapper.ProductMapper;
import com.kosmo.kosmofurniture.service.MapMarkerService;
import com.kosmo.kosmofurniture.service.MemberService;
import com.kosmo.kosmofurniture.service.ProductImageService;
import com.kosmo.kosmofurniture.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final MapMarkerMapper mapMarkerMapper;
    private final ProductMapper productMapper;
    private final NoticeMapper noticeMapper;
    private final ProductImageService productImageService;
    private final ProductService productService;
    private final MapMarkerService mapMarkerService;
    private final MemberService memberService;
    

    /**
     * 관리자메인 뷰페이지
     */
    @GetMapping
    public ModelAndView adminMainPage(HttpSession session) {

        log.debug(SecurityContextHolder.getContext().getAuthentication().toString());
        log.debug("JSESSIONID : {}", session.getId());
        return new ModelAndView("admin/main");
    }

    /**
     * 회원목록 뷰페이지
     */
    @GetMapping("/members")
    public ModelAndView membersView(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("admin/member_list");

        PageInfo<Member> pageInfo = PageInfo.of(memberService.getmembersWithSearchAndPagination(request));

        log.debug("pageInfo.getPageNum() : {}", pageInfo.getPageNum());

        mav.addObject("pageInfo", pageInfo);
        mav.addObject("members", pageInfo.getList());

        return mav;
    }

    /**
     * 지점등록 뷰페이지
     */
    @GetMapping("/map")
    public ModelAndView mapMarker() {
        return new ModelAndView("admin/add_map");
    }

    /**
     * 지점등록 POST
     */
    @PostMapping("/map")
    public ResponseEntity<MapMarker> mapMarkerAdd(MapMarker mapMarker) {

        log.debug("MapMarker : branchName {}, address {}", mapMarker.getBranchName(), mapMarker.getCity());

        MapMarker savedMapMarker = mapMarkerService.createMapMarker(mapMarker);

        return ResponseEntity.ok().body(savedMapMarker);
    }

    /**
     * 전체지점 뷰페이지
     */
    @GetMapping("/maps")
    public ModelAndView showMapList() {

        ModelAndView mav = new ModelAndView("admin/map_list");
        List<MapMarker> mapList = mapMarkerMapper.findAll();
        log.debug("mapList : {}", mapList);
        mav.addObject("mapList", mapList);
        return mav;
    }

//    @GetMapping("/products")
//    public ModelAndView showProducts() {
//
//        ModelAndView mav = new ModelAndView()
//        return mav;
//    }

    /**
     * 전체상품 뷰페이지
     */
    @GetMapping("/products") // URL 예시 :  /admin/products?section=category&search=chair&pageNum=1&pageSize=5
    public ModelAndView showProducts(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("admin/product_list");

        PageInfo<Product> pageInfo = PageInfo.of(productService.getProductsWithSearchAndPagination(request));

        log.debug("pageInfo. : {}", pageInfo.getPageNum());

//        List<Product> products = productMapper.findAll();
        List<ProductImage> images = productImageService.findEachImage(pageInfo.getList());
        mav.addObject("pageInfo", pageInfo);
        mav.addObject("products", pageInfo.getList());
        mav.addObject("images", images);

        return mav;
    }

    /**
     * 단일상품 뷰페이지
     */
    @GetMapping("/products/{productId}")
    public ModelAndView showProduct(@PathVariable Long productId, HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("admin/product_detail");

        String referer = request.getHeader("REFERER");

        log.debug(referer);

        Product product = productMapper.findById(productId);
        List<ProductImage> images = productImageService.findAllByProductId(productId);
        mav.addObject("product", product);
        mav.addObject("images", images);

        return mav;
    }

    /**
     * 상품수정 뷰페이지
     */
    @GetMapping("products/{productId}/update")
    public ModelAndView editProductView(@PathVariable Long productId, HttpServletRequest request) {

        Product product = productMapper.findById(productId);

        List<ProductImage> images = productImageService.findAllByProductId(productId);

        ModelAndView mav = new ModelAndView("admin/product_update");
        mav.addObject("product", product);
        mav.addObject("images", images);

        return mav;
    }

    /**
     * 상품수정 PUT 요청
     */
    @PutMapping("products/update")
    public ResponseEntity<String> editProduct(Product product, @RequestParam(value = "checkedDeleteImages[]", required = false) List<String> checkedDeleteImages) {

        log.debug("checkedDeleteImages == null : {}", checkedDeleteImages == null);
//        log.debug("uploadfiles.isEmpty() : {}", uploadfiles.isEmpty());

        for (String image : Optional.ofNullable(checkedDeleteImages).orElse(Collections.emptyList())) {
            log.debug("imageId : {}", image);
            productImageService.deletebyId(Long.valueOf(image));
        }

        boolean result = productService.updateProduct(product);

        return ResponseEntity.ok().body("{\"isUpdated\" : \"" + result + "\"}");
    }

    @PostMapping("products/ajaxImageUpload")
    public ModelAndView ajaxImageUpload(@RequestPart List<MultipartFile> uploadfiles, @RequestParam String productId) {

            productImageService.save(uploadfiles, Long.valueOf(productId));

        return new ModelAndView("redirect:/admin/products/" + productId);
    }

    /**
     * 상품등록 뷰페이지
     */
    @GetMapping("/products/form")
    public ModelAndView ProductFormView() {
        return new ModelAndView("admin/product_form");
    }

    /**
     * 상품등록 POST
     */
    @PostMapping("/products")
    public ModelAndView addProduct(Product product, List<MultipartFile> uploadfiles) {
        log.debug("ProductId before save : {}", product.getProductId());

        product.setCreatedAt(LocalDateTime.now());
        productMapper.save(product);

        log.debug("savedProductId : {}", product.getProductId());

        productImageService.save(uploadfiles, product.getProductId());
        return new ModelAndView("redirect:/admin");
    }

    /**
     * 상품삭제 API
     */ // DeleteMapping으로 하려 했으나 오류나서 나중에 해볼예정
    @DeleteMapping("/products/{productId}/delete")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {

        log.debug("Controller : DELETE admin/products/{productId}/delete");
        boolean result = productService.deleteProduct(productId);

        return ResponseEntity.ok().body("{\"isDeleted\" : \"" + result + "\"}");
    }
    
    /**
     * 공지등록 뷰페이지
     */
    @GetMapping("/notice")
    public ModelAndView noticeView(HttpServletRequest request) {
    	ModelAndView mav = new ModelAndView("admin/notice");
    	
    	PageHelper.startPage(request);
    	PageInfo<Notice> pageInfo = PageInfo.of(noticeMapper.findAll());
    	mav.addObject("pageInfo", pageInfo);
    	mav.addObject("noticeList", pageInfo.getList());
    	
    	return mav;
    }
    @PostMapping("/notice")
    public ModelAndView addNotice(Notice notice) {

        notice.setCreatedAt(LocalDateTime.now());
        noticeMapper.save(notice);

        return new ModelAndView("redirect:/admin/notice?pageNum=1&pageSize=5");
    }
    
    
    /**
     * 공지등록 페이지
     */
    @GetMapping("/notice-write")
    public ModelAndView noticeForm(HttpServletRequest request) {
    	ModelAndView mav = new ModelAndView("admin/notice_write");
    	
    	return mav;
    }
    
    /**
     * 공지사항 뷰페이지
     */
    @GetMapping("/notice/{noticeId}")
    public ModelAndView showNotice(@PathVariable Long noticeId, HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("admin/notice_view");

        String referer = request.getHeader("REFERER");

        log.debug(referer);

        Notice notice = noticeMapper.findById(noticeId);
        mav.addObject("notice", notice);

        return mav;
    }

    /**
     * 공지수정 뷰페이지
     */
    @GetMapping("notice/{noticeId}/update")
    public ModelAndView editNoticeView(@PathVariable Long noticeId, HttpServletRequest request) {

        Notice notice = noticeMapper.findById(noticeId);

        ModelAndView mav = new ModelAndView("admin/notice_update");
        mav.addObject("notice", notice);

        return mav;
    }

    /**
     * 상품수정 PUT 요청
     */
    @PutMapping("notice/update")
    public ResponseEntity<String> editNotice(Notice notice) {

        noticeMapper.update(notice);

        return ResponseEntity.ok().body("{\"isUpdated\" : \"" + "true" + "\"}");
    }
    
    /**
     * 공지삭제 API
     */
    @DeleteMapping("/notice/{noticeId}/delete")
    public ResponseEntity<String> deleteNotice(@PathVariable Long noticeId) {

        log.debug("Controller : DELETE admin/notice/{noticeId}/delete");
        noticeMapper.deleteById(noticeId);

        return ResponseEntity.ok().body("{\"isDeleted\" : \"" + "true" + "\"}");
    }
    
    
}
