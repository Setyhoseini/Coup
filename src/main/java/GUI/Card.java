package GUI;
import javax.swing.*;
import java.util.ArrayList;

public enum Card {

    Back(new ImageIcon("ba.png")),
    Duke(new ImageIcon("du.png")),
    Contessa(new ImageIcon("co.png")),
    Ambassador(new ImageIcon("am.png")),
    Captain(new ImageIcon("ca.png")),
    Assassin(new ImageIcon("as.png"));

    private final ImageIcon icon;
    public static ArrayList<Card> Deck = new ArrayList<>();

    Card(ImageIcon icon) {
        this.icon = icon;
    }

    public void setImage() {

    }

    public ImageIcon getImage() {
        return icon;
    }
}

