package com.example.restalfabank.controller;

import com.example.restalfabank.model.Box;
import com.example.restalfabank.model.Item;
import com.example.restalfabank.service.BoxService;
import com.example.restalfabank.service.FilterService;
import com.example.restalfabank.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class ItemController {

    private final BoxService boxService;
    private final ItemService itemService;

    public ItemController(BoxService boxService, ItemService itemService) {
        this.boxService = boxService;
        this.itemService = itemService;
    }


    @RequestMapping(value = "test", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Integer>> getAllItems(
            @RequestParam(defaultValue = "", required = false) String box,
            @RequestParam(defaultValue = "", required = false) String color
    ) {
        List<Item> items = itemService.getAll();
        List<Box> boxes = boxService.getAll();

        List<Item> itemsFilter = FilterService.filterItems(boxes, items, box, color);

        List<Integer> ids = new ArrayList<>();

        for (Item item : itemsFilter) {
            ids.add(item.getId());
        }

        if (items.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(ids, HttpStatus.OK);
    }

}
