package TUTORIAL_2_SCARY_MAZE;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.applet.AudioClip;
import java.awt.event.MouseListener;
import javax.imageio.ImageIO;

public  class ScaryMazeGame extends JComponent implements MouseMotionListener,MouseListener{
    BufferedImage intro;
    BufferedImage level1;
    BufferedImage level2;
    BufferedImage level3;
    BufferedImage gameOver;
    BufferedImage currentLevel;
    BufferedImage win;
     AudioClip scream = JApplet.newAudioClip(getClass().getResource("scream.aiff"));
     AudioClip dark = JApplet.newAudioClip(getClass().getResource("dark.wav"));
    public ScaryMazeGame() throws IOException
    {
     intro = ImageIO.read(getClass().getResource("Intro.png"));
     level1 = ImageIO.read(getClass().getResource("Level 1.png"));
     level2 = ImageIO.read(getClass().getResource("Level 2.png"));
     level3 = ImageIO.read(getClass().getResource("Level 3.png"));
     gameOver = ImageIO.read(getClass().getResource("Game Over.jpg"));
     win = ImageIO.read(getClass().getResource("win.png"));
     currentLevel = intro;
    darkplay(); 
    
    }

  public static void main(String[] args) throws IOException {
        JFrame window = new JFrame("Maze Game by Adam");
        ScaryMazeGame game = new ScaryMazeGame();
        
        window.add(game);
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setResizable(false);
        game.addMouseMotionListener(game);//mowi javie ze gra sama w sobie da nam informacje o ruchu myszki we wslasnej grze
        game.addMouseListener(game);
           
    }
     public Dimension getPreferredSize() // klasa JComponent odpowiada za rysowanie tych obiektow
    {
        return new Dimension(800,600);
    }
     protected void paintComponent(Graphics g)
     {
         g.setColor(Color.RED);
         g.fillRect(0,0,800,600);
          
         g.drawImage(currentLevel, 0, 0, null);
     }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // sprawdza kolor pikseli nad ktorm jest myszka oraz przechodzi do nastenego levelu
        
        int  x = e.getX(); // pobiera ruch myszki , aktualne miejsce gdzie sieznajduje
        int  y = e.getY();
        
        int rgb = currentLevel.getRGB(x, y); // pobiera kolor gdzie myszka sie znajduje 
         int level1WallColor = -4758784;
        int level2WallColor = -9628998;
        int level3WallColor = -7272684;
        int goalColor = -15549808;
       System.out.println(rgb);
        if (rgb ==  -15549808 )
        {
            if (currentLevel == intro)
            {
            currentLevel = level1;
            
             
            }
            else if (currentLevel == level1)
            {
                currentLevel = level2;
                
            }
            else if (currentLevel == level2)
            {
                currentLevel = level3;
            }
            else if (currentLevel == level3)
            {
                currentLevel = win;
            }
        }
         if (rgb == level1WallColor) {
            currentLevel = intro;
        }
         if (rgb == level2WallColor) {
          showGameOver();
        
        }
         if (rgb == level3WallColor) {
            showGameOver();
            
        }
       repaint();
      
        
    }
    private void darkplay()
    {
        dark.loop();
        dark.play();
    }
    private void showGameOver() {
        scream.play();
        currentLevel = gameOver;
    }
    
     public void mouseClicked(MouseEvent e) {
        if (currentLevel == gameOver) {
            currentLevel = intro;
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
