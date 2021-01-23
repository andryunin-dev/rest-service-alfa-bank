package com.example.restalfabank.repository;

import com.example.restalfabank.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
