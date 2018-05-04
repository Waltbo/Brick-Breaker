/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import brickbreakerstudent.GameProfiles;
import brickbreakerstudent.Level;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * @author Goat
 */
public class GameBoard extends BorderPane {

    private PlayArea playArea;
    private GameProfiles profiles;
    private int currentLevel;
    private Level[] levels;
    private String profilesFilename;
    private PaddleHandler paddleHandler;
    private BallHandler ballHandler;
    private Timer timer;
    private Ball ball;

    public GameBoard(Level[] levels, GameProfiles profiles, String profilesFilename) {
        this.profilesFilename = profilesFilename;
        this.levels = levels;
        this.profiles = profiles;
        currentLevel = 0;
        playArea = new PlayArea(630, 700, levels[currentLevel]);
        paddleHandler = new PaddleHandler();
        ballHandler = new BallHandler();
        timer = new Timer();
        this.setOnMouseMoved(paddleHandler);
        this.setOnMouseClicked(ballHandler);
        setCenter(playArea);


    }

    public class PaddleHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            playArea.movePaddle(event.getX());
        }

    }

    public class BallHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {

            timer.start();
            playArea.newBall();

        }

    }

    private class Timer extends AnimationTimer {

        private long previous = 0;


        public void handle(long now) {
            if (now - previous >= 200000) {
                playArea.moveBall();
                playArea.setBallVisibility(true);


                playArea.handleCollisions();

            }

        }
    }
}
