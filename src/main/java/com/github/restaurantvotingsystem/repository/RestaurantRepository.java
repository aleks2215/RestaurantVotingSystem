package com.github.restaurantvotingsystem.repository;

import com.github.restaurantvotingsystem.model.Restaurant;
import com.github.restaurantvotingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Query("SELECT r FROM Restaurant r")
    List<Restaurant> getAll();

    @Query("SELECT DISTINCT r FROM Restaurant r " +
            "LEFT JOIN FETCH r.votes vote " +
            "JOIN FETCH r.menus menu " +
            "JOIN FETCH menu.meals meal " +
            "WHERE menu.date=:date")
    List<Restaurant> getAllWithTodayMenuMealsAndVotes(@Param("date") LocalDate date);

    @Query("SELECT DISTINCT r FROM Restaurant r " +
            "JOIN FETCH r.menus menu " +
            "WHERE menu.date=:date and r.id=:restaurantId")
    Restaurant getOneWithTodayMenu(@Param("date") LocalDate date, @Param("restaurantId") int restaurantId);

    @Query("SELECT DISTINCT r FROM Restaurant r " +
            "LEFT JOIN FETCH r.votes vote " +
            "JOIN FETCH r.menus menu " +
            "JOIN FETCH menu.meals meal ")
    List<Restaurant> getAllWithVotesAndMenus();


}
