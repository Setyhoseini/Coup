package Logic.Game;

import GUI.Card;
import GUI.Message;
import Logic.Player.Bot;

import java.util.Vector;

public class Game extends Thread{
    public static Vector<Bot> bots = new Vector<>();
    public static Vector<Card> deck = new Vector<>();
    public static Vector<Integer> players = new Vector<>();

    public Game(Vector<Bot> bots) {
        Game.bots = bots;
        players.add(1);
        players.add(2);
        players.add(3);
        players.add(4);
    }


    public void changeTurn() {

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

    }
}
