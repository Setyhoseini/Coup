package Logic.Player;

import GUI.Card;
import GUI.BotSection;
import Logic.Game.Action;
import Logic.Game.Controller;
import Logic.Game.Game;

import java.util.Collections;
import java.util.Random;
import java.util.Vector;
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

    public void coupDecision() throws InterruptedException {
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

    public void assassinateDecision() throws InterruptedException {
        int rnd = new Random().nextInt(Game.players.size());
        if (Game.players.get(rnd).equals(getNum())) {
            if (rnd == 0) rnd = 1;
            else rnd = 0;
        }
        Action.blockSequenceForAssassinate(getNum(), Game.players.get(rnd));
    }

    public void incomeDecision() throws InterruptedException {
        section.controller = Controller.Income;
        Thread.sleep(2000);
        section.controller = Controller.Neutral;
        section.controller = Controller.Neutral;
        Action.income(getNum());
    }

    public void exchangeOneDecision() throws InterruptedException {
        if (card1 != null) {
            section.controller = Controller.Exchange_One_Card;
            Thread.sleep(2000);
            section.controller = Controller.Neutral;
            section.controller = Controller.Neutral;
            Action.exchangeOne(getNum(), 1);
        }
        else {
            section.controller = Controller.Exchange_One_Card;
            Thread.sleep(2000);
            section.controller = Controller.Neutral;
            section.controller = Controller.Neutral;
            Action.exchangeOne(getNum(), 2);
        }
    }

    public Vector<Integer> stealWithAtLeastTwoCoins() {
        Vector<Integer> list = new Vector<>();
        for (int n : Game.players) {
            if (n == 1) {
                if (Human.coins > 1) list.add(1);
            }
            else {
                if (Game.getBotByNum(n).coins > 1 && n != num) list.add(Game.getBotByNum(n).getNum());
            }
        }
        Collections.shuffle(list);
        return list;
    }

    public Vector<Integer> stealWithAtLeastOneCoin() {
        Vector<Integer> list = new Vector<>();
        for (int n : Game.players) {
            if (n == 1) {
                if (Human.coins > 0) list.add(1);
            }
            else {
                if (Game.getBotByNum(n).coins > 1 && n != num) list.add(Game.getBotByNum(n).getNum());
            }
        }
        Collections.shuffle(list);
        return list;
    }

    public void playParanoid() throws InterruptedException {
        while (Game.gameIsGoing.get()) {
            if (state == PlayerState.IsToPlay && Game.players.size() != 1) {
                    section.controller = Controller.Is_Thinking;
                    Thread.sleep(2000);
                    section.controller = Controller.Neutral;
                    section.controller = Controller.Neutral;
                    state = PlayerState.Neutral;
                    if (coins >= 10) {
                        coupDecision();
                    }
                    else {
                        if (card1 == Card.Captain || card2 == Card.Captain) {
                            Vector<Integer> list = stealWithAtLeastTwoCoins();
                            if (list.size() != 0) {
                                Collections.shuffle(list);
                                Action.blockSequenceForSteal(getNum(), list.get(0));
                            }
                            else {
                                double random = Math.random();
                                if (random > 0.6) Action.blockSequenceForForeignAid(getNum());
                                else if (random < 0.6 && random > 0.3) Action.challengeSequenceForTax(getNum());
                                else incomeDecision();
                            }
                        }
                        else {
                            if (card1 == Card.Assassin || card2 == Card.Assassin) {
                                if (coins > 2) {
                                    assassinateDecision();
                                }
                                else {
                                    incomeDecision();
                                }
                            }
                            else {
                                double random = Math.random();
                                if (card1 == Card.Ambassador || card2 == Card.Ambassador) {
                                    if (random > 0.2) Action.challengeSequenceForExchange(getNum());
                                    else {
                                        if (coins > 2) {
                                            assassinateDecision();
                                        }
                                        else {
                                            incomeDecision();
                                        }
                                    }
                                }
                                else {
                                    if (random > 0.2) {
                                        if (coins > 0) {
                                            exchangeOneDecision();
                                        }
                                        else {
                                            incomeDecision();
                                        }
                                    }
                                    else {
                                        Vector<Integer> list = new Vector<>();
                                        for (int n : Game.players) {
                                            if (n == 1) {
                                                if (Human.coins > 1) list.add(1);
                                            }
                                            else {
                                                if (Game.getBotByNum(n).coins > 1) list.add(Game.getBotByNum(n).getNum());
                                            }
                                        }
                                        if (list.size() != 0) {
                                            Collections.shuffle(list);
                                            Action.blockSequenceForSteal(getNum(), list.get(0));
                                        }
                                        else {
                                            Action.blockSequenceForForeignAid(getNum());
                                        }
                                    }
                                }
                            }
                        }
                    }
                    state = PlayerState.Neutral;
            }
        }
    }

    public void playCautiousAssassin() throws InterruptedException {
        while (Game.gameIsGoing.get()) {
            if (state == PlayerState.IsToPlay && Game.players.size() != 1) {
                section.controller = Controller.Is_Thinking;
                Thread.sleep(2000);
                section.controller = Controller.Neutral;
                section.controller = Controller.Neutral;
                state = PlayerState.Neutral;
                if (coins >= 10) {
                    coupDecision();
                }
                else {
                    if (card1 == Card.Assassin || card2 == Card.Assassin) {
                        if (coins > 2) {
                            assassinateDecision();
                        }
                        else {
                            if (Math.random() > 0.4) {
                                incomeDecision();
                            }
                            else {
                                Action.blockSequenceForForeignAid(getNum());
                            }
                        }
                    }
                    else {
                        if (card1 == Card.Ambassador || card2 == Card.Ambassador) Action.challengeSequenceForExchange(getNum());
                        else {
                            if (coins > 0) {
                                exchangeOneDecision();
                            }
                            else {
                                Action.blockSequenceForForeignAid(getNum());
                            }
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
                section.controller = Controller.Neutral;
                state = PlayerState.Neutral;
                    if (coins < 7) {
                        Action.challengeSequenceForTax(getNum());
                    }
                    else {
                        coupDecision();
                    }
                    state = PlayerState.Neutral;
            }
        }
    }

    public void playNerd() throws InterruptedException {
        while (Game.gameIsGoing.get()) {
            if (state == PlayerState.IsToPlay && Game.players.size() != 1) {
                section.controller = Controller.Is_Thinking;
                Thread.sleep(2000);
                section.controller = Controller.Neutral;
                section.controller = Controller.Neutral;
                state = PlayerState.Neutral;
                if (coins >= 10) {
                    coupDecision();
                }
                else {
                    if ((card1 == Card.Assassin && card2 == Card.Captain) || (card2 == Card.Assassin && card1 == Card.Captain)) {
                        if (coins > 4) {
                            if (Math.random() > 0.45) assassinateDecision();
                            else Action.blockSequenceForForeignAid(getNum());
                        }
                        else {
                            Vector<Integer> list = stealWithAtLeastTwoCoins();
                            if (list.size() == 0) {
                                list = stealWithAtLeastOneCoin();
                                if (list.size() == 0) Action.exchangeTwo(getNum());
                                else Action.blockSequenceForSteal(getNum(), list.get(0));
                            }
                            else Action.blockSequenceForSteal(getNum(), list.get(0));
                        }
                    }
                    if ((card1 == Card.Assassin && card2 == Card.Contessa) || (card2 == Card.Assassin && card1 == Card.Contessa)) {
                        if (coins < 4) {
                            if (coins > 0) exchangeOneDecision();
                            else incomeDecision();
                        }
                        else {
                            if (Math.random() > 0.25) assassinateDecision();
                            else Action.blockSequenceForForeignAid(getNum());
                        }
                    }
                    if ((card1 == Card.Assassin && card2 == Card.Ambassador) || (card2 == Card.Assassin && card1 == Card.Ambassador)) {
                        if (Game.players.size() == 2) {
                            if (coins > 2) assassinateDecision();
                            else Action.challengeSequenceForExchange(getNum());
                        }
                        else {
                            if (coins > 4) assassinateDecision();
                            else Action.challengeSequenceForTax(getNum());
                        }
                    }
                    if ((card1 == Card.Assassin && card2 == Card.Duke) || (card2 == Card.Assassin && card1 == Card.Duke)) {
                        if (Game.players.size() == 2) {
                            if (coins > 2) assassinateDecision();
                            else Action.challengeSequenceForTax(getNum());
                        }
                        else {
                            double random = Math.random();
                            if (random > 0.8) {
                                Vector<Integer> list = stealWithAtLeastTwoCoins();
                                if (list.size() == 0) {
                                    list = stealWithAtLeastOneCoin();
                                    if (list.size() == 0) Action.exchangeTwo(getNum());
                                    else incomeDecision();
                                }
                                else Action.blockSequenceForSteal(getNum(), list.get(0));
                            }
                            else if (random < 0.8 && random > 0.6) {
                                if (coins > 2) assassinateDecision();
                                else incomeDecision();
                            }
                            else if (random < 0.6 && random > 0.3) Action.blockSequenceForForeignAid(getNum());
                            else incomeDecision();
                        }
                    }
                    if ((card1 == Card.Contessa && card2 == Card.Captain) || (card2 == Card.Contessa && card1 == Card.Captain)) {
                        if (coins > 3) Action.blockSequenceForForeignAid(getNum());
                        else {
                            Vector<Integer> list = stealWithAtLeastTwoCoins();
                            if (list.size() == 0) {
                                list = stealWithAtLeastOneCoin();
                                if (list.size() == 0) Action.exchangeTwo(getNum());
                                else {
                                    if (Math.random() > 0.5) Action.challengeSequenceForExchange(getNum());
                                    else incomeDecision();
                                }
                            }
                            else Action.blockSequenceForSteal(getNum(), list.get(0));
                        }
                    }
                    if ((card1 == Card.Ambassador && card2 == Card.Captain) || (card2 == Card.Ambassador && card1 == Card.Captain)) {
                        Vector<Integer> list = stealWithAtLeastTwoCoins();
                        if (list.size() == 0) {
                            list = stealWithAtLeastOneCoin();
                            if (list.size() == 0) Action.exchangeTwo(getNum());
                            else Action.challengeSequenceForExchange(getNum());
                        }
                        else Action.blockSequenceForSteal(getNum(), list.get(0));
                    }
                    if ((card1 == Card.Duke && card2 == Card.Captain) || (card2 == Card.Duke && card1 == Card.Captain)) {
                        if (Math.random() > 0.1) Action.challengeSequenceForTax(getNum());
                        else incomeDecision();
                    }
                    if ((card1 == Card.Ambassador && card2 == Card.Contessa) || (card2 == Card.Ambassador && card1 == Card.Contessa)) {
                        Action.challengeSequenceForExchange(getNum());
                    }
                    if ((card1 == Card.Duke && card2 == Card.Contessa) || (card2 == Card.Duke && card1 == Card.Contessa)) {
                        if (Math.random() > 0.3) Action.challengeSequenceForTax(getNum());
                        else {
                            if (coins > 2) assassinateDecision();
                            else Action.challengeSequenceForTax(getNum());
                        }
                    }
                    if ((card1 == Card.Ambassador && card2 == Card.Duke) || (card2 == Card.Ambassador && card1 == Card.Duke)) {
                        double random = Math.random();
                        if (random > 0.4) Action.blockSequenceForForeignAid(getNum());
                        else if (random < 0.4 && random > 0.2) Action.challengeSequenceForTax(getNum());
                        else if (coins > 0) exchangeOneDecision();
                        else incomeDecision();
                    }
                    if ((card1 == Card.Assassin && card2 == null) ||
                            (card1 == Card.Assassin && card2 == Card.Assassin) ||
                            (card2 == Card.Assassin && card1 == null)) {
                        if (coins > 2) assassinateDecision();
                        else if (Math.random() > 0.5) Action.blockSequenceForForeignAid(getNum());
                        else Action.challengeSequenceForTax(getNum());
                    }
                    if ((card1 == Card.Captain && card2 == null) ||
                            (card1 == Card.Captain && card2 == Card.Captain) ||
                            (card2 == Card.Captain && card1 == null)) {
                        Vector<Integer> list = stealWithAtLeastTwoCoins();
                        if (list.size() == 0) {
                            list = stealWithAtLeastOneCoin();
                            if (list.size() == 0) Action.exchangeTwo(getNum());
                            else if (Math.random() > 0.5) Action.challengeSequenceForExchange(getNum());
                            else incomeDecision();
                        }
                        else Action.blockSequenceForSteal(getNum(), list.get(0));
                    }
                    if ((card1 == Card.Contessa && card2 == null) ||
                            (card1 == Card.Contessa && card2 == Card.Contessa) ||
                            (card2 == Card.Contessa && card1 == null)) {
                        double random = Math.random();
                        if (random > 0.5) Action.challengeSequenceForExchange(getNum());
                        else if (random < 0.5 && random > 0.25) Action.blockSequenceForForeignAid(getNum());
                        else incomeDecision();
                    }
                    if ((card1 == Card.Ambassador && card2 == null) ||
                            (card1 == Card.Ambassador && card2 == Card.Ambassador) ||
                            (card2 == Card.Ambassador && card1 == null)) {
                        Action.challengeSequenceForExchange(getNum());
                    }
                    if ((card1 == Card.Duke && card2 == null) ||
                            (card1 == Card.Duke && card2 == Card.Duke) ||
                            (card2 == Card.Duke && card1 == null)) {
                        Action.challengeSequenceForTax(getNum());
                    }
                }
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