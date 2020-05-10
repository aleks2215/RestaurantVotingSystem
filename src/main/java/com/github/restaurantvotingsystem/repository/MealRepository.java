package com.github.restaurantvotingsystem.repository;

import com.github.restaurantvotingsystem.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Integer> {

    @Query("SELECT m FROM Meal m ORDER BY m.name")
    List<Meal> getAll();

    @Transactional
    @Modifying
    @Query("UPDATE Meal m SET m.name=:name, m.price=:price WHERE m.id=:id")
    void updateById(@Param("id") int id, @Param("name") String name, @Param("price") BigDecimal price);

    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id")
    int delete(@Param("id") int id);
}
