import GUI.*;
import GUI.Frame;

public class Main {
    public static void main(String[] args) {
        new WinnerWindow("human");
        Card.initDeck();
        new Frame();
    }
}