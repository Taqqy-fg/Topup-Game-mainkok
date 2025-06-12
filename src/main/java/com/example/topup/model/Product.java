package com.example.topup.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products") // pastikan mapping ke tabel 'products'
public class Product {
    public enum GameCategory {
        MLBB,
        PUBG
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private Integer discount;

    @Enumerated(EnumType.STRING)
    private GameCategory category;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product parentProduct;

    @Transient // Tidak disimpan di database
    private boolean selected = false;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
