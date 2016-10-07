import javax.swing.*;
public interface IRangeSlider extends BoundedRangeModel{
	
	int getSecondValue();
	int getSecondExtent();
	
	void setSecondValue(int newSecondValue);
	void setSecondExtent(int newSecondExtent);
	
	boolean getSecondValueIsAdjusting();
	void setSecondValueIsAdjusting(boolean b);
	 
	void setRangeProperties(int value, int extent, int secondValue, int secondExtent, int min, int max, boolean adjusting);
	
}
