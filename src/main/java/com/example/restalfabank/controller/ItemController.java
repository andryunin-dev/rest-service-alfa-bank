package com.example.restalfabank.controller;

import com.example.restalfabank.model.Item;
import com.example.restalfabank.service.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class ItemController {

    private final FilterService filterService;

    @RequestMapping(value = "test", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Integer>> getAllItems(
            @RequestParam(defaultValue = "", required = false) String box,
            @RequestParam(defaultValue = "", required = false) String color
    ) {
        List<Item> items = filterService.resultItems(box, color);

        if (items.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Integer> ids = new ArrayList<>();

        for (Item item : items) {
            ids.add(item.getId());
        }

        return new ResponseEntity<>(ids, HttpStatus.OK);
    }

}
