
package mazerunner;

import mazerunner.audios.SimpleAudioPlayer;
import mazerunner.display.Display;



public class Launcher {
    public static void main(String[] args) {
      Game game= new Game("maze Runner",800,580);
        SimpleAudioPlayer.LoadSound();
        
        game.start();
    }
}
