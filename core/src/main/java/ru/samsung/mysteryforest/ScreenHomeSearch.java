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

import org.lwjgl.Sys;

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
    Texture imgKitchenCase1;
    Texture imgKitchenCase2;
    Texture imgKitchenCase3;
    Texture imgKitchenCase4;
    Texture imgKitchenBox1;
    Texture imgKitchenBox2;
    Texture imgKitchenBox3;
    Texture imgKitchenBox4;
    Texture imgKitchenOven;
    Texture imgBathroomBath;
    Texture imgHomeMail;

    Texture imgBedroomCabinetBlack;
    Texture imgKitchenClosetBlack;
    Texture imgBedroomLightBlack;
    Texture imgBedroomEmptyCabinetBlack;
    Texture imgBedroomBedBlack;
    Texture imgKitchenCLoset2Black;
    Texture imgKitchenCase1Black;
    Texture imgKitchenCase2Black;
    Texture imgKitchenCase3Black;
    Texture imgKitchenCase4Black;
    Texture imgKitchenBox1Black;
    Texture imgKitchenBox2Black;
    Texture imgKitchenBox3Black;
    Texture imgKitchenBox4Black;
    Texture imgKitchenOvenBlack;
    Texture imgBathroomBathBlack;
    Texture imgHomeMailBlack;

    Texture imgBedroomBlack;
    Texture imgHallwayBlack;
    Texture imgKitchenBlack;
    Texture imgBathroomBlack;
    Texture imgHomeBlack;

    Texture imgInsert;

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
    SpaceButton btnHideInBath;

    int IMAGE;
    int hallway;
    int bedroom;
    int kitchen;
    int home;
    int bathroom;


    Insert insertObject1 = new Insert(920, 300, 1f, 1f);
    Insert insertObject2 = new Insert(920, 300, 500, 500);

    BackPack backPack = new BackPack();

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

    Vector3 objectPositionKitchenCase1 = new Vector3(350, 0, 0);
    Vector3 objectSizeKitchenCase1 = new Vector3(216, 194, 0);

    Vector3 objectPositionKitchenCase2 = new Vector3(577, 8, 0);
    Vector3 objectSizeKitchenCase2 = new Vector3(181, 211, 0);

    Vector3 objectPositionKitchenCase3 = new Vector3(802, 48, 0);
    Vector3 objectSizeKitchenCase3 = new Vector3(134, 157, 0);

    Vector3 objectPositionKitchenCase4 = new Vector3(1310, 0, 0);
    Vector3 objectSizeKitchenCase4 = new Vector3(237, 132 , 0);

    Vector3 objectPositionKitchenBox1 = new Vector3(346, 187, 0);
    Vector3 objectSizeKitchenBox1 = new Vector3(215, 74, 0);

    Vector3 objectPositionKitchenBox2 = new Vector3(576, 222, 0);
    Vector3 objectSizeKitchenBox2 = new Vector3(186, 54, 0);

    Vector3 objectPositionKitchenBox3 = new Vector3(802, 240, 0);
    Vector3 objectSizeKitchenBox3 = new Vector3(138, 32, 0);

    Vector3 objectPositionKitchenBox4 = new Vector3(1318, 180, 0);
    Vector3 objectSizeKitchenBox4 = new Vector3(233, 30, 0);

    Vector3 objectPositionKitchenOven = new Vector3(964, 12, 0);
    Vector3 objectSizeKitchenOven = new Vector3(304, 266, 0);

    Vector3 objectPositionBathroomBath = new Vector3(119, 48, 0);
    Vector3 objectSizeBathroomBath = new Vector3(551, 761, 0);

    Vector3 objectPositionHomeMail = new Vector3(1176, 130, 0);
    Vector3 objectSizeHomeMail = new Vector3(85, 305, 0);

    ShapeRenderer shapeRenderer;
    float alpha;

    private boolean isHoveringBedroomCabinet = false;
    private boolean isHoveringBedroomEmptyCabinet = false;
    private boolean isHoveringBedroomBed = false;
    private boolean isHoveringBedroomLight = false;
    private boolean isHoveringKitchenCloset = false;
    private boolean isHoveringKitchenCloset2 = false;
    private boolean isHoveringKitchenCase1 = false;
    private boolean isHoveringKitchenCase2 = false;
    private boolean isHoveringKitchenCase3 = false;
    private boolean isHoveringKitchenCase4 = false;
    private boolean isHoveringKitchenBox1 = false;
    private boolean isHoveringKitchenBox2 = false;
    private boolean isHoveringKitchenBox3 = false;
    private boolean isHoveringKitchenBox4 = false;
    private boolean isHoveringKitchenOven = false;
    private boolean isHoveringBathroomBath = false;
    private boolean isHoveringHomeMail = false;


    private float hoverAlpha = 0f;
    private final float HOVER_SPEED = 4f;
    private float hoverAlpha1 = 0f;
    private float hoverAlpha2 = 0f;
    private float hoverAlpha3 = 0f;
    private float hoverAlpha4 = 0f;
    private float hoverAlpha5 = 0f;
    private float hoverAlpha6 = 0f;
    private float hoverAlpha7 = 0f;
    private float hoverAlpha8 = 0f;
    private float hoverAlpha9 = 0f;
    private float hoverAlpha10 = 0f;
    private float hoverAlpha11 = 0f;
    private float hoverAlpha12 = 0f;
    private float hoverAlpha13 = 0f;
    private float hoverAlpha14 = 0f;
    private float hoverAlpha15 = 0f;
    private float hoverAlpha16 = 0f;

    public ScreenHomeSearch(Main main) {
        this.main = main;

        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        fontScroll = new BitmapFont(Gdx.files.internal("fonts/scroll.fnt"));
        fontPodarok = new BitmapFont(Gdx.files.internal("fonts/Podarok.fnt"));
        fontMessageBig = new BitmapFont(Gdx.files.internal("fonts/bundle.fnt"));

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
        imgKitchenCase1 = new Texture("bg/home/kitchen/kitchenCase1.png");
        imgKitchenCase2 = new Texture("bg/home/kitchen/kitchenCase2.png");
        imgKitchenCase3 = new Texture("bg/home/kitchen/kitchenCase3.png");
        imgKitchenCase4 = new Texture("bg/home/kitchen/kitchenCase4.png");
        imgKitchenBox1 = new Texture("bg/home/kitchen/kitchenBox1.png");
        imgKitchenBox2 = new Texture("bg/home/kitchen/kitchenBox2.png");
        imgKitchenBox3 = new Texture("bg/home/kitchen/kitchenBox3.png");
        imgKitchenBox4 = new Texture("bg/home/kitchen/kitchenBox4.png");
        imgKitchenOven = new Texture("bg/home/kitchen/kitchenOven.png");
        imgBathroomBath = new Texture("bg/home/bathroom/bathroomBath.png");
        imgHomeMail = new Texture("bg/home/home/homeMail.png");

        imgBedroomCabinetBlack = new Texture("bg/home/bedroom/black/bedroomCabinet.png");
        imgKitchenClosetBlack = new Texture("bg/home/kitchen/black/kitchenCloset.png");
        imgBedroomLightBlack = new Texture("bg/home/bedroom/black/bedroomLight.png");
        imgBedroomEmptyCabinetBlack = new Texture("bg/home/bedroom/black/bedroomEmptyCabinet.png");
        imgBedroomBedBlack = new Texture("bg/home/bedroom/black/bedroomBed.png");
        imgKitchenCLoset2Black = new Texture("bg/home/kitchen/black/kitchenCloset2.png");
        imgKitchenCase1Black = new Texture("bg/home/kitchen/black/kitchenCase1.png");
        imgKitchenCase2Black = new Texture("bg/home/kitchen/black/kitchenCase2.png");
        imgKitchenCase3Black = new Texture("bg/home/kitchen/black/kitchenCase3.png");
        imgKitchenCase4Black = new Texture("bg/home/kitchen/black/kitchenCase4.png");
        imgKitchenBox1Black = new Texture("bg/home/kitchen/black/kitchenBox1.png");
        imgKitchenBox2Black = new Texture("bg/home/kitchen/black/kitchenBox2.png");
        imgKitchenBox3Black = new Texture("bg/home/kitchen/black/kitchenBox3.png");
        imgKitchenBox4Black = new Texture("bg/home/kitchen/black/kitchenBox4.png");
        imgKitchenOvenBlack = new Texture("bg/home/kitchen/black/kitchenOven.png");
        imgBathroomBathBlack = new Texture("bg/home/bathroom/black/bathroomBath.png");
        imgHomeMailBlack = new Texture("bg/home/home/black/homeMail.png");

        imgBedroomBlack = new Texture("bg/home/bedroom/bedroomTurnOffLight.png");
        imgHallwayBlack = new Texture("bg/home/hallway/hallwayBlack.png");
        imgKitchenBlack = new Texture("bg/home/kitchen/kitchenBlack.png");
        imgHomeBlack = new Texture("bg/home/home/homeBlack.png");
        imgBathroomBlack = new Texture("bg/home/bathroom/bathroomBlack.png");

        imgInsert = new Texture("text/insert.png");


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
        btnHideInBath = new SpaceButton(fontPodarok, 408, 492, "спрятаться");

        IMAGE = 0;
        hallway = 1;
        bedroom = 2;
        kitchen = 3;
        home = 4;
        bathroom = 5;

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
        boolean isCurrentlyHoveringBedroomCabinet = mousePos.x >= objectPositionBedroomCabinet.x &&
            mousePos.x <= objectPositionBedroomCabinet.x + objectSizeBedroomCabinet.x &&
            mousePos.y >= objectPositionBedroomCabinet.y &&
            mousePos.y <= objectPositionBedroomCabinet.y + objectSizeBedroomCabinet.y &&
            IMAGE == bedroom && !(backPack.content.size() == 3);

        boolean isCurrentlyHoveringBedroomEmptyCabinet = mousePos.x >= objectPositionBedroomEmptyCabinet.x &&
            mousePos.x <= objectPositionBedroomEmptyCabinet.x + objectSizeBedroomEmptyCabinet.x &&
            mousePos.y >= objectPositionBedroomEmptyCabinet.y &&
            mousePos.y <= objectPositionBedroomEmptyCabinet.y + objectSizeBedroomEmptyCabinet.y && IMAGE == bedroom && !(backPack.content.size() == 3);

        boolean isCurrentlyHoveringBedroomBed = mousePos.x >= objectPositionBedroomBed.x &&
            mousePos.x <= objectPositionBedroomBed.x + objectSizeBedroomBed.x &&
            mousePos.y >= objectPositionBedroomBed.y &&
            mousePos.y <= objectPositionBedroomBed.y + objectSizeBedroomBed.y && IMAGE == bedroom && !(backPack.content.size() == 3);

        boolean isCurrentlyHoveringBedroomLight = mousePos.x >= objectPositionBedroomLight.x &&
            mousePos.x <= objectPositionBedroomLight.x + objectSizeBedroomLight.x &&
            mousePos.y >= objectPositionBedroomLight.y &&
            mousePos.y <= objectPositionBedroomLight.y + objectSizeBedroomLight.y && IMAGE == bedroom && !(backPack.content.size() == 3);

        boolean isCurrentlyHoveringKitchenCloset = mousePos.x >= objectPositionKitchenCloset.x &&
            mousePos.x <= objectPositionKitchenCloset.x + objectSizeKitchenCloset.x &&
            mousePos.y >= objectPositionKitchenCloset.y &&
            mousePos.y <= objectPositionKitchenCloset.y + objectSizeKitchenCloset.y && IMAGE == kitchen && !(backPack.content.size() == 3);

        boolean isCurrentlyHoveringKitchenCloset2 = mousePos.x >= objectPositionKitchenCloset2.x &&
            mousePos.x <= objectPositionKitchenCloset2.x + objectSizeKitchenCloset2.x &&
            mousePos.y >= objectPositionKitchenCloset2.y &&
            mousePos.y <= objectPositionKitchenCloset2.y + objectSizeKitchenCloset2.y && IMAGE == kitchen && !(backPack.content.size() == 3);

        boolean isCurrentlyHoveringKitchenCase1 = mousePos.x >= objectPositionKitchenCase1.x &&
            mousePos.x <= objectPositionKitchenCase1.x + objectSizeKitchenCase1.x &&
            mousePos.y >= objectPositionKitchenCase1.y &&
            mousePos.y <= objectPositionKitchenCase1.y + objectSizeKitchenCase1.y && IMAGE == kitchen && !(backPack.content.size() == 3);

        boolean isCurrentlyHoveringKitchenCase2 = mousePos.x >= objectPositionKitchenCase2.x &&
            mousePos.x <= objectPositionKitchenCase2.x + objectSizeKitchenCase2.x &&
            mousePos.y >= objectPositionKitchenCase2.y &&
            mousePos.y <= objectPositionKitchenCase2.y + objectSizeKitchenCase2.y && IMAGE == kitchen && !(backPack.content.size() == 3);

        boolean isCurrentlyHoveringKitchenCase3 = mousePos.x >= objectPositionKitchenCase3.x &&
            mousePos.x <= objectPositionKitchenCase3.x + objectSizeKitchenCase3.x &&
            mousePos.y >= objectPositionKitchenCase3.y &&
            mousePos.y <= objectPositionKitchenCase3.y + objectSizeKitchenCase3.y && IMAGE == kitchen && !(backPack.content.size() == 3);

        boolean isCurrentlyHoveringKitchenCase4 = mousePos.x >= objectPositionKitchenCase4.x &&
            mousePos.x <= objectPositionKitchenCase4.x + objectSizeKitchenCase4.x &&
            mousePos.y >= objectPositionKitchenCase4.y &&
            mousePos.y <= objectPositionKitchenCase4.y + objectSizeKitchenCase4.y && IMAGE == kitchen && !(backPack.content.size() == 3);

        boolean isCurrentlyHoveringKitchenBox1 = mousePos.x >= objectPositionKitchenBox1.x &&
            mousePos.x <= objectPositionKitchenBox1.x + objectSizeKitchenBox1.x &&
            mousePos.y >= objectPositionKitchenBox1.y &&
            mousePos.y <= objectPositionKitchenBox1.y + objectSizeKitchenBox1.y && IMAGE == kitchen && !(backPack.content.size() == 3);

        boolean isCurrentlyHoveringKitchenBox2 = mousePos.x >= objectPositionKitchenBox2.x &&
            mousePos.x <= objectPositionKitchenBox2.x + objectSizeKitchenBox2.x &&
            mousePos.y >= objectPositionKitchenBox2.y &&
            mousePos.y <= objectPositionKitchenBox2.y + objectSizeKitchenBox2.y && IMAGE == kitchen && !(backPack.content.size() == 3);

        boolean isCurrentlyHoveringKitchenBox3 = mousePos.x >= objectPositionKitchenBox3.x &&
            mousePos.x <= objectPositionKitchenBox3.x + objectSizeKitchenBox3.x &&
            mousePos.y >= objectPositionKitchenBox3.y &&
            mousePos.y <= objectPositionKitchenBox3.y + objectSizeKitchenBox3.y && IMAGE == kitchen && !(backPack.content.size() == 3);

        boolean isCurrentlyHoveringKitchenBox4 = mousePos.x >= objectPositionKitchenBox4.x &&
            mousePos.x <= objectPositionKitchenBox4.x + objectSizeKitchenBox4.x &&
            mousePos.y >= objectPositionKitchenBox4.y &&
            mousePos.y <= objectPositionKitchenBox4.y + objectSizeKitchenBox4.y && IMAGE == kitchen && !(backPack.content.size() == 3);

        boolean isCurrentlyHoveringKitchenOven = mousePos.x >= objectPositionKitchenOven.x &&
            mousePos.x <= objectPositionKitchenOven.x + objectSizeKitchenOven.x &&
            mousePos.y >= objectPositionKitchenOven.y &&
            mousePos.y <= objectPositionKitchenOven.y + objectSizeKitchenOven.y && IMAGE == kitchen && !(backPack.content.size() == 3);

        boolean isCurrentlyHoveringBathroomBath = mousePos.x >= objectPositionBathroomBath.x &&
            mousePos.x <= objectPositionBathroomBath.x + objectSizeBathroomBath.x &&
            mousePos.y >= objectPositionBathroomBath.y &&
            mousePos.y <= objectPositionBathroomBath.y + objectSizeBathroomBath.y && IMAGE == bathroom && !(backPack.content.size() == 3);

        boolean isCurrentlyHoveringHomeMail = mousePos.x >= objectPositionHomeMail.x &&
            mousePos.x <= objectPositionHomeMail.x + objectSizeHomeMail.x &&
            mousePos.y >= objectPositionHomeMail.y &&
            mousePos.y <= objectPositionHomeMail.y + objectSizeHomeMail.y && IMAGE == home && !(backPack.content.size() == 3);

        if (isCurrentlyHoveringBedroomCabinet != isHoveringBedroomCabinet) {
            isHoveringBedroomCabinet = isCurrentlyHoveringBedroomCabinet;
        }
        if (isCurrentlyHoveringBedroomEmptyCabinet != isHoveringBedroomEmptyCabinet) {
            isHoveringBedroomEmptyCabinet = isCurrentlyHoveringBedroomEmptyCabinet;
        }
        if (isCurrentlyHoveringBedroomBed != isHoveringBedroomBed) {
            isHoveringBedroomBed = isCurrentlyHoveringBedroomBed;
        }
        if (isCurrentlyHoveringBedroomLight != isHoveringBedroomLight) {
            isHoveringBedroomLight = isCurrentlyHoveringBedroomLight;
            if (black){
                btnTurnLight.setText("включить");
            }
            else {
                btnTurnLight.setText("выключить");
            }
        }
        if (isCurrentlyHoveringKitchenCloset != isHoveringKitchenCloset) {
            isHoveringKitchenCloset = isCurrentlyHoveringKitchenCloset;
        }
        if (isCurrentlyHoveringKitchenCloset2 != isHoveringKitchenCloset2) {
            isHoveringKitchenCloset2 = isCurrentlyHoveringKitchenCloset2;
        }
        if (isCurrentlyHoveringKitchenCase1 != isHoveringKitchenCase1) {
            isHoveringKitchenCase1 = isCurrentlyHoveringKitchenCase1;
        }
        if (isCurrentlyHoveringKitchenCase2 != isHoveringKitchenCase2) {
            isHoveringKitchenCase2 = isCurrentlyHoveringKitchenCase2;
        }
        if (isCurrentlyHoveringKitchenCase3 != isHoveringKitchenCase3) {
            isHoveringKitchenCase3 = isCurrentlyHoveringKitchenCase3;
        }
        if (isCurrentlyHoveringKitchenCase4 != isHoveringKitchenCase4) {
            isHoveringKitchenCase4 = isCurrentlyHoveringKitchenCase4;
        }
        if (isCurrentlyHoveringKitchenBox1 != isHoveringKitchenBox1) {
            isHoveringKitchenBox1 = isCurrentlyHoveringKitchenBox1;
        }
        if (isCurrentlyHoveringKitchenBox2 != isHoveringKitchenBox2) {
            isHoveringKitchenBox2 = isCurrentlyHoveringKitchenBox2;
        }
        if (isCurrentlyHoveringKitchenBox3 != isHoveringKitchenBox3) {
            isHoveringKitchenBox3 = isCurrentlyHoveringKitchenBox3;
        }
        if (isCurrentlyHoveringKitchenBox4 != isHoveringKitchenBox4) {
            isHoveringKitchenBox4 = isCurrentlyHoveringKitchenBox4;
        }
        if (isCurrentlyHoveringKitchenOven != isHoveringKitchenOven) {
            isHoveringKitchenOven = isCurrentlyHoveringKitchenOven;
        }
        if (isCurrentlyHoveringBathroomBath != isHoveringBathroomBath) {
            isHoveringBathroomBath = isCurrentlyHoveringBathroomBath;
        }
        if (isCurrentlyHoveringHomeMail != isHoveringHomeMail) {
            isHoveringHomeMail = isCurrentlyHoveringHomeMail;
        }


        //touches
        if (Gdx.input.justTouched()) {
            main.screenClues.back = "ScreenHomeSearch";
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
                    main.screenStart.soundDoor.play();
                }
                IMAGE = hallway;
            }
            if (btnKitchen.hit(touch.x, touch.y)) {
                if (main.screenSettings.On) {
                    main.screenStart.soundDoor.play();
                }
                IMAGE = kitchen;

            }
            if (btnBedroom.hit(touch.x, touch.y)) {
                if (main.screenSettings.On) {
                    main.screenStart.soundDoor.play();
                }
                IMAGE = bedroom;
            }
            if (btnTurnLight.hit(touch.x, touch.y)){
                if (main.screenSettings.On) {
                    main.screenStart.soundLight.play();
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
                    main.screenStart.soundDoor.play();
                }
                IMAGE = home;
            }
            if (btnBathroom.hit(touch.x, touch.y)){
                if (main.screenSettings.On) {
                    main.screenStart.soundDoor.play();
                }
                IMAGE = bathroom;
            }



            if (touch.x >= 1224 && touch.x <= 1375 && touch.y >= 283 && touch.y <= 337 && isHoveringBedroomCabinet){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenClues.IMAGE = main.screenClues.bedroom;
                main.setScreen(main.screenClues);
            }
            if (touch.x >= 616 && touch.x <= 838 && touch.y >= 562 && touch.y <= 782 && isHoveringKitchenCloset){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenClues.IMAGE = main.screenClues.kitchen;
                main.setScreen(main.screenClues);
            }
            if (touch.x >= 254 && touch.x <= 592 && touch.y >= 586 && touch.y <= 827 && isHoveringKitchenCloset2){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenClues.IMAGE = main.screenClues.kitchen2;
                main.setScreen(main.screenClues);
            }
            if (touch.x >= 1232 && touch.x <= 1372 && touch.y >= 130 && touch.y <= 270 && isHoveringBedroomEmptyCabinet){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenClues.IMAGE = main.screenClues.bedroomEmpty;
                main.setScreen(main.screenClues);
            }
            if (touch.x >= 1164 && touch.x <= 1265 && touch.y >= 111 && touch.y <= 432 && isHoveringHomeMail){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenClues.IMAGE = main.screenClues.mail;
                main.setScreen(main.screenClues);
            }
            if (touch.x >= 344 && touch.x <= 571 && touch.y >= 187 && touch.y <= 267 && isHoveringKitchenBox1){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenClues.IMAGE = main.screenClues.kitchenBox1;
                main.setScreen(main.screenClues);
            }
            if (touch.x >= 578 && touch.x <= 767 && touch.y >= 225 && touch.y <= 279 && isHoveringKitchenBox2){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenClues.IMAGE = main.screenClues.kitchenBox2;
                main.setScreen(main.screenClues);
            }
            if (touch.x >= 805 && touch.x <= 940 && touch.y >= 242 && touch.y <= 268 && isHoveringKitchenBox3){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenClues.IMAGE = main.screenClues.kitchenBox3;
                main.setScreen(main.screenClues);
            }
            if (touch.x >= 1321 && touch.x <= 1554 && touch.y >= 177 && touch.y <= 212 && isHoveringKitchenBox4){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenClues.IMAGE = main.screenClues.kitchenBox4;
                main.setScreen(main.screenClues);
            }
            if (touch.x >= 350 && touch.x <= 568 && touch.y >= 0 && touch.y <= 195 && isHoveringKitchenCase1){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenClues.IMAGE = main.screenClues.kitchenCase1;
                main.setScreen(main.screenClues);
            }
            if (touch.x >= 580 && touch.x <= 758 && touch.y >= 11 && touch.y <= 216 && isHoveringKitchenCase2){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenClues.IMAGE = main.screenClues.kitchenCase2;
                main.setScreen(main.screenClues);
            }
            if (touch.x >= 812 && touch.x <= 940 && touch.y >= 50 && touch.y <= 205 && isHoveringKitchenCase3){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenClues.IMAGE = main.screenClues.kitchenCase3;
                main.setScreen(main.screenClues);
            }
            if (touch.x >= 1316 && touch.x <= 1551 && touch.y >= 0 && touch.y <= 131 && isHoveringKitchenCase4){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenClues.IMAGE = main.screenClues.kitchenCase4;
                main.setScreen(main.screenClues);
            }
            if (touch.x >= 975 && touch.x <= 1251 && touch.y >= 21 && touch.y <= 201 && isHoveringKitchenOven){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenClues.IMAGE = main.screenClues.kitchenOven;
                main.setScreen(main.screenClues);
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
            if (btnHideInBath.hit(touch.x, touch.y)){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenClues.IMAGE = main.screenClues.inBath;
                main.setScreen(main.screenClues);

            }

        }
        // events
        insertObject1.moveUp();
        main.Station = "screenHomeSearch";

        if (isCurrentlyHoveringBedroomCabinet) {
            hoverAlpha = Math.min(1f, hoverAlpha + delta * HOVER_SPEED);
        } else {
            hoverAlpha = Math.max(0f, hoverAlpha - delta * HOVER_SPEED);
        }
        if (isCurrentlyHoveringBedroomEmptyCabinet) {
            hoverAlpha1 = Math.min(1f, hoverAlpha1 + delta * HOVER_SPEED);
        } else {
            hoverAlpha1 = Math.max(0f, hoverAlpha1 - delta * HOVER_SPEED);
        }
        if (isCurrentlyHoveringBedroomBed) {
            hoverAlpha2 = Math.min(1f, hoverAlpha2 + delta * HOVER_SPEED);
        } else {
            hoverAlpha2 = Math.max(0f, hoverAlpha2 - delta * HOVER_SPEED);
        }
        if (isCurrentlyHoveringBedroomLight) {
            hoverAlpha3 = Math.min(1f, hoverAlpha3 + delta * HOVER_SPEED);
        } else {
            hoverAlpha3 = Math.max(0f, hoverAlpha3 - delta * HOVER_SPEED);
        }
        if (isCurrentlyHoveringKitchenCloset) {
            hoverAlpha4 = Math.min(1f, hoverAlpha4 + delta * HOVER_SPEED);
        } else {
            hoverAlpha4 = Math.max(0f, hoverAlpha4 - delta * HOVER_SPEED);
        }
        if (isCurrentlyHoveringKitchenCloset2) {
            hoverAlpha5 = Math.min(1f, hoverAlpha5 + delta * HOVER_SPEED);
        } else {
            hoverAlpha5 = Math.max(0f, hoverAlpha5 - delta * HOVER_SPEED);
        }
        if (isCurrentlyHoveringKitchenCase1) {
            hoverAlpha6 = Math.min(1f, hoverAlpha6 + delta * HOVER_SPEED);
        } else {
            hoverAlpha6 = Math.max(0f, hoverAlpha6 - delta * HOVER_SPEED);
        }
        if (isCurrentlyHoveringKitchenCase2) {
            hoverAlpha7 = Math.min(1f, hoverAlpha7 + delta * HOVER_SPEED);
        } else {
            hoverAlpha7 = Math.max(0f, hoverAlpha7 - delta * HOVER_SPEED);
        }
        if (isCurrentlyHoveringKitchenCase3) {
            hoverAlpha8 = Math.min(1f, hoverAlpha8 + delta * HOVER_SPEED);
        } else {
            hoverAlpha8 = Math.max(0f, hoverAlpha8 - delta * HOVER_SPEED);
        }
        if (isCurrentlyHoveringKitchenCase4) {
            hoverAlpha9 = Math.min(1f, hoverAlpha9 + delta * HOVER_SPEED);
        } else {
            hoverAlpha9 = Math.max(0f, hoverAlpha9 - delta * HOVER_SPEED);
        }
        if (isCurrentlyHoveringKitchenBox1) {
            hoverAlpha10 = Math.min(1f, hoverAlpha10 + delta * HOVER_SPEED);
        } else {
            hoverAlpha10 = Math.max(0f, hoverAlpha10 - delta * HOVER_SPEED);
        }
        if (isCurrentlyHoveringKitchenBox2) {
            hoverAlpha11 = Math.min(1f, hoverAlpha11 + delta * HOVER_SPEED);
        } else {
            hoverAlpha11 = Math.max(0f, hoverAlpha11 - delta * HOVER_SPEED);
        }
        if (isCurrentlyHoveringKitchenBox3) {
            hoverAlpha12 = Math.min(1f, hoverAlpha12 + delta * HOVER_SPEED);
        } else {
            hoverAlpha12 = Math.max(0f, hoverAlpha12 - delta * HOVER_SPEED);
        }
        if (isCurrentlyHoveringKitchenBox4) {
            hoverAlpha13 = Math.min(1f, hoverAlpha13 + delta * HOVER_SPEED);
        } else {
            hoverAlpha13 = Math.max(0f, hoverAlpha13 - delta * HOVER_SPEED);
        }
        if (isCurrentlyHoveringKitchenOven) {
            hoverAlpha14 = Math.min(1f, hoverAlpha14 + delta * HOVER_SPEED);
        } else {
            hoverAlpha14 = Math.max(0f, hoverAlpha14 - delta * HOVER_SPEED);
        }
        if (isCurrentlyHoveringBathroomBath) {
            hoverAlpha15 = Math.min(1f, hoverAlpha15 + delta * HOVER_SPEED);
        } else {
            hoverAlpha15 = Math.max(0f, hoverAlpha15 - delta * HOVER_SPEED);
        }
        if (isCurrentlyHoveringHomeMail) {
            hoverAlpha16 = Math.min(1f, hoverAlpha16 + delta * HOVER_SPEED);
        } else {
            hoverAlpha16 = Math.max(0f, hoverAlpha16 - delta * HOVER_SPEED);
        }






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
                if (isHoveringBedroomCabinet){
                    if (hoverAlpha > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha);
                        batch.draw(imgBedroomCabinet, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else if (isHoveringBedroomEmptyCabinet) {
                    if (hoverAlpha1 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha1);
                        batch.draw(imgBedroomEmptyCabinet, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else if (isHoveringBedroomBed) {
                    if (hoverAlpha2 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha2);
                        batch.draw(imgBedroomBed, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        btnHide.font.draw(batch, btnHide.text, btnHide.x, btnHide.y);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else if (isHoveringBedroomLight){
                    if (hoverAlpha3 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha3);
                        batch.draw(imgBedroomLight, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        btnTurnLight.font.draw(batch, btnTurnLight.text, btnTurnLight.x, btnTurnLight.y);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else {
                    batch.draw(imgBedroom, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                }
            }
            else{
                if (isHoveringBedroomCabinet){
                    if (hoverAlpha > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha);
                        batch.draw(imgBedroomCabinetBlack, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else if (isHoveringBedroomEmptyCabinet) {
                    if (hoverAlpha1 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha1);
                        batch.draw(imgBedroomEmptyCabinetBlack, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else if (isHoveringBedroomBed) {
                    if (hoverAlpha2 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha2);
                        batch.draw(imgBedroomBedBlack, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        btnHide.font.draw(batch, btnHide.text, btnHide.x, btnHide.y);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else if (isHoveringBedroomLight){
                    if (hoverAlpha3 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha3);
                        batch.draw(imgBedroomLightBlack, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        btnTurnLight.font.draw(batch, btnTurnLight.text, btnTurnLight.x, btnTurnLight.y);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else {
                    batch.draw(imgBedroomBlack, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                }
            }
            btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);
            fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);

        }
        if (IMAGE == kitchen) {
            if (!black) {
                if (isHoveringKitchenCloset){
                    if (hoverAlpha4 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha4);
                        batch.draw(imgKitchenCloset, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else if (isHoveringKitchenCloset2){
                    if (hoverAlpha5 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha5);
                        batch.draw(imgKitchenCLoset2, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else if (isHoveringKitchenCase1){
                    if (hoverAlpha6 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha6);
                        batch.draw(imgKitchenCase1, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else if (isHoveringKitchenCase2){
                    if (hoverAlpha7 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha7);
                        batch.draw(imgKitchenCase2, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else if (isHoveringKitchenCase3){
                    if (hoverAlpha8 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha8);
                        batch.draw(imgKitchenCase3, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else if (isHoveringKitchenCase4){
                    if (hoverAlpha9 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha9);
                        batch.draw(imgKitchenCase4, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else if (isHoveringKitchenBox1){
                    if (hoverAlpha10 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha10);
                        batch.draw(imgKitchenBox1, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else if (isHoveringKitchenBox2){
                    if (hoverAlpha11 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha11);
                        batch.draw(imgKitchenBox2, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else if (isHoveringKitchenBox3){
                    if (hoverAlpha12 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha12);
                        batch.draw(imgKitchenBox3, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else if (isHoveringKitchenBox4){
                    if (hoverAlpha13 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha13);
                        batch.draw(imgKitchenBox4, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else if (isHoveringKitchenOven){
                    if (hoverAlpha14 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha14);
                        batch.draw(imgKitchenOven, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else{
                    batch.draw(imgKitchen, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                }
            }
            else{
                if (isHoveringKitchenCloset){
                    if (hoverAlpha4 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha4);
                        batch.draw(imgKitchenClosetBlack, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else if (isHoveringKitchenCloset2){
                    if (hoverAlpha5 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha5);
                        batch.draw(imgKitchenCLoset2Black, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else if (isHoveringKitchenCase1){
                    if (hoverAlpha6 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha6);
                        batch.draw(imgKitchenCase1Black, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else if (isHoveringKitchenCase2){
                    if (hoverAlpha7 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha7);
                        batch.draw(imgKitchenCase2Black, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else if (isHoveringKitchenCase3){
                    if (hoverAlpha8 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha8);
                        batch.draw(imgKitchenCase3Black, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else if (isHoveringKitchenCase4){
                    if (hoverAlpha9 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha9);
                        batch.draw(imgKitchenCase4Black, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else if (isHoveringKitchenBox1){
                    if (hoverAlpha10 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha10);
                        batch.draw(imgKitchenBox1Black, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else if (isHoveringKitchenBox2){
                    if (hoverAlpha11 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha11);
                        batch.draw(imgKitchenBox2Black, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else if (isHoveringKitchenBox3){
                    if (hoverAlpha12 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha12);
                        batch.draw(imgKitchenBox3Black, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else if (isHoveringKitchenBox4){
                    if (hoverAlpha13 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha13);
                        batch.draw(imgKitchenBox4Black, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else if (isHoveringKitchenOven){
                    if (hoverAlpha14 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha14);
                        batch.draw(imgKitchenOvenBlack, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else {
                    batch.draw(imgKitchenBlack, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                }
            }
            btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);
            fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);
        }


        if (IMAGE == home) {
            if (!black) {
                if(isCurrentlyHoveringHomeMail){
                    if (hoverAlpha16 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha16);
                        batch.draw(imgHomeMail, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        batch.setColor(1, 1, 1, 1);
                    }
                }

                else{
                    batch.draw(imgHome, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                }
            }
            else{
                if(isCurrentlyHoveringHomeMail){
                    if (hoverAlpha16 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha16);
                        batch.draw(imgHomeMailBlack, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        batch.setColor(1, 1, 1, 1);
                    }
                }
                else {
                    batch.draw(imgHomeBlack, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                }
            }

            fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);
            btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);

        }
        if (IMAGE == bathroom){
            if (!black) {
                if (isCurrentlyHoveringBathroomBath){
                    if (hoverAlpha15 > 0.01f) {
                        batch.setColor(1, 1, 1, hoverAlpha15);
                        batch.draw(imgBathroomBath, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                        btnHideInBath.font.draw(batch, btnHideInBath.text, btnHideInBath.x, btnHideInBath.y);
                        batch.setColor(1, 1, 1, 1);

                    }
                }
                else{
                    batch.draw(imgBathroom, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                }
            }
            else{
                if (hoverAlpha15 > 0.01f) {
                    batch.setColor(1, 1, 1, hoverAlpha15);
                    batch.draw(imgBathroomBathBlack, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                    btnHideInBath.font.draw(batch, btnHideInBath.text, btnHideInBath.x, btnHideInBath.y);
                    batch.setColor(1, 1, 1, 1);
                }
                else{
                batch.draw(imgBathroomBlack, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                }
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
                fontMessageBig.draw(batch, "    Возможно что-то\n        изменилось\n Нужно осмотреть дом", 950, 580);
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
                fontMessageBig.draw(batch, "Что это за странные \n        записки.. \n Лара написала SMS", 1029, 628);
            }
            btnPhone.font.draw(batch, btnPhone.text, btnPhone.x, btnPhone.y);
            if (main.screenPhone.nextChapter){
                btnNextChapter.font.draw(batch, btnNextChapter.text, btnNextChapter.x, btnNextChapter.y);

            }

        }
        else {
            fontPodarok.draw(batch, "Цель: найти 3 улики,\n которые могут помочь", 1200, 800);
        }
        batch.draw(imgBackpack,  234, 15, 120, 90);
        fontPodarok.draw(batch, "Банк " + main.Bank, 1500, 850);

        if (next){
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(0, 0, 0, alpha);
            shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            shapeRenderer.end();

            alpha += delta * 0.6f;

            if (alpha >= 1) {
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
        imgBedroomCabinetBlack.dispose();
        imgBedroomEmptyCabinet.dispose();
        imgBathroom.dispose();
        imgEmily.dispose();
        imgBedroomCabinet.dispose();
        imgKitchenCloset.dispose();
        imgBedroomLight.dispose();
        imgBedroomEmptyCabinet.dispose();
        imgBedroomBed.dispose();
        imgKitchenCLoset2.dispose();
        imgKitchenCase1.dispose();
        imgKitchenCase2.dispose();
        imgKitchenCase3.dispose();
        imgKitchenCase4.dispose();
        imgKitchenBox1.dispose();
        imgKitchenBox2.dispose();
        imgKitchenBox3.dispose();
        imgKitchenBox4.dispose();
        imgKitchenOven.dispose();
        imgBathroomBath.dispose();
        imgHomeMail.dispose();
        imgBedroomCabinetBlack.dispose();
        imgKitchenClosetBlack.dispose();
        imgBedroomLightBlack.dispose();
        imgBedroomEmptyCabinetBlack.dispose();
        imgBedroomBedBlack.dispose();
        imgKitchenCLoset2Black.dispose();
        imgKitchenCase1Black.dispose();
        imgKitchenCase2Black.dispose();
        imgKitchenCase3Black.dispose();
        imgKitchenCase4Black.dispose();
        imgKitchenBox1Black.dispose();
        imgKitchenBox2Black.dispose();
        imgKitchenBox3Black.dispose();
        imgKitchenBox4Black.dispose();
        imgKitchenOvenBlack.dispose();
        imgBathroomBathBlack.dispose();
        imgHomeMailBlack.dispose();
        imgBedroomBlack.dispose();
        imgHallwayBlack.dispose();
        imgKitchenBlack.dispose();
        imgBathroomBlack.dispose();
        imgHomeBlack.dispose();
        imgInsert.dispose();
        imgBackpack.dispose();
        imgPhone.dispose();
        batch.dispose();
        font.dispose();
        fontScroll.dispose();
        fontPodarok.dispose();
        fontMessageBig.dispose();


    }
}
