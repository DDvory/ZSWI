
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author DDvory
 */
public class ConProject{
    //Pri  pridani panelu se zeptat kdyz panel uz existuje, zda-li ho chce nahradit
    //prida split panel, instance na project, doprava prazdny strom do leva zatim hovno, ale bude tam prazdny borderPane
    //alert pro vytvoreni projektu
    private static ConProject INSTANCE;
    private Project project;
    private ConWindow conWindow;
    private TreeView<ConWindow> treeView;
    private Label errorMessage;
    private BorderPane viewPanel;
    private static final Image[] arrayImages;
    
    static{
        arrayImages = new Image[]{
            ResManager.getImage("red"),
            ResManager.getImage("orange"),
            ResManager.getImage("green")
        };
      
    }
    
    private ConProject(Project project) {
        errorMessage = new Label("");
        this.project = project;
        viewPanel = new BorderPane();
    }

    public static void loadProject(Project project) {
        if (project != null) {
            INSTANCE = new ConProject(project);
            INSTANCE.init();
        }

    }

    public static void createProject() {
        Project prj = CreateProjectAlert();
        if (prj != null) {
            INSTANCE = new ConProject(prj);
            INSTANCE.init();
        }

    }

    private void init() {
        conWindow = ConWindow.createWindow(project);
        treeView = new TreeView<>(conWindow.getWindows());
        setTVHandler();
        SplitPane spl = new SplitPane();
        spl.getItems().addAll(treeView, viewPanel);
        Main.getINSTANCE().getRoot().setCenter(spl);
    }

    private void setTVHandler() {
        treeView.setOnMousePressed(e -> {
            if (e.isSecondaryButtonDown()) {
                TreeItem<ConWindow> selectedItem = treeView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    showContextMenu(e);
                }
            }
        });
        treeView.getSelectionModel().selectedItemProperty().addListener(cl->{
            selectItem();
        });
    }
    private void selectItem(){
        TreeItem<ConWindow> item = treeView.getSelectionModel().getSelectedItem();
        if(item==null){
            this.setViewPanel(this.conWindow);
        }else{
            while (item.getValue().getPanel()==null&&item.getParent()!=null) {
                item = item.getParent();
            }
            this.setViewPanel(item.getValue());
        }
           
    }

    private void showContextMenu(MouseEvent eh) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem add = new MenuItem("Přidat větev");
        MenuItem remove = new MenuItem("Odebrat větev");
        MenuItem addPanel = new MenuItem("Přidat panel");
        MenuItem removePanel = new MenuItem("Odebrat panel");
        ConWindow value = treeView.getSelectionModel().getSelectedItem().getValue();
        remove.setOnAction(e -> {
            value.removeWindow();
        });
        add.setOnAction(e -> {
            value.createWindow();
        });
        addPanel.setOnAction(e->{
            value.addPanel();
            this.selectItem();
        });
        removePanel.setOnAction(e->{
            value.removePanel();
            this.selectItem();
        });
        contextMenu.getItems().addAll(add, remove,addPanel,removePanel);
        contextMenu.setX(eh.getScreenX());
        contextMenu.setY(eh.getScreenY());
        contextMenu.show(Main.getINSTANCE().getStage());
    }

    public static Image getStatusImage(int i) {
        try {
            return arrayImages[i];
        } catch (Exception e) {
            return null;
        }
        
    }

    public Label getErrorMessage() {
        return errorMessage;
    }
    
     public void setViewPanel(ConWindow cw){
        this.viewPanel.setCenter(cw.getView());
    }

    public static ConProject getINSTANCE() {
        return INSTANCE;
    }
    
    
    private static Project CreateProjectAlert() {
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
        return new Project(fontData, langData, nameData, null, null);
    }
   
}

