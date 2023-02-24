package main;

import java.util.ArrayList;
import java.util.Scanner;

import exception.NameBlankException;
import logic.Inventory;
import logic.Market;
import logic.Item;
import logic.ItemUtil;

public class Main {
    static boolean isEnd;
    static ArrayList<Inventory> inventories = new ArrayList<Inventory>();
    static Market market;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        setUp();
        showMainMenu();
    }

    public static void setUp() {

        market = new Market();
        try {
            market.getAllItems().add(new Item("Sword", 100));
            market.getAllItems().add(new Item("Chestplate", 200));
            market.getAllItems().add(new Item("Boots", 150));
            market.getAllItems().add(new Item("Helmet", 150));
            market.getAllItems().add(new Item("Shield", 200));
            market.getAllItems().add(new Item("Arrow", 80));
            market.getAllItems().add(new Item("Wand", 150));
            market.getAllItems().add(new Item("Apple", 10));
            market.getAllItems().add(new Item("Potion", 25));
            market.getAllItems().add(new Item("Elixir", 100));
        } catch (NameBlankException e) {
            e.printStackTrace();
        }

        inventories.add(new Inventory("Vishnu"));
        inventories.get(0).setMoney(5000);
        inventories.get(0).addItem(market.getAllItems().get(2), 2);

        inventories.add(new Inventory("Nattee"));
        inventories.get(1).setMoney(10000);
        inventories.get(1).addItem(market.getAllItems().get(0), 3);
    }

    public static void showMainMenu() {

        String input = "999";
        while (!input.equals("0")) {
            System.out.println("========================================");
            System.out.println("       Select an option");
            System.out.println("--------------------------------");
            System.out.println("1. Show market (all items).");
            System.out.println("2. Show player inventory.");
            System.out.println("3. Add new items from file.");
            System.out.println("--------------------------------");
            System.out.println("0. Exit");
            System.out.println("========================================");
            input = (sc.nextLine());
            switch (input) {
                case "1":
                    showMarket();
                    break;
                case "2":
                    showSelectPlayerInventory();
                    break;
                case "3":
                    showAddItemFromFile();
                    break;
                case "0":
                    System.out.println("\n	!! EXIT PROGRAM !!");
                    return;
                default:
                    System.out.println("UNKNOWN COMMAND");
                    break;
            }
        }
    }

    public static void showMarket() {
        String input = "999";
        while (!input.equals("0")) {
            System.out.println("========================================");
            System.out.println("            Market");
            System.out.println("--------------------------------");
            System.out.println(market.toString());
            System.out.println("--------------------------------");
            System.out.println("Select an option");
            System.out.println("1. Buy item");
            System.out.println("--------------------------------");
            System.out.println("0. Exit Market");
            System.out.println("========================================");
            input = (sc.nextLine());
            switch (input) {
                case "1":
                    showSelectPlayerToBuyMarket();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("UNKNOWN COMMAND");
                    break;
            }
        }

    }

    public static void showSelectPlayerToBuyMarket() {
        String input = "999";
        while (!input.equals("0")) {
            System.out.println("========================================");
            System.out.println("   Select player to buy item.");
            System.out.println("--------------------------------");
            for (int i = 0; i < inventories.size(); i++) {
                System.out.println(i + 1 + ". " + inventories.get(i).getPlayerName());
            }
            System.out.println("--------------------------------");
            System.out.println("0. Back");
            System.out.println("========================================");
            input = (sc.nextLine());
            switch (input) {
                case "1":
                    showPlayerBuyingMarket(0);
                    break;
                case "2":
                    showPlayerBuyingMarket(1);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("UNKNOWN COMMAND");
                    break;
            }
        }
    }

    public static void showPlayerBuyingMarket(int i) {
        String input = "999";
        while (!input.equals("0")) {
            System.out.println("========================================");
            System.out.println("           Buy item");
            System.out.println("--------------------------------");
            System.out.println(market.toString());
            System.out.println("--------------------------------");
            System.out.println("	" + inventories.get(i).getPlayerName() + "'s money");
            System.out.println("	     $" + inventories.get(i).getMoney());
            System.out.println("--------------------------------");
            System.out.println(" >> Type item's number.");
            System.out.println(" >> Type 0 to back.");
            System.out.println("========================================");

            // input item number
            input = (sc.nextLine());
            int idx, amount;
            try {
                idx = Integer.parseInt(input);
            } catch (Exception e) {
                idx = -1;
            }
            if (idx > 0 && idx <= market.getAllItems().size()) {
                System.out.println("========================================");
                System.out.println(" >> Type amount.");
                System.out.println(" >> Type 0 to back.");
                System.out.println("========================================");

                // input amount
                input = (sc.nextLine());
                try {
                    amount = Integer.parseInt(input);
                } catch (Exception e) {
                    amount = -1;
                }
                if (amount > 0) {
                    ItemUtil.playerBuyMarket(inventories.get(i), market.getAllItems().get(idx - 1), amount);
                } else {
                    System.out.println("Invalid Amount!");
                }

            } else if (idx == 0) {
                return;
            } else {
                System.out.println("UNKNOWN COMMAND");
            }
        }
    }

    public static void showSelectPlayerInventory() {
        String input = "999";
        while (!input.equals("0")) {
            System.out.println("========================================");
            System.out.println("Select player to open inventory.");
            System.out.println("--------------------------------");
            for (int i = 0; i < inventories.size(); i++) {
                System.out.println(i + 1 + ". " + inventories.get(i).getPlayerName());
            }
            System.out.println("--------------------------------");
            System.out.println("0. Back");
            System.out.println("========================================");
            input = (sc.nextLine());
            switch (input) {
                case "1":
                    showPlayerInventory(0);
                    break;
                case "2":
                    showPlayerInventory(1);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("UNKNOWN COMMAND");
                    break;
            }
        }
    }

    public static void showPlayerInventory(int i) {
        String input = "999";
        while (!input.equals("0")) {
            System.out.println("========================================");
            System.out.println("     " + inventories.get(i).getPlayerName() + "'s inventory");
            System.out.println("            $" + inventories.get(i).getMoney());
            System.out.println("--------------------------------");
            System.out.println(inventories.get(i).toString());
            System.out.println("--------------------------------");
            System.out.println("Select an option");
            System.out.println("1. Sell item to another player.");
            System.out.println("2. Sell item to market.");
            System.out.println("--------------------------------");
            System.out.println("0. Exit inventory.");
            System.out.println("========================================");
            input = (sc.nextLine());
            switch (input) {
                case "1":
                    showSelectPlayerToSellPlayer(i);
                    break;
                case "2":
                    showSellItemToMarket(i);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("UNKNOWN COMMAND");
                    break;
            }
        }
    }

    public static void showSelectPlayerToSellPlayer(int toSell) {
        String input = "999";
        while (!input.equals("0")) {
            System.out.println("========================================");
            System.out.println("   Select player to sell to");
            System.out.println("--------------------------------");
            int id = 1;
            for (int k = 0; k < inventories.size(); k++) {
                if (k != toSell) {
                    System.out.println(id + ". " + inventories.get(k).getPlayerName());
                    id++;
                }
            }
            System.out.println("--------------------------------");
            System.out.println("0. Back");
            System.out.println("========================================");
            input = (sc.nextLine());
            int toBuy = 999;
            try {
                toBuy = Integer.parseInt(input);
            } catch (Exception e) {
                toBuy = -1;
            }
            if (toBuy > 0 && toBuy < inventories.size()) {
                toBuy--; // change to Arr idx
                if (toBuy >= toSell) {
                    toBuy++;
                }
                showInventoryToSellToPlayer(toSell, toBuy);
            } else if (toBuy != 0) {
                System.out.println("UNKNOWN COMMAND");
            }
        }

    }

    public static void showInventoryToSellToPlayer(int toSell, int toBuy) {
        String input = "999";
        while (!input.equals("0")) {
            System.out.println("========================================");
            System.out.println("    Selling item to " + inventories.get(toBuy).getPlayerName() + ".");
            System.out.println("            $" + inventories.get(toBuy).getMoney());
            System.out.println("--------------------------------");
            System.out.println("     " + inventories.get(toSell).getPlayerName() + "'s inventory");
            System.out.println("            $" + inventories.get(toSell).getMoney());
            System.out.println("--------------------------------");
            System.out.println(inventories.get(toSell).toString());
            System.out.println("--------------------------------");
            System.out.println(" >> Type item's number to sell");
            System.out.println(" >> Type 0 to back.");
            System.out.println("========================================");

            // input item number
            input = (sc.nextLine());
            int idx, amount;
            try {
                idx = Integer.parseInt(input);
            } catch (Exception e) {
                idx = -1;
            }
            if (idx > 0 && idx <= inventories.get(toSell).getItems().size()) {
                System.out.println("--------------------------------");
                System.out.println(" >> Type amount.");
                System.out.println(" >> Type 0 to back.");
                System.out.println("========================================");

                // input amount
                input = (sc.nextLine());
                try {
                    amount = Integer.parseInt(input);
                } catch (Exception e) {
                    amount = -1;
                }
                if (amount > 0 && amount <= inventories.get(toSell).getItems().get(idx - 1).getCount()) {
                    ItemUtil.playerSellplayer(inventories.get(toSell), inventories.get(toBuy),
                            inventories.get(toSell).getItems().get(idx - 1).getItem(), amount);
                } else {
                    System.out.println("Invalid Amount!");
                }
            } else if (idx != 0) {
                System.out.println("UNKNOWN COMMAND");
            }
        }
    }

    public static void showSellItemToMarket(int toSell) {
        String input = "999";
        while (!input.equals("0")) {
            System.out.println("========================================");
            System.out.println(inventories.get(toSell).getPlayerName() + " is selling item to the market.");
            System.out.println("--------------------------------");
            System.out.println("     " + inventories.get(toSell).getPlayerName() + "'s inventory");
            System.out.println("            $" + inventories.get(toSell).getMoney());
            System.out.println("--------------------------------");
            System.out.println(inventories.get(toSell).toString());
            System.out.println("--------------------------------");
            System.out.println(" >> Type item's number to sell");
            System.out.println(" >> Type 0 to back.");
            System.out.println("========================================");

            // input item number
            input = (sc.nextLine());
            int idx, amount;
            try {
                idx = Integer.parseInt(input);
            } catch (Exception e) {
                idx = -1;
            }
            if (idx > 0 && idx <= inventories.get(toSell).getItems().size()) {
                System.out.println("--------------------------------");
                System.out.println(" >> Type amount.");
                System.out.println(" >> Type 0 to back.");
                System.out.println("========================================");

                // input amount
                input = (sc.nextLine());
                try {
                    amount = Integer.parseInt(input);
                } catch (Exception e) {
                    amount = -1;
                }
                if (amount > 0 && amount <= inventories.get(toSell).getItems().get(idx - 1).getCount()) {
                    ItemUtil.playerSellMarket(inventories.get(toSell),
                            inventories.get(toSell).getItems().get(idx - 1).getItem(),
                            amount);
                } else {
                    System.out.println("Invalid Amount!");
                }
            } else if (idx != 0) {
                System.out.println("UNKNOWN COMMAND");
            }
        }
    }

    public static void showAddItemFromFile() {
        System.out.println("========================================");
        System.out.println("Type your file name");
        System.out.println("========================================");
        String fileName = sc.nextLine();

        ArrayList<Item> itemToAdd = ItemUtil.getItemFromFile(fileName, market.getAllItems());
        market.addAllItems(itemToAdd);
    }

}
