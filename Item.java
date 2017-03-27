
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.scene.Node;

import javafx.scene.control.Label;

public class Item extends ASelectable {

    private final String type;
    private final Label label;
    private final Meter meter;

    private String STRING = null;
    private LocalTime TIME = null;
    private LocalDate DATE = null;
    private Boolean BOOL = null;
    private Byte INT8_T = null;
    private Short INT16_T = null;
    private Integer INT32_T = null;
    private Long INT64_T = null;
    private Short UINT8_T = null;
    private Integer UINT16_T = null;
    private Long UINT32_T = null;
    private BigInteger UINT64_T = null;
    private Float FLOAT = null;
    private Double DOUBLE = null;

    public Item(int ID,String type,Meter meter) {
        super(ID);
        this.type = type;
        this.label = new Label();
        this.meter = meter;
    }

    public void setToLabel() {
        label.setText(this.getData(null));
    }

    public Label getLabel() {
        return label;
    }

    public void setData( String arg1) {
        super.setData(type, arg1);
    }

    public String getData() {
        return super.getData(type);
    }
    @Override
    public Node getView() {
        return null;
    }

}
