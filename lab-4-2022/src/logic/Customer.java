package logic;

import java.util.ArrayList;
import java.util.Arrays;

import entity.base.Ingredient;
import entity.base.Updatable;

public class Customer implements Updatable {

    private String[] order;
    private int time;
    private int timeDecrementRate;

    private boolean isUpset;

    public Customer(String[] ingredientList, int timeDecrementRate) {
        order = ingredientList;
        this.time = 100;
        this.timeDecrementRate = timeDecrementRate;
        this.isUpset = false;
    }

    public String[] getOrder() {
        return order;
    }

    public void setOrder(String[] order) {
        this.order = order;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time > 0 ? time : 0;
    }

    public int getTimeDecrementRate() {
        return timeDecrementRate;
    }

    public void setTimeDecrementRate(int timeDecrementRate) {
        this.timeDecrementRate = timeDecrementRate;
    }

    public boolean verifyOrder(ArrayList<Ingredient> servedOrder) {

        if (servedOrder.size() != order.length) {
            return false;
        }

        for (Ingredient i : servedOrder) {
            boolean check = false;
            for (String s : order) { // Perform a check against each final
                                     // ingredient, it has to change to true
                                     // somewhere
                if (i.equals(s)) {
                    check = true;
                    break;
                }
            }
            if (!check) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Customer: " + Arrays.toString(order) + ", Remaining Time: "
                + time;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        time -= timeDecrementRate;

        if (time <= 0) {
            setUpset(true);
        }
    }

    public boolean isUpset() {
        return isUpset;
    }

    public void setUpset(boolean isUpset) {
        this.isUpset = isUpset;
    }

}
