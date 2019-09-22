
package mazerunner;

import com.sun.javafx.iio.ImageLoader;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;


import mazerunner.display.Display;
import mazerunner.Graphics.Assets;
import mazerunner.Graphics.GameCamera;
import mazerunner.Graphics.SpriteSheet;
import mazerunner.Graphics.Image;
import mazerunner.input.KeyInput;
import mazerunner.input.MouseInput;
import mazerunner.state.GameState;
import mazerunner.state.Instruction;
import mazerunner.state.Menu;
import mazerunner.state.State;
import mazerunner.state.gameOver;

public class Game implements Runnable{
    private Display display;
     private int width,height;
     public String title;
     private boolean running =false;
     Thread thread=new Thread();
     private BufferStrategy bs;
     private Graphics g;
     
     
     ///states
     public State gamestate;
     public State menu;
     public State gameOver;
    // public State ins;
     
     ///input
     private KeyInput keyManazer;
     ///camera
     private GameCamera gameCamera;
     ///handelar
     
     private Handelar handelar;
     private MouseInput mouseManazer;
    public State ins;
     
      public Game(String title,int width,int height){
    this.width=width;
    this.height=height;
    this.title=title;
    keyManazer=new KeyInput();
    mouseManazer=new MouseInput();
    
}
      public void init(){
          
          display=new Display(title, width, height);
          display.getFrame().addKeyListener(keyManazer);
          display.getFrame().addMouseListener(mouseManazer);
          display.getFrame().addMouseMotionListener(mouseManazer);
          display.getCanvas().addMouseListener(mouseManazer);
          display.getCanvas().addMouseMotionListener(mouseManazer);
          Assets.init();
           handelar=new Handelar(this);
          gameCamera=new GameCamera(handelar,0, 0);
          ins=new Instruction(handelar);
          gameOver=new gameOver(handelar);
          gamestate=new GameState(handelar);
          menu=new Menu(handelar);
          State.setState(menu);
        
      }
   
      public void tick(){
           
          
          keyManazer.tick();
          if(State.getState()!=null)
              
              State.getState().tick();
         
         
      }
    
     
      
      public void run(){
          init();
          
          int fps=60;
          double TimePerTick=1000000000/fps;
          double delta=0;
          long now;
          long LastTime=System.nanoTime();
          long timer=0;
          int ticks=0;
          while(running){
             
              now=System.nanoTime();
              delta+=(now-LastTime)/TimePerTick;
              timer=now-LastTime;
              LastTime=now;
              if(delta>=1){
              tick();
              render();
              ticks++;
              delta--;
                  
                  
              }
              
          }
          stop();
      }
       
       public void render(){
          bs=display.getCanvas().getBufferStrategy();
          if(bs==null){
              display.getCanvas().createBufferStrategy(3);
          return;
          }
          g=bs.getDrawGraphics();
          g.clearRect(0, 0, width, height);
               if(State.getState()!=null)
              State.getState().render(g);
         
          //if(handelar.getKeyManazer().quit)
                    //State.setState(handelar.getGame().gamestate);
    
       
        
          
       bs.show();
          g.dispose();
      }
      public KeyInput getKeyManazer(){
          return keyManazer;
      }   
      public MouseInput getMouseManazer(){
          return mouseManazer;
      }
      public  GameCamera getGameCamera() {
          return gameCamera;
      }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
      
      public synchronized void start() {
          
          if(running)
              return;
          running=true;
          thread=new Thread(this);
          thread.start();
          
      }
       public synchronized void stop() {
        try {
            if(!running)
                return;
            running=false;
            thread.join();               
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
      }

    
    
}
