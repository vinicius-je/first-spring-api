package com.example.cardapio.food;

public record FoodResponseDTO(Integer id, String tile, String image, Integer price) {
    public FoodResponseDTO(Food food){
        this(food.getId(), food.getTitle(), food.getImage(), food.getPrice());
    }
}