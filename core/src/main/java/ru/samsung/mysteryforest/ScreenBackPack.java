package ru.samsung.mysteryforest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScreenBackPack implements Screen {
    Main main;

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;
    public BitmapFont fontScroll;
    public BitmapFont fontPodarok;

    Texture imgBackpack;
    BackPack backPack;

    SpaceButton btnBack;


    List<Texture> clues = new ArrayList<>();
    List<String> paths = new ArrayList<>();
    List<Clues> cluesObjects = new ArrayList<>();

    public String clas = "";

    boolean move = false;





    public ScreenBackPack(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        fontScroll = new BitmapFont(Gdx.files.internal("fonts/scroll.fnt"));
        fontPodarok = new BitmapFont(Gdx.files.internal("fonts/Podarok.fnt"));

        imgBackpack = new Texture("bg/backpack.png");
        btnBack = new SpaceButton(fontPodarok, 150, 30, "X");

        backPack = main.screenHomeSearch.backPack;

        for (int i = 0; i < cluesObjects.size(); i++) {
            cluesObjects.get(i).x = 550 + (i * 300);
            cluesObjects.get(i).y = 526;
            cluesObjects.get(i).width = 200;
            cluesObjects.get(i).height = 200;
        }




    }

    @Override
    public void show() {
        for (int i = 0; i < backPack.content.size(); i++) {

            if (!paths.contains(backPack.content.get(i).path)){
                Texture a = new Texture(backPack.content.get(i).path);
                clues.add(a);
                paths.add(backPack.content.get(i).path);
                cluesObjects.add(backPack.content.get(i));
            }

        }

    }

    @Override
    public void render(float delta) {
        // touches
        if (Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            System.out.println(touch.x + " " + touch.y);
            if (btnBack.hit(touch.x, touch.y)){
                if (Objects.equals(clas, "ScreenHomeSearch")) {
                    main.setScreen(main.screenHomeSearch);
                }
                if (Objects.equals(clas, "ScreenChapter1")) {
                    main.setScreen(main.screenChapter1);
                }
                if (Objects.equals(clas, "ScreenCar")) {
                    main.setScreen(main.screenCar);
                }

            }
            for (int i = 0; i < clues.size(); i++) {
                if (touch.x >= 550 + (i * 300) && touch.x <= 550 + (i * 300) + 200 && touch.y >= 526 && touch.y <= 726){
                    System.out.println("HHHHHHHHHHHHHHH");
                    cluesObjects.get(i).move = true;
                }

            }
        }

        // events


        for (int i = 0; i < cluesObjects.size(); i++) {
            cluesObjects.get(i).x = 550 + (i * 300);
            cluesObjects.get(i).y = 526;
            cluesObjects.get(i).width = 200;
            cluesObjects.get(i).height = 200;

        }

        //paint
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(imgBackpack, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
        btnBack.font.draw(batch, btnBack.text, btnBack.x, btnBack.y);
        fontPodarok.draw(batch, "Улики", 454, 593);
        for (int i = 0; i < clues.size(); i++) {
            batch.draw(clues.get(i), cluesObjects.get(i).x , cluesObjects.get(i).y, cluesObjects.get(i).width, cluesObjects.get(i).height);
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
