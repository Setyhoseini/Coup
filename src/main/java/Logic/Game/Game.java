package Logic.Game;

import GUI.Card;
import GUI.HumanSection;
import Logic.Player.Bot;
import Logic.Player.BotType;
import Logic.Player.Human;
import Logic.Player.PlayerState;

import java.util.Collections;
import java.util.Objects;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Game extends Thread{
    public static Vector<Bot> bots = new Vector<>();
    public static Vector<Integer> players = new Vector<>();
    public static AtomicInteger turn = new AtomicInteger();
    public static AtomicBoolean gameIsGoing = new AtomicBoolean(true);

    public Game(Vector<Bot> bots) {
        Game.bots = bots;
        players.add(1);
        players.add(2);
        players.add(3);
        players.add(4);
        turn.set(1);
        Human.state = PlayerState.IsToPlay;
        HumanSection.enableIsToPlay();
    }

    public static void changeTurn() {
       // if (gameIsGoing.get()) {
            int handle = turn.get();
            if (turn.get() == 1) {
                Human.state = PlayerState.Neutral;
            }
            else {
                for (Bot b : bots) {
                    if (turn.get() == b.getNum()) {
                        b.state = PlayerState.Neutral;
                        break;
                    }
                }
            }
            if (!players.contains(turn.get())) {
                int temp = turn.get();
                for (int n : players) {
                    if (n > turn.get()) {
                        turn.set(n);
                        break;
                    }
                }
                if (temp == turn.get()) {
                    turn.set(players.get(0));
                }
                if (turn.get() == 1) {
                    if (Human.coins >= 10) {
                        HumanSection.enableMustCoup();
                    }
                    else HumanSection.enableIsToPlay();
                    Human.state = PlayerState.IsToPlay;
                }
                else {
                    for (Bot b : bots) {
                        if (Objects.equals(b.getNum(), turn.get())) {
                            b.state = PlayerState.IsToPlay;
                            break;
                        }
                    }
                }
            }
            else {
                int newTurn = players.indexOf(turn.get()) + 1;
                if (newTurn == players.size()) newTurn = 0;
                if (players.get(newTurn) == 1) {
                    if (Human.coins >= 10) {
                        HumanSection.enableMustCoup();
                    }
                    else HumanSection.enableIsToPlay();
                    Human.state = PlayerState.IsToPlay;
                }
                else {
                    for (Bot b : bots) {
                        if (Objects.equals(b.getNum(), players.get(newTurn))) {
                            b.state = PlayerState.IsToPlay;
                            break;
                        }
                    }
                }
                turn.set(players.get(newTurn));
            }
            if (!players.contains(turn.get())) {
                for (int n : players) {
                    if (n > handle) {
                        turn.set(n);
                        break;
                    }
                }
                if (turn.get() == 1) {
                    if (Human.coins >= 10) {
                        HumanSection.enableMustCoup();
                    }
                    else HumanSection.enableIsToPlay();
                    Human.state = PlayerState.IsToPlay;
                }
                else {
                    for (Bot b : bots) {
                        if (Objects.equals(b.getNum(), turn.get())) {
                            b.state = PlayerState.IsToPlay;
                            break;
                        }
                    }
                }
            }
    }

    public static Integer botChallenges(int who) {
        int x = 0;
        if (Bot.paranoidIsPlaying.get()) {
            if (!Bot.paranoidChallenge.get()) {
                for (Bot b : bots) {
                    if (b.getRole() == BotType.Paranoid && who != b.getNum() && players.contains(b.getNum())) {
                        x = b.getNum();
                        break;
                    }
                }
                Bot.paranoidChallenge.set(true);
            }
            else {
                Bot.paranoidChallenge.set(false);
                Vector<Integer> list = new Vector<>();
                for (Bot b : bots) {
                    if (b.getRole() != BotType.Paranoid && who != b.getNum() && players.contains(b.getNum())) {
                        if ((Math.random() < 0.05)) list.add(b.getNum());
                    }
                }
                Collections.shuffle(list);
                if (list.size() != 0) x = list.get(0);
            }
        }
        else {
            Vector<Integer> list = new Vector<>();
            for (Bot b : bots) {
                if (who != b.getNum() && players.contains(b.getNum())) {
                    if ((Math.random() < 0.05)) list.add(b.getNum());
                }
            }
            Collections.shuffle(list);
            if (list.size() != 0) x = list.get(0);
        }
        return x;
    }

    public static Integer botBlocks(int who, int on, String action) {
        int x = 0;
            Vector<Integer> list1 = new Vector<>();
            Vector<Integer> list2 = new Vector<>();
            Bot bot;
                switch (action) {
                    case "foreign_aid":
                        for (Bot b : bots) {

                            if (b.getNum() != who) {
                                if (b.getCard1() == Card.Duke || b.getCard2() == Card.Duke) {
                                    list1.add(b.getNum());
                                } else {
                                    switch (b.getRole()) {
                                        case Paranoid:
                                            if (Math.random() < 0.4 && players.contains(b.getNum())) list2.add(b.getNum());
                                            break;
                                        case Coup_Lover:
                                            if (Math.random() < 0.7 && players.contains(b.getNum())) list2.add(b.getNum());
                                            break;
                                        case Nerd:
                                            if (Math.random() < 0.13 && players.contains(b.getNum())) list2.add(b.getNum());
                                            break;
                                    }
                                }
                            }

                        }
                        if (list1.size() != 0) {
                            Collections.shuffle(list1);
                            x = list1.get(0);
                        }
                        else if (list2.size() != 0) {
                            Collections.shuffle(list2);
                            x = list2.get(0);
                        }
                        break;
                    case "assassinate":
                        bot = getBotByNum(on);
                        if (bot.getCard1() == Card.Contessa || bot.getCard2() == Card.Contessa) {
                            x = bot.getNum();
                        }
                        else {
                            switch (bot.getRole()) {
                                case Paranoid:
                                  if (Math.random() < 0.1) list2.add(bot.getNum());
                                  break;
                                case Coup_Lover:
                                    if (Math.random() < 0.50) list2.add(bot.getNum());
                                    break;
                                case Nerd:
                                    if (Math.random() < 0.02) list2.add(bot.getNum());
                                    break;
                            }
                        }
                        break;
                    case "steal":
                        bot = getBotByNum(on);
                        if (bot.getCard1() == Card.Ambassador || bot.getCard2() == Card.Ambassador
                             || bot.getCard1() == Card.Captain || bot.getCard2() == Card.Captain) {
                            x = bot.getNum();
                        }
                        else {
                            switch (bot.getRole()) {
                                case Paranoid:
                                    if (Math.random() < 0.05) list2.add(bot.getNum());
                                    break;
                                case Coup_Lover:
                                    if (Math.random() < 0.20) list2.add(bot.getNum());
                                    break;
                                case Nerd:
                                    if (Math.random() < 0.06) list2.add(bot.getNum());
                                    break;
                            }
                        }
                        break;
                }
                return x;
        }

    public static Bot getBotByNum(int num) {
        Bot bot = null;
        for (Bot b : bots) {
            if (b.getNum() == num) {
                bot = b;
                break;
            }
        }
        return bot;
    }

    @Override
    public void run() {
         while (gameIsGoing.get()) {
             // if (players.size == 1) --> todo
             if (players.size() == 1) {
                 gameIsGoing.set(false);
                 for (Bot b : bots) b.state = PlayerState.Neutral;
             }
         }
    }
}