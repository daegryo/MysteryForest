package ru.samsung.mysteryforest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Objects;

public class ScreenCarGame implements Screen {
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

    Texture imgBg;
    Texture imgCar;
    Texture imgPlatform;
    Texture imgVetka;
    Texture imgGaz;
    Texture imgTormoz;
    Texture imgGoal;
    Texture imgCoin;

    Music gazMusic;
    Music tormozMusic;
    Music backgroundMusic;

    Platformr carObject;
    Platformr[] platformsObjects;
    Platformr[][] coinObjects;

    Rectangle rectangleCar;
    Rectangle[] rectanglesPlatform;
    Rectangle[][] rectanglesCoin;

    SpaceButton btnGame;
    SpaceButton btnNext;
    SpaceButton btnSettings;


    boolean carObjectJump = false;
    boolean carObjectDown = false;

    boolean promotion = false;
    boolean decreaseSlow = false;
    boolean decreaseFast = false;
    boolean start = true;
    boolean updateTime = false;
    boolean addCoin = true;
    boolean overlapsPlatform = false;
    boolean newStepX = true;


    long TimeStart = TimeUtils.millis();

    int countTry = 3;
    int countCoint = 0;


    public ScreenCarGame(Main main) {
        this.main = main;

        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        fontScroll = new BitmapFont(Gdx.files.internal("fonts/scroll.fnt"));
        fontPodarok = new BitmapFont(Gdx.files.internal("fonts/Podarok.fnt"));
        fontMessage = new BitmapFont(Gdx.files.internal("fonts/message.fnt"));
        fontMessageBig = new BitmapFont(Gdx.files.internal("fonts/messageBig.fnt"));
        fontChapter1 = new BitmapFont(Gdx.files.internal("fonts/chapter1.fnt"));

        imgBg = new Texture("bg/miniGame.png");
        imgCar = new Texture("miniGame/car.png");
        imgPlatform = new Texture("miniGame/platform.png");
        imgVetka = new Texture("miniGame/vetka.png");
        imgGaz = new Texture("miniGame/gaz.png");
        imgTormoz = new Texture("miniGame/tormoz.png");
        imgGoal = new Texture("text/goal.png");
        imgCoin = new Texture("miniGame/coin.png");

        btnGame = new SpaceButton(fontPodarok, 780, 270, "Далее");
        btnNext = new SpaceButton(fontPodarok, 780, 270, "Далее");
        btnSettings = new SpaceButton(fontPodarok, 1500, 50, "settings");

        platformsObjects = new Platformr[30];
        coinObjects = new Platformr[platformsObjects.length][5];

        rectanglesPlatform = new Rectangle[platformsObjects.length];
        rectanglesCoin = new Rectangle[coinObjects.length][5];
        main.screenStart.backgroundMusic.stop();
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/backgroundCar.mp3"));
        gazMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/gaz.mp3"));
        tormozMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/tormoz.mp3"));


        for (int i = 0; i < platformsObjects.length; i++) {
            platformsObjects[i] = new Platformr(500 + i * i * 500, MathUtils.random(300f, 500f), 400, 100, -0.2f, 0);
            rectanglesPlatform[i] = new Rectangle(platformsObjects[i].x, platformsObjects[i].y, platformsObjects[i].width, platformsObjects[i].height);
            for (int j = 0; j < coinObjects[i].length; j++) {
                int a = MathUtils.random(0, 1);
                if (a == 0) {
                    coinObjects[i][j] = new Platformr(platformsObjects[i].x + j * 80, platformsObjects[i].y + 125, 50, 50, platformsObjects[i].stepX, 0);
                } else {
                    coinObjects[i][j] = new Platformr(platformsObjects[i].x + j * 80, platformsObjects[i].y - 70, 50, 50, platformsObjects[i].stepX, 0);
                }
                rectanglesCoin[i][j] = new Rectangle(coinObjects[i][j].x, coinObjects[i][j].y, coinObjects[i][j].width, coinObjects[i][j].height);
            }
        }

        carObject = new Platformr(30, 200, 250, 100, 0, 5);
        rectangleCar = new Rectangle(carObject.x, carObject.y, carObject.width, carObject.height);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //touches
        if (Gdx.input.isTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            if (touch.x >= 1411 && touch.x <= 1485 && touch.y >= 75 && touch.y <= 139) {
                if (main.screenSettings.On) {
                    tormozMusic.pause();
                    gazMusic.play();
                    gazMusic.setLooping(true); // зацикливание
                    gazMusic.setVolume(0.5f);
                }
                promotion = true;

            }
            if (touch.x >= 51 && touch.x <= 146 && touch.y >= 56 && touch.y <= 124) {
                decreaseFast = true;
                gazMusic.pause();
                if (main.screenSettings.On) {
                    tormozMusic.play();
                    tormozMusic.setLooping(true);
                    tormozMusic.setVolume(0.5f);
                }
            }
        } else {
            if (promotion) {
                promotion = false;
                decreaseSlow = true;
                if (main.screenSettings.On) {
                    gazMusic.pause();
                    tormozMusic.pause();
                }
            }

        }
        if (Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            if (!carObjectJump && !carObjectDown && carObject.y <= 200 + 10 && touch.x >= 0 && touch.x <= Main.SCR_WIDTH && touch.y >= 0 && touch.y <= Main.SCR_HEIGHT) { // +10 для небольшого допуска
                for (int i = 0; i < platformsObjects.length; i++) {
                    if (platformsObjects[i].show) {
                        carObjectJump = true;
                    }
                    else{
                        carObjectJump = false;
                        break;
                    }
                }


            }

            if (touch.x >= 1411 && touch.x <= 1485 && touch.y >= 75 && touch.y <= 139) {
                promotion = true;

            }
            if (btnGame.hit(touch.x, touch.y) && start) {
                start = false;
                updateTime = true;
                countTry -= 1;
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
            }
            if (btnSettings.hit(touch.x, touch.y) && start){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenSettings.back = "ScreenCarGame";
                main.setScreen(main.screenSettings);
            }
            if (btnNext.hit(touch.x, touch.y) && countTry == -1) {
                main.dbHelper.updateInformation(main.Id);
                main.setScreen(main.screenRiver);
                backgroundMusic.stop();
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                    main.screenStart.soundClick.play();
                }
            }
        }

        //events
        main.Station = "screenCarGame";
        main.screenStart.backgroundMusic.pause();
        if (main.screenSettings.On) {
            backgroundMusic.play();
            backgroundMusic.setLooping(true);
        }

        if ((countTry == 1 || countTry == 0) && newStepX) {
            for (int i = 0; i < platformsObjects.length; i++) {
                platformsObjects[i].stepX = -0.2f;
                for (int j = 0; j < coinObjects[i].length; j++) {
                    coinObjects[i][j].stepX = -0.2f;

                }
            }
            newStepX = false;
        }
        if (!start) {
            for (int i = 0; i < platformsObjects.length; i++) {
                platformsObjects[i].move();
                rectanglesPlatform[i].x = platformsObjects[i].x;
                rectanglesPlatform[i].y = platformsObjects[i].y;
                for (int j = 0; j < coinObjects[i].length; j++) {
                    coinObjects[i][j].move();
                    rectanglesCoin[i][j].x = coinObjects[i][j].x;
                    rectanglesCoin[i][j].y = coinObjects[i][j].y;
                }
            }
            if (carObjectDown || (!carObjectJump && !carObjectDown && carObject.y > 200)) {
                boolean onPlatform = false;
                for (int i = 0; i < rectanglesPlatform.length; i++) {
                    platformsObjects[i].show = true;
                    if (rectangleCar.overlaps(rectanglesPlatform[i])) {
                        carObjectJump = false;

                        if (rectangleCar.y + rectangleCar.height >= rectanglesPlatform[i].y ||
                            rectangleCar.y + rectangleCar.height <= rectanglesPlatform[i].y + rectanglesPlatform[i].height) {
                            platformsObjects[i].show = false;
                            carObject.y = rectanglesPlatform[i].y + rectanglesPlatform[i].height;
                            rectangleCar.y = carObject.y;
                            carObjectDown = false;
                            onPlatform = true;
                        }
                        if (!platformsObjects[i].show) {
                            carObject.y = rectanglesPlatform[i].y + rectanglesPlatform[i].height;
                            rectangleCar.y = carObject.y;
                            carObjectDown = false;
                            onPlatform = true;
                        }
                        if (platformsObjects[i].show) {
                            carObjectJump = false;
                             carObject.y = rectanglesPlatform[i].y - rectangleCar.height;
                             rectangleCar.y = carObject.y;
                            carObjectDown = true;
                        }
                        break;
                    }
                }

                if (!onPlatform && !carObjectJump && carObject.y > 200) {
                    carObjectDown = true;
                }
            }
            for (int i = 0; i < rectanglesCoin.length; i++) {
                for (int j = 0; j < rectanglesCoin[i].length; j++) {
                    if (rectangleCar.overlaps(rectanglesCoin[i][j])) {
                        if (coinObjects[i][j].show) {
                            main.Bank += 1;
                        }
                        coinObjects[i][j].show = false;

                        addCoin = false;
                    }
                }
            }

            if (carObjectJump) {
                if (carObject.y < 500 && !overlapsPlatform) {
                    carObject.jump();
                    rectangleCar.x = carObject.x;
                    rectangleCar.y = carObject.y;
                } else {
                    carObjectJump = false;
                    carObjectDown = true;
                }
            }
            if (carObjectDown && !overlapsPlatform) {
                if (carObject.y > 200) {
                    carObject.down();
                    rectangleCar.x = carObject.x;
                    rectangleCar.y = carObject.y;
                } else {
                    carObjectDown = false;
                }
            }

            if (promotion) {
                for (int i = 0; i < platformsObjects.length; i++) {
                    if (platformsObjects[i].stepX > -12) {
                        platformsObjects[i].stepX *= 1.006f;
                        for (int j = 0; j < coinObjects[i].length; j++) {
                            coinObjects[i][j].stepX *= 1.006f;
                        }

                    }
                }
            }
            if (decreaseSlow) {
                for (int i = 0; i < platformsObjects.length; i++) {
                    if (platformsObjects[i].stepX < -1) {
                        platformsObjects[i].stepX /= 1.0001f;
                        for (int j = 0; j < coinObjects[i].length; j++) {
                            coinObjects[i][j].stepX = platformsObjects[i].stepX;
                        }
                    } else {
                        decreaseSlow = false;
                    }
                }
            }
            if (decreaseFast) {
                for (int i = 0; i < platformsObjects.length; i++) {
                    if (platformsObjects[i].stepX < -1) {
                        platformsObjects[i].stepX /= 1.01f;
                        for (int j = 0; j < coinObjects[i].length; j++) {
                            coinObjects[i][j].stepX = platformsObjects[i].stepX;
                        }

                    }
                    decreaseFast = false;
                }
            }
        }
        if (updateTime) {
            TimeStart = TimeUtils.millis();
            updateTime = false;
        }
        if (Objects.equals(beatifulTime(TimeUtils.millis() - TimeStart).split(" : ")[0], "30")) {
            start = true;
        }



        //paint
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(imgBg, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
        if (start) {
            batch.draw(imgGoal, 612, 277, 400, 400);
            btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);

            if (countTry == 3) {
                newStepX = true;
                fontMessageBig.draw(batch, "Цель собрать как \nможно больше монет за \n30 секунд.\nЕсть 3 попытки", 625, 580);
                btnGame.font.draw(batch, btnGame.text, btnGame.x, btnGame.y);
            }
            if (countTry == 2) {
                newStepX = true;
                btnGame.font.draw(batch, btnGame.text, btnGame.x, btnGame.y);
                fontMessageBig.draw(batch, "Цель собрать как \nможно больше монет за \n30 секунд.\nОсталось " + countTry + " попытки", 625, 580);
            }
            if (countTry == 1) {
                newStepX = true;
                btnGame.font.draw(batch, btnGame.text, btnGame.x, btnGame.y);
                fontMessageBig.draw(batch, "Цель собрать как \nможно больше монет за \n30 секунд.\nОсталось " + countTry + " попытка", 625, 580);
            }
            if (countTry == 0) {

                fontMessageBig.draw(batch, "Цель собрать как \nможно больше монет за \n30 секунд.\nОсталось " + countTry + " попыток", 625, 580);
                btnNext.font.draw(batch, btnNext.text, btnNext.x, btnNext.y);
            }

        } else {
            batch.draw(imgCar, carObject.x, carObject.y, carObject.width, carObject.height);
            // batch.draw(imgPlatform, platformObject.x, platformObject.y, platformObject.width, platformObject.height);
            for (int i = 0; i < platformsObjects.length; i++) {
                batch.draw(imgPlatform, platformsObjects[i].x, platformsObjects[i].y, platformsObjects[i].width, platformsObjects[i].height);
                for (int j = 0; j < coinObjects[i].length; j++) {
                    if (coinObjects[i][j].show) {
                        batch.draw(imgCoin, coinObjects[i][j].x, coinObjects[i][j].y, coinObjects[i][j].width, coinObjects[i][j].height);
                    }
                }

            }
            batch.draw(imgGaz, 1400, 50, 100, 150);
            batch.draw(imgTormoz, 50, 50, 100, 150);
            fontPodarok.draw(batch, Math.round(platformsObjects[0].stepX * -1 * 10) + " km/h", 736, 762);
            fontPodarok.draw(batch, beatifulTime(TimeUtils.millis() - TimeStart), 1200, 700);
        }
        fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);

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
        imgTormoz.dispose();
        imgBg.dispose();
        imgGaz.dispose();
        imgVetka.dispose();
        imgPlatform.dispose();
        tormozMusic.dispose();
        gazMusic.dispose();
        font.dispose();
        fontScroll.dispose();
        fontPodarok.dispose();
        fontMessage.dispose();;
        fontMessageBig.dispose();
        fontChapter1.dispose();
    }

    public String beatifulTime(long time) {
        int sec = (int) (time / 1000);
        int msec = (int) (time / 10 % 100);
        return new String(sec + " : " + msec);

    }

}


