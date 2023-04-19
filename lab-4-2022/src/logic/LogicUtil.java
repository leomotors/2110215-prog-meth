package logic;

import java.util.Random;

import entity.base.Ingredient;
import entity.ingredient.Bread;
import entity.ingredient.Egg;
import entity.ingredient.Lettuce;
import entity.ingredient.Meat;

public class LogicUtil {
    public static Ingredient createIngredientFromName(String s)
            throws InvalidIngredientException {
        switch (s) {
            case "Lettuce":
                return new Lettuce();
            case "Meat":
                return new Meat();
            case "Egg":
                return new Egg();
            case "Bread":
                return new Bread();
            default:
                throw new InvalidIngredientException(
                        "There is no Ingredient named " + s);
        }
    }

    public static int getRandomNumber(int num) {
        Random r = new Random();
        return r.nextInt(num);
    }

    public static String[] getRandomIngredientOrder() {
        int order = getRandomNumber(8);
        switch (order) {
            case 0:
                return new String[] { "Well Done Steak", "Chopped Lettuce" };
            case 1:
                return new String[] { "Medium Rare Steak" };
            case 2:
                return new String[] { "Bread", "Chopped Lettuce",
                        "Cooked Burger" };
            case 3:
                return new String[] { "Bread", "Chopped Lettuce",
                        "Sunny Side Egg" };
            case 4:
                return new String[] { "Medium Rare Steak", "Fried Egg" };
            case 5:
                return new String[] { "Medium Rare Steak", "Sunny Side Egg" };
            case 6:
                return new String[] { "Bread", "Cooked Burger" };
            case 7:
                return new String[] { "Bread", "Chopped Lettuce",
                        "Cooked Burger", "Sunny Side Egg" };
            default:
                return null;
        }
    }
}
