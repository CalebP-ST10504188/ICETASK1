package com.mycompany.groceryinventory;

import java.util.Scanner;

public class GroceryInventory {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Item[] initialItems = {
            new Item("Apple", Category.FRUITS, 0.5),
            new Item("Carrot", Category.VEGETABLES, 0.3),
            new Item("Milk", Category.DAIRY, 1.2),
            new Item("Bread", Category.BAKERY, 1.5),
            new Item("Chicken", Category.MEAT, 5.0)
        };

        Inventory inventory = new Inventory(initialItems);

        boolean running = true;
        while (running) {
            displayMenu();

            int choice = readMenuChoice(scanner);
            System.out.println();

            switch (choice) {
                case 1:
                    inventory.displayAll();
                    break;

                case 2:
                    addNewItem(scanner, inventory);
                    break;

                case 3:
                    removeExistingItem(scanner, inventory);
                    break;

                case 4:
                    Category category = readCategory(scanner,
                            "Enter category to display (FRUITS, VEGETABLES, DAIRY, BAKERY, MEAT): ");
                    System.out.println();
                    inventory.displayByCategory(category);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    running = false;
                    break;

                default:
                    break;
            }

            if (running) {
                System.out.println();
            }
        }

        scanner.close();
    }
    
    private static void displayMenu() {
        System.out.println("Grocery Store Inventory Management");
        System.out.println("1. Display all items");
        System.out.println("2. Add a new item");
        System.out.println("3. Remove an item");
        System.out.println("4. Display items by category");
        System.out.println("5. Exit");
    }

    private static int readMenuChoice(Scanner scanner) {
        while (true) {
            System.out.print("Choose an option: ");
            String input = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= 5) {
                    return choice;
                }
                System.out.println("Please enter a number between 1 and 5.");
            } catch (NumberFormatException e) {
                System.out.println("That is not a valid number. Please try again.");
            }
        }
    }

    private static void addNewItem(Scanner scanner, Inventory inventory) {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine().trim();

        Category category = readCategory(scanner,
                "Enter category (FRUITS, VEGETABLES, DAIRY, BAKERY, MEAT): ");

        double price = readPrice(scanner);

        inventory.addItem(new Item(name, category, price));
        System.out.println("Item added successfully!");
    }

    private static void removeExistingItem(Scanner scanner, Inventory inventory) {
        System.out.print("Enter item name to remove: ");
        String name = scanner.nextLine().trim();

        if (inventory.removeItem(name)) {
            System.out.println("Item removed successfully!");
        } else {
            System.out.println("No item named \"" + name + "\" was found.");
        }
    }

    private static Category readCategory(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toUpperCase();

            try {
                return Category.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid category. Please choose one of: "
                        + "FRUITS, VEGETABLES, DAIRY, BAKERY, MEAT.");
            }
        }
    }

    private static double readPrice(Scanner scanner) {
        while (true) {
            System.out.print("Enter price: ");
            String input = scanner.nextLine().trim();

            try {
                double price = Double.parseDouble(input);
                if (price >= 0) {
                    return price;
                }
                System.out.println("Price cannot be negative. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("That is not a valid price. Please try again.");
            }
        }
    }
}

