package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class game_Controller {
    @FXML private Label player_settings;
    public void setSettings(){
        player_settings.setText("Versus");
    }
}
