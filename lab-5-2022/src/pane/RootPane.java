package pane;

import javafx.scene.layout.BorderPane;

public class RootPane extends BorderPane {
    private static NavigationPane navigationPane;
    private static DisplayPane displayPane;
    private static InputPane inputPane;

    public RootPane() {
        RootPane.navigationPane = new NavigationPane();
        RootPane.displayPane = new DisplayPane();
        RootPane.inputPane = new InputPane();

        RootPane.navigationPane.addPage();

        this.setLeft(RootPane.navigationPane);
        this.setCenter(RootPane.displayPane);
        this.setBottom(RootPane.inputPane);
    }

    public static NavigationPane getNavigationPane() {
        return RootPane.navigationPane;
    }

    public static DisplayPane getDisplayPane() {
        return RootPane.displayPane;
    }

    public static InputPane getInputPane() {
        return RootPane.inputPane;
    }
}
