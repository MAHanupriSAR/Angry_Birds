package angry_bird.gameObjects.blocks;

import com.badlogic.gdx.physics.box2d.World;

import static angry_bird.utils.Constants.UI.Healths.BlockHealth.STONE_HEALTH;

public class Stone extends Block{

    public Stone(World world, float x, float y, float hx, float hy) {
        super(world, x, y, hx, hy);
        this.setImage("game_objects/blocks/stone.png");
        this.health = STONE_HEALTH;
    }
}
