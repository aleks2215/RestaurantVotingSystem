package com.github.restaurantvotingsystem.web;

import com.github.restaurantvotingsystem.model.Restaurant;
import com.github.restaurantvotingsystem.repository.RestaurantRepository;
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
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
    static final String REST_URL = "/rest";
    private final Logger log = LoggerFactory.getLogger(RestaurantController.class);

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/admin/restaurants/{id}")
    public Restaurant get(@PathVariable int id) {
        log.info("get restaurant by id={}", id);
        return ValidationUtil.checkNotFoundWithId(restaurantRepository.findById(id).orElse(null), id);
    }

    @GetMapping("/admin/restaurants")
    public List<Restaurant> getAll() {
        log.info("get all restaurants");
        return restaurantRepository.getAll();
    }

    @DeleteMapping("/admin/restaurants/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete restaurant by id={}", id);
        ValidationUtil.checkNotFoundWithId(restaurantRepository.delete(id), id);
    }

    @GetMapping("/profile/restaurants")
    public List<Restaurant> getAllWithTodayMenuAndMeals() {
        List<Restaurant> restaurants = restaurantRepository.getAllWithTodayMenuAndMeals(LocalDate.now());
        return restaurants;
    }

    @PostMapping(value = "/admin/restaurants", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@Valid @RequestBody Restaurant restaurant) {
        log.info("create restaurant {}", restaurant);
        ValidationUtil.checkNew(restaurant);
        Restaurant newRestaurant = restaurantRepository.save(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/admin/restaurants/{id}")
                .buildAndExpand(newRestaurant.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(newRestaurant);
    }

    @PutMapping(value = "/admin/restaurants/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable int id) {
        ValidationUtil.assureIdConsistent(restaurant, id);
        log.info("update {} with id={}", restaurant, id);
        restaurantRepository.updateById(id, restaurant.getName());
    }
    
    @GetMapping("/admin/restaurants/history")
    public List<Restaurant> getHistory() {
        log.info("get restaurant history with menus and meals");
        List<Restaurant> restaurants = restaurantRepository.getAllWithMenusAndMeals();
        return restaurants;
    }
}
