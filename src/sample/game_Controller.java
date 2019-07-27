package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    Boolean whosTurn = true;

    public void Initialize(){
        for (int i =0; i <12; i++){
            Button button = pile_list_button.get(i);
            button.setStyle("-fx-background-color: transparent;");
//            final int whichButton = i;
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    int whichButton = -1;
                    switch(button.getId()) {
                        case "pile_button1": whichButton = 0; break;
                        case "pile_button2": whichButton = 1; break;
                        case "pile_button3": whichButton = 2; break;
                        case "pile_button4": whichButton = 3; break;
                        case "pile_button5": whichButton = 4; break;
                        case "pile_button6": whichButton = 5; break;
                        case "pile_button7": whichButton = 6; break;
                        case "pile_button8": whichButton = 7; break;
                        case "pile_button9": whichButton = 8; break;
                        case "pile_button10": whichButton = 9; break;
                        case "pile_button11": whichButton = 10; break;
                        case "pile_button12": whichButton = 11; break;
                    }
                    takeTurn();
                    int amt = Integer.parseInt(button.getText());
                    System.out.println(whichButton);
//                    int nextButton = whichButton+1;
                    for (int k =amt;k>0;k--){
//                        i+(amt-k)
                        if (whichButton==5)
                        {

                        }
                        else if (whichButton==11){

                        }
                        Button nextButton =  pile_list_button.get(whichButton);

                    }


                    whosTurn= !whosTurn;
                }
            });
        }
        takeTurn();
    }

    public void takeTurn(){
        if (whosTurn){
            turn_label.setText("Player 1's Turn!");
            for (int i =0; i <6; i++){
                pile_list_button.get(i).setDisable(false);
            }
            for (int i =6; i <12; i++){
                pile_list_button.get(i).setDisable(true);
            }
        }
        else{
            turn_label.setText("Player 2's Turn!");
            for (int i =0; i <6; i++){
                pile_list_button.get(i).setDisable(true);
            }
            for (int i =6; i <12; i++){
                pile_list_button.get(i).setDisable(false);
            }
        }
    }

    public void setSettings(){
        player_settings.setText("Versus");
    }

}
