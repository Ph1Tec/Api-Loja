package com.loja.produtos.controller;

import com.loja.produtos.dto.ProductCreateDTO;
import com.loja.produtos.dto.ProductResponseDTO;
import com.loja.produtos.model.Product;
import com.loja.produtos.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){this.productService = productService;}
    @PostMapping
    public Product criar(@RequestBody ProductCreateDTO dto){return productService.metodoCriarProduto(dto);}

    @GetMapping
    public List<ProductResponseDTO> listar(){return productService.metodoListar();}

    @GetMapping("/{id}")
    public Product buscarPorId(@PathVariable Long id){return productService.buscarPorId(id);}

    @GetMapping("/categoria/{categoryId}")
    public List<ProductResponseDTO> listarPorCategoria(@PathVariable Long categoryId){return productService.metodoCategoriaProduto(categoryId);}

    @PutMapping("/{id}")
    public Product atualizar(@PathVariable Long id, @RequestBody ProductCreateDTO dto){
        return productService.metodoAtualizarProduto(id,dto);
    }
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        productService.metodoDeletarProduto(id);
    }
}
