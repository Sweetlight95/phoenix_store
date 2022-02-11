package com.phoenix.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoenix.data.dto.ProductDto;
import com.phoenix.data.models.Product;
import com.phoenix.data.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
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

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Get product api test")
    void getProductsTest() throws Exception {
        mockMvc.perform(get("/api/product")
                        .contentType("application/json"))
                        .andExpect(status().is(200))
                        .andDo(print());
    }


    @Test
    @DisplayName("Create a product api test")
    void createProductsTest() throws Exception {
//
//        Path path = Paths.get("src/test/resources/2021-11-29-090801.jpg");
//        assertThat(path.toFile().exists());
//        assertThat(path.getFileName().toString()).isEqualTo("2021-11-29-090801.jpg");
//        //load to multipart
//        MultipartFile multipartFile = new MockMultipartFile(path.getFileName().toString(), path.getFileName().toString(),
//                "img/jpg", Files.readAllBytes(path));

//        ProductDto product = new ProductDto();
//        product.setName("Bamboo Chair");
//        product.setDescription("World class bamboo");
//        product.setPrice(5540);
//        product.setQuantity(9);
//        product.setImage(multipartFile);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/product")
                .param("name", "Bamboo chair")
                .param("description", "World class bamboo")
                .param("price", "540")
                .param("quantity", "5");
//        String requestBody = objectMapper.writeValueAsString(product);

        mockMvc.perform(request
                .contentType("multipart/form-data"))
                .andExpect(status().is(200))
                .andDo(print());
    }

    @Test
    void updateProductsTest() throws Exception{
        Product product = productRepository.findById(14L).orElse(null);
        assertThat(product).isNotNull();

        mockMvc.perform(patch("/api/product/14")
                        .contentType("application/json-patch+json").content(Files.readAllBytes(Path.of("PayLoan.json"))))
                .andExpect(status().is(200))
                .andDo(print());

        product = productRepository.findById(14L).orElse(null);
        assertThat(product).isNotNull();
        assertThat(product.getDescription()).isEqualTo("This is a bamboo");
    }

}