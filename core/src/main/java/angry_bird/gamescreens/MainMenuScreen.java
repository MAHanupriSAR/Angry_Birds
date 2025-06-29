package angry_bird.gamescreens;

import angry_bird.main.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import static angry_bird.utils.Constants.UI.skin.BUTTON_SKIN;
import static angry_bird.utils.Constants.UI.Backgrounds.*;

public class MainMenuScreen implements Screen {
    private Main main;
    private Texture backgroundImage;
    private Stage stage;
    private Table table;
    private Skin skin;
    private TextButton playButton;
    private TextButton quitButton;

    public MainMenuScreen(Main main){
        this.main = main;
        this.backgroundImage = new Texture(MENU_SCREEN_BACKGROUND);
        setupStageForMenuButtons();
        setupTableForMenuButtons();
        setupButtons();
    }

    private void setupStageForMenuButtons(){
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    private void setupTableForMenuButtons(){
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        table.defaults().width(200).height(70).padTop(900).padBottom(100).padLeft(100).padRight(100);
    }

    private void setupButtons(){
        skin = new Skin(Gdx.files.internal(BUTTON_SKIN));
        createPlayButton();
        createQuitButton();
    }

    private void createPlayButton(){
        playButton = new TextButton("PLAY",skin);
        playButton.addListener(event -> {
            if(playButton.isPressed()){
                main.setScreen(new GamesaveScreen(main));
            }
            return true;
        });
        table.add(playButton);
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

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        main.getSpriteBatch().begin();
        main.getSpriteBatch().draw(backgroundImage, 0, 0);
        main.getSpriteBatch().end();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
        backgroundImage.dispose();
        skin.dispose();
    }
}
