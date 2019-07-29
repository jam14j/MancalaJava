package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class rules_Controller implements Initializable {

	@FXML private AnchorPane basePane;
	@FXML private Text title;
	@FXML private TextArea rules;
	@FXML private Button menuButton;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		//Set background color
		String colorString = SharedPreferences.getColor().toString();
		colorString = "#"+colorString.substring(2);
		basePane.setStyle("-fx-background-color: "+colorString);
		//Set language
		if(SharedPreferences.getLanguage().equals("English")) {
			title.setText("Mancala Rules");
			menuButton.setText("Main Menu");
			try {
				Scanner sc = new Scanner(new File("rules.txt"));
				StringBuilder sb = new StringBuilder("");
				while (sc.hasNextLine())
					sb.append(sc.nextLine()+"\n");
				rules.setText(sb.toString());
				sc.close();
			}
			catch (IOException e) { e.printStackTrace(); }
		}
		else if(SharedPreferences.getLanguage().equals("Espanol")) {
			title.setText("Mancala Reglas");
			menuButton.setText("Menu Principal");
			try {
				Scanner sc = new Scanner(new File("rules_spanish.txt"));
				StringBuilder sb = new StringBuilder("");
				while (sc.hasNextLine())
					sb.append(sc.nextLine()+"\n");
				rules.setText(sb.toString());
				sc.close();
			}
			catch (IOException e) { e.printStackTrace(); }
		}
		else { //French
			title.setText("Mancala Regles");
			menuButton.setText("Menu Principal");
			try {
				Scanner sc = new Scanner(new File("rules_french2.txt"));
				StringBuilder sb = new StringBuilder("");
				while(sc.hasNextLine())
					sb.append(sc.nextLine()+"\n");
				rules.setText(sb.toString());
				sc.close();
			}
			catch (IOException e) { e.printStackTrace(); }
		}
	}

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
