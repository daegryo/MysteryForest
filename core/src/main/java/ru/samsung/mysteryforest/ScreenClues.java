package ru.samsung.mysteryforest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

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

    Texture imgUnderBed;

    Texture imgBedroomEmpty;

    Texture imgPaper1;
    Texture imgPaper2;

    SpaceButton btnBack;
    SpaceButton btnTakeIt1;
    SpaceButton btnTakeIt2;

    int IMAGE = 0;
    int bedroom = 0;
    int kitchen = 1;
    int underBed = 2;
    int bedroomEmpty = 3;
    int kitchen2 = 4;

    boolean cluesMove1 = false;
    boolean cluesMove2 = false;

    Clues cluesObject1 = new Clues(921, 526,  0.01f, 0.01f, "text/paper1.png");
    Clues cluesObject2 = new Clues(1329, 623, 0.01f, 0.01f, "text/paper2.png");

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

        imgKitchenCloset2 = new Texture("bg/clues/kitchen/empty.png");


        imgUnderBed = new Texture("bg/home/bedroom/underBed.png");

        imgBedroomEmpty = new Texture("bg/clues/bedroom/empty.png");

        imgPaper1 = new Texture("text/paper1.png");
        imgPaper2 = new Texture("text/paper2.png");

        btnBack = new SpaceButton(fontPodarok, 150, 30, "X");
        btnTakeIt1 = new SpaceButton(fontPodarok, 802, 358, "Взять");
        btnTakeIt2 = new SpaceButton(fontPodarok, 802,280, "Взять");


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
            if (btnBack.hit(touch.x, touch.y)) {
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.setScreen(main.screenHomeSearch);
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
            if (btnTakeIt1.hit(touch.x, touch.y)&& IMAGE == kitchen) {
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                cluesObject1.takeIt = true;
                main.screenHomeSearch.backPack.add( cluesObject1);

            }

            if (btnTakeIt2.hit(touch.x, touch.y) && IMAGE == bedroom) {
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                cluesObject2.takeIt = true;
                main.screenHomeSearch.backPack.add(cluesObject2);
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
        if (IMAGE == underBed){
            batch.draw(imgUnderBed, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
        }
        if (IMAGE == bedroomEmpty){
            batch.draw(imgBedroomEmpty, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
        }
        if (IMAGE == kitchen2){
            batch.draw(imgKitchenCloset2, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
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
