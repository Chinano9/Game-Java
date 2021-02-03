package map;

import graphics.Screen;

public abstract class Map {
    private int width, height;
    
    private int[] tiles;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        
        tiles = new int[width * height];
        generateMap();
    }

    public Map(String path) {
        loadMap(path);
    }
    
    private void generateMap(){
        
    }
    
    private void loadMap(String path){
        
    }
    
    public void update(){
        
    }
    
    public void print(int compensationX, int compensationY, Screen screen){
        
    }
    
}
