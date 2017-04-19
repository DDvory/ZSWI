import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;


public class Window extends ASelectable {
	private String name;
        private Window parent;
	private List<Window> windows;
	private Panel panel;

    public Window(int ID,String name, List<Window> windows,Window parent, Panel panel) {
        super(ID);
        this.name = name;
        this.parent = parent;
        this.windows = windows==null?new ArrayList<>():windows;
        this.panel = panel;
    }

    public Window(String name, Window parent, List<Window> windows, Panel panel) {
        this.name = name;
        this.parent = parent;
        this.windows = windows==null?new ArrayList<>():windows;
        this.panel = panel;
    }
    

    public String getName() {
        return name;
    }

    public List<Window> getWindows() {
        return windows;
    }

    public Panel getPanel() {
        return panel;
    }
    
    public void remove(){
        Window par =this.parent;
        parent.getWindows().remove(this);
    }
        
}
