package com.phoenix.service.product;

import com.phoenix.data.dto.ProductDto;
import com.phoenix.data.models.Product;
import com.phoenix.web.exceptions.ProductDoesNotException;

import java.util.List;

public interface ProductService {

    List<Product> getAllProduct();
    Product findProductById(Long productId) throws ProductDoesNotException;
    Product createProduct(ProductDto productDto);


}
