
import java.util.Optional;
import javafx.event.EventType;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 *
 * @author DDvory
 */
public class ConProject {

    //prida split panel, instance na project, doprava prazdny strom do leva zatim hovno, ale bude tam prazdny borderPane
    //alert pro vytvoreni projektu
    private static ConProject INSTANCE;
    private Project project;
    private ConWindow conWindow;
    private TreeView<ConWindow> treeView;

    private ConProject(Project project) {
        this.project = project;
    }

    public static void loadProject(Project project) {
        INSTANCE = new ConProject(project);
        INSTANCE.conWindow = ConWindow.createWindow(project);
        INSTANCE.treeView = new TreeView<>(INSTANCE.conWindow.getWindows());
        INSTANCE.setTVHandler();
    }

    public static void createProject() {

    }

    private void setTVHandler() {
        treeView.setOnMouseClicked(e -> {
            if (e.isSecondaryButtonDown()) {
                TreeItem<ConWindow> selectedItem = treeView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    showContextMenu();
                }
            }

        });
    }

    private void showContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem add = new MenuItem("Přidat list");
        MenuItem remove = new MenuItem("Odebrat list");
        remove.setOnAction(e -> {
            ConWindow value = treeView.getSelectionModel().getSelectedItem().getValue();
            value.remove();
        });
        add.setOnAction(e -> {
            ConWindow value = treeView.getSelectionModel().getSelectedItem().getValue();
            value.createPanel();
        });
    }

    public static Project CreateProjectAlert() {
        GridPane gridPane = new GridPane();
        TextField name;
        TextField language;
        TextField font;
        Label messageLabel = new Label();
        gridPane.add(new Label("Název projektu: "), 0, 0);
        gridPane.add(name = new TextField(), 1, 0);

        gridPane.add(new Label("Velikost písma: "), 0, 2);

        gridPane.add(font = new TextField(), 1, 2);
        gridPane.add(new Label("Jazyk: "), 0, 3);
        gridPane.add(language = new TextField(), 1, 3);
        gridPane.add(messageLabel, 0, 4, 2, 1);
        Alert alert = Main.getAlert(Alert.AlertType.CONFIRMATION, "Založit projekt", "", "", gridPane);
        
        boolean bool = false;
        String nameData = "";
        int fontData = 0;
        String langData = "";
        
        while (!bool) {
            bool = true;
            Optional<ButtonType> bt = alert.showAndWait();
            if (bt.get() == ButtonType.OK) {
                /////////////////////////////////////////////////////////////////////////////////////////////////
                 nameData = name.getText().trim();
                 langData = language.getText().trim();
                if (nameData.isEmpty()) {
                    Main.SetErrorHandler(name, messageLabel, "Název projektu je příliš krátký");
                    bool = false;
                } else {
                    Main.SetUnErrorHandler(name, messageLabel);

                }
                try {
                    fontData = Integer.decode(font.getText());
                    Main.SetUnErrorHandler(font, messageLabel);
                } catch (Exception e) {
                    Main.SetErrorHandler(font, messageLabel, "Zadaná hodnota není číslo");
                    bool = false;
                }
                if (langData.isEmpty()) {
                    Main.SetErrorHandler(language, messageLabel, "Název jazyka je příliš krátký");
                    bool = false;
                } else {
                    Main.SetUnErrorHandler(language, messageLabel);
                }
                /////////////////////////////////////////////////////////////////////////////////////////////////
            } else {
                return null;
            }
        }
        return new Project(fontData, langData, nameData, null, null, null);
    }

}
