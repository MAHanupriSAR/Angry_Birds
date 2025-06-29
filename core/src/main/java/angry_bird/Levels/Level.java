package angry_bird.Levels;

import angry_bird.gameObjects.Background;
import angry_bird.gameObjects.SlingShot;
import angry_bird.gameObjects.birds.*;
import angry_bird.gameObjects.blocks.Block;
import angry_bird.gameObjects.blocks.Glass;
import angry_bird.gameObjects.blocks.Stone;
import angry_bird.gameObjects.blocks.Wood;
import angry_bird.gameObjects.pigs.ForemanPig;
import angry_bird.gameObjects.pigs.KingPig;
import angry_bird.gameObjects.pigs.MinionPig;
import angry_bird.gameObjects.pigs.Pig;
import angry_bird.utils.GameContactListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Level {
    protected World world;
    protected OrthographicCamera camera;
    protected Box2DDebugRenderer debugRenderer;
    protected SpriteBatch spriteBatch;

    protected GameContactListener gameContactListener;

    protected Background background;
    protected SlingShot slingShot;

    protected ArrayList<Bird> birds;
    protected ArrayList<Pig> pigs;
    protected ArrayList<Block> blocks;

    protected TiledMap tiledMap;

    public Level(){
        world = new World(new Vector2(0, -9.8f), true);
        camera = new OrthographicCamera();
        camera.setToOrtho(false,32,18);
        camera.update();
        debugRenderer = new Box2DDebugRenderer();
        spriteBatch = new SpriteBatch();
        gameContactListener = new GameContactListener(this,5f);
        world.setContactListener(gameContactListener);
        this.birds = new ArrayList<>();
        this.pigs = new ArrayList<>();
        this.blocks = new ArrayList<>();
    }

    protected void setTiledMap(String TMX_FILE){
        tiledMap = new TmxMapLoader().load(TMX_FILE);
        setupGround();
        setupBirds();
        setupSlingshot();
        setupPigs();
        setupBlocks();
    }

    private void setupGround(){
        MapLayer objectLayer = tiledMap.getLayers().get("ground");
        MapObjects objects = objectLayer.getObjects();
        for (MapObject object : objects) {
            float w = (float) object.getProperties().get("width");
            float h = (float) object.getProperties().get("height");
            float topX = (float) object.getProperties().get("x");
            float topY = 18 - (float) object.getProperties().get("y") - h;
            float bottomY = 18 - topY; //instead of calculating the y coordinate from above, calculate it from bottom
            //topX and bottomY are the coordinates of topRight corner of block

            float centreX = topX + w/2;
            float centreY = bottomY - h/2;

            String type = (String) object.getProperties().get("type");

            if("ground".equals(type)){
                background = new Background(world,centreX,centreY,w/2,h/2);
            }
        }
    }

    private void setupSlingshot(){
        slingShot = new SlingShot(world, camera, 5, background.getCentreY()+ background.getHy()+1.5f, birds);
    }

    private void setupBirds(){
        MapLayer objectLayer = tiledMap.getLayers().get("birdsLayer");
        MapObjects objects = objectLayer.getObjects();
        for (MapObject object : objects) {
            float w = (float) object.getProperties().get("width");
            float h = (float) object.getProperties().get("height");
            float topX = (float) object.getProperties().get("x");
            float topY = 18 - (float) object.getProperties().get("y") - h;
            float bottomY = 18 - topY; //instead of calculating the y coordinate from above, calculate it from bottom
            //topX and bottomY are the coordinates of topRight corner of block

            float centreX = topX + w/2;
            float centreY = bottomY - h/2;

            String type = (String) object.getProperties().get("type");

            if ("red".equals(type)) {
                birds.add(new Red(world,centreX,centreY,w/2));
            }
            else if ("yellow".equals(type)) {
                birds.add(new Yellow(world,centreX,centreY,w/2));
            }
            else if ("blue".equals(type)) {
                birds.add(new Blue(world,centreX,centreY,w/2));
            }
            else if ("black".equals(type)) {
                birds.add(new Black(world,centreX,centreY,w/2));
            }
            else if ("bigred".equals(type)) {
                birds.add(new BigRed(world,centreX,centreY,w/2));
            }
        }
    }

    private void setupPigs(){
        MapLayer objectLayer = tiledMap.getLayers().get("pigsLayer");
        MapObjects objects = objectLayer.getObjects();
        for (MapObject object : objects) {
            float w = (float) object.getProperties().get("width");
            float h = (float) object.getProperties().get("height");
            float topX = (float) object.getProperties().get("x");
            float topY = 18 - (float) object.getProperties().get("y") - h;
            float bottomY = 18 - topY; //instead of calculating the y coordinate from above, calculate it from bottom
            //topX and bottomY are the coordinates of topRight corner of block

            float centreX = topX + w/2;
            float centreY = bottomY - h/2;

            String type = (String) object.getProperties().get("type");

            if ("minion".equals(type)) {
                pigs.add(new MinionPig(world,centreX,centreY,w/2));
            }
            else if ("foreman".equals(type)) {
                pigs.add(new ForemanPig(world,centreX,centreY,w/2));
            }
            else if ("king".equals(type)) {
                pigs.add(new KingPig(world,centreX,centreY,w/2));
            }
        }
    }

    private void setupBlocks() {
        MapLayer objectLayer = tiledMap.getLayers().get("blocksLayer");
        MapObjects objects = objectLayer.getObjects();
        /*
         * in tiled, the (x,y) coordinate correspond to the top left corner
         * y is measured from top and x is measured from left
         * */
        for (MapObject object : objects) {
            float w = (float) object.getProperties().get("width");
            float h = (float) object.getProperties().get("height");
            float topX = (float) object.getProperties().get("x");
            float topY = 18 - (float) object.getProperties().get("y") - h;
            float bottomY = 18 - topY; //instead of calculating the y coordinate from above, calculate it from bottom
            //topX and bottomY are the coordinates of topRight corner of block

            float centreX = topX + w/2;
            float centreY = bottomY - h/2;

            String type = (String) object.getProperties().get("type");

            if ("stone".equals(type)) {
                Stone stone;
                if(w>h){
                    stone = new Stone(world, centreX, centreY, h / 2, w / 2);
                    stone.rotateByDegrees(90);
                }
                else{
                    stone = new Stone(world, centreX, centreY, w / 2, h / 2);
                }
                blocks.add(stone);
            }
            else if ("wood".equals(type)) {
                Wood wood;
                if(w>h){
                    wood = new Wood(world, centreX, centreY, h / 2, w / 2);
                    wood.rotateByDegrees(90);
                }
                else{
                    wood = new Wood(world, centreX, centreY, w / 2, h / 2);
                }
                blocks.add(wood);
            }
            else if ("glass".equals(type)) {
                Glass glass;
                if(w>h){
                    glass = new Glass(world, centreX, centreY, h / 2, w / 2);
                    glass.rotateByDegrees(90);
                }
                else{
                    glass = new Glass(world, centreX, centreY, w / 2, h / 2);
                }
                blocks.add(glass);
            }
        }
    }

    void render(SpriteBatch spriteBatch1){
        world.step(1/60f, 6,2);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        debugRenderer.render(world,camera.combined);

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        background.render(spriteBatch1);
        for(Bird bird : birds){
            bird.render(spriteBatch1);
        }
        for(Pig pig : pigs){
            pig.render(spriteBatch1);
        }
        for(Block block : blocks){
            block.render(spriteBatch1);
        }
        gameContactListener.update();
        slingShot.render(spriteBatch1);
        spriteBatch.end();


//        in this if i write slingshot.render() before wood.render()
//        then during rendering, when trajectory is drawn , wood is not drawn
//        similarly if i write slingshot.render() before wood.render() and stone.render()
//        then during rendering, when trajectory is drawn , wood and stone is not drawn
//        means anything written below sling shot.render() will not be rendered during drawing trajectory
    }

    public SlingShot getSlingShot() {
        return slingShot;
    }

    public boolean isWon(){
        return pigs.isEmpty();
    }

    public boolean isLost(){
        return birds.isEmpty() && !pigs.isEmpty();
    }

    public World getWorld() {
        return world;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public ArrayList<Bird> getBirds() {
        return birds;
    }

    public ArrayList<Pig> getPigs() {
        return pigs;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }


    public void dispose() {
        world.dispose();
        debugRenderer.dispose();
        spriteBatch.dispose();
        background.dispose();
    }
}
