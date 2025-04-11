package Dither;

/**
 * The palette object contains a set of colors available for selection.
 * The algorithm for finding the optimal color is specified in a single method chooseColor().
 *
 */
public interface Palette {

    /**
     * Selects the ColorComponent from the set closest to colorRGB and calculates the errors.
     *
     * @param colorRGB original color
     * @param errors array in which errors will be written
     * @return closest color to colorRGB from the set
     */
    ColorComponent chooseColor(int colorRGB, int[] errors);

}
