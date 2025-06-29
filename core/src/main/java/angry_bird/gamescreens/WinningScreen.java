package angry_bird.gamescreens;

import angry_bird.Levels.Level;
import angry_bird.main.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import static angry_bird.utils.Constants.UI.skin.BUTTON_SKIN;
import static angry_bird.utils.Constants.UI.Backgrounds.*;


public class WinningScreen {
    private Main main;
    private PlayingScreen playingScreen;
    private Texture background;
    private Stage winningScreenStage;
    private Table table;
    private Skin skin;
    private TextButton menuButton;
    private TextButton lvlSelectButton;
    private TextButton nextLvlButton;
    private TextButton replayButton;
    private TextButton quitButton;

    public WinningScreen(Main main, PlayingScreen playingScreen) {
        this.main = main;
        this.playingScreen = playingScreen;
        background = new Texture(Gdx.files.internal(WINNING_BACKGROUND));
        setupStageForWinningScreen();
        setupTableForWinningScreen();
        AddButtonsToWinningScreenStage();
    }

    private void setupStageForWinningScreen() {
        winningScreenStage = new Stage(new ScreenViewport());
    }

    private void setupTableForWinningScreen(){
        table = new Table();
        table.setFillParent(true);
        winningScreenStage.addActor(table);
        table.defaults().width(200).height(70).pad(18);
    }

    private void AddButtonsToWinningScreenStage() {
        skin = new Skin(Gdx.files.internal(BUTTON_SKIN));

        createMainMenuButton();
        createLvlSelectButton();
        createNextLvlButton();
        createReplayButton();
        createQuitButton();
    }
    private void createMainMenuButton(){
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
    private void createNextLvlButton(){
        nextLvlButton = new TextButton("NEXT", skin);
        nextLvlButton.addListener(event -> {
            if(nextLvlButton.isPressed()){
                int currLvlNumber = playingScreen.getLevelManager().getCurrLevelNumber();
                if(currLvlNumber==3){
                    main.setScreen(new MainMenuScreen(main));
                }
                else {
                    Level level = playingScreen.getLevelManager().createNewLevel(currLvlNumber + 1);
                    playingScreen.getLevelManager().setCurrLevel(level);
                    playingScreen.getLevelManager().setCurrLevelNumber(currLvlNumber + 1);
                }
            }
            return true;
        });
        table.add(nextLvlButton);
        table.row();
    }
    private void createReplayButton(){
        replayButton = new TextButton("REPLAY", skin);
        replayButton.addListener(event -> {
            if(replayButton.isPressed()){
                int currLvlNumber = playingScreen.getLevelManager().getCurrLevelNumber();
                Level level = playingScreen.getLevelManager().createNewLevel(currLvlNumber);
                playingScreen.getLevelManager().setCurrLevel(level);
            }
            return true;
        });
        table.add(replayButton);
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

    private void drawBackground(SpriteBatch spriteBatch){
        //CREATE_BACKGROUND_RECTANGLE(5,50,5,200,70,18);
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, 1920, 1080);
        spriteBatch.end();
    }

    public void render(){
        drawBackground(main.getSpriteBatch());
        winningScreenStage.act();
        winningScreenStage.draw();
    }

    public Stage getStage() {
        return winningScreenStage;
    }

    public void dispose(){
        winningScreenStage.dispose();
        background.dispose();
        skin.dispose();
    }
}
