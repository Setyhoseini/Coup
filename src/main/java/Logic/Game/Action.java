package Logic.Game;

import GUI.Card;
import GUI.HumanSection;
import Logic.Player.Human;

import java.util.Collections;

public class Action {

    public static void income(int by) {
        if (by == 1) Human.updateCoins(1);
        else Game.getBotByNum(by).updateCoins(1);

        //***************
        Game.changeTurn();
    }

    public static void foreignAid(int by) {
        if (by == 1) Human.updateCoins(2);
        else Game.getBotByNum(by).updateCoins(2);
    }

    public static void tax(int by) {
        if (by == 1) Human.updateCoins(3);
        else Game.getBotByNum(by).updateCoins(3);
    }

    public static void exchangeOne(int by, int card) {
        if (by == 1) {
            Card c = (card == 1 ? Human.card1 : Human.card2);
            Collections.shuffle(Card.Deck);
            Card x = Card.Deck.remove(0);
            if (card == 1) {
                Human.setCard1(x);
                HumanSection.setCard1(x);
            } else {
                Human.setCard2(x);
                HumanSection.setCard2(x);
            }
            Card.Deck.add(c);
        }
        else {
            Card c = (card == 1 ? Game.getBotByNum(by).getCard1() : Game.getBotByNum(by).getCard2());
            Collections.shuffle(Card.Deck);
            Game.getBotByNum(by).setCard1(Card.Deck.remove(0));
            if (card == 1) {
                Game.getBotByNum(by).setCard1(Card.Deck.remove(0));
            } else {
                Game.getBotByNum(by).setCard2(Card.Deck.remove(0));
            }
            Card.Deck.add(c);
        }


        //***************
        Game.changeTurn();
    }

    public void exchangeTwo(int by) {

    }

    public void coup(int by, int on) {

    }

    public void assassinate(int by, int on) {

    }

    public void steal(int by, int on) {

    }

    public void block(int by, int on) {

    }

    public void challenge(int by, int on) {

    }
}
