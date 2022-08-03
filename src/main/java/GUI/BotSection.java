package GUI;

import Logic.Player.Bot;
import Logic.Player.Human;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class BotSection implements ActionListener {
    Bot bot;
    JLabel card1 = new JLabel();
    JLabel card2 = new JLabel();
    JLabel num = new JLabel();
    JButton coup;
    JButton steal;
    JButton assassinate;
    JButton challenge;
    JButton block;

    public BotSection(Bot bot) {
        this.bot = bot;
        card1.setIcon(Card.Back.getImage());
        card2.setIcon(Card.Back.getImage());
        this.num.setText("" + bot.getCoins());
        initCards(bot.getNum());
        initCoinNum(bot.getNum());
        initCoupButton(bot.getNum());
        initStealButton(bot.getNum());
        initAssassinateButton(bot.getNum());
        initChallengeButton(bot.getNum());
        initBlockButton(bot.getNum());
        bot.setSection(this);
    }



    public void revealACard() {
        if (bot.getCard1() != null && bot.getCard2() == null) {
            card1.setIcon(bot.getCard1().getImage());

            // Logic
            bot.setCard1(null);
        }
        else if (bot.getCard2() != null && bot.getCard1() == null){
            card2.setIcon(bot.getCard2().getImage());

            // Logic
            bot.setCard2(null);
        }
        else {
            ArrayList<Card> cards = new ArrayList<>();
            cards.add(bot.getCard1());
            cards.add(bot.getCard2());
            Collections.shuffle(cards);
            if (cards.get(0).equals(bot.getCard1())) {
                card1.setIcon(bot.getCard1().getImage());

                // Logic
                bot.setCard1(null);
            }
            else {
                card2.setIcon(bot.getCard2().getImage());

               // Logic
                bot.setCard2(null);
            }
        }
    }














    public void initCards(int id) {
        switch (id) {
            case 2:
                card1.setBounds(1188, 50, 170, 255);
                card1.setVisible(true);
                Frame.label.add(card1);
                card2.setBounds(1370, 50, 170, 255);
                card2.setVisible(true);
                Frame.label.add(card2);
                break;
            case 3:
                card1.setBounds(1188, 510, 170, 255);
                card1.setVisible(true);
                Frame.label.add(card1);
                card2.setBounds(1370, 510, 170, 255);
                card2.setVisible(true);
                Frame.label.add(card2);
                break;
            case 4:
                card1.setBounds(588, 510, 170, 255);
                card1.setVisible(true);
                Frame.label.add(card1);
                card2.setBounds(770, 510, 170, 255);
                card2.setVisible(true);
                Frame.label.add(card2);
                break;
        }
    }

    public void initCoinNum(int id) {
        switch (id) {
            case 2:
                num.setBounds(1245, 393, 100, 50);
                num.setFont(new Font("Trebuchet MS", Font.BOLD, 40));
                num.setForeground(Color.BLACK);
                Frame.label.add(num);
                break;
            case 3:
                num.setBounds(1245, 853, 100, 50);
                num.setFont(new Font("Trebuchet MS", Font.BOLD, 40));
                num.setForeground(Color.BLACK);
                Frame.label.add(num);
                break;
            case 4:
                num.setBounds(645, 853, 100, 50);
                num.setFont(new Font("Trebuchet MS", Font.BOLD, 40));
                num.setForeground(Color.BLACK);
                Frame.label.add(num);
                break;
        }
    }

    public void initCoupButton(int id) {
        switch (id) {
            case 2:
                coup = new JButton("Coup");
                coup.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
                coup.setBounds(1565, 50, 160, 50);
                coup.setFocusable(false);
                Frame.label.add(coup);
                coup.addActionListener(this);
                break;
            case 3:
                coup = new JButton("Coup");
                coup.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
                coup.setBounds(1565, 510, 160, 50);
                coup.setFocusable(false);
                Frame.label.add(coup);
                coup.addActionListener(this);
                break;
            case 4:
                coup = new JButton("Coup");
                coup.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
                coup.setBounds(965, 510, 160, 50);
                coup.setFocusable(false);
                Frame.label.add(coup);
                coup.addActionListener(this);
                break;
        }
    }

    public void initStealButton(int id) {
        switch (id) {
            case 2:
                steal = new JButton("Steal");
                steal.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
                steal.setBounds(1565, 110, 160, 50);
                steal.setFocusable(false);
                Frame.label.add(steal);
                steal.addActionListener(this);
                break;
            case 3:
                steal = new JButton("Steal");
                steal.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
                steal.setBounds(1565, 570, 160, 50);
                steal.setFocusable(false);
                Frame.label.add(steal);
                steal.addActionListener(this);
                break;
            case 4:
                steal = new JButton("Steal");
                steal.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
                steal.setBounds(965, 570, 160, 50);
                steal.setFocusable(false);
                Frame.label.add(steal);
                steal.addActionListener(this);
                break;
        }
    }

    public void initAssassinateButton(int id) {
        switch (id) {
            case 2:
                assassinate = new JButton("Assassinate");
                assassinate.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
                assassinate.setBounds(1565, 170, 160, 50);
                assassinate.setFocusable(false);
                Frame.label.add(assassinate);
                assassinate.addActionListener(this);
                break;
            case 3:
                assassinate = new JButton("Assassinate");
                assassinate.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
                assassinate.setBounds(1565, 630, 160, 50);
                assassinate.setFocusable(false);
                Frame.label.add(assassinate);
                assassinate.addActionListener(this);
                break;
            case 4:
                assassinate = new JButton("Assassinate");
                assassinate.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
                assassinate.setBounds(965, 630, 160, 50);
                assassinate.setFocusable(false);
                Frame.label.add(assassinate);
                assassinate.addActionListener(this);
                break;
        }
    }

    public void initChallengeButton(int id) {
        switch (id) {
            case 2:
                challenge = new JButton("Challenge");
                challenge.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
                challenge.setBounds(1565, 230, 160, 50);
                challenge.setFocusable(false);
                Frame.label.add(challenge);
                challenge.addActionListener(this);
                break;
            case 3:
                challenge = new JButton("Challenge");
                challenge.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
                challenge.setBounds(1565, 690, 160, 50);
                challenge.setFocusable(false);
                Frame.label.add(challenge);
                challenge.addActionListener(this);
                break;
            case 4:
                challenge = new JButton("Challenge");
                challenge.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
                challenge.setBounds(965, 690, 160, 50);
                challenge.setFocusable(false);
                Frame.label.add(challenge);
                challenge.addActionListener(this);
                break;
        }
    }

    public void initBlockButton(int id) {
        switch (id) {
            case 2:
                block = new JButton("Block");
                block.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
                block.setBounds(1565, 290, 160, 50);
                block.setFocusable(false);
                Frame.label.add(block);
                block.addActionListener(this);
                break;
            case 3:
                block = new JButton("Block");
                block.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
                block.setBounds(1565, 750, 160, 50);
                block.setFocusable(false);
                Frame.label.add(block);
                block.addActionListener(this);
                break;
            case 4:
                block = new JButton("Block");
                block.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
                block.setBounds(965, 750, 160, 50);
                block.setFocusable(false);
                Frame.label.add(block);
                block.addActionListener(this);
                break;
        }
    }



    public void updateCoins() {
        num.setText("" + bot.getCoins());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //coup
        if (actionEvent.getSource() == coup) {
           Human.updateNum(-7);

        }


        //steal
        if (actionEvent.getSource() == steal) {
            Human.updateNum(2);
            bot.updateCoins(-2);
        }


        //challenge
        if (actionEvent.getSource() == challenge) {

        }


        //assassinate
        if (actionEvent.getSource() == assassinate) {
           revealACard();
        }


        //block
        if (actionEvent.getSource() == block) {

        }
    }
}
