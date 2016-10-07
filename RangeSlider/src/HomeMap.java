import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class HomeMap extends JPanel {

	public int minPrice = 0;
	public int maxPrice = 10000;
	public int minPiece = 1;
	public int maxPiece = 10;
	public ArrayList<Home> HomeList = new ArrayList<Home>();

	public HomeMap() {
		generateHomes();
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

	public int getMinPiece() {
		return minPiece;
	}

	public void setMinPiece(int minPiece) {
		this.minPiece = minPiece;
	}

	public int getMaxPiece() {
		return maxPiece;
	}

	public void setMaxPiece(int maxPiece) {
		this.maxPiece = maxPiece;
	}

	public void generateHomes() {
		int imgWidth = 0;
		int imgHeight = 0;
		try {
			BufferedImage map = ImageIO.read(new File("grenoble_map.jpeg"));
			imgWidth = map.getWidth();
			imgHeight = map.getHeight();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 1; i <= 20; i++) {
			Position position = new Position(Math.random() * (imgWidth - 30), Math.random()
					* (imgHeight - 30));
			int nbPieces = (int) (Math.random() * (this.maxPiece - this.minPiece) + this.minPiece);

			int price = (int) (Math.random() * this.maxPrice - this.minPrice) + this.minPrice;

			Home home = new Home(i, position, nbPieces, price);
			HomeList.add(home);
			System.out.println(home);
		}
	}

	public void drawHouseInBounds(Graphics g, int min_value_price, int max_value_price, int min_value_piece,
			int max_value_piece) {

		Iterator<Home> itr = HomeList.iterator();

		while (itr.hasNext()) {

			Home home = itr.next();
			int x = (int) home.getPosition().getX();
			int y = (int) home.getPosition().getY();

			// int X = (int) Math.round(x);
			// int Y = (int) Math.round(y);

			if (home.getValue() <= max_value_price && home.getValue() >= min_value_price
					&& home.getNbPieces() >= min_value_piece && home.getNbPieces() <= max_value_piece) {

				g.setColor(Color.BLACK);
				g.fillOval(x, y, 20, 20);
				g.setColor(Color.red);
				g.fillOval(x + 1, y + 1, 20, 20);
				g.setColor(Color.BLACK);

				g.drawString(Integer.toString(home.getId()), x + 4, y + 15);

			}

		}

	}

	@Override
	public void paintComponent(Graphics g) {

		try {
			Image map = ImageIO.read(new File("grenoble_map.jpeg"));
			g.drawImage(map, 0, 0, this);

		} catch (IOException e) {
			e.printStackTrace();
		}

		drawHouseInBounds(g, getMinPrice(), getMaxPrice(), getMinPiece(), getMaxPiece());

	}

}
