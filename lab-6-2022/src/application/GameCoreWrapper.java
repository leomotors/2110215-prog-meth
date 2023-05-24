package application;

public class GameCoreWrapper extends Thread {
    // Fields
    private GameCore gameCore;

    // Constructors and Methods
    public GameCoreWrapper(BeatMap beatMap, NoteTiles noteTiles) {
        this.gameCore = new GameCore(beatMap, noteTiles);
    }

    @Override
    public void run() {
        this.gameCore.executeGameCore();
    }
}
