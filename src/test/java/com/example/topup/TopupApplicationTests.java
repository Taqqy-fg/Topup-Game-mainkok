package com.example.topup;

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
