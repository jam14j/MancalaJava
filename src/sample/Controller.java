package sample;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class Controller {

	@FXML public void startSingle(ActionEvent event) {
		System.out.println("Hello Single!");
	}

	@FXML public void startVersus(ActionEvent event) {
		System.out.println("Hello Versus!");
	}

}
