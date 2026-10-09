package chapter1;

import java.awt.*;
import java.util.Scanner;

import kreativecomputergrafik.core.Cosys;

public class Circle3 extends Cosys {

    int stepr;

    public Circle3(int stepr) {
        this.stepr = stepr;
    }


    @Override
    public void draw(Graphics g) {
        for (int r = 1; r<= Cosys.PIXY/2; r+=stepr) {
            g.setColor(Color.GREEN);
            circle(g, (double) Cosys.PIXX /2, (double) Cosys.PIXY /2, r);

        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner((System.in));

        System.out.println("stepr: ");
        int stepr = scan.nextInt();

        Circle3 concentricCircles = new Circle3(stepr);

        Cosys.launch(concentricCircles, "NEW");
    }
}
