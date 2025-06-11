package ru.samsung.mysteryforest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.sql.SQLException;
import java.util.Objects;

public class ScreenRegistration implements Screen {
    Main main;

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;
    public BitmapFont fontPodarok;
    public BitmapFont fontMessageBig;

    Texture imgBg1;
    Texture imgTap;

    SpaceButton btnLogin;
    SpaceButton btnRegister; // Новая кнопка регистрации
    SpaceButton btnSettings;

    // Поля для ввода данных
    TextField txtUsername;
    TextField txtPassword;
    Stage stage;

    ShapeRenderer shapeRenderer;
    float alpha = 0f;
    boolean next = false;
    float centerX = Main.SCR_WIDTH / 2 - 150;

    public ScreenRegistration(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        fontPodarok = new BitmapFont(Gdx.files.internal("fonts/Podarok.fnt"));
        fontMessageBig = new BitmapFont(Gdx.files.internal("fonts/bundle.fnt"));
        camera.setToOrtho(false, Main.SCR_WIDTH, Main.SCR_HEIGHT);

        imgBg1 = new Texture("bg/screenGame1.png");
        imgTap = new Texture("text/choice.png");



        btnLogin = new SpaceButton(fontPodarok, centerX, 350, "Войти");
        btnRegister = new SpaceButton(fontPodarok, centerX, 300, "Зарегистрироваться");
        btnSettings = new SpaceButton(fontPodarok, 1500, 50, "settings");
        shapeRenderer = new ShapeRenderer();

        // Инициализация полей ввода

        stage = new Stage(new FitViewport(Main.SCR_WIDTH, Main.SCR_HEIGHT));
        Gdx.input.setInputProcessor(stage);

        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = fontMessageBig;
        style.fontColor = Color.WHITE;

        txtUsername = new TextField("", style);
        txtUsername.setMessageText("Логин");
        txtUsername.setPosition(centerX, 450);
        txtUsername.setSize(300, 40);



        txtPassword = new TextField("", style);
        txtPassword.setMessageText("Пароль");
        txtPassword.setPasswordMode(true);
        txtPassword.setPasswordCharacter('*');
        txtPassword.setPosition(centerX, 400);
        txtPassword.setSize(300, 40);






        stage.addActor(txtUsername);
        stage.addActor(txtPassword);
    }

    @Override
    public void render(float delta) {
        // touches
        if (Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            System.out.println(touch.x + " " + touch.y);

            if (btnLogin.hit(touch.x, touch.y)) {

                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                // Проверка входа
                if(validateLogin()) {
                    next = true;
                }
            }

            if (btnRegister.hit(touch.x, touch.y)) {
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                // Регистрация пользователя
                try {
                    registerUser();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (btnSettings.hit(touch.x, touch.y)){
                if (main.screenSettings.On) {
                    main.screenStart.soundClick.play();
                }
                main.screenSettings.back = "ScreenRegistration";
                main.setScreen(main.screenSettings);
            }
        }

        // Отрисовка
        batch.begin();
        batch.setProjectionMatrix(camera.combined);

        batch.draw(imgBg1, 0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
        batch.draw(imgTap, 590, 445,236, 40);
        batch.draw(imgTap, 590, 396 ,236, 40);
      //  font.draw(batch, "Войти в аккаунт", 531, 465);
        btnLogin.font.draw(batch, btnLogin.text, btnLogin.x, btnLogin.y);
        btnRegister.font.draw(batch, btnRegister.text, btnRegister.x, btnRegister.y);
        btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);
        // Отрисовка полей ввода
        batch.end();
        stage.act(delta);
        stage.draw();
        batch.begin();

        if (next){
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(0, 0, 0, alpha);
            shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            shapeRenderer.end();

            alpha += delta * 0.6f;
            if (alpha >= 1) {
                main.dbHelper.updateInformation(main.Id);
                if (Objects.equals(main.Station, "screenHistory")) {
                    main.setScreen(main.screenHistory);
                }
                if (Objects.equals(main.Station, "screenHomeSearch")) {
                    main.setScreen(main.screenHomeSearch);
                }
                if (Objects.equals(main.Station, "screenChapter1")) {
                    main.setScreen(main.screenChapter1);
                }
                if (Objects.equals(main.Station, "screenCar")) {
                    main.setScreen(main.screenCar);
                }
                if (Objects.equals(main.Station, "screenCarGame")) {
                    main.setScreen(main.screenCarGame);
                }
                if (Objects.equals(main.Station, "screenRiver")) {
                    main.setScreen(main.screenRiver);
                }
                if (Objects.equals(main.Station, "screenMia")) {
                    main.setScreen(main.screenMia);
                }
                if (Objects.equals(main.Station, "screenStore")) {
                    main.setScreen(main.screenMia);
                }
                if (Objects.equals(main.Station, "screenEnd")) {
                    main.setScreen(main.screenEnd);
                }


                //else {
            //        main.setScreen(main.screenHistory);

            //    }

            }
        }

        batch.end();


    }

    private boolean validateLogin() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        // Проверка в базе данных
        if(main.dbHelper.checkUser(username, password)) {
            return true;
        } else {
            // Показать сообщение об ошибке
            return false;
        }
    }

    private void registerUser() throws SQLException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if(username.isEmpty() || password.isEmpty()) {
            // Показать сообщение об ошибке
            return;
        }

        // Проверка, существует ли пользователь
        if(main.dbHelper.userExists(username)) {
            // Показать сообщение, что пользователь существует
            return;
        }

        // Регистрация нового пользователя
        long id = main.dbHelper.addUser(username, password);
        if(id != -1) {
            // Показать сообщение об успешной регистрации
            System.out.println("User registered with ID: " + id);
        } else {
            // Показать сообщение об ошибке регистрации
        }
    }

    // Остальные методы остаются без изменений
    @Override
    public void show() {}
    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
        stage.getViewport().update(width, height, true);
        // возможно, нужно обновить размеры текстовых полей
        txtUsername.setSize(300, 40);
        txtPassword.setSize(300, 40);
    }
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
    @Override
    public void dispose() {
        batch.dispose();
        imgBg1.dispose();
        font.dispose();
        stage.dispose();
        batch.dispose();
        font.dispose();
        fontPodarok.dispose();
        fontMessageBig.dispose();
        imgTap.dispose();

    }
}
