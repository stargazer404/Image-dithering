package Dither;

/**
 * Implements Floyd-Steinberg rule for error propagation.
 *
 */
public class FloydSteinberg extends ScatteringMat {

    public FloydSteinberg() {
        super(2, 16);
    }
    @Override
    public void setError(int x, int errorR, int errorG, int errorB) {
        addError(x + 1, 0, errorR*7, errorG*7, errorB*7);
        addError(x - 1, 1, errorR*3, errorG*3, errorB*3);
        addError(x, 1, errorR*5, errorG*5, errorB*5);
        addError(x + 1, 1, errorR, errorG, errorB);
    }
}
