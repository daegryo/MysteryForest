package ru.samsung.mysteryforest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.List;

public class ScreenHistory implements Screen {
    Main main;

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;
    public BitmapFont fontScroll;
    public BitmapFont fontPodarok;
    public BitmapFont fontMessageBig;


    Texture imgBg;
    Texture imgEmily;
    Texture imgInsert;

    Texture[] scroll = new Texture[10];
    Scroll scrollObject = new Scroll();
    Insert insertObject = new Insert(970, 300, 1f, 1f);
    SpaceButton btnRollUp, btnCheckHome;
    public ReadFile readFile;

    List<String> array;
    boolean scrollDown = false;
    boolean rollUp = false;
    boolean moveDown = false;
    boolean checkHouse = false;
    int phase = 0;
    int cursor = 0;
    private long timeLastPhase, timePhaseInterval = 70;

    public ScreenHistory(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        fontScroll = new BitmapFont(Gdx.files.internal("fonts/scroll.fnt"));
        fontPodarok = new BitmapFont(Gdx.files.internal("fonts/Podarok.fnt"));
        fontMessageBig = new BitmapFont(Gdx.files.internal("fonts/messageBig.fnt"));

        btnRollUp = new SpaceButton(fontPodarok, 793, 450, "свернуть");
        btnCheckHome = new SpaceButton(fontPodarok, 870, 482, "Проверить дом");


        readFile = new ReadFile("assets/story/example.txt", 7);
        array = readFile.reader();

        imgBg = new Texture("bg/houses/houseEmily.png");
        imgEmily = new Texture("heros/Emily/EmilyFullLength.png");
        imgInsert = new Texture("text/insert.png");
        for (int i = 0; i < 10; i++) {
            scroll[i] = new Texture("animation/scroll/" + (i + 1) + ".png");

        }
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
            if (cursor < array.size()){
                cursor++;
            }
            if (cursor == array.size()) {
                if (phase == 9) {
                    scrollDown = true;
                }
            }
            if (btnRollUp.hit(touch.x, touch.y) && scrollDown) {
                rollUp = true;
            }
            if (rollUp &&  scrollObject.y <= 10){
                insertObject.down = true;
                checkHouse = true;
            }
            if (checkHouse && btnCheckHome.hit(touch.x, touch.y)){
                main.setScreen(main.screenHomeSearch);
            }





        }

        //events
        if (rollUp) {
            scrollObject.move();
            btnRollUp.x = scrollObject.x + 60;
            btnRollUp.y = scrollObject.y + 50;
            if (scrollObject.y <= 10) {
                btnRollUp.setText("--->");
                insertObject.moveUp();

            }
        }

        // paint
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(imgBg, 0, 0, Main.SCR_WIDTH,Main.SCR_HEIGHT);

       // System.out.println(phase);
        if (phase < 9 && !scrollDown) {
            batch.draw(scroll[phase], scrollObject.x, scrollObject.y, scrollObject.width, scrollObject.height);
            changePhase();
        }
        if (scrollDown){
            batch.draw(scroll[scroll.length - phase - 1], scrollObject.x, scrollObject.y, scrollObject.width, scrollObject.height);
            changePhase();
            btnRollUp.font.draw(batch,  btnRollUp.text,  btnRollUp.x,  btnRollUp.y);
        }

        else {
            batch.draw(scroll[phase],scrollObject.x, scrollObject.y, scrollObject.width, scrollObject.height);

        }

        if (cursor != array.size() && !(phase < 9 && !scrollDown)) {
            fontMessageBig.draw(batch, array.get(cursor), 623, 750);
        }

        if (rollUp &&  scrollObject.y <= 10){
            batch.draw(imgEmily, 1200, 0, 289, 512);
            batch.draw(imgInsert, insertObject.x, insertObject.y, insertObject.width, insertObject.height);
            if (insertObject.width >= 400) {
                fontMessageBig.draw(batch, "Надо проверить дом", 1020, 540);
            }
        }
        if (checkHouse){
            btnCheckHome.font.draw(batch,  btnCheckHome.text,  btnCheckHome.x, btnCheckHome.y);
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
        imgBg.dispose();
        for (int i = 0; i < scroll.length; i++) {
            scroll[i].dispose();
        }

    }
    private void changePhase(){
        if(TimeUtils.millis() > timeLastPhase+timePhaseInterval) {
            if (scrollDown && phase == 8) phase = 8;
            else {
                if (++phase == 10) phase = 0;
                timeLastPhase = TimeUtils.millis();
            }
        }
    }
}
