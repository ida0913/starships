//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class OuterSpace extends Canvas implements KeyListener, Runnable {
	private Ship ship;
	private AlienHorde horde;
	private Bullets shots;
	/*
	 * uncomment once you are ready for this part
	 *
	 * private AlienHorde horde;
	 * private Bullets shots;
	 */

	private boolean[] keys;
	private BufferedImage back;

	public OuterSpace(int height, int width) {
		setBackground(Color.black);

		keys = new boolean[5];
		horde = new AlienHorde();
		shots = new Bullets();
		// instantiate other instance variables
		// Ship, Alien

		ship = new Ship(400, 400, 75, 50, 10);

		Alien[] aliens = new Alien[10];

		for (int i = 0; i < aliens.length; i++) {
			aliens[i] = new Alien(randomizeAlien(0, width), randomizeAlien(0, height - 300), randomizeAlien(2, 4));
			horde.add(aliens[i]);
		}

		this.addKeyListener(this);
		new Thread(this).start();

		setVisible(true);
	}

	public void update(Graphics window) {
		paint(window);
	}

	public int randomizeAlien(int min, int max) {

		return new Random().nextInt(max - min) + min;
	}

	public void paint(Graphics window) {
		// set up the double buffering to make the game animation nice and smooth
		Graphics2D twoDGraph = (Graphics2D) window;

		// take a snap shop of the current screen and same it as an image
		// that is the exact same width and height as the current screen
		if (back == null)
			back = (BufferedImage) (createImage(getWidth(), getHeight()));

		// create a graphics reference to the back ground image
		// we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();

		graphToBack.setColor(Color.BLUE);
		graphToBack.drawString("StarFighter ", 25, 50);
		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0, 0, 800, 600);

		if (keys[0] == true) {
			ship.move("LEFT");
		}
		if (keys[1] == true) {
			ship.move("RIGHT");
		}
		if (keys[2] == true) {
			ship.move("DOWN");
		}
		if (keys[3] == true) {
			ship.move("UP");
		}
		if (keys[4] == true) {
			shots.add(new Ammo(ship.getX(), ship.getY()));
			keys[4] = false;
		}
			// add code to move Ship, Alien, etc.
			// collision shit
			if (ship.getX() < 0) {
				ship.setX(0);
			}
		if (ship.getX() > 800 - ship.getWidth()) {
			ship.setX(800 - ship.getWidth());
		}
		if (ship.getY() < 0) {
			ship.setY(0);
		}
		if (ship.getY() > 600 - ship.getHeight()) {
			ship.setY(600 - ship.getHeight());
		}
		ship.draw(graphToBack);
		horde.drawEmAll(graphToBack);
		horde.moveEmAll();
		shots.drawEmAll(graphToBack);
		shots.moveEmAll();
		shots.cleanEmUp();

		// add in collision detection to see if Bullets hit the Aliens and if Bullets
		// hit the Ship

		twoDGraph.drawImage(back, null, 0, 0);

		for(Alien a : horde.getAliens()){
			horde.removeDeadOnes(shots.collision(a), a);
		}

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			keys[3] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			keys[4] = true;
		}
		repaint();
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			keys[3] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			keys[4] = false;
		}
		repaint();
	}

	public void keyTyped(KeyEvent e) {
		// no code needed here
	}

	public void run() {
		try {
			while (true) {
				Thread.currentThread().sleep(5);
				repaint();
			}
		} catch (Exception e) {
		}
	}
}
