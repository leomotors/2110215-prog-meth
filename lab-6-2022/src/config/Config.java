package config;

import java.util.Map;

import javafx.scene.input.KeyCode;

public class Config {
	public static final int SCREEN_WIDTH = 300;
	public static final int K_TILE = 4;
	public static final String[] K_TILE_KEY = { "D", "F", "J", "K" };
	public static final KeyCode[] KEY_CODE = { KeyCode.D, KeyCode.F, KeyCode.J, KeyCode.K };
	public static final int SUBTILE_PER_TILE = 50;
	public static final int TILE_HEIGHT = SUBTILE_PER_TILE * 10;
	public static final double TILE_SECTION_BORDER_WIDTH = 0.1;
	public static final int PRESS_KEY_HEIGHT = 25;
	public static final int MENU_HEIGHT = 25;
	public static final Map<String, Integer> KEY_MAPPING = Map.ofEntries(Map.entry("64", 0), Map.entry("192", 1),
			Map.entry("320", 2), Map.entry("448", 3));
	public static final int TILE_TIME_RANGE = 2000;
	public static final int TIME_RANGE_PER_TILE = TILE_TIME_RANGE / SUBTILE_PER_TILE;
	public static final int DELAY_BETWEEN_FRAME = 5;
	public static final int HIT_KEY_PRESS_DELAY = 100;
	public static final int MISS_BEFORE_HIT_KEY_PRESS_DELAY = 50;
}
