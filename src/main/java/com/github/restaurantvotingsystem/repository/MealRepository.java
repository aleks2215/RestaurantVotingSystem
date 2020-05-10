package com.github.restaurantvotingsystem.repository;

import com.github.restaurantvotingsystem.model.Meal;
import com.github.restaurantvotingsystem.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Integer> {

    @Query("SELECT m FROM Meal m WHERE m.menu.id=:menuId")
    List<Meal> getAllMealsForMenu(@Param("menuId") int menuId);

    @Query("SELECT m FROM Meal m ORDER BY m.name")
    List<Meal> getAll();

    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id")
    int delete(@Param("id") int id);
}
