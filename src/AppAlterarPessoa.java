import Agenda.Agenda;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppAlterarPessoa extends Application {

    private static Stage stage;

    public AppAlterarPessoa(Agenda a){
        AlterarPessoaController.setAgenda(a);;
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage thirdStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("thirdStage.fxml"));
        Scene scene = new Scene(root);
        thirdStage.setTitle("Alterar Agendamento");
        thirdStage.setScene(scene);
        thirdStage.show();
        setStage(thirdStage);

    }

    public static Stage getStage(){
        return stage;
    }

    public void setStage(Stage stage){
        AppAlterarPessoa.stage = stage;
    }
    
}
