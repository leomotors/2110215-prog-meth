package logic.unit;

public class Sorcerer extends BaseCompetitor {
    // Constructors
    public Sorcerer(String name) {
        super(name);
        this.setHp(4);
        this.setPower(2);
    }

    public Sorcerer(String name, int hp, int power) {
        super(name, hp, power);
    }

    // Methods
    public void lowerPower(BaseCompetitor enemy, int powerDown) {
        enemy.setPower(enemy.getPower() - Math.max(0, powerDown));
    }

    @Override
    public void attack(BaseCompetitor enemy) {
        var enemyType = enemy.getType();
        var myPower = this.getPower();

        int realPower = (int) Math.floor(enemyType.equals("Tiger") ? myPower * 0.5
                : enemyType.equals("ToughMan") ? myPower * 1.5 : myPower);

        enemy.setHp(enemy.getHp() - realPower);
    }
}
