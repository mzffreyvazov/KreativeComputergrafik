package kreativecomputergrafik.core;

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

    // for bdraw function
    protected double drawUnit = 1.0;

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

    public void box(Graphics g, Color color, double x1, double y1, double x2, double y2) {
        if (color != null) {
            g.setColor(color);
        }
        box(g, x1, y1, x2, y2);
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

    public void circle(Graphics g, double CN, double x, double y, double r) {
        Graphics2D g2 = (Graphics2D) g;
        Path2D.Double path = new Path2D.Double();

        // Start path at initial angle T = TS
        path.moveTo(x + fnx(TS, r), y + fny(TS, r));

        for (int N = 1; N <= CN; N++) {
            double T = TS + ((double) N / CN) * (TE - TS);
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

    public void bdraw(Graphics g, String commands) {
        bdraw(g, commands, this.drawUnit);
    }

    // Method 2: Allows overriding drawUnit if a specific program needs a custom size
    public void bdraw(Graphics g, String commands, double du) {
        if (commands == null || commands.isEmpty()) return;

        String str = commands.toUpperCase().trim();
        int i = 0;

        while (i < str.length()) {
            char c = str.charAt(i);

            // Skip whitespace, semicolons, commas
            if (c == ' ' || c == ';' || c == ',') {
                i++;
                continue;
            }

            // 1. Check prefixes
            boolean blindMove = false; // 'B' = move pen without drawing
            boolean noUpdate  = false; // 'N' = return to origin after draw

            if (c == 'B') {
                blindMove = true;
                i++;
                if (i < str.length()) c = str.charAt(i);
            } else if (c == 'N') {
                noUpdate = true;
                i++;
                if (i < str.length()) c = str.charAt(i);
            }

            // 2. Read Direction Letter (U, D, L, R, E, F, G, H)
            char dir = c;
            i++;

            // 3. Read distance number (e.g. "R50" -> 50, "R1" -> 1)
            int startNum = i;
            while (i < str.length() && Character.isDigit(str.charAt(i))) {
                i++;
            }

            double steps = 1.0; // default if no number follows
            if (i > startNum) {
                steps = Double.parseDouble(str.substring(startNum, i));
            }

            // Simply multiply steps by the drawUnit (no *4 gymnastics!)
            double dist = steps * du;

            // 4. Calculate target position
            double dx = 0.0;
            double dy = 0.0;

            switch (dir) {
                case 'U': dy = -dist; break;              // UP
                case 'D': dy = dist;  break;              // DOWN
                case 'L': dx = -dist; break;              // LEFT
                case 'R': dx = dist;  break;              // RIGHT
                case 'E': dx = dist;  dy = -dist; break;  // UP-RIGHT
                case 'F': dx = dist;  dy = dist;  break;  // DOWN-RIGHT
                case 'G': dx = -dist; dy = dist;  break;  // DOWN-LEFT
                case 'H': dx = -dist; dy = -dist; break;  // UP-LEFT
                default: break;
            }

            double targetX = currentX + dx;
            double targetY = currentY + dy;

            // Draw line unless 'B' (Blind) was requested
            if (!blindMove) {
                line(g, currentX, currentY, targetX, targetY);
            }

            // Update pen position unless 'N' (No-update) was requested
            if (!noUpdate) {
                currentX = targetX;
                currentY = targetY;
            }
        }
    }

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