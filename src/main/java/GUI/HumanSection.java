package GUI;
import Logic.Game.ActionName;
import Logic.Game.Game;
import Logic.Player.Bot;
import Logic.Player.Human;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

public class HumanSection implements ActionListener {
    static JLabel card1 = new JLabel();
    static JLabel card2 = new JLabel();
    static JLabel num = new JLabel("" + Human.coins);
    static JButton income;
    static JButton aid;
    static JButton tax;
    static JButton exchangeBoth;
    static JButton reactToChallenge;
    static JButton doNothing;
    static JButton exchange1;
    static JButton exchange2;

    public HumanSection(Card c1, Card c2) {
        card1.setIcon(c1.getImage());
        card2.setIcon(c2.getImage());
        initCards();
        initCoinNum();
        initIncomeButton();
        initAidButton();
        initTaxButton();
        initExchangeBothButton();
        initReactToChallengeButton();
        initDoNothingButton();
        initExchangeButtons();
    }

    public static void disableAll() {
        income.setEnabled(false);
        aid.setEnabled(false);
        tax.setEnabled(false);
        exchangeBoth.setEnabled(false);
        reactToChallenge.setEnabled(false);
        doNothing.setEnabled(false);
        exchange1.setEnabled(false);
        exchange2.setEnabled(false);
    }

    public static void enableIsToReactToChallenge() {
        disableAll();
        reactToChallenge.setEnabled(true);
    }

    public static void enableIsAskedToChallenge(int num) {
        disableAll();
        for (Bot b : Game.bots) {
            b.section.disableAll();
        }
        Game.getBotByNum(num).section.challenge.setEnabled(true);
        doNothing.setEnabled(true);
    }

    public static void enableIsAskedToBlock(int num) {
        disableAll();
        for (Bot b : Game.bots) {
            b.section.disableAll();
        }
        Game.getBotByNum(num).section.block.setEnabled(true);
        doNothing.setEnabled(true);
    }

    public static void enableNeutral() {
        disableAll();
        for (Bot b : Game.bots) {
            b.section.disableAll();
        }
    }

    public static void enableIsToPlay() {
        disableAll();
        for (Bot b : Game.bots) {
            if (Game.players.contains(b.getNum()) && b.coins > 0) {
                b.section.disableAll();
                b.section.steal.setEnabled(true);
            }
        }
        if (Human.coins >= 3) {
            for (Bot b : Game.bots) {
                if (Game.players.contains(b.getNum()))
                b.section.assassinate.setEnabled(true);
            }
        }
        if (Human.coins >= 7) {
            for (Bot b : Game.bots) {
                if (Game.players.contains(b.getNum()))
                b.section.coup.setEnabled(true);
            }
        }
        tax.setEnabled(true);
        income.setEnabled(true);
        aid.setEnabled(true);
        if (Human.card1 != null && Human.coins > 0) {
            exchange1.setEnabled(true);
        }
        if (Human.card2 != null && Human.coins > 0) {
            exchange2.setEnabled(true);
        }
        exchangeBoth.setEnabled(true);
    }

    public static void enableMustCoup() {
        for (Bot b : Game.bots) {
            if (Game.players.contains(b.getNum())) {
                b.section.coup.setEnabled(true);
            }
        }
    }

    public void initCards() {
        card1.setBounds(588, 50, 170, 255);
        card1.setVisible(true);
        Frame.label.add(card1);
        card2.setBounds(770, 50, 170, 255);
        card2.setVisible(true);
        Frame.label.add(card2);
    }

    public void initCoinNum() {
        num.setBounds(645, 393, 100, 50);
        num.setFont(new Font("Trebuchet MS", Font.BOLD, 40));
        num.setForeground(Color.BLACK);
        Frame.label.add(num);
    }

    public static void updateNum() {
        num.setText("" + Human.coins);
    }

    public static void updateCards() {
        updateCard1();
        updateCard2();
    }

    public static void updateCard1() {
        card1.setIcon(Human.card1.getImage());
    }

    public static void updateCard2() {
        card2.setIcon(Human.card2.getImage());
    }

    public static void replaceACard(int card) {
        if (card == 1) {
            Card current = Human.card1;
            Collections.shuffle(Card.Deck);
            Human.card1 = Card.Deck.remove(0);
            Card.Deck.add(current);
            card1.setIcon(Human.card1.getImage());
        }
        else {
            Card current = Human.card2;
            Collections.shuffle(Card.Deck);
            Human.card2 = Card.Deck.remove(0);
            Card.Deck.add(current);
            card2.setIcon(Human.card2.getImage());
        }
    }

    public static void assassinateACard() {
        if (Human.card2 != null || Human.card1 != null) {
            if (Human.card1 != null && Human.card2 != null) {
                new ChooseOneCardWindow(Human.card1, Human.card2);
                Human.waitForResponse();
                if (Human.lastAction == ActionName.Confirmed_1) {
                    assassinateACard(2);
                }
                else {
                    assassinateACard(1);
                }
                Human.lastAction = null;
            }
            else {
                if (Human.card1 == null) {
                    assassinateACard(2);
                }
                else {
                    assassinateACard(1);
                }
            }
        }
    }

    public static void assassinateACard(int card) {
        if (Human.card1 != null || Human.card2 != null) {
            if (card == 1) {
                card1.setIcon(Human.card1.getDeadImage());
                Human.card1 = null;
            }
            else {
                card2.setIcon(Human.card2.getDeadImage());
                Human.card2 = null;
            }
            if (Human.card1 == null && Human.card2 == null) {
                Game.players.remove((Integer)1);
            }
        }
    }

    public void initIncomeButton() {
        income = new JButton("Income");
        income.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
        income.setBounds(965, 50, 160, 50);
        income.setFocusable(false);
        Frame.label.add(income);
        income.addActionListener(this);
    }

    public void initAidButton() {
        aid = new JButton("Foreign Aid");
        aid.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
        aid.setBounds(965, 110, 160, 50);
        aid.setFocusable(false);
        Frame.label.add(aid);
        aid.addActionListener(this);
    }

    public void initTaxButton() {
        tax = new JButton("Tax");
        tax.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
        tax.setBounds(965, 170, 160, 50);
        tax.setFocusable(false);
        Frame.label.add(tax);
        tax.addActionListener(this);
    }

    public void initExchangeBothButton() {
        exchangeBoth = new JButton("Exchange Cards");
        exchangeBoth.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
        exchangeBoth.setBounds(965, 230, 160, 50);
        exchangeBoth.setFocusable(false);
        Frame.label.add(exchangeBoth);
        exchangeBoth.addActionListener(this);
    }

    public void initReactToChallengeButton() {
        reactToChallenge = new JButton("React to Challenge");
        reactToChallenge.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        reactToChallenge.setBounds(965, 290, 160, 50);
        reactToChallenge.setFocusable(false);
        Frame.label.add(reactToChallenge);
        reactToChallenge.addActionListener(this);
    }

    public void initDoNothingButton() {
        doNothing = new JButton("Do Nothing");
        doNothing.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
        doNothing.setBounds(965, 350, 160, 50);
        doNothing.setFocusable(false);
        Frame.label.add(doNothing);
        doNothing.addActionListener(this);
    }

    public void initExchangeButtons() {
        exchange1 = new JButton("Exchange");
        exchange1.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        exchange1.setBounds(620, 310, 105, 25);
        exchange1.setFocusable(false);
        Frame.label.add(exchange1);
        exchange1.addActionListener(this);
        exchange2 = new JButton("Exchange");
        exchange2.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        exchange2.setBounds(802, 310, 105, 25);
        exchange2.setFocusable(false);
        Frame.label.add(exchange2);
        exchange2.addActionListener(this);
    }

    public static void setCard1(Card card) {
        Human.card1 = card;
        card1.setIcon(card.getImage());
    }
    public static void setCard2(Card card) {
        Human.card2 = card;
        card2.setIcon(card.getImage());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
         if (actionEvent.getSource() == income) {
             Human.lastAction = ActionName.Income;
         }

         if (actionEvent.getSource() == exchange1) {
             Human.lastAction = ActionName.Exchange_Card1;
         }
         if (actionEvent.getSource() == exchange2) {
             Human.lastAction = ActionName.Exchange_Card2;
         }

        if (actionEvent.getSource() == exchangeBoth) {
            Human.lastAction = ActionName.Exchange_Both_Cards;
        }

        if (actionEvent.getSource() == aid) {
           Human.lastAction = ActionName.Foreign_Aid;
        }

        if (actionEvent.getSource() == doNothing) {
            Human.lastAction = ActionName.Do_Nothing;
        }

        if (actionEvent.getSource() == tax) {
            Human.lastAction = ActionName.Tax;
        }

        if (actionEvent.getSource() == reactToChallenge) {
            Human.lastAction = ActionName.React_To_Challenge;
        }
    }
}