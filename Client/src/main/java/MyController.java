import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.sound.sampled.Port;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;

public class MyController {
    // Components
    @FXML public TextField textField_Port;
    @FXML public TextField textField_IP;
    @FXML public Button doneButton_Bet;
    @FXML public TextField anteTextField1_Bet;
    @FXML public TextField anteTextField2_Bet;
    @FXML public TextField pairPlusTextField1_Bet;
    @FXML public TextField pairPlusTextField2_Bet;
    @FXML public Button buttonPlay1_Game;
    @FXML public Button buttonPlay2_Game;
    @FXML public Button buttonFold1_Game;
    @FXML public Button buttonFold2_Game;
    @FXML public Text textPlayBet1_Game;
    @FXML public Text textPlayBet2_Game;
    @FXML public HBox hand1BackBox_Game;
    @FXML public HBox hand2BackBox_Game;
    @FXML public HBox hand1Box_Game;
    @FXML public HBox hand2Box_Game;
    @FXML public HBox dealerHandBox_Game;
    @FXML public HBox dealerBackBox_Game;
    @FXML public TextField textFieldWinAmount1_Game;
    @FXML public TextField textFieldWinAmount2_Game;
    @FXML public TextArea textAreaHistory_Game;
    @FXML public Text textAnte1_Game;
    @FXML public Text textAnte2_Game;
    @FXML public Text textPairPlus1_Game;
    @FXML public Text textPairPlus2_Game;
    @FXML public Button nextButton_Game;
    @FXML public Button restartButton_Game;
//    @FXML public TextField setAnteTextField2_Bet;
//    @FXML public TextField setAnteTextField1_Bet;


    private Client client;
    private Stage primaryStage;
    private PokerInfo pokerInfo;
    private int numActions;

    public void setData(Stage stage){
        primaryStage = stage;
    }

    public void setClient(Client client){
        this.client = client;
    }

//    private void applyStylesheet() {
//        Scene scene = primaryStage.getScene();
//        if (scene != null) {
//            scene.getStylesheets().clear();
//            URL stylesheetURL = getClass().getResource(stylePath);
//            if (stylesheetURL != null) {
//                scene.getStylesheets().add(stylesheetURL.toExternalForm());
//            } else {
//                System.out.println("Stylesheet not found: " + stylePath);
//            }
//        }
//    }

    public void callBet() throws IOException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/Bet.fxml"));
        Parent root = fxmlLoader.load(); // Load view into parent
        root.getStylesheets().add("/STYLES/Default.css");
        MyController controller = fxmlLoader.getController(); // Get the new controller
//        controller.
        controller.setData(primaryStage); // Set game data in the new controller

        int radius = 50;
        controller.doneButton_Bet.setShape(new Circle(radius));
        controller.doneButton_Bet.setMinSize(2 * radius, 2 * radius); // Set minimum size to fit the circle
        controller.doneButton_Bet.setMaxSize(2 * radius, 2 * radius);
//        controller.roundNum++;
//        controller.gameRecord += "Round: " + controller.roundNum + ":\n";

        primaryStage.getScene().setRoot(root);

        String IP_Address = textField_IP.getText();
        int numPort = Integer.parseInt(textField_Port.getText());


//        new Thread(() -> {
//            try {
//                client = new Client(IP_Address, numPort);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            } catch (ClassNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//        }).start();


        new Thread(() -> {
            try {
                // Initialize the client
                 System.out.println("Client created");
                 client = new Client(IP_Address, numPort);
                 Platform.runLater(() -> {
                     controller.setClient(client);
                     controller.client.pokerInfo = client.pokerInfo;

                     new Thread(() -> {
                         try {
                             client.startClient();
                         } catch (IOException e) {
                             throw new RuntimeException(e);
                         } catch (ClassNotFoundException e) {
                             throw new RuntimeException(e);
                         }
                     }).start();
                 });
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
//        controller.setClient(client);

//        new Thread(() -> {
//            try {
//                // Initialize the client
//                 System.out.println("Client created");
//                 client = new Client(IP_Address, numPort);
//            } catch (IOException | ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }).start();
//
//        Platform.runLater(() -> {
//            controller.setClient(client);
//            System.out.println("Client connected");
//            System.out.println(controller.client.pokerInfo.receivedBet);
//        });

    }

    private boolean isValidBet(int betAmount) {
        return betAmount >= 5 && betAmount <= 25;
    }

    private void setBet1(int ante, int pp, Player player1) {
        player1.anteBet = ante;
        player1.pairPlusBet = pp;
        player1.playBet = ante;
    }

    private void setBet2(int ante, int pp, Player player2) {
        player2.anteBet = ante;
        player2.pairPlusBet = pp;
        player2.playBet = ante;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Bet");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void handleDoneButton_Bet(ActionEvent event) throws IOException {
//        Player player1 = new Player();
//        Player player2 = new Player();
//        client.pokerInfo.newCards = true;
//        client.sendPokerInfo();

        Platform.runLater(() -> {
            try {
                int ante1Value = Integer.parseInt(anteTextField1_Bet.getText());
                int ante2Value = Integer.parseInt(anteTextField2_Bet.getText());
                int pairPlus1Value = Integer.parseInt(pairPlusTextField1_Bet.getText());
                int pairPlus2Value = Integer.parseInt(pairPlusTextField2_Bet.getText());

                if (isValidBet(ante1Value) && isValidBet(ante2Value)) {
                    if ((isValidBet(pairPlus1Value) || pairPlus1Value == 0) && (isValidBet(pairPlus2Value) || pairPlus2Value == 0)) {
                        setBet1(ante1Value, pairPlus1Value, client.pokerInfo.player1);
                        setBet2(ante2Value, pairPlus2Value, client.pokerInfo.player2);

                        callGame(event);

                    } else {
                        showAlert("All bets must be between $5 and $25.");
                    }
                } else {
                    showAlert("All bets must be between $5 and $25.");
                }
            } catch (NumberFormatException e) {
                showAlert("Please enter valid integer values for all bets.");
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    private void dealCards(ArrayList<Card> hand, HBox handBox, boolean isBack, int delayVal) {
        for (int i = 0; i < 3; i++) {
            String path;
            if (isBack) {
                path = "/cards/back.png";
            } else {
                path = hand.get(i).getImagePath();
            }

            Image img = new Image(getClass().getResourceAsStream(path));
            ImageView imageView = new ImageView(img);
            imageView.setFitHeight(112);
            imageView.setFitWidth(80);
            imageView.setOpacity(0);

            handBox.getChildren().add(imageView);
            PauseTransition pause = new PauseTransition(Duration.millis(delayVal + (i + 1) * 250));

            pause.setOnFinished(e -> imageView.setOpacity(1));

            pause.play();
        }
    }

//    private void revealDealerHand() {
//        dealCards(client.pokerInfo.dealer.dealersHand, dealerHandBox_Game, false, 0);
//
//        PauseTransition removeBackBoxPause = new PauseTransition(Duration.seconds(0.75));
//        removeBackBoxPause.setOnFinished(e -> {
//            dealerBackBox_Game.setVisible(false);
//            evaluateHands();
//        });
//        removeBackBoxPause.play();
//    }

    public void updateGameResult(){
        Platform.runLater(() -> {
            textAreaHistory_Game.setText(client.pokerInfo.gameRecord);
//            textFieldWinAmount1_Game.setText(String.valueOf(client.pokerInfo.player1.totalWinnings));
//            textFieldWinAmount2_Game.setText(String.valueOf(client.pokerInfo.player2.totalWinnings));

            textFieldWinAmount1_Game.setText("$" + client.pokerInfo.player1.totalWinnings);
            textFieldWinAmount2_Game.setText("$" + client.pokerInfo.player2.totalWinnings);
            restartButton_Game.setVisible(true);
            nextButton_Game.setVisible(true);

            restartButton_Game.setDisable(false);
            nextButton_Game.setDisable(false);
        });
    }

    public void handleButtonPlay1(ActionEvent event) throws IOException {
        numActions++;
        buttonPlay1_Game.setDisable(true);
        buttonFold1_Game.setDisable(true);
        textPlayBet1_Game.setText("$" + client.pokerInfo.player1.playBet);

        client.pokerInfo.player1.playBet = client.pokerInfo.player1.anteBet;

        if (numActions == 2) {
            client.sendPokerInfo();
//            PauseTransition pause = new PauseTransition(Duration.seconds(1));
//            pause.setOnFinished(e -> {
                dealCards(client.pokerInfo.dealer.dealersHand, dealerHandBox_Game, false, 0);
                dealerBackBox_Game.setVisible(false);
//            });
//            pause.play();

//            updateGameResult();
            Platform.runLater(() -> {updateGameResult();});
        }
    }

    public void handleButtonFold1(ActionEvent event) throws IOException {
        client.pokerInfo.player1.isFold = true;
        numActions++;
        dealCards(client.pokerInfo.player1.hand, hand1BackBox_Game, true, 0);

        buttonPlay1_Game.setDisable(true);
        buttonFold1_Game.setDisable(true);
        textPlayBet1_Game.setText("$" + client.pokerInfo.player1.playBet);

        if (numActions == 2) {
            client.sendPokerInfo();
//            PauseTransition pause = new PauseTransition(Duration.seconds(1));
//            pause.setOnFinished(e -> {
                dealCards(client.pokerInfo.dealer.dealersHand, dealerHandBox_Game, false, 0);
                dealerBackBox_Game.setVisible(false);
//            });
//            pause.play();


//            updateGameResult();
            Platform.runLater(() -> {updateGameResult();});
        }
    }

    public void handleButtonFold2(ActionEvent event) throws IOException {
        client.pokerInfo.player2.isFold = true;
        numActions++;
        dealCards(client.pokerInfo.player2.hand, hand2BackBox_Game, true, 0);

        buttonPlay2_Game.setDisable(true);
        buttonFold2_Game.setDisable(true);
        textPlayBet2_Game.setText("$" + client.pokerInfo.player2.playBet);

        if (numActions == 2) {
            client.sendPokerInfo();
//            PauseTransition pause = new PauseTransition(Duration.seconds(1));
//            pause.setOnFinished(e -> {
                dealCards(client.pokerInfo.dealer.dealersHand, dealerHandBox_Game, false, 0);
                dealerBackBox_Game.setVisible(false);
//            });
//            pause.play();


            Platform.runLater(() -> {updateGameResult();});
        }
    }

    public void handleButtonPlay2(ActionEvent event) throws IOException {
        numActions++;
        buttonPlay2_Game.setDisable(true);
        buttonFold2_Game.setDisable(true);
        textPlayBet2_Game.setText("$" + client.pokerInfo.player2.playBet);

        client.pokerInfo.player2.playBet = client.pokerInfo.player2.anteBet;

        if (numActions == 2) {
            client.sendPokerInfo();
//            PauseTransition pause = new PauseTransition(Duration.seconds(1));
//            pause.setOnFinished(e -> {
                dealCards(client.pokerInfo.dealer.dealersHand, dealerHandBox_Game, false, 0);
                dealerBackBox_Game.setVisible(false);
//            });
//            pause.play();


//            updateGameResult();
            Platform.runLater(() -> {updateGameResult();});
        }
    }

    private void setAnteTextField1_Bet(String text, boolean state) {
        anteTextField1_Bet.setText(text);
        anteTextField1_Bet.setEditable(state);
    }

    private void setAnteTextField2_Bet(String text, boolean state) {
        anteTextField2_Bet.setText(text);
        anteTextField2_Bet.setEditable(state);
    }

    private void showAnteMsg(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ante Information");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public void handleNextButton_Game() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/Bet.fxml"));
        Parent root = fxmlLoader.load();
        root.getStylesheets().add("/STYLES/Default.css");
        MyController controller = fxmlLoader.getController();
//        controller.setGameData(player1, player2, dealer, gameRecord, roundNum, stylePath, primaryStage);
        controller.setData(primaryStage);
        controller.setClient(client);

        int radius = 50;
        controller.doneButton_Bet.setShape(new Circle(radius));
        controller.doneButton_Bet.setMinSize(2 * radius, 2 * radius);
        controller.doneButton_Bet.setMaxSize(2 * radius, 2 * radius);

        client.pokerInfo.roundNum++;
        client.pokerInfo.gameRecord += "Round " + client.pokerInfo.roundNum + ":\n";

        primaryStage.getScene().setRoot(root);
//        applyStylesheet();

        String msg;
        if (client.pokerInfo.player1.anteBet != 0 || client.pokerInfo.player2.anteBet != 0) {
            if (client.pokerInfo.player1.anteBet == 0 && client.pokerInfo.player2.anteBet != 0) {
                msg = "Player 2's ante was pushed from the previous round.\n";
                controller.setAnteTextField2_Bet(String.valueOf(client.pokerInfo.player2.anteBet), false);
            } else if (client.pokerInfo.player1.anteBet != 0 && client.pokerInfo.player2.anteBet == 0) {
                msg = "Player 1's ante was pushed from the previous round.\n";
                controller.setAnteTextField1_Bet(String.valueOf(client.pokerInfo.player1.anteBet), false);
            } else {
                msg = "Both players' antes were pushed from the previous round.\n";
                controller.setAnteTextField2_Bet(String.valueOf(client.pokerInfo.player2.anteBet), false);
                controller.setAnteTextField1_Bet(String.valueOf(client.pokerInfo.player1.anteBet), false);
            }

            PauseTransition pause = new PauseTransition(Duration.millis(500));
            pause.setOnFinished(e -> Platform.runLater(() -> showAnteMsg(msg)));
            pause.play();
        }
    }

    public void handleRestartButton_Game() throws IOException {
        client.pokerInfo.player1 = new Player();
        client.pokerInfo.player2 = new Player();
        client.pokerInfo.dealer = new Dealer();
        client.pokerInfo.gameRecord = "";
        client.pokerInfo.roundNum = 0;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/Welcome.fxml"));
        Parent root = fxmlLoader.load();
        root.getStylesheets().add("/STYLES/Default.css");
        MyController controller = fxmlLoader.getController();
        controller.setData(primaryStage);
        controller.setClient(client);
//        controller.setGameData(player1, player2, dealer, gameRecord, roundNum, stylePath, primaryStage);

        primaryStage.getScene().setRoot(root);
//        applyStylesheet();
    }

    public void handleLook1(){

    }

    public void handleLook2(){

    }

    public void handleDefaultLook(){

    }

    public void callExit_Game(){
        System.exit(0);
    }

    public void callGame(ActionEvent e) throws IOException {
        numActions = 0;
//        player1.hand = dealer.dealHand();
//        player2.hand = dealer.dealHand();
//        dealer.dealersHand = dealer.dealHand();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/Game.fxml"));
        Parent root = fxmlLoader.load();
        root.getStylesheets().add("/STYLES/Default.css");
        MyController controller = fxmlLoader.getController();
        controller.setData(primaryStage);
        controller.setClient(client);

//        dealCards(client.pokerInfo.player1.hand, hand1BackBox_Game, false, 0);
//        dealCards(client.pokerInfo.player2.hand, hand2BackBox_Game, false, 0);
//        dealCards(client.pokerInfo.dealer.dealersHand, hand2BackBox_Game, false, 0);

        controller.textAnte1_Game.setText("$" + client.pokerInfo.player1.anteBet);
        controller.textAnte2_Game.setText("$" + client.pokerInfo.player2.anteBet);
        controller.textPairPlus1_Game.setText("$" + client.pokerInfo.player1.pairPlusBet);
        controller.textPairPlus2_Game.setText("$" + client.pokerInfo.player2.pairPlusBet);

        controller.dealCards(client.pokerInfo.player1.hand, controller.hand1Box_Game, false, 0);
        controller.dealCards(client.pokerInfo.player2.hand, controller.hand2Box_Game, false, 0);
//        controller.dealCards(client.pokerInfo.dealer.dealersHand, controller.dealerHandBox_Game, false, 0);
        controller.dealCards(client.pokerInfo.dealer.dealersHand, controller.dealerBackBox_Game, true, 0);

        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            controller.buttonPlay1_Game.setDisable(false);
            controller.buttonFold1_Game.setDisable(false);
            controller.buttonPlay2_Game.setDisable(false);
            controller.buttonFold2_Game.setDisable(false);
            controller.textFieldWinAmount1_Game.setText("$" + client.pokerInfo.player1.totalWinnings);
            controller.textFieldWinAmount2_Game.setText("$" + client.pokerInfo.player2.totalWinnings);
//            controller.dealerBackBox_Game.setDisable(true);
        });
        pause.play();

//        controller.setGameData(player1, player2, dealer, gameRecord, roundNum, stylePath, primaryStage);
//
//        controller.dealCards(player1.hand, controller.hand1Box_Game, false, 0);
//        controller.dealCards(player2.hand, controller.hand2Box_Game, false, 750);
//        controller.dealCards(dealer.dealersHand, controller.dealerBackBox_Game, true, 1500);
//
//        controller.textAnte1_Game.setText("$" + player1.anteBet);
//        controller.textAnte2_Game.setText("$" + player2.anteBet);
//        controller.textPairPlus1_Game.setText("$" + player1.pairPlusBet);
//        controller.textPairPlus2_Game.setText("$" + player2.pairPlusBet);
//
//        PauseTransition pause = new PauseTransition(Duration.seconds(2.5));
//        pause.setOnFinished(event -> {
//            controller.buttonPlay1_Game.setDisable(false);
//            controller.buttonFold1_Game.setDisable(false);
//            controller.buttonPlay2_Game.setDisable(false);
//            controller.buttonFold2_Game.setDisable(false);
//            controller.textFieldWinAmount1_Game.setText("$" + player1.totalWinnings);
//            controller.textFieldWinAmount2_Game.setText("$" + player2.totalWinnings);
//        });
//        pause.play();

        primaryStage.getScene().setRoot(root);
//        applyStylesheet();
    }
}
