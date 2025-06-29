package angry_bird.gameObjects.blocks;

import angry_bird.gameObjects.GameObject;
import com.badlogic.gdx.physics.box2d.World;

public class Block extends GameObject {

    public Block(World world, float x, float y, float hx, float hy){
        super(world,x,y,hx,hy);
    }

    public void rotateByDegrees(float degrees) {
        float radians = (float) Math.toRadians(degrees);
        body.setTransform(body.getPosition(), body.getAngle() + radians);
    }
}
