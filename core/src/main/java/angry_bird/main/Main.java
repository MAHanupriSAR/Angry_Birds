package angry_bird.main;

import angry_bird.gamescreens.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private SpriteBatch spriteBatch;
    private static boolean loadGame;

    private MainMenuScreen mainMenuScreen;
    private GamesaveScreen gamesaveScreen;
    private LevelSelectionScreen levelSelectionScreen;
    private PlayingScreen playingScreen;



    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        initialiseScreens();
        this.setScreen(new MainMenuScreen(this));
    }

    private void initialiseScreens(){
        mainMenuScreen = new MainMenuScreen(this);
        gamesaveScreen = new GamesaveScreen(this);
        levelSelectionScreen = new LevelSelectionScreen(this);
        playingScreen = new PlayingScreen(this);
    }

    @Override
    public void render() {
        super.render();
    }

    public static boolean isLoadGame() {
        return loadGame;
    }

    public void setLoadGame(boolean loadGame) {
        Main.loadGame = loadGame;
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        getScreen().dispose();
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public PlayingScreen getPlayingScreen() {
        return playingScreen;
    }
}
