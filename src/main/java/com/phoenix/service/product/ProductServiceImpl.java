package com.phoenix.service.product;

import com.phoenix.data.dto.ProductDto;
import com.phoenix.data.models.Product;
import com.phoenix.data.repository.ProductRepository;
import com.phoenix.web.exceptions.BusinessLogicException;
import com.phoenix.web.exceptions.ProductDoesNotExistException;
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
    public Product findProductById(Long productId) throws ProductDoesNotExistException {
        if(productId == null){
            throw new IllegalArgumentException("ID cannot be null");
        }
        Optional<Product> queryResult = productRepository.findById(productId);
        if(queryResult.isPresent()){
            return queryResult.get();
        }
        throw new ProductDoesNotExistException("Product with ID :"+productId +": does not exists");
    }

    @Override
    public Product createProduct(ProductDto productDto) throws BusinessLogicException {
        //product dto is not null
        if(productDto == null){
            throw new IllegalArgumentException("Argument cannot be null");
        }
        Optional<Product> query = productRepository.findByName(productDto.getName());
        if(query.isPresent()){
            throw new BusinessLogicException("Product with name" + productDto.getName() + " already exists");
        }
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setDescription(productDto.getDescription());

        return productRepository.save(product);
    }
}
