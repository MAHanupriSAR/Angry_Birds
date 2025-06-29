package angry_bird.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class GameObject {
    protected Body body;
    protected Texture texture;
    protected TextureRegion textureRegion;
    protected float radius;
    protected float hx, hy;
    protected float mass;
    protected float health;

    public GameObject(World world, float x, float y, float rad){
        this.hx = rad;
        this.hy = rad;
        this.radius = rad;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x,y);
        bodyDef.angularDamping = 2.0f;
        body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(rad);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f; // Adjust as needed
        fixtureDef.friction = 1f; // Add friction here
        fixtureDef.restitution = 0f; //bounciness
        body.createFixture(fixtureDef);
        shape.dispose();

        this.mass = body.getMass();

        body.setUserData(this);
    }

    public GameObject(World world, float x, float y, float hx, float hy){
        this.hx = hx;
        this.hy = hy;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(hx, hy);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;      // Adjust for lighter weight
        fixtureDef.friction = 0.3f;     // Moderate friction
        fixtureDef.restitution = 0.0f;  // Small bounce effect
        body.createFixture(fixtureDef);
        shape.dispose();

        this.mass = body.getMass();

        body.setUserData(this);
    }

    public void render(SpriteBatch spriteBatch) {
        if(body != null && body.isActive()) {
            Vector2 position = body.getPosition();
            float angle = (float) Math.toDegrees(body.getAngle());
            spriteBatch.draw(
                textureRegion,
                position.x - hx, position.y - hy,
                hx, hy,
                2 * hx, 2 * hy,
                1, 1,
                angle
            );
        }
    }

    public float getHealth() {
        return health;
    }

    public void reduceHealth(float damage){
        this.health -= damage;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public void updateDensity(float newDensity) {
        for (Fixture fixture : body.getFixtureList()) {
            fixture.setDensity(newDensity);
        }
        body.resetMassData();
    }


    protected void updateFriction(float newFriction) {
        for (Fixture fixture : body.getFixtureList()) {
            fixture.setFriction(newFriction);
        }
    }

    protected void updateRestitution(float newRestitution) {
        for (Fixture fixture : body.getFixtureList()) {
            fixture.setRestitution(newRestitution);
        }
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public float getMass() {
        return mass;
    }

    public Vector2 getCentreOfMass(){
        return body.getWorldCenter();
    }

    public void setImage(String pathToTexture){
        this.texture = new Texture(pathToTexture);
        this.textureRegion = new TextureRegion(texture);
    }

    public Body getBody() {
        return body;
    }

    public void dispose() {
        if (body != null) {
            body.getWorld().destroyBody(body);
            body = null;
        }
        if (texture != null) {
            texture.dispose();
            texture = null;
        }
    }
}
