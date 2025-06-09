package ru.samsung.mysteryforest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.List;

public class ScreenStore implements Screen {
    Main main;

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;
    public BitmapFont fontScroll;
    public BitmapFont fontPodarok;
    public BitmapFont fontMessageChoice;
    public BitmapFont fontMessageBig;
    public BitmapFont fontYellow;

    Texture imgBg;
    Texture imgBgBlur;
    Texture imgBgBlack;
    Texture imgStoreIn;

    Texture imgStoreInStairs;

    Texture imgBackpack;

    Texture imgJohn;

    Texture imgInsertJohn;



    boolean talkAuthor = true;
    boolean talkEmily = false;
    boolean talkLara = false;
    boolean talkAgata = false;
    boolean talkJohn = false;
    boolean choice1 = false;
    boolean choice2 = false;

    boolean drawButton = false;
    boolean talking = false;

    String[] partsEmily;
    String[] partsAgata;
    String[] partsLara;
    String[] partsJohn;

    Insert insertObjectEmily = new Insert(1050, 300, 450, 450);
    Insert insertObjectLara = new Insert(520, 300, 450, 450);
    Insert insertObjectAgata = new Insert(580, 300, 450, 450);
    Insert insertObjectJohn = new Insert(780, 350, 400, 400);

    ReadFile readFileLara;
    ReadFile readFileEmily;
    ReadFile readFileAgata;
    ReadFile readFileJohn;
    ReadFile readFileAuthor;

    List<String> messageLara;
    List<String> messageEmily;
    List<String> messageAgata;
    List<String> messageJohn;
    List<String> messageAuthor;

    SpaceButton btnPhone;
    SpaceButton btnCard;
    SpaceButton btnSettings;

    SpaceButton btnOpen;



    int cursor = 0;
    int cursorAuthor = 0;

    String medium = "";

    Vector3 objectPositionStore = new Vector3(92, 141, 0);
    Vector3 objectSizeStore = new Vector3(1062, 651, 0);

    Vector3 objectPositionStairs = new Vector3(512, 136, 0);
    Vector3 objectSizeStairs = new Vector3(145, 500, 0);

    int IMAGE = 0;
    int black = 0;
    int blur = 1;
    int store = 2;
    int storeIn = 3;
    int storeInStairs = 4;


    public ScreenStore(Main main) {
        this.main = main;

        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        fontScroll = new BitmapFont(Gdx.files.internal("fonts/scroll.fnt"));
        fontPodarok = new BitmapFont(Gdx.files.internal("fonts/Podarok.fnt"));
        fontMessageBig = new BitmapFont(Gdx.files.internal("fonts/bundle.fnt"));
        fontMessageChoice = new BitmapFont(Gdx.files.internal("fonts/bundleSmall.fnt"));
        fontYellow = new BitmapFont(Gdx.files.internal("fonts/yellow.fnt"));

        imgBg = new Texture("bg/store.png");
        imgBgBlur = new Texture("bg/storeBlur.png");
        imgBgBlack = new Texture("text/author.png");
        imgStoreIn = new Texture("bg/store/storeIn.png");

        imgStoreInStairs = new Texture("bg/store/storeInStairs.png");

        imgBackpack = new Texture("icons/backpack.png");

        imgJohn = new Texture("heros/John/John.png");

        imgInsertJohn = new Texture("text/insert1.png");

        btnPhone = new SpaceButton(fontPodarok, 1400, 700, "phone");
        btnCard = new SpaceButton(fontPodarok, 1400, 670, "card");
        btnSettings = new SpaceButton(fontPodarok, 1500, 50, "settings");

        btnOpen = new SpaceButton(fontPodarok, 572, 398, "войти в магазин");

        readFileLara = new ReadFile("assets/story/screenStore/Lara.txt", 1);
        readFileEmily = new ReadFile("assets/story/screenStore/Emily.txt", 1);
        readFileAgata = new ReadFile("assets/story/screenStore/Agata.txt", 1);
        readFileJohn = new ReadFile("assets/story/screenStore/John.txt", 1);

        readFileAuthor = new ReadFile("assets/story/screenStore/author.txt", 3);

        messageLara = readFileLara.reader();
        messageEmily = readFileEmily.reader();
        messageAgata = readFileAgata.reader();
        messageJohn= readFileJohn.reader();

        messageAuthor = readFileAuthor.reader();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // mouse
        Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mousePos);
        if (mousePos.x >= objectPositionStore.x &&
            mousePos.x <= objectPositionStore.x + objectSizeStore.x &&
            mousePos.y >= objectPositionStore.y &&
            mousePos.y <= objectPositionStore.y + objectSizeStore.y && IMAGE == store && !talkJohn && !talking) {
            drawButton = true;
        }
        else {
            drawButton = false;
        }
        if (mousePos.x >= objectPositionStairs.x &&
            mousePos.x <= objectPositionStairs.x + objectSizeStairs.x &&
            mousePos.y >= objectPositionStairs.y &&
            mousePos.y <= objectPositionStairs.y + objectSizeStairs.y && IMAGE == storeIn) {
            IMAGE = storeInStairs;
        }
        else {
            if (IMAGE == storeInStairs){
                IMAGE = storeIn;
            }
        }
        // touches
        if (Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            System.out.println(touch.x + " " + touch.y);
            if (touch.x >= 0 && touch.x <= Main.SCR_WIDTH && touch.y >= 0 && touch.y <= Main.SCR_HEIGHT && talkAuthor){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                if (cursorAuthor <= 2) {
                    cursorAuthor++;
                }
                if (cursorAuthor > 2) {
                    talkAuthor = false;
                    talkJohn = true;
                    IMAGE = store;
                }
            }
            if (touch.x >= 749 && touch.x <= 880 && touch.y >= 0 && touch.y <= 880 && talkJohn && !talking){
                talkJohn = false;
            }
            if (btnOpen.hit(touch.x, touch.y)){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                IMAGE = storeIn;
            }

            if (btnPhone.hit(touch.x, touch.y)) {
                main.setScreen(main.screenPhone);
                main.screenPhone.clas = "ScreenStore";
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
            }
            if (touch.x >= 254 && touch.x <= 339 && touch.y >= 15 && touch.y <= 100) {
                main.setScreen(main.screenBackPack);
                main.screenBackPack.clas = "ScreenStore";
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
            }
            if (btnSettings.hit(touch.x, touch.y)){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenSettings.back = "ScreenStore";
                main.setScreen(main.screenSettings);
            }

        }
        // events
        main.Station = "screenStore";

        // paint
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        if (IMAGE == black) {
            batch.draw(imgBgBlack, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
        }
        if (IMAGE == blur){
            batch.draw(imgBgBlur, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);
            btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);
            btnPhone.font.draw(batch, btnPhone.text, btnPhone.x, btnPhone.y);
            btnCard.font.draw(batch, btnCard.text, btnCard.x, btnCard.y);
            batch.draw(imgBackpack, 234, 15, 120, 90);
        }
        if (IMAGE == store){
            batch.draw(imgBg, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);
            btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);
            btnPhone.font.draw(batch, btnPhone.text, btnPhone.x, btnPhone.y);
            btnCard.font.draw(batch, btnCard.text, btnCard.x, btnCard.y);
            batch.draw(imgBackpack, 234, 15, 120, 90);
            if (talkJohn){
                batch.draw(imgJohn, 737, 0, 200, 500);
                batch.draw(imgInsertJohn, insertObjectJohn.x, insertObjectJohn.y, insertObjectJohn.width, insertObjectJohn.height);
                if (!talking){
                    fontMessageBig.draw(batch, "Добро пожаловать \nв магазин Джона! \n    Заходите", 870, 600);
                }

            }
        }
        if (IMAGE == storeIn){
            batch.draw(imgStoreIn, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);
            fontPodarok.draw(batch, "Пояснение: ты можешь купить \n5 предметов, которые тебе понадобятся далее.\nПокупай предметы, \nисходя из своего банка", 993, 817);

            btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);
            btnPhone.font.draw(batch, btnPhone.text, btnPhone.x, btnPhone.y);
            btnCard.font.draw(batch, btnCard.text, btnCard.x, btnCard.y);
            batch.draw(imgBackpack, 234, 15, 120, 90);
        }
        if (IMAGE == storeInStairs){
            batch.draw(imgStoreInStairs, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);
            fontPodarok.draw(batch, "Пояснение: ты можешь купить \n5 предметов, которые тебе понадобятся далее.\nПокупай предметы, \nисходя из своего банка", 993, 817);

            btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);
            btnPhone.font.draw(batch, btnPhone.text, btnPhone.x, btnPhone.y);
            btnCard.font.draw(batch, btnCard.text, btnCard.x, btnCard.y);
            batch.draw(imgBackpack, 234, 15, 120, 90);
        }



        if (talkAuthor){
            batch.draw(imgBgBlack, 300, 200, 1000, 500);
            medium = messageAuthor.get(cursorAuthor);
            fontYellow.draw(batch, medium, 332, 600);
        }


        if (drawButton){
            btnOpen.font.draw(batch, btnOpen.text, btnOpen.x, btnOpen.y);
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
