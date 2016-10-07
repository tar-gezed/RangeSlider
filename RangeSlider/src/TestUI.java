import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class TestUI {
	public static JLabel values = new JLabel("");
	public static RangeSlider rs = new RangeSlider(0, 50, 0, 100, 0);
	public static JFrame window = new JFrame();
	public static final int rect_width = 10;
	public static final int rect_height = 20;

	public static void main(String[] args) {

		window.setTitle("RangeSlider Test");
		window.setVisible(true);
		window.setLayout(new BorderLayout());

		window.add(rs, BorderLayout.NORTH);
		window.add(values, BorderLayout.SOUTH);
		setvalUI();

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		window.pack();
		window.setLocationRelativeTo(null);
	}

	public static void setvalUI() {
		values.setText("<html>min : " + rs.getMinimum() + " value : " + rs.getValue() + "<br>extent : "
				+ rs.getExtent() + " max : " + rs.getMaximum() + "</html>");
		rs.setMaximum(window.getWidth() - rect_width);
	}
}
