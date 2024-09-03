package com.Emazon.stock_service.Domain.Model;

import java.util.List;

public class Article {
    private Long id;
    private String name;
    private String description;
    private int quantity;
    private Long price;
    private List<Category> categories;
    private Brand brand;

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Article(Long id, String name, String description, int amount, Long price, List<Category> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = amount;
        this.price = price;
        this.categories = categories;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
