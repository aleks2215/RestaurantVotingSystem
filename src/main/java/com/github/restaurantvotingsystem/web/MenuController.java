package com.github.restaurantvotingsystem.web;

import com.github.restaurantvotingsystem.model.Menu;
import com.github.restaurantvotingsystem.repository.MenuRepository;
import com.github.restaurantvotingsystem.repository.RestaurantRepository;
import com.github.restaurantvotingsystem.util.ValidationUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = MenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {
    static final String REST_URL = "/rest/menus";
    private static final Logger log = getLogger(MenuController.class);

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

//    @GetMapping("/history")
//    public List<Menu> getHistory(){
//        List<Menu> menu = menuRepository.getAllWithRestaurantsVotesAndMeals();
//        return menu;
//    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> create(@RequestParam Integer restaurantId, @RequestBody Menu menu) {
        log.info("create menu");
        ValidationUtil.checkNew(menu);
        menu.setRestaurant(restaurantRepository.getOne(restaurantId));
        Menu newMenu = menuRepository.save(menu);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(newMenu.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(newMenu);
    }
}
