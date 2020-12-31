/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graphics;

public final class Sprite {
    private final int size;
    
    private int x, y;
    public int [] pixels;
    private final SpriteSheet sheet;
    
    //Sprite collection
    
    //Collection end

    public Sprite(final int size, final int column, final int row, final SpriteSheet sheet) {
        this.size = size;
        
        pixels = new int [size * size];
        this.x = column * size;
        this.y = row * size;
        this.sheet = sheet;
        
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < 10; x++) {
                pixels[x + y * size] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.getWidth()];
            }
        }
    }
    
    
}
