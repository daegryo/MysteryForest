package ru.samsung.mysteryforest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.utils.MeshBuilder;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class ScreenRegistration implements Screen {
    Main main;

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;
    public BitmapFont fontPodarok;

    Texture imgBg1;
    SpaceButton btnLogin;
    SpaceButton btnSettings;

    ShapeRenderer shapeRenderer;
    float alpha = 0f;
    boolean next = false;

    public ScreenRegistration(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        fontPodarok = new BitmapFont(Gdx.files.internal("fonts/Podarok.fnt"));

        imgBg1 = new Texture("bg/screenGame1.png");
        btnLogin = new SpaceButton(font, 710, 350, "Войти");
        btnSettings = new SpaceButton(fontPodarok, 1500, 50, "settings");
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        // touches
        if (Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            System.out.println(touch.x + " " + touch.y);
            if (btnLogin.hit(touch.x, touch.y)) {
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                next = true;
            }
            if (btnSettings.hit(touch.x, touch.y)){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenSettings.back = "ScreenRegistration";
                main.setScreen(main.screenSettings);
            }
        }
            //events

            // paint
            batch.begin();
            batch.setProjectionMatrix(camera.combined);

            batch.draw(imgBg1, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            font.draw(batch, "Войти в аккаунт", 531, 465);
            btnLogin.font.draw(batch, btnLogin.text, btnLogin.x, btnLogin.y);
            btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);

            if (next){

                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(0, 0, 0, alpha);
                shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                shapeRenderer.end();

                alpha += delta * 0.6f; // Скорость перехода

                if (alpha >= 1) {
                   System.out.println("SCREENSTART");
                    main.setScreen(main.screenHistory);
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
        batch.dispose();
        imgBg1.dispose();
        font.dispose();

    }
}

