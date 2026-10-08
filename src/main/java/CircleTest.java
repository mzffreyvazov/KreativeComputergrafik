import java.awt.*;

public class CircleTest extends Cosys {

    int x;
    int y;
    int r;


    public CircleTest(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
    @Override
    public void draw(Graphics g) {
        circle(g, x, y, r);
    }

    public static void main(String[] args) {
        int mx = PIXX / 2;
        int my = PIXY / 2;
        int r = 300;
        CircleTest circle = new CircleTest(mx, my, r);
        Cosys.launch(circle, "Yeah");
    }
}
