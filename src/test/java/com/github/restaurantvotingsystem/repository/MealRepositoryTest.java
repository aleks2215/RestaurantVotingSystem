package com.github.restaurantvotingsystem.repository;

import com.github.restaurantvotingsystem.TestUtil;
import com.github.restaurantvotingsystem.model.Meal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.restaurantvotingsystem.TestData.*;


public class MealRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    MealRepository mealRepository;

    @Test
    public void get() {
        Meal meal = mealRepository.findById(MEAL1.getId()).orElse(null);
        mealTestMatcher.assertMatch(meal, MEAL1);
    }

    @Test
    public void getAll() {
        List<Meal> meals = mealRepository.getAll()
                .stream()
                .sorted(TestUtil.orderByIdComparator())
                .collect(Collectors.toList());

        mealTestMatcher.assertMatch(meals, MEALS);
    }

    @Test
    public void create() {
        Meal newMeal = getNewMeal();
        newMeal.setMenu(MENU1);
        Meal created = mealRepository.save(newMeal);
        int newId = created.getId();
        newMeal.setId(newId);
        mealTestMatcher.assertMatch(created, newMeal);
        mealTestMatcher.assertMatch(mealRepository.findById(newId).orElse(null), newMeal);
    }

    @Test
    public void updateById() {
        Meal updatedMeal = getUpdatedMeal();
        mealRepository.updateById(updatedMeal.getId(),updatedMeal.getName(), updatedMeal.getPrice());
        mealTestMatcher.assertMatch(mealRepository.findById(updatedMeal.getId()).orElse(null), updatedMeal);
    }

    @Test
    public void delete() {
        mealRepository.delete(MEAL1.getId());
        Assertions.assertNull(mealRepository.findById(MEAL1.getId()).orElse(null));
    }
}