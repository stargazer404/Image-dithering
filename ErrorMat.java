package com.stsrgazer.dizer;

public class ErrorMat {
    private int[][][] errors;
    private int height, width, colorDimension;

    public ErrorMat(int width, int height, int colorDimension) {
        this.height = height;
        this.width = width;
        this.colorDimension = colorDimension;
        errors = new int[height][width][colorDimension];
    }

    public int[] getError(int x, int y) {
        return errors[y][x];
    }
    public void setError(int x, int y, int[] error) {
        for (int i = 0; i < colorDimension; i++) {
            errors[y][x][i] = error[i];
        }
    }

    public int[][][] getSubMat(int x0 , int y0, int width, int height) {
        int[][][] subMat = new int[height][width][colorDimension];
        for (int y = 0; y < height; y++) {
            int yn = y + y0;
            if (yn >= 0 && yn < this.height) {
                for (int x = 0; x < width; x++) {
                    int xn = x + x0;
                    if (xn >= 0 && xn < this.width) {
                        for (int i = 0; i < colorDimension; i++) {
                            subMat[y][x][i] = errors[yn][xn][i];
                        }
                    }
                }
            }
        }
        return subMat;
    }


}
