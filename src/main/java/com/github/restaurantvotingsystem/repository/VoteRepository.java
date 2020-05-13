package com.github.restaurantvotingsystem.repository;

import com.github.restaurantvotingsystem.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT v FROM Vote v " +
            "JOIN FETCH v.restaurant " +
            "WHERE v.date=:date and v.user.id=:userId")
    Vote getWithRestaurantByDateAndUserId(@Param("date") LocalDate date, @Param("userId") int userId);

    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant ")
    List<Vote> getAllWithRestaurant();
}
