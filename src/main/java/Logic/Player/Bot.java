package Logic.Player;

import GUI.Card;
import GUI.BotSection;
import Logic.Game.Action;
import Logic.Game.Controller;
import Logic.Game.Game;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Bot extends Thread {
    public BotSection section;
    BotType role;
    Integer num;
    public int coins;
    public Card card1;
    public Card card2;
    public PlayerState state;
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
        while (Game.gameIsGoing.get()) {
            if (state == PlayerState.IsToPlay && Game.players.size() != 1) {
                if (Game.gameIsGoing.get()) {
                    section.controller = Controller.Is_Thinking;
                    Thread.sleep(2000);
                    section.controller = Controller.Neutral;
                    state = PlayerState.Neutral;
                    Action.blockSequenceForSteal(getNum(), 1);
                }
            }
        }
    }

    public void playCautiousAssassin() throws InterruptedException {
        while (Game.gameIsGoing.get()) {
            if (state == PlayerState.IsToPlay && Game.players.size() != 1) {
                section.controller = Controller.Is_Thinking;
                Thread.sleep(2000);
                section.controller = Controller.Neutral;
                if (card1 == Card.Assassin || card2 == Card.Assassin) {
                    if (coins > 2) {
                        int rnd = new Random().nextInt(Game.players.size());
                        if (Game.players.get(rnd).equals(getNum())) {
                            if (rnd == 0) rnd = 1;
                            else rnd = 0;
                        }
                        Action.blockSequenceForAssassinate(getNum(), Game.players.get(rnd));
                    }
                    else {
                        section.controller = Controller.Income;
                        Thread.sleep(2000);
                        section.controller = Controller.Neutral;
                        Action.income(getNum());
                    }
                }
                else {
                    if (card1 == Card.Ambassador || card2 == Card.Ambassador) Action.challengeSequenceForExchange(getNum());
                    else {
                        if (coins > 0) {
                            if (card1 != null) {
                                section.controller = Controller.Exchange_One_Card;
                                Thread.sleep(2000);
                                section.controller = Controller.Neutral;
                                Action.exchangeOne(getNum(), 1);
                            }
                            else {
                                section.controller = Controller.Exchange_One_Card;
                                Thread.sleep(2000);
                                section.controller = Controller.Neutral;
                                Action.exchangeOne(getNum(), 2);
                            }
                        }
                        else {
                            Action.blockSequenceForForeignAid(getNum());
                        }
                    }
                }
                state = PlayerState.Neutral;
            }
        }
    }

    public void playCoupLover() throws InterruptedException {
        while (Game.gameIsGoing.get()) {
            if (state == PlayerState.IsToPlay && Game.players.size() != 1) {
                section.controller = Controller.Is_Thinking;
                Thread.sleep(2000);
                section.controller = Controller.Neutral;
                state = PlayerState.Neutral;
                    if (coins < 7) {
                        Action.challengeSequenceForTax(getNum());
                    }
                    else {
                        int rnd = new Random().nextInt(Game.players.size());
                        if (Game.players.get(rnd).equals(getNum())) {
                            if (rnd == 0) rnd = 1;
                            else rnd = 0;
                        }
                        switch (Game.players.get(rnd)) {
                            case 1:
                                section.controller = Controller.Launches_Coup_Against_1;
                                break;
                            case 2:
                                section.controller = Controller.Launches_Coup_Against_2;
                                break;
                            case 3:
                                section.controller = Controller.Launches_Coup_Against_3;
                                break;
                            case 4:
                                section.controller = Controller.Launches_Coup_Against_4;
                                break;
                        }
                        Thread.sleep(2000);
                        section.controller = Controller.Neutral;
                        Action.coup(getNum(), Game.players.get(rnd));
                    }
            }
        }
    }

    public void playNerd() throws InterruptedException {
        while (Game.gameIsGoing.get()) {
            if (state == PlayerState.IsToPlay && Game.players.size() != 1) {
                section.controller = Controller.Is_Thinking;
                Thread.sleep(2000);
                Action.income(num);
                section.controller = Controller.Neutral;
                state = PlayerState.Neutral;
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