
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author DDvory
 */
public class ConTable implements Main.Observabler{
    //rok mesic den ->  local date
    private Table table;
    private TableView<Row> tableView;
    private ObservableList<Row> listRows;
    
    private Label nameLabel;
    private BorderPane viewPane;
    
    private ConTable(Table table) {
        this.table =table;
        viewPane = new BorderPane();
        tableView = new TableView<>();
        listRows = FXCollections.observableArrayList();
        nameLabel = new Label();
        init();
        notificate();
    }
    
    public static ConTable createTable(Table table){
        ConTable tab = new ConTable(table);
        return tab;
    }
    private void init(){
        
        BorderPane p = new BorderPane();
        //Button pro upravu tabulky -> spusti alert
        Button bt = new Button("Upravit");
        bt.setOnMouseClicked(e->{
            addLine();
        });
        p.setLeft(nameLabel);
        p.setRight(bt);
        viewPane.setTop(p);
        viewPane.setCenter(tableView);
        int columnCount = table.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            addColumn(i);
        }
        
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setItems(listRows);
        
    }
    public void addLine(){
        listRows.add(new Row(null,table.getColumnCount()));
    }
    public void addColumn(int index){
        TableColumn<Row,Item> tr = new TableColumn<>(table.getColumnNames(index));
        tr.setCellFactory(e->{
            return new TableCellFactory(index);
        });
        //tableView.set
        tableView.getColumns().add(tr);
        
    }

    @Override
    public void notificate() {
        nameLabel.setText(table.getName());
        
    }

    @Override
    public Node getView() {
        return viewPane;
    }
    private class TableCellFactory extends TableCell<Row,Item>{
        private int index;

        public TableCellFactory(int index) {
            this.index = index;
        }
        
        @Override
        protected void updateItem(Item item, boolean bln) {
            super.updateItem(item, bln); 
            if(item==null){
                this.setGraphic(null);
                this.setText("");
                return ;
            }
            if(item==null){
                Label label = new Label();
                Main.SetErrorHandler(label, ConProject.getINSTANCE().getErrorMessage(), "Buňka není nakonfigurována");
            }
        }
    }
    
}
