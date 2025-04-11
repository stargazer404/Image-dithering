package Dither;

/**
 * A simple implementation of the palette interface.
 * Contains a set of colors in explicit form.
 * The choice of a suitable color is made based on its proximity to the current color space in RGB.
 * The search for the closest color is carried out by the exhaustive search method.
 *
 */
public class ColorsCollection implements Palette {

    private ColorComponent[] colors;

    /**
     * Creates a ColorsCollection with the given set of colors.
     *
     * @param colors set of colors
     */
    public ColorsCollection(ColorComponent[] colors) {
        this.colors = colors;
    }

    @Override
    public ColorComponent chooseColor(int rgb, int[] errors) {
        int red = (rgb >> 16) & 0x000000ff;
        int green = (rgb >> 8) & 0x000000ff;
        int blue = rgb & 0x000000ff;
        int l0 = Integer.MAX_VALUE;
        int n = 0;
        for (int i = 0; i < colors.length; i++) {
            int er = red - colors[i].getRed();
            int eg = green - colors[i].getGreen();
            int eb = blue - colors[i].getBlue();
            int l = er*er + eg*eg + eb*eb;
            if (l < l0) {
                l0 = l;
                errors[0] = er;
                errors[1] = eg;
                errors[2] = eb;
                n = i;
            }
        }
        return colors[n];
    }
}
