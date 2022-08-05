package Logic.Player;
import GUI.Card;
import GUI.HumanSection;
import Logic.Game.ActionName;
import Logic.Game.Game;

import java.util.concurrent.atomic.AtomicBoolean;


public class Human extends Thread {
    public static int coins;
    int num;
    public static Card card1;
    public static Card card2;
    public static PlayerState state;
    AtomicBoolean running = new AtomicBoolean(true);
    public static ActionName lastAction = null;


   public Human(Card card1, Card card2) {
       Human.card1 = card1;
       Human.card2 = card2;
       num = 1;
       coins = 2;
       state = PlayerState.IsToPlay;
   }

    public static int getCoins() {
        return coins;
    }

    public int getNum() {
        return num;
    }

    public static Card getCard1() {
        return card1;
    }

    public static Card getCard2() {
        return card2;
    }

    public static void setCoins(int coins) {
        Human.coins = coins;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public static void setCard1(Card card1) {
        Human.card1 = card1;
    }

    public static void setCard2(Card card2) {
        Human.card2 = card2;
    }

    public static void updateCoins(int n) {
       coins += n;
       HumanSection.updateNum();
   }


    @Override
    public void run() {
       while (running.get()) {
           switch (state) {
               case IsToPlay:
                 HumanSection.enableIsToPlay();
                 HumanSection.waitForResponse();
                 switch (lastAction) {
                     case Tax:

                         break;
                     case Foreign_Aid:

                         break;
                     case Exchange_Both_Cards:

                         break;
                 }
                   break;
               case IsAskedToBlock:

                   break;
               case IsAskedToChallenge:

                   break;
               case IsToReactToChallenge:

                   break;
           }
       }
    }
}