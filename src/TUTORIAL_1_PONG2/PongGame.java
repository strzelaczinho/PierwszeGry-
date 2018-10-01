/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TUTORIAL_1_PONG2;

import java.applet.AudioClip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PongGame extends JComponent implements ActionListener,
        MouseMotionListener {
    AudioClip dark = JApplet.newAudioClip(getClass().getResource("dark.wav"));
    private Paddle paddle = new Paddle();
    private int levelXSpeed = 5;
    private int levelYSpeed = 7;
    private Ball ball = new Ball(levelXSpeed, levelYSpeed);
    private int hits = 0;
    private int score = 0;
    private int level = 1;
    private Color skyColor = new Color(178, 223, 224);
    private int timeInLevel = 0;
    private String[] levelNames = {
            "Nowy Poczatek",
            "Na drodze przeznaczenia",
            "Podziemny krag",
            "Nadzieja i odrodzenie",
            "Przestrzen cie otacza!",
            "Hala Króla",
            "Przełamanie",
            "Zagubion dusze ",
            "Ponad gwiazdami...",
            "..."
    };
    public PongGame()
    {
        darkplay();
    }
private void darkplay()
    {
        dark.loop();
        dark.play();
    }
    public static void main(String[] args) {
        JFrame window = new JFrame("Pong Game");
        PongGame game = new PongGame();
        window.add(game);
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setResizable(true);

        Timer t = new Timer(20, game);
        t.start();

        window.addMouseMotionListener(game);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    @Override
    protected void paintComponent(Graphics g) {
        drawSky(g);
        drawLevelInfo(g);
        paddle.draw(g);
        ball.draw(g);
        drawScoreboard(g);
    }

    private void drawScoreboard(Graphics g) {
        g.setColor(new Color(204, 54, 34));
        g.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
        g.drawString("Hits: " + hits, 328, 565);
        g.drawString("Score: " + score, 328, 590);
    }

    private void drawLevelInfo(Graphics g) {
        int alpha = Math.max(0, 255 - timeInLevel);
        g.setColor(new Color(204, 54, 34, alpha));
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 80));
        String levelString = "LEVEL " + level;
        int levelStringWidth = g.getFontMetrics().stringWidth(levelString);
        g.drawString(levelString, (800 - levelStringWidth)/2, 252);
        // Draw the level name
        if (level <= levelNames.length) {
            g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 45));
            String levelName = levelNames[level - 1];
            int levelNameWidth = g.getFontMetrics().stringWidth(levelName);
            g.drawString(levelName, (800 - levelNameWidth)/2, 300);
        }
    }

    private void drawSky(Graphics g) {
        g.setColor(skyColor);
        g.fillRect(0, 0, 800, 600);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timeInLevel = timeInLevel + 1;
        ball.x = ball.x + ball.xSpeed;
        ball.y = ball.y + ball.ySpeed;
        if (ball.x + ball.size >= paddle.x
                && ball.x <= paddle.x + paddle.width
                && ball.y + ball.size >= paddle.y
                && ball.y <= paddle.y + paddle.height) {
            ball.ySpeed = -levelYSpeed;
            hits = hits + 1;
            score = score + level;
            if (hits >= 3) {
                // Advance to the next level
                timeInLevel = 0;
                level = level + 1;
                hits = 0;
                levelXSpeed = levelXSpeed + 2;
                levelYSpeed = levelYSpeed + 2;
                paddle.width = paddle.width - 10;
                skyColor = skyColor.darker();
                if (level == 5) {
                    skyColor = new Color(187, 92, 33);
                }
            }
        }
        if (ball.x >= 800 - ball.size) {
            ball.xSpeed = -levelXSpeed;
        }
        if (ball.x <= 0) {
            ball.xSpeed = levelXSpeed;
        }
        if (ball.y <= 0) {
            ball.ySpeed = levelYSpeed;
           
        }
        if (ball.y >= 600)
        {
             System.exit(0);
        }
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        paddle.x = e.getX() - 75;
        repaint();
    }
}