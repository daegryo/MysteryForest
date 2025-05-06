package ru.samsung.mysteryforest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ScreenPhone implements Screen {
    Main main;

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;
    public BitmapFont fontScroll;
    public BitmapFont fontPodarok;
    public BitmapFont fontMessage;


    public Texture imgPhone;
    public Texture imgPhoneLara;
    public Texture imgPhoneAgata;
    public Texture imgPhoneFamily;
    public Texture imgChoice;

    private SpaceButton btnBack;
    private SpaceButton btnBackToScreen;

    ReadFile readFileLara;
    ReadFile readFileEmilyChoice;
    ReadFile readFileEmilyToAgata;
    ReadFile readFileAgata;

    List<String> messageLara;
    List<String> messageEmilyChoice;
    List<String> messageEmilyToAgata;
    List<String> messageAgata;


    String[] parts, parts1;
    String [][] MessageAgata;
    String [][] MessageEmilyToAgata;

    Map<String, String> order = new HashMap<>();


    int IMAGE = 0;
    int def = 0;
    int Lara = 1;
    int Agata = 2;
    int Family = 3;

    public int STATION = 0;
    int dialog1 = 0;
    int dialog2 = 1;

    boolean choice1 = false;
    boolean choice2 = false;
    boolean choiceAgata1 = false;
    boolean choiceAgata2 = false;
    public boolean nextChapter = false;
    int count = 0;
    int countAgata = 0;
    int cursorAgata = 0;

    public String clas = "";



    public ScreenPhone(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        fontScroll = new BitmapFont(Gdx.files.internal("fonts/scroll.fnt"));
        fontPodarok = new BitmapFont(Gdx.files.internal("fonts/Podarok.fnt"));
        fontMessage = new BitmapFont(Gdx.files.internal("fonts/message.fnt"));

        imgPhone = new Texture("phone/phone2.png");
        imgPhoneLara = new Texture("phone/phoneLara.png");
        imgPhoneAgata = new Texture("phone/phoneAgata.png");
        imgPhoneFamily = new Texture("phone/phoneFamily.png");
        imgChoice = new Texture("text/choice.png");

        btnBack = new SpaceButton(fontPodarok, 667, 82, "back");
        btnBackToScreen = new SpaceButton(fontPodarok, 200, 82, "X");

        readFileLara = new ReadFile("assets/story/messageLara.txt", 1);
        readFileEmilyChoice = new ReadFile("assets/story/messageEmilyChoiceToLara.txt", 1);
        readFileEmilyToAgata = new ReadFile("assets/story/chapter1/messageEmilyToAgata.txt", 1);
        readFileAgata = new ReadFile("assets/story/chapter1/messageAgataToEmily.txt", 1);

        messageLara = readFileLara.reader();
        messageEmilyChoice = readFileEmilyChoice.reader();
        messageEmilyToAgata = readFileEmilyToAgata.reader();
        messageAgata = readFileAgata.reader();
        // to do елефон агата вложенный список чтоб сохранялись всеп сообения
        MessageAgata = new String[messageAgata.size()][2];
        MessageEmilyToAgata = new String[messageEmilyToAgata.size()][4];
        for (int i = 0; i < MessageAgata.length; i++) {
            for (int j = 0; j < MessageAgata[i].length; j++){
                MessageAgata[i][j] = messageAgata.get(i).split("/")[j];
            }
        }
        for (int i = 0; i < MessageEmilyToAgata.length; i++) {
            for (int j = 0; j < MessageEmilyToAgata[i].length; j++){
                MessageEmilyToAgata[i][j] = messageEmilyToAgata.get(i).split("/")[j];
                System.out.println(MessageEmilyToAgata[i][j] + "LLLLLLLLLLLLLLLLLLLL");
            }
        }

        String str = messageEmilyChoice.get(0);
        parts = str.split("/");

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
            if (touch.x >= 659 && touch.x <= 1008 && touch.y >= 622 && touch.y <= 684 && IMAGE == def){
                IMAGE = Lara;
            }
            if (touch.x >= 656 && touch.x <= 1004 && touch.y >= 548 && touch.y <= 615 && IMAGE == def){
                IMAGE = Agata;
            }
            if (touch.x >= 661 && touch.x <= 1007 && touch.y >= 467 && touch.y <= 537 && IMAGE == def){
                IMAGE = Family;
            }
            if (touch.x >= 655 && touch.x <= 1006 && touch.y >= 201 && touch.y <= 242 && !choice2 && STATION == dialog1){
                choice1 = true;
            }
            if (touch.x >= 656 && touch.x <= 1005 && touch.y >= 146 && touch.y <= 193 && !choice1 && STATION == dialog1){
                choice2 = true;
            }
            if (touch.x >= 655 && touch.x <= 1006 && touch.y >= 201 && touch.y <= 242 && !choiceAgata2 && STATION == dialog2){
                choiceAgata1 = true;
            }
            if (touch.x >= 656 && touch.x <= 1005 && touch.y >= 146 && touch.y <= 193 && !choiceAgata1 && STATION == dialog2){
                choiceAgata2 = true;
            }
            if (btnBack.hit(touch.x, touch.y) && IMAGE != def){
                IMAGE = def;
            }
            if (btnBackToScreen.hit(touch.x, touch.y)){
                if (Objects.equals(clas, "ScreenHomeSearch")) {
                    main.setScreen(main.screenHomeSearch);
                }
                if (Objects.equals(clas, "ScreenChapter1")) {
                    main.setScreen(main.screenChapter1);
                }

            }
        }
        //events,

        //paint
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        if (IMAGE == def) {
            batch.draw(imgPhone, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            fontMessage.draw(batch, messageLara.get(0)+ "...", 735, 660);
        }
        if (IMAGE == Lara){
            batch.draw(imgPhoneLara, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            btnBack.font.draw(batch, btnBack.text, btnBack.x, btnBack.y);
            for (int i = 0; i < messageLara.size()-1; i++) {
                fontMessage.draw(batch, messageLara.get(i), 661, 665 + (i * -20));
            }
            if (!choice1 && !choice2) {
                batch.draw(imgChoice, 655, 190, 355, 50);
                batch.draw(imgChoice, 655, 139, 355, 50);

                fontMessage.draw(batch, parts[0], 730, 245);
                fontMessage.draw(batch, parts[1], 765, 170);
            }
            String str1 = messageLara.get(messageLara.size()-1);
            parts1 = str1.split("/");
            if (choice1){

                fontMessage.draw(batch, parts[0], 812, 530);
                if(count < 1){
                main.AttentionLara += Integer.parseInt(parts[2]);
                count++;
                }
                fontMessage.draw(batch, parts1[0], 661, 490);
                nextChapter = true;
            }
            if (choice2){
                fontMessage.draw(batch, parts[1], 840, 510);
                if(count < 1) {
                    main.AttentionLara += Integer.parseInt(parts[3]);
                }
                fontMessage.draw(batch, parts1[1], 661, 490);
                nextChapter = true;
            }
        }
        if (IMAGE == Agata){
            batch.draw(imgPhoneAgata, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            btnBack.font.draw(batch, btnBack.text, btnBack.x, btnBack.y);

            if (Objects.equals(clas, "ScreenChapter1") && STATION == dialog2){
                nextChapter = false;
                if (cursorAgata < MessageAgata.length) {

                    batch.draw(imgChoice, 655, 190, 355, 50);
                    batch.draw(imgChoice, 655, 139, 355, 50);

                    fontMessage.draw(batch, MessageEmilyToAgata[cursorAgata][0], 660, 245);
                    fontMessage.draw(batch, MessageEmilyToAgata[cursorAgata][1], 660, 170);

                    if (choiceAgata1) {
                        //fontMessage.draw(batch, MessageEmilyToAgata[cursorAgata][0], 922, 670);
                        if (countAgata < 1) {
                            main.AttentionAgata += Integer.parseInt(MessageEmilyToAgata[cursorAgata][2]);
                            countAgata++;
                        }
                        //    fontMessage.draw(batch, MessageAgata[cursorAgata][0], 670, 650);
                        String str = "choiceEmily" + cursorAgata;
                        String str1 = "choiceAgata" + cursorAgata;

                        order.put(str, MessageEmilyToAgata[cursorAgata][0]);
                        order.put(str1, MessageAgata[cursorAgata][0]);

                        if (cursorAgata <= MessageAgata.length) {
                            cursorAgata++;
                        }
                        choiceAgata1 = false;
                        choiceAgata2 = false;


                    }
                    if (choiceAgata2) {
                        //   fontMessage.draw(batch, MessageEmilyToAgata[cursorAgata][1], 820, 670);
                        if (countAgata < 1) {
                            main.AttentionAgata += Integer.parseInt(MessageEmilyToAgata[cursorAgata][3]);
                            countAgata++;
                        }
                        // String[] par = MessageAgata[cursorAgata][1].split(":");
                        //String str = "";
                        //for (int i = 0; i < par.length; i++) {
                        //  str += par[i] + "\n";
                        //}
                        //fontMessage.draw(batch, str, 665, 645);
                        String str2 = "choiceEmily" + cursorAgata;
                        String str3 = "choiceAgata" + cursorAgata;
                        System.out.println(str2 + "                " + str3);
                        order.put(str2, MessageEmilyToAgata[cursorAgata][1]);
                        order.put(str3, MessageAgata[cursorAgata][1]);

                        if (cursorAgata <= MessageAgata.length) {
                            cursorAgata++;
                            System.out.println("СУКА ТЫ ДОБАВЛЯЕШЬСЯ ИЛИ НЕТ " + cursorAgata);
                        }
                        choiceAgata1 = false;
                        choiceAgata2 = false;


                    }
                }

                        if (order != null && !order.isEmpty()) {

                            String stroka = " ";
                            String stroka1 = " ";
                            for (int i = 0; i < cursorAgata; i++) {
                                if (i == cursorAgata - 1){
                                    nextChapter = true;
                                }
                                String msgEmily = order.get("choiceEmily" + i);
                                String msgAgata = order.get("choiceAgata" + i);
                                String[] par = msgAgata.split(":");
                                String[] par1 = msgEmily.split(":");

                                for (int j = 0; j < par.length; j++) {
                                  stroka += par[j] + "\n";
                                }
                                for (int j = 0; j < par1.length; j++) {
                                    stroka1 += par1[j] + "\n";
                                }
                                fontMessage.draw(batch, stroka1, 840, 670 + i * -70);
                                fontMessage.draw(batch, stroka, 670, 650 + i * -90);
                                stroka = " ";
                                stroka1 = " ";
                            }
                        }



            }
        }
        if (IMAGE == Family){
            batch.draw(imgPhoneFamily, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            btnBack.font.draw(batch, btnBack.text, btnBack.x, btnBack.y);
        }
        btnBackToScreen.font.draw(batch, btnBackToScreen.text, btnBackToScreen.x, btnBackToScreen.y);


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
        imgPhone.dispose();

    }
}
