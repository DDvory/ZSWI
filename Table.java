
import java.util.ArrayList;
import java.util.List;
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
    private List<Row> listRow;
    private String[] ColumnNames;

    public Table(int ID,String name, int columnCount, List<Row> listRow,String[] ColumnNames) {
        super(ID);
        init(name, columnCount, listRow,ColumnNames);
    }

    public Table(String name, int columnCount, List<Row> listRow,String[] ColumnNames) {
        init(name, columnCount, listRow,ColumnNames);
    }
    private void init(String name, int columnCount, List<Row> listRow,String[] ColumnNames){
        this.name = name;
        this.ColumnNames = ColumnNames;
        this.columnCount = columnCount;
        this.listRow = listRow==null?new ArrayList<>():listRow;
    }

    public String getName() {
        return name;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public List<Row> getListRow() {
        return listRow;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getColumnNames(int i) {
        return ColumnNames[i];
    }
     
    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public void setListRow(ObservableList<Row> listRow) {
        this.listRow = listRow;
    }
    
    
    
}
