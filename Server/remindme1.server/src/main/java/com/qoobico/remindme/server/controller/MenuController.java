package com.qoobico.remindme.server.controller;

import com.qoobico.remindme.server.service.MenuService;
import com.qoobico.remindme.server.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MenuController {

    @Autowired
    private MenuService service;

    @RequestMapping(value = "/menus", method = RequestMethod.GET)
    @ResponseBody
    public List<Menu> getAllReminders() {
        return service.getAll();
    }


    @RequestMapping(value = "/menus/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Menu getAllReminders(@PathVariable("id") long remindID) {
        return service.getByID(remindID);
    }

    @RequestMapping(value = "/menus", method = RequestMethod.POST)
    @ResponseBody
    public Menu saveRemider(@RequestBody Menu menu) {
        return service.save(menu);

    }

    @RequestMapping(value = "/menus1/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable long id) {
        service.remove(id);
    }



}
