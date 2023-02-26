package logic.unit;

public class ToughMan extends BaseCompetitor {
    // Fields
    private int maxHp;

    // Constructors
    public ToughMan(String name) {
        super(name);
        this.setHp(8);
        this.setPower(4);
        this.setMaxHp(8);
    }

    public ToughMan(String name, int hp, int power) {
        super(name, hp, power);
        this.setMaxHp(hp);
    }

    // Methods
    public void heal(int healHp) {
        this.setHp(Math.min(this.getMaxHp(), this.getHp() + Math.max(0, healHp)));
    }

    @Override
    public void attack(BaseCompetitor enemy) {
        var enemyType = enemy.getType();
        var myPower = this.getPower();

        int realPower = (int) Math.floor(enemyType.equals("Sorcerer") ? myPower * 0.5
                : enemyType.equals("Tiger") ? myPower * 1.5 : myPower);

        enemy.setHp(enemy.getHp() - realPower);
    }

    // Getters Setters
    public int getMaxHp() {
        return this.maxHp;
    }

    public void setMaxHp(int maxHp) {
        if (maxHp < 0) {
            this.maxHp = 0;
            this.setHp(0);
            return;
        }

        this.maxHp = maxHp;

        if (maxHp < this.getHp()) {
            this.setHp(maxHp);
        }
    }
}
