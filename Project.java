import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;



public class Project extends Window {
	private String fontSize;
	private String language;
	private List<String> registeredLanguages;
	
	//bottom language + pisma

    public Project(int ID, String name, Load load, List<Window> window, Panel panel,String fontSize, String language, List<String> registeredLanguages) {
        super(ID, name, load, window, panel);
        this.fontSize = fontSize;
        this.language = language;
        this.registeredLanguages = registeredLanguages;
    }

    @Override
    public Node getView() {
        TreeItem<Label> treeItem = this.getTreeItem();
        TreeView<Label> tv = new TreeView(treeItem);
        return tv;
    }
    

}
