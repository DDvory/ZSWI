
import javafx.scene.Node;
import javafx.scene.control.Label;


/**
 *
 * @author DDvory
 */
public class Row extends ASelectable{
       Item[] items;

    public Row(int ID, Item[] items) {
        super(ID);
        this.items = items;
    }

    public Row(Item[] items) {
        this.items = items;
    }
    

    public Item[] getItems() {
        return items;
    }
    public Item getItems(int a) {
        return items[a];
    }  
}
