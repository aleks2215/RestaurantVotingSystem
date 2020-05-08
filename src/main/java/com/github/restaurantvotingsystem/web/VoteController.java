package com.github.restaurantvotingsystem.web;

import com.github.restaurantvotingsystem.service.VoteService;
import com.github.restaurantvotingsystem.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rest/votes", produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestParam int restaurantId) {
        voteService.addOrReplace(SecurityUtil.getUserId(), restaurantId);
    }

    @PostMapping
    public void addOrReplace(@RequestParam int restaurantId) {
        voteService.addOrReplace(SecurityUtil.getUserId(), restaurantId);
    }
}
