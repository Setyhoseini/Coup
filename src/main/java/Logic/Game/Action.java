package Logic.Game;

import GUI.Card;
import GUI.ChooseCardsWindow;
import GUI.HumanSection;
import Logic.Player.Bot;
import Logic.Player.BotType;
import Logic.Player.Human;

import java.text.DecimalFormat;
import java.util.Collections;

public class Action {

    public static void income(int by) {
        if (by == 1) Human.updateCoins(1);
        else Game.getBotByNum(by).updateCoins(1);

        //***************
        Game.changeTurn();
    }

    public static void foreignAid(int by) {
        if (by == 1) Human.updateCoins(2);
        else Game.getBotByNum(by).updateCoins(2);
    }

    public static void tax(int by) {
        if (by == 1) Human.updateCoins(3);
        else Game.getBotByNum(by).updateCoins(3);
    }

    public static void exchangeOne(int by, int card) {
        if (by == 1) {
            Card c = (card == 1 ? Human.card1 : Human.card2);
            Collections.shuffle(Card.Deck);
            Card x = Card.Deck.remove(0);
            if (card == 1) {
                Human.setCard1(x);
                HumanSection.setCard1(x);
            } else {
                Human.setCard2(x);
                HumanSection.setCard2(x);
            }
            Card.Deck.add(c);
        }
        else {
            Card c = (card == 1 ? Game.getBotByNum(by).getCard1() : Game.getBotByNum(by).getCard2());
            Collections.shuffle(Card.Deck);
            Game.getBotByNum(by).setCard1(Card.Deck.remove(0));
            if (card == 1) {
                Game.getBotByNum(by).setCard1(Card.Deck.remove(0));
            } else {
                Game.getBotByNum(by).setCard2(Card.Deck.remove(0));
            }
            Card.Deck.add(c);
        }


        //***************
        Game.changeTurn();
    }

    public static void exchangeTwo(int by) {
        if (by == 1) {
            Collections.shuffle(Card.Deck);
            Card deck1 = Card.Deck.get(0);
            Card deck2 = Card.Deck.get(1);
            new ChooseCardsWindow(deck1, deck2, Human.card1, Human.card2);
            HumanSection.enableNeutral();
            Human.lastAction = null;
            Human.waitForResponse();

            switch (Human.lastAction) {
                case Confirmed_Cards_1_2:
                    Card.Deck.add(Human.card1);
                    Card.Deck.add(Human.card2);
                    Human.card1 = deck1;
                    Human.card2 = deck2;
                    Card.Deck.remove(deck1);
                    Card.Deck.remove(deck2);
                    break;
                case Confirmed_Cards_1_3:
                    Card.Deck.add(Human.card2);
                    Human.card2 = deck1;
                    Card.Deck.remove(deck1);
                    break;
                case Confirmed_Cards_1_4:
                    Card.Deck.add(Human.card1);
                    Human.card1 = deck1;
                    Card.Deck.remove(deck1);
                    break;
                case Confirmed_Cards_2_3:
                    Card.Deck.add(Human.card2);
                    Human.card2 = deck2;
                    Card.Deck.remove(deck2);
                    break;
                case Confirmed_Cards_2_4:
                    Card.Deck.add(Human.card1);
                    Human.card1 = deck2;
                    Card.Deck.remove(deck2);
                    break;
            }
            Human.lastAction = null;
            HumanSection.updateCards();
        }
        else {

        }
    }

    public static void coup(int by, int on) {
        if (by == 1) {
            Human.updateCoins(-7);
            Bot bot = Game.getBotByNum(on);
            bot.section.revealACard();
            if (bot.getCard1() == null && bot.getCard2() == null) {
                Game.players.remove((Integer) on);
            }
        }
        else {
            Bot couper = Game.getBotByNum(by);
            couper.updateCoins(-7);
            if (on == 1) {
                // todo. the human should choose which card to reveal
            }
            else {
                Bot bot = Game.getBotByNum(on);
                bot.section.revealACard();
                if (bot.getCard1() == null && bot.getCard2() == null) {
                    Game.players.remove((Integer) on);
                }
            }
        }
        Game.changeTurn();
    }

    public static void assassinate(int by, int on) {
        if (by == 1) {
            Human.updateCoins(-3);
        }
        else {
            Game.getBotByNum(by).updateCoins(-3);
        }

        if (on == 1) {
            // todo ask the human which card they wanna reveal
        }
        else {
            Bot victim = Game.getBotByNum(on);
            if (victim.getRole() == BotType.Cautious_Assassin) {
                if (victim.getCard1() == Card.Assassin) {
                    if (victim.getCard2() == null) {
                        victim.section.revealACard(1);
                    }
                    else {
                        victim.section.revealACard(2);
                    }
                }
                else {
                    if (victim.getCard1() == null) {
                        victim.section.revealACard(2);
                    }
                    else {
                        if (victim.getCard1() == Card.Assassin) {
                            victim.section.revealACard(2);
                        }
                        else {
                            victim.section.revealACard(1);
                        }
                    }
                }
            }
            else {
                if (victim.getCard1() == null) {
                    victim.section.revealACard(2);
                }
                else {
                    victim.section.revealACard(1);
                }
            }

            if (victim.getCard1() == null && victim.getCard2() == null) {
                Game.players.remove((Integer) on);
            }
        }
            Game.changeTurn();
    }

    public static void steal(int by, int on) {
        if (by == 1) {
            Bot bot = Game.getBotByNum(on);
            if (bot.coins == 1) {
                bot.updateCoins(-1);
                Human.updateCoins(1);
            }
            else {
                bot.updateCoins(-2);
                Human.updateCoins(2);
            }
        }
        else {
            Bot stealer = Game.getBotByNum(by);
            if (on == 1) {
                if (Human.coins == 1) {
                    Human.updateCoins(-1);
                    stealer.updateCoins(1);
                }
                else {
                    Human.updateCoins(-2);
                    stealer.updateCoins(2);
                }
            }
            else {
                Bot b = Game.getBotByNum(on);
                if (b.coins == 1) {
                    b.updateCoins(-1);
                    stealer.updateCoins(1);
                }
                else {
                    b.updateCoins(-2);
                    stealer.updateCoins(2);
                }
            }
        }
        Game.changeTurn();
    }
}