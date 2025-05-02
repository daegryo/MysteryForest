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
    public BitmapFont font, fontGrey, fontText;


    public ScreenStart screenStart;
    public ScreenRegistration screenLogin;
    public ScreenHistory screenHistory;
    public ScreenHomeSearch screenHomeSearch;
    public ScreenBackPack screenBackPack;



//    public ScreenSettings screenSettings;
 //
   // public ScreenAbout screenAbout;
   // public ScreenLeaderBoard screenLeaderBoard;

  //  public ScreenGameStart screenGameStart;





    public static final float SCR_WIDTH = 1600;
    public static final float SCR_HEIGHT= 900;



    @Override
    public void create() {

        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);

        touch = new Vector3();
        font = new BitmapFont(Gdx.files.internal("fonts/Main1.fnt"));
        //  fontGrey = new BitmapFont(Gdx.files.internal("fonts/main1.fnt"));
        //   fontText = new BitmapFont(Gdx.files.internal("fonts/text.fnt"));




        screenStart = new ScreenStart(this);
        screenLogin = new ScreenRegistration(this);
        screenHistory = new ScreenHistory(this);
        screenHomeSearch = new ScreenHomeSearch(this);
        screenBackPack = new ScreenBackPack(this);




        //  screenSettings = new ScreenSettings(this);
        //
        //  screenAbout = new ScreenAbout(this);
        //    screenLeaderBoard = new ScreenLeaderBoard(this);
//
        //   screenGameStart = new ScreenGameStart(this);



           setScreen(screenHomeSearch);

    }


    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        fontGrey.dispose();

    }
}
