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

    @Query("SELECT m FROM Menu m ORDER BY m.date")
    List<Menu> getAll();

    @Transactional
    @Modifying
    @Query("UPDATE Menu m SET m.date=:date WHERE m.id=:id")
    void updateById(@Param("id") int id, @Param("date") LocalDate date);

    @Transactional
    @Modifying
    @Query("DELETE FROM Menu m WHERE m.id=:id")
    int delete(@Param("id") int id);
}
