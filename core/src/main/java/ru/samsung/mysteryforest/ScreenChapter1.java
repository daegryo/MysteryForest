package ru.samsung.mysteryforest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.List;

public class ScreenChapter1 implements Screen {
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


    private float alpha = 0.1f; // начальная прозрачность (1 = непрозрачный)
    private float speed = 0.7f; // скорость изменения прозрачности

    boolean stopFont;
    boolean talkEmily = true;

    Texture imgBg;
    Texture imgBackpack;
    Texture imgPhone;
    Texture imgEmily;
    Texture imgLara;
    Texture imgInsert;
    Texture imgInsert1;
    Texture imgChoice;


    SpaceButton btnPhone;
    SpaceButton btnNextChapter;

    Insert insertObject1 = new Insert(970, 300, 450, 450);
    Insert insertObject2 = new Insert(500, 300, 450, 450);

    ReadFile readFileLara;
    ReadFile readFileEmilyChoice;


    List<String> messageLara;
    List<String> messageEmilyChoice;


    String[] partsEmily;
    String[] partsLara;



    int cursor = 0;
    int count = 0;

    boolean choice1 = false;
    boolean choice2 = false;
    boolean stopDialog = false;


    public ScreenChapter1(Main main) {
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
        stopFont = false;

        imgBg = new Texture("bg/houses/houseEmily.png");
        imgBackpack = new Texture("icons/backpack.png");
        imgPhone = new Texture("icons/phone.png");
        imgEmily = new Texture("heros/Emily/EmilyFullLength.png");
        imgLara = new Texture("heros/Lara/LaraFullLength.png");
        imgInsert = new Texture("text/insert.png");
        imgInsert1 = new Texture("text/insert1.png");
        imgChoice = new Texture("text/choice.png");

        btnPhone = new SpaceButton(fontPodarok, 1400, 700, "phone");
        btnNextChapter = new SpaceButton(font, 740, Main.SCR_HEIGHT/2, "Далее");

        readFileLara = new ReadFile("assets/story/chapter1/messageLara.txt", 1);
        readFileEmilyChoice = new ReadFile("assets/story/chapter1/messageEmilyToLara.txt", 1);


        messageLara = readFileLara.reader();
        messageEmilyChoice = readFileEmilyChoice.reader();

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
            if (touch.x >= 0 && touch.x <= Main.SCR_WIDTH && touch.y >= 0 && touch.y <= Main.SCR_HEIGHT) {
                stopFont = true;
            }
            if (btnPhone.hit(touch.x, touch.y)) {
                main.setScreen(main.screenPhone);
                main.screenPhone.clas = "ScreenChapter1";
            }
            if (touch.x >= 254 && touch.x <= 339 && touch.y >= 15 && touch.y <= 100) {
                main.setScreen(main.screenBackPack);
                main.screenBackPack.clas = "ScreenChapter1";
            }
            if (touch.x >= 1247 && touch.x <= 1443 && touch.y >= 0 && touch.y <= 493 && (choice1 || choice2)) {
                talkEmily = false;
            }
            if (touch.x >= 464 && touch.x <= 653 && touch.y >= 0 && touch.y <= 438) {
                talkEmily = true;
                if (messageEmilyChoice.size() - 1 > cursor) {
                    cursor++;
                    choice1 = false;
                    choice2 = false;
                }
                else{
                    stopDialog = true;
                    main.screenPhone.STATION = main.screenPhone.dialog2;
                }
            }
            if (touch.x >= 850 && touch.x <= 1200 && touch.y >= 63 && touch.y <= 103 && talkEmily){
                choice1 = true;
            }
            if (touch.x >= 850 && touch.x <= 1200 && touch.y >= 8 && touch.y <= 50 && talkEmily){
                choice2 = true;
            }
            if(btnNextChapter.hit(touch.x, touch.y) && main.screenPhone.nextChapter){
                main.setScreen(main.screenCar);
            }


        }

        //events

        //paint
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(imgBg, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
        String strEmily = messageEmilyChoice.get(cursor);
        partsEmily = strEmily.split("/");
        String strLara = messageLara.get(cursor);
        partsLara = strLara.split("/");
        if (!stopFont) {
            Color color = font.getColor();
            updateFont(1);
            fontChapter1.setColor(color.r, color.g, color.b, alpha); // меняем только alpha
            fontChapter1.draw(batch, "Первая глава", 450, 600);
            fontChapter1.setColor(color); // возвращаем исходный цвет
        }
        if (stopFont) {
            batch.draw(imgBackpack, 234, 15, 85, 85);
            btnPhone.font.draw(batch, btnPhone.text, btnPhone.x, btnPhone.y);
            batch.draw(imgEmily, 1200, 0, 250, 512);
            batch.draw(imgLara, 429, 0, 200, 512);
            if (!stopDialog) {
                if (talkEmily) {

                    if (!choice1 && !choice2) {
                        batch.draw(imgChoice, 850, 51, 355, 50);
                        batch.draw(imgChoice, 850, 0, 355, 50);
                        String[] par = partsEmily[1].split(":");
                        String str = "";
                        for (int i = 0; i < par.length; i++) {
                            str += par[i] + " ";
                        }
                        fontMessage.draw(batch, partsEmily[0], 925, 105);
                        fontMessage.draw(batch, str, 925, 31);
                    }
                    if (choice1) {
                        batch.draw(imgInsert, insertObject1.x, insertObject1.y, insertObject1.width, insertObject1.height);
                        fontMessageBig.draw(batch, partsEmily[0], 1030, 590);
                        if (count < 1) {
                            main.AttentionLara += Integer.parseInt(partsEmily[2]);
                            count++;
                        }

                    }
                    if (choice2) {
                        batch.draw(imgInsert, insertObject1.x, insertObject1.y, insertObject1.width, insertObject1.height);
                        String[] par = partsEmily[1].split(":");
                        fontMessageBig.draw(batch, par[0] + "\n" + par[1], 1030, 550);


                        if (count < 1) {
                            main.AttentionLara += Integer.parseInt(partsEmily[2]);
                            count++;
                        }


                    }

                }
                if (!talkEmily) {

                    batch.draw(imgInsert1, insertObject2.x, insertObject2.y, insertObject2.width, insertObject2.height);
                    if (choice1) {
                        String[] par = partsLara[0].split(":");
                        fontMessageBig.draw(batch, par[0] + "\n" + par[1], 588, 610);


                    }
                    if (choice2) {
                        String[] par = partsLara[1].split(":");
                        fontMessageBig.draw(batch, par[0] + "\n" + par[1], 588, 590);
                    }

                }
            }
            if (stopDialog){
                if (main.screenPhone.nextChapter){
                    btnNextChapter.font.draw(batch, btnNextChapter.text, btnNextChapter.x, btnNextChapter.y);

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

    public void updateFont(float deltaTime) {
        // Плавное изменение alpha (0 → 1 → 0)
        alpha = (float) (Math.sin(System.currentTimeMillis() * 0.001 * speed) * 0.5f + 0.5f);

        // Или линейное затухание/появление
        // alpha += deltaTime * speed;
        // if (alpha > 1.0f || alpha < 0.0f) speed *= -1; // меняем направление
    }
}
