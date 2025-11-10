package game.scenes.messageScene;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static game.utils.AppParameters.appInitialWidth;
import static game.utils.Assets.StyleSheet;
import static game.utils.Assets.customFont;

public class MessageScene {
    public Scene getScene(Stage stage, Scene alternativeScene, String message, String btnText){

        Label sceneMessage = new Label(message);
        sceneMessage.setFont(customFont);
        sceneMessage.getStyleClass().add("message");

        Button sceneButton = new Button(btnText);
        sceneButton.setFont(customFont);
        sceneButton.getStyleClass().add("btn");

        VBox componentContainer = new VBox(50, sceneMessage, sceneButton);
        componentContainer.setAlignment(Pos.CENTER);
        componentContainer.getStyleClass().add("vbox");

        Scene scene = new Scene(componentContainer, appInitialWidth, appInitialWidth);
        scene.getStylesheets().add(StyleSheet);

        sceneButton.setOnAction(_ -> stage.setScene(alternativeScene));

        return scene;
    }
}
