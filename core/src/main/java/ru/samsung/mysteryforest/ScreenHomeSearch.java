package ru.samsung.mysteryforest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Objects;

public class ScreenHomeSearch implements Screen {
    Main main;

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;
    public BitmapFont fontScroll;
    public BitmapFont fontPodarok;
    public BitmapFont fontMessageBig;


    Texture imgHallway;
    Texture imgBedroom;
    Texture imgKitchen;
    Texture imgBathroom;
    Texture imgHome;
    Texture imgEmily;

    Texture imgBedroomCabinet;
    Texture imgKitchenCloset;
    Texture imgBedroomLight;
    Texture imgBedroomEmptyCabinet;
    Texture imgBedroomBed;
    Texture imgKitchenCLoset2;


    Texture imgBedroomBlack;
    Texture imgHallwayBlack;
    Texture imgKitchenBlack;
    Texture imgBathroomBlack;
    Texture imgHomeBlack;



    Texture imgInsert;


    Texture imgPaper3;


    Texture imgBackpack;
    Texture imgPhone;

    SpaceButton btnHallway;
    SpaceButton btnBedroom;
    SpaceButton btnKitchen;
    SpaceButton btnHome;


    SpaceButton btnTakeIt3;
    SpaceButton btnPhone;
    SpaceButton btnNextChapter;
    SpaceButton btnBathroom;
    SpaceButton btnSettings;
    SpaceButton btnTurnLight;
    SpaceButton btnHide;



    int IMAGE;
    int hallway;
    int bedroom;
    int kitchen;
    int home;
    int bathroom;
    int bedroomCabinet;
    int kitchenCloset;
    int bedroomLight;
    int bedroomTurnOfLight;
    int bedroomEmptyCabinet;
    int bedroomBed;
    int kitchenCloset2;

    Insert insertObject1 = new Insert(920, 300, 1f, 1f);
    Insert insertObject2 = new Insert(920, 300, 500, 500);



    Clues cluesObject3 = new Clues(867, 453, 20, 20, "text/paper3.png");

    BackPack backPack = new BackPack();



    boolean cluesMove3 = false;

    boolean black = false;

    boolean next = false;

    Vector3 objectPositionBedroomCabinet = new Vector3(1224, 283, 0);
    Vector3 objectSizeBedroomCabinet = new Vector3(151, 54, 0);

    Vector3 objectPositionBedroomLight = new Vector3(1317, 373, 0);
    Vector3 objectSizeBedroomLight = new Vector3(80, 233, 0);

    Vector3 objectPositionBedroomEmptyCabinet = new Vector3(1228, 143, 0);
    Vector3 objectSizeBedroomEmptyCabinet = new Vector3(144, 119, 0);

    Vector3 objectPositionBedroomBed = new Vector3(172, 104, 0);
    Vector3 objectSizeBedroomBed = new Vector3(990, 422, 0);

    Vector3 objectPositionKitchenCloset = new Vector3(617, 562, 0);
    Vector3 objectSizeKitchenCloset = new Vector3(235, 215, 0);

    Vector3 objectPositionKitchenCloset2 = new Vector3(254, 586, 0);
    Vector3 objectSizeKitchenCloset2 = new Vector3(338, 241, 0);

    ShapeRenderer shapeRenderer;
    float alpha;



    public ScreenHomeSearch(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        fontScroll = new BitmapFont(Gdx.files.internal("fonts/scroll.fnt"));
        fontPodarok = new BitmapFont(Gdx.files.internal("fonts/Podarok.fnt"));
        fontMessageBig = new BitmapFont(Gdx.files.internal("fonts/messageBig.fnt"));

        imgHallway = new Texture("bg/home/hallway.png");
        imgBedroom = new Texture("bg/home/bedroom.png");
        imgKitchen = new Texture("bg/home/kitchen.png");
        imgHome = new Texture("bg/home/home.png");
        imgEmily = new Texture("heros/Emily/EmilyFullLength.png");
        imgBathroom = new Texture("bg/home/bathroom.png");

        imgBedroomCabinet = new Texture("bg/home/bedroom/bedroomCabinet.png");
        imgKitchenCloset = new Texture("bg/home/kitchen/kitchenCloset.png");
        imgBedroomLight = new Texture("bg/home/bedroom/bedroomLight.png");
        imgBedroomEmptyCabinet = new Texture("bg/home/bedroom/bedroomEmptyCabinet.png");
        imgBedroomBed = new Texture("bg/home/bedroom/bedroomBed.png");
        imgKitchenCLoset2 = new Texture("bg/home/kitchen/kitchenCloset2.png");

        imgBedroomBlack = new Texture("bg/home/bedroom/bedroomTurnOffLight.png");
        imgHallwayBlack = new Texture("bg/home/hallway/hallwayBlack.png");
        imgKitchenBlack = new Texture("bg/home/kitchen/kitchenBlack.png");
        imgHomeBlack = new Texture("bg/home/home/homeBlack.png");
        imgBathroomBlack = new Texture("bg/home/bathroom/bathroomBlack.png");

        imgInsert = new Texture("text/insert.png");
        imgPaper3 = new Texture("text/paper3.png");

        imgBackpack = new Texture("icons/backpack.png");
        imgPhone = new Texture("icons/phone.png");

        btnHallway = new SpaceButton(fontPodarok, 100, 810, "Прихожая");
        btnBedroom = new SpaceButton(fontPodarok, 100, 780, "Спальня");
        btnKitchen = new SpaceButton(fontPodarok, 100, 750, "Кухня");
        btnHome = new SpaceButton(fontPodarok, 100, 720, "Дом снаружи");
        btnBathroom = new SpaceButton(fontPodarok, 100, 690, "Ванная");

        btnTakeIt3 = new SpaceButton(fontPodarok, 802,280, "Взять");
        btnPhone = new SpaceButton(fontPodarok, 1400, 700, "phone");
        btnNextChapter = new SpaceButton(font, 740, Main.SCR_HEIGHT/2, "Далее");
        btnSettings = new SpaceButton(fontPodarok, 1500, 50, "settings");
        btnTurnLight = new SpaceButton(fontPodarok, 1310, 450, "выключить");
        btnHide = new SpaceButton(fontPodarok, 697, 311, "спрятаться");


        IMAGE = 0;
        hallway = 1;
        bedroom = 2;
        kitchen = 3;
        home = 4;
        bathroom = 5;

        bedroomCabinet = 6;
        kitchenCloset = 7;
        bedroomLight = 8;
        bedroomEmptyCabinet = 9;
        bedroomBed = 10;
        kitchenCloset2 = 11;

        shapeRenderer = new ShapeRenderer();
        alpha = 0f;



    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // mouse
        Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mousePos);
        if (mousePos.x >= objectPositionBedroomCabinet.x &&
            mousePos.x <= objectPositionBedroomCabinet.x + objectSizeBedroomCabinet.x &&
            mousePos.y >= objectPositionBedroomCabinet.y &&
            mousePos.y <= objectPositionBedroomCabinet.y + objectSizeBedroomCabinet.y && IMAGE == bedroom && !(backPack.content.size() == 3)) {
            // Курсор мыши находится над объектом
            IMAGE = bedroomCabinet;
        }
        else {
            if (IMAGE == bedroomCabinet){
                IMAGE = bedroom;
            }
        }
        if (mousePos.x >= objectPositionBedroomEmptyCabinet.x &&
            mousePos.x <= objectPositionBedroomEmptyCabinet.x + objectSizeBedroomEmptyCabinet.x &&
            mousePos.y >= objectPositionBedroomEmptyCabinet.y &&
            mousePos.y <= objectPositionBedroomEmptyCabinet.y + objectSizeBedroomEmptyCabinet.y && IMAGE == bedroom && !(backPack.content.size() == 3)) {
            // Курсор мыши находится над объектом
            IMAGE = bedroomEmptyCabinet;
        }
        else {
            if (IMAGE == bedroomEmptyCabinet){
                IMAGE = bedroom;
            }
        }
        if (mousePos.x >= objectPositionBedroomBed.x &&
            mousePos.x <= objectPositionBedroomBed.x + objectSizeBedroomBed.x &&
            mousePos.y >= objectPositionBedroomBed.y &&
            mousePos.y <= objectPositionBedroomBed.y + objectSizeBedroomBed.y && IMAGE == bedroom && !(backPack.content.size() == 3)) {
            // Курсор мыши находится над объектом
            IMAGE = bedroomBed;
        }
        else {
            if (IMAGE == bedroomBed){
                IMAGE = bedroom;
            }
        }
        if (mousePos.x >= objectPositionBedroomLight.x &&
            mousePos.x <= objectPositionBedroomLight.x + objectSizeBedroomLight.x &&
            mousePos.y >= objectPositionBedroomLight.y &&
            mousePos.y <= objectPositionBedroomLight.y + objectSizeBedroomLight.y && IMAGE == bedroom && !(backPack.content.size() == 3)) {
            // Курсор мыши находится над объектом
            if (black){
                btnTurnLight.setText("включить");
            }
            else {
                btnTurnLight.setText("выключить");
            }
            IMAGE = bedroomLight;
        }
        else {
            if (IMAGE == bedroomLight){
                IMAGE = bedroom;
            }
        }
        if (mousePos.x >= objectPositionKitchenCloset.x &&
            mousePos.x <= objectPositionKitchenCloset.x + objectSizeKitchenCloset.x &&
            mousePos.y >= objectPositionKitchenCloset.y &&
            mousePos.y <= objectPositionKitchenCloset.y + objectSizeKitchenCloset.y && IMAGE == kitchen && !(backPack.content.size() == 3)) {
            // Курсор мыши находится над объектом
            IMAGE = kitchenCloset;
        }
        else {
            if (IMAGE == kitchenCloset){
                IMAGE = kitchen;
            }
        }
        if (mousePos.x >= objectPositionKitchenCloset2.x &&
            mousePos.x <= objectPositionKitchenCloset2.x + objectSizeKitchenCloset2.x &&
            mousePos.y >= objectPositionKitchenCloset2.y &&
            mousePos.y <= objectPositionKitchenCloset2.y + objectSizeKitchenCloset2.y && IMAGE == kitchen && !(backPack.content.size() == 3)) {
            // Курсор мыши находится над объектом
            IMAGE = kitchenCloset2;
        }
        else {
            if (IMAGE == kitchenCloset2){
                IMAGE = kitchen;
            }
        }


        //touches
        if (Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            System.out.println(touch.x + " " + touch.y);
            if (btnSettings.hit(touch.x, touch.y)){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenSettings.back = "ScreenHomeSearch";
                main.setScreen(main.screenSettings);
            }
            if (btnHallway.hit(touch.x, touch.y)) {
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                IMAGE = hallway;
            }
            if (btnKitchen.hit(touch.x, touch.y)) {
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                IMAGE = kitchen;

            }
            if (btnBedroom.hit(touch.x, touch.y)) {
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                IMAGE = bedroom;
            }
            if (btnTurnLight.hit(touch.x, touch.y)){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                if (Objects.equals(btnTurnLight.text, "выключить")) {
                    black = true;
                }
                else{
                    black = false;
                }

            }
            if (btnHome.hit(touch.x, touch.y)) {
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                IMAGE = home;
            }
            if (btnBathroom.hit(touch.x, touch.y)){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                IMAGE = bathroom;
            }


            if ( touch.x >= 867 && touch.x <= 887 && touch.y >= 453 && touch.y <= 473){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                cluesMove3 = true;
            }
            if (touch.x >= 1224 && touch.x <= 1375 && touch.y >= 283 && touch.y <= 337 && IMAGE == bedroomCabinet){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenClues.IMAGE = main.screenClues.bedroom;
                main.setScreen(main.screenClues);
            }
            if (touch.x >= 616 && touch.x <= 838 && touch.y >= 562 && touch.y <= 782 && IMAGE == kitchenCloset){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenClues.IMAGE = main.screenClues.kitchen;
                main.setScreen(main.screenClues);
            }
            if (touch.x >= 254 && touch.x <= 592 && touch.y >= 586 && touch.y <= 827 && IMAGE == kitchenCloset2){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenClues.IMAGE = main.screenClues.kitchen2;
                main.setScreen(main.screenClues);
            }
            if (touch.x >= 1232 && touch.x <= 1372 && touch.y >= 130 && touch.y <= 270 && IMAGE == bedroomEmptyCabinet){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenClues.IMAGE = main.screenClues.bedroomEmpty;
                main.setScreen(main.screenClues);
            }




            if (btnTakeIt3.hit(touch.x, touch.y) && IMAGE == home  ) {
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                cluesObject3.takeIt = true;
                backPack.add(cluesObject3);
            }

            if (touch.x >= 254 && touch.x <= 339 && touch.y >= 15 && touch.y <= 100){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.setScreen(main.screenBackPack);
                main.screenBackPack.clas = "ScreenHomeSearch";
            }
            if (btnPhone.hit(touch.x, touch.y) && backPack.content.size() == 3) {
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.setScreen(main.screenPhone);
                main.screenPhone.clas = "ScreenHomeSearch";
            }
            if(btnNextChapter.hit(touch.x, touch.y) && main.screenPhone.nextChapter){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                next = true;
            }
            if (btnHide.hit(touch.x, touch.y)){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenClues.IMAGE = main.screenClues.underBed;
                main.setScreen(main.screenClues);

            }

        }
        // events
        insertObject1.moveUp();




        //paint
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        if (IMAGE == hallway) {
            if (!black) {
                batch.draw(imgHallway, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            }
            else{
                batch.draw(imgHallwayBlack, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            }

            fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);
            btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);

        }
        if (IMAGE == bedroom) {
            if (!black) {
                batch.draw(imgBedroom, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            }
            else{
                batch.draw(imgBedroomBlack, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            }
            btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);
            fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);

        }
        if (IMAGE == bedroomCabinet){
            batch.draw(imgBedroomCabinet, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);
            fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);

        }
        if (IMAGE == bedroomLight){
            batch.draw(imgBedroomLight, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);
            fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);
            btnTurnLight.font.draw(batch, btnTurnLight.text, btnTurnLight.x, btnTurnLight.y);
        }
        if (IMAGE == bedroomTurnOfLight){

            batch.draw(imgBedroomBlack, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);
            fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);
        }
        if (IMAGE == bedroomEmptyCabinet){
            batch.draw(imgBedroomEmptyCabinet, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);
            fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);
        }
        if (IMAGE == bedroomBed){
            batch.draw(imgBedroomBed, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);
            fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);
            btnHide.font.draw(batch, btnHide.text, btnHide.x, btnHide.y);
        }
        if (IMAGE == kitchen) {
            if (!black) {
                batch.draw(imgKitchen, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            }
            else{
                batch.draw(imgKitchenBlack, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            }

            btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);
            fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);


        }
        if (IMAGE == kitchenCloset){
            batch.draw(imgKitchenCloset, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);
            fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);


        }
        if (IMAGE == kitchenCloset2){
            batch.draw(imgKitchenCLoset2, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);
            fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);

        }

        if (IMAGE == home) {
            if (!black) {
                batch.draw(imgHome, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            }
            else{
                batch.draw(imgHomeBlack, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            }

            fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);
            btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);
            if (cluesMove3){
                cluesObject3.move();
                if (cluesObject3.width >= 400){
                    btnTakeIt3.font.draw(batch,  btnTakeIt3.text,  btnTakeIt3.x,  btnTakeIt3.y);
                }
            }
            batch.draw(imgPaper3, cluesObject3.x, cluesObject3.y, cluesObject3.width, cluesObject3.height);
        }
        if (IMAGE == bathroom){
            if (!black) {
                batch.draw(imgBathroom, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            }
            else{
                batch.draw(imgBathroomBlack, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            }
            fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);
            btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);

        }




        if (IMAGE == 0) {

            batch.draw(imgHallway, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);
            btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);
            batch.draw(imgEmily, 1200, 0, 250, 512);
            batch.draw(imgInsert, insertObject1.x, insertObject1.y, insertObject1.width, insertObject1.height);
            if (insertObject1.width >= 400) {
                fontMessageBig.draw(batch, "  Возможно что-то  \n   изменилось", 1000, 540);
            }
        }
        btnHallway.font.draw(batch,  btnHallway.text,  btnHallway.x,  btnHallway.y);
        btnBedroom.font.draw(batch,  btnBedroom.text,  btnBedroom.x,  btnBedroom.y);
        btnKitchen.font.draw(batch,  btnKitchen.text,  btnKitchen.x,  btnKitchen.y);
        btnHome.font.draw(batch,  btnHome.text,  btnHome.x,  btnHome.y);
        btnBathroom.font.draw(batch, btnBathroom.text, btnBathroom.x, btnBathroom.y);

        if (backPack.content.size() == 3){
            if(!main.screenPhone.nextChapter) {
                fontPodarok.draw(batch, "Выполнено", 1200, 800);
                batch.draw(imgEmily, 1200, 0, 250, 512);
                batch.draw(imgInsert, insertObject2.x, insertObject2.y, insertObject2.width, insertObject2.height);
                fontMessageBig.draw(batch, "Что это за странные \n        записки.. \n Лара написала", 1029, 628);
            }
            btnPhone.font.draw(batch, btnPhone.text, btnPhone.x, btnPhone.y);
            if (main.screenPhone.nextChapter){
                btnNextChapter.font.draw(batch, btnNextChapter.text, btnNextChapter.x, btnNextChapter.y);

            }

        }
        else {
            fontPodarok.draw(batch, "Цель: найти 3 улики,\n которые могут помочь", 1200, 800);
        }
        batch.draw(imgBackpack,  234, 15, 85, 85);
        fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);

        if (next){
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(0, 0, 0, alpha);
            shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            shapeRenderer.end();

            alpha += delta * 0.6f;

            if (alpha >= 1) {
                System.out.println("SCREENSTART");
                main.setScreen(main.screenChapter1);
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
        imgHallway.dispose();
        imgKitchen.dispose();
        imgBedroom.dispose();
        imgHome.dispose();
        font.dispose();
        fontPodarok.dispose();
    }
}
