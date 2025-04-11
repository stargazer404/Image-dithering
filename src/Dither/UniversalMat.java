package Dither;

/**
 * This object provides the user with a universal way to specify an error propagation rule.
 *
 */
public class UniversalMat extends ScatteringMat{

    private int[][] smat;
    private int x0;
    private int matWidth;

    /**
     * Creates an UniversalMat with the specified parameters.
     *
     * @param mat matrix of coefficients
     * @param x0 offset of the origin
     * @param div divider
     */
    public UniversalMat(int[][] mat, int x0, int div) {
        super(mat.length, div);
        smat = mat;
        this.x0 = x0;
        matWidth = mat[0].length - x0;
    }

    @Override
    public void setError(int x, int errorR, int errorG, int errorB) {
        for (int y = 0; y < getHeight(); y++) {
           for (int sx = -x0;  sx < matWidth; sx++) {
               int px = sx + x0;
               if (smat[y][px] != 0) {
                   addError(x + sx, y, errorR*smat[y][px], errorG*smat[y][px], errorB*smat[y][px]);
               }
           }
        }
    }
}
