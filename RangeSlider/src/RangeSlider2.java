import javax.swing.*;
public class RangeSlider2 extends DefaultBoundedRangeModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3194453914777490756L;
	
	
	public void setValue(int value){
		int currentExtent = getExtent();
		int currentValue = getValue();
		
		int diff = currentValue - value;
		
		int newExtent = Math.max(currentExtent + diff, 0);
		
		value = Math.min(value, currentValue + currentExtent);

        int newValue = Math.max(value, getMinimum());
        if (newValue + currentExtent > getMaximum()) {
            newValue = getMaximum() - currentExtent;
        }
        setRangeProperties(newValue, getExtent(), getMinimum(), getMaximum(), getValueIsAdjusting());
	}

}
