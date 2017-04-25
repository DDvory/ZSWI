
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author DDvory
 */
public class ConWindow implements  Main.Observabler {
    private TreeItem<ConWindow> windows;
    private Window window;
    private ConPanel panel;
    private BorderPane panelPane;
    private Label treeLabel;
    private Label panelLabel;
    private ImageView statusCircle;
    
    private ConWindow(Window window) {
        this.window = window;
        
        treeLabel = new Label();
        panelLabel = new Label();
        statusCircle =new ImageView();
        statusCircle.setFitWidth(15);
        statusCircle.setFitHeight(15);
        
        setName(window.getName());
        updateStatusImage();
        notificate();
    }
    public void setName(String name){
        window.setName(name);
        notificate();
        
    }
    public Window getWindow() {
        return window;
    }
    public void updateStatusImage(){
        int i =2;
        if(panel==null)i = 1;
        else if(!panel.isStatus())i = 0;
        statusCircle.setImage(ConProject.getStatusImage(i));
    }

    public static ConWindow createWindow(Window window) {
        ConWindow wind = new ConWindow(window);
        wind.createTree();
        wind.notificate();
        return wind;
    }
    public TreeItem<ConWindow> createTree(){
        windows = new TreeItem<>(this);
        List<Window> listWinds = window.getWindows();
        for (Window listWind : listWinds) {
            ConWindow w = ConWindow.this.createWindow(listWind);
            windows.getChildren().add(w.createTree());
        }
        HBox box = new HBox();
        box.getChildren().addAll(this.getStatusCircle(),treeLabel);
        windows.setGraphic(box);
        return windows;
    }

    @Override
    public String toString() {
        return "";
    }
    

    public TreeItem<ConWindow> getWindows() {
        return windows;
    }

    public ConPanel getPanel() {
        return panel;
    }

    public ImageView getStatusCircle() {
        return statusCircle;
    }

    @Override
    public BorderPane getView() {
        return panelPane;
    }
    

    public void removeWindow() {
        TreeItem<ConWindow> parent = windows.getParent();
        if (parent == null) {
            Main.getAlert(Alert.AlertType.ERROR, "ERROR", "Tato položka nemůže být odstraněna", "", null).show();
            return;
        }
        parent.getChildren().remove(windows);
        parent.getValue().getWindow().remove(window);
        notificate();
        System.gc();
    }

        public void createWindow() {
            Window w = cwAlert();
            if(w==null)return;
            ConWindow cw = ConWindow.this.createWindow(w);
            this.windows.getChildren().add(cw.getWindows());
            this.getWindow().getWindows().add(w);
            this.windows.setExpanded(true);
            this.notificate();
        }

        private static Window cwAlert() {
        GridPane gridPane = new GridPane();
        TextField name;
        Label messageLabel = new Label();
        gridPane.add(new Label("Název Okna: "), 0, 0);
        gridPane.add(name = new TextField(), 1, 0);
        gridPane.add(messageLabel, 0, 4, 2, 1);
        Alert alert = Main.getAlert(Alert.AlertType.CONFIRMATION, "Založit projekt", "", "", gridPane);

        boolean bool = false;
        String nameData = "";

        while (!bool) {
            bool = true;
            Optional<ButtonType> bt = alert.showAndWait();
            if (bt.get() == ButtonType.OK) {
                /////////////////////////////////////////////////////////////////////////////////////////////////
                nameData = name.getText().trim();
                
                if (nameData.isEmpty()) {
                    Main.SetErrorHandler(name, messageLabel, "Název projektu je příliš krátký");
                    bool = false;
                } else {
                    Main.SetUnErrorHandler(name, messageLabel);
                }
                /////////////////////////////////////////////////////////////////////////////////////////////////
            } else {
                return null;
            }
        }
        return new Window(nameData, null, null);
    }

    void addPanel() {
        Panel pa = new Panel(null);
        panel = ConPanel.createPanel(pa);
        window.setPanel(pa);
        createViewPanel(this);
        notificate();
    }

    void removePanel() {
        window.setPanel(null);
        panelPane = null;
        panel=null;
    }
    private static void createViewPanel(ConWindow cw){
        BorderPane panel = new BorderPane();
        BorderPane topPanel = new BorderPane();
        panel.setTop(topPanel);
        topPanel.setLeft(cw.panelLabel);
        //Button pro vytvoreni nove tabulky
        Button bt = new Button("+");
        bt.setOnAction(e->{
            cw.getPanel().createNewTable();
        });
        topPanel.setRight(bt);
        cw.setPanelPane(panel);
        
    }

    public void setPanelPane(BorderPane panelPane) {
        this.panelPane = panelPane;
        notificate();
    }
    
    public Label getTreeLabel() {
        return treeLabel;
    }

    @Override
    public void notificate() {
        treeLabel.setText(window.getName());
        panelLabel.setText(window.getName());
        if(panel!=null)
        panelPane.setCenter(panel.getView());
        this.updateStatusImage();
    }
    
}
