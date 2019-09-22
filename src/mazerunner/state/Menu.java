
package mazerunner.state;


//import mazerunner.ui.Instruction;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import mazerunner.Handelar;
import mazerunner.display.Display;
import mazerunner.Graphics.Assets;
import mazerunner.ui.ClickListener;

import mazerunner.ui.UIImageButton;
import mazerunner.ui.UIManager;
import mazerunner.ui.UIObject;


public class Menu extends State{
    private UIManager uiManager;
   
   
   private GameState gameState;
 

   
    public Menu(Handelar handelar){
        super(handelar);
        uiManager = new UIManager(handelar);
       
        
		handelar.getMouseManazer().setUIManager(uiManager);
               

		uiManager.addObject(new UIImageButton(50, 200, 128, 64, Assets.btn_start, new ClickListener() {
                    
			@Override
			public void onClick() {
                            
				handelar.getMouseManazer().setUIManager(null);
				State.setState(handelar.getGame().gamestate);
			}
                        
		}));
                 
                uiManager.addObject(new UIImageButton(50, 300, 128, 64, Assets.instruction, new ClickListener() {
			@Override
			public void onClick() {
				handelar.getMouseManazer().setUIManager(null);
                               
                              State.setState(handelar.getGame().ins);
                                
                              
                              
			}
		}));
                  uiManager.addObject(new UIImageButton(50, 400, 128, 64, Assets.exit, new ClickListener() {
			@Override
			public void onClick() {
				handelar.getMouseManazer().setUIManager(null);
                               
                              System.exit(0);
                                
                              
                              
			}
		}));
    }
   
        

    @Override
    public void tick() {
        
        uiManager.tick();
       
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.mazerunner,0,0,null);
        uiManager.render(g);
        
        
    }
    
}
