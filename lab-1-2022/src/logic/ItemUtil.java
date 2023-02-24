package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import exception.NameBlankException;

public class ItemUtil {
    public static Scanner sc = new Scanner(System.in);

    public static boolean itemNameIsExist(Market market, String itemName) {
        return market.getAllItems().stream().anyMatch(
                item -> item.getItemName().equals(itemName));
    }

    public static boolean hasEnoughMoneytoBuy(Inventory toBuy, Item item, int amount) {
        return toBuy.getMoney() >= item.getPrice() * amount;
    }

    public static ArrayList<Item> getItemFromFile(String filename, ArrayList<Item> existingItems) {
        File fileToRead = new File(filename);
        ArrayList<Item> itemsFromFile = new ArrayList<Item>();

        Scanner fc = null;
        try {
            fc = new Scanner(fileToRead);

            while (fc.hasNextLine()) {
                String line = fc.nextLine();
                var tokens = line.split(" ");

                String itemName = tokens[0];

                try {
                    int itemPrice = Integer.parseInt(tokens[1]);
                    var newItem = new Item(itemName, itemPrice);

                    if (existingItems.contains(newItem) || itemsFromFile.contains(newItem)) {
                        System.out.printf(
                                "\"%s\" is duplicated, Item will not be added.\n",
                                newItem.getItemName());
                        continue;
                    }

                    itemsFromFile.add(newItem);
                    System.out.printf("\"%s\" Added to the market.\n", newItem);
                } catch (NameBlankException ex) {
                    System.out.println(
                            "Item name cannot be blank! This item will not be added.");
                } catch (IndexOutOfBoundsException | NumberFormatException ex) {
                    System.out.printf(
                            "\"%s\" is having invalid price! This item will not be added.",
                            itemName);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Cannot find file!");
        } finally {
            if (fc != null)
                fc.close();
        }

        return itemsFromFile;
    }

    public static void playerSellplayer(Inventory toSell, Inventory toBuy, Item item, int amount) {
        if (!hasEnoughMoneytoBuy(toBuy, item, amount)) {
            System.out.println(toBuy.getPlayerName() + " dont't have enough money to buy this item.");
            return;
        }

        System.out.println("=========SELL CONFIRMATION=========");
        System.out.println("    Selling " + item.getItemName() + " x" + amount
                + " to " + toBuy.getPlayerName());
        System.out.println("            for $" + item.getPrice() * amount
                + "             ");
        System.out.println(" >> Type \"1\" to confirm selling  ");
        System.out.println(" >> Type anything else to cancel ");
        System.out.println("===================================");
        String input = sc.nextLine();
        switch (input) {
            case "1":
                playerSellPlayerConfirmed(toSell, toBuy, item, amount);
                System.out.println("<<TRANSACTION COMPLETE>>");
                break;
            default:
                System.out.println("<<SELL CANCEL>>");
                break;
        }

    }

    public static void playerSellPlayerConfirmed(Inventory toSell, Inventory toBuy, Item item, int amount) {
        int price = item.getPrice() * amount;

        toSell.setMoney(toSell.getMoney() + price);
        toBuy.setMoney(toBuy.getMoney() - price);

        toSell.removeItem(item, amount);
        toBuy.addItem(item, amount);
    }

    public static void playerSellMarket(Inventory toSell, Item item, int amount) {
        System.out.println("=========SELL CONFIRMATION=========");
        System.out.println("    Selling " + item.getItemName() + " x" + amount
                + " to market      ");
        System.out.println(
                "            for $" + item.getPrice() * amount + "             ");
        System.out.println(" >> Type \"1\" to confirm selling  ");
        System.out.println(" >> Type anything else to cancel ");
        System.out.println("===================================");
        String input = sc.nextLine();
        switch (input) {
            case "1":
                playerSellMarketConfirmed(toSell, item, amount);
                System.out.println("<<TRANSACTION COMPLETE>>");
                break;
            default:
                System.out.println("<<SELL CANCEL>>");
                break;
        }
    }

    public static void playerSellMarketConfirmed(Inventory toSell, Item item, int amount) {
        int price = item.getPrice() * amount;

        toSell.setMoney(toSell.getMoney() + price);
        toSell.removeItem(item, amount);
    }

    public static void playerBuyMarket(Inventory toBuy, Item item, int amount) {
        if (!ItemUtil.hasEnoughMoneytoBuy(toBuy, item, amount)) {
            System.out.println(toBuy.getPlayerName() + " Dont't have enough money\nto buy this item.");
        } else {
            System.out.println("=========BUY CONFIRMATION=========");
            System.out.println("	" + toBuy.getPlayerName() + " is buying ");
            System.out.println("	" + item.getItemName() + " x" + amount + " for $"
                    + item.getPrice() * amount);
            System.out.println(" >> Type \"1\" to confirm buying  ");
            System.out.println(" >> Type anything else to cancel");
            System.out.println("==================================");
            String input = sc.nextLine();
            switch (input) {
                case "1":
                    playerBuyMarketConfirmed(toBuy, item, amount);
                    System.out.println("TRANSACTION COMPLETE!");
                    break;
                default:
                    System.out.println("BUY CANCEL!");
                    break;
            }
        }
    }

    public static void playerBuyMarketConfirmed(Inventory toBuy, Item item, int amount) {
        int price = item.getPrice() * amount;

        toBuy.setMoney(toBuy.getMoney() - price);
        toBuy.addItem(item, amount);
    }

}
