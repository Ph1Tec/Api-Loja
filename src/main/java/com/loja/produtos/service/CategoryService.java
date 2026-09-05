package com.loja.produtos.service;

import com.loja.produtos.dto.CategoryCreateDTO;
import com.loja.produtos.exception.CategoryNotFoundException;
import com.loja.produtos.model.Category;
import com.loja.produtos.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> metodoListar(){
        return categoryRepository.findAll();
    }

    public Category buscarId(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Categoria não encontrada"));
    }
    public Category metodoCriar(CategoryCreateDTO dto){
      Category category = new Category();
      category.setName(dto.getName());
      return categoryRepository.save(category);
    }
    public Category metodoAtualizar(Long id, CategoryCreateDTO dto){
        Category category = buscarId(id);
        category.setName(dto.getName());
        return categoryRepository.save(category);
    }

    public void metodoDeletar(Long id){
        Category category = buscarId(id);
        categoryRepository.delete(category);
    }
}
