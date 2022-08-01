package Logic;

import GUI.Card;
import GUI.HumanSection;
import GUI.RobotSection;

import java.util.concurrent.atomic.AtomicBoolean;

public class Bot extends Thread{
    public int num = 2;
    public AtomicBoolean isToMove = new AtomicBoolean();
    public AtomicBoolean isChallenged = new AtomicBoolean();
    Card card1, card2;
    public int ID;
    String type;
    RobotSection section;

    public Bot(int ID, String type, Card card1, Card card2) {
        this.ID = ID;
        isToMove.set(false);
        isChallenged.set(false);
        this.type = type;
        this.card1 = card1;
        this.card2 = card2;
    }

    public void setSection(RobotSection section) {
        this.section = section;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Card getCard1() {
        return card1;
    }

    public Card getCard2() {
        return card2;
    }

    public void setCard1(Card card1) {
        this.card1 = card1;
    }

    public void setCard2(Card card2) {
        this.card2 = card2;
    }

    public void updateNum(int n) {
        setNum(getNum() + n);
        section.updateNum();
    }




    @Override
    public void run() {

    }
}
