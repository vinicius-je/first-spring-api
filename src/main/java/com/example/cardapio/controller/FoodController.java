package com.example.cardapio.controller;

import com.example.cardapio.food.Food;
import com.example.cardapio.food.FoodRepository;
import com.example.cardapio.food.FoodRequestDTO;
import com.example.cardapio.food.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("food")
public class FoodController {
    @Autowired
    private FoodRepository repository;

    @PostMapping
    public void saveFood(@RequestBody FoodRequestDTO data){
        Food foodData = new Food(data);
        repository.save(foodData);
    }
    @GetMapping
    public List<FoodResponseDTO> getAll(){
        List<FoodResponseDTO> foodList = repository.findAll().stream().map(FoodResponseDTO::new).toList();
        return foodList;
    }

    @GetMapping("/{id}")
    public Optional<Food> getById(@PathVariable Long id){
        Optional<Food> food = repository.findById(id);
        return food;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        repository.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody FoodRequestDTO updatedFood, @PathVariable Long id){
        try {
            Food food = repository.findById(id).get();
            food.setTitle(updatedFood.title());
            food.setPrice(updatedFood.price());
            food.setImage(updatedFood.image());
            repository.save(food);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
