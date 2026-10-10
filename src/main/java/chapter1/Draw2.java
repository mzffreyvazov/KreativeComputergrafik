package chapter1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import kreativecomputergrafik.core.Cosys;

public class Draw2 extends Cosys {

    private ArrayList<String> commands = new ArrayList<>();
    private double du;

    public Draw2(double du, ArrayList<String> commands) {
        this.du = du;
        this.commands = commands;
    }

    @Override
    public void draw(Graphics g) {
        // Line 40: Center circle
        circle(g, MX, MY, 5);
        pset(g, MX, MY);

        // Lines 50-80: Execute each entered command string
        for (String cmd : commands) {
            bdraw(g, cmd, du);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> cmds = new ArrayList<>();

        // Line 45: INPUT "DRAW UNIT"; DU
        System.out.print("DRAW UNIT (e.g. 10): ");
        double du = scanner.nextDouble();

        // Lines 50-80: Interactive loop
        String more = "Y";
        while (more.equalsIgnoreCase("Y")) {
            System.out.print("POLYGON: ");
            String pol = scanner.next();
            cmds.add(pol);

            System.out.print("ONCE MORE (Y/N): ");
            more = scanner.next();
        }

        Cosys.launch(new Draw2(du, cmds), "DRAW2");
    }
}