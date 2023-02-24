package test.grader;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import exception.NameBlankException;
import logic.Inventory;
import logic.Item;
import logic.ItemUtil;
import logic.Market;

public class ItemUtilTest {

    @Test
    public void testItemNameIsExist() {
        Market market = new Market();
        try {
            Item item1 = new Item("Dagger");
            Item item2 = new Item("Sword");
            ArrayList<Item> items = new ArrayList<Item>();
            items.add(item1);
            items.add(item2);
            market.addAllItems(items);
            assertTrue(ItemUtil.itemNameIsExist(market, "Dagger"));
            assertTrue(ItemUtil.itemNameIsExist(market, "Sword"));
            assertFalse(ItemUtil.itemNameIsExist(market, "Cannon"));
        } catch (NameBlankException e) {
            fail();
        }
    }

    @Test
    public void testHasEnoughMoneytoBuy() {
        Inventory inventory = new Inventory("Toe", 4999);
        try {
            Item item1 = new Item("Blood Bullet", 500);
            assertTrue(ItemUtil.hasEnoughMoneytoBuy(inventory, item1, 1));
            assertFalse(ItemUtil.hasEnoughMoneytoBuy(inventory, item1, 10));
        } catch (NameBlankException e) {
            fail();
        }

    }

    @Test
    public void testPlayerSellPlayerConfirmed() {
        Inventory inventory1 = new Inventory("Siri", 3000);
        Inventory inventory2 = new Inventory("Alexa", 3500);
        try {
            Item item1 = new Item("Compass", 50);
            Item item2 = new Item("Lantern", 55);
            inventory1.addItem(item1, 3);
            inventory2.addItem(item1, 1);
            inventory2.addItem(item2, 2);

            ItemUtil.playerSellPlayerConfirmed(inventory1, inventory2, item1, 1);
            assertEquals("Compass", inventory1.getItems().get(0).getItem().getItemName());
            assertEquals(2, inventory1.getItems().get(0).getCount());
            assertEquals("Compass", inventory2.getItems().get(0).getItem().getItemName());
            assertEquals(2, inventory2.getItems().get(0).getCount());
            assertEquals(3050, inventory1.getMoney());
            assertEquals(3450, inventory2.getMoney());

            ItemUtil.playerSellPlayerConfirmed(inventory2, inventory1, item1, 2);
            assertEquals("Compass", inventory1.getItems().get(0).getItem().getItemName());
            assertEquals(4, inventory1.getItems().get(0).getCount());
            assertEquals("Lantern", inventory2.getItems().get(0).getItem().getItemName());
            assertEquals(2, inventory2.getItems().get(0).getCount());
            assertEquals(2950, inventory1.getMoney());
            assertEquals(3550, inventory2.getMoney());

        } catch (NameBlankException e) {
            fail();
        }

    }

    @Test
    public void testPlayerSellMarketConfirmed() {
        Inventory inventory1 = new Inventory("Chrrissa", 4444);
        try {
            Item item1 = new Item("B Blood Type", 99);
            Item item2 = new Item("Tarot Card", 44);
            Item item3 = new Item("Sword", 50);
            Item item4 = new Item("Dagger", 30);
            inventory1.addItem(item1, 2);
            inventory1.addItem(item2, 3);
            inventory1.addItem(item3, 3);
            inventory1.addItem(item4, 5);

            ItemUtil.playerSellMarketConfirmed(inventory1, item1, 1);
            assertEquals("B Blood Type", inventory1.getItems().get(0).getItem().getItemName());
            assertEquals(1, inventory1.getItems().get(0).getCount());
            assertEquals(4444 + 99, inventory1.getMoney());

            ItemUtil.playerSellMarketConfirmed(inventory1, item2, 2);
            assertEquals("Tarot Card", inventory1.getItems().get(1).getItem().getItemName());
            assertEquals(1, inventory1.getItems().get(1).getCount());
            assertEquals(4444 + 99 + 44 * 2, inventory1.getMoney());

            ItemUtil.playerSellMarketConfirmed(inventory1, item3, 3);
            assertEquals(3, inventory1.getItems().size());
            assertEquals("B Blood Type", inventory1.getItems().get(0).getItem().getItemName());
            assertEquals(1, inventory1.getItems().get(0).getCount());
            assertEquals("Tarot Card", inventory1.getItems().get(1).getItem().getItemName());
            assertEquals(1, inventory1.getItems().get(1).getCount());
            assertEquals("Dagger", inventory1.getItems().get(2).getItem().getItemName());

            assertEquals(4444 + 99 + 44 * 2 + 150, inventory1.getMoney());
            assertEquals(5, inventory1.getItems().get(2).getCount());

        } catch (NameBlankException e) {
            fail();
        }

    }

    @Test
    public void testPlayerBuyMarketConfirmed() {
        try {
            Item item1 = new Item("Boomstick", 2420);
            Item item2 = new Item("Staff of Completion", 5);

            Inventory inv1 = new Inventory("Setha", 2440);
            ItemUtil.playerBuyMarketConfirmed(inv1, item1, 1);
            assertEquals(20, inv1.getMoney());
            assertTrue(inv1.getItems().get(0).getItem().equals(item1));
            assertEquals(1, inv1.getItems().get(0).getCount());

            ItemUtil.playerBuyMarketConfirmed(inv1, item2, 3);
            assertEquals(5, inv1.getMoney());
            assertTrue(inv1.getItems().get(1).getItem().equals(item2));
            assertEquals(3, inv1.getItems().get(1).getCount());

        } catch (NameBlankException e) {
            fail();
        }
    }

}
