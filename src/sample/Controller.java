package sample;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML private GridPane basePane;
    @FXML private Button playButton, versusButton, settingsButton, rulesButton;
    @FXML private Text superMancala;

    @FXML public void startSingle(ActionEvent event) {
        try {
            Stage mStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
            Parent root = loader.load();
            game_Controller gc = loader.<game_Controller>getController();
            gc.InitializeSingle();

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
            Parent root = loader.load();
            game_Controller gc = loader.<game_Controller>getController();
            gc.setSettings();
            gc.InitializeVersus();

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Set background color
        String colorString = SharedPreferences.getColor().toString();
        colorString = "#"+colorString.substring(2);
        basePane.setStyle("-fx-background-color: "+colorString);
        //Set font
		superMancala.setFont(Font.loadFont(Main.FONT, 46));
		playButton.setFont(Font.loadFont(Main.FONT, 12));
		versusButton.setFont(Font.loadFont(Main.FONT, 12));
		rulesButton.setFont(Font.loadFont(Main.FONT, 12));
		settingsButton.setFont(Font.loadFont(Main.FONT, 12));

		if(SharedPreferences.getLanguage().equals("English")) {
			playButton.setText("Lonely Player");
			versusButton.setText("Versus");
			rulesButton.setText("Rules");
			settingsButton.setText("Settings");
		}
		else if(SharedPreferences.getLanguage().equals("Espanol")) {
			playButton.setText("Jugador Solitario");
			versusButton.setText("Versus");
			rulesButton.setText("Reglas");
			settingsButton.setText("Preferencias");
		}
		else { //French
			playButton.setText("joueur solitaire");
			versusButton.setText("Contre");
			rulesButton.setText("Regles");
			settingsButton.setText("Reglages");
		}
    }

}
