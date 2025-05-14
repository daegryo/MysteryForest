package ru.samsung.mysteryforest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

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

    Texture imgInsert;
    Texture imgPaper1;
    Texture imgPaper2;
    Texture imgPaper3;


    Texture imgBackpack;
    Texture imgPhone;

    SpaceButton btnHallway;
    SpaceButton btnBedroom;
    SpaceButton btnKitchen;
    SpaceButton btnHome;
    SpaceButton btnTakeIt1;
    SpaceButton btnTakeIt2;
    SpaceButton btnTakeIt3;
    SpaceButton btnPhone;
    SpaceButton btnNextChapter;
    SpaceButton btnBathroom;



    int IMAGE;
    int hallway;
    int bedroom;
    int kitchen;
    int home;
    int bathroom;

    Insert insertObject1 = new Insert(970, 300, 1f, 1f);
    Insert insertObject2 = new Insert(970, 300, 500, 500);

    Clues cluesObject1 = new Clues(921, 526, 20, 20, "text/paper1.png");
    Clues cluesObject2 = new Clues(1329, 623, 20, 20, "text/paper2.png");
    Clues cluesObject3 = new Clues(867, 453, 20, 20, "text/paper3.png");

    BackPack backPack = new BackPack();

    boolean cluesMove1 = false;
    boolean cluesMove2 = false;
    boolean cluesMove3 = false;

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

        imgInsert = new Texture("text/insert.png");
        imgPaper1 = new Texture("text/paper1.png");
        imgPaper2 = new Texture("text/paper2.png");
        imgPaper3 = new Texture("text/paper3.png");

        imgBackpack = new Texture("icons/backpack.png");
        imgPhone = new Texture("icons/phone.png");

        btnHallway = new SpaceButton(fontPodarok, 100, 810, "Прихожая");
        btnBedroom = new SpaceButton(fontPodarok, 100, 780, "Спальня");
        btnKitchen = new SpaceButton(fontPodarok, 100, 750, "Кухня");
        btnHome = new SpaceButton(fontPodarok, 100, 720, "Дом снаружи");
        btnBathroom = new SpaceButton(fontPodarok, 100, 690, "Ванная");
        btnTakeIt1 = new SpaceButton(fontPodarok, 802, 358, "Взять");
        btnTakeIt2 = new SpaceButton(fontPodarok, 802,280, "Взять");
        btnTakeIt3 = new SpaceButton(fontPodarok, 802,280, "Взять");
        btnPhone = new SpaceButton(fontPodarok, 1400, 700, "phone");
        btnNextChapter = new SpaceButton(font, 740, Main.SCR_HEIGHT/2, "Далее");


        IMAGE = 0;
        hallway = 1;
        bedroom = 2;
        kitchen = 3;
        home = 4;
        bathroom = 5;

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
            if (btnHallway.hit(touch.x, touch.y)) {
                IMAGE = hallway;
            }
            if (btnKitchen.hit(touch.x, touch.y)) {
                IMAGE = kitchen;

            }
            if (btnBedroom.hit(touch.x, touch.y)) {
                IMAGE = bedroom;
            }
            if (btnHome.hit(touch.x, touch.y)) {
                IMAGE = home;
            }
            if (btnBathroom.hit(touch.x, touch.y)){
                IMAGE = bathroom;
            }

            if ( touch.x >= 921 && touch.x <= 941 && touch.y >= 526 && touch.y <= 546){
                cluesMove1 = true;
            }
            if ( touch.x >= 1329 && touch.x <= 1349 && touch.y >= 623 && touch.y <= 643){
                cluesMove2 = true;
            }
            if ( touch.x >= 867 && touch.x <= 887 && touch.y >= 453 && touch.y <= 473){
                cluesMove3 = true;
            }
            if (btnTakeIt1.hit(touch.x, touch.y)&& IMAGE == kitchen) {
                cluesObject1.takeIt = true;
                backPack.add( cluesObject1);

            }
            if (btnTakeIt2.hit(touch.x, touch.y) && IMAGE == bedroom) {
                cluesObject2.takeIt = true;
                backPack.add(cluesObject2);
            }
            if (btnTakeIt3.hit(touch.x, touch.y) && IMAGE == home  ) {
                cluesObject3.takeIt = true;
                backPack.add(cluesObject3);
            }

            if (touch.x >= 254 && touch.x <= 339 && touch.y >= 15 && touch.y <= 100){
                main.setScreen(main.screenBackPack);
                main.screenBackPack.clas = "ScreenHomeSearch";
            }
            if (btnPhone.hit(touch.x, touch.y) && backPack.content.size() == 3) {
                main.setScreen(main.screenPhone);
                main.screenPhone.clas = "ScreenHomeSearch";
            }
            if(btnNextChapter.hit(touch.x, touch.y) && main.screenPhone.nextChapter){
                main.setScreen(main.screenChapter1);
            }

        }
        // events
        insertObject1.moveUp();




        //paint
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        if (IMAGE == hallway) {
            batch.draw(imgHallway, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);

        }
        if (IMAGE == bedroom) {
            batch.draw(imgBedroom, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            if (cluesMove2){
                cluesObject2.move();
                if (cluesObject2.width >= 400){
                    btnTakeIt2.font.draw(batch,  btnTakeIt2.text,  btnTakeIt2.x,  btnTakeIt2.y);
                }
            }
            batch.draw(imgPaper2, cluesObject2.x, cluesObject2.y, cluesObject2.width, cluesObject2.height);
        }
        if (IMAGE == kitchen) {
            batch.draw(imgKitchen, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            if (cluesMove1){
                cluesObject1.move();
                if (cluesObject1.width >= 400){
                    btnTakeIt1.font.draw(batch,  btnTakeIt1.text,  btnTakeIt1.x,  btnTakeIt1.y);
                }
            }
            batch.draw(imgPaper1, cluesObject1.x, cluesObject1.y, cluesObject1.width, cluesObject1.height);

        }
        if (IMAGE == home) {
            batch.draw(imgHome, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            if (cluesMove3){
                cluesObject3.move();
                if (cluesObject3.width >= 400){
                    btnTakeIt3.font.draw(batch,  btnTakeIt3.text,  btnTakeIt3.x,  btnTakeIt3.y);
                }
            }
            batch.draw(imgPaper3, cluesObject3.x, cluesObject3.y, cluesObject3.width, cluesObject3.height);
        }
        if (IMAGE == bathroom){
            batch.draw(imgBathroom, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);

        }




        if (IMAGE == 0) {
            batch.draw(imgHallway, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            batch.draw(imgEmily, 1200, 0, 250, 550);
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
                batch.draw(imgEmily, 1200, 0, 289, 512);
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
