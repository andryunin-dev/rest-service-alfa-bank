package com.example.restalfabank.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "box")
public class Box {

    @Id
    private Integer id;

    @Column(name = "contained_in")
    private Integer contained_in;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "box")
    private Set<Item> items;

    public Box() {
    }

    public Box(Integer id, Integer contained_in) {
        this.id = id;
        this.contained_in = contained_in;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContained_in() {
        return contained_in;
    }

    public void setContained_in(Integer contained_in) {
        this.contained_in = contained_in;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Box{" +
                "id=" + id +
                ", contained_in=" + contained_in +
                '}';
    }
}
