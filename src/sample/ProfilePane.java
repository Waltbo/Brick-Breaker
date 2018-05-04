package sample;

import brickbreakerstudent.BrickBreakerIO;
import brickbreakerstudent.GameProfiles;
import brickbreakerstudent.Level;
import brickbreakerstudent.PlayerProfile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static brickbreakerstudent.BrickBreakerIO.readConfigFile;

/**
 * @author Goat
 */
public class ProfilePane extends HBox {

    private GameProfiles profiles;
    private String profileFilename;
    private String configFilename;

    ProfilePane(String profileFilename, String configFilename) {
        this.profileFilename = profileFilename;
        this.configFilename = configFilename;

        profiles = new GameProfiles();

        BrickBreakerIO.readProfiles(profiles, profileFilename);

        Button newUser = new Button();
        newUser.setText("Create New Player");
        Label nameLabel = new Label("Enter New User Name");
        TextField nameText = new TextField();
        ComboBox comboBox = new ComboBox();

        for (int i = 0; i < profiles.getNumProfiles(); i++) {
            comboBox.getItems().add(profiles.getProfile(i).getName());
        }

        Button searchPlayer = new Button();
        searchPlayer.setText("Search for Player");
        Text status = new Text(10, 50, "Status: ");
        searchPlayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (int i = 0; i < profiles.getNumProfiles(); i++) {
                    if (profiles.getProfile(i).getName().equals(comboBox.getItems().get(i))) {
                        startGameBoard();
                        return;
                    } else {
                        status.setText("Status: This name could not be found");
                    }
                }
            }
        });
        newUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (nameText.getText().trim().isEmpty()) {
                    status.setText("Status: Must enter a name");
                    return;
                }
                for (int i = 0; i < profiles.getNumProfiles(); i++) {
                    if (profiles.getProfile(i).getName().equals(nameText.getText().trim())) {
                        status.setText("Status: Player name already taken");
                        return;
                    }
                }
                PlayerProfile guiProfile = new PlayerProfile(nameText.getText().trim());
                profiles.addProfile(guiProfile);
                StackPane info = new StackPane();
                TextArea listOfProfiles = new TextArea();
                Scene stuff = new Scene(info);
                info.getChildren().add(listOfProfiles);
                String profileStuff = guiProfile.toString();
                for (int i = 0; i < profiles.getNumProfiles(); i++) {
                    profileStuff += System.lineSeparator() + profiles.getProfile(i);
                }
                listOfProfiles.setText(profileStuff);
                Stage selectedProfiles = new Stage();
                selectedProfiles.setScene(stuff);
                selectedProfiles.show();
            }

        });

        getChildren().addAll(nameLabel, nameText, newUser, comboBox, searchPlayer, status);
        setSpacing(10);
        setPrefWidth(1000);

    }

    /**
     * @return the profiles
     */
    public GameProfiles getProfiles() {
        return profiles;
    }

    /**
     * @param profiles the profiles to set
     */
    public void setProfiles(GameProfiles profiles) {
        this.profiles = profiles;
    }

    /**
     * @return the profileFilename
     */
    public String getProfileFilename() {
        return profileFilename;
    }

    /**
     * @param profileFilename the profileFilename to set
     */
    public void setProfileFilename(String profileFilename) {
        this.profileFilename = profileFilename;
    }

    /**
     * @return the configFilename
     */
    public String getConfigFilename() {
        return configFilename;
    }

    /**
     * @param configFilename the configFilename to set
     */
    public void setConfigFilename(String configFilename) {
        this.configFilename = configFilename;
    }

    public void startGameBoard() {
        Level[] levels = readConfigFile(configFilename);
        this.setVisible(false); //do not display the ProfilePane any lo0nger.
        GameBoard gameBoard = new GameBoard(levels, profiles, this.profileFilename);
        Scene gameScene = new Scene(gameBoard);
        Stage gameStage = new Stage();
        gameStage.setScene(gameScene);
        gameStage.setTitle("Brick Breaker");
        gameStage.show();
    }

}
