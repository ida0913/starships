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

public class Bullets {
	private List<Ammo> ammo;

	public Bullets() {
		ammo = new ArrayList<Ammo>();
	}

	public void add(Ammo al) {
		ammo.add(al);
	}

	// post - draw each Ammo
	public void drawEmAll(Graphics window) {
		for (int i = 0; i < ammo.size(); i++) {
			ammo.get(i).draw(window);
		}
	}

	public void moveEmAll() {
		if (ammo.size() > 0) {
			for (int i = 0; i < ammo.size(); i++) {
				ammo.get(i).move("DOWN");
			}
		}
	}

	public void cleanEmUp() {
		if (ammo.size() > 0) {
			for (int i = 0; i < ammo.size(); i++) {
				if (ammo.get(i).getY() < -1) {
					ammo.get(i).setPos(-1000, -1000);
				}
			}
		}
	}

	public List<Ammo> getList() {
		return ammo;
	}

	public String toString() {
		return "";
	}

	public boolean collision(Alien al) {
		if (ammo.size() > 0) {
			for (int i = 0; i < ammo.size(); i++) {
				if ((ammo.get(i).getY() > al.getY()) &&
						(ammo.get(i).getY() < al.getY() + ammo.get(i).getHeight()) &&
						(ammo.get(i).getX() > al.getX()) &&
						(ammo.get(i).getX() < al.getX() + al.getWidth())) {
					return true;
				}
			}

		}

		return false;
	}
}

// collision method
