package chapter1;

import kreativecomputergrafik.core.Cosys;
import java.awt.*;
import java.util.Scanner;

public class Color2 extends  Cosys{
    int stepx;
    int stepy;
    int nn;

    public Color2(int stepx, int stepy, int nn) {
        this.stepx = stepx;
        this.stepy = stepy;
        this.nn = nn;
    }

    @Override
    public void draw(Graphics g) {

        int mx = PIXX / 2;
        int my = PIXY / 2;
        Color color;

        for (int n = nn; n>=1; n-=1) {
            if (n%2 == 0) {
                color = Color.PINK;
            } else {
                color = Color.white;
            }

            box(g, color, mx - n*stepx, my + n*stepy, mx + n*stepx, my - n*stepy);
            g.fillRect(mx - n*stepx, my - n*stepy, n*stepx*2, n*stepy*2);

            // If you want circles instead of rectangles
//            g.setColor(color);
//            int r = n*stepx;
//            g.fillOval(mx - r, my - r, 2 * r, 2 * r);

        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("stepx: ");
        int stepx = scan.nextInt();

        System.out.println("stepy: ");
        int stepy = scan.nextInt();

        System.out.println("nn: ");
        int nn = scan.nextInt();

        Color2 rectangles = new Color2(stepx, stepy, nn);
        Cosys.launch(rectangles, "ALAAA");
        
    }
}
