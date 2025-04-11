package Dither;

import java.awt.*;

/**
 * This object is a light with a unique signature.
 *
 */
public class ColorComponent extends Color {

    private int signature;

    /**
     * Creates a ColorComponent object with the given color channel values and signature.
     *
     * @param r red channel value
     * @param g green channel value
     * @param b blue channel value
     * @param signature signature
     */
    public ColorComponent(int r, int g, int b, int signature) {
        super((r << 16) | (g << 8) | b, false);
        this.signature = signature;
    }

    /**
     * Creates a ColorComponent object with the given color and signature.
     *
     * @param rgb color
     * @param signature signature
     */
    public ColorComponent(int rgb, int signature) {
        super(rgb, false);
        this.signature = signature;
    }

    /**
     * Creates a ColorComponent object with the given color.
     * The signature value matches the rgb.
     *
     * @param rgb color
     */
    public ColorComponent(int rgb) {
        super(rgb, false);
        this.signature = rgb;
    }

    /**
     * Returns the signature value.
     *
     * @return signature
     */
    public int getSignature() {
        return signature;
    }

}
