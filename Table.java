
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

/**
 *
 * @author DDvory
 */
public class Table extends ASelectable {
    private String name;
    private int columnCount;
    private ObservableList<Row> listRow;
    
    private Label view_name;
    private TableView view_table;

    public Table(int ID,String name, int columnCount, ObservableList<Row> listRow) {
        super(ID);
        this.name = name;
        this.columnCount = columnCount;
        this.listRow = listRow;
    }
    @Override
    public Node getView() {
        return null;
    }
    
    
}
