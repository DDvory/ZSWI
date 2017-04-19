
import java.util.List;
import javafx.scene.Node;

/**
 *
 * @author DDvory
 */
public class ConPanel {
    private Panel panel;
    private List<ConTable> tables;

    private ConPanel(Panel panel) {
        this.panel = panel;
    }
    
    public static ConPanel createPanel(Panel panel) {
        ConPanel pane = new ConPanel(panel);
        List<Table> tables = panel.getTables();
        for (Table table : tables) {
            pane.tables.add(ConTable.createTable(table));
        }
        
        return null;
    }
    public Panel getPanel() {
        return panel;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

}
