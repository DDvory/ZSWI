
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.layout.VBox;



public class Panel extends ASelectable {
    private List<Table> tables;
	
    public Panel(int ID,List<Table> tables) {
        super(ID);
        this.tables = tables==null?new ArrayList<>():tables;
    }

    public Panel(List<Table> tables) {
        super();
        this.tables = tables==null?new ArrayList<>():tables;
    }

    public List<Table> getTables() {
        return tables;
    }
         
}
