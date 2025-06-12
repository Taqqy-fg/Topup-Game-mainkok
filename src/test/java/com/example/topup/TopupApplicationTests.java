package com.example.topup;

import com.example.topup.repository.ProductRepository;
import com.example.topup.service.ProductService;
import com.example.topup.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootTest
class TopupApplicationTests {

    @Configuration
    static class TestConfig {
        @Bean
        public ProductService productService(ProductRepository repository) {
            return new ProductServiceImpl(repository);
        }
    }

    @MockBean
    private ProductRepository productRepository;

    @Test
    void contextLoads() {
    }
}
