package angry_bird.gamescreens;

import angry_bird.Levels.Level;
import angry_bird.main.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import static angry_bird.utils.Constants.UI.skin.BUTTON_SKIN;

public class PauseMenu {
    private Main main;
    private PlayingScreen playingScreen;
    private Stage pauseMenuStage;
    private Table table;
    private TextButton menuButton;
    private TextButton lvlSelectButton;
    private TextButton resumeButton;
    private TextButton restartButton;
    private TextButton quitButton;
    private Skin skin;

    public PauseMenu(Main main, PlayingScreen playingScreen){
        this.main = main;
        this.playingScreen = playingScreen;
        setupStageForPauseMenu();
        setupTableForPauseMenu();
        AddButtonsToPauseMenuStage();
    }

    private void setupStageForPauseMenu() {
        pauseMenuStage = new Stage(new ScreenViewport());
    }

    private void setupTableForPauseMenu(){
        table = new Table();
        table.setFillParent(true);
        pauseMenuStage.addActor(table);
        table.left().padLeft(150);
        table.defaults().width(200).height(70).pad(18);
    }

    private void AddButtonsToPauseMenuStage() {
        skin = new Skin(Gdx.files.internal(BUTTON_SKIN));

        createMainMEnuButton();
        createLvlSelectButton();
        createResumeButton();
        createRestartButton();
        createQuitButton();
    }

    private void createMainMEnuButton(){
        menuButton = new TextButton("MAIN MENU", skin);
        menuButton.addListener(event -> {
            if(menuButton.isPressed()){
                main.setScreen(new MainMenuScreen(main));
            }
            return true;
        });
        table.add(menuButton);
        table.row();
    }

    private void createLvlSelectButton(){
        lvlSelectButton = new TextButton("Select Level", skin);
        lvlSelectButton.addListener(event -> {
            if(lvlSelectButton.isPressed()){
                main.setScreen(new LevelSelectionScreen(main));
            }
            return true;
        });
        table.add(lvlSelectButton);
        table.row();
    }

    private void createResumeButton(){
        resumeButton = new TextButton("RESUME", skin);
        resumeButton.addListener(event -> {
            if(resumeButton.isPressed()){
                playingScreen.setPaused(false);
            }
            return true;
        });
        table.add(resumeButton);
        table.row();
    }

    private void createRestartButton(){
        restartButton = new TextButton("RESTART", skin);
        restartButton.addListener(event -> {
            if(restartButton.isPressed()){
                int currLvlNumber = playingScreen.getLevelManager().getCurrLevelNumber();
                Level level = playingScreen.getLevelManager().createNewLevel(currLvlNumber);
                playingScreen.getLevelManager().setCurrLevel(level);
                playingScreen.setPaused(false);
            }
            return true;
        });
        table.add(restartButton);
        table.row();
    }

    private void createQuitButton(){
        quitButton = new TextButton("QUIT", skin);
        quitButton.addListener(event -> {
            if(quitButton.isPressed()){
                Gdx.app.exit();
            }
            return true;
        });
        table.add(quitButton);
    }

    private void drawBackground(){
        //CREATE_BACKGROUND_RECTANGLE(5,50,5,200,70,18);
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.setColor(0f,0f,0f,0.8f);
        shapeRenderer.rect(0,0,1920,1080);
        shapeRenderer.setColor(0f,0f,0f,1f);
        shapeRenderer.rect(0,0,500,1080);
        shapeRenderer.end();
    }

    public void render(){
        drawBackground();
        pauseMenuStage.act();
        pauseMenuStage.draw();
    }

    public Stage getStage() {
        return pauseMenuStage;
    }

    public void dispose(){
        pauseMenuStage.dispose();
        skin.dispose();
    }
}
