package com.github.restaurantvotingsystem.service;

import com.github.restaurantvotingsystem.model.Restaurant;
import com.github.restaurantvotingsystem.model.User;
import com.github.restaurantvotingsystem.model.Vote;
import com.github.restaurantvotingsystem.repository.RestaurantRepository;
import com.github.restaurantvotingsystem.repository.UserRepository;
import com.github.restaurantvotingsystem.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class VoteService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private VoteRepository voteRepository;

    public void addOrReplace(int userId, int restaurantId) {
        LocalDate currentDate = LocalDate.now();
        Restaurant restaurantWithTodayMenu = restaurantRepository.getOneWithTodayMenu(currentDate, restaurantId);
        if (restaurantWithTodayMenu == null) {
            throw new IllegalArgumentException("This restaurant doesn't have menu for today");
        }
        LocalTime currentTime = LocalTime.now();
        LocalTime voteLimit = LocalTime.of(11, 0, 0);
        Vote todayVoteFromDb = voteRepository.getByDateAndUserId(currentDate, userId);
        User user = userRepository.findById(userId).orElseThrow();
        Vote newVote = new Vote(currentDate, currentTime, user, restaurantWithTodayMenu);
        Vote savedVote;
        if (todayVoteFromDb == null) {
            savedVote = voteRepository.save(newVote);
        } else if (currentTime.isBefore(voteLimit)) {
            voteRepository.delete(todayVoteFromDb);
            savedVote = voteRepository.save(newVote);
        } else {
            throw new IllegalArgumentException("You can vote again only before 11:00 AM");
        }

    }

    public Vote create(Vote vote, int userId) {
        LocalDate currentDate = LocalDate.now();
        Restaurant restaurantWithTodayMenu = restaurantRepository.getOneWithTodayMenu(currentDate, 10);
        if (restaurantWithTodayMenu == null) {
            throw new IllegalArgumentException("This restaurant doesn't have menu for today");
        }
        LocalTime currentTime = LocalTime.now();
        LocalTime voteLimit = LocalTime.of(11, 0, 0);
        Vote todayVoteFromDb = voteRepository.getByDateAndUserId(currentDate, userId);
        User user = userRepository.findById(userId).orElseThrow();
        Vote newVote = new Vote(currentDate, currentTime, user, restaurantWithTodayMenu);
        Vote savedVote;
        if (todayVoteFromDb == null) {
            savedVote = voteRepository.save(newVote);
        } else if (currentTime.isBefore(voteLimit)) {
            voteRepository.delete(todayVoteFromDb);
            savedVote = voteRepository.save(newVote);
        } else {
            throw new IllegalArgumentException("You can vote again only before 11:00 AM");
        }
return vote;
    }
}
