package TUTORIAL_5_THROW_A_TURKEY;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class RzutKurczakiem extends JComponent implements ActionListener,MouseListener{  
    Image yakRunning1;
    Image yakRunning2;    
    Image footballImage;
    Image yakKicking;
    Image background;
    private int yakX= 20;
    private int yakY = 400;
    private int yakSpeed = 12;
    private boolean yakRunning;
    private boolean footballFlying = false;
    boolean muzyka = true;
    
    double footballXSpeed = 15;
    double footballYSpeed = -50;
    int scrollPosition = 0;
    private double footballScale =1;
    private int footballRotation = 0;
    
    private int  distanceFromFootbal;
    AudioClip   jump = JApplet.newAudioClip(getClass().getResource("jump.wav"));
    AudioClip   bull = JApplet.newAudioClip(getClass().getResource("bull.wav"));
    private int footballX = 600;
    private int footballY = 530;
    public RzutKurczakiem() throws IOException {
        yakRunning1 = ImageIO.read(getClass().getResource("Bull_Run1.png"));
        yakRunning2 = ImageIO.read(getClass().getResource("Bull_Run2.png"));
        footballImage = ImageIO.read(getClass().getResource("turkey.png"));
        yakKicking  = ImageIO.read(getClass().getResource("Bull_Run3.png"));
        background = ImageIO.read(getClass().getResource("background.png"));
       
    }
     
    public static void main(String[] args) throws IOException {
        
        JFrame window = new JFrame();
        RzutKurczakiem game = new RzutKurczakiem();
        window.add(game);
        window.pack();
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Timer t = new Timer(30,game); // co 30 milisekund wykonywana ejst animacja
        t.start();
        
        window.addMouseListener(game); 
         
}
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800,600);
    }
    @Override
    public void paintComponent(Graphics g) {
     Graphics2D g2 = (Graphics2D) g;
     g.drawImage(background,0-scrollPosition,0,null);
      if (footballFlying )
      {
         
          g.drawImage(yakKicking, yakX-scrollPosition, yakY,250,180, null);
         
      }
      else
      {
          if ((yakX % 50) > 25)
          {
               g.drawImage(yakRunning2,yakX-scrollPosition,yakY,250,180, null);
          }
          else
          {
               g.drawImage(yakRunning1,yakX-scrollPosition,yakY,250,180, null);
          }
         
      }
       g.setColor(Color.black);
      g.drawString("Distance from fotball: " + distanceFromFootbal,100,100);
     
      g2.translate(footballX-scrollPosition, footballY);
      g2.rotate(footballRotation); // rotacja 30 stopni
      g2.scale(footballScale, footballScale);
      g.drawImage(footballImage,-25,-25,50,50,null); // -25 , -25 to jest rotation center. Czyli polowa kurczaka czyli srodek
      
   
     
     
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       
        if (yakRunning)
                {
                  if (muzyka)
         {
             ZaryczJakByk();
             muzyka= false;
         }
                     yakX = yakX + yakSpeed;
                  scrollPosition = scrollPosition + 12; //tutaj bedzie biegl razem z ekranem 
                }    
        if (footballFlying)
        {
            
            footballX = (int) (footballX + footballXSpeed);
            footballY = (int) (footballY + (footballYSpeed));
            
            footballYSpeed = footballYSpeed + 3;
            if (footballY > 550) // jezeli spadnie na ziemie ponizej 550
            {
               
                footballY = 550; // ma zostac na ziemi
               
                
             
                if (footballX > 1300)
                {
                    footballX = 1300;
                    footballY = 550;
                }
                
            }
           else
            {
                   footballRotation = footballRotation + 1;
                   footballScale  = footballScale + 0.5;
            }
            scrollPosition = scrollPosition + 20; // tutaj ekran sie przesunie bo kopnieciu 
            if (scrollPosition > 620)
            {
                scrollPosition = 620;
                
            }
            
        }
        repaint(); 
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
     
    }

    @Override
    public void mousePressed(MouseEvent e) {
      yakRunning = true;
      repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
          jump.play();
        
      yakRunning = false;
      footballFlying = true;
     
      distanceFromFootbal= yakX+200 - footballX; // 200 czyli odstep od lewej strony byka do jego nogi
      
      if (Math.abs(distanceFromFootbal) < 20 )
      {
          footballYSpeed = -50;
          footballXSpeed = 25;
      }
      else if (distanceFromFootbal > 20 && distanceFromFootbal < 100)
      {
          footballYSpeed = -40;
          footballXSpeed = 15;
      }
      else
      {
          footballXSpeed = 0;
          footballYSpeed = 0;
      }
      
      repaint();
      
     
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
      
    }
    public void ZaryczJakByk()
    {
        bull.play();
    }
  
}
