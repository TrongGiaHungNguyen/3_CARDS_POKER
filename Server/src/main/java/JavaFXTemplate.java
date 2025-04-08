import javafx.application.Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class JavaFXTemplate extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/Welcome.fxml"));
		Parent root = fxmlLoader.load();
		root.getStylesheets().add("/STYLES/Default.css");
		MyController controller = fxmlLoader.getController(); // Get the new controller

		ObservableList<String> obList = FXCollections.observableArrayList();
//		obList.add("Welcome");

		controller.setData(primaryStage, obList);
	     
		Scene scene = new Scene(root, 900,655);
		primaryStage.setScene(scene);
		primaryStage.show();

//		Server server = new Server();
//		server.runServer();
	}

}
