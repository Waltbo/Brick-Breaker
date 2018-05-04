/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Goat
 */
public class Main extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        String configFilename = "brickbreaker.txt";
        String profileFilename = "brickbreakerprofiles.txt";

        ProfilePane pfPane = new ProfilePane(profileFilename, configFilename);
        Scene scene = new Scene(pfPane, 800, 100);

        primaryStage.setTitle("Brick Breaker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
