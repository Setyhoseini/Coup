package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;

public class ChooseCardsWindow extends JFrame implements ActionListener {

    int count = 0;
    AtomicBoolean isChoosing = new AtomicBoolean(true);

    JLabel deck1Label = new JLabel();
    JLabel deck2Label = new JLabel();
    JLabel card1Label = new JLabel();
    JLabel card2Label = new JLabel();

    JCheckBox deck1Check = new JCheckBox();
    JCheckBox deck2Check = new JCheckBox();
    JCheckBox card1Check = new JCheckBox();
    JCheckBox card2Check = new JCheckBox();

    JLabel yourCards = new JLabel("Your Cards:");
    JLabel fromDeck = new JLabel("From Deck:");
    JLabel chooseTwo = new JLabel("Choose Two Cards");

    JButton confirm = new JButton("Confirm");
    JButton reset = new JButton("Reset");



    ButtonGroup checks = new ButtonGroup();

    public ChooseCardsWindow(Card deck1, Card deck2, Card card1, Card card2) {



        chooseTwo.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        chooseTwo.setBounds(240, 12, 300, 50);
        chooseTwo.setForeground(Color.orange);



        yourCards.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
        fromDeck.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
        yourCards.setBounds(25, 260, 200, 50);
        fromDeck.setBounds(25, 572, 200, 50);
        yourCards.setForeground(Color.LIGHT_GRAY);
        fromDeck.setForeground(Color.LIGHT_GRAY);



        confirm.setBounds(615, 610, 100, 40);
        confirm.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
        confirm.setFocusable(false);
        confirm.setEnabled(false);

        reset.setBounds(615, 550, 100, 40);
        reset.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
        reset.setFocusable(false);

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.setSize(760, 760);
        this.setVisible(true);
        this.setLayout(null);
        this.setResizable(false);


        this.add(confirm);
        this.add(reset);
        this.add(chooseTwo);



        card1Check.setBounds(265, 360, 20, 20);
        card1Check.setBackground(null);
        this.add(card1Check);

        card2Check.setBounds(460, 360, 20, 20);
        card2Check.setBackground(null);
        this.add(card2Check);

        deck1Check.setBounds(265, 669, 20, 20);
        deck1Check.setBackground(null);
        this.add(deck1Check);

        deck2Check.setBounds(460, 669, 20, 20);
        deck2Check.setBackground(null);
        this.add(deck2Check);


        checks.add(card1Check);
        checks.add(card2Check);
        checks.add(deck1Check);
        checks.add(deck2Check);


        card1Check.addActionListener(this);
        card2Check.addActionListener(this);
        deck1Check.addActionListener(this);
        deck2Check.addActionListener(this);

        confirm.addActionListener(this);
        reset.addActionListener(this);


        card1Label.setBounds(190, 100, 170, 255);
        card1Label.setIcon(card1.getImage());
        this.add(card1Label);

        card2Label.setBounds(385, 100, 170, 255);
        card2Label.setIcon(card2.getImage());
        this.add(card2Label);

        deck1Label.setBounds(190, 409, 170, 255);
        deck1Label.setIcon(deck1.getImage());
        this.add(deck1Label);

        deck2Label.setBounds(385, 409, 170, 255);
        deck2Label.setIcon(deck2.getImage());
        this.add(deck2Label);


        this.setLocationRelativeTo(null);
        this.add(fromDeck);
        this.add(yourCards);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
         if (actionEvent.getSource() == card1Check || actionEvent.getSource() == card2Check
             || actionEvent.getSource() == deck1Check || actionEvent.getSource() == deck2Check) {
             count++;
             if (card1Check.isSelected()) {
                 checks.remove(card1Check);
             }
             if (card2Check.isSelected()) {
                 checks.remove(card2Check);
             }
             if (deck1Check.isSelected()) {
                 checks.remove(deck1Check);
             }
             if (deck2Check.isSelected()) {
                 checks.remove(deck2Check);
             }
             if (count == 2) {
                 card1Check.setEnabled(false);
                 card2Check.setEnabled(false);
                 deck1Check.setEnabled(false);
                 deck2Check.setEnabled(false);
             }
         }

         if (actionEvent.getSource() == reset) {
             count = 0;
             card1Check.setEnabled(true);
             card2Check.setEnabled(true);
             deck1Check.setEnabled(true);
             deck2Check.setEnabled(true);

             card1Check.setSelected(false);
             card2Check.setSelected(false);
             deck1Check.setSelected(false);
             deck2Check.setSelected(false);
         }

         if (actionEvent.getSource() == confirm) {

         }

        }
}
