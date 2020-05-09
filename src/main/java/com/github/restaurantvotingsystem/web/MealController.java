package com.github.restaurantvotingsystem.web;

import com.github.restaurantvotingsystem.model.Meal;
import com.github.restaurantvotingsystem.model.Menu;
import com.github.restaurantvotingsystem.repository.MealRepository;
import com.github.restaurantvotingsystem.repository.MenuRepository;
import com.github.restaurantvotingsystem.util.ValidationUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = MealController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealController {
    static final String REST_URL = "/rest/meals";
     private static final Logger log = getLogger(MealController.class);

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private MenuRepository menuRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> create(@RequestParam Integer menuId, @RequestBody Meal meal) {
        log.info("create meal");
        ValidationUtil.checkNew(meal);
        meal.setMenu(menuRepository.getOne(menuId));
        Meal newMeal = mealRepository.save(meal);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(newMeal.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(newMeal);
    }
}
