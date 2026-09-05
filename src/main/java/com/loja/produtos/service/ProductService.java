package com.loja.produtos.service;

import com.loja.produtos.dto.ProductCreateDTO;
import com.loja.produtos.dto.ProductResponseDTO;
import com.loja.produtos.exception.ProductNotFoundException;
import com.loja.produtos.model.Category;
import com.loja.produtos.model.Product;
import com.loja.produtos.repository.CategoryRepository;
import com.loja.produtos.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public Category buscarCategory(ProductCreateDTO dto){
       return categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ProductNotFoundException("não encontrado"));
    }
    public Product metodoCriarProduto(ProductCreateDTO dto){
        Category category = buscarCategory(dto);
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setCategory(category);
        return productRepository.save(product);
    }
    public List<ProductResponseDTO> metodoListar()
    {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponseDTO(product))
                .toList();
    }
    public List<ProductResponseDTO> metodoCategoriaProduto(Long categoryId){
        return productRepository.findByCategoryId(categoryId)
                .stream()
                .map(product -> new ProductResponseDTO(product))
                .toList();
    }
    public Product metodoAtualizarProduto(Long id, ProductCreateDTO dto){
        Category category = buscarCategory(dto);
        Product product = buscarPorId(id);

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setCategory(category);
        return productRepository.save(product);
    }
    public void metodoDeletarProduto(Long id){
        Product product = buscarPorId(id);
        productRepository.delete(product);
    }

    public Product buscarPorId(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encotrado"));
    }
    public ProductResponseDTO buscarPorIdResponse(Long id){
        Product product = buscarPorId(id);
        return new ProductResponseDTO(product);
    }
}
