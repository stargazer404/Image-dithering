package com.stsrgazer.dizer;

public class ScatteringMat {
    private int[][] mat;
    private int divider;

    public final int offsetX, h, w;

    public ScatteringMat(int[][] mat, int divider) {
        this.divider = divider;
        h = mat.length;
        w = mat[0].length;
        offsetX = w/2;
        this.mat = new int[h][w];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                this.mat[y][x] = mat[h - 1 - y][w - 1 - x];
            }
        }
    }

    public int getDivider() {
        return divider;
    }

    public int[] calculationError(int[][][] subErrorMat) {
        int cn = subErrorMat[0][0].length;
        int[] error = new int[cn];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int k = mat[y][x];
                if (k != 0) {
                    for (int c = 0; c < cn; c++) {
                        error[c] += subErrorMat[y][x][c] * k;
                    }
                }
            }
        }
        for (int c = 0; c < cn; c++) {
            error[c] /= divider;
        }
        return error;
    }

}
