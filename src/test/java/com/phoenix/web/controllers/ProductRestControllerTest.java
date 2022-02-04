package com.phoenix.web.controllers;

import com.phoenix.data.models.Product;
import com.phoenix.data.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts={"/db/insert.sql"})
class ProductRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Get product api test")
    void getProductsTest() throws Exception {
        Product product = new Product();
        product.setName("Bamboo Chair");
        product.setDescription("World class bamboo");
        product.setPrice(5540);
        product.setQuantity(9);

        mockMvc.perform(get("/api/product")
                .contentType("application/json"))
                .andExpect(status().is(200))
                .andDo(print());
    }

}