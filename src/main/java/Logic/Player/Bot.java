package Logic.Player;

import GUI.Card;
import GUI.BotSection;
import Logic.Game.Action;
import Logic.Game.ActionName;
import Logic.Game.Controller;
import Logic.Game.Game;

import java.util.concurrent.atomic.AtomicBoolean;

public class Bot extends Thread {
    public BotSection section;
    BotType role;
    Integer num;
    public int coins;
    Card card1;
    Card card2;
    public PlayerState state;
    AtomicBoolean running = new AtomicBoolean(true);
    ActionName lastAction = null;
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

    public int getNum() {
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

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void updateCoins(int n) {
        coins += n;
        section.updateCoins();
    }


    public boolean challenges() {
        if (role == BotType.Paranoid) {
            if (paranoidChallenge.get()) {
                paranoidChallenge.set(false);
                return false;
            }
            else {
                paranoidChallenge.set(true);
                return true;
            }
        }
        else {
            return !(Math.random() > 0.3);
        }
    }


//
//    public ActionName calculateAction() {
//
//    }




    public void playParanoid() throws InterruptedException {
        while (running.get()) {
            switch (state) {
                case IsToPlay:
                        section.controller = Controller.Is_Thinking;
                        Thread.sleep(4000);
                        Action.income(num);
                        section.controller = Controller.Neutral;
                        state = PlayerState.Neutral;
                    break;
                case IsToReactToChallenge:

                    break;
                case IsAskedToBlock:

                    break;
                case IsAskedToChallenge:

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
                        Thread.sleep(4000);
                        Action.income(num);
                        section.controller = Controller.Neutral;
                        state = PlayerState.Neutral;
                    break;
                case IsToReactToChallenge:

                    break;
                case IsAskedToBlock:

                    break;
                case IsAskedToChallenge:

                    break;
            }
        }
    }

    public void playCoupLover() throws InterruptedException {
        while (running.get()) {
            switch (state) {
                case IsToPlay:
                        section.controller = Controller.Is_Thinking;
                        Thread.sleep(4000);
                        Action.income(num);
                        section.controller = Controller.Neutral;
                        state = PlayerState.Neutral;

                    break;
                case IsToReactToChallenge:

                    break;
                case IsAskedToBlock:

                    break;
                case IsAskedToChallenge:

                    break;
            }
        }
    }

    public void playNerd() throws InterruptedException {
        while (running.get()) {
            switch (state) {
                case IsToPlay:
                        section.controller = Controller.Is_Thinking;
                        Thread.sleep(4000);
                        Action.income(num);
                        section.controller = Controller.Neutral;
                        state = PlayerState.Neutral;
                    break;
                case IsToReactToChallenge:

                    break;
                case IsAskedToBlock:

                    break;
                case IsAskedToChallenge:

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
