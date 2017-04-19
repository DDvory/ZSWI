
import java.util.List;
import java.util.Optional;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author DDvory
 */
public class ConWindow {

    private TreeItem<ConWindow> windows;
    private Window window;
    private ConPanel panel;
    private BorderPane viewPanel;
    private BorderPane panelPane;

    private ConWindow(Window window) {
        this.window = window;
        panelPane = new BorderPane();
    }

    public Window getWindow() {
        return window;
    }

    public static ConWindow createWindow(Window window) {
        ConWindow wind = new ConWindow(window);
        wind.windows = new TreeItem<>();
        List<Window> listWinds = window.getWindows();
        for (Window listWind : listWinds) {
            wind.windows.getChildren().add(new TreeItem<>(createWindow(listWind)));
        }
        if (wind.panel != null) {
            wind.viewPanel = wind.createPanel();
        }
        return wind;
    }

    public BorderPane createPanel() {
        return null;
    }

    public TreeItem<ConWindow> getWindows() {
        return windows;
    }

    public ConPanel getPanel() {
        return panel;
    }

    public BorderPane getViewPanel() {
        return viewPanel;
    }

    public void remove() {
        TreeItem<ConWindow> parent = windows.getParent();
        if (parent == null) {
            Main.getAlert(Alert.AlertType.ERROR, "ERROR", "Tato položka nemůže být odstraněna", "", null).show();
            return;
        }
        parent.getChildren().remove(windows);
        window.remove();
        System.gc();
    }

    public void add() {
    }

    //Create project project, Create Window
    

}
