package ru.samsung.mysteryforest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.Objects;

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
    SpaceButton btnSettings;
    SpaceButton btnHomeEmily;
    SpaceButton btnShop;

    MapIcon mapIconObject;
    MapIcon placeObject;

    float lastTouchX = 1700f;
    float lastTouchY = 1000f;

    boolean drawPlace = false;
    boolean noPlace = false;

    boolean homeEmily = false;
    boolean shop = false;

    String place = "";
    String back = "";



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
        btnSettings = new SpaceButton(fontPodarok, 1500, 50, "settings");
        btnHomeEmily = new SpaceButton(fontPodarok, 312, 515, "отправиться");
        btnShop = new SpaceButton(fontPodarok, 932, 302, "отправиться");


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
            if (btnSettings.hit(touch.x, touch.y)){
                main.screenSettings.back = "ScreenCard";
                main.setScreen(main.screenSettings);
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
                if (Objects.equals(back, "ScreenMia")) {
                    main.setScreen(main.screenMia);
                }
                if (Objects.equals(back, "ScreenStore")) {
                    main.setScreen(main.screenStore);
                }


            }
            if (btnHomeEmily.hit(touch.x, touch.y) && homeEmily){
                main.screenHomeSearch.next = false;
                main.setScreen(main.screenHomeSearch);
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
            }
            if (lastTouchX >= touch.x - 10 && lastTouchX <= touch.x + 10 && lastTouchY >= touch.y - 10 && lastTouchY <= touch.y + 10) {
                drawPlace = true;
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                if (touch.x >= 210 && touch.x <= 366 && touch.y >= 432 && touch.y <= 688){
                    place = "дом Эмили";
                    noPlace = false;
                    homeEmily = true;
                    shop = false;
                }
                else if (touch.x >= 932 && touch.x <= 1065 && touch.y >= 302 && touch.y <= 365){
                    place = "магазин\n у Джона";
                    noPlace = false;
                    homeEmily = false;
                    shop = true;
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
        btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);
        btnBack.font.draw(batch, btnBack.text, btnBack.x, btnBack.y);
        batch.draw(imgIconMap, mapIconObject.x, mapIconObject.y, mapIconObject.width, mapIconObject.height);
        if (drawPlace) {
            if (!noPlace) {
                batch.draw(imgPlace, placeObject.x, placeObject.y, placeObject.width, placeObject.height);
                fontMessage.draw(batch, place, placeObject.x + 5, placeObject.y + 50);
                if (homeEmily){
                    btnHomeEmily.font.draw(batch, btnHomeEmily.text, btnHomeEmily.x, btnHomeEmily.y);
                }
                if (shop){
                    btnShop.font.draw(batch, btnShop.text, btnShop.x, btnShop.y);
                }
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
