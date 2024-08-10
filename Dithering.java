package com.stsrgazer.dizer;

import java.awt.image.BufferedImage;

public class Dithering {

    public static final ScatteringMat FloydSteinberg = new ScatteringMat(new int[][]{{0, 0, 7}, {3, 5, 1}}, 16);
    public static final ScatteringMat JarvisJudiceNinke = new ScatteringMat(new int[][]{{0, 0, 0, 7, 5}, {3, 5, 7, 5, 3}, {1, 3 ,5, 3, 1}}, 48);
    public static final ScatteringMat Stucki = new ScatteringMat(new int[][]{{0, 0, 0, 8, 4}, {2, 4, 8, 4, 2}, {1, 2 ,4, 2, 1}}, 42);
    public static final ScatteringMat Atkinson = new ScatteringMat(new int[][]{{0, 0, 0, 1, 1}, {0, 1, 1, 1, 0}, {0, 0 ,1, 0, 0}}, 8);
    public static final ScatteringMat Burkes = new ScatteringMat(new int[][]{{0, 0, 0, 8, 4}, {2, 4, 8, 2, 4}}, 32);
    public static final ScatteringMat Sierra = new ScatteringMat(new int[][]{{0, 0, 0, 5, 3}, {2, 4, 5, 2, 4}, {0, 2, 3, 2, 0}}, 32);
    public static final ScatteringMat ThoRowSierra = new ScatteringMat(new int[][]{{0, 0, 0, 4, 3}, {1, 2, 3, 2, 1}}, 16);
    public static final ScatteringMat SierraLite = new ScatteringMat(new int[][]{{0, 0, 2}, {1, 1, 0}}, 4);



    public static BufferedImage RGBDithering(BufferedImage image, int[] colors, ScatteringMat mat, double sensitivity) {
        int h = image.getHeight(), w = image.getWidth();
        ErrorMat errorRGB = new ErrorMat(w, h, 3);
        BufferedImage result = new BufferedImage(w, h, image.getType());
        ColorCalculation col = new ColorCalculation(colors, sensitivity);
        int xsm = mat.offsetX, ysm = mat.h, wsm = mat.w;

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int[] error = mat.calculationError(errorRGB.getSubMat(x - xsm, y - ysm + 1, wsm, ysm));
                int[] pixel = col.createPixel(error, image.getRGB(x, y));
                int[] errorBuf = new int[3];
                int color = col.getColor(pixel, errorBuf);
                result.setRGB(x, y, color);
                errorRGB.setError(x, y, errorBuf);
            }
        }
        return result;
    }
    
}

