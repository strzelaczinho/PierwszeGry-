package TUTORIAL_4_BOUNCING_BABIES2;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;


public class BouncingBabies extends JComponent implements ActionListener,KeyListener,MouseListener{
Image babyImage;
Image flameImage;
Image firemanImage;
Image backgroundImage;
private  final Baby[] babies = new Baby[10];
private int firemenY = 450;
private int firemenX = 250;
int timeUntilNextBaby = 50;
int droppedBabies = 0;
int savedBabies = 0;
private int flameX = 30;
private int flameY = 300;
int timeFlamesMove = 50;
Random random = new Random();
AudioClip jump = JApplet.newAudioClip(getClass().getResource("jump.wav"));
AudioClip move = JApplet.newAudioClip(getClass().getResource("move.wav"));
AudioClip loser = JApplet.newAudioClip(getClass().getResource("loser.wav"));
AudioClip back = JApplet.newAudioClip(getClass().getResource("dark.wav"));
    public BouncingBabies() throws IOException 
    {
        babyImage = ImageIO.read(getClass().getResource("baby.png"));
        flameImage = ImageIO.read(getClass().getResource("fire2.png"));
        firemanImage = ImageIO.read(getClass().getResource("fireman4aa.png"));
        backgroundImage = ImageIO.read(getClass().getResource("background.png"));
        
        babies[1] = new Baby();
       
        back.play();
       
    }
    public static void main(String[] args) throws IOException {
        
        JFrame window = new JFrame("Babies Revenge");
        BouncingBabies game = new BouncingBabies();
        window.add(game);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.addMouseListener(game);
        window.setResizable(false);
        Timer t  = new Timer(40,game);
        t.start();
        window.addKeyListener(game);
       
    }
    @Override
    protected void paintComponent(Graphics g) {
      
     
        
        
      g.drawImage(backgroundImage,0,0,null);
      g.drawImage(firemanImage, firemenX, firemenY, null);
      for (int i = 0;i< 10;i++)
      {
          if (babies[i] == null)
          {
              continue;
          }
      g.drawImage(babyImage, babies[i].getBabyX(), babies[i].getBabyY(),babies[i].getBabyWidth(),babies[i].getBabyWidth(),null);// powiekszam sobie zdjecie z 25 pikseli do 50 pikseli
       }
     
     
        g.setColor(Color.WHITE);
        g.drawString("Saved: "+ savedBabies,500,20);
        g.drawString("Dropped: "+ droppedBabies,500, 40);
       
        g.drawImage(flameImage, flameX , flameY,100,100, null);// zmienilem sobie piksele na 100 bo nie chcialo mi ie w programie 
  
    }
    @Override
    public Dimension getPreferredSize() {
       return new Dimension(800,600);
    }
   
     
    public void actionPerformed(ActionEvent e) {
        if (timeFlamesMove < 0)
        {
              flameX = random.nextInt(115);
              flameY = random.nextInt(500);
              timeFlamesMove = 20;
        }
        else
        {
            timeFlamesMove--;
        }
       
         if (timeUntilNextBaby <=0)
         {
             dzieckoWracaNaStart();
             timeUntilNextBaby = random.nextInt(75)+10; // nowe dziecko every 50 frames liczac ze kazdy frame to 30 milisekund
         }
         else
         {
             timeUntilNextBaby = timeUntilNextBaby -1;
         }
        for (int i = 0;i<10;i++)
        {
         
            if (babies[i] == null)
            {
                continue;
            }
             babies[i].setBabyX(babies[i].getBabyX()+3);
             babies[i].setBabyY (babies[i].getBabyY() + babies[i].getBabyYspeed());
             babies[i].setBabyYspeed( babies[i].getBabyYspeed()+ 1); 
             if(babies[i].getBabyY() > 500) 
                 {
                 if (babies[i].getBabyX() >= firemenX && babies[i].getBabyX() <= firemenX+100 )
                 {
                     
                     makeTheBabyBounce(i);
                     jump.play();
                 
                 }
                 else
                 {
                    droppedBabies = droppedBabies + 1;
                     
                     babies[i] = null;
                 }
              }else if (babies[i].getBabyX() > 650 && babies[i].getBabyY() >= 460)
              {
                  savedBabies = savedBabies + 1;
                  babies[i] = null;
                  loser.play();
              }
             
        }
         repaint();
     
    }
    private final void dzieckoWracaNaStart() { // rysuje dziecko na mapie 
                 for (int i = 0;i<10;i++)
                 {
                     if(babies[i] == null)
                     {
                        babies[i] = new Baby();
                     break;
                     }
                     
                 }
 
    }
    private void makeTheBabyBounce(int i) {
            if (babies[i] != null)
            {
              babies[i].setBabyYspeed( (int) (-0.9 * Math.abs(babies[i].getBabyYspeed()))); // na wszelki wypadek jest abs zeby dziecko nie leciało jak walniete za szybko
            }     
            
      
    }
    @Override
    public void keyTyped(KeyEvent e) 
    {
      
    }
    @Override
    public void keyPressed(KeyEvent e) {
     if (e.getKeyCode() == KeyEvent.VK_LEFT)
     {
         if (firemenX <= 150)
         {
             firemenX = 150;
         }
         else
         {
             move.play();
         firemenX -= 100;
         }
     }
     if (e.getKeyCode() == KeyEvent.VK_RIGHT)
     {
         if (firemenX >= 500)
         {
             firemenX = 500;
             
         }
         else 
         {
             move.play();
              firemenX += 100;
         }
     }
     repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
// do nasluchiwania punktow
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("x = "+e.getX()+" y = "+e.getY());
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
