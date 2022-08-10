package Logic.Player;

import GUI.Card;
import GUI.BotSection;
import Logic.Game.Action;
import Logic.Game.Controller;

import java.util.concurrent.atomic.AtomicBoolean;

public class Bot extends Thread {
    public BotSection section;
    BotType role;
    Integer num;
    public int coins;
    public Card card1;
    public Card card2;
    public PlayerState state;
    AtomicBoolean running = new AtomicBoolean(true);
    public static AtomicBoolean paranoidChallenge = new AtomicBoolean(false);
    public static AtomicBoolean paranoidIsPlaying = new AtomicBoolean(false);

    public Bot(int num, Card card1, Card card2, BotType role) {
        this.num = num;
        this.card1 = card1;
        this.card2 = card2;
        this.role = role;
        this.coins = 2;
        this.state = PlayerState.Neutral;
        if (role == BotType.Paranoid) paranoidIsPlaying.set(true);
    }

    public void setSection(BotSection section) {
        this.section = section;
    }

    public Card getCard1() {
        return card1;
    }

    public Card getCard2() {
        return card2;
    }

    public BotType getRole() {
        return role;
    }

    public Integer getNum() {
        return num;
    }

    public int getCoins() {
        return coins;
    }

    public void setCard1(Card card1) {
        this.card1 = card1;
    }

    public void setCard2(Card card2) {
        this.card2 = card2;
    }

    public void updateCoins(int n) {
        coins += n;
        section.updateCoins();
    }

    public void playParanoid() throws InterruptedException {
        while (running.get()) {
            switch (state) {
                case IsToPlay:
                        section.controller = Controller.Is_Thinking;
                        Thread.sleep(2000);
                        section.controller = Controller.Neutral;
                        Action.blockSequenceForSteal(getNum(), 1);
                        state = PlayerState.Neutral;
                    break;

                case Neutral:

                    break;
            }
        }
    }

    public void playCautiousAssassin() throws InterruptedException {
        while (running.get()) {
            switch (state) {
                case IsToPlay:
                        section.controller = Controller.Is_Thinking;
                        Thread.sleep(2000);
                        section.controller = Controller.Neutral;
//                        if (card1 == Card.Ambassador || card2 == Card.Ambassador) Action.challengeSequenceForExchange(getNum());
//                        else
                    //    Action.income(num);

                        Action.blockSequenceForAssassinate(getNum(), 1);
                        state = PlayerState.Neutral;
                    break;
            }
        }
    }

    public void playCoupLover() throws InterruptedException {
        while (running.get()) {
            switch (state) {
                case IsToPlay:
                        section.controller = Controller.Is_Thinking;
                        Thread.sleep(2000);
                        Action.income(num);
                        section.controller = Controller.Neutral;
                       // Action.blockSequenceForSteal(getNum(), 1);
                        state = PlayerState.Neutral;
                    break;
            }
        }
    }

    public void playNerd() throws InterruptedException {
        while (running.get()) {
            switch (state) {
                case IsToPlay:
                        section.controller = Controller.Is_Thinking;
                        Thread.sleep(2000);
                        Action.income(num);
                        section.controller = Controller.Neutral;
                        state = PlayerState.Neutral;
                    break;
            }
        }
    }

    @Override
    public void run() {
        switch (role) {
            case Paranoid:
                try {
                    playParanoid();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case Cautious_Assassin:
                try {
                    playCautiousAssassin();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case Coup_Lover:
                try {
                    playCoupLover();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case Nerd:
                try {
                    playNerd();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}