package application;

import java.util.ArrayList;

import config.Config;
import javafx.scene.control.Label;

public class ScoreProcessor {

	private BeatMap beatMap;
	private KeyPressTiles keyPressTiles;
	private Label hitScoreLabel, missScoreLabel;
	private int hit, miss;
	private ArrayList<Integer> notePosition;

	public ScoreProcessor(BeatMap beatMap, KeyPressTiles keyPressTiles, Label hitScoreLabel, Label missScoreLabel) {
		this.beatMap = beatMap;
		this.keyPressTiles = keyPressTiles;
		this.hitScoreLabel = hitScoreLabel;
		this.missScoreLabel = missScoreLabel;
		this.hit = 0;
		this.miss = 0;
		notePosition = new ArrayList<Integer>();

		for (int i = 0; i < Config.K_TILE; i++) {
			notePosition.add(0);
		}
	}

	public void updateScoreProcessorLoop() {
		int beatMapLoopMusicTimeStamp = beatMap.getCurrentMusicTimestamp();

		for (int i = 0; i < Config.K_TILE; i++) {
			int currentNoteIdx = notePosition.get(i);
			int currentNoteTime = beatMap.getBeatMapNote(i, currentNoteIdx);

			if (currentNoteTime == -1) {
				continue;
			}
			if (beatMapLoopMusicTimeStamp - currentNoteTime > Config.HIT_KEY_PRESS_DELAY) {
				miss += 1;
				notePosition.set(i, currentNoteIdx + 1);
			} else if (Math.abs(currentNoteTime - beatMapLoopMusicTimeStamp) <= Config.HIT_KEY_PRESS_DELAY
					+ Config.MISS_BEFORE_HIT_KEY_PRESS_DELAY) {
				if (keyPressTiles.isKeyPress(i)) {
					if (Math.abs(currentNoteTime - beatMapLoopMusicTimeStamp) <= Config.HIT_KEY_PRESS_DELAY) {
						hit += 1;
						notePosition.set(i, currentNoteIdx + 1);
					} else {
						miss += 1;
						notePosition.set(i, currentNoteIdx + 1);
					}
				}
			}
		}
		this.updateScoreLabel();
	}

	public void executeScoreProcessor() {
		try {
			while (true) {
				Thread.sleep(1);
				this.updateScoreProcessorLoop();
			}
		} catch (Exception e) {
		}
	}

	public void updateScoreLabel() {
		Utility.updateLabel(hitScoreLabel, String.valueOf(hit));
		Utility.updateLabel(missScoreLabel, String.valueOf(miss));
	}
}
