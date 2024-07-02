import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppMainScreen extends Application {
    private static Stage stage;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage secondStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("secondStage.fxml"));
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("mainScreen.css").toExternalForm();
        scene.getStylesheets().add(css);
        secondStage.setTitle("Tela De Visualizacao");
        secondStage.setScene(scene);
        secondStage.show();
        setStage(secondStage);

    }

    public static Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        AppMainScreen.stage = stage;
    }

}
