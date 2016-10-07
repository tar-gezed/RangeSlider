import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DisplayWindow {
	public static boolean RIGHT_TO_LEFT = false;

	public static void addComponentsToPane(Container pane) {

		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

		if (RIGHT_TO_LEFT) {
			pane.setComponentOrientation(java.awt.ComponentOrientation.RIGHT_TO_LEFT);
		}

		RangeSlider rangeSlider = new RangeSlider(0, 20, 0, 200, 40);
		RangeSlider rangeSlider2 = new RangeSlider(0, 20, 0, 100, 40);
		JPanel infoPanel = new JPanel();
		JPanel firstPanel = new JPanel();
		JPanel secondPanel = new JPanel();

		JLabel rangeSliderLowerLabel = new JLabel();
		JLabel rangeSliderLowerValue = new JLabel();
		JLabel rangeSliderUpperLabel = new JLabel();
		JLabel rangeSliderUpperValue = new JLabel();

		rangeSliderLowerLabel.setText("Lower value:");
		rangeSliderUpperLabel.setText("Upper value:");

		// Add listener to update display.
		rangeSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				RangeSlider slider = (RangeSlider) e.getSource();
				rangeSliderLowerValue.setText(Integer.toString(slider.getValue()));
				rangeSliderUpperValue.setText(Integer.toString(slider.getUpperValue()));
			}
		});

		rangeSliderLowerValue.setHorizontalAlignment(JLabel.CENTER);
		rangeSliderUpperValue.setHorizontalAlignment(JLabel.CENTER);
		// firstPanel.add(rangeSliderLowerLabel);
		// firstPanel.add(rangeSliderLowerValue);
		// secondPanel.add(rangeSliderUpperLabel);
		// secondPanel.add(rangeSliderUpperValue);

		infoPanel.setLayout(new GridLayout(2, 2));
		infoPanel.add(rangeSliderLowerLabel);
		infoPanel.add(rangeSliderLowerValue);
		infoPanel.add(rangeSliderUpperLabel);
		infoPanel.add(rangeSliderUpperValue);
		// infoPanel.add(secondPanel, BorderLayout.SOUTH);

		pane.add(infoPanel);
		pane.add(new JSeparator());
		rangeSlider.setAlignmentY(Component.CENTER_ALIGNMENT);
		pane.add(rangeSlider);
		pane.add(rangeSlider2);

	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked from the event
	 * dispatch thread.
	 */
	private static void createAndShowGUI() {

		// Create and set up the window.
		JFrame window = new JFrame();
		window.setTitle("DisplayWindow");
		window.setSize(1000, 1000);
		window.setPreferredSize(new Dimension(500, 200));
		window.setMaximumSize(new Dimension(500, 200));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up the content pane.
		addComponentsToPane(window.getContentPane());
		// Use the content pane's default BorderLayout. No need for
		// setLayout(new BorderLayout());
		// Display the window.
		window.pack();
		window.setVisible(true);
		window.setLocationRelativeTo(null);
	}

	public static void main(String[] args) {

		//
		// try {
		// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		// } catch (Exception ex) {
		// ex.printStackTrace();
		// }
		//
		// JFrame window = new JFrame();
		// window.setTitle("DisplayWindow");
		// window.setSize(1000, 1000);
		//

		//
		// window.setVisible(true);
		// window.add(rangeSliderUpperLabel);
		//
		// window.add(new RangeSlider(0, 50, 0, 100, 0));
		//
		// window.pack();
		// window.setLocationRelativeTo(null);

		/* Use an appropriate Look and Feel */
		try {
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		/* Turn off metal's use bold fonts */
		// UIManager.put("swing.boldMetal", Boolean.FALSE);

		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
