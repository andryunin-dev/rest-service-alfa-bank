package com.example.restalfabank.service;

import com.example.restalfabank.model.Box;
import com.example.restalfabank.model.Item;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class FilterService {

    public static List<Item> filterItems(List<Box> boxes, List<Item> items, String idBox, String color) {
        if (idBox.isEmpty() && color.isEmpty()) {
            return items;
        }

        List<Integer> allInnerBoxes = getAllInnerBoxes(boxes, idBox);

        List<Item> filterById = filterById(items, allInnerBoxes);

        List<Item> result = filterByColor(filterById, color);

        return result;
    }

    private static List<Item> filterByColor(List<Item> filterById, String color) {
        if (color.isEmpty()) {
            return filterById;
        }

        List<Item> itemsFilter = new ArrayList<>();

        for (Item item : filterById) {
            if (color.toLowerCase().equals(item.getColor())) {
                itemsFilter.add(item);
            }
            if (color.toLowerCase().equals("null") && item.getColor() == null) {
                itemsFilter.add(item);
            }
        }
        return itemsFilter;
    }

    private static List<Item> filterById(List<Item> items, List<Integer> allInnerBoxes) {
        if (allInnerBoxes.isEmpty()) {
            return items;
        }

        List<Item> itemsFilter = new ArrayList<>();

        for (Integer id : allInnerBoxes) {
            for (Item item: items) {
                if (item.getBox() != null && id.equals(item.getBox().getId())) {
                    itemsFilter.add(item);
                }
                if (item.getBox() == null && id.equals(-1)) {
                    itemsFilter.add(item);
                }
            }
        }

        itemsFilter.sort(Comparator.comparing(Item::getId));

        return itemsFilter;
    }

    private static List<Integer> getAllInnerBoxes(List<Box> boxes, String idBox) {
        List<Integer> idFilter = new ArrayList<>();

        if (idBox.toLowerCase().equals("null")) {
            idFilter.add(-1);
            return idFilter;
        }

        Integer id;

        try {
            id = Integer.valueOf(idBox);
        } catch (NumberFormatException nfe) {
            id = null;
        }

        if (boxes != null && !idBox.isEmpty() && id != null) {
            idFilter.add(id);

            PriorityQueue<Integer> queue = new PriorityQueue<>();
            queue.add(id);

            while (!queue.isEmpty()) {
                for (Box box : boxes) {
                    if (box.getContained_in() != null && box.getContained_in().equals(queue.peek())) {
                        idFilter.add(box.getId());
                        queue.add(box.getId());
                    }
                }
                queue.poll();
            }
            return idFilter;
        }
        return idFilter;
    }

}
