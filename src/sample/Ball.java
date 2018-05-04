/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.scene.shape.Circle;

/**
 * @author Goat
 */
public class Ball extends Circle {

    private int speed;
    private double direction;

    public Ball(int paWidth, int paHeight, double dir) {
        setCenterX(paWidth / 2);
        setCenterY(paHeight / 2);
        direction = dir;
        speed = 8;
        setRadius(9);
    }

    /**
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * @return the direction
     */
    public double getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(double direction) {
        this.direction = direction;
    }

    public void move() {
        this.setCenterX(this.getCenterX() + speed * Math.cos(direction * (Math.PI * 2) / 360));
        this.setCenterY(this.getCenterY() + speed * Math.sin(direction * (Math.PI * 2) / 360));

    }

    public int getTopEdge() {
        return (int) (getCenterY() - getRadius());
    }

    public int getBottomEdge() {
        return (int) (getCenterY() + getRadius());
    }

    public int getLeftEdge() {
        return (int) (getCenterX() - getRadius());
    }

    public int getRightEdge() {
        return (int) (getCenterX() + getRadius());
    }

}
