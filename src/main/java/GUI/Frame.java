package GUI;
import Logic.Game.Game;
import Logic.Game.Loader;
import Logic.Player.Bot;
import Logic.Player.BotType;
import Logic.Player.Human;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Vector;

public class Frame extends JFrame implements ActionListener {
    ImageIcon icon2 = new ImageIcon("sum.png");
    ImageIcon icon3 = new ImageIcon("test2.png");
    ImageIcon icon7 = new ImageIcon("coin.png");
    ImageIcon icon = new ImageIcon("bg.png");

    ImageIcon actions = new ImageIcon("actions.png");

    public static JLabel label = new JLabel();
    public static JLabel label1 = new JLabel();
    public static JTextArea text = new JTextArea();

    JButton confirm = new JButton("Confirm");
    JButton reset = new JButton("Reset");

    JCheckBox nerdCheck = new JCheckBox();
    JCheckBox paranoidCheck = new JCheckBox();
    JCheckBox coupLoverCheck = new JCheckBox();
    JCheckBox cautiousAssassinCheck = new JCheckBox();

    BotType player2Type;
    BotType player3Type;
    BotType player4Type;

    int count = 0;

    public Frame() {
        this.setSize(1800, 1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        initChoosePlayersWindow();
        this.repaint();
        this.revalidate();
    }

    public void initChoosePlayersWindow() {
        label1.setBounds(0, 0, 1800, 1000);
        label1.setBackground(Color.DARK_GRAY);
        label1.setVisible(true);
        label1.setOpaque(true);
        this.add(label1);
        this.setVisible(true);

        JLabel nerd = new JLabel("Nerd");
        JLabel paranoid = new JLabel("Paranoid");
        JLabel coupLover = new JLabel("Coup Lover");
        JLabel cautiousAssassin = new JLabel("Cautious Assassin");

        nerd.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        paranoid.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        cautiousAssassin.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        coupLover.setFont(new Font("Trebuchet MS", Font.BOLD, 30));

        nerd.setForeground(Color.LIGHT_GRAY);
        paranoid.setForeground(Color.LIGHT_GRAY);
        cautiousAssassin.setForeground(Color.LIGHT_GRAY);
        coupLover.setForeground(Color.LIGHT_GRAY);

        nerd.setBounds(1382, 350, 300, 300);
        paranoid.setBounds(320, 350, 300, 300);
        cautiousAssassin.setBounds(620, 350, 300, 300);
        coupLover.setBounds(1042, 350, 300, 300);

        nerdCheck.setBounds(1350, 491, 20, 20);
        nerdCheck.setBackground(null);
        nerdCheck.addActionListener(this);
        label1.add(nerdCheck);

        paranoidCheck.setBounds(288, 491, 20, 20);
        paranoidCheck.setBackground(null);
        paranoidCheck.addActionListener(this);
        label1.add(paranoidCheck);

        cautiousAssassinCheck.setBounds(588, 491, 20, 20);
        cautiousAssassinCheck.setBackground(null);
        cautiousAssassinCheck.addActionListener(this);
        label1.add(cautiousAssassinCheck);

        coupLoverCheck.setBounds(1010, 491, 20, 20);
        coupLoverCheck.setBackground(null);
        coupLoverCheck.addActionListener(this);
        label1.add(coupLoverCheck);

        confirm.setBounds(900, 650, 110, 40);
        confirm.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        confirm.setFocusable(false);
        confirm.setEnabled(false);
        label1.add(confirm);

        reset.setBounds(740, 650, 110, 40);
        reset.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        reset.setFocusable(false);
        label1.add(reset);

        confirm.addActionListener(this);
        reset.addActionListener(this);

        JLabel dialog = new JLabel("Choose your opponents in order");
        dialog.setFont(new Font("Trebuchet MS", Font.BOLD, 33));
        dialog.setForeground(Color.ORANGE);
        dialog.setBounds(640, 150, 700, 40);
        label1.add(dialog);

        label1.add(nerd);
        label1.add(paranoid);
        label1.add(cautiousAssassin);
        label1.add(coupLover);
    }

    public void initBackground() {
        label.setBounds(0, 0, 1800, 1000);
        label.setIcon(icon);
        label.setVisible(false);
        JLabel sum = new JLabel();
        sum.setIcon(icon2);
        sum.setBounds(30, 30, 514, 392);
        sum.setVisible(true);
        label.add(sum);
        this.add(label);
        this.setVisible(true);
    }

    public void initTextArea() {

        text.setBounds(60, 495, 443, 430);
        text.setFont(new Font("Serif", Font.BOLD, 25));
        text.setBackground(Color.LIGHT_GRAY);
        text.setMargin( new Insets(20,20,20,20));
        JScrollPane scrollPane = new JScrollPane(text);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(60, 495, 443, 430);
        text.setVisible(true);
        scrollPane.setVisible(true);
        text.setEditable(false);
        label.add(scrollPane);
        JLabel title = new JLabel(actions);
        title.setBounds(21, 375, 200, 200);
        title.setVisible(true);
        label.add(title);
    }

    public void initHumanSection() {
        JLabel coin2 = new JLabel();
        coin2.setIcon(icon7);
        coin2.setBounds(585, 390, 60, 60);
        coin2.setVisible(true);
        label.add(coin2);
        JLabel section = new JLabel();
        section.setIcon(icon3);
        section.setBounds(570, 35, 570, 430);
        section.setVisible(true);
        label.add(section);
    }

    public void initPlayerTwoSection() {
        JLabel coin3 = new JLabel();
        coin3.setIcon(icon7);
        coin3.setBounds(1185, 390, 60, 60);
        coin3.setVisible(true);
        label.add(coin3);
        JLabel section2 = new JLabel();
        section2.setIcon(icon3);
        section2.setBounds(1170, 35, 570, 430);
        section2.setVisible(true);
        label.add(section2);
    }

    public void initPlayerThreeSection() {
        JLabel coin4 = new JLabel();
        coin4.setIcon(icon7);
        coin4.setBounds(1185, 850, 60, 60);
        coin4.setVisible(true);
        label.add(coin4);
        JLabel section3 = new JLabel();
        section3.setIcon(icon3);
        section3.setBounds(1170, 495, 570, 430);
        section3.setVisible(true);
        label.add(section3);
    }

    public void initPlayerFourSection() {
        JLabel coin = new JLabel();
        coin.setIcon(icon7);
        coin.setBounds(585, 850, 60, 60);
        coin.setVisible(true);
        label.add(coin);
        JLabel section4 = new JLabel();
        section4.setIcon(icon3);
        section4.setBounds(570, 495, 570, 430);
        section4.setVisible(true);
        label.add(section4);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == reset) {
            count = 0;

            nerdCheck.setSelected(false);
            nerdCheck.setEnabled(true);

            paranoidCheck.setSelected(false);
            paranoidCheck.setEnabled(true);

            cautiousAssassinCheck.setSelected(false);
            cautiousAssassinCheck.setEnabled(true);

            coupLoverCheck.setSelected(false);
            coupLoverCheck.setEnabled(true);

            confirm.setEnabled(false);
        }

       if (actionEvent.getSource() == nerdCheck) {
           count++;
           if (count == 3) {
               nerdCheck.setEnabled(false);
               paranoidCheck.setEnabled(false);
               cautiousAssassinCheck.setEnabled(false);
               coupLoverCheck.setEnabled(false);
               confirm.setEnabled(true);

               player4Type = BotType.Nerd;
           }
           else {
               if (count == 1) player2Type = BotType.Nerd;
               if (count == 2) player3Type = BotType.Nerd;
               nerdCheck.setEnabled(false);
           }
       }

        if (actionEvent.getSource() == paranoidCheck) {
            count++;
            if (count == 3) {
                nerdCheck.setEnabled(false);
                paranoidCheck.setEnabled(false);
                cautiousAssassinCheck.setEnabled(false);
                coupLoverCheck.setEnabled(false);

                player4Type = BotType.Paranoid;
                confirm.setEnabled(true);
            }
            else {
                if (count == 1) player2Type = BotType.Paranoid;
                if (count == 2) player3Type = BotType.Paranoid;
                paranoidCheck.setEnabled(false);
            }
        }

        if (actionEvent.getSource() == cautiousAssassinCheck) {
            count++;
            if (count == 3) {
                nerdCheck.setEnabled(false);
                paranoidCheck.setEnabled(false);
                cautiousAssassinCheck.setEnabled(false);
                coupLoverCheck.setEnabled(false);

                player4Type = BotType.Cautious_Assassin;
                confirm.setEnabled(true);
            }
            else {
                if (count == 1) player2Type = BotType.Cautious_Assassin;
                if (count == 2) player3Type = BotType.Cautious_Assassin;
                cautiousAssassinCheck.setEnabled(false);
            }
        }

        if (actionEvent.getSource() == coupLoverCheck) {
            count++;
            if (count == 3) {
                nerdCheck.setEnabled(false);
                paranoidCheck.setEnabled(false);
                cautiousAssassinCheck.setEnabled(false);
                coupLoverCheck.setEnabled(false);

                player4Type = BotType.Coup_Lover;
                confirm.setEnabled(true);
            }
            else {
                if (count == 1) player2Type = BotType.Coup_Lover;
                if (count == 2) player3Type = BotType.Coup_Lover;
                coupLoverCheck.setEnabled(false);
            }
        }

        if (actionEvent.getSource() == confirm) {
            Vector<Card> cards = Loader.cards();
            for (Card c : cards) {
                Card.Deck.remove(c);
            }

            Human player = new Human(cards.get(0), cards.get(1));
            new HumanSection(cards.get(0), cards.get(1));

            Loader.saveTypes(player2Type, player3Type, player4Type);
            Vector<BotType> types = Loader.types();

            Bot bot2 = new Bot(2, cards.get(2), cards.get(3), types.get(0));
            Bot bot3 = new Bot(3, cards.get(4), cards.get(5), types.get(1));
            Bot bot4 = new Bot(4, cards.get(6), cards.get(7), types.get(2));

            Vector<Bot> bots = new Vector<>();
            bots.add(bot2);
            bots.add(bot3);
            bots.add(bot4);

            BotSection section2 = new BotSection(bot2);
            BotSection section3 = new BotSection(bot3);
            BotSection section4 = new BotSection(bot4);

            Game game = new Game(bots);

            player.start();
            bot2.start();
            bot3.start();
            bot4.start();

            section2.start();
            section3.start();
            section4.start();

            game.start();

            initTextArea();
            initBackground();
            initHumanSection();
            initPlayerTwoSection();
            initPlayerThreeSection();
            initPlayerFourSection();

            label.setVisible(true);
            label1.setVisible(false);

            this.repaint();
            this.revalidate();
        }
    }
}