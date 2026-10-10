package chapter1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import kreativecomputergrafik.core.Cosys;

public class Draw3 extends Cosys {

    private double du;
    // Line 70: Permanent command string
    private String pol = "R1U1L1D1";

    public Draw3(double du) {
        this.du = du;
    }

    @Override
    public void draw(Graphics g) {
        // Line 40: CIRCLE (PIXX/2,PIXY/2),1
        pset(g, MX, MY);

        // Line 80: DRAW "XPOL$;"
        bdraw(g, pol, du);
    }

    public static void main(String[] args) {
        // Line 50: INPUT "DRAW UNIT"; DU
        Scanner scanner = new Scanner(System.in);
        System.out.print("DRAW UNIT (e.g. 20): ");
        double du = scanner.nextDouble();

        Cosys.launch(new Draw3(du), "DRAW3");
    }
}