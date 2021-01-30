package com.example.restalfabank.service;

import com.example.restalfabank.model.Item;
import com.example.restalfabank.repository.ItemRepository;
import com.example.restalfabank.service.interfaces.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public void save(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public List<Item> findByColor(String color) {
        return itemRepository.findByColor(color);
    }

    public List<Item> findByBoxId(Integer id) {
        return itemRepository.findByBoxId(id);
    }

    public List<Item> findByBoxIdAndColor(Integer id, String color) {
        return itemRepository.findByBoxIdAndColor(id, color);
    }

}
