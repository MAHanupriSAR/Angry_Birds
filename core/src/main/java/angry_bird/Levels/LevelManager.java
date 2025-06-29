package angry_bird.Levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class LevelManager {
    private int currLevelNumber;
    private Level currLevel;
    private final Map<Integer, Supplier<Level>> levelSupplierMap;
    private final Map<Integer, Level> levelMap;

    public LevelManager() {
        levelMap = new HashMap<>();
        currLevelNumber = 0;
        levelSupplierMap = new HashMap<>();
        levelSupplierMap.put(1, Level1::new);
        levelSupplierMap.put(2, Level2::new);
        levelSupplierMap.put(3, Level3::new);
    }

    public void loadLevel(int levelNum) {
        if (currLevel != null) {
            currLevel.dispose();
        }
        currLevel = levelSupplierMap.get(levelNum).get();
    }

    public Map<Integer, Supplier<Level>> getLevelSupplierMap() {
        return levelSupplierMap;
    }

    public Level getCurrLevel() {
        return currLevel;
    }
    public void setCurrLevel(Level currLevel) {
        this.currLevel = currLevel;
    }
    public Level createNewLevel(int lvlNum){
        return levelSupplierMap.get(lvlNum).get();
    }

    public int getCurrLevelNumber() {
        return currLevelNumber;
    }
    public void setCurrLevelNumber(int currLevelNumber) {
        this.currLevelNumber = currLevelNumber;
    }

    public void render(SpriteBatch spriteBatch) {
        if (currLevel != null) {
            currLevel.render(spriteBatch);
        }
    }
}
