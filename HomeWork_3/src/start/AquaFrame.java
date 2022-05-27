package start;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
/**
 * A AquaFrame is a Class that is extended JFrame which is the frame where all the component are paste on.
 * @version 1.0 2 May 2022
 * @author  Tomer Damti, Nave Sfunim
 * @see AquaFrame
 */
public class AquaFrame extends JFrame implements ActionListener{
    public static void main(String[] args) {
        // Create a frame and container for the panels.
        AquaFrame frameAqua = new AquaFrame("my Aquarium");

        // Exit when the window is closed.
        frameAqua.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Show the converter.
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        frameAqua.setBounds(dimension.width / 2 - 400, dimension.height / 2 - 300, 950, 500);
        frameAqua.setVisible(true);
    }

    private final JMenuItem jmenuitemExit, jmenuitemHelp, jmenuitemImage, jmenuitemBlue, jmenuitemNone;
    public AquaPanel panelAqua;
    public Image backImage;
    /**
     * AquaFrame constructor is building all the component on the GUI.
     * it have a menu that have a info button, setbacground button, exit button.
     *
     * on the frame we add the aquaPanel which is main displayin the program.
     */
    public AquaFrame(String title) {
        super(title);
        JMenu jmenuFile = new JMenu("File");
        JMenu jmenuHelp = new JMenu("Help");
        JMenu jMenuBackground = new JMenu("BackGround");
        jmenuitemExit = new JMenuItem("Exit");
        jmenuitemHelp = new JMenuItem("Help");
        jmenuitemImage = new JMenuItem("Image");
        jmenuitemBlue = new JMenuItem("Blue");
        jmenuitemNone = new JMenuItem("None");
        panelAqua = new AquaPanel();

        add(panelAqua);

        jmenuFile.add(jmenuitemExit);
        jmenuHelp.add(jmenuitemHelp);
        jMenuBackground.add(jmenuitemImage);
        jMenuBackground.add(jmenuitemBlue);
        jMenuBackground.add(jmenuitemNone);

        JMenuBar mb = new JMenuBar();
        mb.add(jmenuFile);
        mb.add(jMenuBackground);
        mb.add(jmenuHelp);
        setJMenuBar(mb);

        jmenuitemExit.addActionListener(this);
        jmenuitemHelp.addActionListener(this);
        jmenuitemImage.addActionListener(this);
        jmenuitemBlue.addActionListener(this);
        jmenuitemNone.addActionListener(this);

        addWindowListener(new WindowAdapter() {

                              public void windowClosed(WindowEvent e)
                              {
                                  System.exit(0);
                              }
                          }
        );
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /**
     * This function is active when someone pres on some button
     * so the event know which button is prest and do action accordingly.
     * @param //jmenuitemHelp , jmenuitemExit , jmenuitemImage, jmenuitemBlue, jmenuitemNone, panelAqua.exitButtons.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jmenuitemHelp) {
//            JDialog dlgAbout = new CustomHELPDialog(this, "Message", true);
//            dlgAbout.setVisible(true);
             new CustomHELPDialog();
        }
        else if (e.getSource() == jmenuitemExit) {
            System.exit(0);
        }
        else if (e.getSource() == jmenuitemImage) {
            try {
                backImage = ImageIO.read(new File("C:\\Users\\naves\\iCloudDrive\\תואר ראשון - הנדסת תוכנה\\שנה ב\\סמסטר ב\\תכנות מונחה עצמים מתקדם\\עבודות\\HomeWork2\\HomeWork_2\\הורדה.jpg"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            panelAqua.setImage(backImage);
            panelAqua.isSetImage = true;
        }
        else if (e.getSource() == jmenuitemBlue) {
            panelAqua.isSetImage = false;
            panelAqua.setBackground(Color.blue);
        }
        else if (e.getSource() == jmenuitemNone) {
            panelAqua.isSetImage = false;

            panelAqua.setBackground(Color.white);
        }
        else if (e.getSource() == panelAqua.exitButtons) {
            System.exit(0);
        }
    }
}
