package angry_bird.gameObjects.birds;

import angry_bird.gameObjects.GameObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;

public class Black extends Bird{
    public Black(World world, float posX, float posY, float radius) {
        super(world, posX, posY, radius);
        this.setImage("game_objects/birds/black.png");
    }

    @Override
    public void ability() {
        float explosionRadius = 5.0f; // Radius of the explosion
        float explosionForce = 200.0f; // Force applied to nearby objects
        float explosionDamage = 50.0f; // Damage dealt to nearby objects
        Vector2 explosionCenter = body.getWorldCenter();

        this.getBody().getWorld().QueryAABB(new QueryCallback() {
            @Override
            public boolean reportFixture(Fixture fixture) {
                Body targetBody = fixture.getBody();
                if (targetBody != body) {
                    Vector2 targetPosition = targetBody.getWorldCenter();
                    float distance = explosionCenter.dst(targetPosition);
                    if (distance <= explosionRadius) {
                        Object userData = targetBody.getUserData();
                        if (userData instanceof GameObject) {
                            ((GameObject) userData).reduceHealth(explosionDamage * (1 - distance / explosionRadius));
                        }
                        Vector2 forceDirection = targetPosition.cpy().sub(explosionCenter).nor();
                        targetBody.applyLinearImpulse(
                            forceDirection.scl(explosionForce * (1 - distance / explosionRadius)),
                            targetPosition,
                            true
                        );
                    }
                }
                return true;
            }
        },
        explosionCenter.x - explosionRadius, explosionCenter.y - explosionRadius,
        explosionCenter.x + explosionRadius, explosionCenter.y + explosionRadius);
        dispose();
    }
}
