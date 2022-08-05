package Logic.Player;
import GUI.Card;
import GUI.HumanSection;
import Logic.Game.Action;
import Logic.Game.ActionName;
import Logic.Game.Controller;
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
                         lastAction = null;



                         try {
                             Thread.sleep(2500);
                         } catch (InterruptedException e) {
                             e.printStackTrace();
                         }

                         int challenge = Game.botChallenges();
                         Bot challenger = Game.getBotByNum(challenge);
                         if (challenge == 0) {
                             Action.tax(1);
                             state = PlayerState.Neutral;
                             Game.changeTurn();
                         }
                         else {
                             challenger.section.controller = Controller.Challenges;
                             HumanSection.enableIsToReactToChallenge();
                             HumanSection.waitForResponse();

                             lastAction = null;

                             if (card1 == Card.Duke || card2 == Card.Duke) {
                                 // show message "lost challenge"
                                 challenger.section.controller = Controller.Lost_Challenge;
                                 try {
                                     Thread.sleep(4000);
                                 } catch (InterruptedException e) {
                                     e.printStackTrace();
                                 }
                                 // apply losing the challenge
                                 challenger.section.controller = Controller.Neutral;
                                 challenger.section.revealACard();
                                 if (challenger.card1 == null && challenger.card2 == null) {
                                     Game.players.remove(challenge);
                                 }

                                 // apply tax
                                 Action.tax(1);

                                 // change turn
                                 Game.changeTurn();
                             }
                         }

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