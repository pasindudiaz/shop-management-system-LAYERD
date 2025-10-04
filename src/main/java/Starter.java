import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Starter extends Application {

    Stage stage = new Stage();
    @Override
    public void start(Stage stage) throws Exception {
       stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"))));
       stage.show();
       stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch();

    }

}
