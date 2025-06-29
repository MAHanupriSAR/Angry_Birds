package angry_bird.gameObjects.blocks;
import com.badlogic.gdx.physics.box2d.World;

import static angry_bird.utils.Constants.UI.Healths.BlockHealth.GLASS_HEALTH;

public class Glass extends Block{
    public Glass(World world, float x, float y, float hx, float hy) {
        super(world, x, y, hx, hy);
        this.setImage("game_objects/blocks/glass.png");
        this.health = GLASS_HEALTH;
    }
}
