package angry_bird.gameObjects.pigs;

import com.badlogic.gdx.physics.box2d.World;

import static angry_bird.utils.Constants.UI.Healths.PigHealth.MINION_PIG_HEALTH;

public class MinionPig extends Pig{
    public MinionPig(World world, float posX, float posY, float radius) {
        super(world, posX, posY, radius);
        this.setImage("game_objects/pigs/minion_pig.png");
        this.health = MINION_PIG_HEALTH;
        this.mass = 1.0f;
    }
}
