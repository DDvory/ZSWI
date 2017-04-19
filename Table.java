
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

    public Table(int ID,String name, int columnCount, ObservableList<Row> listRow) {
        super(ID);
        this.name = name;
        this.columnCount = columnCount;
        this.listRow = listRow;
    }

    public Table(String name, int columnCount, ObservableList<Row> listRow) {
        this.name = name;
        this.columnCount = columnCount;
        this.listRow = listRow;
    }

    public String getName() {
        return name;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public ObservableList<Row> getListRow() {
        return listRow;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public void setListRow(ObservableList<Row> listRow) {
        this.listRow = listRow;
    }
    
    
    
}
