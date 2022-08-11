package GUI;

import Logic.Game.ActionName;
import Logic.Game.Controller;
import Logic.Game.Game;
import Logic.Player.Bot;
import Logic.Player.BotType;
import Logic.Player.Human;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

public class BotSection extends Thread implements ActionListener {
    public Controller controller = Controller.Neutral;
    public AtomicBoolean running = new AtomicBoolean(true);

    Bot bot;
    JLabel card1 = new JLabel();
    JLabel card2 = new JLabel();
    JLabel num = new JLabel();
    JButton coup;
    JButton steal;
    JButton assassinate;
    JButton challenge;
    JButton block;

    JLabel assassinateDecision1 = new JLabel();
    JLabel assassinateDecision2 = new JLabel();
    JLabel assassinateDecision3 = new JLabel();
    JLabel assassinateDecision4 = new JLabel();

    JLabel stealDecision1 = new JLabel();
    JLabel stealDecision2 = new JLabel();
    JLabel stealDecision3 = new JLabel();
    JLabel stealDecision4 = new JLabel();

    JLabel coupDecision1 = new JLabel();
    JLabel coupDecision2 = new JLabel();
    JLabel coupDecision3 = new JLabel();
    JLabel coupDecision4 = new JLabel();

    JLabel blockDecision = new JLabel();
    JLabel challengeDecision = new JLabel();
    JLabel incomeDecision = new JLabel();
    JLabel foreignAidDecision = new JLabel();
    JLabel taxDecision = new JLabel();
    JLabel exchangeOneDecision = new JLabel();
    JLabel exchangeTwoDecision = new JLabel();
    JLabel loseChallenge = new JLabel();
    JLabel winChallenge = new JLabel();

    JLabel thinkingLabel = new JLabel();
    ImageIcon loading = new ImageIcon("thinking6.gif");

    ImageIcon assassinate1 = new ImageIcon("assassinate1.png");
    ImageIcon assassinate2 = new ImageIcon("assassinate2.png");
    ImageIcon assassinate3 = new ImageIcon("assassinate3.png");
    ImageIcon assassinate4 = new ImageIcon("assassinate4.png");

    ImageIcon coup1 = new ImageIcon("coup1.png");
    ImageIcon coup2 = new ImageIcon("coup2.png");
    ImageIcon coup3 = new ImageIcon("coup3.png");
    ImageIcon coup4 = new ImageIcon("coup4.png");

    ImageIcon steal1 = new ImageIcon("steal1.png");
    ImageIcon steal2 = new ImageIcon("steal2.png");
    ImageIcon steal3 = new ImageIcon("steal3.png");
    ImageIcon steal4 = new ImageIcon("steal4.png");

    ImageIcon blockLabel = new ImageIcon("block.png");
    ImageIcon challengeLabel = new ImageIcon("challenge.png");
    ImageIcon exchangeTwoLabel = new ImageIcon("exchangeTwo.png");
    ImageIcon exchangeOneLabel = new ImageIcon("exchangeOne.png");
    ImageIcon taxLabel = new ImageIcon("tax.png");
    ImageIcon incomeLabel = new ImageIcon("income.png");
    ImageIcon foreignAidLabel = new ImageIcon("foreignAid.png");
    ImageIcon winChallengeLabel = new ImageIcon("winChallenge.png");
    ImageIcon loseChallengeLabel = new ImageIcon("loseChallenge.png");

    public void disableAll() {
        coup.setEnabled(false);
        steal.setEnabled(false);
        challenge.setEnabled(false);
        block.setEnabled(false);
        assassinate.setEnabled(false);
    }

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
        initStateLabels(bot.getNum());
        bot.setSection(this);
    }

    public void initStateLabels(int id) {
        initThinkingLabels(id);
        initWinChallengeLabels(id);
        initLoseChallengeLabels(id);
        initExchangeTwoDecisionLabels(id);
        initExchangeOneDecisionLabels(id);
        initTaxDecisionLabels(id);
        initAssassinateDecision1Labels(id);
        initAssassinateDecision2Labels(id);
        initAssassinateDecision3Labels(id);
        initAssassinateDecision4Labels(id);
        initStealDecision1Labels(id);
        initStealDecision2Labels(id);
        initStealDecision3Labels(id);
        initStealDecision4Labels(id);
        initIncomeDecisionLabels(id);
        initForeignAidDecisionLabels(id);
        initBlockDecisionLabels(id);
        initChallengeDecisionLabels(id);
        initCoupDecision1Labels(id);
        initCoupDecision2Labels(id);
        initCoupDecision3Labels(id);
        initCoupDecision4Labels(id);
    }

    public void initWinChallengeLabels(int id) {
        switch (id) {
            case 2:
                winChallenge.setBounds(1263, 62, 380, 600);
                winChallenge.setIcon(winChallengeLabel);
                winChallenge.setVisible(false);
                Frame.label.add(winChallenge);
                break;
            case 3:
                winChallenge.setBounds(1263, 522, 380, 600);
                winChallenge.setIcon(winChallengeLabel);
                winChallenge.setVisible(false);
                Frame.label.add(winChallenge);
                break;
            case 4:
                winChallenge.setBounds(663, 522, 380, 600);
                winChallenge.setIcon(winChallengeLabel);
                winChallenge.setVisible(false);
                Frame.label.add(winChallenge);
                break;
        }
    }

    public void initLoseChallengeLabels(int id) {
        switch (id) {
            case 2:
                loseChallenge.setBounds(1263, 62, 380, 600);
                loseChallenge.setIcon(loseChallengeLabel);
                loseChallenge.setVisible(false);
                Frame.label.add(loseChallenge);
                break;
            case 3:
                loseChallenge.setBounds(1263, 522, 380, 600);
                loseChallenge.setIcon(loseChallengeLabel);
                loseChallenge.setVisible(false);
                Frame.label.add(loseChallenge);
                break;
            case 4:
                loseChallenge.setBounds(663, 522, 380, 600);
                loseChallenge.setIcon(loseChallengeLabel);
                loseChallenge.setVisible(false);
                Frame.label.add(loseChallenge);
                break;
        }
    }

    public void initExchangeTwoDecisionLabels(int id) {
        switch (id) {
            case 2:
                exchangeTwoDecision.setBounds(1223, 62, 380, 600);
                exchangeTwoDecision.setIcon(exchangeTwoLabel);
                exchangeTwoDecision.setVisible(false);
                Frame.label.add(exchangeTwoDecision);
                break;
            case 3:
                exchangeTwoDecision.setBounds(1223, 522, 380, 600);
                exchangeTwoDecision.setIcon(exchangeTwoLabel);
                exchangeTwoDecision.setVisible(false);
                Frame.label.add(exchangeTwoDecision);
                break;
            case 4:
                exchangeTwoDecision.setBounds(623, 522, 380, 600);
                exchangeTwoDecision.setIcon(exchangeTwoLabel);
                exchangeTwoDecision.setVisible(false);
                Frame.label.add(exchangeTwoDecision);
                break;
        }
    }

    public void initExchangeOneDecisionLabels(int id) {
        switch (id) {
            case 2:
                exchangeOneDecision.setBounds(1253, 62, 380, 600);
                exchangeOneDecision.setIcon(exchangeOneLabel);
                exchangeOneDecision.setVisible(false);
                Frame.label.add(exchangeOneDecision);
                break;
            case 3:
                exchangeOneDecision.setBounds(1253, 522, 380, 600);
                exchangeOneDecision.setIcon(exchangeOneLabel);
                exchangeOneDecision.setVisible(false);
                Frame.label.add(exchangeOneDecision);
                break;
            case 4:
                exchangeOneDecision.setBounds(653, 522, 380, 600);
                exchangeOneDecision.setIcon(exchangeOneLabel);
                exchangeOneDecision.setVisible(false);
                Frame.label.add(exchangeOneDecision);
                break;
        }
    }

    public void initTaxDecisionLabels(int id) {
        switch (id) {
            case 2:
                taxDecision.setBounds(1253, 62, 380, 600);
                taxDecision.setIcon(taxLabel);
                taxDecision.setVisible(false);
                Frame.label.add(taxDecision);
                break;
            case 3:
                taxDecision.setBounds(1253, 522, 380, 600);
                taxDecision.setIcon(taxLabel);
                taxDecision.setVisible(false);
                Frame.label.add(taxDecision);
                break;
            case 4:
                taxDecision.setBounds(653, 522, 380, 600);
                taxDecision.setIcon(taxLabel);
                taxDecision.setVisible(false);
                Frame.label.add(taxDecision);
                break;
        }
    }

    public void initForeignAidDecisionLabels(int id) {
        switch (id) {
            case 2:
                foreignAidDecision.setBounds(1213, 62, 380, 600);
                foreignAidDecision.setIcon(foreignAidLabel);
                foreignAidDecision.setVisible(false);
                Frame.label.add(foreignAidDecision);
                break;
            case 3:
                foreignAidDecision.setBounds(1213, 522, 380, 600);
                foreignAidDecision.setIcon(foreignAidLabel);
                foreignAidDecision.setVisible(false);
                Frame.label.add(foreignAidDecision);
                break;
            case 4:
                foreignAidDecision.setBounds(613, 522, 380, 600);
                foreignAidDecision.setIcon(foreignAidLabel);
                foreignAidDecision.setVisible(false);
                Frame.label.add(foreignAidDecision);
                break;
        }
    }

    public void initIncomeDecisionLabels(int id) {
        switch (id) {
            case 2:
                incomeDecision.setBounds(1278, 62, 380, 600);
                incomeDecision.setIcon(incomeLabel);
                incomeDecision.setVisible(false);
                Frame.label.add(incomeDecision);
                break;
            case 3:
                incomeDecision.setBounds(1278, 522, 380, 600);
                incomeDecision.setIcon(incomeLabel);
                incomeDecision.setVisible(false);
                Frame.label.add(incomeDecision);
                break;
            case 4:
                incomeDecision.setBounds(678, 522, 380, 600);
                incomeDecision.setIcon(incomeLabel);
                incomeDecision.setVisible(false);
                Frame.label.add(incomeDecision);
                break;
        }
    }

    public void initChallengeDecisionLabels(int id) {
        switch (id) {
            case 2:
                challengeDecision.setBounds(1299, 62, 380, 600);
                challengeDecision.setIcon(challengeLabel);
                challengeDecision.setVisible(false);
                Frame.label.add(challengeDecision);
                break;
            case 3:
                challengeDecision.setBounds(1299, 522, 380, 600);
                challengeDecision.setIcon(challengeLabel);
                challengeDecision.setVisible(false);
                Frame.label.add(challengeDecision);
                break;
            case 4:
                challengeDecision.setBounds(699, 522, 380, 600);
                challengeDecision.setIcon(challengeLabel);
                challengeDecision.setVisible(false);
                Frame.label.add(challengeDecision);
                break;
        }
    }

    public void initBlockDecisionLabels(int id) {
        switch (id) {
            case 2:
                blockDecision.setBounds(1268, 62, 380, 600);
                blockDecision.setIcon(blockLabel);
                blockDecision.setVisible(false);
                Frame.label.add(blockDecision);
                break;
            case 3:
                blockDecision.setBounds(1268, 522, 380, 600);
                blockDecision.setIcon(blockLabel);
                blockDecision.setVisible(false);
                Frame.label.add(blockDecision);
                break;
            case 4:
                blockDecision.setBounds(668, 522, 380, 600);
                blockDecision.setIcon(blockLabel);
                blockDecision.setVisible(false);
                Frame.label.add(blockDecision);
                break;
        }
    }

    public void initCoupDecision1Labels(int id) {
        switch (id) {
            case 2:
                coupDecision1.setBounds(1180, 62, 400, 600);
                coupDecision1.setIcon(coup1);
                coupDecision1.setVisible(false);
                Frame.label.add(coupDecision1);
                break;
            case 3:
                coupDecision1.setBounds(1180, 522, 400, 600);
                coupDecision1.setIcon(coup1);
                coupDecision1.setVisible(false);
                Frame.label.add(coupDecision1);
                break;
            case 4:
                coupDecision1.setBounds(580, 522, 400, 600);
                coupDecision1.setIcon(coup1);
                coupDecision1.setVisible(false);
                Frame.label.add(coupDecision1);
                break;
        }
    }

    public void initCoupDecision2Labels(int id) {
        switch (id) {
            case 2:
                coupDecision2.setBounds(1180, 67, 400, 600);
                coupDecision2.setIcon(coup2);
                coupDecision2.setVisible(false);
                Frame.label.add(coupDecision2);
                break;
            case 3:
                coupDecision2.setBounds(1180, 527, 400, 600);
                coupDecision2.setIcon(coup2);
                coupDecision2.setVisible(false);
                Frame.label.add(coupDecision2);
                break;
            case 4:
                coupDecision2.setBounds(580, 527, 400, 600);
                coupDecision2.setIcon(coup2);
                coupDecision2.setVisible(false);
                Frame.label.add(coupDecision2);
                break;
        }
    }

    public void initCoupDecision3Labels(int id) {
        switch (id) {
            case 2:
                coupDecision3.setBounds(1180, 67, 410, 600);
                coupDecision3.setIcon(coup3);
                coupDecision3.setVisible(false);
                Frame.label.add(coupDecision3);
                break;
            case 3:
                coupDecision3.setBounds(1180, 527, 410, 600);
                coupDecision3.setIcon(coup3);
                coupDecision3.setVisible(false);
                Frame.label.add(coupDecision3);
                break;
            case 4:
                coupDecision3.setBounds(580, 527, 410, 600);
                coupDecision3.setIcon(coup3);
                coupDecision3.setVisible(false);
                Frame.label.add(coupDecision3);
                break;
        }
    }

    public void initCoupDecision4Labels(int id) {
        switch (id) {
            case 2:
                coupDecision4.setBounds(1180, 67, 422, 600);
                coupDecision4.setIcon(coup4);
                coupDecision4.setVisible(false);
                Frame.label.add(coupDecision4);
                break;
            case 3:
                coupDecision4.setBounds(1180, 527, 422, 600);
                coupDecision4.setIcon(coup4);
                coupDecision4.setVisible(false);
                Frame.label.add(coupDecision4);
                break;
            case 4:
                coupDecision4.setBounds(580, 527, 422, 600);
                coupDecision4.setIcon(coup4);
                coupDecision4.setVisible(false);
                Frame.label.add(coupDecision4);
                break;
        }
    }

    public void initStealDecision4Labels(int id) {
        switch (id) {
            case 2:
                stealDecision4.setBounds(1190, 62, 380, 600);
                stealDecision4.setIcon(steal4);
                stealDecision4.setVisible(false);
                Frame.label.add(stealDecision4);
                break;
            case 3:
                stealDecision4.setBounds(1190, 522, 380, 600);
                stealDecision4.setIcon(steal4);
                stealDecision4.setVisible(false);
                Frame.label.add(stealDecision4);
                break;
            case 4:
                stealDecision4.setBounds(590, 522, 380, 600);
                stealDecision4.setIcon(steal4);
                stealDecision4.setVisible(false);
                Frame.label.add(stealDecision4);
                break;
        }
    }

    public void initStealDecision3Labels(int id) {
        switch (id) {
            case 2:
                stealDecision3.setBounds(1190, 62, 380, 600);
                stealDecision3.setIcon(steal3);
                stealDecision3.setVisible(false);
                Frame.label.add(stealDecision3);
                break;
            case 3:
                stealDecision3.setBounds(1190, 522, 380, 600);
                stealDecision3.setIcon(steal3);
                stealDecision3.setVisible(false);
                Frame.label.add(stealDecision3);
                break;
            case 4:
                stealDecision3.setBounds(590, 522, 380, 600);
                stealDecision3.setIcon(steal3);
                stealDecision3.setVisible(false);
                Frame.label.add(stealDecision3);
                break;
        }
    }

    public void initStealDecision2Labels(int id) {
        switch (id) {
            case 2:
                stealDecision2.setBounds(1190, 62, 380, 600);
                stealDecision2.setIcon(steal2);
                stealDecision2.setVisible(false);
                Frame.label.add(stealDecision2);
                break;
            case 3:
                stealDecision2.setBounds(1190, 522, 380, 600);
                stealDecision2.setIcon(steal2);
                stealDecision2.setVisible(false);
                Frame.label.add(stealDecision2);
                break;
            case 4:
                stealDecision2.setBounds(590, 522, 380, 600);
                stealDecision2.setIcon(steal2);
                stealDecision2.setVisible(false);
                Frame.label.add(stealDecision2);
                break;
        }
    }

    public void initStealDecision1Labels(int id) {
        switch (id) {
            case 2:
                stealDecision1.setBounds(1190, 62, 380, 600);
                stealDecision1.setIcon(steal1);
                stealDecision1.setVisible(false);
                Frame.label.add(stealDecision1);
                break;
            case 3:
                stealDecision1.setBounds(1190, 522, 380, 600);
                stealDecision1.setIcon(steal1);
                stealDecision1.setVisible(false);
                Frame.label.add(stealDecision1);
                break;
            case 4:
                stealDecision1.setBounds(590, 522, 380, 600);
                stealDecision1.setIcon(steal1);
                stealDecision1.setVisible(false);
                Frame.label.add(stealDecision1);
                break;
        }
    }

    public void initAssassinateDecision4Labels(int id) {
        switch (id) {
            case 2:
                assassinateDecision4.setBounds(1180, 62, 380, 600);
                assassinateDecision4.setIcon(assassinate4);
                assassinateDecision4.setVisible(false);
                Frame.label.add(assassinateDecision4);
                break;
            case 3:
                assassinateDecision4.setBounds(1180, 522, 380, 600);
                assassinateDecision4.setIcon(assassinate4);
                assassinateDecision4.setVisible(false);
                Frame.label.add(assassinateDecision4);
                break;
            case 4:
                assassinateDecision4.setBounds(580, 522, 380, 600);
                assassinateDecision4.setIcon(assassinate4);
                assassinateDecision4.setVisible(false);
                Frame.label.add(assassinateDecision4);
                break;
        }
    }

    public void initAssassinateDecision3Labels(int id) {
        switch (id) {
            case 2:
                assassinateDecision3.setBounds(1180, 62, 380, 600);
                assassinateDecision3.setIcon(assassinate3);
                assassinateDecision3.setVisible(false);
                Frame.label.add(assassinateDecision3);
                break;
            case 3:
                assassinateDecision3.setBounds(1180, 522, 380, 600);
                assassinateDecision3.setIcon(assassinate3);
                assassinateDecision3.setVisible(false);
                Frame.label.add(assassinateDecision3);
                break;
            case 4:
                assassinateDecision3.setBounds(580, 522, 380, 600);
                assassinateDecision3.setIcon(assassinate3);
                assassinateDecision3.setVisible(false);
                Frame.label.add(assassinateDecision3);
                break;
        }
    }

    public void initAssassinateDecision2Labels(int id) {
        switch (id) {
            case 2:
                assassinateDecision2.setBounds(1180, 62, 380, 600);
                assassinateDecision2.setIcon(assassinate2);
                assassinateDecision2.setVisible(false);
                Frame.label.add(assassinateDecision2);
                break;
            case 3:
                assassinateDecision2.setBounds(1180, 522, 380, 600);
                assassinateDecision2.setIcon(assassinate2);
                assassinateDecision2.setVisible(false);
                Frame.label.add(assassinateDecision2);
                break;
            case 4:
                assassinateDecision2.setBounds(580, 522, 380, 600);
                assassinateDecision2.setIcon(assassinate2);
                assassinateDecision2.setVisible(false);
                Frame.label.add(assassinateDecision2);
                break;
        }
    }

    public void initAssassinateDecision1Labels(int id) {
        switch (id) {
            case 2:
                assassinateDecision1.setBounds(1180, 62, 380, 600);
                assassinateDecision1.setIcon(assassinate1);
                assassinateDecision1.setVisible(false);
                Frame.label.add(assassinateDecision1);
                break;
            case 3:
                assassinateDecision1.setBounds(1180, 522, 380, 600);
                assassinateDecision1.setIcon(assassinate1);
                assassinateDecision1.setVisible(false);
                Frame.label.add(assassinateDecision1);
                break;
            case 4:
                assassinateDecision1.setBounds(580, 522, 380, 600);
                assassinateDecision1.setIcon(assassinate1);
                assassinateDecision1.setVisible(false);
                Frame.label.add(assassinateDecision1);
                break;
        }
    }

    public void initThinkingLabels(int id) {
        switch (id) {
            case 2:
                thinkingLabel.setBounds(1293, 62, 380, 600);
                thinkingLabel.setIcon(loading);
                thinkingLabel.setVisible(false);
                Frame.label.add(thinkingLabel);
                break;
            case 3:
                thinkingLabel.setBounds(1293, 522, 380, 600);
                thinkingLabel.setIcon(loading);
                thinkingLabel.setVisible(false);
                Frame.label.add(thinkingLabel);
                break;
            case 4:
                thinkingLabel.setBounds(693, 522, 380, 600);
                thinkingLabel.setIcon(loading);
                thinkingLabel.setVisible(false);
                Frame.label.add(thinkingLabel);
                break;
        }
    }

    public void revealACard() {
        if (card1 != null || card2 != null) {
            if (bot.getCard1() != null && bot.getCard2() == null) {
                card1.setIcon(bot.getCard1().getDeadImage());
                bot.setCard1(null);
            }
            else if (bot.getCard2() != null && bot.getCard1() == null){
                card2.setIcon(bot.getCard2().getDeadImage());
                bot.setCard2(null);
            }
            else {
                ArrayList<Card> cards = new ArrayList<>();
                cards.add(bot.getCard1());
                cards.add(bot.getCard2());
                Collections.shuffle(cards);
                if (cards.get(0).equals(bot.getCard1())) {
                    card1.setIcon(bot.getCard1().getDeadImage());
                    bot.setCard1(null);
                }
                else {
                    card2.setIcon(bot.getCard2().getDeadImage());
                    bot.setCard2(null);
                }
            }
            if (bot.card1 == null && bot.card2 == null) {
                if (bot.getRole() == BotType.Paranoid) Bot.paranoidIsPlaying.set(false);
                Game.players.remove(bot.getNum());
            }
        }
    }

    public void revealACard(int card) {
        if (card == 1) {
            card1.setIcon(bot.getCard1().getImage());
        }
        else {
            card2.setIcon(bot.getCard2().getImage());
        }
    }

    public void assassinateACard(int card) {
        if (card1 != null || card2 != null) {
            if (card == 1) {
                assert card1 != null;
                card1.setIcon(bot.getCard1().getDeadImage());
                bot.setCard1(null);
            }
            else {
                card2.setIcon(bot.getCard2().getDeadImage());
                bot.setCard2(null);
            }
            if (bot.card1 == null && bot.card2 == null) {
                if (bot.getRole() == BotType.Paranoid) Bot.paranoidIsPlaying.set(false);
                Game.players.remove(bot.getNum());
            }
        }
    }

    public void replaceACard(int card) {
        if (card == 1) {
            Card current = bot.card1;
            Collections.shuffle(Card.Deck);
            bot.card1 = Card.Deck.remove(0);
            Card.Deck.add(current);
            card1.setIcon(Card.Back.getImage());
        }
        else {
            Card current = bot.card2;
            Collections.shuffle(Card.Deck);
            bot.card2 = Card.Deck.remove(0);
            Card.Deck.add(current);
            card2.setIcon(Card.Back.getImage());
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
        if (actionEvent.getSource() == coup) {
           switch (bot.getNum()) {
               case 2:
                   Human.lastAction = ActionName.Coup_On_2;
                   break;
               case 3:
                   Human.lastAction = ActionName.Coup_On_3;
                   break;
               case 4:
                   Human.lastAction = ActionName.Coup_On_4;
                   break;
           }
        }

        if (actionEvent.getSource() == steal) {
            switch (bot.getNum()) {
                case 2:
                    Human.lastAction = ActionName.Steal_From_2;
                    break;
                case 3:
                    Human.lastAction = ActionName.Steal_From_3;
                    break;
                case 4:
                    Human.lastAction = ActionName.Steal_From_4;
                    break;
            }
        }

        if (actionEvent.getSource() == challenge) {
            Human.lastAction = ActionName.Challenge;
        }

        if (actionEvent.getSource() == assassinate) {
            switch (bot.getNum()) {
                case 2:
                    Human.lastAction = ActionName.Assassinate_2;
                    break;
                case 3:
                    Human.lastAction = ActionName.Assassinate_3;
                    break;
                case 4:
                    Human.lastAction = ActionName.Assassinate_4;
                    break;
            }
        }

        if (actionEvent.getSource() == block) {
           Human.lastAction = ActionName.Block;
        }
    }

    public void neutralState() {
        assassinateDecision1.setVisible(false);
        assassinateDecision2.setVisible(false);
        assassinateDecision3.setVisible(false);
        assassinateDecision4.setVisible(false);
        stealDecision1.setVisible(false);
        stealDecision2.setVisible(false);
        stealDecision3.setVisible(false);
        stealDecision4.setVisible(false);
        coupDecision1.setVisible(false);
        coupDecision2.setVisible(false);
        coupDecision3.setVisible(false);
        coupDecision4.setVisible(false);
        blockDecision.setVisible(false);
        challengeDecision.setVisible(false);
        incomeDecision.setVisible(false);
        foreignAidDecision.setVisible(false);
        taxDecision.setVisible(false);
        exchangeOneDecision.setVisible(false);
        exchangeTwoDecision.setVisible(false);
        winChallenge.setVisible(false);
        loseChallenge.setVisible(false);
        thinkingLabel.setVisible(false);
    }

    @Override
    public void run() {
        while (running.get()) {
            switch (controller) {
                case Is_Thinking:
                    thinkingLabel.setVisible(true);
                    break;
                case Neutral:
                    neutralState();
                    break;
                case Challenges:
                    challengeDecision.setVisible(true);
                    break;
                case Blocks:
                    blockDecision.setVisible(true);
                    break;
                case Lost_Challenge:
                    loseChallenge.setVisible(true);
                    break;
                case Won_Challenge:
                    winChallenge.setVisible(true);
                    break;
                case Foreign_Aid:
                    foreignAidDecision.setVisible(true);
                    break;
                case Tax:
                    taxDecision.setVisible(true);
                    break;
                case Exchange_Cards:
                    exchangeTwoDecision.setVisible(true);
                    break;
                case Wants_To_Assassinate_Player1:
                    assassinateDecision1.setVisible(true);
                    break;
                case Wants_To_Assassinate_Player2:
                    assassinateDecision2.setVisible(true);
                    break;
                case Wants_To_Assassinate_Player3:
                    assassinateDecision3.setVisible(true);
                    break;
                case Wants_To_Assassinate_Player4:
                    assassinateDecision4.setVisible(true);
                    break;
                case Wants_To_Steal_From_Player1:
                    stealDecision1.setVisible(true);
                    break;
                case Wants_To_Steal_From_Player2:
                    stealDecision2.setVisible(true);
                    break;
                case Wants_To_Steal_From_Player3:
                    stealDecision3.setVisible(true);
                    break;
                case Wants_To_Steal_From_Player4:
                    stealDecision4.setVisible(true);
                    break;
                case Launches_Coup_Against_1:
                    coupDecision1.setVisible(true);
                    break;
                case Launches_Coup_Against_2:
                    coupDecision2.setVisible(true);
                    break;
                case Launches_Coup_Against_3:
                    coupDecision3.setVisible(true);
                    break;
                case Launches_Coup_Against_4:
                    coupDecision4.setVisible(true);
                    break;
                case Exchange_One_Card:
                    exchangeOneDecision.setVisible(true);
                    break;
                case Income:
                    incomeDecision.setVisible(true);
                    break;
            }
        }
    }
}