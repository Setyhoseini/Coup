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

    public static void initDeck() {
        Card card1 = Card.Duke;         Card card2 = Card.Duke;         Card card3 = Card.Duke;
        Card card4 = Card.Ambassador;   Card card5 = Card.Ambassador;   Card card6 = Card.Ambassador;
        Card card7 = Card.Contessa;     Card card8 = Card.Contessa;     Card card9 = Card.Contessa;
        Card card10 = Card.Captain;     Card card11 = Card.Captain;     Card card12 = Card.Captain;
        Card card13 = Card.Assassin;    Card card14 = Card.Assassin;    Card card15 = Card.Assassin;

        Card.Deck.add(card1);
        Card.Deck.add(card2);
        Card.Deck.add(card3);
        Card.Deck.add(card4);
        Card.Deck.add(card5);
        Card.Deck.add(card6);
        Card.Deck.add(card7);
        Card.Deck.add(card8);
        Card.Deck.add(card9);
        Card.Deck.add(card10);
        Card.Deck.add(card11);
        Card.Deck.add(card12);
        Card.Deck.add(card13);
        Card.Deck.add(card14);
        Card.Deck.add(card15);
    }
}