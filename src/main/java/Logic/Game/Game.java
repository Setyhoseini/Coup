package Logic.Game;

import GUI.Card;
import GUI.Message;
import Logic.Player.Bot;
import Logic.Player.BotType;
import Logic.Player.Human;
import Logic.Player.PlayerState;

import java.util.Collections;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;

public class Game extends Thread{
    public static Vector<Bot> bots = new Vector<>();
    public static Vector<Card> deck = new Vector<>();
    public static Vector<Integer> players = new Vector<>();
    public static ActionName lastAction = null;
    public static Integer turn = 1;
    public static AtomicBoolean gameIsGoing = new AtomicBoolean(true);

    public Game(Vector<Bot> bots) {
        Game.bots = bots;
        players.add(1);
        players.add(2);
        players.add(3);
        players.add(4);
    }


    public static void changeTurn() {
        int newTurn = players.indexOf(turn) + 1;
        if (newTurn == players.size()) newTurn = 0;
        if (players.get(newTurn) == 1) {
            Human.state = PlayerState.IsToPlay;
        }
        else {
            for (Bot b : bots) {
                if (b.getNum() == players.get(newTurn)) {
                    b.state = PlayerState.IsToPlay;
                    break;
                }
            }
        }
    }


    public static int botChallenges() {
        int x = 0;
        if (Bot.paranoidIsPlaying.get()) {
            if (!Bot.paranoidChallenge.get()) {
                for (Bot b : bots) {
                    if (b.getRole() == BotType.Paranoid) {
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
                    if (b.getRole() != BotType.Paranoid) {
                        if (!(Math.random() > 0.3)) list.add(b.getNum());
                    }
                }
                Collections.shuffle(list);
                x = list.get(0);
            }
        }
        return x;
    }


    public static int botBlocks(int who, int on, String action) {
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
                                            if (Math.random() < 0.4) list2.add(b.getNum());
                                            break;
                                        case Coup_Lover:
                                            if (Math.random() < 0.35) list2.add(b.getNum());
                                            break;
                                        case Cautious_Assassin:
                                            if (Math.random() < 0.07) list2.add(b.getNum());
                                            break;
                                        case Nerd:
                                            if (Math.random() < 0.13) list2.add(b.getNum());
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
                                case Cautious_Assassin:
                                    if (Math.random() < 0.05) list2.add(bot.getNum());
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
                                case Cautious_Assassin:
                                    if (Math.random() < 0.10) list2.add(bot.getNum());
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


    public static void receiveMessage(Message message) {

    }



    @Override
    public void run() {
         while (gameIsGoing.get()) {
             // if (players.size == 0) --> todo
         }
    }
}
