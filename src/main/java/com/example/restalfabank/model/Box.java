package com.example.restalfabank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "box")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Box {

    @Id
    private Integer id;

    @Column(name = "contained_in")
    private Integer containedIn;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "box")
    private Set<Item> items;

    public Box(Integer id, Integer containedIn) {
        this.id = id;
        this.containedIn = containedIn;
    }
}
