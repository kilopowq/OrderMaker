package com.qoobico.remindme.server.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "menus")
public class Menus {


    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "weight", nullable = false, length = 50)
    private String weight;

    @Column(name = "consist", nullable = false, length = 50)
    private String consist;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "type", nullable = false)
    private int type;

/*
    @Column(name = "title", nullable = false, length = 50)
    private String title;



    @Column(name = "remind_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date remindDate;
*/

    public Menus() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
/*
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getRemindDate() {
        return remindDate;
    }

    public void setRemindDate(Date remindDate) {
        this.remindDate = remindDate;
    }
    */


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getConsist() {
        return consist;
    }

    public void setConsist(String consist) {
        this.consist = consist;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

