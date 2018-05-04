/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

/**
 * @author Goat
 */
public class Brick extends javafx.scene.shape.Rectangle {

    public static final int BRICK_WIDTH = 35;
    public static final int BRICK_HEIGHT = 20;
    private int pointValue;
    public Brick(int xLoc, int yLoc) {
        setX(xLoc);
        setY(yLoc);
        this.setWidth(BRICK_WIDTH);
        this.setHeight(BRICK_HEIGHT);
        pointValue = 0;
    }

    /**
     * @return the pointValue
     */
    public int getPointValue() {
        return pointValue;
    }

    /**
     * @param pointValue the pointValue to set
     */
    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }

}
