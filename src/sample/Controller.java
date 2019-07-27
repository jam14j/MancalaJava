package sample;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {

	@FXML public void startSingle(ActionEvent event) {
        try {
            Stage mStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("game.fxml"));
            mStage.setTitle("Super Mancala");
            mStage.setScene(new Scene(root, 600, 440));
            mStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}

	@FXML public void startVersus(ActionEvent event) {
        try {
            Stage mStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
            Parent root = (Parent) loader.load();
            game_Controller gc = loader.<game_Controller>getController();
            gc.setSettings();
            gc.Initialize();

            mStage.setTitle("Super Mancala");
            mStage.setScene(new Scene(root, 600, 440));
            mStage.show();
        }
        catch (IOException e){ e.printStackTrace(); }
	}

    @FXML public void showRules(ActionEvent event) {
        try {
            Stage mStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("rules.fxml"));
            mStage.setTitle("Super Mancala Rules");
            mStage.setScene(new Scene(root, 600, 440));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void showSettings(ActionEvent event) {
        try {
            Stage mStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("settings.fxml"));
            mStage.setTitle("Super Mancala Settings");
            mStage.setScene(new Scene(root, 600, 440));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
