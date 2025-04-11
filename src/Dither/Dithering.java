package Dither;

import java.awt.image.BufferedImage;

/**
 * The Dithering class performs image dithering using the error diffusion method.
 * The Dithering contains objects ScatteringMat and Palette.
 * Only 8-bit RGB image is suitable for conversion.
 *
 * @see ScatteringMat
 * @see Palette
 */
public class Dithering {

    public static final ScatteringMat MAT_FloydSteinberg = new FloydSteinberg();
    public static final ScatteringMat MAT_Stucki = new UniversalMat(new int[][]{{0, 0, 0, 8, 4}, {2, 4, 8, 4, 2}, {1, 2 ,4, 2, 1}}, 2, 48);
    public static final ScatteringMat MAT_JarvisJudiceNinke = new UniversalMat(new int[][]{{0, 0, 0, 7, 5}, {3, 5, 7, 5, 3}, {1, 3 ,5, 3, 1}}, 2, 48);
    public static final ScatteringMat MAT_Sierra = new UniversalMat(new int[][]{{0, 0, 0, 5, 3}, {2, 4, 5, 2, 4}, {0, 2, 3, 2, 0}}, 2, 32);
    public static final ScatteringMat MAT_Atkinson = new Atkinson();


    private ScatteringMat mat;
    private Palette palette;

    /**
     * Creates a Dithering object with the specified color palette and ScatteringMat.
     *
     * @param colors palette that will be used by this object
     * @param mat ScatteringMat that will be used by this object
     */
    public Dithering(Palette colors, ScatteringMat mat) {
        this.mat = mat;
        this.palette = colors;
    }

    /**
     * Creates a Dithering object with the specified color palette.
     *
     * @param colors set of colors available to this object
     */
    public Dithering(ColorComponent[] colors) {
        mat = new FloydSteinberg();
        this.palette = new ColorsCollection(colors);
    }

    /**
     * Sets the ScatteringMat for this object.
     *
     * @param mat ScatteringMat that will be used by this object
     */
    public void setMat(ScatteringMat mat) {
        this.mat = mat;
    }

    /**
     * Sets the color palette for this object.
     *
     * @param chooser palette that will be used by this object
     */
    public void setPalette(Palette chooser) {
        this.palette = chooser;
    }


    /**
     * Translate the image into the color palette of this Dithering object.
     *
     * @param image image to be converted
     * @return transformed image in which each pixel contains the signature of the corresponding ColorComponent
     */
    public BufferedImage filter(BufferedImage image) {
        int h = image.getHeight(), w = image.getWidth();
        BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        mat.setMat(w);
        int[] errors = new int[3];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int rgb = mat.getChangedColor(image.getRGB(x, y), x);
                int newsig = palette.chooseColor(rgb, errors).getSignature();
                mat.setError(x, errors[0], errors[1], errors[2]);
                result.setRGB(x, y, newsig);
            }
            mat.shiftY();
        }
        mat.release();
        return result;
    }

}

