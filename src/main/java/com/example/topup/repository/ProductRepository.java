package com.example.topup.repository;

import com.example.topup.model.Product;
import com.example.topup.model.Product.GameCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(GameCategory category);
}
