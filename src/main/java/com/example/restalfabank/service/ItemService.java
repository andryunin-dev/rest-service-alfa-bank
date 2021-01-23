package com.example.restalfabank.service;

import com.example.restalfabank.model.Item;
import com.example.restalfabank.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    public Item getById(Integer id) {
        return itemRepository.getOne(id);
    }

    public void save(Item item) {
        itemRepository.save(item);
    }

    public List<Item> getAll() {
        return itemRepository.findAll();
    }

}
