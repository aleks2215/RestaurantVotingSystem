package com.github.restaurantvotingsystem.service;

import com.github.restaurantvotingsystem.model.*;
import com.github.restaurantvotingsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VoteRepository voteRepository;

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

//    @Transactional
//    public Restaurant save(Restaurant restaurant) {
//        return restaurantRepository.save(restaurant);
//    }

//    @Transactional
//    public Menu saveMenu(Menu menu, int restaurantId) {
//        menu.setRestaurant(restaurantRepository.getOne(restaurantId));
//        return menuRepository.save(menu);
//    }


//    public List<Restaurant> getAllWithTodayMenuAndMeals() {
//        restaurantRepository.findAll()
//        return restaurantRepository.getAllWithTodayMenuAndMeals(LocalDate.now());
//    }

//    public List<Restaurant> getAllWithVotesAndMenus() {
//        return restaurantRepository.getAllWithVotesAndMenus();
//    }

//    public Vote create(int userId, int restaurantId) {
//        LocalDate currentDate = LocalDate.now();
//        Restaurant restaurantWithTodayMenu = restaurantRepository.getOneWithTodayMenu(currentDate, restaurantId);
//        if (restaurantWithTodayMenu == null) {
//            throw new IllegalArgumentException("This restaurant doesn't have menu for today");
//        }
//        LocalTime currentTime = LocalTime.now();
//        LocalTime voteLimit = LocalTime.of(11, 0, 0);
//        Vote todayVoteFromDb = voteRepository.getByDateAndUserId(currentDate, userId);
//        User user = userRepository.findById(userId).orElseThrow();
//        Vote newVote = new Vote(currentDate, user, restaurantWithTodayMenu);
//        Vote savedVote;
//        if (todayVoteFromDb == null) {
//            savedVote = voteRepository.save(newVote);
//        } else if (currentTime.isBefore(voteLimit)) {
//            voteRepository.delete(todayVoteFromDb);
//            savedVote = voteRepository.save(newVote);
//        } else {
//            throw new IllegalArgumentException("You can vote again only before 11:00 AM");
//        }
//
//    }

//    public Vote update(int userId, int restaurantId) {
//        LocalDate currentDate = LocalDate.now();
//        Restaurant restaurantWithTodayMenu = restaurantRepository.getOneWithTodayMenu(currentDate, restaurantId);
//        if (restaurantWithTodayMenu == null) {
//            throw new IllegalArgumentException("This restaurant doesn't have menu for today");
//        }
//        LocalTime currentTime = LocalTime.now();
//        LocalTime voteLimit = LocalTime.of(11, 0, 0);
//        Vote todayVoteFromDb = voteRepository.getByDateAndUserId(currentDate, userId);
//        User user = userRepository.findById(userId).orElseThrow();
//        Vote newVote = new Vote(currentDate, currentTime, user, restaurantWithTodayMenu);
//        Vote savedVote;
//        if (todayVoteFromDb == null) {
//            savedVote = voteRepository.save(newVote);
//        } else if (currentTime.isBefore(voteLimit)) {
//            voteRepository.delete(todayVoteFromDb);
//            savedVote = voteRepository.save(newVote);
//        } else {
//            throw new IllegalArgumentException("You can vote again only before 11:00 AM");
//        }
//
//    }


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
