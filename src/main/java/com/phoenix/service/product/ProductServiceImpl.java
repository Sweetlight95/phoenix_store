package com.phoenix.service.product;

import com.phoenix.data.dto.ProductDto;
import com.phoenix.data.models.Product;
import com.phoenix.data.repository.ProductRepository;
import com.phoenix.web.exceptions.ProductDoesNotException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Long productId) throws ProductDoesNotException {
        if(productId == null){
            throw new IllegalArgumentException("ID cannot be null");
        }
        Optional<Product> queryResult = productRepository.findById(productId);
        if(queryResult.isPresent()){
            return queryResult.get();
        }
        throw new ProductDoesNotException("Product with ID :"+productId +": does not exists");
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        //product dto is not null
        if(productDto == null){
            throw new IllegalArgumentException("Argument cannot be null");
        }
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setDescription(productDto.getDescription());

        return productRepository.save(product);
    }
}
