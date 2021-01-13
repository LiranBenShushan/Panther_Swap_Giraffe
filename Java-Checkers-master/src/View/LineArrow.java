package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

public class LineArrow {

	int x;
	int y;
	int endX;
	int endY;
	Color color;
	int thickness;

	public LineArrow(int x, int y, int x2, int y2, Color color, int thickness) {
		super();
		this.x = x;
		this.y = y;
		this.endX = x2;
		this.endY = y2;

		this.color = color;
		this.thickness = thickness;
	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();

		g2.setColor(color);
		g2.setStroke(new BasicStroke(thickness));
		g2.drawLine(x, y, endX, endY);
		;
		drawArrowHead(g2);
		g2.dispose();

	}

	private void drawArrowHead(Graphics2D g2) {
		double angle = Math.atan2(endY - y, endX - x);
		AffineTransform tx = g2.getTransform();
		tx.translate(endX, endY);
		tx.rotate(angle - Math.PI / 2d);
		g2.setTransform(tx);

		Polygon arrowHead = new Polygon();
		arrowHead.addPoint(0, 15);
		arrowHead.addPoint(-15, -15);
		arrowHead.addPoint(15, -15);
		g2.fill(arrowHead);
	}

}
