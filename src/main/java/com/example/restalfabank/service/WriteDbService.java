package com.example.restalfabank.service;

import com.example.restalfabank.model.Box;
import com.example.restalfabank.model.Item;
import com.example.restalfabank.service.interfaces.BoxService;
import com.example.restalfabank.service.interfaces.ItemService;
import com.example.restalfabank.service.parser.GetArgument;
import com.example.restalfabank.service.parser.ParserXml;
import com.example.restalfabank.service.parser.Task;
import com.example.restalfabank.service.parser.TaskFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.TreeSet;

@Service
@RequiredArgsConstructor
public class WriteDbService {

    private final BoxService boxService;
    private final ItemService itemService;

    public void loadDataFromXmlToDb(String[] args) {

        TaskFactory taskFactory = new TaskFactory();

        String argument = GetArgument.getArgument(args);

        Task task = taskFactory.createTask(argument);

        if (!argument.isEmpty()) {
            task.execute(argument.split("=")[1]);
        }

        TreeSet<Box> boxes = ParserXml.getBoxes();

        TreeSet<Item> items = ParserXml.getItems();

        writeDb(boxes, items);
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

}
