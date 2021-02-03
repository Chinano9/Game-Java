package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {

    private final String path;
    private final int width, height;
    public final int[] pixels;

    //Sprite sheet collection.
    public static SpriteSheet desert = new SpriteSheet("/textures/SpritesDesert.png", 320, 320);
    //Collection end.

    public SpriteSheet(final String path, final int width, final int height) {
        this.width = width;
        this.height = height;
        this.path = path;

        pixels = new int[width * height];
        BufferedImage image;
        try {
            image = ImageIO.read(SpriteSheet.class.getResource(path));
            image.getRGB(0, 0, width, height, pixels, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int getWidth() {
        return width;
    }

}
