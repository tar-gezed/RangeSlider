import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class HomeFinder extends JFrame {

	public HomeFinder() {

		super();

		HomeMap homeMap = new HomeMap();

		RangeSlider rsPiece = new RangeSlider(0, homeMap.getMaxPiece() / 3, homeMap.getMinPiece(),
				homeMap.getMaxPiece(), homeMap.getMaxPiece() / 3);

		RangeSlider rsPrice = new RangeSlider(0, homeMap.getMaxPrice() / 3, homeMap.getMinPrice(),
				homeMap.getMaxPrice(), homeMap.getMaxPrice() / 3);

		JLabel rangeSliderLowerPièceLabel = new JLabel();
		JLabel rangeSliderLowerPièceValue = new JLabel();
		JLabel rangeSliderUpperPièceLabel = new JLabel();
		JLabel rangeSliderUpperPièceValue = new JLabel();
		JLabel rangeSliderLowerPriceLabel = new JLabel();
		JLabel rangeSliderLowerPriceValue = new JLabel();
		JLabel rangeSliderUpperPriceLabel = new JLabel();
		JLabel rangeSliderUpperPriceValue = new JLabel();

		JPanel roomPanel = new JPanel(new GridLayout(2, 1));
		JPanel pricePanel = new JPanel(new GridLayout(2, 1));

		JPanel flowPrice = new JPanel(new FlowLayout());
		JPanel flowPièce = new JPanel(new FlowLayout());

		roomPanel.add(flowPièce);
		roomPanel.add(rsPiece);

		pricePanel.add(flowPrice);
		pricePanel.add(rsPrice);

		flowPièce.add(rangeSliderLowerPièceLabel);
		flowPièce.add(rangeSliderLowerPièceValue);
		flowPièce.add(rangeSliderUpperPièceLabel);
		flowPièce.add(rangeSliderUpperPièceValue);

		flowPrice.add(rangeSliderLowerPriceLabel);
		flowPrice.add(rangeSliderLowerPriceValue);
		flowPrice.add(rangeSliderUpperPriceLabel);
		flowPrice.add(rangeSliderUpperPriceValue);

		rangeSliderLowerPièceLabel.setText("Rooms: ");
		rangeSliderUpperPièceLabel.setText(" - ");
		rangeSliderLowerPièceValue.setText(String.valueOf(rsPiece.getValue()));
		rangeSliderUpperPièceValue.setText(String.valueOf(rsPiece.getUpperValue()));

		rangeSliderLowerPriceLabel.setText("Price: ");
		rangeSliderUpperPriceLabel.setText(" - ");
		rangeSliderLowerPriceValue.setText(String.valueOf(rsPrice.getValue()));
		rangeSliderUpperPriceValue.setText(String.valueOf(rsPrice.getUpperValue()));

		setTitle("HomeFinder");
		setSize(1075, 680);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel contentPane = new JPanel(new BorderLayout());
		JPanel contentPaneEast = new JPanel(new GridLayout(6, 1));

		this.setContentPane(contentPane);

		contentPane.add(homeMap, BorderLayout.CENTER);
		contentPane.add(contentPaneEast, BorderLayout.EAST);

		// RangeSlider slider_prix = new RangeSlider(homeMap.getMinPrice(), homeMap.getMaxPrice(),
		// homeMap.getMinPrice(), homeMap.getMaxPrice(), 1, "Selection des prix : ", 1);
		// slider_prix.setPreferredSize(new Dimension(125, 75));
		// RangeSlider slider_nb_pieces = new RangeSlider(homeMap.getMinPiece(),
		// homeMap.getMaxPiece(),
		// homeMap.getMinPiece(), homeMap.getMaxPiece(), 1, "Nombre de pièces :", 2);
		// slider_nb_pieces.setPreferredSize(new Dimension(125, 75));
		//
		// slider_prix.getModel().addRangeSliderListener(homeMap);
		// slider_nb_pieces.getModel().addRangeSliderListener(homeMap);
		//

		// Add listener to update display.
		rsPiece.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				RangeSlider slider = (RangeSlider) e.getSource();
				rangeSliderLowerPièceValue.setText(Integer.toString(slider.getValue()));
				rangeSliderUpperPièceValue.setText(Integer.toString(slider.getUpperValue()));
			}
		});

		// Add listener to update display.
		rsPrice.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				RangeSlider slider = (RangeSlider) e.getSource();
				rangeSliderLowerPriceValue.setText(Integer.toString(slider.getValue()));
				rangeSliderUpperPriceValue.setText(Integer.toString(slider.getUpperValue()));
			}
		});

		contentPaneEast.add(new JPanel());
		contentPaneEast.add(new JPanel());
		contentPaneEast.add(roomPanel);
		contentPaneEast.add(pricePanel);
		contentPaneEast.add(new JPanel());
		contentPaneEast.add(new JPanel());
		setVisible(true);

		setResizable(false);
	}
}
