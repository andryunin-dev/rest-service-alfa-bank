package com.example.restalfabank.model;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item {

    @Id
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contained_in")
    private Box box;

    @Column(name = "color", length = 100)
    private String color;

    public Item() {
    }

    public Item(Integer id, Box box, String color) {
        this.id = id;
        this.box = box;
        this.color = color;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", box=" + box +
                ", color='" + color + '\'' +
                '}';
    }
}
