import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;



public class Project extends Window {
	private int fontSize;
	private String language;
	
	//bottom language + pisma

    public Project(int ID, String name, List<Window> window, Panel panel,int fontSize, String language) {
        super(ID, name, window,null, panel);
        this.fontSize = fontSize;
        this.language = language;
    }

    public Project(int fontSize, String language,  String name, Window parent, List<Window> windows, Panel panel) {
        super(name, parent, windows, panel);
        this.fontSize = fontSize;
        this.language = language;
    }
    

    

}
