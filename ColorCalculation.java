package com.stsrgazer.dizer;

public class ColorCalculation {
    private int[] colors;
    private double sensitivity;

    public ColorCalculation(int[] colors, double sensitivity) {
        this.colors = colors;
        this.sensitivity = sensitivity;
    }

    public int[] createPixel(int[] errors, int color) {
        int[] channels = new int[3];
        channels[0] = (0x000000FF & color >> 16) + (int) (errors[0] * sensitivity);
        channels[1] = (0x000000FF & color >> 8) + (int) (errors[1] * sensitivity);
        channels[2] = (0x000000FF & color) + (int) (errors[2] * sensitivity);
        return channels;
    }



    public int getColor(int[] pixel, int[] errors) {
        int n = 0;
        int l0 = Integer.MAX_VALUE;
        for (int i = 0; i < colors.length; i++) {
            int dr = pixel[0] - (0x000000FF & colors[i] >> 16);
            int dg = pixel[1] - (0x000000FF & colors[i] >> 8);
            int db = pixel[2] - (0x000000FF & colors[i]);
            int l = dr*dr + dg*dg + db*db;
            if (l <= l0) {
                l0 = l;
                errors[0] = dr;
                errors[1] = dg;
                errors[2] = db;
                n = i;
            }
        }
        return colors[n];
    }


}
