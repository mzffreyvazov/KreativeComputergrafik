package chapter1;

import kreativecomputergrafik.core.Cosys;

import java.awt.*;
import java.util.Scanner;

public class Color3 extends Cosys {
    int stepx;
    int stepy;
    int r;

    public Color3(int stepx, int stepy, int r) {
        this.stepx = stepx;
        this.stepy = stepy;
        this.r = r;
    }

    @Override
    public void draw(Graphics g) {
        // Line 45: LINE (R,R)-(PIXX-R, PIXY-R),1,BF (Filled box in background)
        g.setColor(Color.blue);
        g.fillRect(r, r, PIXX - 2 * r, PIXY - 2 * r);

        // Lines 50-90: Nested loops to draw circles
        g.setColor(Color.BLACK);
        for (int x = 2 * r; x <= PIXX - 2 * r; x += stepx) {
            for (int y = 2 * r; y <= PIXY - 2 * r; y += stepy) {
                // Line 70: CIRCLE (X,Y),R,0
                circle(g, x, y, r);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("STEPX: ");
        int stepx = scan.nextInt();

        System.out.println("STEPY: ");
        int stepy = scan.nextInt();

        System.out.println("R: ");
        int r = scan.nextInt();

        Color3 circles = new Color3(stepx, stepy, r);
        Cosys.launch(circles, "COLOR3");
    }
}