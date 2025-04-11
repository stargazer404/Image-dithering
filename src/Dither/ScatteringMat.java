package Dither;

/**
 * The ScatteringMat object contains a buffer in which error values are stored,
 * and also defines the rule by which the error will be propagated.
 *
 */
public abstract class ScatteringMat{
    private int[][][] errorMat;
    private int div = 1;
    private int width = 0, height = 0;

    /**
     * Creates a new ScatteringMat object with the specified error buffer height.
     *
     * @param matHeight error buffer height
     * @param div normalization coefficient
     */
    public ScatteringMat(int matHeight, int div) {
        this.div = div;
        height = matHeight;
    }

    /**
     * Returns the height of the error buffer array.
     *
     * @return error buffer height
     */
    public int getHeight() {
        return height;
    }


    /**
     * Creates a new empty error buffer of the given width.
     *
     * @param width error buffer width
     */
    public void setMat(int width) {
        this.width = width;
        errorMat = new int[height][width][3];
    }

    /**
     * Shifts the error array up one row.
     */
    public void shiftY() {
        for (int i = 0; i < height - 1; i++) {
            errorMat[i] = errorMat[i + 1];
        }
        errorMat[height - 1] = new int[width][3];
    }

    /**
     * Adds the error value to the corresponding error buffer cell.
     *
     * @param x
     * @param y
     * @param errorR error in red channel
     * @param errorG error in green channel
     * @param errorB error in blue channel
     */
    public void addError(int x, int y, int errorR, int errorG, int errorB) {
        if (x >= 0 && x < width) {
            errorMat[y][x][0] += errorR;
            errorMat[y][x][1] += errorG;
            errorMat[y][x][2] += errorB;
        }
    }

    /**
     * Transforms the original color according to the error of the given pixel
     *
     * @param originalRGB original color
     * @param x pixel position in array
     * @return changed color
     */
    public int getChangedColor(int originalRGB, int x){
        int[] errors = errorMat[0][x];
        int newRGB = 0;
        for (int i = 0; i < 3; i++) {
            int c = (originalRGB >> (2 - i)*8) & 0x000000FF;
            c += errors[i]/div;
            if (c < 0) {
                c = 0;
            } else if (c > 255) {
                c = 255;
            }
            newRGB  = newRGB << 8;
            newRGB = newRGB | c;
        }
        return newRGB;
    }

    /**
     * Resets the state of an object, freeing up resources.
     */
    public void release() {
        errorMat = null;
    }

    /**
     * Propagates error according to the specified rule.
     * The error propagation rule is contained within this method.
     *
     * @param x the position of the point relative to which the error will propagate
     * @param errorR error in red channel
     * @param errorG error in green channel
     * @param errorB error in blue channel
     */
    public abstract void setError(int x, int errorR, int errorG, int errorB);

}
