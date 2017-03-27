
import javafx.scene.Node;
import javafx.scene.control.Label;


/**
 *
 * @author DDvory
 */
public class Row extends ASelectable{
       Label[] view;
       Item[] items;

    public Row(int ID, Item[] items) {
        super(ID);
        this.view = new Label[items.length];
        this.items = items;
        for (int i = 0; i < items.length; i++) {
           view[i] = items[i].getLabel();
            
        }
    }

    public Item[] getItems() {
        return items;
    }
    public Item getItems(int a) {
        return items[a];
    }

    public Label[] getLabels() {
        return view;
    }
    public Label getLabel(int a ) {
        return view[a];
    }

    @Override
    public Node getView() {
        return null;
    }
    
       
}
