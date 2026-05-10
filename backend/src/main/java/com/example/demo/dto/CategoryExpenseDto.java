package com.example.demo.dto;


public class CategoryExpenseDto {

    private String category;

    private Double totalAmount;

    public CategoryExpenseDto(
            String category,
            Double totalAmount) {

        this.category = category;
        this.totalAmount = totalAmount;
    }

    public String getCategory() {
        return category;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }
}