package angry_bird.gameObjects;

import angry_bird.gameObjects.birds.Bird;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;
import java.util.Queue;

public class  SlingShot {
    private Texture texture;
    private float centerX, centerY;
    private float pouchCenterX, pouchCenterY;
    private final float hx = 0.6f;
    private final float hy = 1.5f;

    private ArrayList<Bird> birds;
    private int i;

    private Bird bird;
    private Bird launchedBird;
    private OrthographicCamera camera;
    private World world;

    private ShapeRenderer shapeRenderer;
    private boolean isDragging = false;
    private Vector2 dragStart = new Vector2();
    private Vector2 dragEnd = new Vector2();

    private final float STRENGTH = 10f;

    public SlingShot(World world, OrthographicCamera camera, float x, float y, ArrayList<Bird> birds){
        this.camera = camera;
        this.world = world;
        this.birds = birds; i=0;

        this.centerX = x; this.pouchCenterX = centerX;
        this.centerY = y; this.pouchCenterY = centerY+1f;

        shapeRenderer = new ShapeRenderer();

        this.texture = new Texture("game_objects/slingshot.png");

        setupInput();
        loadNextBird();
    }

    public void setupBird(Bird bird){
        this.bird = bird;
        bird.changePosition(pouchCenterX, pouchCenterY);
        bird.getBody().setGravityScale(0);
    }

    private void loadNextBird() {
        if (i<birds.size()) {
            setupBird(birds.get(i));
            i++;
        } else {
            bird = null; // No more birds left
        }
    }

    public void setupInput() {
        Gdx.input.setInputProcessor(new SlingShotInputProcessor());
    }

    private void drawTrajectory(Vector2 startPoint, Vector2 launchVector) {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0, 1, 0, 1);

        float timeStep = 0.1f; // Time step for simulation
        int steps = 100; // Number of steps to simulate

        Vector2 currentPosition = new Vector2(startPoint);
        Vector2 previousPosition = new Vector2(startPoint);
        Vector2 velocity = new Vector2(launchVector);

        for (int i = 0; i < steps; i++) {
            float t = i * timeStep;
            currentPosition.x = startPoint.x + velocity.x * t;
            currentPosition.y = startPoint.y + velocity.y * t + 0.5f * world.getGravity().y * t * t;
            shapeRenderer.line(previousPosition, currentPosition);
            previousPosition.set(currentPosition);
            if (currentPosition.y <= 0) break;
        }

        shapeRenderer.end();
    }

    private void drawDottedTrajectory(Vector2 startPoint, Vector2 launchVector) {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        float timeStep = 0.025f; // Time step for simulation
        int steps = 10; // Number of steps to simulate
        float dotRadius = 0.05f; // Radius of each dot
        int sides = 30;
        Vector2 currentPosition = new Vector2(startPoint);
        Vector2 velocity = new Vector2(launchVector);
        for (int i = 0; i < steps; i++) {
            float t = i * timeStep;
            currentPosition.x = startPoint.x + velocity.x * t;
            currentPosition.y = startPoint.y + velocity.y * t + 0.5f * this.world.getGravity().y * t * t;
            Gdx.gl.glDisable(GL20.GL_BLEND);
            shapeRenderer.setColor(0, 0, 0, 1);
            shapeRenderer.circle(currentPosition.x, currentPosition.y, dotRadius+0.05f,sides);
            shapeRenderer.setColor(1, 1, 1, 1);
            shapeRenderer.circle(currentPosition.x, currentPosition.y, dotRadius,sides);
            if (currentPosition.y <= 0) break;
        }
        shapeRenderer.end();
    }

//    public void render(SpriteBatch spriteBatch){
//        spriteBatch.draw(this.texture, x-hx, centerY-hy, 2*hx, 2*hy);
//        if(isDragging){
//            // Calculate the launch vector
//            Vector2 launchVector = new Vector2(dragStart).sub(dragEnd).scl(5f); // Adjust the scaling factor
////            drawTrajectory(circleBody.getWorldCenter(), launchVector);
//            drawDottedTrajectory(bird.getCentreOfMass(), launchVector);
//        }
//    }

    public void render(SpriteBatch spriteBatch){
        spriteBatch.draw(this.texture, centerX -hx, centerY -hy, 2*hx, 2*hy);
        if(isDragging){
            Vector2 launchVector = new Vector2(dragStart).sub(dragEnd).scl(STRENGTH);
            spriteBatch.end();
            Gdx.gl.glEnable(GL20.GL_BLEND);
            shapeRenderer.setProjectionMatrix(camera.combined);
            drawDottedTrajectory(new Vector2(centerX, centerY +1f), launchVector);
            spriteBatch.begin();
        }

        if(launchedBird!=null  && Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            launchedBird.ability();
            launchedBird = null;
        }
    }

    public float getHx() {
        return hx;
    }

    public float getHy() {
        return hy;
    }

    public void dispose(){
        shapeRenderer.dispose();
    }

    public class SlingShotInputProcessor extends InputAdapter{
        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            Vector3 touchPos3D = camera.unproject(new Vector3(screenX, screenY, 0));
            Vector2 touchPos = new Vector2(touchPos3D.x, touchPos3D.y);
            float activationRadius = 1.5f;
            if (touchPos.dst(pouchCenterX, pouchCenterY) <= activationRadius && bird!=null) {
                dragStart.set(pouchCenterX, pouchCenterY);
                dragEnd.set(dragStart);
                isDragging = true;
                return true;
            }
            return false;
        }
        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            if (isDragging) {
                Vector3 touchPos3D = camera.unproject(new Vector3(screenX, screenY, 0));
                Vector2 touchPos = new Vector2(touchPos3D.x, touchPos3D.y);
                float maxRadius = 2.0f;
                Vector2 pouchCenter = new Vector2(pouchCenterX, pouchCenterY);
                if (touchPos.dst(pouchCenter) > maxRadius) {
                    dragEnd.set(touchPos.sub(pouchCenter).nor().scl(maxRadius).add(pouchCenter));
                } else {
                    dragEnd.set(touchPos);
                }
            }
            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            if (isDragging) {
                isDragging = false;
                bird.getBody().setGravityScale(1);
                Vector2 launchVector = dragStart.sub(dragEnd);
                launchVector.scl(STRENGTH);
                bird.applyInitialForce(launchVector);
                launchedBird = bird;
                loadNextBird();
            }
            return true;
        }
    }
}
