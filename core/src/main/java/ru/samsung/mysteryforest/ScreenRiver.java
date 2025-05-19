package ru.samsung.mysteryforest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import java.util.List;

public class ScreenRiver implements Screen {
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
    Texture imgEmily;
    Texture imgLara;
    Texture imgAgata;

    Texture imgInsert;

    Insert insertObject = new Insert(800, 400, 450, 450);

    SpaceButton btnPhone;
    SpaceButton btnCard;
    SpaceButton btnSettings;
    SpaceButton btnBack;


    private float alpha = 0.1f; // начальная прозрачность (1 = непрозрачный)
    private float speed = 0.7f; // скорость изменения прозрачности

    boolean stopFont = false;
    boolean drawButtonBack = true;
    boolean next = false;

    public ReadFile readFile;
    List<String> array;

    int cursor = 0;

    ShapeRenderer shapeRenderer;

    public ScreenRiver(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        fontScroll = new BitmapFont(Gdx.files.internal("fonts/scroll.fnt"));
        fontPodarok = new BitmapFont(Gdx.files.internal("fonts/Podarok.fnt"));
        fontMessage = new BitmapFont(Gdx.files.internal("fonts/message.fnt"));
        fontMessageBig = new BitmapFont(Gdx.files.internal("fonts/messageBig.fnt"));
        fontChapter1 = new BitmapFont(Gdx.files.internal("fonts/blood.fnt"));

        imgBg = new Texture("bg/river.png");
        imgEmily = new Texture("heros/Emily/EmilyFullLength.png");
        imgLara = new Texture("heros/Lara/LaraFullLength.png");
        imgAgata = new Texture("heros/Agata/FullLength1.png");

        imgInsert = new Texture("text/insert1.png");

        btnPhone = new SpaceButton(fontPodarok, 1400, 700, "phone");
        btnCard = new SpaceButton(fontPodarok, 1400, 670, "card");
        btnSettings = new SpaceButton(fontPodarok, 1500, 50, "settings");
        btnBack = new SpaceButton(fontPodarok, 100, 70, "Back");

        readFile = new ReadFile("assets/story/screenRiver/Mia.txt", 2);
        array = readFile.reader();

        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //touches
        if (Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            System.out.println(touch.x + " " + touch.y);
            if (touch.x >= 0 && touch.x <= Main.SCR_WIDTH && touch.y >= 0 && touch.y <= Main.SCR_HEIGHT) {
                stopFont = true;
            }
            if (btnSettings.hit(touch.x, touch.y) && stopFont){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenSettings.back = "ScreenRiver";
                main.setScreen(main.screenSettings);
            }
            if (cursor < array.size() && touch.x >= 870 && touch.x <= 1210 && touch.y >= 531 && touch.y <= 756 && stopFont){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                cursor++;
            }
            if(btnBack.hit(touch.x, touch.y)) {
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }

                if (cursor != 0) {
                    cursor -= 1;
                }
            }
            if (cursor == array.size()) {
                    drawButtonBack = false;
                    if (main.screenSettings.On) {
                        main.screenStart.soundClick.play();
                    }

                }

        }
        // events

        // paint
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.draw(imgBg, 0,  0, Main.SCR_WIDTH, Main.SCR_HEIGHT);

        if (!stopFont) {
            Color color = font.getColor();
            updateFont(1);
            fontChapter1.setColor(color.r, color.g, color.b, alpha); // меняем только alpha
            fontChapter1.draw(batch, "Вторая глава", 450, 600);
            fontChapter1.setColor(color); // возвращаем исходный цвет
        }
        else {
            if (cursor != array.size()) {
                batch.draw(imgInsert, insertObject.x, insertObject.y, insertObject.width, insertObject.height);
                fontMessageBig.draw(batch, array.get(cursor), 880, 700);

            }
            else{
                next = true;
            }
            fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);
            btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);
            btnPhone.font.draw(batch, btnPhone.text, btnPhone.x, btnPhone.y);
            btnCard.font.draw(batch, btnCard.text, btnCard.x, btnCard.y);

            if (next){

                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(0, 0, 0, alpha);
                shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                shapeRenderer.end();

                alpha += delta * 0.4f; // Скорость перехода

                if (alpha >= 1) {
                    System.out.println("SCREENSTART");
                    main.setScreen(main.screenEnd);
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
        imgBg.dispose()
        ;
    }

    public void updateFont(float deltaTime) {
        alpha = (float) (Math.sin(System.currentTimeMillis() * 0.003 * speed) * 0.5f + 0.5f);
    }
}
