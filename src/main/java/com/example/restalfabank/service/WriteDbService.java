package com.example.restalfabank.service;

import com.example.restalfabank.model.Box;
import com.example.restalfabank.model.Item;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.TreeSet;

@Service
public class WriteDbService {

    private final BoxService boxService;
    private final ItemService itemService;

    public WriteDbService(BoxService boxService, ItemService itemService) {
        this.boxService = boxService;
        this.itemService = itemService;
    }

    private void writeDb(TreeSet<Box> boxes, TreeSet<Item> items) {
        if (!boxes.isEmpty()) {
            for (Box box : boxes) {
                boxService.save(box);
            }
        }

        if (!items.isEmpty()) {
            for (Item item : items) {
                itemService.save(item);
            }
        }
    }

    public void writeXmlToDb(String file, String classpath, String url) {
        ParserXml parserXml = new ParserXml();
        try {
            parserXml.parseXml(file, classpath, url);
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }

        TreeSet<Box> boxes = ParserXml.getBoxes();
        TreeSet<Item> items = ParserXml.getItems();

        writeDb(boxes, items);
    }

}
