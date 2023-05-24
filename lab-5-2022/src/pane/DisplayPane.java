package pane;

import java.util.ArrayList;

import component.TodoItem;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class DisplayPane extends VBox {
    private final ArrayList<ArrayList<TodoItem>> todoLists = new ArrayList<>();

    public DisplayPane() {
        this.setBackground(new Background(new BackgroundFill(Color.WHITE,
                CornerRadii.EMPTY, Insets.EMPTY)));
        this.setPadding(new Insets(20));
        this.setSpacing(10);
    }

    public void addTodoList() {
        this.todoLists.add(new ArrayList<>());
    }

    public void setActiveTodoList(int index) {
        if (index < 0 || index >= this.todoLists.size()) {
            return;
        }

        this.getChildren().clear();
        this.getChildren().addAll(this.todoLists.get(index));
    }

    public void addTodoItem(TodoItem todoItem) {
        int currentPage = RootPane.getNavigationPane().getCurrentPage();
        this.todoLists.get(currentPage).add(todoItem);
        this.getChildren().add(todoItem);
    }

    public void removeTodoItem(TodoItem todoItem) {
        int currentPage = RootPane.getNavigationPane().getCurrentPage();
        this.todoLists.get(currentPage).remove(todoItem);
        this.getChildren().remove(todoItem);
    }
}
