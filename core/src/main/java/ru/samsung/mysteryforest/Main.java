package ru.samsung.mysteryforest;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class Main extends Game {
    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font, fontGrey, fontMessage;


    public ScreenStart screenStart;
    public ScreenRegistration screenLogin;
    public ScreenHistory screenHistory;
    public ScreenHomeSearch screenHomeSearch;
    public ScreenClues screenClues;
    public ScreenBackPack screenBackPack;
    public ScreenPhone screenPhone;
    public ScreenChapter1 screenChapter1;
    public ScreenCar screenCar;
    public ScreenCarGame screenCarGame;
    public ScreenRiver screenRiver;
    public ScreenCard screenCard;
    public ScreenSettings screenSettings;
    public ScreenEnd screenEnd;

    public static final float SCR_WIDTH = 1600;
    public static final float SCR_HEIGHT= 900;

    public int AttentionLara = 50;
    public int AttentionAgata = 30;
    public int AttentionFamily = 70;

    public int Bank = 50;


    @Override
    public void create() {

        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);

        touch = new Vector3();
        font = new BitmapFont(Gdx.files.internal("fonts/Main1.fnt"));






        screenStart = new ScreenStart(this);
        screenLogin = new ScreenRegistration(this);
        screenHistory = new ScreenHistory(this);
        screenHomeSearch = new ScreenHomeSearch(this);
        screenClues = new ScreenClues(this);
        screenBackPack = new ScreenBackPack(this);
        screenPhone = new ScreenPhone(this);
        screenChapter1 = new ScreenChapter1(this);
        screenCar = new ScreenCar(this);
        screenCarGame = new ScreenCarGame(this);
        screenRiver = new ScreenRiver(this);
        screenCard = new ScreenCard(this);
        screenSettings = new ScreenSettings(this);
        screenEnd = new ScreenEnd(this);

        setScreen(screenHomeSearch);

    }


    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        fontGrey.dispose();

    }
}
