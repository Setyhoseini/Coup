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
    Integer num;
    public static Card card1;
    public static Card card2;
    public static PlayerState state;
    static AtomicBoolean running = new AtomicBoolean(true);
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




   public static void waitForResponse() {
       while (running.get() && lastAction == null) {

       }
   }












   public void applyCaseSteal(int from) {  /* there's a terrible bug here somewhere about asking the human to challenge the block*/
       lastAction = null;
       HumanSection.enableNeutral();

       try {
           Thread.sleep(2500);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }

       Integer block2 = Game.botBlocks(1, from, "steal");

       if (block2 == 0) {
           // todo waits for bots to challenge
           Integer challenge3 = Game.botChallenges(1);
           if (challenge3 == 0) {
               Action.steal(1, from);
           }
           else {
               Bot challenger = Game.getBotByNum(challenge3);
               challenger.section.controller = Controller.Challenges;
               HumanSection.enableIsToReactToChallenge();
               waitForResponse();

               lastAction = null;
               HumanSection.enableNeutral();

               if (card1 == Card.Captain || card2 == Card.Captain) {
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
                       Game.players.remove(challenge3);
                   }

                   // apply tax
                   Action.steal(1, from);

                   // change turn
                   Game.changeTurn();
               }
               else {
                   //  TODO win the challenge case
                   challenger.section.controller = Controller.Won_Challenge;
                   try {
                       Thread.sleep(4000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }

                   challenger.section.controller = Controller.Neutral;
                   // todo ask the human which card they wanna reveal
                   if (card1 == null && card2 == null) {
                       Game.players.remove(1);
                   }

                   Game.changeTurn();
               }
           }


       }
       else {
           Bot blocker = Game.getBotByNum(block2);
           blocker.section.controller = Controller.Blocks;

           HumanSection.enableIsAskedToChallenge(block2);
           waitForResponse();
           HumanSection.enableNeutral();

           if (lastAction == ActionName.Do_Nothing) {
               HumanSection.enableNeutral();

               blocker.section.controller = Controller.Neutral;
               lastAction = null;

               // ask bots to challenge the block
               Integer challenge2 = Game.botChallenges(block2);
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

                   if (blocker.getCard1() == Card.Ambassador || blocker.getCard2() == Card.Ambassador
                           || blocker.getCard1() == Card.Captain || blocker.getCard2() == Card.Captain) {
                       blocker.section.controller = Controller.Won_Challenge;

                       challenger.section.revealACard();
                       if (challenger.card1 == null && challenger.getCard2() == null) {
                           Game.players.remove(challenger.getNum());
                       }

                       // showing the bot's card and then replacing it
                       if (blocker.getCard1() == Card.Ambassador || blocker.getCard1() == Card.Captain) {
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
                           Game.players.remove(block2);
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


               if (blocker.getCard1() == Card.Ambassador || blocker.getCard2() == Card.Ambassador
                       || blocker.getCard1() == Card.Captain || blocker.getCard2() == Card.Captain) {
                   blocker.section.controller = Controller.Won_Challenge;

                   // todo the human chooses which card they wanna reveal
                   if (Human.card1 == null && Human.getCard2() == null) {
                       Game.players.remove(1);
                   }


                   // showing the bot's card and then replacing it
                   if (blocker.getCard1() == Card.Ambassador || blocker.getCard1() == Card.Captain) {
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
                       Game.players.remove(block2);
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
   }





   public static void pause(float seconds) throws InterruptedException {
       Thread.sleep((long) (seconds*1000));
   }







    @Override
    public void run() {
       while (running.get()) {
           switch (state) {
               case IsToPlay:
                 HumanSection.enableIsToPlay();
                 waitForResponse();

                   while (Human.lastAction == null) {
                       try {
                           wait();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
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

//                               Integer challenge = Game.botChallenges(1);
//
//                               if (challenge == 0) {
//                                   Action.tax(1);
//                                   Game.changeTurn();
//                               }
//                               else {
//                                   Bot challenger = Game.getBotByNum(challenge);
//                                   challenger.section.controller = Controller.Challenges;
//                                   HumanSection.enableIsToReactToChallenge();
//                                   waitForResponse();
//
//                                   lastAction = null;
//                                   HumanSection.enableNeutral();
//
//
//                                   if (card1 == Card.Duke || card2 == Card.Duke) {
//
//                                       // todo human duke should be replaced from deck
//
//                                       // show message "lost challenge"
//                                       challenger.section.controller = Controller.Lost_Challenge;
//                                       try {
//                                           Thread.sleep(4000);
//                                       } catch (InterruptedException e) {
//                                           e.printStackTrace();
//                                       }
//                                       // apply losing the challenge
//                                       challenger.section.controller = Controller.Neutral;
//                                       challenger.section.revealACard();
//                                       if (challenger.card1 == null && challenger.card2 == null) {
//                                           Game.players.remove(challenge);
//                                       }
//
//                                       // apply tax
//                                       Action.tax(1);
//
//                                       // change turn
//                                       Game.changeTurn();
//                                   }
//                                   else {
//                                       //  TODO win the challenge case
//                                       challenger.section.controller = Controller.Won_Challenge;
//                                       try {
//                                           Thread.sleep(4000);
//                                       } catch (InterruptedException e) {
//                                           e.printStackTrace();
//                                       }
//
//                                       challenger.section.controller = Controller.Neutral;
//                                       // todo ask the human which card they wanna reveal
//                                       if (card1 == null && card2 == null) {
//                                           Game.players.remove(1);
//                                       }
//
//                                       Game.changeTurn();
//                                   }
//                               }
                               try {
                                   Action.challengeSequenceForTax(1);
                               } catch (InterruptedException e) {
                                   e.printStackTrace();
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
                               Integer block = Game.botBlocks(1, 0, "foreign_aid");
                               if (block == 0) {
                                   Action.foreignAid(1);
                                   Game.changeTurn();
                               }
                               else {
                                   Bot blocker = Game.getBotByNum(block);
                                   blocker.section.controller = Controller.Blocks;

                                   HumanSection.enableIsAskedToChallenge(block);
                                   waitForResponse();
                                   HumanSection.enableNeutral();

                                   if (lastAction == ActionName.Do_Nothing) {
                                       HumanSection.enableNeutral();

                                       blocker.section.controller = Controller.Neutral;
                                       lastAction = null;

                                       // ask bots to challenge the block
                                       Integer challenge2 = Game.botChallenges(block);
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



                           case Income:
                               lastAction = null;
                               HumanSection.enableNeutral();
                               Action.income(1);
                               break;


                           case Exchange_Card1:
                               lastAction = null;
                               HumanSection.enableNeutral();
                               Action.exchangeOne(1, 1);
                               break;


                           case Exchange_Card2:
                               lastAction = null;
                               HumanSection.enableNeutral();
                               Action.exchangeOne(1, 2);
                               break;









                           case Exchange_Both_Cards:
                               lastAction = null;
                               HumanSection.enableNeutral();

                               // todo show a loading message or something
                               try {
                                   Thread.sleep(2500);
                               } catch (InterruptedException e) {
                                   e.printStackTrace();
                               }

                               Integer challenge4 = Game.botChallenges(1);

                               if (challenge4 == 0) {
                                   Action.exchangeTwo(1);
                                   Game.changeTurn();
                               }
                               else {
                                   Bot challenger = Game.getBotByNum(challenge4);
                                   challenger.section.controller = Controller.Challenges;
                                   HumanSection.enableIsToReactToChallenge();
                                   waitForResponse();

                                   lastAction = null;
                                   HumanSection.enableNeutral();


                                   if (card1 == Card.Ambassador || card2 == Card.Ambassador) {

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
                                           Game.players.remove(challenge4);
                                       }

                                       // apply tax
                                       Action.exchangeTwo(1);

                                       // change turn
                                       Game.changeTurn();
                                   }
                                   else {
                                       //  TODO win the challenge case
                                       challenger.section.controller = Controller.Won_Challenge;
                                       try {
                                           Thread.sleep(4000);
                                       } catch (InterruptedException e) {
                                           e.printStackTrace();
                                       }

                                       challenger.section.controller = Controller.Neutral;
                                       // todo ask the human which card they wanna reveal
                                       if (card1 == null && card2 == null) {
                                           Game.players.remove(1);
                                       }

                                       Game.changeTurn();
                                   }
                               }
                               break;









                           case Coup_On_2:
                               lastAction = null;
                               HumanSection.enableNeutral();
                               Action.coup(1, 2);
                               break;


                           case Coup_On_3:
                               lastAction = null;
                               HumanSection.enableNeutral();
                               Action.coup(1, 3);
                               break;


                           case Coup_On_4:
                               lastAction = null;
                               HumanSection.enableNeutral();
                               Action.coup(1, 4);
                               break;


                           case Steal_From_2:
                               applyCaseSteal(2);
                               break;
                           case Steal_From_3:
                               applyCaseSteal(3);
                               break;
                           case Steal_From_4:
                               applyCaseSteal(4);
                               break;


                           case Assassinate_2:
                               lastAction = null;
                               HumanSection.enableNeutral();
                               Action.assassinate(1, 2);
                               break;
                           case Assassinate_3:
                               lastAction = null;
                               HumanSection.enableNeutral();
                               Action.assassinate(1, 3);
                               break;
                           case Assassinate_4:
                               lastAction = null;
                               HumanSection.enableNeutral();
                               Action.assassinate(1, 4);
                               break;
                       }
           }
       }
    }
}