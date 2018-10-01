package TUTORIAL_1_PONG;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.*;

public class PongGame extends JComponent implements ActionListener,MouseMotionListener {
    private int ballX = 400;
    private int ballY = 150;
    private int ballYspeed=7;
    private int ballXspeed = 5;
       private int paddleX=0;
       
 
    // private int paddley;
    public static void main(String[] args) {
        JFrame window = new JFrame("Pong Game by Adam");
        PongGame game = new PongGame();
       
        
        window.add(game);
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.addMouseMotionListener(game);
        
      
        Timer t = new Timer(20,game);// w milisekundach
        t.start();

   
    }
 
    public Dimension getPreferredSize() // musi byc getPreferredSize() tylko tak zwroci nazwe bez nadpisywania
    {
        return new Dimension(800,600);
    }
    protected void paintComponent(Graphics g) //dzieki tej metodzie mozemy rysowac na screenie
    {
          g.setColor(new Color(178,223,224));
          g.fillRect(0,0,800,600);
          
        g.setColor(new Color(110,61,23)); // lub po prostu Rgb n new Color(0x6E410D)
        g.fillRect(paddleX, 510, 150, 15);
        
        g.setColor(new Color(155,93,169));
        g.fillOval(ballX,ballY,30,30);
     
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ballX = ballX + ballXspeed;
        ballY = ballY + ballYspeed;
      //  if (ballY >= 510 ) // jesli pilka znajdzie sie poza 510 w skali y . Zacznij rysowac pilke na osi x+5,y-7 czyli bedzie odbicie , Os jest odwrocona w javie nalezy pamietac
        if (ballX >= paddleX && ballX <= paddleX+150 && ballY > 480) // ballX jest wieksza od poczatku naszego paddla oraz mniejzsa od paddle+dlugosc paddla oraz jest ponizej y480 nastapi odbicie
      {// greater than the left side and less than the right side + width of the paddle
            ballYspeed = -7;
             System.out.println("X = "+ballX+" Y = "+ballY);
        }
        if (ballX >= 800)
        {
            System.out.println("X = "+ballX+" Y = "+ballY);
            ballXspeed =-5;
        }
        if (ballX <= 0)
        {
            
            ballXspeed = 5;
             System.out.println("X = "+ballX+" Y = "+ballY);
        }
        if (ballY <= 0)
        {
            ballYspeed = 7;
             System.out.println("X = "+ballX+" Y = "+ballY);
           
        }
       
        repaint();
    }
    @Override
    public void mouseDragged(MouseEvent e) {
       
    }
    @Override
    public void mouseMoved(MouseEvent e) {
    paddleX = e.getX()-75; // padlle X . Tutaj odejmuje 75 zeby miec kursor myszki na srodku od . To jest polowa 150 czyli punkt x na screeni
   
    //   paddley = e.getY();
    repaint();
    }
  
    
}
