import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;

public class Line2 extends Canvas {

    // Lines 20-30
    static final int PIXX = 640;
    static final int PIXY = 320;

    int STEPX;
    int STEPY;

    public Line2(int stepX, int stepY) {
        this.STEPX = stepX;
        this.STEPY = stepY;
        setSize(PIXX, PIXY);
    }

    @Override
    public void paint(Graphics g) {
        // Line 20: CLS (Black screen)
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, PIXX, PIXY);

        // Line 50-70: FOR X=10 TO PIXX-10 STEP STEPX ... NEXT X
        g.setColor(Color.WHITE);
        for (int X = 10; X <= PIXX - 10; X += STEPX) {
//            for(int Y = 10; Y <=PIXY - 10; Y += STEPY) {
//                g.drawLine(X, 10, X, Y);
//            }
            // Line 60: PSET (X, PIXY/2)
            g.drawLine(X, 10, X+10, PIXY-10);

        }
    }

    public static void main(String[] args) {
        // Line 40: INPUT "STEPX";STEPX
        Scanner scanner = new Scanner(System.in);
        System.out.println("STEPX: ");
        int stepX = scanner.nextInt();

        System.out.println("STEPY: ");
        int stepY = scanner.nextInt();

        JFrame frame = new JFrame("PSET2");
        Line2 canvas = new Line2(stepX, stepY);

        frame.add(canvas);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}