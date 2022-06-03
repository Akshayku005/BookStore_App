package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

public @Data class OrderDTO {
    private Integer quantity;
    @NotEmpty(message = "Please provide address")
    private String address;
    private Integer userId;
    private Integer bookId;
    private boolean cancel;
    private Integer price;
}