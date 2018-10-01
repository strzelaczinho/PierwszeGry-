package Platformówka;

import java.io.*;
import java.awt.*;

public class Plansza {

	private int x; // offset for scrolling. Oddalenie obiektow do scrolowania
	private int y;
	private int WysokoscMapy;
	private int SzerokoscMapy;
	private int rozmiarKlocka; // rozmiar klocka
	private int[][] map; // mapa klockow
	
	public Plansza(String s, int rozmiarKlocka)
	{
		this.rozmiarKlocka = rozmiarKlocka;
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(s));
			SzerokoscMapy = Integer.parseInt(br.readLine());
			WysokoscMapy = Integer.parseInt(br.readLine());
			map = new int[WysokoscMapy][SzerokoscMapy];
			
			String odstep = " "; // odstepy 
			for (int wiersz = 0;wiersz < WysokoscMapy;wiersz++)
			{
				String linia = br.readLine(); // czyta cala linie
				String[] tablicaTokenow = linia.split(odstep); // chcemy rozdzielic te wartosci i zapisac w tablicy tokenow bez wiodacej spacji
				for (int kolumna = 0;kolumna<SzerokoscMapy;kolumna++)
				{
					map[wiersz][kolumna] = Integer.parseInt(tablicaTokenow[kolumna]); // przekazuje ca�a linie s
					
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

// zeros to bloki po ktorych nie mozna chodzic. 
// 20 15, to szerokosc i wysokosc mapy 
// 0 to sa wolne bloki 
public void update()
{
	
}
public void draw(Graphics2D g)
{
	for (int wiersz = 0;wiersz < WysokoscMapy;wiersz++)
	{
		for (int kolumna = 0;kolumna<SzerokoscMapy;kolumna++)
		{
			int rc = map[wiersz][kolumna];
			if (rc == 0)
			{
				g.setColor(Color.BLACK);
			}
			if (rc == 1)
			{
				g.setColor(Color.WHITE);
			}
			g.fillRect(x+kolumna*rozmiarKlocka, y+wiersz*rozmiarKlocka, rozmiarKlocka, rozmiarKlocka);
		}
	}
}




}