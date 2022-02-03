package com.phoenix.web.controllers;

import com.phoenix.data.dto.ProductDto;
import com.phoenix.data.models.Product;
import com.phoenix.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {

    @Autowired
    ProductService productService;

    @GetMapping()
    public ResponseEntity<?> findAllProducts(){
        List<Product> productList = productService.getAllProduct();
        return ResponseEntity.ok().body(productList);
    }
    @PostMapping()
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto){
        Product product = productService.createProduct(productDto);
        return ResponseEntity.ok().body(product);
    }
}
