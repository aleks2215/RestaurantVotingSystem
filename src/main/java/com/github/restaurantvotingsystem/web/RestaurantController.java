package com.github.restaurantvotingsystem.web;

import com.github.restaurantvotingsystem.model.Meal;
import com.github.restaurantvotingsystem.model.Menu;
import com.github.restaurantvotingsystem.model.Restaurant;
import com.github.restaurantvotingsystem.model.Vote;
import com.github.restaurantvotingsystem.repository.RestaurantRepository;
import com.github.restaurantvotingsystem.service.RestaurantService;
import com.github.restaurantvotingsystem.util.SecurityUtil;
import com.github.restaurantvotingsystem.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
    static final String REST_URL = "/rest/restaurants";
    private final Logger log = LoggerFactory.getLogger(RestaurantController.class);

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("get all restaurants");
        return restaurantService.getAll();
    }

    @GetMapping("/meals")
    public List<Meal> getAllMeals() {
        return restaurantService.getAllMeals();
    }

//    @GetMapping
//    public List<Restaurant> getAllWithTodayMenuAndMeals() {
//        List<Restaurant> restaurants = restaurantService.getAllWithTodayMenuAndMeals();
//        return restaurants;
//    }


//    @PostMapping(value = "/{restaurantId}/vote")
//    public ResponseEntity<Vote> create(@PathVariable int restaurantId) {
//        Vote newVote = restaurantService.create(restaurantId, SecurityUtil.getUserId());
//
//        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path(REST_URL + "/{restaurantId}/vote/{id}")
//                .buildAndExpand(newVote.getId()).toUri();
//
//        return ResponseEntity.created(uriOfNewResource).body(newVote);
//    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        log.info("create restaurant");
        ValidationUtil.checkNew(restaurant);
        Restaurant newRestaurant = restaurantService.save(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(newRestaurant.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(newRestaurant);
    }

    @GetMapping("/history")
    public List<Restaurant> getHistory() {
        List<Restaurant> restaurants = restaurantRepository.getAllWithMenusAndMeals();
        return restaurants;
    }
}
