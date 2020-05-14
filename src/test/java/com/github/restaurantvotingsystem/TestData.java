package com.github.restaurantvotingsystem;

import com.github.restaurantvotingsystem.model.Meal;
import com.github.restaurantvotingsystem.model.Menu;
import com.github.restaurantvotingsystem.model.Restaurant;
import com.github.restaurantvotingsystem.model.Vote;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestData {
    public static TestMatcher<Restaurant> restaurantTestMatcher =
            TestMatcher.usingFieldsComparator(Restaurant.class, "menus", "votes");
    public static TestMatcher<Menu> menuTestMatcher =
            TestMatcher.usingFieldsComparator(Menu.class, "meals", "restaurant");
    public static TestMatcher<Meal> mealTestMatcher =
            TestMatcher.usingFieldsComparator(Meal.class, "menu");
    public static TestMatcher<Vote> voteTestMatcher =
            TestMatcher.usingFieldsComparator(Vote.class, "user", "restaurant");

    public static final Restaurant RESTAURANT1 = new Restaurant(100003, "Rush-Hour");
    public static final Restaurant RESTAURANT2 = new Restaurant(100004, "Tashir");
    public static final Menu MENU1 = new Menu(100005, LocalDate.now());
    public static final Menu MENU2 = new Menu(100006, LocalDate.now());
    public static final Menu MENU3 = new Menu(100007, LocalDate.of(2020, 5, 8));
    public static final Meal MEAL1 = new Meal(100008, "Pork", BigDecimal.valueOf(1000));
    public static final Meal MEAL2 = new Meal(100009, "Steak Vesuvius", BigDecimal.valueOf(350));
    public static final Meal MEAL3 = new Meal(100010, "Pizza Valentine", BigDecimal.valueOf(362));
    public static final Meal MEAL4 = new Meal(100011, "Salad Caesar", BigDecimal.valueOf(39));
    public static final Meal MEAL5 = new Meal(100012, "Pizza Margarita", BigDecimal.valueOf(190));
    public static final Meal MEAL6 = new Meal(100013, "Pizza Venice", BigDecimal.valueOf(362));
    public static final Meal MEAL7 = new Meal(100014, "Stuff pizza", BigDecimal.valueOf(390));
    public static final Meal MEAL8 = new Meal(100015, "Sushi", BigDecimal.valueOf(220));
    public static final Meal MEAL9 = new Meal(100016, "Orange Juice", BigDecimal.valueOf(30));
    public static final List<Meal> MEALS = Arrays.asList(MEAL1, MEAL2, MEAL3, MEAL4, MEAL5, MEAL6, MEAL7, MEAL8, MEAL9);

    public static Meal getNewMeal() {
        return new Meal(null, "SpicyAndDelicious", BigDecimal.valueOf(1500));
    }

    public static Meal getUpdatedMeal() {
        return new Meal(MEAL1.getId(), "Coffee", BigDecimal.valueOf(100));
    }

    public static Menu getNewMenu() {
        return new Menu(null, LocalDate.of(2020,5,10));
    }

    public static Menu getUpdatedMenu() {
        return new Menu(MENU1.getId(), LocalDate.of(2020,5,11));
    }

    public static Restaurant getNewRestaurant() {
        return new Restaurant(null, "AutoSushi");
    }

    public static Restaurant getUpdatedRestaurant() {
        return new Restaurant(RESTAURANT1.getId(), "SushiOstrov");
    }


}
