package com.example.restalfabank.service.parser;

import com.example.restalfabank.model.Box;
import com.example.restalfabank.model.Item;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Comparator;
import java.util.Stack;
import java.util.TreeSet;


public class ParserXml {

    private static TreeSet<Box> boxes = new TreeSet<>(Comparator.comparing(Box::getId));
    private static TreeSet<Item> items = new TreeSet<>(Comparator.comparing(Item::getId));
    private static Stack<Integer> stack = new Stack<>();

    public static TreeSet<Box> getBoxes() {
        return boxes;
    }
    public static TreeSet<Item> getItems() {
        return items;
    }

    public static class XMLHandler extends DefaultHandler {

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            Integer contained_in;

            if (stack.empty()) {
                contained_in = null;
            } else {
                contained_in = stack.peek();
            }

            if (qName.equals("Box")) {
                Integer id = Integer.parseInt(attributes.getValue("id"));
                boxes.add(new Box(id, contained_in));
                stack.push(id);
            }

            if (qName.equals("Item")) {
                Integer id = Integer.parseInt(attributes.getValue("id"));
                String color = attributes.getValue("color");

                Box box = boxes.stream()
                        .filter(boxes -> contained_in == boxes.getId())
                        .findAny()
                        .orElse(null);

                items.add(new Item(id, box, color));
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if (qName.equals("Box")) {
                stack.pop();
            }
        }
    }

}
