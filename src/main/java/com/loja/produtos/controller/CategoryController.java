package com.loja.produtos.controller;

import com.loja.produtos.dto.CategoryCreateDTO;
import com.loja.produtos.model.Category;
import com.loja.produtos.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category criar(@RequestBody CategoryCreateDTO dto){
        return categoryService.metodoCriar(dto);
    }

    @GetMapping
    public List<Category> listar(){
        return categoryService.metodoListar();
    }

    @GetMapping("/{id}")
    public Category buscarPorId(@PathVariable Long id){
        return  categoryService .buscarId(id);
    }

    @PutMapping("/{id}")
    public Category atualizar(@PathVariable Long id, @RequestBody CategoryCreateDTO dto){
        return categoryService.metodoAtualizar(id,dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        categoryService.metodoDeletar(id);
    }
}
