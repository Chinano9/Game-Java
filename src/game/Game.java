
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
    
    @Override
    public void run(){
        while (inRun) {
            
        }
    }

}
