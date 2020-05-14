package com.github.restaurantvotingsystem;

import com.github.restaurantvotingsystem.model.AbstractBaseEntity;
import com.github.restaurantvotingsystem.model.Meal;

import java.util.Comparator;
import java.util.List;

public class TestUtil {

    public static  <T extends AbstractBaseEntity> Comparator<T> orderByIdComparator() {
        return new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                if (o1.getId() > o2.getId()) {
                    return 1;
                } else if (o1.getId() < o2.getId()) {
                    return -1;
                }
                return 0;
            }
        };
    }
}
