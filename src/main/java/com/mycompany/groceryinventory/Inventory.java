package com.mycompany.groceryinventory;

import java.util.ArrayList;
import java.util.Arrays;

public class Inventory {

    private final ArrayList<Item> items;

    public Inventory(Item[] initialItems) {
        this.items = new ArrayList<>(Arrays.asList(initialItems));
    }

    public void displayAll() {
        System.out.println("All items in inventory:");

        if (items.isEmpty()) {
            System.out.println("The inventory is empty.");
            return;
        }

        for (Item item : items) {
            System.out.println(item);
        }
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public boolean removeItem(String name) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equalsIgnoreCase(name)) {
                items.remove(i);
                return true;
            }
        }
        return false;
    }

    public void displayByCategory(Category category) {
        System.out.println("Items in category " + category + ":");

        boolean found = false;
        for (Item item : items) {
            if (item.getCategory() == category) {
                System.out.println(item);
                found = true;
            }
        }

        if (!found) {
            System.out.println("There are no items in this category.");
        }
    }
}
