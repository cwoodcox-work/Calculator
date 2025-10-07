import javax.swing.*;
import java.awt.*;

public class CalculatorApp {


    static void main() {

        int frameWidth = 300;
        int frameHeight = 500;
        JFrame frame = new JFrame("Calculator");
        frame.setSize(frameWidth,frameHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        DisplayPanel displayPanel = new DisplayPanel();


        ButtonPanel buttonPanel = new ButtonPanel(displayPanel);


        frame.add(displayPanel,BorderLayout.NORTH);
        frame.add(buttonPanel,BorderLayout.CENTER);

        frame.setVisible(true);




    }
}
