package Logic.Player;
import GUI.Card;
import GUI.HumanSection;


public class Human extends Thread {
    public static int coins;
    int id;
    public static Card card1;
    public static Card card2;


   public Human(Card card1, Card card2) {
       Human.card1 = card1;
       Human.card2 = card2;
       id = 1;
       coins = 2;
   }

   public static void updateNum(int n) {
       coins += n;
       HumanSection.updateNum();
   }


    @Override
    public void run() {

    }
}