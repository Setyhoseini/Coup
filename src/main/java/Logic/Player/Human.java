package Logic.Player;
import GUI.Card;
import GUI.HumanSection;
import Logic.Game.Action;
import Logic.Game.ActionName;
import Logic.Game.Controller;
import Logic.Game.Game;

import java.util.Collections;
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
                         HumanSection.enableNeutral();


                         // todo show a loading message or something
                         try {
                             Thread.sleep(2500);
                         } catch (InterruptedException e) {
                             e.printStackTrace();
                         }

                         int challenge = Game.botChallenges();

                         if (challenge == 0) {
                             Action.tax(1);
                             Game.changeTurn();
                         }
                         else {
                             Bot challenger = Game.getBotByNum(challenge);
                             challenger.section.controller = Controller.Challenges;
                             HumanSection.enableIsToReactToChallenge();
                             HumanSection.waitForResponse();

                             lastAction = null;
                             HumanSection.enableNeutral();


                             if (card1 == Card.Duke || card2 == Card.Duke) {

                                 // todo human duke should be replaced from deck


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
                             else {
                               //  TODO win the challenge case
                             }
                         }
                         break;











                     case Foreign_Aid:

                         lastAction = null;
                         HumanSection.enableNeutral();


                         try {
                             Thread.sleep(2500);
                         } catch (InterruptedException e) {
                             e.printStackTrace();
                         }

                         //
                         int block = Game.botBlocks(1, 0, "foreign_aid");
                         if (block == 0) {
                             Action.foreignAid(1);
                             Game.changeTurn();
                         }
                         else {
                             Bot blocker = Game.getBotByNum(block);
                             blocker.section.controller = Controller.Blocks;


                             HumanSection.enableIsAskedToChallenge(block);
                             HumanSection.waitForResponse();
                             HumanSection.enableNeutral();

                             if (lastAction == ActionName.Do_Nothing) {
                                 HumanSection.enableNeutral();

                                 blocker.section.controller = Controller.Neutral;
                                 lastAction = null;

                                 // ask bots to challenge the block
                                 int challenge2 = Game.botChallenges();
                                 if (challenge2 == 0) {
                                     Game.changeTurn();
                                 }
                                 else {
                                     Bot challenger = Game.getBotByNum(challenge2);
                                     challenger.section.controller = Controller.Challenges;

                                     try {
                                         Thread.sleep(4000);
                                     } catch (InterruptedException e) {
                                         e.printStackTrace();
                                     }

                                     challenger.section.controller = Controller.Neutral;

                                     if (blocker.getCard1() == Card.Duke || blocker.getCard2() == Card.Duke) {
                                         blocker.section.controller = Controller.Won_Challenge;

                                         challenger.section.revealACard();
                                         if (challenger.card1 == null && challenger.getCard2() == null) {
                                             Game.players.remove(challenger.getNum());
                                         }



                                         // showing the bot's duke and then replacing it
                                         if (blocker.getCard1() == Card.Duke) {
                                             blocker.section.revealACard(1);

                                             try {
                                                 Thread.sleep(3000);
                                             } catch (InterruptedException e) {
                                                 e.printStackTrace();
                                             }


                                             Collections.shuffle(Card.Deck);
                                             blocker.card1 = Card.Deck.get(0);
                                             blocker.section.hideACard(1);
                                         }
                                         else {
                                             blocker.section.revealACard(2);


                                             try {
                                                 Thread.sleep(3000);
                                             } catch (InterruptedException e) {
                                                 e.printStackTrace();
                                             }


                                             Collections.shuffle(Card.Deck);
                                             blocker.card2 = Card.Deck.get(0);
                                             blocker.section.hideACard(2);
                                         }


                                         blocker.section.controller = Controller.Neutral;
                                         Game.changeTurn();
                                     }

                                     else {
                                         blocker.section.controller = Controller.Lost_Challenge;

                                         blocker.section.revealACard();
                                         if (blocker.card1 == null && blocker.card2 == null) {
                                             Game.players.remove(block);
                                         }


                                         try {
                                             Thread.sleep(3000);
                                         } catch (InterruptedException e) {
                                             e.printStackTrace();
                                         }



                                         blocker.section.controller = Controller.Neutral;


                                         Action.foreignAid(1);
                                         Game.changeTurn();
                                     }
                                 }
                             }


                             else {
                                 // when the human themselves challenge their blocker
                                 HumanSection.enableNeutral();

                                 blocker.section.controller = Controller.Neutral;
                                 lastAction = null;









                                 if (blocker.getCard1() == Card.Duke || blocker.getCard2() == Card.Duke) {
                                     blocker.section.controller = Controller.Won_Challenge;

                                     // todo the human chooses which card they wanna reveal
                                     if (Human.card1 == null && Human.getCard2() == null) {
                                         Game.players.remove(1);
                                     }


                                     // showing the bot's duke and then replacing it
                                     if (blocker.getCard1() == Card.Duke) {
                                         blocker.section.revealACard(1);

                                         try {
                                             Thread.sleep(3000);
                                         } catch (InterruptedException e) {
                                             e.printStackTrace();
                                         }


                                         Collections.shuffle(Card.Deck);
                                         blocker.card1 = Card.Deck.get(0);
                                         blocker.section.hideACard(1);
                                     }
                                     else {
                                         blocker.section.revealACard(2);


                                         try {
                                             Thread.sleep(3000);
                                         } catch (InterruptedException e) {
                                             e.printStackTrace();
                                         }


                                         Collections.shuffle(Card.Deck);
                                         blocker.card2 = Card.Deck.get(0);
                                         blocker.section.hideACard(2);
                                     }


                                     blocker.section.controller = Controller.Neutral;
                                     Game.changeTurn();
                                 }

                                 else {
                                     blocker.section.controller = Controller.Lost_Challenge;

                                     blocker.section.revealACard();
                                     if (blocker.card1 == null && blocker.card2 == null) {
                                         Game.players.remove(block);
                                     }


                                     try {
                                         Thread.sleep(3000);
                                     } catch (InterruptedException e) {
                                         e.printStackTrace();
                                     }



                                     blocker.section.controller = Controller.Neutral;


                                     Action.foreignAid(1);
                                     Game.changeTurn();
                                 }
                             }
                         }
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