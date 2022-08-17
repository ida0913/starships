//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;

public class Ammo extends MovingThing {
	private int speed;
	private Image image;

	public Ammo() {
		this(0, 0, 0);
	}

	public Ammo(int x, int y) {
		// add code
		this(x, y, 1);
	}

	public Ammo(int x, int y, int s) {
		// add code
		super(x, y);
		this.speed = s;

		try {
			URL url = getClass().getResource("/img/bullet-1.png");
			image = ImageIO.read(url);
		} catch (Exception e) {
			throw new Error("Can't find image");
		}
	}

	public void setSpeed(int s) {
		// add code
		speed = s;
	}

	public int getSpeed() {
		return speed;
	}

	public void draw(Graphics window) {
		// add code to draw the ammo
		window.drawImage(image, getX(), getY(), 25, 25, null);
	}

	public void move(String direction) {
		// add code to draw the ammo
		if (direction.equals("UP")) {
			this.setY(super.getY() + speed);
		}
		if (direction.equals("DOWN")) {
			this.setY(super.getY() - speed);
		}
		if (direction.equals("RIGHT")) {
			this.setX(super.getX() + speed);
		}
		if (direction.equals("LEFT")) {
			this.setX(super.getX() - speed);
		}
	}

	public String toString() {
		return "";
	}
}
