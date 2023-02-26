package application;

import java.util.ArrayList;
import java.util.Scanner;
import logic.game.GameSystem;
import logic.unit.*;

public class Main {
    private static Scanner sc;

    public static void main(String args[]) {
        System.out.println("Welcome to Colosseum game");
        sc = new Scanner(System.in);
        while (!GameSystem.getInstance().isGameEnd()) {
            GameSystem.getInstance().printCompetitorsStatus();
            System.out.println("<0> Add new competitor");
            System.out.println("<1> Attack");
            System.out.println("<2> Use skill");
            int choice = sc.nextInt();
            while (choice < 0 || choice > 2) {
                System.out.println("Invalid input");
                choice = sc.nextInt();
            }
            if (choice == 0) {
                addFlow();
            } else if (choice == 1) {
                attackFlow(GameSystem.getInstance().getAllCompetitors());
            } else if (choice == 2) {
                skillFlow(GameSystem.getInstance().getAllCompetitors());
            }
            GameSystem.getInstance().removeDeadCompetitors();
        }
        System.out.println("Game end");
        System.out.println(GameSystem.getInstance().getAllCompetitors().get(0).getType() + " : "
                + GameSystem.getInstance().getAllCompetitors().get(0).getName() + " Win");
    }

    private static void addFlow() {
        System.out.println("<0> BaseCompetitor");
        System.out.println("<1> Sorcerer");
        System.out.println("<2> Tiger");
        System.out.println("<3> ToughMan");
        System.out.println("Choose which type you want to add");
        int choice = sc.nextInt();
        while (choice < 0 || choice > 3) {
            System.out.println("Invalid input");
            choice = sc.nextInt();
        }
        System.out.println("Enter name : ");
        String name = sc.next();
        GameSystem.getInstance().addNewCompetitor(name, choice);
    }

    private static void attackFlow(ArrayList<BaseCompetitor> allCompetitors) {
        for (int i = 0; i < allCompetitors.size(); i++) {
            System.out.println("<" + i + "> " + allCompetitors.get(i).getName());
        }
        System.out.println("Enter number of attacker");
        int attacker = sc.nextInt();
        while (attacker >= allCompetitors.size() || attacker < 0) {
            System.out.println("Invalid input");
            attacker = sc.nextInt();
        }
        System.out.println("Enter which number you want to attack : ");
        int attacked = sc.nextInt();
        while (attacked >= allCompetitors.size() || attacked < 0 || attacked == attacker) {
            System.out.println("Invalid input");
            attacked = sc.nextInt();
        }
        allCompetitors.get(attacker).attack(GameSystem.getInstance().getAllCompetitors().get(attacked));

    }

    private static void skillFlow(ArrayList<BaseCompetitor> allCompetitors) {
        boolean anyoneCanUseSkill = false;
        for (int i = 0; i < allCompetitors.size(); i++) {
            if (!allCompetitors.get(i).getType().equals("BaseCompetitor")) {
                anyoneCanUseSkill = true;
                System.out.println("<" + i + "> " + allCompetitors.get(i).getName());
            }
        }
        if (anyoneCanUseSkill == false) {
            System.out.println("You don't have competitor which have skill");
            return;
        }
        System.out.println("Enter number you want to use skill");
        int skillUser = sc.nextInt();
        while (true) {
            if (skillUser < 0 || skillUser >= allCompetitors.size())
                ;
            else if (allCompetitors.get(skillUser).getType() != "BaseCompetitor")
                break;
            System.out.println("Invalid input");
            skillUser = sc.nextInt();
        }
        if (allCompetitors.get(skillUser).getType().equals("Sorcerer")) {
            System.out.println("Enter number you want to decrease power");
            int number = sc.nextInt();
            while (number < 0 || number >= allCompetitors.size() || number == skillUser) {
                System.out.println("Invalid input");
                number = sc.nextInt();
            }
            System.out.println("Enter how many power you want to decrease");
            int decreasePower = sc.nextInt();
            while (decreasePower <= 0) {
                System.out.println("Invalid input");
                decreasePower = sc.nextInt();
            }
            Sorcerer skillSorcerer = (Sorcerer) (allCompetitors.get(skillUser));
            skillSorcerer.lowerPower(GameSystem.getInstance().getAllCompetitors().get(number), decreasePower);
        } else if (allCompetitors.get(skillUser).getType().equals("Tiger")) {
            System.out.println("Enter add power after train");
            int addPower = sc.nextInt();
            while (addPower <= 0) {
                System.out.println("Invalid input");
                addPower = sc.nextInt();
            }
            Tiger skillTiger = (Tiger) (allCompetitors.get(skillUser));
            skillTiger.train(addPower);
        } else if (allCompetitors.get(skillUser).getType().equals("ToughMan")) {
            System.out.println("Enter hp you want to heal");
            int healHp = sc.nextInt();
            while (healHp <= 0) {
                System.out.println("Invalid input");
                healHp = sc.nextInt();
            }
            ToughMan skillToughMan = (ToughMan) (allCompetitors.get(skillUser));
            skillToughMan.heal(healHp);
        }
    }
}
