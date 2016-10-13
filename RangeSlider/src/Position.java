/**
 * Classe position permettant d'avoir la position géographique d'un appartement
 *
 * @author Quentin Dunand
 *
 */
public class Position {

	double x;
	double y;

	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// Ensemble de getter et setter pour parametrer une position
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Override de la méthode toString pour permettre une lecture facile d'une position
	 */
	@Override
	public String toString() {
		return "[x : " + this.x + " y : " + this.y + "]";

	}

}
