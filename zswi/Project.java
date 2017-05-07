package zswi;


import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import zswi.FontSizeObervers.FontSize;

public class Project extends Window {
    
    private ViewProject vProject;
    private String language;

    //bottom language + pisma
    public Project(int ID, String name, List<Window> window, Panel panel, String language) {
        super(ID, name,null ,window, panel);
        init( language);
    }

    public Project( String language, String name, List<Window> windows, Panel panel) {
        super(name,null, windows, panel);
        init( language);
    }

    private void init( String language) {
        FontSize.getINSTANCE().setSize(12);
        this.language = language;
        vProject = new ViewProject(this);
    }

    public String getLanguage() {
        return language;
    }
    public static Project createProject(){
        return AlertManager.Project();
    }
}
