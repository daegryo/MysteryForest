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
    Texture imgStoreInAmulet;
    Texture imgStoreInChips;
    Texture imgStoreInKnight;
    Texture imgStoreInLight;
    Texture imgStoreInPharmacy;
    Texture imgBackpack;

    Texture imgJohn;
    Texture imgInsertJohn;

    boolean talkAuthor = true;

    boolean talkJohn = false;

    boolean drawButton = false;
    boolean talking = false;

    Insert insertObjectJohn = new Insert(780, 350, 400, 400);


    ReadFile readFileAuthor;
    List<String> messageAuthor;

    SpaceButton btnPhone;
    SpaceButton btnCard;
    SpaceButton btnSettings;

    SpaceButton btnOpen;

    SpaceButton btnBuyStairs;
    SpaceButton btnBuyAmulet;
    SpaceButton btnBuyChips;
    SpaceButton btnBuyKnight;
    SpaceButton btnBuyLight;
    SpaceButton btnBuyPharmacy;
    SpaceButton btnCloseStore;




    int cursor = 0;
    int cursorAuthor = 0;

    String medium = "";

    Vector3 objectPositionStore = new Vector3(92, 141, 0);
    Vector3 objectSizeStore = new Vector3(1062, 651, 0);

    Vector3 objectPositionStairs = new Vector3(512, 136, 0);
    Vector3 objectSizeStairs = new Vector3(145, 500, 0);

    Vector3 objectPositionAmulet = new Vector3(1216, 138, 0);
    Vector3 objectSizeAmulet = new Vector3(164, 24, 0);

    Vector3 objectPositionChips = new Vector3(1309, 193, 0);
    Vector3 objectSizeChips = new Vector3(183, 98, 0);

    Vector3 objectPositionKnight = new Vector3(951, 52, 0);
    Vector3 objectSizeKnight = new Vector3(252, 43, 0);

    Vector3 objectPositionLight = new Vector3(880, 152, 0);
    Vector3 objectSizeLight = new Vector3(120, 96, 0);

    Vector3 objectPositionPharmacy = new Vector3(288, 115, 0);
    Vector3 objectSizePharmacy = new Vector3(113, 98, 0);

    int IMAGE = 0;
    int black = 0;
    int blur = 1;
    int store = 2;
    int storeIn = 3;

    boolean isHoveringStairs = false;
    boolean isHoveringAmulet = false;
    boolean isHoveringChips = false;
    boolean isHoveringKnight = false;
    boolean isHoveringLight = false;
    boolean isHoveringPharmacy = false;

    private float hoverAlpha = 0f;
    private float hoverAlpha1 = 0f;
    private float hoverAlpha2 = 0f;
    private float hoverAlpha3 = 0f;
    private float hoverAlpha4 = 0f;
    private float hoverAlpha5 = 0f;

    private final float HOVER_SPEED = 4f;

    Clues cluesObjectStairs = new Clues(867, 453, 0.01f, 0.01f, "text/devices/stairs.png");
    Clues cluesObjectAmulet = new Clues(867, 453, 0.01f, 0.01f, "text/devices/amulet.png");
    Clues cluesObjectChips = new Clues(867, 453, 0.01f, 0.01f, "text/devices/chips.png");
    Clues cluesObjectKnight = new Clues(867, 453, 0.01f, 0.01f, "text/devices/knight.png");
    Clues cluesObjectPharmacy = new Clues(867, 453, 0.01f, 0.01f, "text/devices/pharmacy.png");
    Clues cluesObjectLight = new Clues(867, 453, 0.01f, 0.01f, "text/devices/lamp.png");

    boolean buyStairs = true;
    boolean buyAmulet = true;
    boolean buyChips = true;
    boolean buyLight = true;
    boolean buyKnight = true;
    boolean buyPharmacy = true;

    boolean closeStore = false;




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

        imgStoreInAmulet = new Texture("bg/store/storeInAmulet.png");
        imgStoreInChips = new Texture("bg/store/storeInChips.png");
        imgStoreInKnight = new Texture("bg/store/storeInKnight.png");
        imgStoreInLight = new Texture("bg/store/storeInLight.png");
        imgStoreInPharmacy = new Texture("bg/store/storeInPharmacy.png");
        imgStoreInStairs = new Texture("bg/store/storeInStairs.png");

        imgBackpack = new Texture("icons/backpack.png");

        imgJohn = new Texture("heros/John/John.png");

        imgInsertJohn = new Texture("text/insert1.png");

        btnPhone = new SpaceButton(fontPodarok, 1400, 700, "phone");
        btnCard = new SpaceButton(fontPodarok, 1400, 670, "card");
        btnSettings = new SpaceButton(fontPodarok, 1500, 50, "settings");

        btnOpen = new SpaceButton(fontPodarok, 572, 398, "войти в магазин");
        btnCloseStore = new SpaceButton(fontPodarok, 1350, 559, "Выйти из магазина");

        btnBuyStairs = new SpaceButton(fontPodarok, 604, 414, "Купить");
        btnBuyAmulet = new SpaceButton(fontPodarok, 1290, 160, "Купить");
        btnBuyChips = new SpaceButton(fontPodarok, 1407, 246, "Купить");
        btnBuyKnight = new SpaceButton(fontPodarok, 1108, 79, "Купить");
        btnBuyLight = new SpaceButton(fontPodarok, 931, 216, "Купить");
        btnBuyPharmacy = new SpaceButton(fontPodarok, 356, 160, "Купить");

        readFileAuthor = new ReadFile("assets/story/screenStore/author.txt", 3);
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
            mousePos.y <= objectPositionStore.y + objectSizeStore.y && IMAGE == store && !talkJohn && !talking && !closeStore) {
            drawButton = true;
        }
        else {
            drawButton = false;
        }
        boolean isCurrentlyHoveringStairs = mousePos.x >= objectPositionStairs.x &&
            mousePos.x <= objectPositionStairs.x + objectSizeStairs.x &&
            mousePos.y >= objectPositionStairs.y &&
            mousePos.y <= objectPositionStairs.y + objectSizeStairs.y && IMAGE == storeIn;
        boolean isCurrentlyHoveringAmulet = mousePos.x >= objectPositionAmulet.x &&
            mousePos.x <= objectPositionAmulet.x + objectSizeAmulet.x &&
            mousePos.y >= objectPositionAmulet.y &&
            mousePos.y <= objectPositionAmulet.y + objectSizeAmulet.y && IMAGE == storeIn ;
        boolean isCurrentlyHoveringChips = mousePos.x >= objectPositionChips.x &&
            mousePos.x <= objectPositionChips.x + objectSizeChips.x &&
            mousePos.y >= objectPositionChips.y &&
            mousePos.y <= objectPositionChips.y + objectSizeChips.y && IMAGE == storeIn;
        boolean isCurrentlyHoveringKnigt = mousePos.x >= objectPositionKnight.x &&
            mousePos.x <= objectPositionKnight.x + objectSizeKnight.x &&
            mousePos.y >= objectPositionKnight.y &&
            mousePos.y <= objectPositionKnight.y + objectSizeKnight.y && IMAGE == storeIn;
        boolean isCurrentlyHoveringLight = mousePos.x >= objectPositionLight.x &&
            mousePos.x <= objectPositionLight.x + objectSizeLight.x &&
            mousePos.y >= objectPositionLight.y &&
            mousePos.y <= objectPositionLight.y + objectSizeLight.y && IMAGE == storeIn;
        boolean isCurrentlyHoveringPharmacy = mousePos.x >= objectPositionPharmacy.x &&
            mousePos.x <= objectPositionPharmacy.x + objectSizePharmacy.x &&
            mousePos.y >= objectPositionPharmacy.y &&
            mousePos.y <= objectPositionPharmacy.y + objectSizePharmacy.y && IMAGE == storeIn;

        if (isCurrentlyHoveringStairs != isHoveringStairs) {
            isHoveringStairs = isCurrentlyHoveringStairs;
        }
        if (isCurrentlyHoveringAmulet != isHoveringAmulet) {
            isHoveringAmulet = isCurrentlyHoveringAmulet;
        }
        if (isCurrentlyHoveringChips != isHoveringChips) {
            isHoveringChips = isCurrentlyHoveringChips;
        }
        if (isCurrentlyHoveringKnigt != isHoveringKnight) {
            isHoveringKnight = isCurrentlyHoveringKnigt;
        }
        if (isCurrentlyHoveringLight != isHoveringLight) {
            isHoveringLight = isCurrentlyHoveringLight;
        }
        if (isCurrentlyHoveringPharmacy != isHoveringPharmacy) {
            isHoveringPharmacy = isCurrentlyHoveringPharmacy;
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
                if (closeStore){
                    main.dbHelper.updateInformation(main.Id);
                    main.setScreen(main.screenEnd);

                }
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
            if (btnBuyStairs.hit(touch.x, touch.y)){
                if (buyStairs) {
                    main.screenHomeSearch.backPack.addHelpers(cluesObjectStairs);
                    if (main.Bank - 10 >= 0) {
                        if (main.screenSettings.On) {
                            main.screenStart.soundBuy.play();
                        }
                        main.Bank -= 10;
                        buyStairs = false;
                    }

                }
            }
            if (btnBuyAmulet.hit(touch.x, touch.y)){
                if (buyAmulet) {

                    if (main.Bank - 30 >= 0) {
                        if (main.screenSettings.On) {
                            main.screenStart.soundBuy.play();
                        }
                        main.Bank -= 30;
                        buyAmulet = false;
                        main.screenHomeSearch.backPack.addHelpers(cluesObjectAmulet);
                    }

                }
            }

            if (btnBuyChips.hit(touch.x, touch.y)){
                if (buyChips) {

                    if (main.Bank - 30 >= 0) {
                        if (main.screenSettings.On) {
                            main.screenStart.soundBuy.play();
                        }
                        main.Bank -= 30;
                        buyChips = false;
                        main.screenHomeSearch.backPack.addHelpers(cluesObjectChips);
                    }

                }
            }
            if (btnBuyKnight.hit(touch.x, touch.y)){
                if (buyKnight) {

                    if (main.Bank - 40 >= 0) {
                        if (main.screenSettings.On) {
                            main.screenStart.soundBuy.play();
                        }
                        main.Bank -= 40;
                        buyKnight = false;
                        main.screenHomeSearch.backPack.addHelpers(cluesObjectKnight);
                    }

                }
            }
            if (btnBuyPharmacy.hit(touch.x, touch.y)){
                if (buyPharmacy) {

                    if (main.Bank - 30 >= 0) {
                        if (main.screenSettings.On) {
                            main.screenStart.soundBuy.play();
                        }
                        main.Bank -= 30;
                        buyPharmacy = false;
                        main.screenHomeSearch.backPack.addHelpers(cluesObjectPharmacy);
                    }

                }
            }
            if (btnBuyLight.hit(touch.x, touch.y)){
                if (buyLight) {

                    if (main.Bank - 60 >= 0) {
                        if (main.screenSettings.On) {
                            main.screenStart.soundBuy.play();
                        }
                        main.Bank -= 60;
                        buyLight = false;
                        main.screenHomeSearch.backPack.addHelpers(cluesObjectLight);
                    }

                }
            }
            if (btnCloseStore.hit(touch.x, touch.y)){
                IMAGE = store;
                closeStore = true;
                talkJohn = true;
            }

        }
        // events
        if (isCurrentlyHoveringStairs) {
            hoverAlpha = Math.min(1f, hoverAlpha + delta * HOVER_SPEED);
        } else {
            hoverAlpha = Math.max(0f, hoverAlpha - delta * HOVER_SPEED);
        }
        if (isCurrentlyHoveringAmulet) {
            hoverAlpha1 = Math.min(1f, hoverAlpha1 + delta * HOVER_SPEED);
        } else {
            hoverAlpha1 = Math.max(0f, hoverAlpha1 - delta * HOVER_SPEED);
        }
        if (isCurrentlyHoveringChips) {
            hoverAlpha2 = Math.min(1f, hoverAlpha2 + delta * HOVER_SPEED);
        } else {
            hoverAlpha2 = Math.max(0f, hoverAlpha2 - delta * HOVER_SPEED);
        }
        if (isCurrentlyHoveringKnigt) {
            hoverAlpha3 = Math.min(1f, hoverAlpha3 + delta * HOVER_SPEED);
        } else {
            hoverAlpha3 = Math.max(0f, hoverAlpha3 - delta * HOVER_SPEED);
        }
        if (isCurrentlyHoveringLight) {
            hoverAlpha4 = Math.min(1f, hoverAlpha4 + delta * HOVER_SPEED);
        } else {
            hoverAlpha4 = Math.max(0f, hoverAlpha4 - delta * HOVER_SPEED);
        }
        if (isCurrentlyHoveringPharmacy) {
            hoverAlpha5 = Math.min(1f, hoverAlpha5 + delta * HOVER_SPEED);
        } else {
            hoverAlpha5 = Math.max(0f, hoverAlpha5 - delta * HOVER_SPEED);
        }

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
                if (!talking && !closeStore){
                    fontMessageBig.draw(batch, "Добро пожаловать \nв магазин Джона! \n    Заходите", 870, 600);
                }
                if (closeStore){
                    fontMessageBig.draw(batch, "Пусть удача \nвсегда будет с вами", 870, 600);
                }

            }
        }
        if (IMAGE == storeIn){
            if (isCurrentlyHoveringStairs && buyStairs){
                if (hoverAlpha > 0.01f) {
                    batch.setColor(1, 1, 1, hoverAlpha);
                    batch.draw(imgStoreInStairs, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                    if (buyStairs) {
                        btnBuyStairs.font.draw(batch, btnBuyStairs.text, btnBuyStairs.x, btnBuyStairs.y);
                        fontPodarok.draw(batch, "Цена 10", 604, 370);
                    }
                    batch.setColor(1, 1, 1, 1);
                }
            }
            else if (isCurrentlyHoveringAmulet && buyAmulet){
                if (hoverAlpha1 > 0.01f) {
                    batch.setColor(1, 1, 1, hoverAlpha1);
                    batch.draw(imgStoreInAmulet, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                    if (buyAmulet) {
                        btnBuyAmulet.font.draw(batch, btnBuyAmulet.text, btnBuyAmulet.x, btnBuyAmulet.y);
                        fontPodarok.draw(batch, "Цена 30", 1290, 110);
                    }
                    batch.setColor(1, 1, 1, 1);
                }
            }
            else if (isCurrentlyHoveringChips && buyChips){
                if (hoverAlpha2 > 0.01f) {
                    batch.setColor(1, 1, 1, hoverAlpha2);
                    batch.draw(imgStoreInChips, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                    if (buyChips) {
                        btnBuyChips.font.draw(batch, btnBuyChips.text, btnBuyChips.x, btnBuyChips.y);
                        fontPodarok.draw(batch, "Цена 30", 1407, 206);
                    }
                    batch.setColor(1, 1, 1, 1);
                }
            }
            else if (isCurrentlyHoveringKnigt && buyKnight){
                if (hoverAlpha3 > 0.01f) {
                    batch.setColor(1, 1, 1, hoverAlpha3);
                    batch.draw(imgStoreInKnight, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                    if (buyKnight) {
                        btnBuyKnight.font.draw(batch, btnBuyKnight.text, btnBuyKnight.x, btnBuyKnight.y);
                        fontPodarok.draw(batch, "Цена 40", 1106, 120);
                    }
                    batch.setColor(1, 1, 1, 1);
                }
            }
            else if (isCurrentlyHoveringLight && buyLight){
                if (hoverAlpha4 > 0.01f) {
                    batch.setColor(1, 1, 1, hoverAlpha4);
                    batch.draw(imgStoreInLight, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                    if (buyLight) {
                        btnBuyLight.font.draw(batch, btnBuyLight.text, btnBuyLight.x, btnBuyLight.y);
                        fontPodarok.draw(batch, "Цена 60", 931, 180);
                    }
                    batch.setColor(1, 1, 1, 1);
                }
            }
            else if (isCurrentlyHoveringPharmacy && buyPharmacy){
                if (hoverAlpha5 > 0.01f) {
                    batch.setColor(1, 1, 1, hoverAlpha5);
                    batch.draw(imgStoreInPharmacy, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                    if (buyPharmacy) {
                        btnBuyPharmacy.font.draw(batch, btnBuyPharmacy.text, btnBuyPharmacy.x, btnBuyPharmacy.y);
                        fontPodarok.draw(batch, "Цена 30", 356, 120);
                    }
                    batch.setColor(1, 1, 1, 1);
                }
            }
            else {
                batch.draw(imgStoreIn, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            }
            fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);
            fontPodarok.draw(batch, "Пояснение: ты можешь купить \nдо 5 предметов, которые тебе понадобятся далее.\nПокупай предметы, \nисходя из своего банка", 993, 817);

            btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);
            btnPhone.font.draw(batch, btnPhone.text, btnPhone.x, btnPhone.y);
            btnCard.font.draw(batch, btnCard.text, btnCard.x, btnCard.y);
            btnCloseStore.font.draw(batch, btnCloseStore.text, btnCloseStore.x, btnCloseStore.y);
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
        batch.dispose();
        font.dispose();
        fontScroll.dispose();
        fontPodarok.dispose();
        fontMessageChoice.dispose();
        fontMessageBig.dispose();
        imgBg.dispose();
        imgBgBlur.dispose();
        imgBgBlack.dispose();
        imgStoreIn.dispose();
        imgStoreInStairs.dispose();
        imgStoreInAmulet.dispose();
        imgStoreInChips.dispose();
        imgStoreInKnight.dispose();
        imgStoreInLight.dispose();
        imgStoreInPharmacy.dispose();
        imgBackpack.dispose();
        imgJohn.dispose();
        imgInsertJohn.dispose();


    }
}
