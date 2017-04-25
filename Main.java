
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{
    private static Main INSTANCE;
    private BorderPane root;
    private Stage stage;
    public static void main(String[] args) {
        
        Main.launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        INSTANCE = this;
        this.stage=stage;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        stage.setScene(new Scene(root = new BorderPane()));
        root.setPrefSize(screenSize.width/2, screenSize.height/2);
        ConMenu.setMenu(root);
        stage.show();
    }
    
    public void clearPanel(){
        root.setCenter(null);
    }

    public BorderPane getRoot() {
        return root;
    }

    public Stage getStage() {
        return stage;
    }
    
    public static Main getINSTANCE() {
        return INSTANCE;
    }
    
    public static Alert getAlert(AlertType type, String title, String headerText, String content, Node graphics) {
        Alert tmp = new Alert(type);
        tmp.setTitle(title);
        tmp.setHeaderText(headerText);
        tmp.setContentText(content);
        tmp.getDialogPane().setContent(graphics);
        return tmp;
    }
    public static void SetErrorHandler(Node node, Label messageLabel, String text){
        node.setStyle("-fx-control-inner-background: red");
        node.setOnMouseEntered(e->{
            messageLabel.setText("!!! -> "+text);
        });
        node.setOnMouseExited(e->{
            messageLabel.setText("");
        });
    }
    public static void SetUnErrorHandler(Node node, Label messageLabel){
        node.setStyle("-fx-control-inner-background: white");
        messageLabel.setText("");
        node.setOnMouseEntered(e->{
        });
        node.setOnMouseExited(e->{
        });
    }
     public static interface Observabler {
        public void notificate();
        public Node getView();
    }

}
