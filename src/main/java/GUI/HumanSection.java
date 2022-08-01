package GUI;
import Logic.Human;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

public class HumanSection implements ActionListener {
    static JLabel card1 = new JLabel();
    static JLabel card2 = new JLabel();
    static JLabel num = new JLabel("" + Human.num);
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
        num.setText("" + Human.num);
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
        exchangeBoth = new JButton("Exchange cards");
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

    public void setCard1(Card card) {
        Human.card1 = card;
        card1.setIcon(card.getImage());
    }
    public void setCard2(Card card) {
        Human.card2 = card;
        card2.setIcon(card.getImage());
    }



    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //income
         if (actionEvent.getSource() == income) {
             Human.updateNum(1);
         }

         //exchange one card
         if (actionEvent.getSource() == exchange1) {
             Card c = Human.card1;
             Collections.shuffle(Card.Deck);
             setCard1(Card.Deck.remove(0));
             Card.Deck.add(c);
         }
        if (actionEvent.getSource() == exchange2) {
            Card c = Human.card2;
            Collections.shuffle(Card.Deck);
            setCard2(Card.Deck.remove(0));
            Card.Deck.add(c);
        }


        //exchange both cards
        if (actionEvent.getSource() == exchangeBoth) {
            Card c1 = Human.card1;
            Card c2 = Human.card2;
            Collections.shuffle(Card.Deck);
            setCard1(Card.Deck.remove(0));
            setCard2(Card.Deck.remove(1));
            Card.Deck.add(c1);
            Card.Deck.add(c2);
        }

        //foreign aid
        if (actionEvent.getSource() == aid) {
            Human.updateNum(2);
        }

        //do nothing
        if (actionEvent.getSource() == doNothing) {

        }

        //tax
        if (actionEvent.getSource() == tax) {
            Human.updateNum(3);
        }

        //react to challenge
        if (actionEvent.getSource() == reactToChallenge) {

        }

    }
}
