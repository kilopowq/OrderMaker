package com.qoobico.remindme.server.repository;

import com.qoobico.remindme.server.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MenuRepository extends JpaRepository<Menu,Long> {
}
