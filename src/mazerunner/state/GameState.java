
package mazerunner.state;

import java.awt.Graphics;
import mazerunner.Handelar;
import mazerunner.entities.creatures.Player;
import mazerunner.Graphics.Assets;
import mazerunner.tiles.Tile;
import mazerunner.worlds.World;

public class GameState extends State{

    private World world;
    public static int  level=3;
    public Player player;
   
   
    
    public GameState(Handelar handelar){
        super(handelar);
        
        
        if(level==3)
            world =new World(handelar,"src/res/world/world3.txt");
         handelar.setWorld(world);
       
      
       handelar.getGameCamera().move(100,200);
    }
    

    @Override
    public void tick() {
        world.tick();
      
    }
    

    @Override
    public void render(Graphics g) {
        
       world.render(g);
       
    
       
     
    }
    
    
}
