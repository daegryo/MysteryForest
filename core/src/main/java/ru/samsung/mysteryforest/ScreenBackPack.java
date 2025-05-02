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

    Texture imgPaper1;
    Texture imgPaper2;
    Texture imgPaper3;

    List<Texture> clues = new ArrayList<>();

    



    public ScreenBackPack(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        fontScroll = new BitmapFont(Gdx.files.internal("fonts/scroll.fnt"));
        fontPodarok = new BitmapFont(Gdx.files.internal("fonts/Podarok.fnt"));

        imgBackpack = new Texture("bg/backpack.png");
        btnBack = new SpaceButton(fontPodarok, 150, 15, "X");

        backPack = main.screenHomeSearch.backPack;



    }

    @Override
    public void show() {
        for (int i = 0; i < backPack.content.size(); i++) {
            Texture a = new Texture(backPack.content.get(i).path);
            clues.add(a);
        }

    }

    @Override
    public void render(float delta) {
        System.out.println(backPack.content.size());
        // touches
        if (Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            System.out.println(touch.x + " " + touch.y);
            if (btnBack.hit(touch.x, touch.y)){
                main.setScreen(main.screenHomeSearch );
            }
        }

        // events

        //paint
        System.out.println(clues.size());
        batch.begin();
        batch.draw(imgBackpack, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
        btnBack.font.draw(batch, btnBack.text, btnBack.x, btnBack.y);
        fontPodarok.draw(batch, "Улики", 454, 593);
        for (int i = 0; i < clues.size(); i++) {
            batch.draw(clues.get(i), 550 + (i * 150) , 526, 200,200);
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
