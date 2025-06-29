package angry_bird.gameObjects.blocks;
import com.badlogic.gdx.physics.box2d.World;

import static angry_bird.utils.Constants.UI.Healths.BlockHealth.WOOD_HEALTH;

public class Wood extends Block{
    public Wood(World world, float x, float y, float hx, float hy) {
        super(world, x, y, hx, hy);
        this.setImage("game_objects/blocks/wood.png");
        this.health = WOOD_HEALTH;
    }
}
