package chapter1;

import java.awt.Graphics;
import java.util.Scanner;

import kreativecomputergrafik.core.Cosys;

public class Line4 extends Cosys {
    int stepX;

    public Line4(int stepX) {
        this.stepX = stepX;
    }

    @Override
    public void draw(Graphics g) {
        // Only the actual algorithm from lines 50-80!
        for (int X1 = 10; X1 <= PIXX - 10; X1 += stepX) {
            for (int X2 = 10; X2 <= PIXX - 10; X2 += stepX) {
                line(g, X1, 10, X2, PIXY - 10);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("STEPX (e.g. 20): ");
        int stepX = scanner.nextInt();

        Cosys.launch(new Line4(stepX), "LINE4");
    }
}