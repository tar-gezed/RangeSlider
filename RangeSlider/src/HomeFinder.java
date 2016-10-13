import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * L'application HomeFinder avec sa carte et ses deux range sliders
 *
 * @author Quentin Dunand & Elsa Navarro
 *
 */
public class HomeFinder extends JFrame {

	public HomeFinder() {

		// Notre composant représentant notre map
		HomeMap homeMap = new HomeMap();

		// Le range slider manipulant le nombre de pièce de l'appartmement
		RangeSlider rsPiece = new RangeSlider(0, homeMap.getMaxPiece() - homeMap.getMinPiece(),
				homeMap.getMinPiece(), homeMap.getMaxPiece(), homeMap.getMinPiece());

		// Le range slider manipulant le prix de location
		RangeSlider rsPrice = new RangeSlider(0, homeMap.getMaxPrice() - homeMap.getMinPrice(),
				homeMap.getMinPrice(), homeMap.getMaxPrice(), homeMap.getMinPrice());

		// Les différents labels de l'applciation
		JLabel rangeSliderLowerPièceLabel = new JLabel();
		JLabel rangeSliderLowerPièceValue = new JLabel();
		JLabel rangeSliderUpperPièceLabel = new JLabel();
		JLabel rangeSliderUpperPièceValue = new JLabel();
		JLabel rangeSliderLowerPriceLabel = new JLabel();
		JLabel rangeSliderLowerPriceValue = new JLabel();
		JLabel rangeSliderUpperPriceLabel = new JLabel();
		JLabel rangeSliderUpperPriceValue = new JLabel();

		// Conteneur pour le range slider des pièces
		JPanel roomPanel = new JPanel(new GridLayout(2, 1));
		// Conteneur pour le range slider des prix
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

		// Listener pour mettre à jour l'affichage en fonction du nombre de pièces
		rsPiece.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				RangeSlider slider = (RangeSlider) e.getSource();
				rangeSliderLowerPièceValue.setText(Integer.toString(slider.getValue()));
				rangeSliderUpperPièceValue.setText(Integer.toString(slider.getUpperValue()));
				homeMap.setMaxPiece(slider.getUpperValue());
				homeMap.setMinPiece(slider.getValue());
				homeMap.repaint();
			}
		});

		// Listener pour mettre à jour l'affichage en fonction du nombre du prix
		rsPrice.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				RangeSlider slider = (RangeSlider) e.getSource();
				rangeSliderLowerPriceValue.setText(Integer.toString(slider.getValue()));
				rangeSliderUpperPriceValue.setText(Integer.toString(slider.getUpperValue()));
				homeMap.setMaxPrice(slider.getUpperValue());
				homeMap.setMinPrice(slider.getValue());
				homeMap.repaint();
			}
		});

		contentPaneEast.add(new JPanel());
		contentPaneEast.add(new JPanel());
		contentPaneEast.add(roomPanel);
		contentPaneEast.add(pricePanel);
		contentPaneEast.add(new JPanel());
		contentPaneEast.add(new JPanel());
		setVisible(true);

		// Fenêtre non resizable et position au centre
		setResizable(false);
		setLocationRelativeTo(null);
	}
}
