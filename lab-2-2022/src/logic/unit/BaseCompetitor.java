package logic.unit;

public class BaseCompetitor {
    // Fields
    private String name;
    private int power;
    private int hp;

    // Constructor
    public BaseCompetitor(String name) {
        this.setName(name);
        this.setHp(5);
        this.setPower(3);
    }

    public BaseCompetitor(String name, int hp, int power) {
        this.setName(name);
        this.setHp(hp);
        this.setPower(power);
    }

    // Methods
    public void attack(BaseCompetitor competitor) {
        competitor.setHp(competitor.getHp() - this.getPower());
    }

    public String getType() {
        return this.getClass().getSimpleName();
    }

    // Getters Setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return this.power;
    }

    public void setPower(int power) {
        this.power = Math.max(1, power);
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = Math.max(0, hp);
    }
}
