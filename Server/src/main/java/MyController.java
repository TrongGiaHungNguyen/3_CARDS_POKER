import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.awt.*;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MyController {
    // Scenes
    @FXML public Pane root1;

    // Components
    @FXML public TextField textFieldPort;
    @FXML public ListView<String> listView;
    @FXML public Button startButton;
    @FXML public Label label;

    private Server server;
    private Stage primaryStage;
    private ObservableList<String> LV_content;

    public static String message;


    public void initialize() {
//        listView.getItems().add("");
//        LV_content = FXCollections.observableArrayList();
//        if (listView == null) return;
//        System.out.println("Hello");
//        listView.setItems(LV_content);
    }


    public void setData(Stage stage, ObservableList<String> obList){
        primaryStage = stage;
        LV_content = obList;

        if (listView == null) return;
//        System.out.println("Hello");
        listView.setItems(LV_content);
    }

    public void endServer(){
        System.exit(0);
    }

    public void startServer_Button() throws IOException {
//        startButton.setText("Hallo");
//        label.setText("Server Started");
//        label.setStyle("-fx-text-fill: yellow; -fx-font-size: 14px;");
//        label.setPrefHeight(50);
//        label.setPrefWidth(50);

        int port = Integer.parseInt(textFieldPort.getText());

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/Server.fxml"));
        Parent root = fxmlLoader.load(); // Load view into parent
        root.getStylesheets().add("/STYLES/Default.css");

        MyController controller = fxmlLoader.getController(); // Get the new controller
        controller.setData(primaryStage, LV_content);
        primaryStage.getScene().setRoot(root);

        new Thread(() -> {
            server = new Server(LV_content);
            server.runServer(port);
        }).start();


    }

//    public void sendMessage(String msg){
//        message = msg;
//        addToListView(message);
//    }
//
//    public void addToListView(String msg){
//        listView.getItems().add(msg);
//    }
//
//    public void addToListView(){
//        listView.getItems().add("Hello");
//    }


}
