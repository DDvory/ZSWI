
import java.util.List;
import javafx.scene.Node;
import javafx.scene.layout.VBox;



public class Panel extends ASelectable {
    private List<Table> tables;
	
    public Panel(int ID,List<Table> tables) {
        super(ID);
        this.tables = tables;
    }

    public Panel(List<Table> tables) {
        this.tables = tables;
    }

    public List<Table> getTables() {
        return tables;
    }
         
}
