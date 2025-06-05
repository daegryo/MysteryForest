package ru.samsung.mysteryforest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.List;
import java.util.Objects;

public class ScreenCar implements Screen {
    Main main;

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;
    public BitmapFont fontScroll;
    public BitmapFont fontPodarok;
    public BitmapFont fontMessage;
    public BitmapFont fontMessageChoice;
    public BitmapFont fontMessageBig;
    public BitmapFont fontChapter1;

    Texture imgBg;
    Texture imgCar;
    Texture imgBackpack;
    Texture imgEmily;
    Texture imgLara;
    Texture imgAgata;
    Texture imgInsertEmily;
    Texture imgInsertAgata;
    Texture imgInsertLara;
    Texture imgChoice;
    Texture imgPaper1;
    Texture imgCard;


    SpaceButton btnPhone;
    SpaceButton btnCard;
    SpaceButton btnNextChapter;
    SpaceButton btnTakeIt1;
    SpaceButton btnTakeIt2;
    SpaceButton btnSettings;


    Insert insertObjectEmily = new Insert(1050, 300, 450, 450);
    Insert insertObjectLara = new Insert(520, 300, 450, 450);
    Insert insertObjectAgata = new Insert(580, 300, 450, 450);

    ReadFile readFileLara;
    ReadFile readFileEmily;
    ReadFile readFileAgata;


    List<String> messageLara;
    List<String> messageEmily;
    List<String> messageAgata;

    boolean talkEmily = true;
    boolean talkAgata = false;
    boolean talkLara = false;
    boolean choice1 = false;
    boolean choice2 = false;
    boolean show = false;
    boolean stopDialog = false;
    boolean showPaper = false;
    boolean showCard = false;

    String[] partsEmily;
    String[] partsAgata;
    String[] partsLara;

    Clues cluesObject1 = new Clues(921, 526, 20, 20, "text/paper1.png");
    Clues cluesObject2 = new Clues(921, 526, 20, 20, "bg/card.png");


    int count = 0;
    int cursor = 0;
    int counter = 0;

    int IMAGE = 0;
    int bg = 0;
    int car = 1;

    Vector3 objectPositionCar = new Vector3(431, 135, 0);
    Vector3 objectSizeCar = new Vector3(723, 291, 0);

    //touch.x >= 431 && touch.x <= 1154 && touch.y >= 135 && touch.y <= 426


    public ScreenCar(Main main) {
        this.main = main;

        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        fontScroll = new BitmapFont(Gdx.files.internal("fonts/scroll.fnt"));
        fontPodarok = new BitmapFont(Gdx.files.internal("fonts/Podarok.fnt"));
        fontMessage = new BitmapFont(Gdx.files.internal("fonts/message.fnt"));
        fontMessageBig = new BitmapFont(Gdx.files.internal("fonts/messageBig.fnt"));
        fontMessageChoice = new BitmapFont(Gdx.files.internal("fonts/bundleSmall.fnt"));
        fontChapter1 = new BitmapFont(Gdx.files.internal("fonts/chapter1.fnt"));

        imgBg = new Texture("bg/car.png");
        imgBackpack = new Texture("icons/backpack.png");
        imgCar = new Texture("bg/car/carYellow.png");
        imgEmily = new Texture("heros/Emily/EmilyFullLength.png");
        imgLara = new Texture("heros/Lara/LaraFullLength.png");
        imgAgata = new Texture("heros/Agata/FullLength1.png");
        imgInsertEmily = new Texture("text/insert.png");
        imgInsertAgata = new Texture("text/insert.png");
        imgInsertLara = new Texture("text/insert1.png");
        imgChoice = new Texture("text/choice.png");
        imgPaper1 = new Texture("text/paper1.png");
        imgCard = new Texture("bg/card.png");

        btnPhone = new SpaceButton(fontPodarok, 1400, 700, "phone");
        btnCard = new SpaceButton(fontPodarok, 1400, 670, "card");

        btnNextChapter = new SpaceButton(font, 740, Main.SCR_HEIGHT/2, "Далее");
        btnTakeIt1 = new SpaceButton(fontPodarok, 802, 358, "Свернуть");
        btnTakeIt2 = new SpaceButton(fontPodarok, 802, 300, "Взять");
        btnSettings = new SpaceButton(fontPodarok, 1500, 50, "settings");



        readFileLara = new ReadFile("assets/story/screenCar/Lara.txt", 1);
        readFileEmily = new ReadFile("assets/story/screenCar/Emily.txt", 1);
        readFileAgata = new ReadFile("assets/story/screenCar/Agata.txt", 1);

        messageLara = readFileLara.reader();
        messageEmily = readFileEmily.reader();
        messageAgata = readFileAgata.reader();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // mouse
        Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mousePos);
        if (mousePos.x >= objectPositionCar.x &&
            mousePos.x <= objectPositionCar.x + objectSizeCar.x &&
            mousePos.y >= objectPositionCar.y &&
            mousePos.y <= objectPositionCar.y + objectSizeCar.y && IMAGE == bg && counter < 1) {
            IMAGE = car;
        }
        else {
            if (IMAGE == car){
                IMAGE = bg;
            }
        }
        // touches
        if (Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            System.out.println(touch.x + " " + touch.y);
            if (btnPhone.hit(touch.x, touch.y)) {
                main.setScreen(main.screenPhone);
                main.screenPhone.clas = "ScreenCar";
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
            }
            if (touch.x >= 254 && touch.x <= 339 && touch.y >= 15 && touch.y <= 100) {
                main.setScreen(main.screenBackPack);
                main.screenBackPack.clas = "ScreenCar";
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
            }
            if (btnSettings.hit(touch.x, touch.y)){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenSettings.back = "ScreenCar";
                main.setScreen(main.screenSettings);
            }
            if (btnNextChapter.hit(touch.x, touch.y)) {
                if (main.screenSettings.On) {
                main.screenStart.soundClick.play();
            }
                main.dbHelper.updateInformation(main.Id);
                main.setScreen(main.screenCarGame);
            }
            if (touch.x >= 1347 && touch.x <= 1508 && touch.y >= 0 && touch.y <= 478 && talkEmily && (choice1 || choice2)) {
                talkEmily = false;
                talkAgata = true;
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
            }
            if (touch.x >= 896 && touch.x <= 1089 && touch.y >= 0 && touch.y <= 473 && talkAgata) {
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                talkAgata = false;
                talkLara = true;
            }
            if (touch.x >= 492 && touch.x <= 639 && touch.y >= 0 && touch.y <= 445 && talkLara) {
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                talkLara = false;
                talkEmily = true;
                if (cursor == 0) {
                    System.out.println(partsLara[0]);
                    show = true;
                    showPaper = true;
                }
                if (cursor == 4){
                    show = true;
                    showCard = true;
                    System.out.println("CARD");
                }
                if (messageEmily.size() - 1 > cursor) {
                    cursor++;
                    choice1 = false;
                    choice2 = false;
                    if (cursor == 1){
                        main.screenPhone.STATION = main.screenPhone.dialog3;
                    }
                }
                else {
                    stopDialog = true;
                }
            }
            if (touch.x >= 854 && touch.x <= 1244 && touch.y >= 63 && touch.y <= 104) {
                choice1 = true;
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
            }
            if (touch.x >= 854 && touch.x <= 1244 && touch.y >= 0 && touch.y <= 51) {
                choice2 = true;
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
            }
            if (btnTakeIt1.hit(touch.x, touch.y)){
                show = false;
                showPaper = false;
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
            }
            if (btnTakeIt2.hit(touch.x, touch.y)){
                cluesObject2.takeIt = true;
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
            }
            if(btnCard.hit(touch.x, touch.y)){
                main.setScreen(main.screenCard);
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
            }
            if (touch.x >= 431 && touch.x <= 1154 && touch.y >= 135 && touch.y <= 426 && counter < 1) {
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenClues.back = "ScreenCar";
                main.screenClues.IMAGE = main.screenClues.car;
                main.setScreen(main.screenClues);
                counter++;
            }

        }

        // events
        if (cluesObject2.stopShow){
            show = false;
            showCard = false;

        }
        main.Station = "screenCar";

        //paint
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);
        if (IMAGE == bg) {
            batch.draw(imgBg, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
        }
        if (IMAGE == car){
            batch.draw(imgCar, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
        }
        btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);
        batch.draw(imgBackpack, 234, 15, 85, 85);
        batch.draw(imgEmily, 1300, 0, 250, 512);
        batch.draw(imgLara, 429, 0, 200, 512);
        batch.draw(imgAgata, 870, 0, 150, 512);
        fontPodarok.draw(batch, "Лара", 405, 498);
        fontPodarok.draw(batch, "Эмили", 1466, 498);
        fontPodarok.draw(batch, "Агата", 981, 498);
        if (6 == cursor){
            btnNextChapter.font.draw(batch, btnNextChapter.text, btnNextChapter.x, btnNextChapter.y);
            btnCard.font.draw(batch, btnCard.text, btnCard.x, btnCard.y);

        }
        else {
            if (cluesObject2.stopShow){
                btnCard.font.draw(batch, btnCard.text, btnCard.x, btnCard.y);
            }
            if (!stopDialog && (cursor != 2 || main.screenPhone.nextPieceCar)) {

                if (talkEmily && !show) {
                    partsEmily = messageEmily.get(cursor).split("/");
                    if (!choice1 && !choice2) {
                        batch.draw(imgChoice, 850, 51, 400, 50);
                        batch.draw(imgChoice, 850, 0, 400, 50);
                      //  System.out.println(cursor);
                        String[] par = partsEmily[1].split(":");
                        String str = "";
                        for (int i = 0; i < par.length; i++) {
                            str += par[i] + " ";
                        }
                        String[] par1 = partsEmily[0].split(":");
                        String str1 = "";
                        for (int i = 0; i < par1.length; i++) {
                            str1 += par1[i] + " ";
                        }
                        fontMessageChoice.draw(batch, str1, 863, 105);
                        fontMessageChoice.draw(batch, str, 863, 31);
                    }
                    if (choice1) {
                        batch.draw(imgInsertEmily, insertObjectEmily.x, insertObjectEmily.y, insertObjectEmily.width, insertObjectEmily.height);
                        String[] par = partsEmily[0].split(":");
                        fontMessageBig.draw(batch, par[0] + "\n" + par[1], 1100, 600);
                        if (count < 1) {
                            main.AttentionLara += Integer.parseInt(partsEmily[2]);
                            main.AttentionAgata += Integer.parseInt(partsEmily[2]);
                            count++;
                        }

                    }
                    if (choice2) {
                        batch.draw(imgInsertEmily, insertObjectEmily.x, insertObjectEmily.y, insertObjectEmily.width, insertObjectEmily.height);
                        String[] par = partsEmily[1].split(":");
                        fontMessageBig.draw(batch, par[0] + "\n" + par[1], 1100, 580);

                        if (count < 1) {
                            main.AttentionLara += Integer.parseInt(partsEmily[3]);
                            main.AttentionAgata += Integer.parseInt(partsEmily[3]);
                            count++;
                        }


                    }

                }
                if (talkAgata && !show) {
                    partsAgata = messageAgata.get(cursor).split("/");
                    batch.draw(imgInsertAgata, insertObjectAgata.x, insertObjectAgata.y, insertObjectAgata.width, insertObjectAgata.height);
                    if (choice1) {
                        String[] par = partsAgata[0].split(":");
                        fontMessageBig.draw(batch, par[0] + "\n" + par[1], 630, 600);
                    }
                    if (choice2) {
                        String[] par = partsAgata[1].split(":");
                        fontMessageBig.draw(batch, par[0] + "\n" + par[1], 630, 600);
                    }
                }
                if (talkLara && !show) {
                    partsLara = messageLara.get(cursor).split("/");
                    batch.draw(imgInsertLara, insertObjectLara.x, insertObjectLara.y, insertObjectLara.width, insertObjectLara.height);
                    if (choice1) {
                        String[] par = partsLara[0].split(":");
                        fontMessageBig.draw(batch, par[0] + "\n" + par[1], 654, 600);


                    }
                    if (choice2) {
                        String[] par = partsLara[1].split(":");
                        fontMessageBig.draw(batch, par[0] + "\n" + par[1], 654, 600);

                    }
                }
                if (show) {
                    if (showPaper) {
                        batch.draw(imgPaper1, cluesObject1.x, cluesObject1.y, cluesObject1.width, cluesObject1.height);
                        cluesObject1.move();
                        if (cluesObject1.width >= 400) {
                            btnTakeIt1.font.draw(batch, btnTakeIt1.text, btnTakeIt1.x, btnTakeIt1.y);
                        }
                    }
                    if (showCard){
                        batch.draw(imgCard,cluesObject2.x, cluesObject2.y, cluesObject2.width, cluesObject2.height);
                        cluesObject2.move();
                        if (cluesObject1.width >= 400) {
                            btnTakeIt2.font.draw(batch, btnTakeIt2.text, btnTakeIt2.x, btnTakeIt2.y);
                        }
                    }
                }
            }
        }

            fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);
            btnPhone.font.draw(batch, btnPhone.text, btnPhone.x, btnPhone.y);

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
        imgPaper1.dispose();
    }
}
