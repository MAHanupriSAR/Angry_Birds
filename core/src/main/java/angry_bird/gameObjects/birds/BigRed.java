package angry_bird.gameObjects.birds;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class BigRed extends Bird{
    public BigRed(World world, float posX, float posY, float radius) {
        super(world, posX, posY, radius);
        this.setImage("game_objects/birds/big_red.png");
        updateDensity(2);
    }

    public void applyInitialForce(Vector2 launchVector){
        body.applyLinearImpulse(launchVector.scl(5),body.getWorldCenter(),true);
    }
}
