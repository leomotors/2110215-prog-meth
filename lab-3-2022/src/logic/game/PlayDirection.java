package logic.game;

public enum PlayDirection {
    FORWARD,
    BACKWARD;

    public PlayDirection getOpposite() {
        if (this == PlayDirection.FORWARD) {
            return PlayDirection.BACKWARD;
        } else
            return PlayDirection.FORWARD;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
