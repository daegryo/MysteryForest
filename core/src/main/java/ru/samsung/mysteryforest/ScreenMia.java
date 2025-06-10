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
    public BitmapFont fontMessageChoice;
    public BitmapFont fontMessageBig;
    public BitmapFont fontYellow;

    Texture imgBg;
    Texture imgBgBlur;
    Texture imgPixel;

    Texture imgEmily;
    Texture imgLara;
    Texture imgAgata;
    Texture imgMia;
    Texture imgAuthor;

    Texture imgBackpack;
    Texture imgPaper;
    Texture imgRibbon;

    Texture imgInsertEmily;
    Texture imgInsertAgata;
    Texture imgInsertLara;
    Texture imgInsertMia;
    Texture imgChoice;

    Insert insertObjectEmily = new Insert(1150, 400, 400, 400);
    Insert insertObjectLara = new Insert(960, 400, 480, 480);
    Insert insertObjectAgata = new Insert(920, 400, 400, 400);
    Insert insertObjectMia = new Insert(410, 300, 400, 400);

    SpaceButton btnPhone;
    SpaceButton btnCard;
    SpaceButton btnSettings;
    SpaceButton btnTakeIt;
    SpaceButton btnTakeIt1;

    private float alpha = 0.1f;
    private float speed = 0.7f;
    ShapeRenderer shapeRenderer;

    boolean next = false;

    boolean talkEmily = false;
    boolean talkAgata = false;
    boolean talkLara = false;
    boolean talkMia = false;
    boolean talkAuthor = true;

    boolean choice1 = false;
    boolean choice2 = false;

    boolean noChoice = true;

    boolean showMia = false;

    boolean showPaper = false;

    // Переменные для анимации затемнения
    private float fadeAlpha = 0f;
    private boolean isFadingOut = false;
    private boolean isFadingIn = false;
    private float fadeSpeed = 2f;

    ReadFile readFileLaraNoChoice;
    ReadFile readFileEmilyNoChoice;
    ReadFile readFileAgataNoChoice;
    ReadFile readFileMiaNoChoice;
    ReadFile readFileAuthor;

    ReadFile readFileLara;
    ReadFile readFileEmily;
    ReadFile readFileAgata;
    ReadFile readFileMia;

    List<String> messageLaraNoChoice;
    List<String> messageEmilyNoChoice;
    List<String> messageAgataNoChoice;
    List<String> messageMiaNoChoice;
    List<String> messageAuthor;

    List<String> messageLara;
    List<String> messageEmily;
    List<String> messageAgata;
    List<String> messageMia;

    String medium = "";

    String[] partsEmily;
    String[] partsAgata;
    String[] partsLara;
    String[] partsMia;

    int cursor = 0;
    int cursorChoice = 0;
    int count = 0;

    Clues cluesObject5 = new Clues(300, 300, 400, 400, "text/paper5.png");
    Clues cluesObject6 = new Clues(300, 300, 400, 400, "device/ribbon.png");

    public ScreenMia(Main main) {
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

        imgBg = new Texture("bg/Mia.png");
        imgBgBlur = new Texture("bg/blurMia.png");
        imgPixel = new Texture("text/author.png"); // Создайте 1x1 пиксельную текстуру

        imgEmily = new Texture("heros/Emily/EmilyFullLength.png");
        imgLara = new Texture("heros/Lara/LaraFullLength.png");
        imgAgata = new Texture("heros/Agata/FullLength1.png");
        imgMia = new Texture("heros/Mia/MiaFullLength.png");
        imgAuthor = new Texture("text/author.png");

        imgInsertEmily = new Texture("text/insert.png");
        imgInsertAgata = new Texture("text/insert.png");
        imgInsertLara = new Texture("text/insert1.png");
        imgInsertMia = new Texture("text/insert1.png");
        imgChoice = new Texture("text/choice.png");
        imgBackpack = new Texture("icons/backpack.png");
        imgPaper = new Texture("text/paper5.png");
        imgRibbon = new Texture("device/ribbon.png");

        btnPhone = new SpaceButton(fontPodarok, 1500, 700, "phone");
        btnCard = new SpaceButton(fontPodarok, 1500, 670, "card");
        btnSettings = new SpaceButton(fontPodarok, 1500, 50, "settings");
        btnTakeIt = new SpaceButton(fontPodarok, 460, 271, "Взять");
        btnTakeIt1 = new SpaceButton(fontPodarok, 460, 271, "Взять");

        readFileLaraNoChoice = new ReadFile("assets/story/screenMia/noChoiceLara.txt", 1);
        readFileEmilyNoChoice = new ReadFile("assets/story/screenMia/noChoiceEmily.txt", 1);
        readFileAgataNoChoice = new ReadFile("assets/story/screenMia/noChoiceAgata.txt", 1);
        readFileMiaNoChoice = new ReadFile("assets/story/screenMia/noChoiceMia.txt", 1);

        readFileLara = new ReadFile("assets/story/screenMia/Lara.txt", 1);
        readFileEmily = new ReadFile("assets/story/screenMia/Emily.txt", 1);
        readFileAgata = new ReadFile("assets/story/screenMia/Agata.txt", 1);
        readFileMia = new ReadFile("assets/story/screenMia/Mia.txt", 1);

        readFileAuthor = new ReadFile("assets/story/screenMia/author.txt", 4);

        messageLaraNoChoice = readFileLaraNoChoice.reader();
        messageEmilyNoChoice = readFileEmilyNoChoice.reader();
        messageAgataNoChoice = readFileAgataNoChoice.reader();
        messageMiaNoChoice = readFileMiaNoChoice.reader();

        messageLara = readFileLara.reader();
        messageEmily = readFileEmily.reader();
        messageAgata = readFileAgata.reader();
        messageMia = readFileMia.reader();

        messageAuthor = readFileAuthor.reader();

        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        // Обработка касаний
        if (Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            System.out.println(touch.x + " " + touch.y);

            if (btnSettings.hit(touch.x, touch.y)) {
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenSettings.back = "ScreenMia";
                main.setScreen(main.screenSettings);
            }

            if (btnPhone.hit(touch.x, touch.y)) {
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenPhone.clas = "ScreenMia";
                main.setScreen(main.screenPhone);
            }

            if (btnCard.hit(touch.x, touch.y)) {
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenCard.back = "ScreenMia";
                main.setScreen(main.screenCard);
            }
            if (btnTakeIt.hit(touch.x, touch.y)){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                showPaper = false;
                main.screenHomeSearch.backPack.add(cluesObject5);

            }
            if (btnTakeIt1.hit(touch.x, touch.y) && cursorChoice > 0 && !showMia ){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.dbHelper.updateInformation(main.Id);
                next = true;
                main.screenHomeSearch.backPack.add(cluesObject6);

            }

            if (noChoice) {

                if (touch.x >= 254 && touch.x <= 339 && touch.y >= 15 && touch.y <= 100) {
                    if (main.screenSettings.On) {
                        main.screenStart.soundClick.play();
                    }
                    main.setScreen(main.screenBackPack);
                    main.screenBackPack.clas = "ScreenMia";
                }

                if (touch.x >= 302 && touch.x <= 1299 && touch.y >= 212 && touch.y <= 703 && talkAuthor) {
                    talkAuthor = false;
                    talkAgata = true;
                    if (cursor == 1){
                        showPaper = true;
                    }
                    if (main.screenSettings.On) {
                        main.screenStart.soundClick.play();
                    }
                }

                if (touch.x >= 1158 && touch.x <= 1268 && touch.y >= 0 && touch.y <= 502 && talkAgata) {
                    if (main.screenSettings.On) {
                        main.screenStart.soundClick.play();
                    }

                    if (cursor == 0) {
                        talkAgata = false;
                        talkLara = true;
                    }
                    if (cursor == 1) {
                        fadeSpeed = 1f;
                        if (!isFadingOut) {
                            isFadingOut = true;
                            fadeAlpha = 0f;
                        }
                    }
                }

                if (touch.x >= 969 && touch.x <= 1079 && touch.y >= 0 && touch.y <= 504 && talkLara) {
                    if (main.screenSettings.On) {
                        main.screenStart.soundClick.play();
                    }
                    talkLara = false;
                    talkEmily = true;
                }

                if (touch.x >= 1395 && touch.x <= 1503 && touch.y >= 0 && touch.y <= 510 && talkEmily) {
                    if (!isFadingOut) {
                        isFadingOut = true;
                        fadeAlpha = 0f;
                    }
                    if (main.screenSettings.On) {
                        main.screenStart.soundClick.play();
                    }
                }

            }
            if (!noChoice) {

                if (touch.x >= 254 && touch.x <= 339 && touch.y >= 15 && touch.y <= 100) {
                    if (main.screenSettings.On) {
                        main.screenStart.soundClick.play();
                    }
                    main.setScreen(main.screenBackPack);
                    main.screenBackPack.clas = "ScreenMia";
                }
                if (touch.x >= 394 && touch.x <= 496 && touch.y >= 0 && touch.y <= 472 && talkMia) {
                    if (cursorChoice == 0) {
                        talkMia = false;
                        talkEmily = true;
                    }
                    else{
                        if (!isFadingOut) {
                            isFadingOut = true;
                            fadeAlpha = 0f;
                        }
                    }
                    if (main.screenSettings.On) {
                        main.screenStart.soundClick.play();
                    }

                }

                if (touch.x >= 1158 && touch.x <= 1268 && touch.y >= 0 && touch.y <= 502 && talkAgata) {
                    if (main.screenSettings.On) {
                        main.screenStart.soundClick.play();
                    }
                    talkAgata = false;
                    talkLara = true;
                }

                if (touch.x >= 969 && touch.x <= 1079 && touch.y >= 0 && touch.y <= 504 && talkLara) {
                    if (main.screenSettings.On) {
                        main.screenStart.soundClick.play();
                    }
                    talkLara = false;
                    talkMia = true;
                    cursorChoice++;
                }

                if (touch.x >= 1395 && touch.x <= 1503 && touch.y >= 0 && touch.y <= 510 && talkEmily) {
                    talkEmily = false;
                    talkAgata = true;
                    if (main.screenSettings.On) {
                        main.screenStart.soundClick.play();
                    }
                }



                if (touch.x >= 854 && touch.x <= 1244 && touch.y >= 63 && touch.y <= 104 && !noChoice) {
                    choice1 = true;
                    if (main.screenSettings.On) {
                        main.screenStart.soundClick.play();
                    }
                }

                if (touch.x >= 854 && touch.x <= 1244 && touch.y >= 0 && touch.y <= 51 && !noChoice) {
                    choice2 = true;
                    if (main.screenSettings.On) {
                        main.screenStart.soundClick.play();
                    }
                }
            }

        }

        // События
        main.Station = "screenMia";


        if (isFadingOut) {
            fadeAlpha += fadeSpeed * Gdx.graphics.getDeltaTime();

            if (fadeAlpha >= 1f) {
                fadeAlpha = 1f;
                isFadingOut = false;
                isFadingIn = true;
                if (talkEmily) {
                    talkEmily = false;
                    talkAuthor = true;
                    cursor++;

                }
                if (talkAgata && cursor == 1){
                    talkAgata = false;
                    talkMia = true;
                    showMia = true;

                }
                if (cursorChoice == 1 && showMia){
                    talkMia = false;
                    showMia = false;
                }

            }
        }

        if (isFadingIn) {
            fadeAlpha -= fadeSpeed * Gdx.graphics.getDeltaTime();
            if (fadeAlpha <= 0f) {
                fadeAlpha = 0f;
                isFadingIn = false;
            }
        }

        // Отрисовка
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        if (!talkAuthor) {
            batch.draw(imgBg, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            batch.draw(imgEmily, 1342, 0, 250, 512);
            batch.draw(imgLara, 939, 0, 200, 512);
            batch.draw(imgAgata, 1159, 0, 150, 512);

            fontPodarok.draw(batch, "Лара", 915, 460);
            fontPodarok.draw(batch, "Агата", 1109, 460);
            fontPodarok.draw(batch, "Эмили", 1334, 460);

            if (cursor > 0 && showMia){
                batch.draw(imgMia, 381, 0, 150, 470);
                fontPodarok.draw(batch, "Мия", 350, 430);
            }
            if (cursorChoice > 0 && !showMia){
                batch.draw(imgRibbon, 300, 300, 400, 470);
                btnTakeIt1.font.draw(batch, btnTakeIt1.text, btnTakeIt1.x, btnTakeIt1.y);
            }
        } else {
            batch.draw(imgBgBlur, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
        }

        if (noChoice) {
            if (talkAuthor){
                batch.draw(imgAuthor, 300, 200, 1000, 500);
                medium = messageAuthor.get(cursor);
                fontYellow.draw(batch, medium, 332, 600);
            }
            if (talkAgata && !showPaper){
                medium = messageAgataNoChoice.get(cursor);
                batch.draw(imgInsertAgata, insertObjectAgata.x, insertObjectAgata.y, insertObjectAgata.width, insertObjectAgata.height);
                String[] par = medium.split(":");
                fontMessageBig.draw(batch, par[0] + "\n" + par[1] + "\n" + par[2], 957, 700);
            }
            if (talkLara){
                medium = messageLaraNoChoice.get(cursor);
                batch.draw(imgInsertLara, insertObjectLara.x, insertObjectLara.y, insertObjectLara.width, insertObjectLara.height);
                String[] par = medium.split(":");
                fontMessageBig.draw(batch, par[0] + "\n" + par[1] + "\n" + par[2]+ "\n" + par[3], 1050, 760);
            }
            if (talkEmily){
                medium = messageEmilyNoChoice.get(cursor);
                batch.draw(imgInsertEmily, insertObjectEmily.x, insertObjectEmily.y, insertObjectEmily.width, insertObjectEmily.height);
                String[] par = medium.split(":");
                fontMessageBig.draw(batch, par[0] + "\n" + par[1] + "\n" + par[2], 1200, 710);
            }
            if (talkMia){
                medium = messageMiaNoChoice.get(cursor-1);
                batch.draw(imgInsertMia, insertObjectMia.x, insertObjectMia.y, insertObjectMia.width, insertObjectMia.height);
                String[] par = medium.split(":");
                fontMessageBig.draw(batch, par[0] + "\n" + par[1], 500, 580);
                noChoice = false;
            }
            if (showPaper){
                batch.draw(imgPaper, 300, 300, 400, 400);
                btnTakeIt.font.draw(batch,btnTakeIt.text, btnTakeIt.x, btnTakeIt.y);
            }
        }
        if (!noChoice){
            if (talkEmily) {
                if (cursorChoice == 0) {
                    partsEmily = messageEmily.get(cursorChoice).split("/");
                    if (!choice1 && !choice2) {
                        batch.draw(imgChoice, 850, 51, 400, 50);
                        batch.draw(imgChoice, 850, 0, 400, 50);
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
                        fontMessageBig.draw(batch, par[0] + "\n" + par[1], 1200, 710);
                        if (count < 1) {
                            main.AttentionLara += Integer.parseInt(partsEmily[2]);
                            main.AttentionAgata += Integer.parseInt(partsEmily[2]);
                            count++;
                        }

                    }
                    if (choice2) {
                        batch.draw(imgInsertEmily, insertObjectEmily.x, insertObjectEmily.y, insertObjectEmily.width, insertObjectEmily.height);
                        String[] par = partsEmily[1].split(":");
                        fontMessageBig.draw(batch, par[0] + "\n" + par[1], 1200, 710);

                        if (count < 1) {
                            main.AttentionLara += Integer.parseInt(partsEmily[3]);
                            main.AttentionAgata += Integer.parseInt(partsEmily[3]);
                            count++;
                        }


                    }
                }

            }
            if (talkAgata) {
                if (cursorChoice == 0) {
                    partsAgata = messageAgata.get(cursorChoice).split("/");
                    batch.draw(imgInsertAgata, insertObjectAgata.x, insertObjectAgata.y, insertObjectAgata.width, insertObjectAgata.height);
                    if (choice1) {
                        String[] par = partsAgata[0].split(":");
                        fontMessageBig.draw(batch, par[0] + "\n" + par[1], 957, 700);
                    }
                    if (choice2) {
                        String[] par = partsAgata[1].split(":");
                        fontMessageBig.draw(batch, par[0] + "\n" + par[1], 957, 700);
                    }
                }
            }
            if (talkLara) {
                if (cursorChoice == 0) {
                    partsLara = messageLara.get(cursorChoice).split("/");
                    batch.draw(imgInsertLara, insertObjectLara.x, insertObjectLara.y, insertObjectLara.width, insertObjectLara.height);
                    if (choice1) {
                        String[] par = partsLara[0].split(":");
                        fontMessageBig.draw(batch, par[0] + "\n" + par[1], 1050, 760);
                    }
                    if (choice2) {
                        String[] par = partsLara[1].split(":");
                        fontMessageBig.draw(batch, par[0] + "\n" + par[1], 1050, 760);

                    }
                }
            }
            if (talkMia) {
                batch.draw(imgInsertMia, insertObjectMia.x, insertObjectMia.y, insertObjectMia.width, insertObjectMia.height);
                if (cursorChoice > 1) {
                    partsMia = messageMia.get(cursorChoice).split("/");
                    if (choice1) {

                        String[] par = partsMia[0].split(":");
                        fontMessageBig.draw(batch, par[0] + "\n" + par[1], 500, 580);
                    }
                    if (choice2) {
                        String[] par = partsMia[1].split(":");
                        fontMessageBig.draw(batch, par[0] + "\n" + par[1], 500, 580);

                    }
                }
                else{
                    partsMia = messageMia.get(cursorChoice).split("/");
                    String[] par = partsMia[0].split(":");
                    fontMessageBig.draw(batch, par[0] + "\n" + par[1], 500, 580);
                }
            }
        }

        // Отрисовка затемнения
        if (isFadingOut || isFadingIn) {
            batch.setColor(0, 0, 0, fadeAlpha);
            batch.draw(imgPixel, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            batch.setColor(1, 1, 1, 1);
        }

        fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);
        btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);
        btnPhone.font.draw(batch, btnPhone.text, btnPhone.x, btnPhone.y);
        btnCard.font.draw(batch, btnCard.text, btnCard.x, btnCard.y);
        batch.draw(imgBackpack, 234, 15, 120, 90);

        if (next) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(0, 0, 0, alpha);
            shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            shapeRenderer.end();

            alpha += delta * 0.6f;
            if (alpha >= 1) {
                main.setScreen(main.screenStore);
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
        batch.dispose();
        font.dispose();
        fontScroll.dispose();
        fontPodarok.dispose();
        fontMessageChoice.dispose();
        fontMessageBig.dispose();
        imgBg.dispose();
        imgBgBlur.dispose();
        imgPixel.dispose();
        imgEmily.dispose();
        imgLara.dispose();
        imgAgata.dispose();
        imgMia.dispose();
        imgAuthor.dispose();
        imgBackpack.dispose();
        imgPaper.dispose();
        imgRibbon.dispose();
        imgInsertEmily.dispose();
        imgInsertAgata.dispose();
        imgInsertLara.dispose();
        imgInsertMia.dispose();
        imgChoice.dispose();
    }
}
