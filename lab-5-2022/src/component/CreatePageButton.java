package component;

import javafx.scene.Cursor;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import pane.RootPane;

public class CreatePageButton extends StackPane {
    // Constructors
    public CreatePageButton() {
        var circle = new Circle(20, Color.LIGHTGRAY);
        var text = new Text("+");
        text.setFont(new Font(20));

        this.setCursor(Cursor.HAND);
        this.setOnMouseClicked(event -> {
            RootPane.getNavigationPane().addPage();
        });

        this.getChildren().addAll(circle, text);
    }
}
