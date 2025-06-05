package ru.samsung.mysteryforest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class ScreenStart implements Screen {
    private Main main;

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font, fontChapter1;
    Texture imgBG;

   SpaceButton btnGame;
   ShapeRenderer shapeRenderer;
   float alpha;
   boolean next = false;
   Music backgroundMusic;
   Sound soundClick;
   // SpaceButton btnSettings;
   // SpaceButton btnLeaderBoard;
    //SpaceButton btnAbout;
    //SpaceButton btnExit;
   public float volume = 0.5f;



    public ScreenStart(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        fontChapter1 = new BitmapFont(Gdx.files.internal("fonts/chapter1.fnt"));

        imgBG = new Texture("bg/start.png");


        btnGame = new SpaceButton(font, 750, 360, "play");

        shapeRenderer = new ShapeRenderer();
        alpha = 0f;

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/background.mp3"));
        soundClick = Gdx.audio.newSound(Gdx.files.internal("sounds/click.mp3"));
/*

        btnSettings = new SpaceButton(font, 900, 250, "Settings");
        btnAbout =  new SpaceButton(font, 800, 250, "About");
        btnLeaderBoard = new SpaceButton(font, 700, 250, "LeaderBoard");
        btnExit =  new SpaceButton(font, 600, 250, "Exit");

 */

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // касание
        if (Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            System.out.println(touch.x + " " + touch.y);
            if(btnGame.hit(touch.x, touch.y)){
                if (main.screenSettings.On) {
                    soundClick.play();
                }
                next = true;


            }
          /*  if(btnSettings.hit(touch.x, touch.y)){
                main.setScreen(main.screenSettings);
            }

            if(btnAbout.hit(touch.x, touch.y)){
                main.setScreen(main.screenAbout);
            }
            if(btnLeaderBoard.hit(touch.x, touch.y)){
                main.setScreen(main.screenLeaderBoard);
            }
            if(btnExit.hit(touch.x, touch.y)){
                Gdx.app.exit();
            }

           */




        }
        // events
        if (main.screenSettings.On) {
            backgroundMusic.play();
            backgroundMusic.setLooping(true);
            backgroundMusic.setVolume(volume);
        }

        // отрисовка
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(imgBG, 0, 0, Main.SCR_WIDTH,Main.SCR_HEIGHT);

        font.draw(batch, "Mystery Forest", 530, 465);
        btnGame.font.draw(batch,btnGame.text, btnGame.x, btnGame.y);

        if (next){
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(0, 0, 0, alpha);
            shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            shapeRenderer.end();

            alpha += delta * 0.6f;

            if (alpha >= 1) {
                System.out.println("SCREENSTART");
                main.setScreen(main.screenLogin);
            }
        }




        batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
        imgBG.dispose();
        batch.dispose();
        font.dispose();
        shapeRenderer.dispose();
        backgroundMusic.dispose();
        soundClick.dispose();

    }
}
