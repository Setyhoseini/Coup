import GUI.*;
import GUI.Frame;
import Logic.Player.Bot;
import Logic.Player.BotType;
import Logic.Player.Human;

public class Main {
    public static void main(String[] args) {
        Card card1 = Card.Duke;         Card card2 = Card.Duke;         Card card3 = Card.Duke;
        Card card4 = Card.Ambassador;   Card card5 = Card.Ambassador;   Card card6 = Card.Ambassador;
        Card card7 = Card.Contessa;     Card card8 = Card.Contessa;     Card card9 = Card.Contessa;
        Card card10 = Card.Captain;     Card card11 = Card.Captain;     Card card12 = Card.Captain;
        Card card13 = Card.Assassin;    Card card14 = Card.Assassin;    Card card15 = Card.Assassin;

        Card.Deck.add(card9);
        Card.Deck.add(card10);
        Card.Deck.add(card11);
        Card.Deck.add(card12);
        Card.Deck.add(card13);
        Card.Deck.add(card14);
        Card.Deck.add(card15);

        new Human(card1, card2);
        new HumanSection(card1, card2);

        new BotSection(new Bot(2, card3, card4, BotType.Paranoid));
        new BotSection(new Bot(3, card5, card6, BotType.Cautious_Assassin));
        new BotSection(new Bot(4, card7, card8, BotType.Coup_Lover));

        new Frame();
    }
}