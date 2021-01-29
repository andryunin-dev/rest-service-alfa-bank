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

    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    public void save(Item item) {
        itemRepository.save(item);
    }

}
