
package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet{
    private final String root;
    private final int width, height;
    public final int [] pixels;
    
    //Sprite sheet collection.
    
    //Collection end.

    public SpriteSheet(final String root, final int width,final int height) throws IOException {
        this.width = width;
        this.height = height;
        this.root = root;
        
        pixels = new int [width * height];
        try {
        	BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(root));
        	image.getRGB(0, 0, width, height, pixels, 0, width);
        }catch(IOException e){
        	e.printStackTrace();
        }
        
        
    }

    public int getWidth() {
        return width;
    }
    
}