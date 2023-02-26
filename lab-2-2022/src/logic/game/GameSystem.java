package logic.game;

import java.util.ArrayList;

import logic.unit.BaseCompetitor;
import logic.unit.Sorcerer;
import logic.unit.Tiger;
import logic.unit.ToughMan;

public class GameSystem {
    private ArrayList<BaseCompetitor> allCompetitors;
    private boolean gameEnd;
    private static GameSystem instance = null;

    public static GameSystem getInstance() {
        if (instance == null) {
            instance = new GameSystem();
        }
        return instance;
    }

    private GameSystem() {
        allCompetitors = new ArrayList<BaseCompetitor>();
        allCompetitors.add(new BaseCompetitor("c1"));
        allCompetitors.add(new BaseCompetitor("c2", 5, 2));
        allCompetitors.add(new Tiger("t1", 6, 4));
        allCompetitors.add(new Sorcerer("s1", 5, 3));
        allCompetitors.add(new ToughMan("c1", 10, 1));
        setGameEnd(false);
    }

    public ArrayList<BaseCompetitor> getAllCompetitors() {
        return allCompetitors;
    }

    public void setAllCompetitors(ArrayList<BaseCompetitor> allCompetitors) {
        this.allCompetitors = allCompetitors;
    }

    public boolean isGameEnd() {
        if (allCompetitors.size() <= 1) {
            gameEnd = true;
        }
        return gameEnd;
    }

    public void setGameEnd(boolean gameEnd) {
        this.gameEnd = gameEnd;
    }

    public void addNewCompetitor(String name, int choice) {
        switch (choice) {
            case 0: {
                this.allCompetitors.add(new BaseCompetitor(name));
                break;
            }
            case 1: {
                this.allCompetitors.add(new Sorcerer(name));
                break;
            }
            case 2: {
                this.allCompetitors.add(new Tiger(name));
                break;
            }
            case 3: {
                this.allCompetitors.add(new ToughMan(name));
                break;
            }
        }
    }

    public void printCompetitorsStatus() {
        for (int i = 0; i < allCompetitors.size(); i++) {
            BaseCompetitor temp = allCompetitors.get(i);
            System.out.println(
                    temp.getType() + " " + temp.getName() + " hp: " + temp.getHp() + " power: " + temp.getPower());
        }
    }

    public void removeDeadCompetitors() {
        for (int j = allCompetitors.size() - 1; j >= 0; j--) {
            if (allCompetitors.get(j).getHp() <= 0) {
                allCompetitors.remove(j);
            }
        }
    }
}
