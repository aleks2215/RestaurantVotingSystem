package com.github.restaurantvotingsystem.repository;

import com.github.restaurantvotingsystem.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Integer> {

    @Query("SELECT m FROM Meal m WHERE m.menu.id=:menuId")
    List<Meal> getAllMealsForMenu(@Param("menuId") int menuId);


}
