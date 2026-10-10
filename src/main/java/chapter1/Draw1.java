package chapter1;

import java.awt.*;
import java.util.Scanner;
import kreativecomputergrafik.core.Cosys;

public class Draw1 extends Cosys {

    private String command;


    public Draw1(String command) {
        this.command = command;
    }

    @Override
    public void draw(Graphics g) {
        int mx = PIXX / 2;
        int my = PIXY / 2;

        circle(g, mx, my, 5);

        bdraw(g, command, 1.0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Command: ");
        String command = scanner.next();

        Draw1 letsee = new Draw1(command);

        Cosys.launch(letsee, "title");

    }


}
