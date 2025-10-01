import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel {
    private JButton[] buttons;
    private String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
    };

    private DisplayPanel displayPanel;

    private CalcLogic calculate = new CalcLogic();

    public ButtonPanel(DisplayPanel displaypanel){
        displayPanel=displaypanel;

        this.setLayout(new GridLayout(4,4,5,5));

        buttons = new JButton[buttonLabels.length];

        for (int i=0; i< buttonLabels.length;i++){
            buttons[i]= new JButton(buttonLabels[i]);
            buttons[i].addActionListener(new ButtonClickListener());
            this.add(buttons[i]);
        }


    }

    private class ButtonClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String label = source.getText();

            if (String.valueOf("=").equals(label)) {
                String text = displayPanel.getDisplayText();
                displayPanel.setDisplayText(calculate.result(text));
            }else if(String.valueOf("C").equals(label)){
                displayPanel.setDisplayText("");
            }else{
                displayPanel.setDisplayText(displayPanel.getDisplayText() + label);
            }






        }
    }
}
