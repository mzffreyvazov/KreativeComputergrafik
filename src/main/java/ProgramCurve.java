import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;

public class ProgramCurve extends Canvas {

    // --- Lines 20-40: COSYS parameters ---
    static final int PIXX = 640;
    static final int PIXY = 320;
    static final double SF = 1; // SCREENFACTOR (aspect ratio correction)

    // User coordinates and screen coordinates
    double X, Y;
    int XS, YS;
    int lastXS, lastYS; // Emulates BASIC's current graphics position for LINE -(XS,YS)

    // Mathematical parameters (Lines 1100, 1120)
    double R = 9.0;
    double TS = 0.0;
    double TE = 2.0 * Math.PI;
    int NN = 12;

    // Line 1300: DEF FNX(T) = R * COS(T)
    double fnX(double T) {
        return R * Math.cos(T);
    }

    // Line 1310: DEF FNY(T) = R * SIN(T)
    double fnY(double T) {
        return R * Math.sin(T);
    }

    // Subroutine 2000: 'TRANSF (transforms user coords X, Y -> screen pixels XS, YS)
    void gosub2000() {
        // Centers the origin (0,0) on the screen and applies scale and aspect ratio
        double scale = 14.0;
        XS = (int) (PIXX / 2.0 + X * scale);
        YS = (int) (PIXY / 2.0 - Y * scale * SF);
    }

    @Override
    public void paint(Graphics g) {
        // Line 20: SCREEN 105: CLS (Black background)
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, PIXX, PIXY);

        // Drawing color: white monochrome
        g.setColor(Color.WHITE);

        // Line 1010: 'GOSUB 2200: 'DRAW COSYS (commented out in original)

        // Line 1400: X = FNX(0): Y = FNY(0): GOSUB 2000: 'TRANSF
        X = fnX(0);
        Y = fnY(0);
        gosub2000();

        // Line 1410: PSET (XS, YS)
        lastXS = XS;
        lastYS = YS;
        g.drawLine(XS, YS, XS, YS);

        // Line 1420: FOR N = 1 TO NN
        for (int N = 1; N <= NN; N++) {
            // Line 1430: T = TS + N / NN * (TE - TS)
            double T = TS + ((double) N / NN) * (TE - TS);

            // Line 1440: X = FNX(T): Y = FNY(T): GOSUB 2000: 'TRANSF
            X = fnX(T);
            Y = fnY(T);
            gosub2000();

            // Line 1450: LINE -(XS, YS)  (draw from last position to new position)
            g.drawLine(lastXS, lastYS, XS, YS);

            // Update current position
            lastXS = XS;
            lastYS = YS;
        } // Line 1460: NEXT N
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MAIN PROGRAM CURVE");
        ProgramCurve canvas = new ProgramCurve();
        canvas.setSize(PIXX, PIXY);

        frame.add(canvas);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}