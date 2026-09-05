package com.loja.produtos.dto;

import com.loja.produtos.model.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CategoryResponseDTO {
    private Long id;
    private String name;

    public  CategoryResponseDTO(Category category){
        this.id = category.getId();
        this.name = category.getName();
    }
}
