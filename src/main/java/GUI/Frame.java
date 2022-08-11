package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener {
    ImageIcon icon2 = new ImageIcon("sum.png");
    ImageIcon icon3 = new ImageIcon("test2.png");
    ImageIcon icon7 = new ImageIcon("coin.png");
    ImageIcon icon = new ImageIcon("bg.png");
    public static JLabel label = new JLabel();
    public static JTextArea text = new JTextArea();

    public Frame() {
        this.setSize(1800, 1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        initBackground();
        initHumanSection();
        initPlayerTwoSection();
        initPlayerThreeSection();
        initPlayerFourSection();
        initTextArea();
        this.repaint();
        this.revalidate();
    }

    public void initBackground() {
        label.setBounds(0, 0, 1800, 1000);
        label.setIcon(icon);
        label.setVisible(true);
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
        ImageIcon actions = new ImageIcon("actions.png");
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




     //   text.append("Player " + 4 + ":      " + "Assassinate" + " \u2192" + " Player " + 4 + "\n");
      //  text.append("------------------------------------------------" + "\n");
      //  text.append("Player 4: Assassinate " + "\u2192" + " Player 3" + "\n");

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

    }
}