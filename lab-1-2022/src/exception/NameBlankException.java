package exception;

public class NameBlankException extends Exception {
    public NameBlankException() {
        super("Item name cannot be blank! This item will not be added.");
    }
}
