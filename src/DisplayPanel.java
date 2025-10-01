import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel {

    protected JTextField displayfield;

    public DisplayPanel(){

        this.setLayout(new BorderLayout());

        displayfield = new JTextField();
        displayfield.setEditable(false);
        displayfield.setHorizontalAlignment(JTextField.RIGHT);
        this.add(displayfield,BorderLayout.CENTER);

        }

    public void setDisplayText(String text) {
        displayfield.setText(text);
    }

    public String getDisplayText(){
        return displayfield.getText();
    }





}
