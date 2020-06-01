package com.github.restaurantvotingsystem.web;

import com.github.restaurantvotingsystem.TestData;
import com.github.restaurantvotingsystem.TestUtil;
import com.github.restaurantvotingsystem.model.Meal;
import com.github.restaurantvotingsystem.model.Menu;
import com.github.restaurantvotingsystem.model.Restaurant;
import com.github.restaurantvotingsystem.repository.RestaurantRepository;
import com.github.restaurantvotingsystem.util.Exceptions.NotFoundException;
import com.github.restaurantvotingsystem.web.json.JsonUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.restaurantvotingsystem.TestData.*;
import static com.github.restaurantvotingsystem.TestUtil.readFromJson;
import static com.github.restaurantvotingsystem.TestUtil.userHttpBasic;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RestaurantControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantController.REST_URL;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "/admin/restaurants/" + RESTAURANT1.getId())
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(restaurantTestMatcher.contentJson(RESTAURANT1));
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "/admin/restaurants/")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(restaurantTestMatcher.contentJson(RESTAURANT1, RESTAURANT2));
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + "/admin/restaurants/" + RESTAURANT1.getId())
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());
        Assertions.assertNull(restaurantRepository.findById(RESTAURANT1.getId()).orElse(null));
    }

    @Test
    void create() throws Exception {
        Restaurant newRestaurant = TestData.getNewRestaurant();
        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post(REST_URL + "/admin/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(newRestaurant)))
                .andExpect(status().isCreated());

        Restaurant created = readFromJson(action, Restaurant.class);
        int newId = created.id();
        newRestaurant.setId(newId);
        restaurantTestMatcher.assertMatch(created, newRestaurant);
        restaurantTestMatcher.assertMatch(restaurantRepository.findById(newId).orElse(null), newRestaurant);
    }

    @Test
    void update() throws Exception {
        Restaurant updatedRestaurant = TestData.getUpdatedRestaurant();
        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL + "/admin/restaurants/" + RESTAURANT1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updatedRestaurant)))
                .andExpect(status().isNoContent());

        restaurantTestMatcher.assertMatch(restaurantRepository.findById(RESTAURANT1.getId()).orElse(null), updatedRestaurant);
    }

    @Test
    void getHistory() throws Exception {
        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "/admin/restaurants/history")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        List<Restaurant> restaurants = TestUtil.readFromJsonList(action, Restaurant.class);
        restaurantTestMatcher.assertMatch(restaurants, RESTAURANT1, RESTAURANT2);

        List<Menu> menus = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            menus.addAll(restaurant.getMenus());
        }
        menus = menus.stream().sorted(TestUtil.orderByIdComparator()).collect(Collectors.toList());
        menuTestMatcher.assertMatch(menus, MENU1, MENU2, MENU3);

        List<Meal> meals = new ArrayList<>();
        for (Menu menu : menus) {
            meals.addAll(menu.getMeals());
        }
        meals = meals.stream().sorted(TestUtil.orderByIdComparator()).collect(Collectors.toList());
        mealTestMatcher.assertMatch(meals, MEALS);
    }
}