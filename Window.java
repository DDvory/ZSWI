import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;


public class Window extends ASelectable {
	private String name;
	private Load load;
	private List<Window> window;
	private Panel panel;
        
        private Label view_label;
        private TreeItem<Label> view_tree;

    public Window(int ID,String name, Load load, List<Window> window, Panel panel) {
        super(ID);
        this.name = name;
        this.load = load;
        this.window = window;
        this.panel = panel;
        view_label = new Label(name);
    }

    public String getName() {
        return name;
    }

    public Load getLoad() {
        return load;
    }

    public List<Window> getWindow() {
        return window;
    }

    public Panel getPanel() {
        return panel;
    }
    @Override
    public Node getView() {
        return panel.getView();
    }
    public TreeItem<Label> getTreeItem(){
        view_tree = new TreeItem(view_label);
        for (Window win : window) {
            view_tree.getChildren().add(win.getTreeItem());
        }
        return view_tree;
    }
        
}
