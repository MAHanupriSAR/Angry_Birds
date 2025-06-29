package angry_bird.serializable;

import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public class GameObjectState implements Serializable {
    private float health,x,y,angle;
    private Vector2 linearVelocity;
    private float angularVelocity;

    public GameObjectState(float x, float y, float angle,float health, Vector2 linearVelocity, float angularVelocity){
        this.health = health;
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.angularVelocity = angularVelocity;
        this.linearVelocity = linearVelocity;
    }

    public float getHealth() {
        return health;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getAngle() {
        return angle;
    }

    public float getAngularVelocity() {
        return angularVelocity;
    }

    public Vector2 getLinearVelocity() {
        return linearVelocity;
    }
}
