package com.github.restaurantvotingsystem.service;

import com.github.restaurantvotingsystem.model.Restaurant;
import com.github.restaurantvotingsystem.model.User;
import com.github.restaurantvotingsystem.model.Vote;
import com.github.restaurantvotingsystem.repository.RestaurantRepository;
import com.github.restaurantvotingsystem.repository.UserRepository;
import com.github.restaurantvotingsystem.repository.VoteRepository;
import com.github.restaurantvotingsystem.util.Exceptions.VotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.restaurantvotingsystem.util.ValidationUtil;

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

    //    public Vote create(int userId, int restaurantId) {
//        Restaurant restaurantWithTodayMenu =
//                ValidationUtil.checkNotFound(restaurantRepository.getOneWithTodayMenu(LocalDate.now(), restaurantId)
//                        , "wrong restaurantId");
//
//        Vote todayVoteFromDb = voteRepository.getByDateAndUserId(LocalDate.now(), userId);
//        if (todayVoteFromDb == null) {
//            User user = userRepository.findById(userId).orElseThrow();
//            Vote newVote = new Vote(LocalDate.now(), user, restaurantWithTodayMenu);
//            savedVote = voteRepository.save(newVote);
//        }
//
//        return null;
//    }
    public Vote voting(int userId, int restaurantId) {
        LocalDate currentDate = LocalDate.now();
        Restaurant restaurantWithTodayMenu =
                ValidationUtil.checkNotFound(restaurantRepository.getWithTodayMenu(currentDate, restaurantId)
                        , "wrong restaurantId");
        LocalTime voteLimit = LocalTime.of(11, 0, 0);
        Vote todayVoteFromDb = voteRepository.getByDateAndUserId(currentDate, userId);
        User user = userRepository.findById(userId).orElseThrow();
        Vote vote = new Vote(currentDate, user, restaurantWithTodayMenu);
        if (todayVoteFromDb == null) {
            return voteRepository.save(vote);
        } else if (LocalTime.now().isBefore(voteLimit)) {
//            voteRepository.delete(todayVoteFromDb);
            vote.setRestaurant(restaurantWithTodayMenu);
            return voteRepository.save(vote);
        } else {
            throw new VotingException("You can change your vote until 11:00 AM");
        }

    }
//
//    public Vote create(Restaurant restaurant, int userId) {
//        LocalDate currentDate = LocalDate.now();
//        Restaurant restaurantWithTodayMenu = restaurantRepository.getOneWithTodayMenu(currentDate, 10);
//        if (restaurantWithTodayMenu == null) {
//            throw new IllegalArgumentException("This restaurant doesn't have menu for today");
//        }
//        LocalTime currentTime = LocalTime.now();
//        LocalTime voteLimit = LocalTime.of(11, 0, 0);
//        Vote todayVoteFromDb = voteRepository.getByDateAndUserId(currentDate, userId);
//        User user = userRepository.findById(userId).orElseThrow();
//        Vote newVote = new Vote(currentDate, user, restaurantWithTodayMenu);
//        Vote savedVote;
//        if (todayVoteFromDb == null) {
//            savedVote = voteRepository.save(newVote);
//        } else if (currentTime.isBefore(voteLimit)) {
//            voteRepository.delete(todayVoteFromDb);
//            savedVote = voteRepository.save(newVote);
//        } else {
//            throw new IllegalArgumentException("You can vote again only before 11:00 AM");
//        }
//        return vote;
//    }

//    public Vote voting(int userId, int restaurantId) {
//        LocalDate currentDate = LocalDate.now();
//        Restaurant restaurantWithTodayMenu = restaurantRepository.getOneWithTodayMenu(currentDate, restaurantId);
//        if (restaurantWithTodayMenu == null) {
//            throw new IllegalArgumentException("This restaurant doesn't have menu for today");
//        }
//        LocalTime currentTime = LocalTime.now();
//        LocalTime voteLimit = LocalTime.of(11, 0, 0);
//        Vote todayVoteFromDb = voteRepository.getByDateAndUserId(currentDate, userId);
//        User user = userRepository.findById(userId).orElseThrow();
//        Vote newVote = new Vote(currentDate, currentTime, user, restaurantWithTodayMenu);
//        Vote savedVote;
//        if (todayVoteFromDb == null) {
//            savedVote = voteRepository.save(newVote);
//        } else if (currentTime.isBefore(voteLimit)) {
//            voteRepository.delete(todayVoteFromDb);
//            savedVote = voteRepository.save(newVote);
//        } else {
//            throw new IllegalArgumentException("You can vote again only before 11:00 AM");
//        }
//
//    }
}
