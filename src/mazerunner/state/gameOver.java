
package mazerunner.state;

import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import mazerunner.Handelar;
import mazerunner.audios.SimpleAudioPlayer;
import mazerunner.Graphics.Assets;


public class gameOver extends State{

    public gameOver(Handelar hendelar) {
        super(hendelar);
        
        
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
       
       g.drawImage(Assets.gameover, 0, 0, null);
      SimpleAudioPlayer.stop();
        
       
    }
    
    
}
