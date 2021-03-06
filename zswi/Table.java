package zswi;


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
    private ViewTable vTable;
    private List<Item> listItems;

    public Table(int ID,String name, int columnCount, List<Item> listItems) {
        super(ID);
        init(name, listItems);
    }

    public Table(String name, List<Item> listRow) {
        init(name, listRow);
    }
    private void init(String name, List<Item> listRow){
        this.name = name;
        this.listItems = listRow==null?new ArrayList<>():listRow;
        vTable = new ViewTable(this);
        vTable.notificate();
    }
    public void createItem(){
        Item item  =AlertManager.Item();
        if(item!=null)listItems.add(item);
        vTable.notificate();
    }
    public void removeItem(Item item){
        listItems.remove(item);
        vTable.notificate();
    }
    public String getName() {
        return name;
    }

    public List<Item> getListItems() {
        return listItems;
    }
    
    public void setName(String name) {
        this.name = name;
        vTable.notificate();
    } 

    public ViewTable getvTable() {
        return vTable;
    }
    
}
