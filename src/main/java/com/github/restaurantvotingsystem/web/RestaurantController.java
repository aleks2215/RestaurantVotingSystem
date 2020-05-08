package com.github.restaurantvotingsystem.web;

import com.github.restaurantvotingsystem.model.Meal;
import com.github.restaurantvotingsystem.model.Restaurant;
import com.github.restaurantvotingsystem.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/meals")
    public List<Meal> getAllMeals() {
        return restaurantService.getAllMeals();
    }

    @GetMapping
    public List<Restaurant> getAllWithTodayMenuAndMeals() {
        List<Restaurant> restaurants = restaurantService.getAllWithTodayMenuAndMeals();
        return restaurants;
    }

    @GetMapping("/history")
    public List<Restaurant> getAllWithVotesAndMenus() {
        List<Restaurant> restaurants = restaurantService.getAllWithVotesAndMenus();
        return restaurants;
    }
}
