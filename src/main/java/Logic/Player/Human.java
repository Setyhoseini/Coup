package Logic.Player;
import GUI.Card;
import GUI.HumanSection;
import Logic.Game.Action;
import Logic.Game.ActionName;

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

    public static Card getCard1() {
        return card1;
    }

    public static Card getCard2() {
        return card2;
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
       while (running.get() && lastAction == null) {}
   }

   public static void pause(float seconds) throws InterruptedException {
       Thread.sleep((long) (seconds*1000));
   }

    @Override
    public void run() {
       while (running.get()) {
           if (state == PlayerState.IsToPlay) {
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
                       try {
                           pause(2);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
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
                           pause(1.5F);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       try {
                           Action.blockSequenceForForeignAid(1);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
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
                       try {
                           pause(2);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       try {
                           Action.challengeSequenceForExchange(1);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
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
                       lastAction = null;
                       try {
                           pause(1.5F);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       HumanSection.enableNeutral();
                       try {
                           Action.blockSequenceForSteal(1, 2);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       break;

                   case Steal_From_3:
                       lastAction = null;
                       try {
                           pause(1.5F);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       HumanSection.enableNeutral();
                       try {
                           Action.blockSequenceForSteal(1, 3);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       break;

                   case Steal_From_4:
                       lastAction = null;
                       try {
                           pause(1.5F);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       HumanSection.enableNeutral();
                       try {
                           Action.blockSequenceForSteal(1, 4);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       break;


                   case Assassinate_2:
                       lastAction = null;
                       try {
                           pause(1.5F);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       HumanSection.enableNeutral();
                       try {
                           Action.blockSequenceForAssassinate(1, 2);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       break;
                   case Assassinate_3:
                       lastAction = null;
                       HumanSection.enableNeutral();
                       try {
                           pause(1.5F);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       try {
                           Action.blockSequenceForAssassinate(1, 3);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       break;
                   case Assassinate_4:
                       lastAction = null;
                       HumanSection.enableNeutral();
                       try {
                           pause(1.5F);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       try {
                           Action.blockSequenceForAssassinate(1, 4);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       break;
               }
           }
       }
    }
}