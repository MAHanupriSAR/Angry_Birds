package angry_bird.serializable;

import angry_bird.Levels.Level;
import angry_bird.gameObjects.birds.Bird;
import angry_bird.gameObjects.blocks.Block;
import angry_bird.gameObjects.pigs.Pig;
import com.badlogic.gdx.math.Vector2;

import java.io.*;
import java.util.ArrayList;

public class LevelState implements Serializable{
    private int birdIndex;
    private int birdsLaunched;
    private ArrayList<GameObjectState> pigs;
    private ArrayList<GameObjectState> birds;
    private ArrayList<GameObjectState> blocks;

    public LevelState(){
        this.birds = new ArrayList<>();
        this.pigs = new ArrayList<>();
        this.blocks = new ArrayList<>();
    }

    public static void saveLevel(Level level, int lvlNumber){
        String fileName = "level_" + lvlNumber + ".ser";
        LevelState levelState = new LevelState();
        ArrayList<Bird> birds = level.getBirds();
        ArrayList<Pig> pigs = level.getPigs();
        ArrayList<Block> blocks = level.getBlocks();

        for(int i=0; i<birds.size(); i++){
            Bird bird = birds.get(i);
            float centreX = bird.getBody().getPosition().x;
            float centreY = bird.getBody().getPosition().y;
            float angle = bird.getBody().getAngle();
            float health = bird.getHealth();
            Vector2 linearVelocity = bird.getBody().getLinearVelocity();
            float angularVelocity  = bird.getBody().getAngularVelocity();
            GameObjectState birdState = new GameObjectState(centreX,centreY,angle,health,linearVelocity,angularVelocity);
            levelState.getBirds().add(birdState);
        }
        for(int i=0; i<pigs.size(); i++){
            Pig pig = pigs.get(i);
            float centreX = pig.getBody().getPosition().x;
            float centreY = pig.getBody().getPosition().y;
            float angle = pig.getBody().getAngle();
            float health = pig.getHealth();
            Vector2 linearVelocity = pig.getBody().getLinearVelocity();
            float angularVelocity = pig.getBody().getAngularVelocity();
            GameObjectState pigState = new GameObjectState(centreX,centreY,angle,health,linearVelocity,angularVelocity);
            levelState.getPigs().add(pigState);
        }
        for(int i=0; i<pigs.size(); i++){
            Block block = blocks.get(i);
            float centreX = block.getBody().getPosition().x;
            float centreY = block.getBody().getPosition().y;
            float angle = block.getBody().getAngle();
            float health = block.getHealth();
            Vector2 linearVelocity = block.getBody().getLinearVelocity();
            float angularVelocity = block.getBody().getAngularVelocity();
            GameObjectState blockState = new GameObjectState(centreX,centreY,angle,health,linearVelocity,angularVelocity);
            levelState.getBlocks().add(blockState);
        }
        levelState.setBirdIndex(level.getSlingShot().getI());
        levelState.setBirdsLaunched(level.getBirdsLaunched());
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(levelState);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int loadLevelStateForBirdIndex(Level level, int lvlNumber){
        String fileName = "level_" + lvlNumber + ".ser";
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            LevelState levelState = (LevelState) ois.readObject();
            for(int i=0; i<levelState.birdIndex; i++){
                level.getBirds().get(i).getBody().setGravityScale(1);
            }
            return levelState.getBirdIndex();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return lvlNumber;
    }

    public static void loadLevel(Level level, int lvlNumber){
        String fileName = "level_" + lvlNumber + ".ser";
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            LevelState levelState = (LevelState) ois.readObject();
            ArrayList<Bird> birds = level.getBirds();
            ArrayList<Pig> pigs = level.getPigs();
            ArrayList<Block> blocks = level.getBlocks();

            for(int i=0; i<levelState.getBirds().size(); i++){
                Bird bird = birds.get(i);
                GameObjectState birdState = levelState.getBirds().get(i);
                float centreX = birdState.getX();
                float centreY = birdState.getY();
                float angle = birdState.getAngle();
                float health = birdState.getHealth();
                Vector2 linearVelocity = birdState.getLinearVelocity();
                float angularVelocity = birdState.getAngularVelocity();
                bird.getBody().setTransform(centreX,centreY,angle);
                bird.getBody().setLinearVelocity(linearVelocity);
                bird.getBody().setAngularVelocity(angularVelocity);
                bird.setHealth(health);
            }
            for(int i=0; i<levelState.getPigs().size(); i++){
                Pig pig = pigs.get(i);
                GameObjectState pigState = levelState.getPigs().get(i);
                float centreX = pigState.getX();
                float centreY = pigState.getY();
                float angle = pigState.getAngle();
                float health = pigState.getHealth();
                Vector2 linearVelocity = pigState.getLinearVelocity();
                float angularVelocity = pigState.getAngularVelocity();
                pig.getBody().setTransform(centreX,centreY,angle);
                pig.getBody().setLinearVelocity(linearVelocity);
                pig.getBody().setAngularVelocity(angularVelocity);
                pig.setHealth(health);
            }
            for(int i=0; i<levelState.getBlocks().size(); i++){
                Block block = blocks.get(i);
                GameObjectState blockState = levelState.getBlocks().get(i);
                float centreX = blockState.getX();
                float centreY = blockState.getY();
                float angle = blockState.getAngle();
                float health = blockState.getHealth();
                Vector2 linearVelocity = blockState.getLinearVelocity();
                float angularVelocity = blockState.getAngularVelocity();
                block.getBody().setTransform(centreX,centreY,angle);
                block.getBody().setLinearVelocity(linearVelocity);
                block.getBody().setAngularVelocity(angularVelocity);
                block.setHealth(health);
            }
            level.setBirdsLaunched(levelState.getBirdsLaunched());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<GameObjectState> getBirds() {
        return birds;
    }

    public ArrayList<GameObjectState> getPigs() {
        return pigs;
    }

    public ArrayList<GameObjectState> getBlocks() {
        return blocks;
    }

    public int getBirdIndex() {
        return birdIndex;
    }

    public void setBirdIndex(int birdIndex) {
        this.birdIndex = birdIndex;
    }

    public int getBirdsLaunched() {
        return birdsLaunched;
    }

    public void setBirdsLaunched(int birdsLaunched) {
        this.birdsLaunched = birdsLaunched;
    }
}
