package com.example.restalfabank.service;

import com.example.restalfabank.model.Box;
import com.example.restalfabank.model.Item;
import org.springframework.stereotype.Service;

import java.util.TreeSet;

@Service
public class WriteDbService {

    private final BoxServiceImpl boxService;
    private final ItemServiceImpl itemService;

    public WriteDbService(BoxServiceImpl boxService, ItemServiceImpl itemService) {
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
        parserXml.parseXml(file, classpath, url);

        TreeSet<Box> boxes = ParserXml.getBoxes();
        TreeSet<Item> items = ParserXml.getItems();

        writeDb(boxes, items);
    }

}
