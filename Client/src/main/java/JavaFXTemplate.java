import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;


public class JavaFXTemplate extends Application {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
//		Client client = new Client();
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/Welcome.fxml"));
		Parent root = fxmlLoader.load();
		root.getStylesheets().add("/STYLES/Default.css");
		MyController controller = fxmlLoader.getController(); // Get the new controller

//		ObservableList<String> obList = FXCollections.observableArrayList();
//		obList.add("Welcome");

//		Client client;
		controller.setData(primaryStage);

		Scene scene = new Scene(root, 900,655);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
