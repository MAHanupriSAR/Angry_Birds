package angry_bird.gameObjects;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

public class Background {
    private Body body;
    private Texture image;
    private float hx;
    private float hy;
    private float centreX;
    private float centreY;

    public Background(World world, float x, float y, float groundHx, float groundHy) {
        this.hx = groundHx;
        this.hy = groundHy;
        this.centreX = x;
        this.centreY = y;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(groundHx, groundHy);

        FixtureDef groundFixtureDef = new FixtureDef();
        groundFixtureDef.shape = shape;
        groundFixtureDef.friction = 1f; // Add friction here
        body.createFixture(groundFixtureDef);
        shape.dispose();

        setupBoundaries(world);
    }

    public void setImage(String pathToImage){
        image = new Texture(pathToImage);
    }

    private void setupBoundaries(World world){
        //Left
        drawBoundary(world,-0.5f,9,0.5f,9);
        //right
        drawBoundary(world,32.5f, 9,0.5f,9);
        //top
        drawBoundary(world,16,18.5f,16,0.5f);
    }

    private void drawBoundary(World world, float x, float y, float hx, float hy){
        BodyDef boundBodyDef  = new BodyDef();
        boundBodyDef.type = BodyDef.BodyType.StaticBody;
        boundBodyDef.position.set(x,y);
        Body boundBody = world.createBody(boundBodyDef);
        PolygonShape boundShape = new PolygonShape();
        boundShape.setAsBox(hx,hy);
        FixtureDef boundFixtureDef = new FixtureDef();
        boundFixtureDef.shape = boundShape;
        boundFixtureDef.friction = 1f;
        boundBody.createFixture(boundFixtureDef);
        boundShape.dispose();
    }

    public float getHy() {
        return hy;
    }

    public float getHx() {
        return hx;
    }

    public float getCentreX() {
        return centreX;
    }

    public float getCentreY() {
        return centreY;
    }

    public void render(SpriteBatch spriteBatch){
        if(this.image!=null) {
            spriteBatch.draw(this.image, 0, 0, 32, 18);
        }
    }

    public void dispose(){
        image.dispose();
    }
}
