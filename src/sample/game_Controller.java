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
    Boolean whosTurn = true;

    public void Initialize() {
        for (int i = 0; i < 12; i++) {
            Button button = pile_list_button.get(i);
            button.setStyle("-fx-background-color: transparent;");
            takeTurn();
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    int amt = Integer.parseInt(button.getText());
                    button.setText("0");
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
                    for (int k = amt; k > 0; k--) {
                        if (whichButton == 5) {
                            whichButton++;
                            Button nextButton = pile_list_button.get(whichButton);
                            if (whosTurn)//player one's turn so allow  p1 mancala to go up
                            {
                                //extra k-- because one goes in mancala
                                k--;
                                int newValue = Integer.parseInt(p1_mancala_label.getText());
                                newValue++;
                                p1_mancala_label.setText(String.valueOf(newValue));

                                if (k > 0) {
                                    newValue = Integer.parseInt(nextButton.getText());
                                    newValue++;
                                    nextButton.setText(String.valueOf(newValue));
                                }
                            } else {//else dont put anything into p1_mancala
                                int newValue = Integer.parseInt(nextButton.getText());
                                newValue++;
                                nextButton.setText(String.valueOf(newValue));
                            }
                        } else if (whichButton == 11) {
                            whichButton=0;
                            Button nextButton = pile_list_button.get(0);
                            if (whosTurn)//if player one dont add to p2_mancala
                            {
                                int newValue = Integer.parseInt(nextButton.getText());
                                newValue++;
                                nextButton.setText(String.valueOf(newValue));
                            } else {//add to p2 mancala cuz its p2's turn
                                k--;
                                int newValue = Integer.parseInt(p2_mancala_label.getText());
                                newValue++;
                                p2_mancala_label.setText(String.valueOf(newValue));

                                if (k > 0) {
                                    newValue = Integer.parseInt(nextButton.getText());
                                    newValue++;
                                    nextButton.setText(String.valueOf(newValue));
                                }
                            }
                        } else {
                            whichButton++;
                            Button nextButton = pile_list_button.get(whichButton);
                            int newValue = Integer.parseInt(nextButton.getText());
                            newValue++;
                            nextButton.setText(String.valueOf(newValue));
                        }

                        if (whichButton==12)
                        {
                            whichButton=0;
                        }
                    }
                    String ending;

                    if (whichButton==6 && whosTurn){
                        ending = "p1_mancala";
                    }
                    else if (whichButton==0&& !whosTurn){
                        ending = "p2 mancala";
                    }
                    else {
                        whosTurn = !whosTurn;
                        takeTurn();
                        ending = String.valueOf(whichButton);
                    }
                    System.out.printf("Starting pile: %s Ending index : %s\n",button.getId(),ending);

                }
            });
        }
    }

    public void takeTurn() {
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
