package logic.unit;

public class Tiger extends BaseCompetitor {
    // Constructors
    public Tiger(String name) {
        super(name);
        this.setHp(7);
        this.setPower(5);
    }

    public Tiger(String name, int hp, int power) {
        super(name, hp, power);
    }

    // Methods
    public void train(int addPower) {
        this.setPower(this.getPower() + Math.max(0, addPower));
    }

    @Override
    public void attack(BaseCompetitor enemy) {
        var enemyType = enemy.getType();
        var myPower = this.getPower();

        int realPower = (int) Math.floor(enemyType.equals("ToughMan") ? myPower * 0.5
                : enemyType.equals("Sorcerer") ? myPower * 1.5 : myPower);

        enemy.setHp(enemy.getHp() - realPower);
    }
}
