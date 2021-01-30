package com.example.restalfabank.service;

import com.example.restalfabank.model.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterService {

    private final ItemServiceImpl itemService;

    public List<Item> resultItems(String box, String color) {

        Integer id = null;
        try {
            id = Integer.valueOf(box);
        } catch (NumberFormatException nfe) {
            box = "";
        }

        if (box.isEmpty() && color.isEmpty()) {
            return itemService.findAll();
        }

        if (box.isEmpty()) {
            return itemService.findByColor(color);
        }

        if (color.isEmpty()) {
            return itemService.findByBoxId(id);
        }

        return itemService.findByBoxIdAndColor(id, color);
    }

}
