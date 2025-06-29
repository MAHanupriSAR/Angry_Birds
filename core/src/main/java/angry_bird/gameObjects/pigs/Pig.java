package angry_bird.gameObjects.pigs;

import angry_bird.gameObjects.GameObject;
import com.badlogic.gdx.physics.box2d.World;

public class Pig extends GameObject {
    public Pig(World world, float posX, float posY, float radius) {
        super(world, posX, posY, radius);
    }
}
