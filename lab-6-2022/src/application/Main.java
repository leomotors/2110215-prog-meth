package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	private VBox root;
	private Scene scene;
	private BeatMap beatMap;
	private NoteTiles noteTiles;
	private KeyPressTiles keyPressTiles;
	private Menu menu;

	@Override
	public void start(Stage primaryStage) {
		root = new VBox();
		scene = new Scene(root);
		beatMap = new BeatMap();
		noteTiles = new NoteTiles();
		keyPressTiles = new KeyPressTiles();
		menu = new Menu(primaryStage, beatMap, noteTiles, keyPressTiles);

		root.getChildren().add(menu);
		root.getChildren().add(noteTiles);
		root.getChildren().add(keyPressTiles);
		scene.setOnKeyPressed((e) -> {
			this.keyPressTiles.handleKeyPress(e);
		});
		scene.setOnKeyReleased((e) -> {
			this.keyPressTiles.handleKeyRelease(e);
		});
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Osu! Mania Lite");
		primaryStage.show();
	}

	public void stop() {
		Utility.cleanUp(this.menu);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
