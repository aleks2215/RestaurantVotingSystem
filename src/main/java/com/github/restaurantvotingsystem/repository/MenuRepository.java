package com.github.restaurantvotingsystem.repository;

import com.github.restaurantvotingsystem.model.Menu;
import com.github.restaurantvotingsystem.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Query("SELECT m FROM Menu m WHERE m.date=:date")
    List<Menu> findByDate(@Param("date") LocalDate date);

    @Transactional
    @Modifying
    @Query("DELETE FROM Menu m WHERE m.id=:id")
    int delete(@Param("id") int id);

//    @Query("SELECT DISTINCT menu.restaurant.name, menu FROM Menu menu " +
//            "JOIN FETCH menu.restaurant r " +
//            "LEFT JOIN FETCH r.votes vote " +
//            "JOIN FETCH menu.meals meal ")
//    List<Menu> getAllWithRestaurantsVotesAndMeals();
}
