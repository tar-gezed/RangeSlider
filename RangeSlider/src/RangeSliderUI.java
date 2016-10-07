import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;

public class RangeSliderUI extends BasicSliderUI {

	RangeSlider rangeSlider;

	Rectangle lower;
	Rectangle upper;

	enum Etats {
		IDLE, CLICK_RIGHT_SIDE, CLICK_LEFT_SIDE, CLICK_MIDDLE, CLICK_SLIDER_LEFT, CLICK_SLIDER_RIGHT
	};

	Etats etat;

	public RangeSliderUI(RangeSlider rangeSlider) {
		super(rangeSlider);
		this.rangeSlider = rangeSlider;
		etat = Etats.IDLE;
		lower = new Rectangle(rangeSlider.getValue(), 0, 10, 20);
		upper = new Rectangle(rangeSlider.getUpperValue(), 0, 10, 20);

		// TODO Auto-generated constructor stub
	}

	@Override
	protected TrackListener createTrackListener(JSlider slider) {
		return new RangeSliderEvent();
	}

	// pour dessiner nos 2 rectangles...
	@Override
	public void paint(Graphics g, JComponent c) {
		TestUI.setvalUI();// On maj les valeurs min/value/extent/max affichée
		// super.paint(g, c);
		// appel à paintThunb dans le super...
		super.paint(g, c);
	}

	@Override
	public void paintThumb(Graphics g) {
		Graphics2D g2D = (Graphics2D) g.create();

		lower.x = rangeSlider.getValue();
		upper.x = rangeSlider.getUpperValue();

		// middle
		g2D.setColor(Color.ORANGE);
		g2D.fillRect(lower.x + lower.width, lower.height / 4, upper.x - lower.x, lower.height / 2);

		// left cursor
		g2D.setColor(Color.LIGHT_GRAY);
		g2D.fillRect(lower.x, lower.y, lower.width, lower.height);

		// right cursor
		g2D.setColor(Color.LIGHT_GRAY);
		g2D.fillRect(upper.x, upper.y, upper.width, upper.height);

		g2D.dispose();
	}

	private class RangeSliderEvent extends TrackListener {
		Etats getPosition(MouseEvent e) {
			if (upper.contains(e.getPoint())) {
				return Etats.CLICK_SLIDER_RIGHT;
			} else if (lower.contains(e.getPoint())) {
				return Etats.CLICK_SLIDER_LEFT;
			} else if (e.getX() < lower.x) {
				return Etats.CLICK_LEFT_SIDE;
			} else if (e.getX() > upper.x) {
				return Etats.CLICK_RIGHT_SIDE;
			} else {
				return Etats.CLICK_MIDDLE;
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseReleased(e);
			etat = Etats.IDLE;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

			etat = getPosition(e);
			switch (etat) {
			case CLICK_LEFT_SIDE:
				rangeSlider.setValue(e.getX());
				break;
			case CLICK_RIGHT_SIDE:
				rangeSlider.setUpperValue(e.getX());
				break;
			// case CLICK_MIDDLE:
			// // on cherche le bord le plus proche, et on le déplace à la position voulue
			// // if((e.getX()-gauche.x)<((droite.x-gauche.x)/2)) {
			// // self.setSliderGauche(e.getX());
			// // }else {
			// // self.setSliderDroite(e.getX());
			// // }
			// oldValue = e.getX();
			// break;
			default:
				break;
			}

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			switch (etat) {
			// case CLICK_MIDDLE:
			// // on cherche le bord le plus proche, et on ajuste le rect voulu
			// // if((e.getX()-gauche.x)<((droite.x-gauche.x)/2)) {
			// // self.setSliderGauche(e.getX());
			// // }else {
			// // self.setSliderDroite(e.getX());
			// // }
			// int newValue = e.getX();
			// int offset = newValue - oldValue;
			// self.moveRange(offset);
			// oldValue = newValue;
			// break;
			case CLICK_SLIDER_LEFT:
				rangeSlider.setValue(e.getX());
				break;
			case CLICK_SLIDER_RIGHT:
				rangeSlider.setUpperValue(e.getX());
				break;
			default:
				break;
			}
		}

	}
}
