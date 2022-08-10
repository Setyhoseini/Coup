package Logic.Game;

import GUI.*;
import Logic.Player.Bot;
import Logic.Player.BotType;
import Logic.Player.Human;

import java.util.Collections;

public class Action {

    public static void income(int by) {
        if (by == 1) Human.updateCoins(1);
        else Game.getBotByNum(by).updateCoins(1);
        Game.changeTurn();
    }

    public static void foreignAid(int by) {
        if (by == 1) Human.updateCoins(2);
        else Game.getBotByNum(by).updateCoins(2);
        Game.changeTurn();
    }

    public static void tax(int by) {
        if (by == 1) Human.updateCoins(3);
        else Game.getBotByNum(by).updateCoins(3);
        Game.changeTurn();
    }

    public static void exchangeOne(int by, int card) {
        if (by == 1) {
            Card c = (card == 1 ? Human.getCard1() : Human.getCard2());
            Collections.shuffle(Card.Deck);

            System.out.println(Card.Deck.toString());

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



        //**********************
        System.out.println(Card.Deck.toString());
    }

    public static void exchangeTwo(int by) {
        if (by == 1) {
            Collections.shuffle(Card.Deck);
            Card deck1 = Card.Deck.get(0);
            Card deck2 = Card.Deck.get(1);
            if (Human.card2 == null) {
                new ChooseOneCardForExchangeWindow(deck1, deck2, Human.card1);
                HumanSection.enableNeutral();
                Human.lastAction = null;
                Human.waitForResponse();

                switch (Human.lastAction) {
                    case Confirmed_2:
                        Card.Deck.add(Human.card1);
                        Human.card1 = deck2;
                        Card.Deck.remove(deck2);
                        break;
                    case Confirmed_1:
                        Card.Deck.add(Human.card1);
                        Human.card1 = deck1;
                        Card.Deck.remove(deck1);
                        break;
                }
                HumanSection.updateCard1();
            }
            else if (Human.card1 == null) {
                new ChooseOneCardForExchangeWindow(deck1, deck2, Human.card2);
                HumanSection.enableNeutral();
                Human.lastAction = null;
                Human.waitForResponse();

                switch (Human.lastAction) {
                    case Confirmed_2:
                        Card.Deck.add(Human.card2);
                        Human.card2 = deck2;
                        Card.Deck.remove(deck2);
                        break;
                    case Confirmed_1:
                        Card.Deck.add(Human.card2);
                        Human.card2 = deck1;
                        Card.Deck.remove(deck1);
                        break;
                }
                HumanSection.updateCard2();
            }
            else {
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
                HumanSection.updateCards();
            }
            Human.lastAction = null;
        }
        else {
            Bot doer = Game.getBotByNum(by);
            if (doer.getRole() == BotType.Cautious_Assassin) {
                Collections.shuffle(Card.Deck);
                Card deck1 = Card.Deck.remove(0);
                Card deck2 = Card.Deck.remove(1);
                if (deck1 == Card.Assassin) {
                    if (doer.card1 == Card.Ambassador) {
                        Card card1 = doer.card1;
                        doer.card1 = deck1;
                        Card.Deck.add(card1);
                    }
                    else {
                        Card card2 = doer.card2;
                        doer.card2 = deck1;
                        Card.Deck.add(card2);
                    }
                }
            }
            else {
                if (doer.card1 == null) exchangeOne(by, 2);
                else if (doer.card2 == null) exchangeOne(by, 1);
                else {
                    Collections.shuffle(Card.Deck);
                    Card deck1 = Card.Deck.remove(0);
                    Card deck2 = Card.Deck.remove(1);
                    Card card1 = doer.card1;
                    Card card2 = doer.card2;
                    doer.card1 = deck1;
                    doer.card2 = deck2;
                    Card.Deck.add(card1);
                    Card.Deck.add(card2);
                }
            }
        }
        Game.changeTurn();
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
            HumanSection.assassinateACard();
        }
        else {
            Bot victim = Game.getBotByNum(on);
            if (victim.getRole() == BotType.Cautious_Assassin) {
                if (victim.getCard1() == Card.Assassin) {
                    if (victim.getCard2() == null) {
                        victim.section.assassinateACard(1);
                    }
                    else {
                        victim.section.assassinateACard(2);
                    }
                }
                else {
                    if (victim.getCard1() == null) {
                        victim.section.assassinateACard(2);
                    }
                    else {
                        if (victim.getCard1() == Card.Assassin) {
                            victim.section.assassinateACard(2);
                        }
                        else {
                            victim.section.assassinateACard(1);
                        }
                    }
                }
            }
            else {
                if (victim.getCard1() == null) {
                    victim.section.assassinateACard(2);
                }
                else {
                    victim.section.assassinateACard(1);
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

    public static void pause(float seconds) throws InterruptedException {
        Thread.sleep((long) (seconds*1000));
    }

    public static void challengeSequenceForTax(int by) throws InterruptedException {
        Integer challenge = Game.botChallenges(by);
        if (by == 1) {
            if (challenge == 0) {
                Action.tax(1);
            }
            else {
                Bot challenger = Game.getBotByNum(challenge);
                challenger.section.controller = Controller.Challenges;
                HumanSection.enableIsToReactToChallenge();
                Human.waitForResponse();
                Human.lastAction = null;
                HumanSection.enableNeutral();
                challenger.section.controller = Controller.Neutral;
                if (Human.card1 == Card.Duke || Human.card2 == Card.Duke) {
                    if (Human.card1 == Card.Duke) HumanSection.replaceACard(1);
                    else HumanSection.replaceACard(2);
                    challenger.section.controller = Controller.Lost_Challenge;
                    pause(4);
                    challenger.section.controller = Controller.Neutral;
                    challenger.section.revealACard();
                    Action.tax(1);
                }
                else {
                    challenger.section.controller = Controller.Won_Challenge;
                    pause(4);
                    HumanSection.assassinateACard();
                    challenger.section.controller = Controller.Neutral;
                    Game.changeTurn();
                }
            }
        }
        else if (!Game.players.contains(1)) {
            Bot doer = Game.getBotByNum(by);
            doer.section.controller = Controller.Tax;
            if (challenge == 0) {
                Action.tax(by);
            }
            else {
                Bot challenger = Game.getBotByNum(challenge);
                challenger.section.controller = Controller.Challenges;
                pause(1.5F);
                challenger.section.controller = Controller.Neutral;
                if (doer.card1 == Card.Duke || doer.card2 == Card.Duke) {
                    if (doer.card1 == Card.Duke) doer.section.revealACard(1);
                    else doer.section.revealACard(2);
                    challenger.section.controller = Controller.Lost_Challenge;
                    pause(2);
                    if (doer.card1 == Card.Duke) doer.section.replaceACard(1);
                    else doer.section.replaceACard(2);
                    challenger.section.controller = Controller.Neutral;
                    doer.section.controller = Controller.Neutral;
                    challenger.section.revealACard();
                    Action.tax(by);
                }
                else {
                    challenger.section.controller = Controller.Won_Challenge;
                    pause(4);
                    doer.section.revealACard();
                    challenger.section.controller = Controller.Neutral;
                    doer.section.controller = Controller.Neutral;
                    Game.changeTurn();
                }
            }
        }
        else {
            Bot doer = Game.getBotByNum(by);
            doer.section.controller = Controller.Tax;
            HumanSection.enableIsAskedToChallenge(by);
            Human.waitForResponse();
            if (Human.lastAction == ActionName.Do_Nothing) {
                Human.lastAction = null;
                HumanSection.enableNeutral();
                if (challenge == 0) {
                    Action.tax(by);
                    doer.section.controller = Controller.Neutral;
                }
                else {
                    Bot challenger = Game.getBotByNum(challenge);
                    challenger.section.controller = Controller.Challenges;
                    pause(4);
                    challenger.section.controller = Controller.Neutral;
                    if (doer.card1 == Card.Duke || doer.card2 == Card.Duke) {
                        if (doer.card1 == Card.Duke) doer.section.revealACard(1);
                        else doer.section.revealACard(2);
                        challenger.section.controller = Controller.Lost_Challenge;
                        pause(4);
                        if (doer.card1 == Card.Duke) doer.section.replaceACard(1);
                        else doer.section.replaceACard(2);
                        challenger.section.controller = Controller.Neutral;
                        doer.section.controller = Controller.Neutral;
                        challenger.section.revealACard();
                        Action.tax(by);
                    }
                    else {
                        challenger.section.controller = Controller.Won_Challenge;
                        pause(4);
                        doer.section.revealACard();
                        challenger.section.controller = Controller.Neutral;
                        doer.section.controller = Controller.Neutral;
                        Game.changeTurn();
                    }
                }
            }
            else {
                HumanSection.enableNeutral();
                Human.lastAction = null;
                if (doer.card1 == Card.Duke || doer.card2 == Card.Duke) {
                    doer.section.controller = Controller.Neutral;
                    if (doer.card1 == Card.Duke) doer.section.revealACard(1);
                    else doer.section.revealACard(2);
                    doer.section.controller = Controller.Won_Challenge;
                    pause(4);
                    doer.section.controller = Controller.Neutral;
                    if (doer.card1 == Card.Duke) doer.section.replaceACard(1);
                    else doer.section.replaceACard(2);
                    HumanSection.assassinateACard();
                    Action.tax(by);
                }
                else {
                    doer.section.controller = Controller.Neutral;
                    doer.section.controller = Controller.Lost_Challenge;
                    pause(4);
                    doer.section.controller = Controller.Neutral;
                    doer.section.revealACard();
                    Game.changeTurn();
                }
            }
        }
    }

    public static void challengeSequenceForExchange(int by) throws InterruptedException {
        Integer challenge = Game.botChallenges(by);
        if (by == 1) {
            if (challenge == 0) {
                Action.exchangeTwo(1);
            }
            else {
                Bot challenger = Game.getBotByNum(challenge);
                challenger.section.controller = Controller.Challenges;
                HumanSection.enableIsToReactToChallenge();
                Human.waitForResponse();
                Human.lastAction = null;
                HumanSection.enableNeutral();
                challenger.section.controller = Controller.Neutral;
                if (Human.card1 == Card.Ambassador || Human.card2 == Card.Ambassador) {
                    if (Human.card1 == Card.Ambassador) HumanSection.replaceACard(1);
                    else HumanSection.replaceACard(2);
                    challenger.section.controller = Controller.Lost_Challenge;
                    pause(4);
                    challenger.section.controller = Controller.Neutral;
                    challenger.section.revealACard();
                    Action.exchangeTwo(1);
                }
                else {
                    challenger.section.controller = Controller.Won_Challenge;
                    pause(4);
                    HumanSection.assassinateACard();
                    challenger.section.controller = Controller.Neutral;
                    Game.changeTurn();
                }
            }
        }
        else if (!Game.players.contains(1)) {
            Bot doer = Game.getBotByNum(by);
            doer.section.controller = Controller.Exchange_Cards;
            if (challenge == 0) {
                Action.exchangeTwo(by);
            }
            else {
                Bot challenger = Game.getBotByNum(challenge);
                challenger.section.controller = Controller.Challenges;
                pause(1.5F);
                challenger.section.controller = Controller.Neutral;
                if (doer.card1 == Card.Ambassador || doer.card2 == Card.Ambassador) {
                    if (doer.card1 == Card.Ambassador) doer.section.revealACard(1);
                    else doer.section.revealACard(2);
                    challenger.section.controller = Controller.Lost_Challenge;
                    pause(2);
                    if (doer.card1 == Card.Ambassador) doer.section.replaceACard(1);
                    else doer.section.replaceACard(2);
                    challenger.section.controller = Controller.Neutral;
                    doer.section.controller = Controller.Neutral;
                    challenger.section.revealACard();
                    Action.exchangeTwo(by);
                }
                else {
                    challenger.section.controller = Controller.Won_Challenge;
                    pause(4);
                    doer.section.revealACard();
                    challenger.section.controller = Controller.Neutral;
                    doer.section.controller = Controller.Neutral;
                    Game.changeTurn();
                }
            }
        }
        else {
            Bot doer = Game.getBotByNum(by);
            doer.section.controller = Controller.Exchange_Cards;
            HumanSection.enableIsAskedToChallenge(by);
            Human.waitForResponse();
            if (Human.lastAction == ActionName.Do_Nothing) {
                Human.lastAction = null;
                HumanSection.enableNeutral();
                if (challenge == 0) {
                    Action.exchangeTwo(by);
                    doer.section.controller = Controller.Neutral;
                }
                else {
                    Bot challenger = Game.getBotByNum(challenge);
                    challenger.section.controller = Controller.Challenges;
                    pause(4);
                    challenger.section.controller = Controller.Neutral;
                    if (doer.card1 == Card.Ambassador || doer.card2 == Card.Ambassador) {
                        if (doer.card1 == Card.Ambassador) doer.section.revealACard(1);
                        else doer.section.revealACard(2);
                        challenger.section.controller = Controller.Lost_Challenge;
                        pause(4);
                        if (doer.card1 == Card.Ambassador) doer.section.replaceACard(1);
                        else doer.section.replaceACard(2);
                        challenger.section.controller = Controller.Neutral;
                        doer.section.controller = Controller.Neutral;
                        challenger.section.revealACard();
                        Action.exchangeTwo(by);
                    }
                    else {
                        challenger.section.controller = Controller.Won_Challenge;
                        pause(4);
                        doer.section.revealACard();
                        challenger.section.controller = Controller.Neutral;
                        doer.section.controller = Controller.Neutral;
                        Game.changeTurn();
                    }
                }
            }
            else {
                HumanSection.enableNeutral();
                Human.lastAction = null;
                if (doer.card1 == Card.Ambassador || doer.card2 == Card.Ambassador) {
                    doer.section.controller = Controller.Neutral;
                    if (doer.card1 == Card.Ambassador) doer.section.revealACard(1);
                    else doer.section.revealACard(2);
                    doer.section.controller = Controller.Won_Challenge;
                    pause(4);
                    doer.section.controller = Controller.Neutral;
                    if (doer.card1 == Card.Ambassador) doer.section.replaceACard(1);
                    else doer.section.replaceACard(2);
                    HumanSection.assassinateACard();
                    Action.exchangeTwo(by);
                }
                else {
                    doer.section.controller = Controller.Neutral;
                    doer.section.controller = Controller.Lost_Challenge;
                    pause(4);
                    doer.section.controller = Controller.Neutral;
                    doer.section.revealACard();
                    Game.changeTurn();
                }
            }
        }
    }

    public static void challengeSequenceForForeignAidBlock(int theBlocker, int on) throws InterruptedException {
        if (theBlocker == 1) {
            HumanSection.enableNeutral();
            Human.lastAction = null;
            Integer challenge2 = Game.botChallenges(1);
            if (challenge2 == 0) {
                Game.changeTurn();
            } else {
                Bot challenger = Game.getBotByNum(challenge2);
                if (on == challenge2) challenger.section.controller = Controller.Neutral;
                challenger.section.controller = Controller.Challenges;
                HumanSection.enableIsToReactToChallenge();
                Human.waitForResponse();
                Human.lastAction = null;
                HumanSection.enableNeutral();
                challenger.section.controller = Controller.Neutral;
                if (Human.getCard1() == Card.Duke || Human.getCard2() == Card.Duke) {
                    challenger.section.controller = Controller.Lost_Challenge;
                    challenger.section.revealACard();
                    if (Human.getCard1() == Card.Duke) {
                        HumanSection.replaceACard(1);
                    } else {
                        HumanSection.replaceACard(2);
                    }
                    pause(1.5F);
                    challenger.section.controller = Controller.Neutral;
                    Game.changeTurn();
                } else {
                    challenger.section.controller = Controller.Won_Challenge;
                    HumanSection.assassinateACard();
                    pause(1.5F);
                    challenger.section.controller = Controller.Neutral;
                    Action.foreignAid(on);
                }
            }
        }
        else if (Game.players.contains(1)) {
            Bot blocker = Game.getBotByNum(theBlocker);
            HumanSection.enableIsAskedToChallenge(theBlocker);
            Human.waitForResponse();
            HumanSection.enableNeutral();
            if (Human.lastAction == ActionName.Do_Nothing) {
                HumanSection.enableNeutral();
                blocker.section.controller = Controller.Neutral;
                Human.lastAction = null;
                Integer challenge2 = Game.botChallenges(theBlocker);
                if (challenge2 == 0) {
                    Game.changeTurn();
                } else {
                    Bot challenger = Game.getBotByNum(challenge2);
                    challenger.section.controller = Controller.Challenges;
                    pause(2.5F);
                    challenger.section.controller = Controller.Neutral;
                    if (blocker.getCard1() == Card.Duke || blocker.getCard2() == Card.Duke) {
                        blocker.section.controller = Controller.Won_Challenge;
                        challenger.section.revealACard();
                        if (blocker.getCard1() == Card.Duke) {
                            blocker.section.revealACard(1);
                            pause(1.5F);
                            blocker.section.replaceACard(1);
                        } else {
                            blocker.section.revealACard(2);
                            pause(1.5F);
                            blocker.section.replaceACard(2);
                        }
                        blocker.section.controller = Controller.Neutral;
                        Game.changeTurn();
                    } else {
                        blocker.section.controller = Controller.Lost_Challenge;
                        blocker.section.revealACard();
                        pause(3);
                        blocker.section.controller = Controller.Neutral;
                        Action.foreignAid(on);
                    }
                }
            } else {
                HumanSection.enableNeutral();
                blocker.section.controller = Controller.Neutral;
                Human.lastAction = null;
                if (blocker.getCard1() == Card.Duke || blocker.getCard2() == Card.Duke) {
                    blocker.section.controller = Controller.Won_Challenge;
                    if (blocker.getCard1() == Card.Duke) {
                        blocker.section.revealACard(1);
                        pause(1.5F);
                        blocker.section.replaceACard(1);
                    } else {
                        blocker.section.revealACard(2);
                        pause(1.5F);
                        blocker.section.replaceACard(2);
                    }
                    HumanSection.assassinateACard();
                    blocker.section.controller = Controller.Neutral;
                    Game.changeTurn();
                } else {
                    blocker.section.controller = Controller.Lost_Challenge;
                    blocker.section.revealACard();
                    pause(2);
                    blocker.section.controller = Controller.Neutral;
                    Action.foreignAid(on);
                }
            }
        }
        else {
            Bot blocker = Game.getBotByNum(theBlocker);
            blocker.section.controller = Controller.Neutral;
            Integer challenge2 = Game.botChallenges(theBlocker);
            if (challenge2 == 0) {
                Game.changeTurn();
            } else {
                Bot challenger = Game.getBotByNum(challenge2);
                challenger.section.controller = Controller.Challenges;
                pause(2.5F);
                challenger.section.controller = Controller.Neutral;
                if (blocker.getCard1() == Card.Duke || blocker.getCard2() == Card.Duke) {
                    blocker.section.controller = Controller.Won_Challenge;
                    challenger.section.revealACard();
                    if (blocker.getCard1() == Card.Duke) {
                        blocker.section.revealACard(1);
                        pause(2);
                        blocker.section.replaceACard(1);
                    } else {
                        blocker.section.revealACard(2);
                        pause(2);
                        blocker.section.replaceACard(2);
                    }
                    blocker.section.controller = Controller.Neutral;
                    Game.changeTurn();
                } else {
                    blocker.section.controller = Controller.Lost_Challenge;
                    blocker.section.revealACard();
                    pause(3);
                    blocker.section.controller = Controller.Neutral;
                    Action.foreignAid(on);
                }
            }
        }
    }

    public static void challengeSequenceForAssassinateBlock(int theBlocker, int on) throws InterruptedException {
        if (theBlocker == 1) {
            HumanSection.enableNeutral();
            Human.lastAction = null;
            Integer challenge2 = Game.botChallenges(1);
            if (challenge2 == 0) {
                Game.changeTurn();
            } else {
                Bot challenger = Game.getBotByNum(challenge2);
                if (on == challenge2) challenger.section.controller = Controller.Neutral;
                challenger.section.controller = Controller.Challenges;
                HumanSection.enableIsToReactToChallenge();
                Human.waitForResponse();
                Human.lastAction = null;
                HumanSection.enableNeutral();
                challenger.section.controller = Controller.Neutral;
                if (Human.getCard1() == Card.Contessa || Human.getCard2() == Card.Contessa) {
                    challenger.section.controller = Controller.Lost_Challenge;
                    challenger.section.revealACard();
                    if (Human.getCard1() == Card.Contessa) {
                        HumanSection.replaceACard(1);
                    } else {
                        HumanSection.replaceACard(2);
                    }
                    pause(1.5F);
                    challenger.section.controller = Controller.Neutral;
                    Game.getBotByNum(on).section.controller = Controller.Neutral;
                    Game.changeTurn();
                } else {
                    challenger.section.controller = Controller.Won_Challenge;
                    HumanSection.assassinateACard();
                    pause(1.5F);
                    challenger.section.controller = Controller.Neutral;
                    Action.assassinate(on, theBlocker);
                }
            }
        }
        else if (Game.players.contains(1)) {
            Bot blocker = Game.getBotByNum(theBlocker);
            pause(1);
            blocker.section.controller = Controller.Blocks;
            HumanSection.enableIsAskedToChallenge(theBlocker);
            Human.waitForResponse();
            HumanSection.enableNeutral();
            if (Human.lastAction == ActionName.Do_Nothing) {
                HumanSection.enableNeutral();
                blocker.section.controller = Controller.Neutral;
                Human.lastAction = null;
                Integer challenge2 = Game.botChallenges(theBlocker);
                if (challenge2 == 0) {
                    if (on != 1) Game.getBotByNum(on).section.controller = Controller.Neutral;
                    Game.changeTurn();
                } else {
                    Bot challenger = Game.getBotByNum(challenge2);
                    if (challenge2 == on) challenger.section.controller = Controller.Neutral;
                    challenger.section.controller = Controller.Challenges;
                    pause(2.5F);
                    challenger.section.controller = Controller.Neutral;
                    if (blocker.getCard1() == Card.Contessa || blocker.getCard2() == Card.Contessa) {
                        blocker.section.controller = Controller.Won_Challenge;
                        challenger.section.revealACard();
                        if (blocker.getCard1() == Card.Contessa) {
                            blocker.section.revealACard(1);
                            pause(1.5F);
                            blocker.section.replaceACard(1);
                        } else {
                            blocker.section.revealACard(2);
                            pause(1.5F);
                            blocker.section.replaceACard(2);
                        }
                        blocker.section.controller = Controller.Neutral;
                        if (on != 1) Game.getBotByNum(on).section.controller = Controller.Neutral;
                        Game.changeTurn();
                    } else {
                        blocker.section.controller = Controller.Lost_Challenge;
                        blocker.section.revealACard();
                        pause(3);
                        blocker.section.controller = Controller.Neutral;
                        if (on != 1) Game.getBotByNum(on).section.controller = Controller.Neutral;
                        Action.assassinate(theBlocker, on);
                    }
                }
            } else {
                HumanSection.enableNeutral();
                blocker.section.controller = Controller.Neutral;
                Human.lastAction = null;
                if (blocker.getCard1() == Card.Contessa || blocker.getCard2() == Card.Contessa) {
                    blocker.section.controller = Controller.Won_Challenge;
                    if (blocker.getCard1() == Card.Contessa) {
                        blocker.section.revealACard(1);
                        pause(1.5F);
                        blocker.section.replaceACard(1);
                    } else {
                        blocker.section.revealACard(2);
                        pause(1.5F);
                        blocker.section.replaceACard(2);
                    }
                    HumanSection.assassinateACard();
                    blocker.section.controller = Controller.Neutral;
                    if (on != 1) Game.getBotByNum(on).section.controller = Controller.Neutral;
                    Game.changeTurn();
                } else {
                    blocker.section.controller = Controller.Lost_Challenge;
                    blocker.section.revealACard();
                    pause(2);
                    blocker.section.controller = Controller.Neutral;
                    if (on != 1) Game.getBotByNum(on).section.controller = Controller.Neutral;
                    Action.assassinate(theBlocker, on);
                }
            }
        }
        else {
            Bot blocker = Game.getBotByNum(theBlocker);
            pause(1.5F);
            blocker.section.controller = Controller.Blocks;
            pause(1.5F);
            blocker.section.controller = Controller.Neutral;
            Integer challenge2 = Game.botChallenges(theBlocker);
            if (challenge2 == 0) {
                Game.changeTurn();
            } else {
                Bot challenger = Game.getBotByNum(challenge2);
                if (challenge2 == on) challenger.section.controller = Controller.Neutral;
                challenger.section.controller = Controller.Challenges;
                pause(2.5F);
                challenger.section.controller = Controller.Neutral;
                if (blocker.getCard1() == Card.Contessa || blocker.getCard2() == Card.Contessa) {
                    blocker.section.controller = Controller.Won_Challenge;
                    challenger.section.revealACard();
                    if (blocker.getCard1() == Card.Contessa) {
                        blocker.section.revealACard(1);
                        pause(2);
                        blocker.section.replaceACard(1);
                    } else {
                        blocker.section.revealACard(2);
                        pause(2);
                        blocker.section.replaceACard(2);
                    }
                    blocker.section.controller = Controller.Neutral;
                    if (on != 1) Game.getBotByNum(on).section.controller = Controller.Neutral;
                    Game.changeTurn();
                } else {
                    blocker.section.controller = Controller.Lost_Challenge;
                    blocker.section.revealACard();
                    pause(3);
                    blocker.section.controller = Controller.Neutral;
                    if (on != 1) Game.getBotByNum(on).section.controller = Controller.Neutral;
                    Action.assassinate(theBlocker, on);
                }
            }
        }
    }

    public static void challengeSequenceForStealBlock(int theBlocker, int on) throws InterruptedException {
        if (theBlocker == 1) {
            HumanSection.enableNeutral();
            Human.lastAction = null;
            Integer challenge2 = Game.botChallenges(1);
            if (challenge2 == 0) {
                Game.changeTurn();
            } else {
                Bot challenger = Game.getBotByNum(challenge2);
                if (on == challenge2) challenger.section.controller = Controller.Neutral;
                challenger.section.controller = Controller.Challenges;
                HumanSection.enableIsToReactToChallenge();
                Human.waitForResponse();
                Human.lastAction = null;
                HumanSection.enableNeutral();
                challenger.section.controller = Controller.Neutral;
                if (Human.getCard1() == Card.Ambassador || Human.getCard2() == Card.Ambassador
                     || Human.getCard1() == Card.Captain || Human.getCard2() == Card.Captain) {
                    challenger.section.controller = Controller.Lost_Challenge;
                    challenger.section.revealACard();
                    if (Human.getCard1() == Card.Ambassador || Human.getCard1() == Card.Captain) {
                        HumanSection.replaceACard(1);
                    } else {
                        HumanSection.replaceACard(2);
                    }
                    pause(1.5F);
                    challenger.section.controller = Controller.Neutral;
                    Game.getBotByNum(on).section.controller = Controller.Neutral;
                    Game.changeTurn();
                } else {
                    challenger.section.controller = Controller.Won_Challenge;
                    HumanSection.assassinateACard();
                    pause(1.5F);
                    challenger.section.controller = Controller.Neutral;
                    Action.steal(on, theBlocker);
                }
            }
        }
        else if (Game.players.contains(1)) {
            Bot blocker = Game.getBotByNum(theBlocker);
            pause(1);
            blocker.section.controller = Controller.Blocks;
            HumanSection.enableIsAskedToChallenge(theBlocker);
            Human.waitForResponse();
            HumanSection.enableNeutral();
            if (Human.lastAction == ActionName.Do_Nothing) {
                HumanSection.enableNeutral();
                blocker.section.controller = Controller.Neutral;
                Human.lastAction = null;
                Integer challenge2 = Game.botChallenges(theBlocker);
                if (challenge2 == 0) {
                    if (on != 1) Game.getBotByNum(on).section.controller = Controller.Neutral;
                    Game.changeTurn();
                } else {
                    Bot challenger = Game.getBotByNum(challenge2);
                    if (challenge2 == on) challenger.section.controller = Controller.Neutral;
                    challenger.section.controller = Controller.Challenges;
                    pause(2.5F);
                    challenger.section.controller = Controller.Neutral;
                    if (blocker.getCard1() == Card.Ambassador || blocker.getCard2() == Card.Ambassador
                        || blocker.getCard1() == Card.Captain || blocker.getCard2() == Card.Captain) {
                        blocker.section.controller = Controller.Won_Challenge;
                        challenger.section.revealACard();
                        if (blocker.getCard1() == Card.Captain || blocker.getCard1() == Card.Ambassador) {
                            blocker.section.revealACard(1);
                            pause(1.5F);
                            blocker.section.replaceACard(1);
                        } else {
                            blocker.section.revealACard(2);
                            pause(1.5F);
                            blocker.section.replaceACard(2);
                        }
                        blocker.section.controller = Controller.Neutral;
                        if (on != 1) Game.getBotByNum(on).section.controller = Controller.Neutral;
                        Game.changeTurn();
                    } else {
                        blocker.section.controller = Controller.Lost_Challenge;
                        blocker.section.revealACard();
                        pause(3);
                        blocker.section.controller = Controller.Neutral;
                        if (on != 1) Game.getBotByNum(on).section.controller = Controller.Neutral;
                        Action.steal(theBlocker, on);
                    }
                }
            } else {
                HumanSection.enableNeutral();
                blocker.section.controller = Controller.Neutral;
                Human.lastAction = null;
                if (blocker.getCard1() == Card.Ambassador || blocker.getCard2() == Card.Ambassador
                    || blocker.getCard1() == Card.Captain || blocker.getCard2() == Card.Captain) {
                    blocker.section.controller = Controller.Won_Challenge;
                    if (blocker.getCard1() == Card.Captain || blocker.getCard1() == Card.Ambassador) {
                        blocker.section.revealACard(1);
                        pause(1.5F);
                        blocker.section.replaceACard(1);
                    } else {
                        blocker.section.revealACard(2);
                        pause(1.5F);
                        blocker.section.replaceACard(2);
                    }
                    HumanSection.assassinateACard();
                    blocker.section.controller = Controller.Neutral;
                    if (on != 1) Game.getBotByNum(on).section.controller = Controller.Neutral;
                    Game.changeTurn();
                } else {
                    blocker.section.controller = Controller.Lost_Challenge;
                    blocker.section.revealACard();
                    pause(2);
                    blocker.section.controller = Controller.Neutral;
                    if (on != 1) Game.getBotByNum(on).section.controller = Controller.Neutral;
                    Action.steal(theBlocker, on);
                }
            }
        }
        else {
            Bot blocker = Game.getBotByNum(theBlocker);
            pause(1.5F);
            blocker.section.controller = Controller.Blocks;
            pause(1.5F);
            blocker.section.controller = Controller.Neutral;
            Integer challenge2 = Game.botChallenges(theBlocker);
            if (challenge2 == 0) {
                Game.changeTurn();
            } else {
                Bot challenger = Game.getBotByNum(challenge2);
                if (challenge2 == on) challenger.section.controller = Controller.Neutral;
                challenger.section.controller = Controller.Challenges;
                pause(2.5F);
                challenger.section.controller = Controller.Neutral;
                if (blocker.getCard1() == Card.Captain || blocker.getCard2() == Card.Captain
                    || blocker.getCard1() == Card.Ambassador || blocker.getCard2() == Card.Ambassador) {
                    blocker.section.controller = Controller.Won_Challenge;
                    challenger.section.revealACard();
                    if (blocker.getCard1() == Card.Captain || blocker.getCard1() == Card.Ambassador) {
                        blocker.section.revealACard(1);
                        pause(2);
                        blocker.section.replaceACard(1);
                    } else {
                        blocker.section.revealACard(2);
                        pause(2);
                        blocker.section.replaceACard(2);
                    }
                    blocker.section.controller = Controller.Neutral;
                    if (on != 1) Game.getBotByNum(on).section.controller = Controller.Neutral;
                    Game.changeTurn();
                } else {
                    blocker.section.controller = Controller.Lost_Challenge;
                    blocker.section.revealACard();
                    pause(3);
                    blocker.section.controller = Controller.Neutral;
                    if (on != 1) Game.getBotByNum(on).section.controller = Controller.Neutral;
                    Action.steal(theBlocker, on);
                }
            }
        }
    }

    public static void challengeSequenceForAssassinate(int by, int on) throws InterruptedException {
        Integer challenge = Game.botChallenges(by);
        if (by == 1) {
            if (challenge == 0) {
                Action.assassinate(1, on);
            }
            else {
                Bot challenger = Game.getBotByNum(challenge);
                challenger.section.controller = Controller.Challenges;
                HumanSection.enableIsToReactToChallenge();
                Human.waitForResponse();
                Human.lastAction = null;
                HumanSection.enableNeutral();
                challenger.section.controller = Controller.Neutral;
                if (Human.card1 == Card.Assassin || Human.card2 == Card.Assassin) {
                    if (Human.card1 == Card.Assassin) HumanSection.replaceACard(1);
                    else HumanSection.replaceACard(2);
                    challenger.section.controller = Controller.Lost_Challenge;
                    pause(4);
                    challenger.section.controller = Controller.Neutral;
                    challenger.section.revealACard();
                    Action.assassinate(1, on);
                }
                else {
                    challenger.section.controller = Controller.Won_Challenge;
                    pause(4);
                    HumanSection.assassinateACard();
                    challenger.section.controller = Controller.Neutral;
                    Game.changeTurn();
                }
            }
        }
        else if (!Game.players.contains(1)) {
            Bot doer = Game.getBotByNum(by);
            if (challenge == 0) {
                Action.assassinate(by, on);
            }
            else {
                Bot challenger = Game.getBotByNum(challenge);
                challenger.section.controller = Controller.Challenges;
                pause(1.5F);
                challenger.section.controller = Controller.Neutral;
                if (doer.card1 == Card.Assassin || doer.card2 == Card.Assassin) {
                    if (doer.card1 == Card.Assassin) doer.section.revealACard(1);
                    else doer.section.revealACard(2);
                    challenger.section.controller = Controller.Lost_Challenge;
                    pause(2);
                    if (doer.card1 == Card.Assassin) doer.section.replaceACard(1);
                    else doer.section.replaceACard(2);
                    challenger.section.controller = Controller.Neutral;
                    doer.section.controller = Controller.Neutral;
                    challenger.section.revealACard();
                    Action.assassinate(by, on);
                }
                else {
                    challenger.section.controller = Controller.Won_Challenge;
                    pause(4);
                    doer.section.revealACard();
                    challenger.section.controller = Controller.Neutral;
                    doer.section.controller = Controller.Neutral;
                    Game.changeTurn();
                }
            }
        }
        else {
            Bot doer = Game.getBotByNum(by);
            HumanSection.enableIsAskedToChallenge(by);
            Human.waitForResponse();
            if (Human.lastAction == ActionName.Do_Nothing) {
                Human.lastAction = null;
                HumanSection.enableNeutral();
                if (challenge == 0) {
                    Action.assassinate(by, on);
                    doer.section.controller = Controller.Neutral;
                }
                else {
                    Bot challenger = Game.getBotByNum(challenge);
                    challenger.section.controller = Controller.Challenges;
                    pause(4);
                    challenger.section.controller = Controller.Neutral;
                    if (doer.card1 == Card.Assassin || doer.card2 == Card.Assassin) {
                        if (doer.card1 == Card.Assassin) doer.section.revealACard(1);
                        else doer.section.revealACard(2);
                        challenger.section.controller = Controller.Lost_Challenge;
                        pause(4);
                        if (doer.card1 == Card.Assassin) doer.section.replaceACard(1);
                        else doer.section.replaceACard(2);
                        challenger.section.controller = Controller.Neutral;
                        doer.section.controller = Controller.Neutral;
                        challenger.section.revealACard();
                        Action.assassinate(by, on);
                    }
                    else {
                        challenger.section.controller = Controller.Won_Challenge;
                        pause(4);
                        doer.section.revealACard();
                        challenger.section.controller = Controller.Neutral;
                        doer.section.controller = Controller.Neutral;
                        Game.changeTurn();
                    }
                }
            }
            else {
                HumanSection.enableNeutral();
                Human.lastAction = null;
                if (doer.card1 == Card.Assassin || doer.card2 == Card.Assassin) {
                    doer.section.controller = Controller.Neutral;
                    if (doer.card1 == Card.Assassin) doer.section.revealACard(1);
                    else doer.section.revealACard(2);
                    doer.section.controller = Controller.Won_Challenge;
                    pause(4);
                    doer.section.controller = Controller.Neutral;
                    if (doer.card1 == Card.Assassin) doer.section.replaceACard(1);
                    else doer.section.replaceACard(2);
                    if (on == 1) {
                        if (Human.card2 == null) HumanSection.assassinateACard(1);
                        else HumanSection.assassinateACard(2);
                    }
                    else {
                        HumanSection.assassinateACard();
                    }
                    Action.assassinate(by, on);
                }
                else {
                    doer.section.controller = Controller.Neutral;
                    doer.section.controller = Controller.Lost_Challenge;
                    pause(4);
                    doer.section.controller = Controller.Neutral;
                    doer.section.revealACard();
                    Game.changeTurn();
                }
            }
        }
    }

    public static void challengeSequenceForSteal(int by, int on) throws InterruptedException {
        Integer challenge = Game.botChallenges(by);
        if (by == 1) {
            if (challenge == 0) {
                Action.steal(1, on);
            }
            else {
                Bot challenger = Game.getBotByNum(challenge);
                challenger.section.controller = Controller.Challenges;
                HumanSection.enableIsToReactToChallenge();
                Human.waitForResponse();
                Human.lastAction = null;
                HumanSection.enableNeutral();
                challenger.section.controller = Controller.Neutral;
                if (Human.card1 == Card.Captain || Human.card2 == Card.Captain) {
                    if (Human.card1 == Card.Captain) HumanSection.replaceACard(1);
                    else HumanSection.replaceACard(2);
                    challenger.section.controller = Controller.Lost_Challenge;
                    pause(4);
                    challenger.section.controller = Controller.Neutral;
                    challenger.section.revealACard();
                    Action.steal(1, on);
                }
                else {
                    challenger.section.controller = Controller.Won_Challenge;
                    pause(4);
                    HumanSection.assassinateACard();
                    challenger.section.controller = Controller.Neutral;
                    Game.changeTurn();
                }
            }
        }
        else if (!Game.players.contains(1)) {
            Bot doer = Game.getBotByNum(by);
            if (challenge == 0) {
                Action.steal(by, on);
            }
            else {
                Bot challenger = Game.getBotByNum(challenge);
                challenger.section.controller = Controller.Challenges;
                pause(1.5F);
                challenger.section.controller = Controller.Neutral;
                if (doer.card1 == Card.Captain || doer.card2 == Card.Captain) {
                    if (doer.card1 == Card.Captain) doer.section.revealACard(1);
                    else doer.section.revealACard(2);
                    challenger.section.controller = Controller.Lost_Challenge;
                    pause(2);
                    if (doer.card1 == Card.Captain) doer.section.replaceACard(1);
                    else doer.section.replaceACard(2);
                    challenger.section.controller = Controller.Neutral;
                    doer.section.controller = Controller.Neutral;
                    challenger.section.revealACard();
                    Action.steal(by, on);
                }
                else {
                    challenger.section.controller = Controller.Won_Challenge;
                    pause(4);
                    doer.section.revealACard();
                    challenger.section.controller = Controller.Neutral;
                    doer.section.controller = Controller.Neutral;
                    Game.changeTurn();
                }
            }
        }
        else {
            Bot doer = Game.getBotByNum(by);
            HumanSection.enableIsAskedToChallenge(by);
            Human.waitForResponse();
            if (Human.lastAction == ActionName.Do_Nothing) {
                Human.lastAction = null;
                HumanSection.enableNeutral();
                if (challenge == 0) {
                    Action.steal(by, on);
                    doer.section.controller = Controller.Neutral;
                }
                else {
                    Bot challenger = Game.getBotByNum(challenge);
                    challenger.section.controller = Controller.Challenges;
                    pause(4);
                    challenger.section.controller = Controller.Neutral;
                    if (doer.card1 == Card.Captain || doer.card2 == Card.Captain) {
                        if (doer.card1 == Card.Captain) doer.section.revealACard(1);
                        else doer.section.revealACard(2);
                        challenger.section.controller = Controller.Lost_Challenge;
                        pause(4);
                        if (doer.card1 == Card.Captain) doer.section.replaceACard(1);
                        else doer.section.replaceACard(2);
                        challenger.section.controller = Controller.Neutral;
                        doer.section.controller = Controller.Neutral;
                        challenger.section.revealACard();
                        Action.steal(by, on);
                    }
                    else {
                        challenger.section.controller = Controller.Won_Challenge;
                        pause(4);
                        doer.section.revealACard();
                        challenger.section.controller = Controller.Neutral;
                        doer.section.controller = Controller.Neutral;
                        Game.changeTurn();
                    }
                }
            }
            else {
                HumanSection.enableNeutral();
                Human.lastAction = null;
                if (doer.card1 == Card.Captain || doer.card2 == Card.Captain) {
                    doer.section.controller = Controller.Neutral;
                    if (doer.card1 == Card.Captain) doer.section.revealACard(1);
                    else doer.section.revealACard(2);
                    doer.section.controller = Controller.Won_Challenge;
                    pause(4);
                    doer.section.controller = Controller.Neutral;
                    if (doer.card1 == Card.Captain) doer.section.replaceACard(1);
                    else doer.section.replaceACard(2);
                    HumanSection.assassinateACard();
                    Action.steal(by, on);
                }
                else {
                    doer.section.controller = Controller.Neutral;
                    doer.section.controller = Controller.Lost_Challenge;
                    pause(4);
                    doer.section.controller = Controller.Neutral;
                    doer.section.revealACard();
                    Game.changeTurn();
                }
            }
        }
    }

    public static void blockSequenceForForeignAid(int by) throws InterruptedException {
        if (by != 1 && Game.players.contains(1)) {
            HumanSection.enableIsAskedToBlock(by);
            Game.getBotByNum(by).section.controller = Controller.Foreign_Aid;
            Human.waitForResponse();
            if (Human.lastAction == ActionName.Do_Nothing) {
                Human.lastAction = null;
                Integer block = Game.botBlocks(by, 0, "foreign_aid");
                if (block == 0) {
                    HumanSection.enableNeutral();
                    Game.getBotByNum(by).section.controller = Controller.Neutral;
                    Action.foreignAid(by);
                } else {
                    Bot blocker = Game.getBotByNum(block);
                    blocker.section.controller = Controller.Blocks;
                    HumanSection.enableIsAskedToChallenge(block);
                    Human.waitForResponse();
                    HumanSection.enableNeutral();
                    Game.getBotByNum(by).section.controller = Controller.Neutral;
                    challengeSequenceForForeignAidBlock(block, by);
                }
            } else {
                Game.getBotByNum(by).section.controller = Controller.Neutral;
                challengeSequenceForForeignAidBlock(1, by);
            }
        } else {
            if (by != 1) Game.getBotByNum(by).section.controller = Controller.Foreign_Aid;
            Integer block = Game.botBlocks(by, 0, "foreign_aid");
            pause(1.5F);
            if (block == 0) {
                if (by != 1) Game.getBotByNum(by).section.controller = Controller.Neutral;
                Action.foreignAid(by);
            } else {
                Bot blocker = Game.getBotByNum(block);
                blocker.section.controller = Controller.Blocks;
                if (by != 1) pause(2);
                if (by != 1) Game.getBotByNum(by).section.controller = Controller.Neutral;
                challengeSequenceForForeignAidBlock(block, by);
            }
        }
    }

    public static void blockSequenceForSteal(int doer, int on) throws InterruptedException {
        if (doer != 1) {
            Bot stealer = Game.getBotByNum(doer);
            switch (on) {
                case 1:
                    stealer.section.controller = Controller.Wants_To_Steal_From_Player1;
                    break;
                case 2:
                    stealer.section.controller = Controller.Wants_To_Steal_From_Player2;
                    break;
                case 3:
                    stealer.section.controller = Controller.Wants_To_Steal_From_Player3;
                    break;
                case 4:
                    stealer.section.controller = Controller.Wants_To_Steal_From_Player4;
                    break;
            }
            if (on == 1) {
                HumanSection.enableIsAskedToBlock(doer);
                Human.waitForResponse();
                if (Human.lastAction == ActionName.Do_Nothing) {
                    Human.lastAction = null;
                    HumanSection.enableNeutral();
                    challengeSequenceForSteal(doer, 1);
                } else {
                    challengeSequenceForStealBlock(1, doer);
                }
            }
            else {
                Integer block = Game.botBlocks(doer, on, "steal");
                if (block == 0) {
                    challengeSequenceForSteal(doer, on);
                }
                else {
                    challengeSequenceForStealBlock(on, doer);
                }
            }
        }
        else {
            Integer block = Game.botBlocks(doer, on, "steal");
            if (block == 0) {
                challengeSequenceForSteal(doer, on);
            }
            else {
                challengeSequenceForStealBlock(on, doer);
            }
        }
    }

    public static void blockSequenceForAssassinate(int doer, int on) throws InterruptedException {
        if (doer != 1) {
            Bot assassin = Game.getBotByNum(doer);
            switch (on) {
                case 1:
                    assassin.section.controller = Controller.Wants_To_Assassinate_Player1;
                    break;
                case 2:
                    assassin.section.controller = Controller.Wants_To_Assassinate_Player2;
                    break;
                case 3:
                    assassin.section.controller = Controller.Wants_To_Assassinate_Player3;
                    break;
                case 4:
                    assassin.section.controller = Controller.Wants_To_Assassinate_Player4;
                    break;
            }
            if (on == 1) {
                HumanSection.enableIsAskedToBlock(doer);
                Human.waitForResponse();
                if (Human.lastAction == ActionName.Do_Nothing) {
                    Human.lastAction = null;
                    HumanSection.enableNeutral();
                    challengeSequenceForAssassinate(doer, 1);
                } else {
                    challengeSequenceForAssassinateBlock(1, doer);
                }
            }
            else {
                Integer block = Game.botBlocks(doer, on, "assassinate");
                if (block == 0) {
                    challengeSequenceForAssassinate(doer, on);
                }
                else {
                    challengeSequenceForAssassinateBlock(on, doer);
                }
            }
        }
        else {
          //  HumanSection.enableNeutral();
            Integer block = Game.botBlocks(doer, on, "assassinate");
            if (block == 0) {
                challengeSequenceForAssassinate(doer, on);
            }
            else {
                challengeSequenceForAssassinateBlock(on, doer);
            }
        }
    }
}