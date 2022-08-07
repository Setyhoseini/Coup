package GUI;

import Logic.Game.ActionName;
import Logic.Player.Human;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseOneCardWindow extends JFrame implements ActionListener {
    JLabel card1Label = new JLabel();
    JLabel card2Label = new JLabel();
    JLabel choose = new JLabel("Choose the card you want to keep");

    JCheckBox card1Check = new JCheckBox();
    JCheckBox card2Check = new JCheckBox();

    JButton confirm = new JButton("Confirm");

    ButtonGroup checks = new ButtonGroup();

    public ChooseOneCardWindow(Card card1, Card card2) {
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.setSize(500, 500);
        this.setVisible(true);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        card1Label.setBounds(60, 100, 170, 255);
        card1Label.setIcon(card1.getImage());
        this.add(card1Label);

        card2Label.setBounds(255, 100, 170, 255);
        card2Label.setIcon(card2.getImage());
        this.add(card2Label);

        initButtons();
        initCheckBoxes();
        initTextLabels();
    }

    public void initButtons() {
        confirm.setBounds(192, 400, 100, 40);
        confirm.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
        confirm.setFocusable(false);
        confirm.setEnabled(false);
        this.add(confirm);

        confirm.addActionListener(this);
    }

    public void initTextLabels() {
        choose.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
        choose.setBounds(43, 12, 500, 50);
        choose.setForeground(Color.orange);

        this.add(choose);
    }

    public void initCheckBoxes() {
        card1Check.setBounds(135, 360, 20, 20);
        card1Check.setBackground(null);
        this.add(card1Check);

        card2Check.setBounds(330, 360, 20, 20);
        card2Check.setBackground(null);
        this.add(card2Check);

        checks.add(card1Check);
        checks.add(card2Check);

        card1Check.addActionListener(this);
        card2Check.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == card1Check || actionEvent.getSource() == card2Check) {
            confirm.setEnabled(true);
        }

        if (actionEvent.getSource() == confirm) {
            if (card1Check.isSelected()) Human.lastAction = ActionName.Confirmed_1;
            if (card2Check.isSelected()) Human.lastAction = ActionName.Confirmed_2;
        }
    }
}