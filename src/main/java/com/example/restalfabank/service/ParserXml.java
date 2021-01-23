package com.example.restalfabank.service;

import com.example.restalfabank.model.Box;
import com.example.restalfabank.model.Item;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
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

    public void parseXml(String filePath, String classpath, String urlPath) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;

        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }

        XMLHandler handler = new XMLHandler();

        if (filePath.length() != 0) {
            File file = new File(filePath);
            try {
                parser.parse(file, handler);
            } catch (SAXException | IOException e) {
                e.printStackTrace();
            }
        }

        if (classpath.length() != 0) {
            File file = new File(classpath);
            try {
                parser.parse(file, handler);
            } catch (SAXException | IOException e) {
                e.printStackTrace();
            }
        }

        if (urlPath.length() != 0) {
            URL url = null;
            try {
                url = new URL(urlPath);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            File file = null;
            try {
                file = Paths.get(url.toURI()).toFile();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            try {
                parser.parse(file, handler);
            } catch (SAXException | IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static class XMLHandler extends DefaultHandler {

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
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
                        .filter(boxes -> contained_in.equals(boxes.getId()))
                        .findAny()
                        .orElse(null);

                items.add(new Item(id, box, color));
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals("Box")) {
                stack.pop();
            }
        }
    }
}
