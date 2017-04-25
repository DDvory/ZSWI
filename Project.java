
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
    public Project(int ID, String name, List<Window> window, Panel panel, int fontSize, String language) {
        super(ID, name, window, panel);
        init(fontSize, language);
    }

    public Project(int fontSize, String language, String name, List<Window> windows, Panel panel) {
        super(name, windows, panel);
        init(fontSize, language);
    }

    private void init(int fontSize, String language) {
        this.fontSize = fontSize;
        this.language = language;
    }

    public int getFontSize() {
        return fontSize;
    }

    public String getLanguage() {
        return language;
    }
}
