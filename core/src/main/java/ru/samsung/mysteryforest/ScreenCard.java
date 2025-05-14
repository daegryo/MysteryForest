package ru.samsung.mysteryforest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class ScreenCard implements Screen {
    Main main;

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;
    public BitmapFont fontScroll;
    public BitmapFont fontPodarok;
    public BitmapFont fontMessage;
    public BitmapFont fontMessageBig;
    public BitmapFont fontChapter1;

    Texture imgBg;
    Texture imgIconMap;
    Texture imgPlace;

    SpaceButton btnBack;

    MapIcon mapIconObject;
    MapIcon placeObject;

    float lastTouchX = 1700f;
    float lastTouchY = 1000f;

    boolean drawPlace = false;
    boolean noPlace = false;

    String place = "";



    public ScreenCard(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        fontScroll = new BitmapFont(Gdx.files.internal("fonts/scroll.fnt"));
        fontPodarok = new BitmapFont(Gdx.files.internal("fonts/Podarok.fnt"));
        fontMessage = new BitmapFont(Gdx.files.internal("fonts/message.fnt"));
        fontMessageBig = new BitmapFont(Gdx.files.internal("fonts/messageBig.fnt"));
        fontChapter1 = new BitmapFont(Gdx.files.internal("fonts/chapter1.fnt"));

        imgBg = new Texture("bg/card.png");
        imgIconMap = new Texture("icons/map.png");
        imgPlace = new Texture("text/map.png");

        btnBack = new SpaceButton(fontPodarok, 100, 50, "X");

        mapIconObject = new MapIcon(500, 400, 70, 86);
        placeObject = new MapIcon(470, 500, 100, 60);
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
            mapIconObject.x = touch.x;
            mapIconObject.y = touch.y;
            placeObject.x = touch.x - 30;
            placeObject.y = touch.y + 85;
            if (btnBack.hit(touch.x, touch.y)){
                main.setScreen(main.screenCar);
            }
            if (lastTouchX >= touch.x - 10 && lastTouchX <= touch.x + 10 && lastTouchY >= touch.y - 10 && lastTouchY <= touch.y + 10) {
                drawPlace = true;
                if (touch.x >= 1208 && touch.x <= 1598 && touch.y >= 388 && touch.y <= 900){
                    place = "крепость";
                    noPlace = false;
                }
                else if (touch.x >= 492 && touch.x <= 623 && touch.y >= 716 && touch.y <= 880){
                    place = "элек\nтричество";
                    noPlace = false;
                }
                else if (touch.x >= 705 && touch.x <= 907 && touch.y >= 497 && touch.y <= 604){
                    place = "Мия";
                    noPlace = false;
                }
                else if (touch.x >= 930 && touch.x <= 1044 && touch.y >= 630 && touch.y <= 758){
                    place = "магазин\n у Джона";
                    noPlace = false;
                }
                else {
                    noPlace = true;
                }

            }
            else
            {
                drawPlace = false;
            }
            lastTouchX = touch.x;
            lastTouchY = touch.y;
        }
        // events

        // paint
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.draw(imgBg, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
        btnBack.font.draw(batch, btnBack.text, btnBack.x, btnBack.y);
        batch.draw(imgIconMap, mapIconObject.x, mapIconObject.y, mapIconObject.width, mapIconObject.height);
        if (drawPlace) {
            if (!noPlace) {
                batch.draw(imgPlace, placeObject.x, placeObject.y, placeObject.width, placeObject.height);
                fontMessage.draw(batch, place, placeObject.x + 5, placeObject.y + 50);
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

    }
}
