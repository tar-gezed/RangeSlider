public class Home {

	private int id;
	private Position position;
	private int nbPieces;
	private int value;

	public Home(int id, Position position, int nbPieces, int value) {
		super();
		this.id = id;
		this.position = position;
		this.nbPieces = nbPieces;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public int getNbPieces() {
		return nbPieces;
	}

	public void setNbPieces(int nbPieces) {
		this.nbPieces = nbPieces;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "[Home n°" + this.id + "] NbPieces: " + this.nbPieces + ", Position: "
				+ this.position.toString() + ", Value: " + this.value;
	}
}
