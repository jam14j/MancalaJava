package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Ellipse;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javax.sound.sampled.*;
import javax.swing.*;

import static com.sun.javafx.scene.control.skin.Utils.getResource;


public class game_Controller {
    @FXML private Label player_settings, turn_label, p1_mancala_label, p2_mancala_label, winner_label;
    @FXML private Ellipse p1_mancala, p2_mancala;
    @FXML private List<Button> pile_list_button;
    @FXML private List<Ellipse> pile_list_ellipse;
    Boolean whosTurn = true;


    public void Initialize()  {
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
                    boolean forGoAgain = true;
                    for (int k = amt; k > 0; k--) {
                        forGoAgain=true;
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
                                    forGoAgain=false;
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
                                    forGoAgain=false;
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
                        //steal implementation
//                        int under12= whichButton+1;
//                        if (under12==12)
//                            under12=0;
//                        Button forSteal = pile_list_button.get(under12);
//                        if (forSteal.getText().compareTo("1")==0)
//                        {
//                            if (whosTurn) {//p1
//                                if (whichButton == 0){
//                                //11 is across
//                                    Button across = pile_list_button.get(11);
//                                    int tempValue = Integer.parseInt(across.getText());
//                                    int addTo = Integer.parseInt(p1_mancala_label.getText());
//                                    int sum = addTo+tempValue;
//                                    p1_mancala_label.setText(String.valueOf(sum));
//                                    playSteal();
//                                }
//                                else if (whichButton == 1){
//                                //10 is across
//                                    Button across = pile_list_button.get(10);
//                                    int tempValue = Integer.parseInt(across.getText());
//                                    int addTo = Integer.parseInt(p1_mancala_label.getText());
//                                    int sum = addTo+tempValue;
//                                    p1_mancala_label.setText(String.valueOf(sum));
//                                    playSteal();
//                                }
//                                else if (whichButton == 2){
//                                //9 is across
//                                    Button across = pile_list_button.get(9);
//                                    int tempValue = Integer.parseInt(across.getText());
//                                    int addTo = Integer.parseInt(p1_mancala_label.getText());
//                                    int sum = addTo+tempValue;
//                                    p1_mancala_label.setText(String.valueOf(sum));
//                                    playSteal();
//                                }
//                                else if (whichButton == 3){
//                                    //8is across
//                                    Button across = pile_list_button.get(8);
//                                    int tempValue = Integer.parseInt(across.getText());
//                                    int addTo = Integer.parseInt(p1_mancala_label.getText());
//                                    int sum = addTo+tempValue;
//                                    p1_mancala_label.setText(String.valueOf(sum));
//                                    playSteal();
//                                }
//                                else if (whichButton == 4){
//                                    //7 is across
//                                    Button across = pile_list_button.get(7);
//                                    int tempValue = Integer.parseInt(across.getText());
//                                    int addTo = Integer.parseInt(p1_mancala_label.getText());
//                                    int sum = addTo+tempValue;
//                                    p1_mancala_label.setText(String.valueOf(sum));
//                                    playSteal();
//                                }
//                                else if (whichButton == 5){
//                                    //6 is across
//                                    Button across = pile_list_button.get(6);
//                                    int tempValue = Integer.parseInt(across.getText());
//                                    int addTo = Integer.parseInt(p1_mancala_label.getText());
//                                    int sum = addTo+tempValue;
//                                    p1_mancala_label.setText(String.valueOf(sum));
//                                    playSteal();
//                                }
//                            }
//                            else{//p2
//                                if (whichButton == 6){
//                                    //5 is across
//                                    Button across = pile_list_button.get(5);
//                                    int tempValue = Integer.parseInt(across.getText());
//                                    int addTo = Integer.parseInt(p2_mancala_label.getText());
//                                    int sum = addTo+tempValue;
//                                    p2_mancala_label.setText(String.valueOf(sum));
//                                    playSteal();
//                                }
//                                else if (whichButton == 7){
//                                    //4 is across
//                                    Button across = pile_list_button.get(4);
//                                    int tempValue = Integer.parseInt(across.getText());
//                                    int addTo = Integer.parseInt(p2_mancala_label.getText());
//                                    int sum = addTo+tempValue;
//                                    p2_mancala_label.setText(String.valueOf(sum));
//                                    playSteal();
//                                }
//                                else if (whichButton == 8){
//                                    //3 is across
//                                    Button across = pile_list_button.get(3);
//                                    int tempValue = Integer.parseInt(across.getText());
//                                    int addTo = Integer.parseInt(p2_mancala_label.getText());
//                                    int sum = addTo+tempValue;
//                                    p2_mancala_label.setText(String.valueOf(sum));
//                                    playSteal();
//                                }
//                                else if (whichButton == 9){
//                                    //2 is across
//                                    Button across = pile_list_button.get(2);
//                                    int tempValue = Integer.parseInt(across.getText());
//                                    int addTo = Integer.parseInt(p2_mancala_label.getText());
//                                    int sum = addTo+tempValue;
//                                    p2_mancala_label.setText(String.valueOf(sum));
//                                    playSteal();
//                                }
//                                else if (whichButton == 10){
//                                    //1 is across
//                                    Button across = pile_list_button.get(1);
//                                    int tempValue = Integer.parseInt(across.getText());
//                                    int addTo = Integer.parseInt(p2_mancala_label.getText());
//                                    int sum = addTo+tempValue;
//                                    p2_mancala_label.setText(String.valueOf(sum));
//                                    playSteal();
//                                }
//                                else if (whichButton == 11){
//                                    //0 is across
//                                    Button across = pile_list_button.get(0);  int tempValue = Integer.parseInt(across.getText());
//                                    int addTo = Integer.parseInt(p2_mancala_label.getText());
//                                    int sum = addTo+tempValue;
//                                    p2_mancala_label.setText(String.valueOf(sum));
//                                    playSteal();
//                                }
                            //}
                        //}
                    }





                    String ending;
                    if (whichButton==6 && whosTurn&& forGoAgain){
                        ending = "p1_mancala";
                        turn_label.setText("Player 1's Turn Again!!!");
                        AudioInputStream audioIn = null;
                        try {
                            audioIn = AudioSystem.getAudioInputStream(this.getClass().getResource("go_again.wav"));
                            Clip clip = AudioSystem.getClip();
                            clip.open(audioIn);
                            clip.start();
                        } catch (UnsupportedAudioFileException er) {
                            er.printStackTrace();
                        } catch (IOException er) {
                            er.printStackTrace();
                        } catch (LineUnavailableException er) {
                            er.printStackTrace();
                        }

                    }
                    else if (whichButton==0&& !whosTurn&& forGoAgain){
                        ending = "p2 mancala";
                        turn_label.setText("Player 2's Turn Again!!!");
                        AudioInputStream audioIn = null;
                        try {
                            audioIn = AudioSystem.getAudioInputStream(this.getClass().getResource("go_again.wav"));
                            Clip clip = AudioSystem.getClip();
                            clip.open(audioIn);
                            clip.start();
                        } catch (UnsupportedAudioFileException err) {
                            err.printStackTrace();
                        } catch (IOException err) {
                            err.printStackTrace();
                        } catch (LineUnavailableException err) {
                            err.printStackTrace();
                        }
                    }
                    else {
                        whosTurn = !whosTurn;
                        takeTurn();
                        ending = String.valueOf(++whichButton);
                    }
                    System.out.printf("Starting pile: %s \tEnding pile : %s " +
                            "\t Pebbles: %d\n",button.getId(),ending, amt);
                    Boolean check_win = true;
                    for (int f = 0; f<6; f++){
                        Button thisButtons = pile_list_button.get(f);
                        System.out.println(thisButtons.getText().compareTo("0")!=0);
                        if (thisButtons.getText().compareTo("0")!=0)
                            check_win=false;
                    }
                    if (check_win)
                    {
                        playVictory();
                        endGame();
                    }
                    check_win = true;
                    for (int f = 6; f<12; f++){
                        Button thisButtons = pile_list_button.get(f);
                        System.out.println(thisButtons.getText().compareTo("0")!=0);
                        if (thisButtons.getText().compareTo("0")!=0)
                            check_win=false;
                    }
                    if (check_win)
                    {
                        playVictory();
                        endGame();
                    }
                }
            });
        }

    }

    private void playSteal() {
        AudioInputStream audioIn = null;
        try {
            audioIn = AudioSystem.getAudioInputStream(this.getClass().getResource("yoink.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException er) {
            er.printStackTrace();
        } catch (IOException er) {
            er.printStackTrace();
        } catch (LineUnavailableException er) {
            er.printStackTrace();
        }

    }


    private void endGame() {
        for (Button button : pile_list_button){
            button.setDisable(true);
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

    public void playVictory(){
        int playerOneScore = Integer.parseInt(p1_mancala_label.getText());
        int playerTwoScore =Integer.parseInt(p2_mancala_label.getText());

        if (playerOneScore < playerTwoScore)
        {//player one lost
            winner_label.setText("PLayer Two WINS!");
            winner_label.setVisible(true);
        }
        else if (playerOneScore > playerTwoScore){
            //player one wins
            winner_label.setText("PLayer One WINS!");
            winner_label.setVisible(true);
        }
        else {
            winner_label.setText("Its a DRAW!");
            winner_label.setVisible(true);
        }
        AudioInputStream audioIn = null;
        try {
            audioIn = AudioSystem.getAudioInputStream(this.getClass().getResource("ff7.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException er) {
            er.printStackTrace();
        } catch (IOException er) {
            er.printStackTrace();
        } catch (LineUnavailableException er) {
            er.printStackTrace();
        }

    }
}
