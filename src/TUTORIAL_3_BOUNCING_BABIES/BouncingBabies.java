package TUTORIAL_3_BOUNCING_BABIES;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class BouncingBabies extends JComponent implements MouseListener,ActionListener, KeyListener{
    private int Gamewidth = 800;
    private int Gameheight = 600;
    private int groundHeight = 40;
    private int buildingWidth = 60;
    private double babyYSpeed=0;
    private double gravity =1; // jedna piksela na Frame
    Image babyImage;
    private  int babyX = 50;
    private  double babyY = 100;
    
    private int firemanX1 = 400;
    private int firemanX2= 550;
    private int trampolineX = 415; 
    
    private int groundY = 560;
    
    public BouncingBabies() throws IOException
    {
       babyImage  = ImageIO.read(getClass().getResource("baby.png"));
    }
    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame("Save the Babies");
        BouncingBabies game = new BouncingBabies(); // tu rezerwuje pamiec
      
        window.add(game);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.addMouseListener(game);
        window.addKeyListener(game);
        Timer t = new Timer(30,game); // na kazde 30 milisekund dajemy ActionListeener
        t.start();
        
    }
    public Dimension getPreferredSize()// 1 metoda do przeciazenia
    {
        return new Dimension(Gamewidth,Gameheight);
    }
    protected void paintComponent(Graphics g)
    {   //background
        g.setColor(Color.black);
        g.fillRect(0, 0, Gamewidth, Gameheight);
        
        // ground
        g.setColor(Color.white.darker());
        g.fillRect(0, Gameheight-groundHeight,Gamewidth ,groundHeight);// od miejsca 0 do od gory 600-40 . Wypelniam wszystko na 800,40
        
        // building 
        g.setColor(new Color(212,81,58));
        g.fillRect(0, 60, buildingWidth, Gameheight-60-40);
        
        //Ambulance
        g.setColor(Color.white);
        g.fillRect(680, 460, 120, 100);
        
        //Fireman
        g.setColor(new Color(84,163,181));
        g.fillRect(firemanX1,600-40-60,30,60);
        g.fillRect(firemanX2,600-40-60,30,60);
        
        // Trampoline
        g.setColor(Color.ORANGE);
        g.fillRect(trampolineX,535,150,10);
        
        //Baby
        g.setColor(new Color(241,209,86));
   //     g.fillRect(babyX, (int)babyY, 25, 25);
        g.drawImage(babyImage, babyX, (int) babyY, null);
        
        
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
     babyX= babyX+4;
     babyY = babyY + babyYSpeed;
     babyYSpeed = babyYSpeed + gravity;
     
   // checks if baby is near the ground  
   if (babyY +25>= groundY) // 25 pikesli wielkosc dziecka
     {
       // sprawdza czy dziecko dotyka strazaka
       if (babyX + 25 >= firemanX1 && babyX +25 <= firemanX2)
       {
           babyYSpeed = -0.8 * babyYSpeed; //flips into opposite direction. To jest okolo -30
       }
       else
       {
           tossNewBaby();
       }
         
     }
   
   if (babyX > Gamewidth) // jesli dziecko wyleci za x BoardWidth wtedy dzieckoX = 50 oraz dzieckoY = 100 , i speed tez ma poczatkowa
   {
            tossNewBaby();
   }
     repaint();
     
    }

    private void tossNewBaby() {
        babyX = 50;
        babyY = 100;
        babyYSpeed = 0;
    }

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

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
      if (firemanX1 <= 100)
      {
          firemanX1=60;
          firemanX2 = 210;
          trampolineX = 75;
      }
       if (firemanX2 >= 680)
      {
          firemanX1= 530;
          firemanX2 = 680;
          trampolineX = 545;
      }
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
        firemanX1 -=100;
        firemanX2 -=100 ;
        trampolineX -=100;
        
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
        firemanX1   +=100;
        firemanX2   +=100 ;
        trampolineX +=100;
       
        }
         repaint();
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    
}
