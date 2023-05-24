package pane;

import component.TodoItem;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class InputPane extends HBox {
    public InputPane() {
        this.setPrefHeight(70);
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,
                CornerRadii.EMPTY, Insets.EMPTY)));
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);

        var textField = new TextField();
        textField.setPrefWidth(300);

        var button = new Button("Submit");
        button.setOnAction(event -> {
            var text = textField.getText();

            if (text.isEmpty()) {
                return;
            }

            var todoItem = new TodoItem(text);
            RootPane.getDisplayPane().addTodoItem(todoItem);

            textField.setText("");
        });

        this.getChildren().addAll(textField, button);
    }
}
