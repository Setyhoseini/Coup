package GUI;

import Logic.Game.ActionName;
import Logic.Player.Human;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseOneCardForExchangeWindow extends JFrame implements ActionListener {
    JLabel deck1Label = new JLabel();
    JLabel deck2Label = new JLabel();
    JLabel card1Label = new JLabel();

    JCheckBox deck1Check = new JCheckBox();
    JCheckBox deck2Check = new JCheckBox();
    JCheckBox card1Check = new JCheckBox();

    JLabel yourCards = new JLabel("Your Cards:");
    JLabel fromDeck = new JLabel("From Deck:");
    JLabel chooseTwo = new JLabel("Choose One Card");

    JButton confirm = new JButton("Confirm");

    ButtonGroup checks = new ButtonGroup();

    public ChooseOneCardForExchangeWindow(Card deck1, Card deck2, Card card1) {
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.setSize(760, 760);
        this.setVisible(true);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        card1Label.setBounds(190, 100, 170, 255);
        card1Label.setIcon(card1.getImage());
        this.add(card1Label);

        deck1Label.setBounds(190, 409, 170, 255);
        deck1Label.setIcon(deck1.getImage());
        this.add(deck1Label);

        deck2Label.setBounds(385, 409, 170, 255);
        deck2Label.setIcon(deck2.getImage());
        this.add(deck2Label);

        initButtons();
        initCheckBoxes();
        initTextLabels();
    }

    public void initButtons() {
        confirm.setBounds(615, 610, 100, 40);
        confirm.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
        confirm.setFocusable(false);
        confirm.setEnabled(false);
        this.add(confirm);
        confirm.addActionListener(this);
    }

    public void initTextLabels() {
        chooseTwo.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        chooseTwo.setBounds(240, 12, 300, 50);
        chooseTwo.setForeground(Color.orange);
        yourCards.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
        fromDeck.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
        yourCards.setBounds(25, 260, 200, 50);
        fromDeck.setBounds(25, 572, 200, 50);
        yourCards.setForeground(Color.LIGHT_GRAY);
        fromDeck.setForeground(Color.LIGHT_GRAY);
        this.add(chooseTwo);
        this.add(fromDeck);
        this.add(yourCards);
    }

    public void initCheckBoxes() {
        card1Check.setBounds(265, 360, 20, 20);
        card1Check.setBackground(null);
        this.add(card1Check);

        deck1Check.setBounds(265, 669, 20, 20);
        deck1Check.setBackground(null);
        this.add(deck1Check);

        deck2Check.setBounds(460, 669, 20, 20);
        deck2Check.setBackground(null);
        this.add(deck2Check);

        checks.add(card1Check);
        checks.add(deck1Check);
        checks.add(deck2Check);

        card1Check.addActionListener(this);
        deck1Check.addActionListener(this);
        deck2Check.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == card1Check || actionEvent.getSource() == deck1Check || actionEvent.getSource() == deck2Check) {
            confirm.setEnabled(true);
        }

        if (actionEvent.getSource() == confirm) {
            if (card1Check.isSelected()) Human.lastAction = ActionName.Confirmed_3;
            if (deck1Check.isSelected()) Human.lastAction = ActionName.Confirmed_1;
            if (deck2Check.isSelected()) Human.lastAction = ActionName.Confirmed_2;
            this.dispose();
        }
    }
}