import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;


public class Window extends ASelectable {
	private String name;
	private List<Window> windows;
	private Panel panel;

    public Window(int ID,String name, List<Window> windows, Panel panel) {
        super(ID);
        init(name, windows, panel);
    }

    public Window(String name, List<Window> windows, Panel panel) {
        super();
        init(name, windows, panel);
        
    }
    private void init(String name, List<Window> windows, Panel panel){
        this.name = name;
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
    
    public void remove(Window wind){
        windows.remove(wind);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }
    
        
}
