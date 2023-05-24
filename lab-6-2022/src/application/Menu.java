package application;

import java.io.File;
import java.io.FilenameFilter;

import config.Config;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Menu extends HBox {

	private Button selectMapButton, playButton;
	private Label hitLabel, hitScoreLabel, missLabel, missScoreLabel;
	private BeatMap beatMap;
	private NoteTiles noteTiles;
	private KeyPressTiles keyPressTiles;
	private GameCoreWrapper gameCoreWrapper;
	private ScoreProcessorWrapper scoreProcessorWrapper;

	public Menu(Stage primaryStage, BeatMap beatMap, NoteTiles noteTiles, KeyPressTiles keyPressTiles) {
		super();
		this.setMinHeight(Config.MENU_HEIGHT);
		this.setMaxHeight(Config.MENU_HEIGHT);
		this.beatMap = beatMap;
		this.noteTiles = noteTiles;
		this.keyPressTiles = keyPressTiles;
		selectMapButton = new Button("Select Beat Map");
		selectMapButton.setOnAction(e -> {
			File mapDir = new DirectoryChooser().showDialog(primaryStage);
			String[] osuFiles = {};
			String[] mp3Files = {};

			if (mapDir != null) {
				osuFiles = mapDir.list(new FilenameFilter() {
					@Override
					public boolean accept(File f, String name) {
						return name.endsWith(".osu");
					}
				});
				mp3Files = mapDir.list(new FilenameFilter() {
					@Override
					public boolean accept(File f, String name) {
						return name.endsWith(".mp3");
					}
				});
			}
			if (osuFiles.length == 1 && mp3Files.length == 1) {
				String osuFile = mapDir.getAbsolutePath() + "/" + osuFiles[0];
				String mp3File = mapDir.getAbsolutePath() + "/" + mp3Files[0];

				if (!this.beatMap.loadBeatMap(osuFile, mp3File)) {
					Alert alertBox = new Alert(AlertType.ERROR);

					alertBox.setHeaderText("Load Beat Map Failed");
					alertBox.setContentText("Load Beat Map Failed: Unrecognized Format");
					alertBox.show();
				}
			} else {
				Alert alertBox = new Alert(AlertType.ERROR);

				alertBox.setHeaderText("Invalid Directory");
				alertBox.setContentText("Invalid Directory: Zero or Multiple Beat Map Found");
				alertBox.show();
			}
		});
		playButton = new Button("Play");
		playButton.setOnAction(e -> {
			this.gameCoreWrapper = Utility.setUpGameCore(this.gameCoreWrapper, this.beatMap, this.noteTiles);
			this.scoreProcessorWrapper = Utility.setUpScoreProcessor(this.scoreProcessorWrapper, this.beatMap,
					this.keyPressTiles, this.hitScoreLabel, this.missScoreLabel);
		});
		hitLabel = new Label(" Hit: ");
		hitLabel.setMinHeight(Config.MENU_HEIGHT);
		hitLabel.setMaxHeight(Config.MENU_HEIGHT);
		hitScoreLabel = new Label("0");
		hitScoreLabel.setMinHeight(Config.MENU_HEIGHT);
		hitScoreLabel.setMaxHeight(Config.MENU_HEIGHT);
		missLabel = new Label(" Miss: ");
		missLabel.setMinHeight(Config.MENU_HEIGHT);
		missLabel.setMaxHeight(Config.MENU_HEIGHT);
		missScoreLabel = new Label("0");
		missScoreLabel.setMinHeight(Config.MENU_HEIGHT);
		missScoreLabel.setMaxHeight(Config.MENU_HEIGHT);
		this.getChildren().add(selectMapButton);
		this.getChildren().add(playButton);
		this.getChildren().add(hitLabel);
		this.getChildren().add(hitScoreLabel);
		this.getChildren().add(missLabel);
		this.getChildren().add(missScoreLabel);
	}

	public GameCoreWrapper getGameCoreWrapper() {
		return this.gameCoreWrapper;
	}

	public ScoreProcessorWrapper getScoreProcessorWrapper() {
		return this.scoreProcessorWrapper;
	}
}
