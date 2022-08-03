package Logic.Player;

import GUI.Card;
import GUI.BotSection;
import Logic.Game.Action;

public class Bot extends Thread {
    BotSection section;
    BotType role;
    int num;
    int coins;
    Card card1;
    Card card2;
    PlayerState state;

    public Bot(int num, Card card1, Card card2, BotType role) {
        this.num = num;
        this.card1 = card1;
        this.card2 = card2;
        this.role = role;
        this.coins = 2;
        this.state = PlayerState.IsToPlay;
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

    public void updateCoins(int n) {
        coins += n;
        section.updateCoins();
    }


    public void playParanoid() throws InterruptedException {
        while (true) {
            switch (state) {
                case IsToPlay:
                    Thread.sleep(7000);
                    Action.income(num);
                    break;
                case IsToReactToChallenge:

                    break;
                case IsAskedToBlock:

                    break;
                case IsAskedToChallenged:

                    break;
            }
        }
    }

    public void playCautiousAssassin() {
        while (true) {

        }
    }

    public void playCoupLover() {
        while (true) {

        }
    }

    public void playNerd() {
        while (true) {

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
                playCautiousAssassin();
                break;
            case Coup_Lover:
                playCoupLover();
                break;
            case Nerd:
                playNerd();
                break;
        }
    }
}
