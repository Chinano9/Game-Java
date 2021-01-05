
package graphics;

public final class Screen {
    private final int width, height;
    
    public final int [] pixels;
    
    //Temporal
    private final static int SPRITE_SIZE = 32; 
    private final static int SPRITE_MASK = SPRITE_SIZE - 1;
    //Temporal End

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        
        pixels = new int [width * height];
    }
    
    public void clean (){
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }
    
    public void print(final int compensationX, final int compensationY){
        for (int y = 0; y < height; y++) {
            int positionY = y + compensationY;
            
            if (positionY < 0 || positionY >= height) {
                continue;
            }
            for (int x = 0; x < width; x++) {
                int positionX = x + compensationX;
                
                if (positionX < 0 || positionX >= width) {
                    continue;
                }
                
                pixels[positionX + positionY * width] = Sprite.asphalt.pixels[(x & SPRITE_MASK) + (y & SPRITE_MASK) * SPRITE_SIZE];
                
            }
        }
        

    }
    
}
