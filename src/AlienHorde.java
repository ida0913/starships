//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class AlienHorde {
	private List<Alien> aliens;

	public AlienHorde() {
		aliens = new ArrayList<Alien>();
	}

	public void add(Alien al) {
		aliens.add(al);
	}

	public void drawEmAll(Graphics window) {
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).draw(window);
		}
	}

	public List<Alien> getAliens(){
		return aliens;
	}

	public void moveEmAll() {
		for (int i = 0; i < aliens.size(); i++) {
			if (aliens.get(i).getX() > 740) {
				aliens.get(i).setAlienBool(true);
			}
			if (aliens.get(i).getX() < 0) {
				aliens.get(i).setAlienBool(false);
			}
			if (aliens.get(i).getAlienBool()) {
				aliens.get(i).move("LEFT");
			} else {
				aliens.get(i).move("RIGHT");
			}
		}
	}

	public void removeDeadOnes(boolean coll, Alien al) {
		if(coll){
			al.setPos(-100, -100);
		}
	}

	public String toString() {
		return "";
	}
}
