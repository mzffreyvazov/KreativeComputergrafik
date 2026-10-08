package Chapter1_FundamentalsOfCG;

import java.awt.*;
import java.util.Scanner;

public class LINE6 extends Cosys {
    int stepx;
    int stepy;
    int nn;

    public LINE6(int stepx, int stepy, int nn) {
        this.stepx = stepx;
        this.stepy = stepy;
        this.nn = nn;
    }

    @Override
    public void draw(Graphics g) {
        int mx = PIXX / 2;
        int my = PIXY / 2;

        for (int n = 1; n<nn; n++) {
            box(g, mx - n*stepx, my - n*stepy, mx + n*stepx, my + n*stepy);
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Step X: ");
        int stepx = scanner.nextInt();

        System.out.println("Step Y: ");
        int stepy = scanner.nextInt();

        System.out.println("NN: ");
        int nn = scanner.nextInt();

        LINE6 canvas = new LINE6(stepx, stepy, nn);
        Cosys.launch(canvas, "Chapter1_FundamentalsOfCG.LINE6");
    }
}
