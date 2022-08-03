package Logic.Game;

import Logic.Player.Human;

public class Action {

    public static void income(int by) {
        if (by == 1) Human.updateCoins(1);
        else Game.getBotByNum(by).updateCoins(1);
    }

    public void foreignAid(int by) {
        if (by == 1) Human.updateCoins(2);
        else Game.getBotByNum(by).updateCoins(2);
    }

    public void tax(int by) {
        if (by == 1) Human.updateCoins(3);
        else Game.getBotByNum(by).updateCoins(3);
    }

    public void exchangeOne(int by) {

    }

    public void exchangeTwo(int by) {

    }

    public void coup(int by, int on) {

    }

    public void assassinate(int by, int on) {

    }

    public void steal(int by, int on) {

    }

    public void block(int by, int on) {

    }

    public void challenge(int by, int on) {

    }
}
