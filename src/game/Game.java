
package game;

import control.Keyboard;
import graphics.Screen;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{

    private static final long serialVersionUID = 1L;
    
    private static final int WIDE = 800, HEIGHT = 600;
    private static final String NAME = "Game";
    
    private static int ups = 0, fps = 0;
    
    private static int x = 0, y = 0;
    
    private static volatile boolean inRun = false;
    
    private static JFrame window;
    private static Thread thread;
    private static Keyboard keyboard;
    private static Screen screen;
    
    private static BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private static int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    
    private Game(){
        setPreferredSize(new Dimension(WIDE,HEIGHT));
        
        screen = new Screen(WIDTH,HEIGHT);
        
        keyboard = new Keyboard();
        addKeyListener(keyboard);
        
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
        keyboard.update();
        
        if (keyboard.up) {
            System.out.println("Up");
        }
        if (keyboard.down) {
            System.out.println("Down");
        }
        if (keyboard.left) {
            System.out.println("Left");
        }
        if (keyboard.right) {
            System.out.println("Right");
        }
        
        ups++;
    }
    
    private void print(){
    	BufferStrategy strategy = getBufferStrategy();
    	
    	if(strategy == null) {
    		createBufferStrategy(3);
    		return;
    	}
    	
    	screen.clean();
    	screen.print(x, y);
    	
    	System.arraycopy(screen.pixels, 0, pixels, 0, pixels.length);
    	
//    	for(int i = 0; i < pixels.length; i++) {
//    		pixels[i] = screen.pixels[i];
//    	}
    	
    	Graphics g = strategy.getDrawGraphics();
    	
    	g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    	g.dispose();
    	
    	strategy.show();
    	
        fps++;
    }
    
    @Override
    public void run(){
        final int NS_BY_SECOND = 1000000000;
        final byte UPS_TARGET = 60;
        final double NS_BY_UPDATE = NS_BY_SECOND / UPS_TARGET;
        
        long referenceUpdate = System.nanoTime(), referenceCounter = System.nanoTime();
        
        double currentTime, delta = 0;
        
        requestFocus();
        
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
