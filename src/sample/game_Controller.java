package sample;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.sound.sampled.*;


public class game_Controller {
    @FXML private AnchorPane basePane;
    @FXML
    private Label player_settings, turn_label, p1_mancala_label, p2_mancala_label, winner_label;
    @FXML private Ellipse p1_mancala, p2_mancala;
    @FXML private List<Button> pile_list_button;
    @FXML private List<Ellipse> pile_list_ellipse;
    private boolean whosTurn = true;
    private boolean players, cputurn;
    private String player1turn, player2turn, yourturn, yourturnagain, p1win, p2win, draw, youwin, youlose;

    private void setLook(boolean isSinglePlayer) {
        //set button font
        for(Button b : pile_list_button) {
            b.setFont(Font.loadFont(Main.FONT, 16));
        }
        p1_mancala_label.setFont(Font.loadFont(Main.FONT, 24));
        p2_mancala_label.setFont(Font.loadFont(Main.FONT, 24));
        winner_label.setFont(Font.loadFont(Main.FONT, 40));
        winner_label.setTextFill(Color.GOLD);
        //set background
        String colorString = SharedPreferences.getColor().toString();
        colorString = "#"+colorString.substring(2);
        basePane.setStyle("-fx-background-color: "+colorString);
        //set language
        if(isSinglePlayer) {
            if(SharedPreferences.getLanguage().equals("English"))
                player_settings.setText("Single Player");
            else if(SharedPreferences.getLanguage().equals("Espanol"))
                player_settings.setText("Un Jugador");
            else //French
                player_settings.setText("joueur unique");
        }
        else { //versus
            if(SharedPreferences.getLanguage().equals("English"))
                player_settings.setText("Versus");
            else if(SharedPreferences.getLanguage().equals("Espanol"))
                player_settings.setText("Versus");
            else //French
                player_settings.setText("Contre");
        }
        if(SharedPreferences.getLanguage().equals("English")) {
            player1turn = "Player 1's turn!";
            player2turn = "Player 2's turn!";
            yourturn = "Your turn!";
            yourturnagain = "Your turn again!!!";
            p1win = "Player 1 wins!";
            p2win = "Player 2 wins!";
            draw = "Draw!!!";
            youwin = "You win!!!";
            youlose = "You lose!";
        }
        else if(SharedPreferences.getLanguage().equals("Espanol")) {
            player1turn = "Turno del jugador 1";
            player2turn = "Turno del jugador 2";
            yourturn = "Tu turno!";
            yourturnagain = "Tu turno otra vez!!!";
            p1win = "Gana el jugador 1";
            p2win = "Gana el jugador 2";
            draw = "Empate!!!";
            youwin = "Ganaste!!!";
            youlose = "Perdiste!";
        }
        else { //French
            player1turn = "Tour du joueur 1";
            player2turn = "Tour du joueur 2";
            yourturn = "A ton tour!";
            yourturnagain = "ton tour encore!!!";
            p1win = "joueur 1 gagne";
            p2win = "joueur 2 gagne";
            draw = "Match nul!!!";
            youwin = "Gagne!!!";
            youlose = "Perdre!";
        }

    }


    public void InitializeSingle() {
        setLook(true);
        players = false;
        playStart();
        for (int i = 0; i < 12; i++) {
            Button button = pile_list_button.get(i);
            button.setStyle("-fx-background-color: transparent;");
            takeTurn(players);
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

                        forGoAgain = true;
                        if (whichButton == 5) {
                            whichButton++;
                            Button nextButton = pile_list_button.get(whichButton);
                            if (whosTurn)//player one's turn so allow  p1 mancala to go up
                            {
                                //extra k-- because one goes in mancala
                                k--;
                                PauseTransition ptm = new PauseTransition(Duration.seconds((amt-k)/2.0));
                                int newValue = Integer.parseInt(p1_mancala_label.getText());
                                newValue++;
                                int finalValueM = newValue;
                                ptm.setOnFinished(event -> p1_mancala_label.setText(String.valueOf(finalValueM)));
                                ptm.play();

                                if (k > 0) {
                                    forGoAgain = false;
                                    PauseTransition pt = new PauseTransition(Duration.seconds((amt-k+1)/2.0));
                                    newValue = Integer.parseInt(nextButton.getText());
                                    newValue++;
                                    int finalValue = newValue;
                                    pt.setOnFinished(event ->nextButton.setText(String.valueOf(finalValue)));
                                    pt.play();
                                }
                            } else {//else dont put anything into p1_mancala
                                PauseTransition pt = new PauseTransition(Duration.seconds((amt-k+1)/2.0));
                                int newValue = Integer.parseInt(nextButton.getText());
                                newValue++;
                                int finalValue = newValue;
                                pt.setOnFinished(event ->nextButton.setText(String.valueOf(finalValue)));
                                pt.play();
                            }
                        } else if (whichButton == 11) {
                            whichButton = 0;
                            Button nextButton = pile_list_button.get(0);
                            if (whosTurn)//if player one dont add to p2_mancala
                            {
                                PauseTransition pt = new PauseTransition(Duration.seconds((amt-k+1)/2.0));
                                int newValue = Integer.parseInt(nextButton.getText());
                                newValue++;
                                int finalValue = newValue;
                                pt.setOnFinished(event ->nextButton.setText(String.valueOf(finalValue)));
                                pt.play();
                            } else {//add to p2 mancala cuz its p2's turn
                                k--;
                                PauseTransition ptm = new PauseTransition(Duration.seconds((amt-k)/2.0));
                                int newValue = Integer.parseInt(p2_mancala_label.getText());
                                newValue++;
                                int finalValueM = newValue;
                                ptm.setOnFinished(event -> p2_mancala_label.setText(String.valueOf(finalValueM)));
                                ptm.play();

                                if (k > 0) {
                                    PauseTransition pt = new PauseTransition(Duration.seconds((amt-k+1)/2.0));
                                    newValue = Integer.parseInt(nextButton.getText());
                                    newValue++;
                                    int finalValue = newValue;
                                    pt.setOnFinished(event ->nextButton.setText(String.valueOf(finalValue)));
                                    pt.play();
                                    forGoAgain = false;
                                }
                            }
                        } else {
                            whichButton++;
                            Button nextButton = pile_list_button.get(whichButton);
                            PauseTransition pt = new PauseTransition(Duration.seconds((amt-k+1)/2.0));
                            int newValue = Integer.parseInt(nextButton.getText());
                            newValue++;
                            int finalValue = newValue;
                            pt.setOnFinished(event ->nextButton.setText(String.valueOf(finalValue)));
                            pt.play();
                        }

                        if (whichButton == 12) {
                            whichButton = 0;
                        }
                    }

                    //steal implementation
                    PauseTransition pt_steal = new PauseTransition(Duration.seconds((amt+1)/2.0));
                    final int fwhichButton = whichButton;
                    final boolean fForGoAgain = forGoAgain;
                    pt_steal.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            int under12 = fwhichButton + 1;
                            System.out.println("fwhichButton: "+fwhichButton);
                            Button forSteal = pile_list_button.get(fwhichButton);
                            System.out.printf("STEALING: Starting pile: %s \tEnding pile : %d " +
                                    "\t Pebbles: %s\n", button.getId(), fwhichButton, forSteal.getText());
                            if (forSteal.getText().compareTo("1") == 0 &&
                                    (under12 > 0 && under12 < 7) && !whosTurn && fForGoAgain) {
                                Button across = pile_list_button.get(11 - fwhichButton);
                                int tempValue = Integer.parseInt(across.getText());
                                int addTo = Integer.parseInt(p1_mancala_label.getText());
                                int sum = addTo + tempValue;
                                p1_mancala_label.setText(String.valueOf(sum));
                                across.setText("0");
                                if(tempValue != 0)
                                    playSteal();

                            } else if (forSteal.getText().compareTo("1") == 0 &&
                                    (under12 > 6 && under12 < 13) && whosTurn && fForGoAgain) {
                                Button across = pile_list_button.get(11 - fwhichButton);
                                int tempValue = Integer.parseInt(across.getText());
                                int addTo = Integer.parseInt(p2_mancala_label.getText());
                                int sum = addTo + tempValue;
                                p2_mancala_label.setText(String.valueOf(sum));
                                across.setText("0");
                                if(tempValue != 0)
                                    playSteal();
                            }
                        }
                    });
                    pt_steal.play();


                    String ending;
                    if (whichButton == 6 && whosTurn && forGoAgain) {
                        PauseTransition pt_turn = new PauseTransition(Duration.seconds(amt/2.0));
                        pt_turn.setOnFinished(event -> takeTurn(players));
                        pt_turn.play();
                        ending = "p1_mancala";
                        turn_label.setText(player1turn);
                        playGoAgain(amt);

                    } else if (whichButton == 0 && !whosTurn && forGoAgain) {
                        PauseTransition pt_turn = new PauseTransition(Duration.seconds(amt/2.0));
                        pt_turn.setOnFinished(event -> takeTurn(players));
                        pt_turn.play();
                        ending = "p2 mancala";
                        turn_label.setText(player2turn);
                        playGoAgain(amt);
                    } else {
                        whosTurn = !whosTurn;
                        PauseTransition pt_turn = new PauseTransition(Duration.seconds(amt/2.0));
                        pt_turn.setOnFinished(event -> takeTurn(players));
                        pt_turn.play();
                        ending = String.valueOf(++whichButton);
                    }
                    System.out.printf("Starting pile: %s \tEnding pile : %s " +
                            "\t Pebbles: %d\n", button.getId(), ending, amt);
                    //Endgame check needs a delay, otherwise it checks during animation. Causing early endgame
                    PauseTransition pt_check = new PauseTransition(Duration.seconds((amt+1)/2.0));
                    pt_check.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            boolean check_win = true;
                            for (int f = 0; f < 6; f++) {
                                Button thisButtons = pile_list_button.get(f);
                                if (thisButtons.getText().compareTo("0") != 0)
                                    check_win = false;
                            }
                            if (check_win) {
                                endGame();
                                Victory(amt);

                            }
                            check_win = true;
                            for (int f = 6; f < 12; f++) {
                                Button thisButtons = pile_list_button.get(f);
                                if (thisButtons.getText().compareTo("0") != 0)
                                    check_win = false;
                            }
                            if (check_win) {
                                endGame();
                                Victory(amt);
                            }
                        }
                    });
                    pt_check.play();
                    //CPU play
                    final boolean ffForGoAgain = forGoAgain;
                    if(cputurn) {
                        PauseTransition pt_pc = new PauseTransition(Duration.seconds((amt+2)/2.0));
                        pt_pc.setOnFinished(event -> choiceByPC(amt, ffForGoAgain));
                        pt_pc.play();
                    }
                    cputurn = true;
                }
            });
        }
        cputurn = true;
        //if repeat on the first turn, then go again
        if(choiceByPC(0, false) == 2) {
            PauseTransition pt_extra = new PauseTransition(Duration.seconds(3));
            pt_extra.setOnFinished(event -> choiceByPC(4, false));
            pt_extra.play();
        }
    }

    public void InitializeVersus() {
        setLook(false);
        players = true;
        playStart();
        for (int i = 0; i < 12; i++) {
            Button button = pile_list_button.get(i);
            button.setStyle("-fx-background-color: transparent;");
            takeTurn(players);
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

                        forGoAgain = true;
                        if (whichButton == 5) {
                            whichButton++;
                            Button nextButton = pile_list_button.get(whichButton);
                            if (whosTurn)//player one's turn so allow  p1 mancala to go up
                            {
                                //extra k-- because one goes in mancala
                                k--;
                                PauseTransition ptm = new PauseTransition(Duration.seconds((amt-k)/2.0));
                                int newValue = Integer.parseInt(p1_mancala_label.getText());
                                newValue++;
                                int finalValueM = newValue;
                                ptm.setOnFinished(event -> p1_mancala_label.setText(String.valueOf(finalValueM)));
                                ptm.play();

                                if (k > 0) {
                                    forGoAgain = false;
                                    PauseTransition pt = new PauseTransition(Duration.seconds((amt-k+1)/2.0));
                                    newValue = Integer.parseInt(nextButton.getText());
                                    newValue++;
                                    int finalValue = newValue;
                                    pt.setOnFinished(event ->nextButton.setText(String.valueOf(finalValue)));
                                    pt.play();
                                }
                            } else {//else dont put anything into p1_mancala
                                PauseTransition pt = new PauseTransition(Duration.seconds((amt-k+1)/2.0));
                                int newValue = Integer.parseInt(nextButton.getText());
                                newValue++;
                                int finalValue = newValue;
                                pt.setOnFinished(event ->nextButton.setText(String.valueOf(finalValue)));
                                pt.play();
                            }
                        } else if (whichButton == 11) {
                            whichButton = 0;
                            Button nextButton = pile_list_button.get(0);
                            if (whosTurn)//if player one dont add to p2_mancala
                            {
                                PauseTransition pt = new PauseTransition(Duration.seconds((amt-k+1)/2.0));
                                int newValue = Integer.parseInt(nextButton.getText());
                                newValue++;
                                int finalValue = newValue;
                                pt.setOnFinished(event ->nextButton.setText(String.valueOf(finalValue)));
                                pt.play();
                            } else {//add to p2 mancala cuz its p2's turn
                                k--;
                                PauseTransition ptm = new PauseTransition(Duration.seconds((amt-k)/2.0));
                                int newValue = Integer.parseInt(p2_mancala_label.getText());
                                newValue++;
                                int finalValueM = newValue;
                                ptm.setOnFinished(event -> p2_mancala_label.setText(String.valueOf(finalValueM)));
                                ptm.play();

                                if (k > 0) {
                                    PauseTransition pt = new PauseTransition(Duration.seconds((amt-k+1)/2.0));
                                    newValue = Integer.parseInt(nextButton.getText());
                                    newValue++;
                                    int finalValue = newValue;
                                    pt.setOnFinished(event ->nextButton.setText(String.valueOf(finalValue)));
                                    pt.play();
                                    forGoAgain = false;
                                }
                            }
                        } else {
                            whichButton++;
                            Button nextButton = pile_list_button.get(whichButton);
                            PauseTransition pt = new PauseTransition(Duration.seconds((amt-k+1)/2.0));
                            int newValue = Integer.parseInt(nextButton.getText());
                            newValue++;
                            int finalValue = newValue;
                            pt.setOnFinished(event ->nextButton.setText(String.valueOf(finalValue)));
                            pt.play();
                        }

                        if (whichButton == 12) {
                            whichButton = 0;
                        }
                    }

                    //steal implementation
                    PauseTransition pt_steal = new PauseTransition(Duration.seconds((amt+1)/2.0));
                    final int fwhichButton = whichButton;
                    final boolean fForGoAgain = forGoAgain;
                    pt_steal.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            int under12 = fwhichButton + 1;
                            System.out.println("fwhichButton: "+fwhichButton);
                            Button forSteal = pile_list_button.get(fwhichButton);
                            System.out.printf("STEALING: Starting pile: %s \tEnding pile : %d " +
                                    "\t Pebbles: %s\n", button.getId(), fwhichButton, forSteal.getText());
                            if (forSteal.getText().compareTo("1") == 0 &&
                                    (under12 > 0 && under12 < 7) && !whosTurn && fForGoAgain) {
                                Button across = pile_list_button.get(11 - fwhichButton);
                                int tempValue = Integer.parseInt(across.getText());
                                int addTo = Integer.parseInt(p1_mancala_label.getText());
                                int sum = addTo + tempValue;
                                p1_mancala_label.setText(String.valueOf(sum));
                                across.setText("0");
                                if(tempValue != 0)
                                    playSteal();

                            } else if (forSteal.getText().compareTo("1") == 0 &&
                                    (under12 > 6 && under12 < 13) && whosTurn && fForGoAgain) {
                                Button across = pile_list_button.get(11 - fwhichButton);
                                int tempValue = Integer.parseInt(across.getText());
                                int addTo = Integer.parseInt(p2_mancala_label.getText());
                                int sum = addTo + tempValue;
                                p2_mancala_label.setText(String.valueOf(sum));
                                across.setText("0");
                                if(tempValue != 0)
                                    playSteal();
                            }
                        }
                    });
                    pt_steal.play();


                    String ending;
                    if (whichButton == 6 && whosTurn && forGoAgain) {
                        PauseTransition pt_turn = new PauseTransition(Duration.seconds(amt/2.0));
                        pt_turn.setOnFinished(event -> takeTurn(players));
                        pt_turn.play();
                        ending = "p1_mancala";
                        turn_label.setText(player1turn);
                        playGoAgain(amt);

                    } else if (whichButton == 0 && !whosTurn && forGoAgain) {
                        PauseTransition pt_turn = new PauseTransition(Duration.seconds(amt/2.0));
                        pt_turn.setOnFinished(event -> takeTurn(players));
                        pt_turn.play();
                        ending = "p2 mancala";
                        turn_label.setText(player2turn);
                        playGoAgain(amt);
                    } else {
                        whosTurn = !whosTurn;
                        PauseTransition pt_turn = new PauseTransition(Duration.seconds(amt/2.0));
                        pt_turn.setOnFinished(event -> takeTurn(players));
                        pt_turn.play();
                        ending = String.valueOf(++whichButton);
                    }
                    System.out.printf("Starting pile: %s \tEnding pile : %s " +
                            "\t Pebbles: %d\n", button.getId(), ending, amt);
                    //Endgame check needs a delay, otherwise it checks during animation. Causing early endgame
                    PauseTransition pt_check = new PauseTransition(Duration.seconds((amt+1)/2.0));
                    pt_check.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            boolean check_win = true;
                            for (int f = 0; f < 6; f++) {
                                Button thisButtons = pile_list_button.get(f);
                                if (thisButtons.getText().compareTo("0") != 0)
                                    check_win = false;
                            }
                            if (check_win) {
                                endGame();
                                Victory(amt);

                            }
                            check_win = true;
                            for (int f = 6; f < 12; f++) {
                                Button thisButtons = pile_list_button.get(f);
                                if (thisButtons.getText().compareTo("0") != 0)
                                    check_win = false;
                            }
                            if (check_win) {
                                endGame();
                                Victory(amt);
                            }
                        }
                    });
                    pt_check.play();
                }
            });
        }

    }

    private void playSteal() {
        String filename;
        filename = "sounds/yoink.wav";
        if(!SharedPreferences.isMute()) {
            Media sound = new Media(new File(filename).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.seek(Duration.millis(0));
            mediaPlayer.play();
            mediaPlayer.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                    mediaPlayer.stop();
                }
            });
        }
    }

    private void endGame() {
        turn_label.setText("Game over!");
        for (Button button : pile_list_button) {
            button.setDisable(true);
        }
        for (int i = 0; i < 6; i++) {
            Button button = pile_list_button.get(i);
            int tempValue = Integer.parseInt(p1_mancala_label.getText());
            int addTo = Integer.parseInt(button.getText());
            int sum = tempValue + addTo;
            p1_mancala_label.setText(String.valueOf(sum));
            button.setText("0");
        }
        for (int i = 6; i < 12; i++) {
            Button button = pile_list_button.get(i);
            int tempValue = Integer.parseInt(p2_mancala_label.getText());
            int addTo = Integer.parseInt(button.getText());
            int sum = tempValue + addTo;
            p2_mancala_label.setText(String.valueOf(sum));
            button.setText("0");
        }
    }


    public void takeTurn(boolean players) {
        if (whosTurn) {
            turn_label.setText(player1turn);
            for (int i = 0; i < 6; i++) {
                pile_list_button.get(i).setDisable(false);
                if (pile_list_button.get(i).getText().compareTo("0") == 0)
                    pile_list_button.get(i).setDisable(true);
            }
            for (int i = 6; i < 12; i++) {
                pile_list_button.get(i).setDisable(true);
                if (pile_list_button.get(i).getText().compareTo("0") == 0)
                    pile_list_button.get(i).setDisable(true);
            }
        } else {
            if (players)
                turn_label.setText(player2turn);
            else
                turn_label.setText(yourturn);

            for (int i = 0; i < 6; i++) {
                pile_list_button.get(i).setDisable(true);
                if (pile_list_button.get(i).getText().compareTo("0") == 0)
                    pile_list_button.get(i).setDisable(true);
            }
            for (int i = 6; i < 12; i++) {
                pile_list_button.get(i).setDisable(false);
                if (pile_list_button.get(i).getText().compareTo("0") == 0)
                    pile_list_button.get(i).setDisable(true);
            }
        }
    }

    public void setSettings() {
        player_settings.setText("Versus");
    }

    public void Victory(int amt) {
        playVictory();
        int playerOneScore = Integer.parseInt(p1_mancala_label.getText());
        int playerTwoScore = Integer.parseInt(p2_mancala_label.getText());

        if (players) {
            if (playerOneScore < playerTwoScore) {//player one lost
                winner_label.setText(p2win);
                winner_label.setVisible(true);
            } else if (playerOneScore > playerTwoScore) {
                //player one wins
                winner_label.setText(p1win);
                winner_label.setVisible(true);
            } else {
                winner_label.setText(draw);
                winner_label.setVisible(true);
            }
        } else {
            if (playerOneScore < playerTwoScore) {//player one lost
                winner_label.setText(youwin);
                winner_label.setVisible(true);
            } else if (playerOneScore > playerTwoScore) {
                //player one wins
                winner_label.setText(youlose);
                winner_label.setVisible(true);
            } else {
                winner_label.setText(draw);
                winner_label.setVisible(true);
            }
        }
        PauseTransition pt_end = new PauseTransition(Duration.seconds((amt+1)/2.0));
        pt_end.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for(Button b : pile_list_button)
                    b.setText("0");
            }
        });
        pt_end.play();
    }

    public void playStart() {
        String filename;
        if (SharedPreferences.getLanguage().equals("Espanol"))
            filename = "sounds/are_you_ready_juan.wav";
        else if(SharedPreferences.getLanguage().equals("English"))
            filename = "sounds/are_you_ready_grant.wav";
        else //French
            filename = "sounds/are_you_ready_andrea.wav";

        if(!SharedPreferences.isMute()) {
            Media sound = new Media(new File(filename).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.seek(Duration.millis(0));
            mediaPlayer.play();
        }
    }

    public void playGoAgain(int amt) {
        String filename;
        if (!players && whosTurn) {
            if(SharedPreferences.getLanguage().equals("English"))
            filename = "sounds/haha_grant.wav";
            else if(SharedPreferences.getLanguage().equals("Espanol"))
                filename = "sounds/haha_juan.wav";
            else //French
                filename = "sounds/haha_andrea.wav";
        }
        else
            filename = "sounds/go_again.wav";
        if(!SharedPreferences.isMute()) {
            Media sound = new Media(new File(filename).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.seek(Duration.millis(0));
            PauseTransition pt_sound = new PauseTransition(Duration.seconds(amt / 2.0));
            pt_sound.setOnFinished(event -> mediaPlayer.play());
            pt_sound.play();
        }
    }

    public void playVictory() {
        int playerOneScore = Integer.parseInt(p1_mancala_label.getText());
        int playerTwoScore = Integer.parseInt(p2_mancala_label.getText());

        String filename;
        if (!players) {
            if (playerOneScore > playerTwoScore) {
                //player one wins
                if (SharedPreferences.getLanguage().equals("English"))
                    filename = "sounds/beaten_by_computer_grant.wav";
                else if (SharedPreferences.getLanguage().equals("Espanol"))
                    filename = "sounds/beaten_by_computer_juan.wav";
                else //French
                    filename = "sounds/beaten_by_computer_andrea.wav";
            } else
                filename = "sounds/ff7.wav";
        } else
            filename = "sounds/ff7.wav";
        if(!SharedPreferences.isMute()) {
            Media sound = new Media(new File(filename).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.seek(Duration.millis(0));
            mediaPlayer.play();
        }
    }

    public int choiceByPC(int amt, boolean forGoAgain) {
        cputurn = forGoAgain; //end turn
        ArrayList<Integer> mList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Button button = pile_list_button.get(i);
            if (button.getText().compareTo("0") != 0) {
                mList.add(i);
            }
        }
        Random ran = new Random();
        int x = mList.size();
        int randNum = -1;
        if (x > 0) {
            randNum = ran.nextInt(x);
            Button button = pile_list_button.get(mList.get(randNum));
            button.fire();
        } else {
            endGame();
            Victory(amt);
        }
        return randNum;
    }
}
