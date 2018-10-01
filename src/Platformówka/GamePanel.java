package Platformówka;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
public class GamePanel extends JPanel implements Runnable {
	public static final int WIDTH = 400;
	public static final int HEIGHT = 400;
	
	private Thread thread;
	private boolean running;
	
	private BufferedImage image;
	private Graphics2D g;
	
	private int FPS = 30;
	private int targetTime = 1000/FPS;
	
	private Plansza plansza;
	
	public GamePanel()
	{
		super();
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setFocusable(true);
		requestFocus(); // wymaga sie aby ten kompoinent by� widoczny na poczatku
	}
	//Makes this Container displayable by connecting it to a native screen resource. Making a container displayable willcause all of its children to be made displayable.This method is called internally by the toolkit and shouldnot be called directly by programs
	//addNotify() simply informs the GamePanel that it is now attached to the parent JFrame. I wanted to start the game when I know for sure everything is set up.
	//It shouldn't be a problem though if you put everything in addNotify() in the constructor instead.
	public void addNotify()
	{
		super.addNotify();
		if (thread == null)
		{
			thread = new Thread(this);
			thread.start();
		}
	}
	@Override
	public void run() {
		init();			// inicjalizuje
		
		long startTime;
		long urdTime;
		long waitTime;
		
		while(running)
		{
			startTime = System.nanoTime();
			update();// Calls paint ktory calls PaintComponent najczesciej jesli jest Grapgics g . Tutaj metoda wlasna bedzie
			render();
			draw();
			
			urdTime = (System.nanoTime() - startTime/1000000);
			waitTime = targetTime - urdTime;
			
			try
			{
				Thread.sleep(waitTime);
			}
			catch(Exception e)
			{
				
			}
		}		
	}
	private void init()
	{
		running = true;
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		
		plansza = new Plansza("C:\\Users\\adam\\Documents\\NetBeansProjects\\GAMES\\src\\Platformówka\\testmap.txt",32);
	}
//////////////////////////////////////////////////////////////
	private void update()
	{
		plansza.update();
	}
	private void render()
	{
		plansza.draw(g);
	}
	private void draw()
	{
		Graphics g2 = getGraphics(); // pobiera grafike aktualnego komponentu
		g2.drawImage(image,0,0,null);// rysuje  od 0 , 0 
		g2.dispose();// zwalnia pamiec 
	}
}


