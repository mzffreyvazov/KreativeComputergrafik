package chapter1;

import java.awt.*;

import kreativecomputergrafik.core.Cosys;

public class Draw4 extends Cosys {

    // Line 40: DU=10
    private double DU = 10.0; // Drawing Unit

    // Line 50: NX=5: NY=5: KLX=NX*DU: KLY=NY*DU
    private int NX = 22; // Tile Size in Units in X direction
    private int NY = 12; // Tile Size in Units in Y direction
    private double TWX = NX * DU; // TWX: Single TIle Width in X direction
    private double TWY = NY * DU; // TWY: SIngle Tile Width in Y direction

    // Line 60: KKX=INT(PIXX/KLX): KKY=INT(PIXY/KLY)
    private int TNX = (int) (PIXX / TWX); // TNX: Number of Tiles in X Direction
    private int TNY = (int) (PIXY / TWY); // TNY: Number of Tiles in Y Direction

    // Line 70: Leftover centering margins
    private double EPX = PIXX - TNX * TWX; // EPX: Empty Pixels in X direction
    private double EPY = PIXY - TNY * TWY; // EPY: Empty Pixels in Y direction

    // Line 90: The geometric tile motif
    private String command = "BR3L2G1D1E2G1R2E1R2BR1R2L2G1L2G1L2G1D1BD1D1U1R1U2R2D1L1D1R2U3R2D1L1D1R2U3R2D1L1D1R2U3R1R1D3R2U1L1U1R2D3R2U1L1U1R2D3R2U1L1U1R2D2R1D1D1L1D2L2U1R1U1L2D3L2U1R1U1L2D3L2U1R1U1L2D3L1L1U3L2D1R1D1L2U3L2D1R1D1L2U3L2D1R1D1L2U2L1U1BD2D1F1R2F1R2F1R2BL3L2H1L2H1D1F1R2L1H2BD2BR13R2E1R2E1R2E1U1BD1G1L2G1L2G1L2BR3R2E1R2E1D1G1L2R1E1(BF1BU8U1H1L2H1L2H1L2BR3R2F1R2F1U1H1L2R1F1)BL10D2(G1L2G1L2G1L3BR4R2E1R2E1D1G1L2R1E1(BE1BU1F1R2F1R2F1R3BL4L2H1L2H1D1F1R2L1H1(((BF1BR5G1L2G1L2G1D2BU3U1E1R2BR1G1L2G1E2(((BG2BD1H1L2H1L2H1L3BR4R2F1R2F1U1H1L2R1F1";

    @Override
    public void draw(Graphics g) {
        // Lines 100-150: Double loop through all grid cells
        for (int KX = 0; KX < TNX; KX++) {
            for (int KY = 0; KY < TNY; KY++) {
                // Line 120: Position pen at top-left corner of tile (KX, KY)
                double startX = EPX / 2.0 + KX * TWX;
                double startY = EPY / 2.0 + KY * TWY;
                pset(g, startX, startY);

                // Line 130: DRAW "XPOL$;"
                bdraw(g, command, DU);

            } // Line 140: NEXT KY
        } // Line 150: NEXT KX
    }

    public static void main(String[] args) {
        Cosys.launch(new Draw4(), "DRAW4 - Geometric Wallpaper");
    }
}