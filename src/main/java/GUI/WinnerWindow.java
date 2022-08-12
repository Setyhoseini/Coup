package GUI;

import javax.swing.*;
import java.awt.*;

public class WinnerWindow {
    public WinnerWindow(String who) {
        switch (who) {
            case "human":
                JLabel label = new JLabel("You Win!");
                label.setFont(new Font("Serif", Font.BOLD, 18));
                JOptionPane.showMessageDialog(null,label,"",1);
                break;
            case "paranoid":
                label = new JLabel("Paranoid Wins!");
                label.setFont(new Font("Serif", Font.BOLD, 18));
                JOptionPane.showMessageDialog(null,label,"",1);
                break;
            case "coup_lover":
                label = new JLabel("Coup Lover Wins!");
                label.setFont(new Font("Serif", Font.BOLD, 18));
                JOptionPane.showMessageDialog(null,label,"",1);
                break;
            case "nerd":
                label = new JLabel("Nerd Wins!");
                label.setFont(new Font("Serif", Font.BOLD, 18));
                JOptionPane.showMessageDialog(null,label,"",1);
                break;
            case "cautious_assassin":
                label = new JLabel("Cautious Assassin Wins!");
                label.setFont(new Font("Serif", Font.BOLD, 18));
                JOptionPane.showMessageDialog(null,label,"",1);
                break;
        }
    }
}
