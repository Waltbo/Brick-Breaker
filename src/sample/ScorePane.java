/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * @author Goat
 */
public class ScorePane extends HBox {

    private int score;
    private Label label;

    public ScorePane() {
        score = 0;
        this.score = score;
        this.label = new Label();
        label.setPadding(new Insets(5, 5, 5, 5));
        label.setText("Score: " + score);
        this.getChildren().add(label);
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
        label.setText("Score: " + this.score);
    }

    /**
     * @return the label
     */
    public Label getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(Label label) {
        this.label = label;
    }

    public int incrementScore(int pts) {
        pts = 0;
        score = +pts;
        return score;
    }


}
