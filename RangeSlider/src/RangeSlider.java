import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JSlider;

/**
 * Le modèle de notre RangeSLider qui étend la classe JSlider
 *
 * @author Quentin Dunand
 *
 */
public class RangeSlider extends JSlider {

	/**
	 *
	 */
	private static final long serialVersionUID = 6237485347414465839L;

	/**
	 * Main constructor
	 *
	 * @param orientation
	 * @param extent
	 * @param min
	 * @param max
	 * @param value
	 */
	public RangeSlider(int orientation, int extent, int min, int max, int value) {
		this.orientation = orientation;
		sliderModel = new DefaultBoundedRangeModel(value, extent, min, max);
		sliderModel.setExtent(extent);
		sliderModel.addChangeListener(changeListener);

		updateUI();
	}

	/**
	 * Constructor prenant en paramètre les valeurs min et max
	 *
	 * @param min
	 *            The minimum value possible
	 * @param max
	 *            The Maximum value possible
	 */
	public RangeSlider(int min, int max) {
		super(min, max);
	}

	@Override
	public void updateUI() {
		setUI(new RangeSliderUI(this));
		updateLabelUIs();
	}

	/**
	 * Setter pour la valeur supérieure (slider le plus à droite)
	 */
	public void setUpperValue(int value) {

		int minValue = getValue();
		// Calcul du nouveau extent
		int newExtent = Math.min(Math.max(0, value - minValue), getMaximum() - minValue);
		setExtent(newExtent);
	}

	/**
	 * Getter pour la valeur supérieure (slider le plus à droite)
	 *
	 * @return the upper value of the range
	 */
	public int getUpperValue() {
		return getValue() + getExtent();
	}

	/**
	 * Override de setValue afin de setter la valeur inférieure (le slider le plus à gauche)
	 */
	@Override
	public void setValue(int n) {
		int oldValue = getValue();
		// Si la valeur n'as pas changé, pas la peine de calculer quelque chose
		if (oldValue == n) {
			return;
		}

		// Sauvegarde de l'ancienne valeur de extent
		int oldExtent = getExtent();
		// Calcul des nouvelles valeurs de extent et Value
		int newValue = Math.min(Math.max(getMinimum(), n), oldValue + oldExtent);
		int newExtent = oldExtent + oldValue - newValue;

		// On set le nouveau extent et le nouveau value après les avoir calculés
		getModel().setRangeProperties(newValue, newExtent, getMinimum(), getMaximum(), getValueIsAdjusting());
	}
}
