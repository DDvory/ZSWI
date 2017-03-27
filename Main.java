
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
        Main.launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        
    }
    public static Alert getAlert(AlertType type, String title, String headerText, String content, Node graphics) {
        Alert tmp = new Alert(type);
        tmp.setTitle(title);
        tmp.setHeaderText(headerText);
        tmp.setContentText(content);
        tmp.getDialogPane().setContent(graphics);
        return tmp;
    }


}
