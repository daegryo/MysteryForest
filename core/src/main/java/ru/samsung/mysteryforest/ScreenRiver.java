package ru.samsung.mysteryforest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

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

    SpaceButton btnPhone;
    SpaceButton btnCard;


    private float alpha = 0.1f; // начальная прозрачность (1 = непрозрачный)
    private float speed = 0.7f; // скорость изменения прозрачности

    boolean stopFont = false;

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

        btnPhone = new SpaceButton(fontPodarok, 1400, 700, "phone");
        btnCard = new SpaceButton(fontPodarok, 1400, 670, "card");



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
            batch.draw(imgBg, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);

            batch.draw(imgEmily, 1300, 0, 250, 512);
            batch.draw(imgLara, 429, 0, 200, 512);
            batch.draw(imgAgata, 870, 0, 150, 512);
            btnPhone.font.draw(batch, btnPhone.text, btnPhone.x, btnPhone.y);
            btnCard.font.draw(batch, btnCard.text, btnCard.x, btnCard.y);

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
        imgBg.dispose();
    }

    public void updateFont(float deltaTime) {
        // Плавное изменение alpha (0 → 1 → 0)
        alpha = (float) (Math.sin(System.currentTimeMillis() * 0.003 * speed) * 0.5f + 0.5f);

        // Или линейное затухание/появление
        // alpha += deltaTime * speed;
        // if (alpha > 1.0f || alpha < 0.0f) speed *= -1; // меняем направление
    }
}
