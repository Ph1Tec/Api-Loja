package com.loja.produtos.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductCreateDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private Long categoryId;
}
