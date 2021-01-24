package com.example.restalfabank.service.interfaces;

import com.example.restalfabank.model.Item;

import java.util.List;

public interface ItemService {

    List<Item> getAll();

    void save(Item item);

}
