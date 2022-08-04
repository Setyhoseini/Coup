package Logic.Player;
import GUI.Card;
import GUI.HumanSection;
import Logic.Game.Game;

import java.util.concurrent.atomic.AtomicBoolean;


public class Human extends Thread {
    public static int coins;
    int num;
    public static Card card1;
    public static Card card2;
    public static PlayerState state;
    AtomicBoolean running = new AtomicBoolean(true);


   public Human(Card card1, Card card2) {
       Human.card1 = card1;
       Human.card2 = card2;
       num = 1;
       coins = 2;
       state = PlayerState.IsToPlay;
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
                   try {
                       Thread.sleep(10000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   state = PlayerState.Neutral;
                   Game.getBotByNum(2).state = PlayerState.IsToPlay;
           }
       }
    }
}