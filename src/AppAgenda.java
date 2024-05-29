

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppAgenda extends Application {

    private static Stage stage;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("primaryStage.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Agenda Cadastro");
        primaryStage.setScene(scene);
        primaryStage.show();
        setStage(primaryStage);

    }

    public static Stage getStage(){
        return stage;
    }

    public void setStage(Stage stage){
        AppAgenda.stage = stage;
    }

}
