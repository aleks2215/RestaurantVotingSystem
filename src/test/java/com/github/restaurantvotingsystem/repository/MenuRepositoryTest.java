package com.github.restaurantvotingsystem.repository;

import com.github.restaurantvotingsystem.TestUtil;
import com.github.restaurantvotingsystem.model.Menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.restaurantvotingsystem.TestData.*;

public class MenuRepositoryTest  extends AbstractRepositoryTest {

    @Autowired
    MenuRepository menuRepository;

    @Test
    public void get() {
        Menu menu = menuRepository.findById(MENU1.getId()).orElse(null);
        menuTestMatcher.assertMatch(menu, MENU1);
    }

    @Test
    public void getAll() {
        List<Menu> menus = menuRepository.getAll()
                .stream()
                .sorted(TestUtil.orderByIdComparator())
                .collect(Collectors.toList());
        menuTestMatcher.assertMatch(menus, MENU1, MENU2, MENU3);
    }

    @Test
    public void create() {
        Menu newMenu = getNewMenu();
        newMenu.setRestaurant(RESTAURANT1);
        Menu created = menuRepository.save(newMenu);
        int newId = created.getId();
        newMenu.setId(newId);
        menuTestMatcher.assertMatch(created, newMenu);
        menuTestMatcher.assertMatch(menuRepository.findById(newId).orElse(null), newMenu);
    }

    @Test
    public void updateById() {
        Menu updatedMenu = getUpdatedMenu();
        menuRepository.updateById(updatedMenu.getId(),updatedMenu.getDate());
        menuTestMatcher.assertMatch(menuRepository.findById(updatedMenu.getId()).orElse(null), updatedMenu);
    }

    @Test
    public void delete() {
        menuRepository.delete(MENU1.getId());
        Assertions.assertNull(menuRepository.findById(MENU1.getId()).orElse(null));
    }
}