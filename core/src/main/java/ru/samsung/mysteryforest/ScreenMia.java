package ru.samsung.mysteryforest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import java.util.List;

public class ScreenMia implements Screen {
    Main main;

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;
    public BitmapFont fontScroll;
    public BitmapFont fontPodarok;
    public BitmapFont fontMessageBig;

    Texture imgBg;

    Texture imgEmily;
    Texture imgLara;
    Texture imgAgata;

    Texture imgInsert;


    SpaceButton btnPhone;
    SpaceButton btnCard;
    SpaceButton btnSettings;

    public ReadFile readFile;
    List<String> array;

    private float alpha = 0.1f;
    private float speed = 0.7f;
    ShapeRenderer shapeRenderer;

    boolean next = false;

    public ScreenMia(Main main) {
        this.main = main;

        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        fontScroll = new BitmapFont(Gdx.files.internal("fonts/scroll.fnt"));
        fontPodarok = new BitmapFont(Gdx.files.internal("fonts/Podarok.fnt"));
        fontMessageBig = new BitmapFont(Gdx.files.internal("fonts/bundle.fnt"));

        imgBg = new Texture("bg/Mia.png");

        imgEmily = new Texture("heros/Emily/EmilyFullLength.png");
        imgLara = new Texture("heros/Lara/LaraFullLength.png");
        imgAgata = new Texture("heros/Agata/FullLength1.png");

        imgInsert = new Texture("text/insert1.png");

        btnPhone = new SpaceButton(fontPodarok, 1400, 700, "phone");
        btnCard = new SpaceButton(fontPodarok, 1400, 670, "card");
        btnSettings = new SpaceButton(fontPodarok, 1500, 50, "settings");

        readFile = new ReadFile("assets/story/screenRiver/Mia.txt", 2);
        array = readFile.reader();

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
        }
        // events
        main.Station = "screenMia";
        // paint
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(imgBg, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);

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
