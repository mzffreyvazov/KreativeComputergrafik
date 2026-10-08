package Chapter1_FundamentalsOfCG;

import java.awt.*;
import java.util.Scanner;

public class Circle4 extends Cosys {

    int stepr;
    int dx;

    public Circle4(int stepr, int dx) {
        this.stepr = stepr;
        this.dx = dx;
    }


    @Override
    public void draw(Graphics g) {
        for (int r = 1; r<= Cosys.PIXY/2; r+=stepr) {
            g.setColor(Color.PINK);
            circle(g, ((double) Cosys.PIXX /2) - dx, (double) Cosys.PIXY /2 - dx, r);
            circle(g, ((double) Cosys.PIXX /2) + dx, (double) Cosys.PIXY /2 + dx, r);
            circle(g, ((double) Cosys.PIXX /2) + dx, (double) Cosys.PIXY /2 - dx, r);
            circle(g, ((double) Cosys.PIXX /2) - dx, (double) Cosys.PIXY /2 + dx, r);


        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner((System.in));

        System.out.println("stepr: ");
        int stepr = scan.nextInt();

        System.out.println("dx");
        int dx = scan.nextInt();

        Circle4 concentricCircles = new Circle4(stepr, dx);

        Cosys.launch(concentricCircles, "NEW");
    }
}
