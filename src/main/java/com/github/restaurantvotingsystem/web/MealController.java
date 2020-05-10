package com.github.restaurantvotingsystem.web;

import com.github.restaurantvotingsystem.model.Meal;
import com.github.restaurantvotingsystem.repository.MealRepository;
import com.github.restaurantvotingsystem.repository.MenuRepository;
import com.github.restaurantvotingsystem.util.ValidationUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = MealController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealController {
    static final String REST_URL = "/rest/admin/meals";
    private static final Logger log = getLogger(MealController.class);

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private MenuRepository menuRepository;

    @GetMapping("/{id}")
    public Meal get(@PathVariable int id) {
        log.info("get meal by id={}", id);
        return ValidationUtil.checkNotFoundWithId(mealRepository.findById(id).orElse(null), id);
    }

    @GetMapping
    public List<Meal> getAll() {
        log.info("get all menus");
        return mealRepository.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete meal by id={}", id);
        ValidationUtil.checkNotFoundWithId(mealRepository.delete(id), id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> create(@RequestParam Integer menuId, @RequestBody Meal meal) {
        ValidationUtil.checkNew(meal);
        log.info("create {} for menu {}", meal, menuId);
        meal.setMenu(menuRepository.getOne(menuId));
        Meal newMeal = mealRepository.save(meal);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(newMeal.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(newMeal);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Meal meal, @PathVariable int id) {
        ValidationUtil.assureIdConsistent(meal, id);
        log.info("update meal {} by id={}", meal, id);
        mealRepository.save(meal);
    }
}
