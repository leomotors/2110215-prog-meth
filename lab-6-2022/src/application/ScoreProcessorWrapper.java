package application;

import javafx.scene.control.Label;

public class ScoreProcessorWrapper extends Thread {
    // Fields
    private ScoreProcessor scoreProcessor;

    // Constructors and Methods
    public ScoreProcessorWrapper(BeatMap beatMap, KeyPressTiles keyPressTiles,
            Label hitScoreLabel, Label missScoreLabel) {
        this.scoreProcessor = new ScoreProcessor(beatMap, keyPressTiles,
                hitScoreLabel, missScoreLabel);
    }

    @Override
    public void run() {
        this.scoreProcessor.executeScoreProcessor();
    }
}
