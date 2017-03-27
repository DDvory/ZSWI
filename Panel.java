
import java.util.List;
import javafx.scene.Node;
import javafx.scene.layout.VBox;



public class Panel extends ASelectable {
        private List<Table> tables;
	
        private VBox view_Box;

    public Panel(int ID,List<Table> tables) {
        super(ID);
        this.tables = tables;
    }
    @Override
    public Node getView() {
        return null;
    }
         
}
