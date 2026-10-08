import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import javax.swing.JFrame;

public abstract class Cosys extends Canvas {

    // Lines 20-40 of the book's COSYS template
    public static final int PIXX = 640 * 2;
    public static final int PIXY = 320 * 2;
    public static final double SF = 1.0; // 1.0 for modern square pixels

    // Screen center coordinates (MX, MY)
    public static final int MX = PIXX / 2;
    public static final int MY = PIXY / 2;

    // Emulates the BASIC internal graphics cursor / pen position (double precision)
    protected double currentX = 0.0;
    protected double currentY = 0.0;

    // Circle default parameters
    public static final double TS = 0.0;
    public static final double TE = 2 * Math.PI;
    public static final double R = 200.0;
    public static final double NN = 360.0;

    public Cosys() {
        setSize(PIXX, PIXY);
        setBackground(Color.WHITE);
    }

    // --- BASIC Graphic Command Emulators ---

    // Emulates CLS
    public void cls(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, PIXX, PIXY);
    }

    // Emulates PSET (X, Y)
    public void pset(Graphics g, double x, double y) {
        currentX = x;
        currentY = y;
        if (g instanceof Graphics2D g2) {
            g2.draw(new Line2D.Double(x, y, x, y));
        } else {
            g.drawLine((int) Math.round(x), (int) Math.round(y), (int) Math.round(x), (int) Math.round(y));
        }
    }

    // Emulates LINE (X1, Y1)-(X2, Y2)
    public void line(Graphics g, double x1, double y1, double x2, double y2) {
        currentX = x2;
        currentY = y2;
        if (g instanceof Graphics2D g2) {
            g2.draw(new Line2D.Double(x1, y1, x2, y2));
        } else {
            g.drawLine((int) Math.round(x1), (int) Math.round(y1), (int) Math.round(x2), (int) Math.round(y2));
        }
    }

    // Emulates relative LINE -(X2, Y2) from current pen position
    public void line(Graphics g, double x2, double y2) {
        line(g, currentX, currentY, x2, y2);
    }

    // Emulates LINE (X1, Y1)-(X2, Y2),,B (Box)
    public void box(Graphics g, double x1, double y1, double x2, double y2) {
        double left = Math.min(x1, x2);
        double top = Math.min(y1, y2);
        double width = Math.abs(x2 - x1);
        double height = Math.abs(y2 - y1);

        if (g instanceof Graphics2D g2) {
            g2.draw(new Line2D.Double(left, top, left + width, top));
            g2.draw(new Line2D.Double(left + width, top, left + width, top + height));
            g2.draw(new Line2D.Double(left + width, top + height, left, top + height));
            g2.draw(new Line2D.Double(left, top + height, left, top));
        } else {
            g.drawRect((int) Math.round(left), (int) Math.round(top), (int) Math.round(width), (int) Math.round(height));
        }
    }

    // High-precision smooth circle renderer using Path2D.Double
    public void circle(Graphics g, double x, double y, double r) {
        Graphics2D g2 = (Graphics2D) g;
        Path2D.Double path = new Path2D.Double();

        // Start path at initial angle T = TS
        path.moveTo(x + fnx(TS, r), y + fny(TS, r));

        for (int N = 1; N <= NN; N++) {
            double T = TS + ((double) N / NN) * (TE - TS);
            double X = x + fnx(T, r);
            double Y = y + fny(T, r);

            path.lineTo(X, Y);
        }

        g2.draw(path);

        // Synchronize pen cursor to the last drawn point
        currentX = x + fnx(TE, r);
        currentY = y + fny(TE, r);
    }

    public double fnx(double t, double r) {
        return r * Math.cos(t);
    }

    public double fny(double t, double r) {
        return r * Math.sin(t);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // Anti-aliasing and max quality rendering hints
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY
        );
        g2.setRenderingHint(
                RenderingHints.KEY_STROKE_CONTROL,
                RenderingHints.VALUE_STROKE_PURE
        );

        // Set clean stroke joins & caps
        g2.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        cls(g2);
        g2.setColor(Color.PINK);
        draw(g2);
    }

    // Child classes implement this method
    public abstract void draw(Graphics g);

    // Reusable window launcher
    public static void launch(Cosys canvas, String title) {
        JFrame frame = new JFrame(title);
        frame.add(canvas);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}