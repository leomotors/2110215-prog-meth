package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import config.Config;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class KeyPressTiles extends HBox {

	private ArrayList<Label> keyPressTiles;

	private Map<KeyCode, Boolean> keyPress;

	public KeyPressTiles() {
		super();
		keyPressTiles = new ArrayList<Label>();
		keyPress = new HashMap<KeyCode, Boolean>();
		this.setMinHeight(Config.PRESS_KEY_HEIGHT);
		this.setMaxHeight(Config.PRESS_KEY_HEIGHT);

		for (int i = 0; i < Config.K_TILE; i++) {
			Label keyPressTileLabel = new Label();

			keyPressTileLabel.setMinWidth(Config.SCREEN_WIDTH / Config.K_TILE);
			keyPressTileLabel.setMaxWidth(Config.SCREEN_WIDTH / Config.K_TILE);
			keyPressTileLabel.setMinHeight(Config.PRESS_KEY_HEIGHT);
			keyPressTileLabel.setMaxHeight(Config.PRESS_KEY_HEIGHT);
			keyPressTileLabel.setText(Config.K_TILE_KEY[i]);
			keyPressTileLabel.setAlignment(Pos.CENTER);
			keyPressTileLabel
					.setBackground(new Background(new BackgroundFill(i % 2 == 0 ? Color.LIMEGREEN : Color.LIGHTGREEN,
							CornerRadii.EMPTY, Insets.EMPTY)));
			keyPressTiles.add(keyPressTileLabel);
			this.getChildren().add(keyPressTiles.get(i));
		}
	}

	public boolean isKeyPress(int idx) {
		if (idx < 0 || idx >= Config.KEY_CODE.length || !keyPress.containsKey(Config.KEY_CODE[idx])) {
			return false;
		}
		return keyPress.get(Config.KEY_CODE[idx]);
	}

	public void handleKeyPress(KeyEvent e) {
		keyPress.put(e.getCode(), true);
		for (int i = 0; i < Config.KEY_CODE.length; i++) {
			if (e.getCode() == Config.KEY_CODE[i]) {
				this.keyPressTiles.get(i)
						.setBackground(new Background(new BackgroundFill(i % 2 == 0 ? Color.DARKGREEN : Color.GREEN,
								CornerRadii.EMPTY, Insets.EMPTY)));
			}
		}
	}

	public void handleKeyRelease(KeyEvent e) {
		keyPress.put(e.getCode(), false);
		for (int i = 0; i < Config.KEY_CODE.length; i++) {
			if (e.getCode() == Config.KEY_CODE[i]) {
				this.keyPressTiles.get(i).setBackground(new Background(new BackgroundFill(
						i % 2 == 0 ? Color.LIMEGREEN : Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
			}
		}
	}
}
