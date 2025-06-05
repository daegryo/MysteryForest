package ru.samsung.mysteryforest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.Objects;

public class ScreenClues implements Screen {
    Main main;

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;
    public BitmapFont fontScroll;
    public BitmapFont fontPodarok;
    public BitmapFont fontMessageBig;

    Texture imgBedroomCabinet;
    Texture imgBedroomCabinetWithout;

    Texture imgKitchenCloset;
    Texture imgKitchenClosetWithout;

    Texture imgKitchenCloset2;

    Texture imgHomeMail;
    Texture imgHomeMailWithout;

    Texture imgUnderBed;
    Texture imgInBath;
    Texture imgBedroomEmpty;
    Texture imgKitchenBox1;
    Texture imgKitchenBox2;
    Texture imgKitchenBox3;
    Texture imgKitchenBox3Empty;
    Texture imgKitchenCase1;
    Texture imgKitchenCase2;
    Texture imgCar;

    Texture imgKitchenLight;

    Texture imgPaper1;
    Texture imgPaper2;
    Texture imgPaper3;
    Texture imgPaper4;
    Texture imgLamp;

    Texture imgCoin;

    SpaceButton btnBack;
    SpaceButton btnTakeIt1;
    SpaceButton btnTakeIt2;
    SpaceButton btnTakeIt3;
    SpaceButton btnTakeIt4;
    SpaceButton btnTakeIt5;

    int IMAGE = 0;
    int bedroom = 0;
    int kitchen = 1;
    int underBed = 2;
    int bedroomEmpty = 3;
    int kitchen2 = 4;
    int mail = 5;
    int kitchenBox1 = 6;
    int kitchenBox2 = 7;
    int inBath = 8;
    int kitchenCase1 = 9;
    int kitchenBox3 = 10;
    int kitchenLight = 11;
    int kitchenBox3Empty = 12;
    int kitchenCase2;

    int car = 13;

    boolean cluesMove1 = false;
    boolean cluesMove2 = false;
    boolean cluesMove3 = false;
    boolean cluesMove4 = false;
    boolean cluesMove5 = false;

    boolean drawCoin1 = true;
    boolean drawCoin2 = true;
    boolean drawCoin3 = true;
    boolean drawCoin4 = true;
    boolean drawCoin5 = true;
    boolean drawCoin6 = true;
    boolean drawCoin7 = true;
    boolean drawCoin8 = true;
    boolean drawCoin9 = true;
    boolean drawCoin10 = true;
    boolean drawCoin11 = true;
    boolean drawCoin12 = true;
    boolean drawCoin13 = true;

    Vector3 objectPositionKitchenLight = new Vector3(707, 276, 0);
    Vector3 objectSizeKitchenLight = new Vector3(246, 194, 0);

    Clues cluesObject1 = new Clues(921, 526,  0.01f, 0.01f, "text/paper1.png");
    Clues cluesObject2 = new Clues(1329, 623, 0.01f, 0.01f, "text/paper2.png");
    Clues cluesObject3 = new Clues(867, 453, 0.01f, 0.01f, "text/paper3.png");
    Clues cluesObject4 = new Clues(867, 453, 0.01f, 0.01f, "text/lamp.png");
    Clues cluesObject5 = new Clues(1296, 560, 50, 50, "text/paper4.png");

    String back = "";

    public ScreenClues(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        fontScroll = new BitmapFont(Gdx.files.internal("fonts/scroll.fnt"));
        fontPodarok = new BitmapFont(Gdx.files.internal("fonts/Podarok.fnt"));
        fontMessageBig = new BitmapFont(Gdx.files.internal("fonts/messageBig.fnt"));

        imgBedroomCabinet = new Texture("bg/clues/bedroom/bedroomCabinet.png");
        imgBedroomCabinetWithout = new Texture("bg/clues/bedroom/bedroomCabinetWithout.png");

        imgKitchenCloset = new Texture("bg/clues/kitchen/kitchenCloset.png");
        imgKitchenClosetWithout = new Texture("bg/clues/kitchen/kitchenClosetWithout.png");

        imgHomeMail = new Texture("bg/clues/home/homeMail.png");
        imgHomeMailWithout = new Texture("bg/clues/home/homeMailWithout.png");

        imgKitchenCloset2 = new Texture("bg/clues/kitchen/empty.png");
        imgUnderBed = new Texture("bg/home/bedroom/underBed.png");
        imgInBath = new Texture("bg/home/bathroom/inBath.png");
        imgBedroomEmpty = new Texture("bg/clues/bedroom/empty.png");
        imgKitchenBox1 = new Texture("bg/clues/kitchen/kitchenBox1.png");
        imgKitchenBox2 = new Texture("bg/clues/kitchen/kitchenBox2.png");
        imgKitchenBox3 = new Texture("bg/clues/kitchen/kitchenBox3.png");
        imgKitchenBox3Empty = new Texture("bg/clues/kitchen/kitchenBox3Empty.png");
        imgKitchenCase1 = new Texture("bg/clues/kitchen/kitchenCase1.png");
        imgKitchenCase2 = new Texture("bg/clues/kitchen/kitchenCase2.png");
        imgCar = new Texture("bg/clues/car/car.png");

        imgKitchenLight = new Texture("bg/clues/kitchen/mouse/kitchenBox3.png");

        imgPaper1 = new Texture("text/paper1.png");
        imgPaper2 = new Texture("text/paper2.png");
        imgPaper3 = new Texture("text/paper3.png");
        imgPaper4 = new Texture("text/paper4.png");
        imgLamp = new Texture("text/lamp.png");

        imgCoin = new Texture("miniGame/coin.png");

        btnBack = new SpaceButton(fontPodarok, 150, 30, "X");
        btnTakeIt1 = new SpaceButton(fontPodarok, 802, 358, "Взять");
        btnTakeIt2 = new SpaceButton(fontPodarok, 802,280, "Взять");
        btnTakeIt3 = new SpaceButton(fontPodarok, 802,280, "Взять");
        btnTakeIt4 = new SpaceButton(fontPodarok, 802,280, "Взять");
        btnTakeIt5 = new SpaceButton(fontPodarok, 789,190, "Взять");

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //mouse
        Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mousePos);
        if (mousePos.x >= objectPositionKitchenLight.x &&
            mousePos.x <= objectPositionKitchenLight.x + objectSizeKitchenLight.x &&
            mousePos.y >= objectPositionKitchenLight.y &&
            mousePos.y <= objectPositionKitchenLight.y + objectSizeKitchenLight.y && IMAGE == kitchenBox3) {
            IMAGE = kitchenLight;
        }
        else {
            if (IMAGE == kitchenLight){
                IMAGE = kitchenBox3;
            }
        }
        // touches
        if (Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            System.out.println(touch.x + " " + touch.y);
            if (btnBack.hit(touch.x, touch.y)) {
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                if (Objects.equals(back, "ScreenHomeSearch")) {
                    main.setScreen(main.screenHomeSearch);
                }
                if (Objects.equals(back, "ScreenCar")) {
                    main.setScreen(main.screenCar);
                }
            }
            if (touch.x >= 618 && touch.x <= 912 && touch.y >= 440 && touch.y <= 560 && IMAGE == bedroom){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                cluesMove2 = true;
            }
            if (touch.x >= 662 && touch.x <= 934 && touch.y >= 277 && touch.y <= 394 && IMAGE == kitchen){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                cluesMove1 = true;
            }
            if ( touch.x >= 1054 && touch.x <= 1252 && touch.y >= 166 && touch.y <= 349){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                cluesMove3 = true;
            }
            if (touch.x >= 707 && touch.x <= 947 && touch.y >= 277 && touch.y <= 473 && IMAGE == kitchenLight){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                IMAGE = kitchenBox3Empty;
                cluesMove4 = true;
            }
            //change
            if (touch.x >= 1296 && touch.x <= 1346 && touch.y >= 560 && touch.y <= 610 && IMAGE == car){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                IMAGE = car;
                cluesMove5 = true;
            }

            if (btnTakeIt1.hit(touch.x, touch.y)&& IMAGE == kitchen) {
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                cluesObject1.takeIt = true;
                main.screenHomeSearch.backPack.add( cluesObject1);

            }
            if (btnTakeIt3.hit(touch.x, touch.y) && IMAGE == mail  ) {
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                cluesObject3.takeIt = true;
                main.screenHomeSearch.backPack.add(cluesObject3);
            }

            if (btnTakeIt2.hit(touch.x, touch.y) && IMAGE == bedroom) {
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                cluesObject2.takeIt = true;
                main.screenHomeSearch.backPack.add(cluesObject2);
            }
            if (btnTakeIt4.hit(touch.x, touch.y) && IMAGE == kitchenBox3Empty) {
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                cluesObject4.takeIt = true;
                main.screenHomeSearch.backPack.addHelpers(cluesObject4);
            }
            if (btnTakeIt5.hit(touch.x, touch.y) && IMAGE == car) {
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                cluesObject5.takeIt = true;
                main.screenHomeSearch.backPack.add(cluesObject5    );
            }
            if (touch.x >= 722 && touch.x <= 822 && touch.y >= 414 && touch.y <= 514 && IMAGE == kitchenBox1){
                if (drawCoin1) {
                    main.Bank++;
                }
                drawCoin1 = false;

            }
            if (touch.x >= 1286 && touch.x <= 1386 && touch.y >= 404 && touch.y <= 504 && IMAGE == kitchenBox1){
                if (drawCoin2) {
                    main.Bank++;
                }
                drawCoin2 = false;
            }
            if (touch.x >= 933 && touch.x <= 1033 && touch.y >= 789 && touch.y <= 889 && IMAGE == kitchenBox1){
                if (drawCoin3) {
                    main.Bank++;
                }
                drawCoin3 = false;
            }
            if (touch.x >= 463 && touch.x <= 563 && touch.y >= 531 && touch.y <= 631 && IMAGE == kitchen2){
                if (drawCoin4) {
                    main.Bank++;
                }
                drawCoin4 = false;
            }
            if (touch.x >= 713 && touch.x <= 813 && touch.y >= 569 && touch.y <= 669 && IMAGE == kitchen2){
                if (drawCoin5) {
                    main.Bank++;
                }
                drawCoin5 = false;
            }
            if (touch.x >= 968 && touch.x <= 1068 && touch.y >= 539 && touch.y <= 639 && IMAGE == kitchen2){
                if (drawCoin6) {
                    main.Bank++;
                }
                drawCoin6 = false;
            }
            if (touch.x >= 1117 && touch.x <= 1217 && touch.y >= 585 && touch.y <= 685 && IMAGE == kitchen2){
                if (drawCoin7) {
                    main.Bank++;
                }
                drawCoin7 = false;
            }
            if (touch.x >= 881 && touch.x <= 981 && touch.y >= 257 && touch.y <= 357 && IMAGE == kitchen2){
                if (drawCoin8) {
                    main.Bank++;
                }
                drawCoin8 = false;
            }
            if (touch.x >= 651 && touch.x <= 751 && touch.y >= 297 && touch.y <= 397 && IMAGE == kitchen2){
                if (drawCoin9) {
                    main.Bank++;
                }
                drawCoin9 = false;
            }
            if (touch.x >= 1105 && touch.x <= 1205 && touch.y >= 259 && touch.y <= 359 && IMAGE == kitchen2){
                if (drawCoin10) {
                    main.Bank++;
                }
                drawCoin10 = false;
            }
            if (touch.x >= 463 && touch.x <= 563 && touch.y >= 245 && touch.y <= 345 && IMAGE == kitchen2){
                if (drawCoin11) {
                    main.Bank++;
                }
                drawCoin11 = false;
            }
            if (touch.x >= 435 && touch.x <= 535 && touch.y >= 353 && touch.y <= 453 && IMAGE == kitchen2){
                if (drawCoin12) {
                    main.Bank++;
                }
                drawCoin12 = false;
            }
            if (touch.x >= 1146 && touch.x <= 1246 && touch.y >= 357 && touch.y <= 457 && IMAGE == kitchen2){
                if (drawCoin13) {
                    main.Bank++;
                }
                drawCoin13 = false;
            }
        }

        // events

        // paint
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        if (IMAGE == bedroom) {
            if (!cluesObject2.takeIt) {
                batch.draw(imgBedroomCabinet, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            }
            else{
                batch.draw(imgBedroomCabinetWithout, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            }
            if (cluesMove2 && IMAGE == bedroom){
                cluesObject2.move();
                if (cluesObject2.width >= 400){
                    btnTakeIt2.font.draw(batch,  btnTakeIt2.text,  btnTakeIt2.x,  btnTakeIt2.y);
                }
            }

            batch.draw(imgPaper2, cluesObject2.x, cluesObject2.y, cluesObject2.width, cluesObject2.height);
        }
        if (IMAGE == kitchen){
            if (!cluesObject1.takeIt) {
                batch.draw(imgKitchenCloset, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            }
            else{
                batch.draw(imgKitchenClosetWithout, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            }
            if (cluesMove1 && IMAGE == kitchen){
                cluesObject1.move();
                if (cluesObject1.width >= 400){
                    btnTakeIt1.font.draw(batch,  btnTakeIt1.text,  btnTakeIt1.x,  btnTakeIt1.y);
                }
            }
            batch.draw(imgPaper1, cluesObject1.x, cluesObject1.y, cluesObject1.width, cluesObject1.height);
        }
        if (IMAGE == mail){
            if (!cluesObject3.takeIt) {
                batch.draw(imgHomeMail, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            }
            else{
                batch.draw(imgHomeMailWithout, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            }
            if (cluesMove3 && IMAGE == mail){
                System.out.println(cluesObject3.width);
                cluesObject3.move();
                if (cluesObject3.width >= 400){
                    btnTakeIt3.font.draw(batch,  btnTakeIt3.text,  btnTakeIt3.x,  btnTakeIt3.y);
                }
            }
            batch.draw(imgPaper3, cluesObject3.x, cluesObject3.y, cluesObject3.width, cluesObject3.height);
        }
        if (IMAGE == underBed){
            batch.draw(imgUnderBed, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
        }
        if (IMAGE == inBath){
            batch.draw(imgInBath, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
        }

        if (IMAGE == bedroomEmpty){
            batch.draw(imgBedroomEmpty, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
        }
        if (IMAGE == kitchen2){
            batch.draw(imgKitchenCloset2, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            if (drawCoin4) {
                batch.draw(imgCoin, 463, 531, 100, 100);
            }
            if (drawCoin5) {
                batch.draw(imgCoin, 713, 569, 100, 100);
            }
            if (drawCoin6) {
                batch.draw(imgCoin, 968, 539, 100, 100);
            }
            if (drawCoin7) {
                batch.draw(imgCoin, 1117, 585, 100, 100);
            }
            if (drawCoin8) {
                batch.draw(imgCoin, 881, 257, 100, 100);
            }
            if (drawCoin9) {
                batch.draw(imgCoin, 651, 297, 100, 100);
            }
            if (drawCoin10) {
                batch.draw(imgCoin, 1105, 259, 100, 100);
            }
            if (drawCoin11) {
                batch.draw(imgCoin, 463, 245, 100, 100);
            }
            if (drawCoin12) {
                batch.draw(imgCoin, 435, 353, 100, 100);
            }
            if (drawCoin13) {
                batch.draw(imgCoin, 1146, 357, 100, 100);
            }

        }
        if (IMAGE == kitchenBox1){
            batch.draw(imgKitchenBox1, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            if (drawCoin1) {
                batch.draw(imgCoin, 722, 414, 100, 100);
            }
            if (drawCoin2) {
                batch.draw(imgCoin, 1286, 404, 100, 100);
            }
            if (drawCoin3) {
                batch.draw(imgCoin, 933, 789, 100, 100);
            }


        }
        if (IMAGE == kitchenBox2){
            batch.draw(imgKitchenBox2, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
        }
        if (IMAGE == kitchenBox3){
            if (!cluesObject4.takeIt) {
                batch.draw(imgKitchenBox3, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            }
            else{
                batch.draw(imgKitchenBox3Empty, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            }
            if (cluesMove4 && IMAGE == kitchenBox3){
                System.out.println(cluesObject3.width);
                cluesObject4.move();
                if (cluesObject4.width >= 400){
                    btnTakeIt4.font.draw(batch,  btnTakeIt3.text,  btnTakeIt3.x,  btnTakeIt3.y);
                }
            }
            batch.draw(imgLamp, cluesObject4.x, cluesObject4.y, cluesObject4.width, cluesObject4.height);
        }
        if (IMAGE == kitchenBox3Empty){
            batch.draw(imgKitchenBox3Empty, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);

            if (cluesMove4 && IMAGE == kitchenBox3Empty){
                System.out.println(cluesObject3.width);
                cluesObject4.move();
                if (cluesObject4.width >= 400){
                    btnTakeIt4.font.draw(batch,  btnTakeIt3.text,  btnTakeIt3.x,  btnTakeIt3.y);
                }
            }
            batch.draw(imgLamp, cluesObject4.x, cluesObject4.y, cluesObject4.width, cluesObject4.height);
        }

        if (IMAGE == kitchenLight){
            batch.draw(imgKitchenLight, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
        }
        if (IMAGE == car){
            batch.draw(imgCar, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            if (cluesMove5 && IMAGE == car){

                cluesObject5.move();
                if (cluesObject5.width >= 400){
                    btnTakeIt5.font.draw(batch,  btnTakeIt5.text,  btnTakeIt5.x,  btnTakeIt5.y);
                }
            }
            batch.draw(imgPaper4, cluesObject5.x, cluesObject5.y, cluesObject5.width, cluesObject5.height);
        }

        if (IMAGE == kitchenCase1){
            batch.draw(imgKitchenCase1, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
        }
        if (IMAGE == kitchenCase2){
            batch.draw(imgKitchenCase2, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
        }

        fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);
        btnBack.font.draw(batch, btnBack.text, btnBack.x, btnBack.y);
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
