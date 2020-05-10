package com.github.restaurantvotingsystem.to;

import org.springframework.util.Assert;

public abstract class BaseTo {
    protected Integer id;

    public BaseTo() {
    }

    public BaseTo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // doesn't work for hibernate lazy proxy
    public int id() {
        Assert.notNull(getId(), "Entity must has id");
        return getId();
    }
}
