package com.github.restaurantvotingsystem.service;

import com.github.restaurantvotingsystem.model.Meal;
import com.github.restaurantvotingsystem.model.Menu;
import com.github.restaurantvotingsystem.model.Restaurant;
import com.github.restaurantvotingsystem.repository.MealRepository;
import com.github.restaurantvotingsystem.repository.MenuRepository;
import com.github.restaurantvotingsystem.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MealRepository mealRepository;

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    public List<Meal> getAllMeals() {
        List<Meal> meals = mealRepository.getAllMealsForMenu(100004);
        meals.forEach(meal -> {
                    meal.setMenu(null);
                });

        return meals;
    }

    public List<Restaurant> getAllWithTodayMenu() {
       return restaurantRepository.getAllWithTodayMenu(LocalDate.now());
    }

    public List<Restaurant> getAllWithVotesAndMenus() {
        return restaurantRepository.getAllWithVotesAndMenus();
    }

//    List<Restaurant> restaurants = restaurantRepository.findAll();
//        restaurants.forEach(restaurant -> {
//        restaurant.getMenus().size();
//        restaurant.getMenus().forEach(menu -> {
//            menu.getMeals().size();
//        });
//    });
////        restaurants.size();
////        List<Menu> todayMenus = menuRepository.findByDate(LocalDate.now());
////        todayMenus.forEach(menu -> {
////            menu.getMeals();
////        });
////        restaurants.forEach(restaurant -> {
////            restaurant.getMenus().size();
////            restaurant.getMenus().forEach(menu -> {
////                menu.getMeals().size();
////            });
//////            restaurant.setVotes(null);
//////            Set<Menu> menus = restaurant.getMenus();
//////            restaurant.setMenus(menus);
//////            restaurant.getMenus();
////        });
////        restaurants.forEach(restaurant -> {
//////            restaurant.setVotes(null);
////            restaurant.setMenus(todayMenus.stream().filter(menu -> menu.getRestaurant().getId().equals(restaurant.getId())).collect(Collectors.toSet()));
////        });
//        return restaurants;

}
