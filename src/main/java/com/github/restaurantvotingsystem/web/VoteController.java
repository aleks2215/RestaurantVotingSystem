package com.github.restaurantvotingsystem.web;

import com.github.restaurantvotingsystem.model.Menu;
import com.github.restaurantvotingsystem.model.Vote;
import com.github.restaurantvotingsystem.repository.VoteRepository;
import com.github.restaurantvotingsystem.service.VoteService;
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
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    static final String REST_URL = "/rest/profile/votes";
    private static final Logger log = getLogger(VoteController.class);

    @Autowired
    private VoteService voteService;

    @Autowired
    private VoteRepository voteRepository;

    @PostMapping
    public Vote voting(@RequestParam int restaurantId) {
        log.info("voting as user id={} for restaurant id={}",SecurityUtil.authUserId(), restaurantId);
        return voteService.voting(SecurityUtil.authUserId(), restaurantId);
    }

    @GetMapping("/history")
    public List<Vote> getVotesHistory() {
        return voteRepository.getAllWithRestaurant();
    }

    //    public Vote create(@RequestParam int restaurantId) {
//        log.info("voting for restaurant {} from user {}", restaurantId, SecurityUtil.getUserId());
//        return voteService.create(SecurityUtil.getUserId(), restaurantId);
//    }
//
//    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    public void update(@RequestParam int restaurantId) {
////        voteService.addOrReplace(SecurityUtil.getUserId(), restaurantId);
//    }

//    @PostMapping
//    public void addOrReplace(@RequestParam int restaurantId) {
//        voteService.addOrReplace(SecurityUtil.getUserId(), restaurantId);
//    }

//    @PostMapping(value = "/{restaurantId}/vote")
//    public ResponseEntity<Vote> create(@PathVariable int restaurantId) {
////        Vote newVote = voteService.create(vote, SecurityUtil.getUserId());
//
////        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
////                .path(REST_URL + "/{id}/vote/{id}")
////                .buildAndExpand(newVote.getId()).toUri();
////
////        return ResponseEntity.created(uriOfNewResource).body(newVote);
//    }
}
