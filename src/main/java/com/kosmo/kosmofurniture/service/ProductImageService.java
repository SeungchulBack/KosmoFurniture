package com.kosmo.kosmofurniture.service;

import com.kosmo.kosmofurniture.domain.Product;
import com.kosmo.kosmofurniture.domain.ProductImage;
import com.kosmo.kosmofurniture.mapper.ProductImageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductImageService {

    private final ProductImageMapper productImageMapper;

    private String saveFolder = System.getProperty("user.dir") + "\\files";

    @Transactional
    public ProductImage getFirstImage(Long productId) {
        return productImageMapper.findOneByProductId(productId);
    }

    @Transactional
    public void save(List<MultipartFile> uploadfiles, Long productId) {

        ProductImage productImage = new ProductImage();

        log.debug("uploadfiles empty? : {}", uploadfiles.get(0).isEmpty());

        /* 업로드파일이 있는지 체크 */
        if (!uploadfiles.get(0).isEmpty()) {
            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
            if (!new File(saveFolder).exists()) {
                try {
                    new File(saveFolder).mkdir();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }

            for (MultipartFile file : uploadfiles) {

                String originalFilename = file.getOriginalFilename();

                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH) + 1;
                int date = c.get(Calendar.DATE);

                //파일이름생성 코드
                Random r = new Random();
                int random = r.nextInt(1000000);
                int index = originalFilename.lastIndexOf(".");
                String fileExtension = originalFilename.substring(index + 1); /*확장자추출*/
                String dbFileName = "product_image_" + year + month + date + random + "." + fileExtension;

                log.debug("saveFolder : {}", saveFolder);

                try {
                    byte[] bytes1 = file.getBytes();
                    byte[] bytes2 = Arrays.copyOf(bytes1, bytes1.length);
                    new FileOutputStream(saveFolder + "\\" + dbFileName).write(bytes1);
                    new FileOutputStream("C:\\Users\\user\\projects\\KosmoFurniture\\files" + "\\" + dbFileName).write(bytes2);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                productImage.setProductId(productId);
                productImage.setOriginalFileName(originalFilename);
                productImage.setDbFileName(dbFileName);
                productImageMapper.save(productImage);
            }

        }
    }

    @Transactional
    public List<ProductImage> findEachImage(List<Product> products) {
        List<ProductImage> productImages = new ArrayList<>();
        ProductImage firstImage;

        for (Product product : products) {
            firstImage = getFirstImage(product.getProductId());
            productImages.add(firstImage);
        }
        return productImages;
    }
}
