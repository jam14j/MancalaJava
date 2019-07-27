package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class settings_Controller {



	public void backToMain(ActionEvent event) {
		try {
			Stage mStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
			mStage.setTitle("Super Mancala Rules");
			mStage.setScene(new Scene(root, 600, 440));
		}
		catch(IOException e) { e.printStackTrace(); }
	}
}
