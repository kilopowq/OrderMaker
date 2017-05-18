package com.qoobico.remindme.server.service;

import com.qoobico.remindme.server.repository.MenuRepository;
import com.qoobico.remindme.server.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository repository;

    public List<Menu> getAll() {
        return repository.findAll();
    }

    public Menu getByID(long id) {
        return repository.findOne(id);
    }

    public Menu save(Menu menu) {
        return repository.saveAndFlush(menu);
    }

    public void remove(long id) {
        repository.delete(id);
    }
}
