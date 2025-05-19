package ru.samsung.mysteryforest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.Objects;

public class ScreenSettings implements Screen {
    private Main main;

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;
    public BitmapFont fontScroll;
    public BitmapFont fontPodarok;
    public BitmapFont fontMessage;

    Texture imgBg;

    public SpaceButton btnOn;
    public SpaceButton btnBack;

    boolean On = true;

    String back = "";



    public ScreenSettings(Main main) {

        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        fontScroll = new BitmapFont(Gdx.files.internal("fonts/scroll.fnt"));
        fontPodarok = new BitmapFont(Gdx.files.internal("fonts/Podarok.fnt"));
        fontMessage = new BitmapFont(Gdx.files.internal("fonts/message.fnt"));

        imgBg = new Texture("bg/history.png");

        btnOn = new SpaceButton(fontPodarok, 600, 629, "On");
        btnBack = new SpaceButton(fontPodarok, 10, 50, "back");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // touches
        if (Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            System.out.println(touch.x + " " + touch.y);
            if (btnOn.hit(touch.x, touch.y)){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                if (On){
                    btnOn.setText("Off");
                    if (!Objects.equals(back, "ScreenCarGame")) {
                        main.screenStart.backgroundMusic.pause();

                    }
                    if (Objects.equals(back, "ScreenCarGame")) {
                        main.screenCarGame.backgroundMusic.pause();
                    }
                    On = false;
                }
                else{
                    btnOn.setText("On");
                    if (!Objects.equals(back, "ScreenCarGame")) {
                        main.screenStart.backgroundMusic.play();
                    }
                    if (Objects.equals(back, "ScreenCarGame")) {
                        main.screenCarGame.backgroundMusic.play();
                    }
                    On = true;
                }
            }
            if (btnBack.hit(touch.x, touch.y)) {
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                if (Objects.equals(back, "ScreenRegistration")) {
                    main.setScreen(main.screenLogin);
                }
                if (Objects.equals(back, "ScreenHistory")) {
                    main.setScreen(main.screenHistory);
                }
                if (Objects.equals(back, "ScreenHomeSearch")) {
                    main.setScreen(main.screenHomeSearch);
                }
                if (Objects.equals(back, "ScreenChapter1")) {
                    main.setScreen(main.screenChapter1);
                }
                if (Objects.equals(back, "ScreenCar")) {
                    main.setScreen(main.screenCar);
                }
                if (Objects.equals(back, "ScreenCarGame")) {
                    main.setScreen(main.screenCarGame);
                }
                if (Objects.equals(back, "ScreenRiver")) {
                    main.setScreen(main.screenRiver);
                }

            }
        }
        //events

        //paint
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(imgBg, 0, 0, Main.SCR_WIDTH,Main.SCR_HEIGHT);
        fontPodarok.draw(batch, "Звук", 529, 629);
        btnOn.font.draw(batch, btnOn.text, btnOn.x, btnOn.y);
        btnBack.font.draw(batch, btnBack.text, btnBack.x, btnBack.y);
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

    }
}
