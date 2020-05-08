package com.github.restaurantvotingsystem.web;

import com.github.restaurantvotingsystem.model.Meal;
import com.github.restaurantvotingsystem.model.Vote;
import com.github.restaurantvotingsystem.service.VoteService;
import com.github.restaurantvotingsystem.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    static final String REST_URL = "/rest/votes";

    @Autowired
    private VoteService voteService;

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestParam int restaurantId) {
        voteService.addOrReplace(SecurityUtil.getUserId(), restaurantId);
    }

//    @PostMapping
//    public void addOrReplace(@RequestParam int restaurantId) {
//        voteService.addOrReplace(SecurityUtil.getUserId(), restaurantId);
//    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> create(@RequestBody Vote vote) {
        Vote newVote = voteService.create(vote, SecurityUtil.getUserId());

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(newVote.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(newVote);
    }
}
