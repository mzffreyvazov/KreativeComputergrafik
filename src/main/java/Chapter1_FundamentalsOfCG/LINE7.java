package Chapter1_FundamentalsOfCG;

import java.awt.Graphics;
import java.util.Scanner;

public class LINE7 extends Cosys {

    int X1, Y1, X2, Y2;

    public LINE7(int x1, int y1, int x2, int y2) {
        this.X1 = x1;
        this.Y1 = y1;
        this.X2 = x2;
        this.Y2 = y2;
    }

    @Override
    public void draw(Graphics g) {
        pset(g, X1, Y1);
        line(g, X2, Y2);
    }

    public static void main(String[] args) {
        // Line 40: INPUT "X1,Y1,X2,Y2";X1,Y1,X2,Y2
        Scanner scanner = new Scanner(System.in);
        System.out.print("X1, Y1, X2, Y2 (e.g. 50 50 400 200): ");
        int x1 = scanner.nextInt();
        int y1 = scanner.nextInt();
        int x2 = scanner.nextInt();
        int y2 = scanner.nextInt();

        Cosys.launch(new LINE7(x1, y1, x2, y2), "Line 7");

    }
}