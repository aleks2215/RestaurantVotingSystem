package com.github.restaurantvotingsystem;

import com.github.restaurantvotingsystem.model.User;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private Integer userId;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        userId = user.getId();
    }

    public int getId() {
        return userId;
    }
}