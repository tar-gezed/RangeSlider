import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

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
	 * Constructor with min and max value
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
	 * Sets the upper (rightest) value of the range.
	 */
	public void setUpperValue(int value) {

		int minValue = getValue();

		// Compute new extent and then sets it
		int newExtent = Math.min(Math.max(0, value - minValue) + 10, getMaximum() - minValue);
		System.out.println("nex Extent = " + newExtent);
		setExtent(newExtent);
	}

	@Override
	public void addChangeListener(ChangeListener l) {
		// TODO Auto-generated method stub
		super.addChangeListener(l);
	}

	/**
	 *
	 * @return the upper value of the range
	 */
	public int getUpperValue() {
		return getValue() + getExtent();
	}

	/**
	 * Override setValue to set the lowest value
	 */
	@Override
	public void setValue(int n) {
		int oldValue = getValue();
		if (oldValue == n) {
			return;
		}

		// Backup old extent value
		int oldExtent = getExtent();
		// Compute new value :
		int newValue = Math.min(Math.max(getMinimum(), n), oldValue + oldExtent - 10);
		int newExtent = oldExtent + oldValue - newValue;

		// Sets new value and new extent
		getModel().setRangeProperties(newValue, newExtent, getMinimum(), getMaximum(), getValueIsAdjusting());
	}
}
