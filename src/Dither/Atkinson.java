package Dither;

/**
 * Implements Atkinson's rule for error propagation.
 *
 */
public class Atkinson extends ScatteringMat {

    public Atkinson() {
        super(3, 8);
    }
    @Override
    public void setError(int x, int errorR, int errorG, int errorB) {
        addError(x + 1, 0, errorR, errorG, errorB);
        addError(x + 2, 0, errorR, errorG, errorB);
        addError(x - 1, 1, errorR, errorG, errorB);
        addError(x, 1, errorR, errorG, errorB);
        addError(x + 1, 1, errorR, errorG, errorB);
        addError(x, 2, errorR, errorG, errorB);
    }
}
