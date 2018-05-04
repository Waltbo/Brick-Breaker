/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.scene.shape.Rectangle;

/**
 * @author Goat
 */
public class Paddle extends Rectangle {

    public static final int PADDLE_WIDTH = 70;
    public static final int PADDLE_HEIGHT = 10;
    private int BASE_Y;
    private int paHeight;
    private int paWidth;

    public Paddle(int paWidth, int paHeight) {
        this.paWidth = paWidth;
        this.paHeight = paHeight;
        this.setWidth(PADDLE_WIDTH);
        this.setHeight(PADDLE_HEIGHT);
        BASE_Y = 580;
        move(paWidth / 2);

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

    public void move(double xLoc) {
        setY(BASE_Y);
        setX(xLoc - (PADDLE_WIDTH / 2));

    }

}
