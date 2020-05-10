package com.github.restaurantvotingsystem.repository;

import com.github.restaurantvotingsystem.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Query("SELECT m FROM Menu m ORDER BY m.date")
    List<Menu> getAll();

    @Transactional
    @Modifying
    @Query("DELETE FROM Menu m WHERE m.id=:id")
    int delete(@Param("id") int id);
}
