import GUI.*;
import GUI.Frame;
import Logic.Game.Game;
import Logic.Player.Bot;
import Logic.Player.BotType;
import Logic.Player.Human;

import java.util.Vector;

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

        Bot bot2 = new Bot(2, card3, card4, BotType.Paranoid);
        Bot bot3 = new Bot(3, card5, card6, BotType.Cautious_Assassin);
        Bot bot4 = new Bot(4, card7, card8, BotType.Coup_Lover);

        Vector<Bot> bots = new Vector<>();
        bots.add(bot2);
        bots.add(bot3);
        bots.add(bot4);

       BotSection section2 = new BotSection(bot2);
       BotSection section3 = new BotSection(bot3);
       BotSection section4 = new BotSection(bot4);

        bot2.start();
        bot3.start();
        bot4.start();

        section2.start();


        Game game = new Game(bots);




        new Frame();


    }
}