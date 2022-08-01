package Logic;
import GUI.Card;
import GUI.HumanSection;

public class Human extends Thread{
   public static int num = 2;
   public static Card card1, card2;

   public Human(Card c1, Card c2) {
       card1 = c1;
       card2 = c2;
   }

   public static void updateNum(int n) {
       num += n;
       HumanSection.updateNum();
   }


    @Override
    public void run() {

    }
}