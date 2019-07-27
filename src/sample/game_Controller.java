package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Ellipse;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class game_Controller {
    @FXML private Label player_settings, turn_label, p1_mancala_label, p2_mancala_label;
    @FXML private Ellipse p1_mancala, p2_mancala;
    @FXML private List<Button> pile_list_button;
    @FXML private List<Ellipse> pile_list_ellipse;
    private boolean whosTurn = true;

    public void Initialize() {
        for (int i = 0; i < 12; i++) {
            Button button = pile_list_button.get(i);
            button.setStyle("-fx-background-color: transparent;");
            takeTurn();
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    int whichButton = -1;
                    switch (button.getId()) {
                        case "pile_button1":
                            whichButton = 0;
                            break;
                        case "pile_button2":
                            whichButton = 1;
                            break;
                        case "pile_button3":
                            whichButton = 2;
                            break;
                        case "pile_button4":
                            whichButton = 3;
                            break;
                        case "pile_button5":
                            whichButton = 4;
                            break;
                        case "pile_button6":
                            whichButton = 5;
                            break;
                        case "pile_button7":
                            whichButton = 6;
                            break;
                        case "pile_button8":
                            whichButton = 7;
                            break;
                        case "pile_button9":
                            whichButton = 8;
                            break;
                        case "pile_button10":
                            whichButton = 9;
                            break;
                        case "pile_button11":
                            whichButton = 10;
                            break;
                        case "pile_button12":
                            whichButton = 11;
                            break;
                    }
                    //System.out.println("Button pressed: "+whichButton);
                    int amt = Integer.parseInt(button.getText());
                    button.setText("0");    // moved this up here for clarity -Juan
                    for (int k = amt; k > 0; k--) {
                        if(whichButton == 5 && whosTurn && k>0) {
                            k--;
                            int mancalaValue = Integer.parseInt(p1_mancala_label.getText());
                            mancalaValue++;
                            p1_mancala_label.setText(String.valueOf(mancalaValue));
                        }
                        else if(whichButton == 11 && !whosTurn && k>0) {
                            k--;
                            int mancalaValue = Integer.parseInt(p2_mancala_label.getText());
                            mancalaValue++;
                            p2_mancala_label.setText(String.valueOf(mancalaValue));
                        }
                        if(k == 0)
                            break;
                        whichButton++;
                        if(whichButton == 12)
                            whichButton = 0;
                        Button nextButton = pile_list_button.get(whichButton);
                        int newValue = Integer.parseInt(nextButton.getText());
                        newValue++;
                        nextButton.setText(String.valueOf(newValue));
                    }
                    takeTurn();
                }
            });
        }
    }

    public void takeTurn() {
        whosTurn = !whosTurn;
        if (whosTurn) {
            turn_label.setText("Player 1's Turn!");
            for (int i = 0; i < 6; i++) {
                pile_list_button.get(i).setDisable(false);
            }
            for (int i = 6; i < 12; i++) {
                pile_list_button.get(i).setDisable(true);
            }
        } else {
            turn_label.setText("Player 2's Turn!");
            for (int i = 0; i < 6; i++) {
                pile_list_button.get(i).setDisable(true);
            }
            for (int i = 6; i < 12; i++) {
                pile_list_button.get(i).setDisable(false);
            }
        }
    }

    public void setSettings() {
        player_settings.setText("Versus");
    }

}
