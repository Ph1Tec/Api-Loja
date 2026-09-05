package com.loja.produtos.exception;

import com.loja.produtos.model.Product;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message){
        super(message);
    }
}
