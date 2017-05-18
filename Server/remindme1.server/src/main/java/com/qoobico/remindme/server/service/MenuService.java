package com.qoobico.remindme.server.service;

import com.qoobico.remindme.server.entity.Menu;

import java.util.List;

/**
 * Created by Marik on 04.05.2016.
 */
public interface MenuService {

    List<Menu> getAll();
    Menu getByID(long id);
    Menu save(Menu menu);
    void remove(long id);
}
