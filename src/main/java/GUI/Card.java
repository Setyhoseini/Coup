package GUI;
import javax.swing.*;
import java.util.ArrayList;

public enum Card {

    Back(new ImageIcon("ba.png"), null),
    Duke(new ImageIcon("du.png"), new ImageIcon("du-2.png")),
    Contessa(new ImageIcon("co.png"), new ImageIcon("co-2.png")),
    Ambassador(new ImageIcon("am.png"), new ImageIcon("am-2.png")),
    Captain(new ImageIcon("ca.png"), new ImageIcon("ca-2.png")),
    Assassin(new ImageIcon("as.png"), new ImageIcon("as-2.png"));

    private final ImageIcon icon1;
    private final ImageIcon icon2;

    public static ArrayList<Card> Deck = new ArrayList<>();

    Card(ImageIcon icon1, ImageIcon icon2) {
        this.icon1 = icon1;
        this.icon2  = icon2;
    }

    public ImageIcon getImage() {
        return icon1;
    }

    public ImageIcon getDeadImage() {
        return icon2;
    }
}