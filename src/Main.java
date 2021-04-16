import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("./utils/sample.fxml"));
        primaryStage.setTitle("Disk Scheduling algorithms");
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.show();
        primaryStage.getIcons().add(new Image("utils/icon1.png"));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
