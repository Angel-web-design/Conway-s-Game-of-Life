package game.menuScene;

import game.gameScene.GameScene;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static game.utils.AppParameters.appInitialWidth;
import static game.utils.Assets.*;

public class MenuScene {
    public Scene getMenuScene(Stage stage){
        Canvas canvas = new Canvas(appInitialWidth, (double) appInitialWidth /3);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setImageSmoothing(false);

        gc.drawImage(
                bannerImage,
                (canvas.getWidth() - bannerImage.getWidth()) / 2,
                (canvas.getHeight() - bannerImage.getHeight()) / 2,
                bannerImage.getWidth(),
                bannerImage.getHeight());

        Button playButton = new Button("Start");
        playButton.setFont(customFont);
        playButton.getStyleClass().add("btn");

        Spinner<Integer> fpsInput = new Spinner<>();
        fpsInput.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 60, 10)); // min, max, initial
        fpsInput.setEditable(true); // permitir escribir directamente
        fpsInput.setPrefWidth(150);
        fpsInput.getEditor().setFont(customFont);
        SpinnerValueFactory.IntegerSpinnerValueFactory fpsInputValueFactory =
                (SpinnerValueFactory.IntegerSpinnerValueFactory) fpsInput.getValueFactory();
        fpsInputValueFactory.setAmountToStepBy(5);

        Spinner<Integer> simulationSizeInput = new Spinner<>();
        simulationSizeInput.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 100, 80)); // min, max, initial
        simulationSizeInput.setEditable(true); // permitir escribir directamente
        simulationSizeInput.setPrefWidth(150);
        simulationSizeInput.getEditor().setFont(customFont);
        SpinnerValueFactory.IntegerSpinnerValueFactory simulationSizeInputValueFactory =
                (SpinnerValueFactory.IntegerSpinnerValueFactory) simulationSizeInput.getValueFactory();
        simulationSizeInputValueFactory.setAmountToStepBy(5);

        Label fpsInputLabel = new Label("Simulation's FPS:");
        fpsInputLabel.setLabelFor(fpsInputLabel);
        fpsInputLabel.setFont(customFont);
        fpsInputLabel.getStyleClass().add("label-style");

        Label simulationSizeInputLabel = new Label("Grid Size:");
        simulationSizeInputLabel.setLabelFor(simulationSizeInput);
        simulationSizeInputLabel.setFont(customFont);
        simulationSizeInputLabel.getStyleClass().add("label-style");

        HBox inputContainer = new HBox(20, fpsInputLabel, fpsInput, simulationSizeInputLabel, simulationSizeInput);
        inputContainer.setAlignment(Pos.CENTER);

        VBox menuRoot = new VBox(50, canvas, playButton, inputContainer);
        menuRoot.setAlignment(Pos.TOP_CENTER);
        menuRoot.getStyleClass().add("vbox");


        Scene menuScene = new Scene(menuRoot, appInitialWidth, appInitialWidth + 80);
        menuScene.getStylesheets().add(StyleSheet);

        playButton.setOnAction(_ -> {
            Scene gameScene = new GameScene().getGameScene(stage, menuScene, (double) fpsInput.getValue(), simulationSizeInput.getValue());
            gameScene.getStylesheets().add(StyleSheet);
            stage.setScene(gameScene); // Cambia la escena actual por gameScene
        });

        return menuScene;
    }
}
