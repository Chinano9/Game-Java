package map.tiles;

import graphics.Screen;
import graphics.Sprite;

public abstract class Tile {
    public int x, y;
    
    public Sprite sprite;
    
    public Tile(Sprite sprite){
        this.sprite = sprite;
    }
    
    public void print(int x, int y, Screen screen){
        
    }
    
    public boolean solid(){
        return false;
    }
}
