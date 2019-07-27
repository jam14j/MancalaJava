package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Ellipse;

import java.util.List;

public class game_Controller {
    @FXML private Label player_settings,turn_label, p1_mancala_label, p2_mancala_label;
    @FXML private Ellipse p1_mancala, p2_mancala;
    @FXML private List<Button> pile_list_button;
    @FXML private List<Ellipse> pile_list_ellipse;

    public void Initialize(){
        for (Button button : pile_list_button){
            button.setStyle("-fx-background-color: transparent;");
        }
    }

    public void setSettings(){
        player_settings.setText("Versus");
    }

}
