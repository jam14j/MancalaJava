package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class settings_Controller implements Initializable {
	@FXML private ComboBox<String> languageBox;
	@FXML private ColorPicker bColorPicker;
	@FXML private ToggleButton muteButton;
	@FXML private AnchorPane basePane;
	@FXML private Button backButton;
	@FXML private Text settingsLabel, languageLabel, backgroundLabel;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		//Set all the buttons with the info in the SharedPreferences
		List<String> languages = new ArrayList<String>();
		languages.add("English");
		languages.add("Espanol");
		languages.add("Francais");
		ObservableList oblist = FXCollections.observableList(languages);
		languageBox.setItems(oblist);
		languageBox.setValue(SharedPreferences.getLanguage());
		bColorPicker.setValue(SharedPreferences.getColor());
		muteButton.setSelected(SharedPreferences.isMute());

		//Set the window look to match the preferences
		String colorString = SharedPreferences.getColor().toString();
		colorString = "#" + colorString.substring(2);
		basePane.setStyle("-fx-background-color: " + colorString);
		//Set language
		if(SharedPreferences.getLanguage().equals("English")) {
			languageLabel.setText("Language:");
			backgroundLabel.setText("Background:");
			settingsLabel.setText("Settings");
			muteButton.setText("Mute Sound");
			backButton.setText("Main Menu");
		}
		else if(SharedPreferences.getLanguage().equals("Espanol")) {
			languageLabel.setText("Lenguaje:");
			backgroundLabel.setText("Fondo:");
			settingsLabel.setText("Preferencias");
			muteButton.setText("Sin Sonido");
			backButton.setText("Menu Principal");
		}
		else { //French
			languageLabel.setText("Langue:");
			backgroundLabel.setText("Fond:");
			settingsLabel.setText("reglages");
			muteButton.setText("Son Muet");
			backButton.setText("Menu Principal");
		}
	}

	public void backToMain(ActionEvent event) {
		//Save the settings into the sharedPreferences Object
		SharedPreferences.setLanguage(languageBox.getValue());
		SharedPreferences.setColor(bColorPicker.getValue());
		SharedPreferences.setMute(muteButton.isSelected());

		try {
			Stage mStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
			mStage.setTitle("Super Mancala Rules");
			mStage.setScene(new Scene(root, 600, 440));
		}
		catch(IOException e) { e.printStackTrace(); }
	}

}
