package angry_bird.gameObjects.birds;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Yellow extends Bird{
    public Yellow(World world, float posX, float posY, float radius) {
        super(world, posX, posY, radius);
        this.setImage("game_objects/birds/yellow.png");
    }

    @Override
    public void ability() {
        Vector2 currentVelocity = this.getBody().getLinearVelocity();
        if (currentVelocity.len() > 0) {
            Vector2 boost = currentVelocity.nor().scl(100f);
            this.getBody().applyLinearImpulse(boost, this.getBody().getWorldCenter(), true);
            System.out.println("Yellow bird ability activated! Speed boosted.");
        }
    }
}
