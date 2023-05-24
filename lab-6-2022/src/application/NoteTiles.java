package application;

import java.util.ArrayList;

import config.Config;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class NoteTiles extends HBox {

	private ArrayList<VBox> noteTile;
	private ArrayList<ArrayList<Label>> noteSubTile;

	public NoteTiles() {
		super();
		noteTile = new ArrayList<VBox>();
		noteSubTile = new ArrayList<ArrayList<Label>>();
		this.setMinHeight(Config.TILE_HEIGHT);
		this.setMaxHeight(Config.TILE_HEIGHT);

		for (int i = 0; i < Config.K_TILE; i++) {
			noteSubTile.add(new ArrayList<Label>());

			VBox iNoteTile = new VBox();
			iNoteTile.setBackground(new Background(new BackgroundFill(i % 2 == 0 ? Color.LIGHTSKYBLUE : Color.LIGHTBLUE,
					CornerRadii.EMPTY, Insets.EMPTY)));
			iNoteTile.setMinWidth(Config.SCREEN_WIDTH / Config.K_TILE);
			iNoteTile.setMaxWidth(Config.SCREEN_WIDTH / Config.K_TILE);

			for (int j = 0; j < Config.SUBTILE_PER_TILE; j++) {
				Label subTileLabel = new Label();

				noteSubTile.get(i).add(subTileLabel);
				subTileLabel.setMinWidth(iNoteTile.getMinWidth());
				subTileLabel.setMaxWidth(iNoteTile.getMaxWidth());
				subTileLabel.setMinHeight(this.getMinHeight() / Config.SUBTILE_PER_TILE);
				subTileLabel.setMaxHeight(this.getMaxHeight() / Config.SUBTILE_PER_TILE);
				subTileLabel.setBorder(new Border(new BorderStroke(Color.GREY, BorderStrokeStyle.SOLID,
						CornerRadii.EMPTY, new BorderWidths(Config.TILE_SECTION_BORDER_WIDTH))));
				iNoteTile.getChildren().add(subTileLabel);
			}
			noteTile.add(iNoteTile);
			this.getChildren().add(noteTile.get(i));
		}
	}

	void resetTileColor(int kRow, int subTile) {
		if (kRow < this.noteSubTile.size() && subTile < this.noteSubTile.get(kRow).size()) {
			this.noteSubTile.get(kRow).get(subTile).setBackground(new Background(new BackgroundFill(
					kRow % 2 == 0 ? Color.LIGHTSKYBLUE : Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		}
	}

	void setTileColor(int kRow, int subTile) {
		if (kRow < this.noteSubTile.size() && subTile < this.noteSubTile.get(kRow).size()) {
			this.noteSubTile.get(kRow).get(subTile)
					.setBackground(new Background(new BackgroundFill(kRow % 2 == 0 ? Color.DARKGREEN : Color.GREEN,
							CornerRadii.EMPTY, Insets.EMPTY)));
		}
	}
}
