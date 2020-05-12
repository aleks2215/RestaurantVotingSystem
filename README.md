Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) **without frontend**.

The task is:

Build a voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

As a result, provide a link to github repository.

It should contain the code and **README.md with API documentation and curl commands for based use-cases to test it..**

CURL commands
-----------------------------
Admin
----
### Restautants
#### get all restaurants
`curl -s http://localhost:8080/restaurantvotingsystem/rest/admin/restaurants --user admin@gmail.com:admin
`
#### get restaurant 100003
`curl -s http://localhost:8080/restaurantvotingsystem/rest/admin/restaurants/100003 --user admin@gmail.com:admin
`
#### delete restaurant 100003
`curl -s -X DELETE http://localhost:8080/restaurantvotingsystem/rest/admin/restaurants/100003  --user admin@gmail.com:admin
`
#### create restaurant
`curl -s -X POST -d '{"name":"AutoSushi"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restaurantvotingsystem/rest/admin/restaurants --user admin@gmail.com:admin
`
#### update restaurant
`curl -s -X PUT -d '{"name":"SushiOstrov"}' -H 'Content-Type: application/json;charset=UTF-8' http://localhost:8080/restaurantvotingsystem/rest/admin/restaurants/100003 --user admin@gmail.com:admin
`
#### get all restaurant with menus and meals (history)
`curl -s http://localhost:8080/restaurantvotingsystem/rest/admin/restaurants/history --user admin@gmail.com:admin
`
### Menus
#### get all menus
`curl -s http://localhost:8080/restaurantvotingsystem/rest/admin/menus --user admin@gmail.com:admin
`
#### get menu 100005
`curl -s http://localhost:8080/restaurantvotingsystem/rest/admin/menus/100005 --user admin@gmail.com:admin
`
#### delete menu 100005
`curl -s -X DELETE http://localhost:8080/restaurantvotingsystem/rest/admin/menus/100005  --user admin@gmail.com:admin
`
#### create menu
`curl -s -X POST -d '{"date":"2020-05-10"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restaurantvotingsystem/rest/admin/menus?restaurantId=100003 --user admin@gmail.com:admin
`
#### update menu
`curl -s -X PUT -d '{"date":"2020-05-11"}' -H 'Content-Type: application/json;charset=UTF-8' http://localhost:8080/restaurantvotingsystem/rest/admin/menus/100005 --user admin@gmail.com:admin
`
### Meals
#### get all meals
`curl -s http://localhost:8080/restaurantvotingsystem/rest/admin/meals --user admin@gmail.com:admin
`
#### get meal 100008
`curl -s http://localhost:8080/restaurantvotingsystem/rest/admin/meals/100008 --user admin@gmail.com:admin
`
#### delete meal 100008
`curl -s -X DELETE http://localhost:8080/restaurantvotingsystem/rest/admin/meals/100008  --user admin@gmail.com:admin
`
#### create meal
`curl -s -X POST -d '{"name":"SpicyAndDelicious", "price":"1500"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restaurantvotingsystem/rest/admin/meals?menuId=100005 --user admin@gmail.com:admin
`
#### update meal
`curl -s -X PUT -d '{"name":"Coffee", "price":"100"}' -H 'Content-Type: application/json;charset=UTF-8' http://localhost:8080/restaurantvotingsystem/rest/admin/meals/100008 --user admin@gmail.com:admin`

### Votes
#### get votes and restaurants (history)
`curl -s http://localhost:8080/restaurantvotingsystem/rest/admin/votes/history --user admin@gmail.com:admin`

User
----
#### get all restaurants with today menu and meals for voting
`curl -s http://localhost:8080/restaurantvotingsystem/rest/profile/restaurants --user user@yandex.ru:password`

#### voting
`curl -s -X POST http://localhost:8080/restaurantvotingsystem/rest/profile/votes?restaurantId=100003 --user user@yandex.ru:password`
