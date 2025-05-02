package ru.samsung.mysteryforest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
    public BitmapFont font;

    Texture imgBG;

   SpaceButton btnGame;
   ShapeRenderer shapeRenderer;

   float alpha;
   boolean next = false;
   // SpaceButton btnSettings;
   // SpaceButton btnLeaderBoard;
    //SpaceButton btnAbout;
    //SpaceButton btnExit;



    public ScreenStart(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;

        imgBG = new Texture("bg/start.png");


        btnGame = new SpaceButton(font, 650, 300, "play");

        shapeRenderer = new ShapeRenderer();
        alpha = 0f;
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

        // отрисовка
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(imgBG, 0, 0, Main.SCR_WIDTH,Main.SCR_HEIGHT);

        font.draw(batch, "Mystery Forest", 620, 465);
        btnGame.font.draw(batch,btnGame.text, btnGame.x, btnGame.y);

        if (next){
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(0, 0, 0, alpha);
            shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            shapeRenderer.end();

            alpha += delta * 0.6f; // Скорость перехода

            if (alpha >= 1) {
                System.out.println("SCREENSTART");
                main.setScreen(main.screenLogin);
            }
        }




        batch.end();

        /*

        btnSettings.font.draw(batch,btnSettings.text, btnSettings.x, btnSettings.y);
        btnAbout.font.draw(batch,btnAbout.text, btnAbout.x, btnAbout.y);
        btnLeaderBoard.font.draw(batch,btnLeaderBoard.text, btnLeaderBoard.x, btnLeaderBoard.y);
        btnExit.font.draw(batch,btnExit.text, btnExit.x, btnExit.y);

         */

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

    }
}
