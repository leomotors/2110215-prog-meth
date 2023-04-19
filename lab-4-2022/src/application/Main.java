package application;

import java.util.ArrayList;
import java.util.Scanner;

import entity.base.Item;
import entity.base.Updatable;
import entity.container.Dish;
import entity.container.Pan;
import entity.counter.Bin;
import entity.counter.ChoppingBoard;
import entity.counter.Counter;
import entity.counter.Crate;
import entity.counter.DishWasher;
import entity.counter.Stove;
import logic.Customer;
import logic.LogicUtil;
import logic.Player;

public class Main {

    private static Player player;
    private static Scanner keyBoard;

    private static boolean isGameActive;

    private static ArrayList<Customer> customerList;
    private static ArrayList<Counter> kitchenList;

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        keyBoard = new Scanner(System.in);

        while (true) {
            System.out.println("=============================");
            System.out.println("Welcome to");
            System.out.println("Overdue! All You Can Rush");
            System.out.println("=============================");
            System.out.println("What are you doing?");
            System.out.println("1) Start Game");
            System.out.println("2) Quit");
            System.out.println("=============================");
            int results = getChoice();

            if (results == 1) {
                System.out.println("=============================");
                startGame();
            } else if (results == 2) {
                break;
            } else {
                System.out.println("Invalid Input, Terminate the game.");
                break;
            }
        }
    }

    // ======================================================================

    public static int getChoice() {
        System.out.print(">> ");
        String input = keyBoard.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // ======================================================================

    public static void startGame() {
        isGameActive = true;

        player = new Player();
        populateCustomer();
        populateKitchen();

        int choice;

        while (isGameActive) {
            System.out.println("Kitchen Menu");
            System.out.println("Current Customer");
            // Print Customer
            printArrayList(customerList);
            if (player.getHoldingItem() == null) {
                System.out.println("Your Hand: Empty");
            } else {
                System.out.println("Your Hand: " + player.getHoldingItem());
            }

            System.out.println("=============================");
            // Print Kitchen Equipment
            printArrayList(kitchenList);
            System.out
                    .println((kitchenList.size() + 1) + ") Serve the Customer");
            System.out.println("=============================");
            System.out.println("What are you going to interact with?");
            System.out.println("=============================");
            choice = getChoice();
            if (choice > 0 && choice < kitchenList.size() + 1) {
                kitchenList.get(choice - 1).interact(player);
            } else if (choice == kitchenList.size() + 1) {
                if (serveCustomer()) {
                    if (customerList.size() <= 0) {
                        System.out.println("Congrats! You win the game.");
                        isGameActive = false;
                    }
                } else {
                    System.out.println(
                            "You cannot serve the customer with that!");
                }
            } else {
                System.out.println("Invalid Choice");
            }
            processUpdate();
            System.out.println("=============================");
        }
    }

    public static void populateCustomer() {
        customerList = new ArrayList<Customer>();
        for (int i = 0; i < LogicUtil.getRandomNumber(3) + 1; i++) {
            customerList.add(new Customer(LogicUtil.getRandomIngredientOrder(),
                    LogicUtil.getRandomNumber(3) + 1));
        }
    }

    public static void populateKitchen() {
        kitchenList = new ArrayList<Counter>();
        kitchenList.add(new Crate("Lettuce"));
        kitchenList.add(new Crate("Meat"));
        kitchenList.add(new Crate("Egg"));
        kitchenList.add(new Crate("Bread"));

        kitchenList.add(new Counter(new Dish()));
        kitchenList.add(new Counter(new Dish()));
        kitchenList.add(new Counter(new Dish()));
        kitchenList.add(new ChoppingBoard());
        kitchenList.add(new Stove(new Pan()));
        kitchenList.add(new Stove(new Pan()));
        kitchenList.add(new Bin());
        kitchenList.add(new DishWasher());
    }

    public static <T> void printArrayList(ArrayList<T> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println((i + 1) + ") " + arrayList.get(i));
        }
    }

    public static void processUpdate() {

        if (!isGameActive) {
            return;
        }

        for (Customer c : customerList) {
            c.update();
        }

        for (Counter c : kitchenList) {
            if (c instanceof Updatable) {
                ((Updatable) c).update();
            }
        }

        // Removes all upset customer
        for (int i = customerList.size() - 1; i >= 0; i--) {
            if (customerList.get(i).isUpset()) {
                customerList.remove(i);
            }
        }

        // If all customer left, you lose
        if (customerList.size() <= 0) {
            System.out.println("Game Over, You Lose.");
            isGameActive = false;
        }
    }

    public static boolean serveCustomer() {

        Item currentHand = player.getHoldingItem();

        if (player.isHandEmpty() || !(currentHand instanceof Dish)) {
            return false;
        }

        for (Customer c : customerList) {
            if (c.verifyOrder(((Dish) currentHand).getContent())) {
                customerList.remove(c);

                Dish currentDish = (Dish) currentHand;
                currentDish.clearContent();
                currentDish.setDirty(100);

                return true;
            }
        }
        return false;
    }

}
