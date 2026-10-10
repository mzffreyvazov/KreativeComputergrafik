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

        pset(g, MX, MY);
        bdraw(g, command, 10.0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Command: ");
        String command = scanner.next();

        Draw1 letsee = new Draw1(command);

        Cosys.launch(letsee, "title");

    }


}
