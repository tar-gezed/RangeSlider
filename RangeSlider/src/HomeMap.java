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

/**
 * Notre composant map avec les logements dessinés par dessus
 *
 * @author Quentin Dunand & Elsa Navarro
 *
 */
public class HomeMap extends JPanel {

	// Les infos principales de notre map
	public int minPrice = 0;
	public int maxPrice = 10000;
	public int minPiece = 1;
	public int maxPiece = 10;
	public ArrayList<Home> HomeList = new ArrayList<Home>();

	/**
	 * Main constructor: génère uniquement les appartements
	 */
	public HomeMap() {
		generateHomes();
	}

	/**
	 * Constructor avec en paramètre les valeurs min et max des appartements à generer
	 *
	 * @param minPrice
	 * @param maxPrice
	 * @param minPiece
	 * @param maxPiece
	 */
	public HomeMap(int minPrice, int maxPrice, int minPiece, int maxPiece) {
		setMinPrice(minPrice);
		setMaxPrice(maxPrice);
		setMinPiece(minPiece);
		setMaxPiece(maxPiece);
		generateHomes();
	}

	// Ensemble de getter et setter permettant la mise à jour de l'affichage
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

	/**
	 * Génération des appartements selon la taille de la carte
	 */
	public void generateHomes() {
		int imgWidth = 0;
		int imgHeight = 0;
		try {
			BufferedImage map = ImageIO.read(new File("grenoble_map.jpeg"));
			imgWidth = map.getWidth();
			imgHeight = map.getHeight();

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Boucle de génération des appartements
		for (int i = 1; i <= 50; i++) {
			Position position = new Position(Math.random() * (imgWidth - 30), Math.random()
					* (imgHeight - 30));
			int nbPieces = (int) (Math.random() * (this.maxPiece - this.minPiece) + this.minPiece);

			int price = (int) (Math.random() * this.maxPrice - this.minPrice) + this.minPrice;

			Home home = new Home(i, position, nbPieces, price);
			HomeList.add(home);
			System.out.println(home);
		}
	}

	/**
	 * Méthode permettant le dessin des appartements sur la carte
	 *
	 * @param g
	 * @param min_value_price
	 * @param max_value_price
	 * @param min_value_piece
	 * @param max_value_piece
	 */
	public void drawHomes(Graphics g, int min_value_price, int max_value_price, int min_value_piece,
			int max_value_piece) {

		Iterator<Home> itr = HomeList.iterator();

		while (itr.hasNext()) {

			Home home = itr.next();
			// On arrondi les positions pour afficher les appartements sur la carte
			int x = (int) home.getPosition().getX();
			int y = (int) home.getPosition().getY();

			// Dessin uniquement si les valeurs sont dans les ranges determinés
			if (home.getValue() <= max_value_price && home.getValue() >= min_value_price
					&& home.getNbPieces() >= min_value_piece && home.getNbPieces() <= max_value_piece) {

				g.setColor(Color.BLACK);
				g.fillOval(x, y, 20, 20);
				g.setColor(Color.red);
				g.fillOval(x + 1, y + 1, 20, 20);
				g.setColor(Color.BLACK);

				// Centrage approximatif du texte dans les ronds dessinés
				if (home.getId() < 10) {
					g.drawString(Integer.toString(home.getId()), x + 7, y + 15);
				} else {
					g.drawString(Integer.toString(home.getId()), x + 4, y + 15);
				}

			}

		}

	}

	/**
	 * Dessin de la carte et des logements
	 */
	@Override
	public void paintComponent(Graphics g) {
		// Lecture de l'image pour pouvoir la dessinée
		try {
			Image map = ImageIO.read(new File("grenoble_map.jpeg"));
			g.drawImage(map, 0, 0, this);

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Dessin des appartements
		drawHomes(g, getMinPrice(), getMaxPrice(), getMinPiece(), getMaxPiece());

	}

}
