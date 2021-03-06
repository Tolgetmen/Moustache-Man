package com.Scenes;

import com.MainClass.game.MainClass;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.concurrent.CountDownLatch;

import javax.swing.plaf.TextUI;


public class Hud implements Disposable {
    public Stage hudStage;
    private Viewport viewport;
    private ImageButton button;
    ImageButton.ImageButtonStyle imageButtonStyle;


    public Integer worldTimer;
    public float timeCount;
    private static Integer score;

    private Label countdownLabel;
    private static Label scoreLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label manLabel;
    Texture buttonUp;
    Texture buttonRight;
    Texture buttonLeft;
    TextureRegionDrawable up;
    TextureRegionDrawable left;
    TextureRegionDrawable right;
    boolean upPressed;
    boolean leftPressed;
    boolean rightPressed;
    Table controls;
    Table jump;


    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public Hud(SpriteBatch sb) {
        controls = new Table();
        controls.left().bottom();


        worldTimer = 300;
        timeCount = 0;
        score = 0;

        viewport = new FitViewport(MainClass.V_Width, MainClass.V_Height, new OrthographicCamera());
        hudStage = new Stage(viewport);
        Gdx.input.setInputProcessor(hudStage);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countdownLabel = new Label(String.format("%10d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%10d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        manLabel = new Label("MOUSTACHE MAN", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(manLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);

        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countdownLabel).expandX();

        buttonLeft = new Texture("leftButton.png");
        buttonRight = new Texture("rightButton.png");
        buttonUp = new Texture("upButton.png");


        Image upImg = new Image(buttonUp);
        upImg.setSize(200, 200);
        upImg.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                upPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                upPressed = false;

            }
        });

        Image leftImg = new Image(buttonLeft);
        leftImg.setSize(200, 200);
        leftImg.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = false;

            }
        });


        Image rightImg = new Image(buttonRight);
        rightImg.setSize(200, 200);
        rightImg.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = false;

            }
        });


        controls.row().pad(5, 5, 5, 5);
        controls.add(leftImg).size(leftImg.getWidth(), leftImg.getHeight());
        controls.add();
        controls.add(rightImg).size(rightImg.getWidth(), rightImg.getHeight());

        upImg.setPosition(hudStage.getWidth() - 200, 0);




        hudStage.addActor(table);
        hudStage.addActor(controls);
        hudStage.addActor(upImg);





    }

    public void update(float dt) {
        timeCount += dt;
        if (timeCount >= 1) {
            worldTimer--;
            countdownLabel.setText(String.format("%10d", worldTimer));
            timeCount = 0;
        }

        if (timeCount <= 0){

        }
    }

    public static void addscore(int value){
        score+= value;
        scoreLabel.setText(String.format("%10d", score));

    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }




    @Override
    public void dispose() {
        hudStage.dispose();
    }


    public int score(){
        return score;
    }
}
