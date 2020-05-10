package com.github.restaurantvotingsystem.web;

import com.github.restaurantvotingsystem.model.Vote;
import com.github.restaurantvotingsystem.repository.VoteRepository;
import com.github.restaurantvotingsystem.service.VoteService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    static final String REST_URL = "/rest";
    private static final Logger log = getLogger(VoteController.class);

    @Autowired
    private VoteService voteService;

    @Autowired
    private VoteRepository voteRepository;

    @PostMapping("/profile/votes")
    public Vote voting(@RequestParam int restaurantId) {
        log.info("voting as user id={} for restaurant id={}", SecurityUtil.authUserId(), restaurantId);
        return voteService.voting(SecurityUtil.authUserId(), restaurantId);
    }

    @GetMapping("/admin/votes/history")
    public List<Vote> getVotesHistory() {
        log.info("get votes history with restaurant");
        return voteRepository.getAllWithRestaurant();
    }
}
