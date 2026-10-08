import java.awt.*;
import java.util.Scanner;

public class Circle2 extends Cosys{

    int stepx;
    int stepy;
    int r;

    public Circle2(int stepx, int stepy, int r) {
        this.stepx = stepx;
        this.r = r;
        this.stepy = stepy;
    }

    @Override
    public void draw(Graphics g) {
        for (int x = 2*r; x<=PIXX-2*r; x+=stepx) {
            for (int y = 2*r; y<=PIXY-2*r; y+=stepy) {
                circle(g, x, y, r);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("step x: ");
        int stepx = scanner.nextInt();

        System.out.println("step y: ");
        int stepy = scanner.nextInt();

        System.out.println("r: ");
        int r = scanner.nextInt();

        Circle2 circles = new Circle2(stepx, stepy, r);

        Cosys.launch(circles, "Circles");

    }
}
