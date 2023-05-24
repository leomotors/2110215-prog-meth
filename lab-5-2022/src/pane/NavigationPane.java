package pane;

import java.util.ArrayList;

import component.CreatePageButton;
import component.PageButton;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class NavigationPane extends VBox {
    private final ArrayList<PageButton> pageButtons = new ArrayList<>();
    private int currentPage;

    public NavigationPane() {
        this.setPrefWidth(80);
        this.setBackground(
                new Background(new BackgroundFill(Color.GRAY, null, null)));
        this.setSpacing(15);
        this.setPadding(new Insets(15));

        CreatePageButton createPageButton = new CreatePageButton();
        this.getChildren().add(createPageButton);
    }

    public void addPage() {
        var newPageNumber = this.pageButtons.size();
        var pageBtn = new PageButton(newPageNumber);
        this.pageButtons.add(pageBtn);

        this.getChildren().add(this.getChildren().size() - 1, pageBtn);
        RootPane.getDisplayPane().addTodoList();

        this.setCurrentPage(newPageNumber);
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(int pageNumber) {
        if (pageNumber < 0 || pageNumber >= this.pageButtons.size()) {
            return;
        }

        this.pageButtons.get(this.getCurrentPage()).setActive(false);
        this.currentPage = pageNumber;
        this.pageButtons.get(pageNumber).setActive(true);
        RootPane.getDisplayPane().setActiveTodoList(pageNumber);
    }
}
