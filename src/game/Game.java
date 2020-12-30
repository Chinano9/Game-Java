
package game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{

    private static final long serialVersionUID = 1L;
    
    private static final int WIDE = 800, HEIGHT = 600;
    private static final String NAME = "Game";
    
    private static int ups = 0, fps = 0;
    
    private static volatile boolean inRun = false;
    
    private static JFrame window;
    private static Thread thread;
    
    private Game(){
        setPreferredSize(new Dimension(WIDE,HEIGHT));
        
        window = new JFrame(NAME);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLayout(new BorderLayout());
        window.add(this, BorderLayout.CENTER);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
    
    private synchronized void start(){
        inRun = true;
        
        thread = new Thread(this, "Graphics");
        thread.start();
    }
    
    private synchronized void stop(){
        inRun = false;
        
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    private void update(){
        ups++;
    }
    
    private void print(){
        fps++;
    }
    
    @Override
    public void run(){
        final int NS_BY_SECOND = 1000000000;
        final byte UPS_TARGET = 60;
        final double NS_BY_UPDATE = NS_BY_SECOND / UPS_TARGET;
        
        long referenceUpdate = System.nanoTime(), referenceCounter = System.nanoTime();
        
        double currentTime, delta = 0;
        
        while (inRun) {
            final long bucleStart = System.nanoTime();
            
            currentTime = bucleStart - referenceUpdate;
            referenceUpdate = bucleStart;
            
            delta += currentTime / NS_BY_UPDATE;
            while (delta >= 1) {
                update();
                delta--;
            }
            print();
            
            if (System.nanoTime() - referenceCounter > NS_BY_SECOND) {
                window.setTitle(NAME + " || UPS: " + ups + " || FPS: " + fps);
                ups = 0;
                fps = 0;
                referenceCounter = System.nanoTime();
            }
        }
    }

}
