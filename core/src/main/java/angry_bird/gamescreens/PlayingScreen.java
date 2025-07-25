package angry_bird.gamescreens;

import angry_bird.Levels.Level1;
import angry_bird.Levels.Level2;
import angry_bird.Levels.Level3;
import angry_bird.Levels.LevelManager;
import angry_bird.main.Main;
import angry_bird.serializable.LevelState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import java.io.File;

public class PlayingScreen implements Screen {
    private Main main;

    private PauseMenu pauseMenu;
    private WinningScreen winningScreen;
    private LoosingScreen loosingScreen;

    private boolean paused, won, loss;

    private LevelManager levelManager;

    public PlayingScreen(Main main){
        this.main = main;
        pauseMenu = new PauseMenu(main, this);
        winningScreen = new WinningScreen(main, this);
        loosingScreen = new LoosingScreen(main, this);

        paused = false; won = false; loss = false;

        levelManager = new LevelManager();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(!paused) {
            main.getSpriteBatch().begin();
            levelManager.render(main.getSpriteBatch());
            main.getSpriteBatch().end();
        }
        overlayInputs();
        displayOverlays();
    }

    private void overlayInputs(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            paused = !paused;
            if (paused) {
                Gdx.input.setInputProcessor(pauseMenu.getStage());  // Set the stage to receive input
            } else {
                levelManager.getCurrLevel().getSlingShot().setupInput();  // Set input processor back to normal
            }
        }
    }
    private void displayOverlays(){
        if(paused){
            pauseMenu.render();
        }
        else if(levelManager.getCurrLevel().isWon()){
            winningScreen.render();
            Gdx.input.setInputProcessor(winningScreen.getStage());
        }
        else if(levelManager.getCurrLevel().isLost()){
            loosingScreen.render();
            Gdx.input.setInputProcessor(loosingScreen.getStage());
        }
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        pauseMenu.dispose();
        winningScreen.dispose();
        loosingScreen.dispose();
    }

    public boolean isPaused() {
        return paused;
    }
    public void setPaused(boolean status){
        this.paused = status;
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }

    public void loadLevel(int lvlNum){
        String filename = "level_" + lvlNum + ".ser";
        File file = new File(filename);
        if(main.isLoadGame() && file.exists()){
            System.out.println("hellooooo");
            if(lvlNum==1){
                Level1 level1 = new Level1();
                LevelState.loadLevel(level1,lvlNum);
                levelManager.setCurrLevel(level1);
                levelManager.setCurrLevelNumber(lvlNum);
            } else if (lvlNum==2) {
                Level2 level2 = new Level2();
                LevelState.loadLevel(level2,lvlNum);
                levelManager.setCurrLevel(level2);
                levelManager.setCurrLevelNumber(lvlNum);
            } else if (lvlNum==3) {
                Level3 level3 = new Level3();
                LevelState.loadLevel(level3,lvlNum);
                levelManager.setCurrLevel(level3);
                levelManager.setCurrLevelNumber(lvlNum);
            }
        }
        else {
            this.levelManager.loadLevel(lvlNum);
            levelManager.setCurrLevelNumber(lvlNum);
        }
    }
}
