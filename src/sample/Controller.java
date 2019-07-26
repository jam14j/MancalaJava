package sample;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
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

        }
	}

	@FXML public void startVersus(ActionEvent event) {
        try {
            Stage mStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("game.fxml"));
            mStage.setTitle("Super Mancala");
            mStage.setScene(new Scene(root, 600, 440));
            mStage.show();
        }
        catch (IOException e){

        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        game_Controller gc = loader.getController();
        gc.setSettings();

	}
}
