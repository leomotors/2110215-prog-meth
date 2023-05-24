package component;

import javafx.scene.Cursor;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import pane.RootPane;

public class PageButton extends StackPane {
    // Fields
    private Circle circle;

    // Constructors
    public PageButton(int pageNumber) {
        this.circle = new Circle(25, Color.LIGHTGRAY);

        var text = new Text(Integer.toString(pageNumber));
        text.setFont(new Font(25));

        this.setCursor(Cursor.HAND);
        this.setOnMouseClicked(event -> {
            RootPane.getNavigationPane().setCurrentPage(pageNumber);
        });

        this.getChildren().addAll(this.circle, text);
    }

    // Methods
    public void setActive(boolean value) {
        this.circle.setFill(value ? Color.WHITE : Color.LIGHTGRAY);
    }
}
