/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import brickbreakerstudent.BrickRow;
import brickbreakerstudent.Level;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import static sample.Brick.BRICK_HEIGHT;
import static sample.Brick.BRICK_WIDTH;

/**
 * @author Goat
 */
public class PlayArea extends BorderPane {

    private Brick[][] bricks;
    private int BASE_Y;
    private int paHeight;
    private int paWidth;
    private Paddle paddle;
    private Ball ball;
    private ScorePane scoreboard;
    public PlayArea(int paHeight, int paWidth, Level level) {
        this.paHeight = paHeight;
        this.paWidth = paWidth;
        BASE_Y = 10;
        createBricks(level);
        this.setPrefSize(paWidth, paHeight);
        paddle = new Paddle(paWidth, paHeight);
        System.out.println(paddle.getX() + " " + paddle.getY());
        ball = new Ball(550, 550, 550);
        ball.setCenterX(paddle.getX() + (paddle.getWidth() / 2));
        ball.setCenterY(paddle.getY() - ball.getRadius());
        ball.setDirection(Math.random() * 100);
        setBallVisibility(false);
        Pane playArea = new Pane();
        playArea.getChildren().addAll(paddle, ball);
        this.setCenter(playArea);
        scoreboard = new ScorePane();
        this.setBottom(scoreboard);

    }

    /**
     * @return the BASE_Y
     */
    public int getBASE_Y() {
        return BASE_Y;
    }

    /**
     * @param BASE_Y the BASE_Y to set
     */
    public void setBASE_Y(int BASE_Y) {
        this.BASE_Y = BASE_Y;
    }

    /**
     * @return the paHeight
     */
    public int getPaHeight() {
        return paHeight;
    }

    /**
     * @param paHeight the paHeight to set
     */
    public void setPaHeight(int paHeight) {
        this.paHeight = paHeight;
    }

    /**
     * @return the paWidth
     */
    public int getPaWidth() {
        return paWidth;
    }

    /**
     * @param paWidth the paWidth to set
     */
    public void setPaWidth(int paWidth) {
        this.paWidth = paWidth;
    }

    private void createBricks(Level level) {
        bricks = new Brick[level.getNumBrickRows()][level.getBricksPerRow()];
        for (int i = 0; i < bricks.length; i++) {
            BrickRow brickRow = level.getBrickRow(i);
            for (int j = 0; j < bricks[i].length; j++) {

                if (brickRow.getBrickMaskValue(i)) {
                    Brick brick = new Brick(BRICK_WIDTH * j, BASE_Y + (BRICK_HEIGHT * i));
                    brick.setFill(brickRow.getColor());
                    brick.setStroke(Color.BLACK);
                    bricks[i][j] = brick;
                    this.getChildren().add(brick);
                }
            }
        }
    }

    public void movePaddle(double xLoc) {
        paddle.move(xLoc);
    }

    public void handleCollisions() {

        if (ball.isVisible()) {
            if (ball.getTopEdge() <= 0) {
                ball.setCenterY(ball.getRadius() + 1);
                ball.setDirection(360 - ball.getDirection());
            }

            //check for collision
//                System.out.println(ball.getBottomEdge() + " " + paddle.getY());
            if (ball.getTopEdge() > (paddle.getY() + paddle.getHeight())) {
                ball.setVisible(false);
            }

            //check for collision
            if (ball.getCenterX() - ball.getRadius() <= 0) {
                ball.setCenterX((ball.getRadius() + 1));
                if (ball.getDirection() < 180) {
                    ball.setDirection(90 + (90 - ball.getDirection()));
                } else {
                    ball.setDirection(270 + (270 - ball.getDirection()));
                }
            }

            //check for collision
            if (ball.getCenterX() + ball.getRadius() >= paWidth) {
                ball.setCenterX(paWidth - (ball.getRadius() + 1));
                if (ball.getDirection() < 180) {
                    ball.setDirection(90 + (90 - ball.getDirection()));
                } else {
                    ball.setDirection(270 + (270 - ball.getDirection()));
                }
            }
            checkCollision();
            brickCollision();

        }
    }

    public void moveBall() {
        ball.move();
    }

    public void newBall() {
        if (ball.isVisible() == false) {
            ball.setCenterX(paddle.getX() + (paddle.getWidth() / 2));
            ball.setCenterY(paddle.getY() - ball.getRadius());
            ball.setDirection(Math.random() * 90);
            ball.setSpeed(8);
        }

    }

    public void setBallVisibility(Boolean visibility) {

        ball.setVisible(visibility);
    }

    public void brickCollision() {
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[i].length; j++) {
                if (bricks[i][j] != null && bricks[i][j].isVisible()) {
                    if ((((ball.getBottomEdge() <= (bricks[i][j].getY() + bricks[i][j].getHeight())) && (ball.getBottomEdge() >= (bricks[i][j].getY())))
                            || ((ball.getTopEdge() <= (bricks[i][j].getY() + bricks[i][j].getHeight())) && (ball.getTopEdge() >= (bricks[i][j].getY()))))
                            && (ball.getLeftEdge() < (bricks[i][j].getX() + bricks[i][j].getWidth())) && (ball.getRightEdge() > bricks[i][j].getX())) {

                        ball.setDirection((360 - ball.getDirection()) * (Math.random() * 10));
                        bricks[i][j].setVisible(false);
                        scoreboard.setScore(scoreboard.getScore() + 10);
                        if (ball.getSpeed() < 50) {
                            ball.setSpeed((int) (ball.getSpeed() * 1.12));

                        }
                        return;

                    }
                }
            }
        }
    }

    public void checkCollision() {
        if ((ball.getBottomEdge() <= paddle.getY() + paddle.getHeight()) && (ball.getBottomEdge() >= paddle.getY()) && (ball.getLeftEdge() < (paddle.getX() + paddle.getWidth())) && (ball.getRightEdge() > paddle.getX())) {

            ball.setCenterY(paddle.getY() - (ball.getRadius() + 1));
            ball.setDirection(360 - ball.getDirection());
            if (ball.getSpeed() < 50) {
                ball.setSpeed((int) (ball.getSpeed() * 1.12));
            }
        }

    }

}
