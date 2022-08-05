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
    public static int turn = 1;
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
        if (newTurn == 1) Human.state = PlayerState.IsToPlay;
        else {
            for (Bot b : bots) {
                if (b.getNum() == newTurn) {
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
