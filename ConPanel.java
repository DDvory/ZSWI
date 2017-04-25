
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author DDvory
 */
public class ConPanel implements Main.Observabler {
    private Panel panel;
    private boolean status = true;
    private List<ConTable> tables;
    private ScrollPane view;

    private ConPanel(Panel panel) {
        this.panel = panel;
        tables = new ArrayList<>();
        view = new ScrollPane();
        view.setFitToWidth(true);
        notificate();
    }

    public boolean isStatus() {
        return status;
    }
    
    public static ConPanel createPanel(Panel panel) {
        ConPanel pane = new ConPanel(panel);
        List<Table> tables = panel.getTables();
        for (Table table : tables) {
            pane.tables.add(ConTable.createTable(table));
        }
        return pane;
    }
    
    public Panel getPanel() {
        return panel;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }
    public void createNewTable(){
        Table tb = ctAlert();
        if(tb!=null)tables.add(ConTable.createTable(tb));
        notificate();
    }

    @Override
    public void notificate() {
        VBox vBox = new VBox();
        for (ConTable table : tables) {
            vBox.getChildren().add(table.getView());
        }
        view.setContent(vBox);
    }

    @Override
    public ScrollPane getView() {
        return view;
    }
    public static Table ctAlert() {
        GridPane gridPane = new GridPane();
        int count = 1;
        String[] names = null;
        HBox hbox = new HBox();
        TextField name = new TextField();
        Label lb = new Label("1");
        VBox box = new VBox();
        Button add = new Button("+");
        Button remove = new Button("-");
        ScrollPane scrollPane = new ScrollPane(box);
        scrollPane.setPrefHeight(100);
        hbox.getChildren().addAll(lb,new Label(" "), remove,new Label(" "),add);
        scrollPane.setScaleShape(false);
        Label messageLabel = new Label();
        gridPane.add(new Label("Název tabulky: "), 0, 0);
        gridPane.add(name  , 1, 0);
        
        gridPane.add(new Label("Počet sloupců: "), 0, 1);
        gridPane.add(hbox  , 1, 1);
        
        gridPane.add(new Label("Názvy sloupců: "), 0, 2);
        gridPane.add(scrollPane , 1, 2);
         box.getChildren().add(new TextField("1"));
        
        add.setOnMouseClicked(e->{
            int decode = Integer.decode(lb.getText())+1;
            lb.setText((decode)+"");
            box.getChildren().add(new TextField(decode+""));
        });
        remove.setOnMouseClicked(e->{
            int decode = Integer.decode(lb.getText())-1;
            lb.setText((decode>=1?decode:1)+"");
            if(decode>=1)
            box.getChildren().remove(decode);
        });
        
        gridPane.add(messageLabel, 0, 4, 2, 1);
        Alert alert = Main.getAlert(Alert.AlertType.CONFIRMATION, "Přidat tabulku", "", "", gridPane);
        alert.setResizable(true);
        boolean bool = false;
        String nameData = "";

        while (!bool) {
            bool = true;
            Optional<ButtonType> bt = alert.showAndWait();
            if (bt.get() == ButtonType.OK) {
                /////////////////////////////////////////////////////////////////////////////////////////////////
                nameData = name.getText().trim();
                
                if (nameData.isEmpty()) {
                    Main.SetErrorHandler(name, messageLabel, "Název tabulky je příliš krátký");
                    bool = false;
                } else {
                    Main.SetUnErrorHandler(name, messageLabel);
                }
                ObservableList<Node> children = box.getChildren();
                count = Integer.decode(lb.getText());
                names  = new String[count];
                for (int i = 0; i < children.size(); i++) {
                    names[i] = ((TextField)children.get(i)).getText();
                }
                /////////////////////////////////////////////////////////////////////////////////////////////////
            } else {
                return null;
            }
        }
        return new Table(nameData,count,null,names);
    }
}
