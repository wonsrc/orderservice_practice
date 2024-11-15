package com.playdata.orderservice.product.dto;

import com.playdata.orderservice.product.entity.Product;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSaveReqDto {

    private String name;
    private String category;
    private int price;
    private int stockQuantity;
    private MultipartFile productImage;

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .category(category)
                .price(price)
                .stockQuantity(stockQuantity)
                .build();
    }

}