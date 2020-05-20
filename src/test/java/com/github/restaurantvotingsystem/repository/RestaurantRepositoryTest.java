package com.github.restaurantvotingsystem.repository;

import com.github.restaurantvotingsystem.TestUtil;
import com.github.restaurantvotingsystem.model.Restaurant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.restaurantvotingsystem.TestData.*;


public class RestaurantRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Test
    public void get() {
        Restaurant restaurant = restaurantRepository.findById(RESTAURANT1.getId()).orElse(null);
        restaurantTestMatcher.assertMatch(restaurant, RESTAURANT1);
    }

    @Test
    public void getAll() {
        List<Restaurant> restaurants = restaurantRepository.getAll()
                .stream()
                .sorted(TestUtil.orderByIdComparator())
                .collect(Collectors.toList());

        restaurantTestMatcher.assertMatch(restaurants, RESTAURANT1, RESTAURANT2);
    }

    @Test
    public void create() {
        Restaurant newRestaurant = getNewRestaurant();
        Restaurant created = restaurantRepository.save(newRestaurant);
        int newId = created.getId();
        newRestaurant.setId(newId);
        restaurantTestMatcher.assertMatch(created, newRestaurant);
        restaurantTestMatcher.assertMatch(restaurantRepository.findById(newId).orElse(null), newRestaurant);
    }

    @Test
    public void updateById() {
        Restaurant updatedRestaurant = getUpdatedRestaurant();
        restaurantRepository.updateById(updatedRestaurant.getId(), updatedRestaurant.getName());
        restaurantTestMatcher.assertMatch(restaurantRepository.findById(updatedRestaurant.getId()).orElse(null), updatedRestaurant);
    }

    @Test
    public void delete() {
        restaurantRepository.delete(RESTAURANT1.getId());
        Assertions.assertNull(restaurantRepository.findById(RESTAURANT1.getId()).orElse(null));
    }
}