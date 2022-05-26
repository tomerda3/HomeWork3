package start;
/**
 * A CustonHELPDialog Class the show a info on the mission when we click on it.
 * @version 1.0 2 May 2022
 * @author  Tomer Damti, Nave Sfunim
 * @see AquaFrame
 */
import javax.swing.*;
public class CustomHELPDialog {
    JFrame f;
    /**
     * constructor
     * here we build the JDialog that present all the information we want too..
     */
    CustomHELPDialog(){
        f=new JFrame();
        JOptionPane.showMessageDialog(f,"Home Work 2\nGUI @ Threads.");
    }
}