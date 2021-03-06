package com.mithrilclient;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import com.mithrilclient.reflection.ReflectionHooks;

@SuppressWarnings("serial")
public final class CanvasWrapper extends Component {
	private final MithrilClient mithrilClient;
	private final Component parent;
	private final ReflectionHooks hooks;
	private GraphicsWrapper wrapper;

	public CanvasWrapper(MithrilClient mithrilClient, Component parent, ReflectionHooks hooks) {
		this.mithrilClient = mithrilClient;
		this.parent = parent;
		this.hooks = hooks;
		this.wrapper = new GraphicsWrapper(parent.getGraphics(), this);
	}

	@Override
	public void update(Graphics g) {
		parent.update(g);
	}

	@Override
	public Graphics getGraphics() {
		Graphics g = parent.getGraphics();
		if (!wrapper.isReference(g)) wrapper = new GraphicsWrapper(parent.getGraphics(), this);
		return wrapper;
	}

	@Override
	public void paint(Graphics g) {
		parent.paint(g);
	}

	public void tick(Graphics g) {
		mithrilClient.tick(hooks, g);
	}

	public static class GraphicsWrapper extends Graphics {
		private final Graphics g;
		private final CanvasWrapper wrapper;

		public GraphicsWrapper(Graphics g, CanvasWrapper wrapper) {
			this.g =  g;
			this.wrapper = wrapper;
		}

		public boolean isReference(Graphics g) {
			return this.g == g;
		}

		@Override
		public void clearRect(int x, int y, int width, int height) {
			g.clearRect(x, y, width, height);
		}

		@Override
		public void clipRect(int x, int y, int width, int height) {
			g.clipRect(x, y, width, height);
		}

		@Override
		public void copyArea(int x, int y, int width, int height, int dx, int dy) {
			g.copyArea(x, y, width, height, dx, dy);
		}

		@Override
		public Graphics create() {
			return g.create();
		}

		@Override
		public void dispose() {
			g.dispose();
		}

		@Override
		public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
			g.drawArc(x, y, width, height, startAngle, arcAngle);
		}

		@Override
		public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
			wrapper.tick(img.getGraphics());
			return g.drawImage(img, x, y, observer);
		}

		@Override
		public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
			return g.drawImage(img, x, y, bgcolor, observer);
		}

		@Override
		public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
			return g.drawImage(img, x, y, width, height, observer);
		}

		@Override
		public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor,
				ImageObserver observer) {
			return g.drawImage(img, x, y, width, height, bgcolor, observer);
		}

		@Override
		public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
				ImageObserver observer) {
			return g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
		}

		@Override
		public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
				Color bgcolor, ImageObserver observer) {
			return g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer);
		}

		@Override
		public void drawLine(int x1, int y1, int x2, int y2) {
			g.drawLine(x1, y1, x2, y2);
		}

		@Override
		public void drawOval(int x, int y, int width, int height) {
			g.drawOval(x, y, width, height);
		}

		@Override
		public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
			g.drawPolygon(xPoints, yPoints, nPoints);
		}

		@Override
		public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
			g.drawPolyline(xPoints, yPoints, nPoints);
		}

		@Override
		public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
			g.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
		}

		@Override
		public void drawString(String str, int x, int y) {
			g.drawString(str, x, y);
		}

		@Override
		public void drawString(AttributedCharacterIterator iterator, int x, int y) {
			g.drawString(iterator, x, y);
		}

		@Override
		public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
			g.fillArc(x, y, width, height, startAngle, arcAngle);
		}

		@Override
		public void fillOval(int x, int y, int width, int height) {
			g.fillOval(x, y, width, height);
		}

		@Override
		public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
			g.fillPolygon(xPoints, yPoints, nPoints);
		}

		@Override
		public void fillRect(int x, int y, int width, int height) {
			g.fillRect(x, y, width, height);
		}

		@Override
		public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
			g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
		}

		@Override
		public Shape getClip() {
			return g.getClip();
		}

		@Override
		public Rectangle getClipBounds() {
			return g.getClipBounds();
		}

		@Override
		public Color getColor() {
			return g.getColor();
		}

		@Override
		public Font getFont() {
			return g.getFont();
		}

		@Override
		public FontMetrics getFontMetrics(Font f) {
			return g.getFontMetrics(f);
		}

		@Override
		public void setClip(Shape clip) {
			g.setClip(clip);
		}

		@Override
		public void setClip(int x, int y, int width, int height) {
			g.setClip(x, y, width, height);
		}

		@Override
		public void setColor(Color c) {
			g.setColor(c);
		}

		@Override
		public void setFont(Font font) {
			g.setFont(font);
		}

		@Override
		public void setPaintMode() {
			g.setPaintMode();
		}

		@Override
		public void setXORMode(Color c1) {
			g.setXORMode(c1);
		}

		@Override
		public void translate(int x, int y) {
			g.translate(x, y);
		}
	}
}
