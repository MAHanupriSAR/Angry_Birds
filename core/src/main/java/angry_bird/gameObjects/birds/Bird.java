package angry_bird.gameObjects.birds;

import angry_bird.gameObjects.GameObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Bird extends GameObject {
    public Bird(World world, float posX, float posY, float radius) {
        super(world,posX, posY, radius);
    }

    public void changePosition(float x, float y){
        body.setTransform(x, y, body.getAngle());
    }

    public void applyInitialForce(float x, float y) {
        body.applyLinearImpulse(new Vector2(x, y), body.getWorldCenter(), true);
    }

    public void applyInitialForce(Vector2 launchVector){
        body.applyLinearImpulse(launchVector,body.getWorldCenter(),true);
    }

    public void ability(){

    }
}
