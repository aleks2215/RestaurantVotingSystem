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
}
