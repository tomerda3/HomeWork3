package start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddPlantsDialog extends JDialog implements ActionListener {
    JButton jbnAdd;
    private final JRadioButton radioLaminaria, radioZostera;
    private final JSlider sliderSize;
    private final JSlider sliderX_location;
    ButtonGroup buttonGroup;
    public String plant_Type;
    private int plantSize, x_location;

    /**
     * A function that returns the type of the animal.
     * @return the type in String.
     */
    public String getPlant_Type() {
        return plant_Type;
    }

    /**
     * A function that returns the size of the animal.
     * @return the size in int.
     */
    public int getPlantSize() {
        return plantSize;
    }

    /**
     * A function that returns the horizontal velocity of the animal.
     * @return the horSpeed in int.
     */
    public int getX_location() {
        return x_location;
    }

    AddPlantsDialog(JFrame parent, String title, boolean modal){
        super(parent, title, modal);
        setLocation(450, 150);

        JPanel p1 = new JPanel();
        JLabel jlabeltext = new JLabel("Select Plant:");
        BorderLayout myBorderLayout = new BorderLayout();
        myBorderLayout.setHgap(20);
        myBorderLayout.setVgap(20);
        p1.setLayout(myBorderLayout);
        p1.add(jlabeltext,BorderLayout.NORTH);

        radioLaminaria = new JRadioButton("Laminaria");
        radioZostera = new JRadioButton("Zostera");
        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioLaminaria);
        buttonGroup.add(radioZostera);
        radioZostera.setSelected(true);

        p1.add(radioLaminaria,BorderLayout.EAST);
        p1.add(radioZostera,BorderLayout.WEST);

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(4, 1,20,20));
        JLabel jlabelSize = new JLabel("Size");
        JLabel jlabelX_location = new JLabel("X location");

        sliderSize = new JSlider(JSlider.HORIZONTAL,100, 400, 250);
        sliderSize.setPaintTicks(true);
        sliderSize.setMinorTickSpacing(10);
        sliderSize.setPaintTrack(true);
        sliderSize.setMajorTickSpacing(50);
        sliderSize.setPaintLabels(true);
        p2.add(jlabelSize);
        p2.add(sliderSize);

        sliderX_location = new JSlider(JSlider.HORIZONTAL,50, 700, 375);
        sliderX_location.setPaintTicks(true);
        sliderX_location.setMinorTickSpacing(25);
        sliderX_location.setPaintTrack(true);
        sliderX_location.setMajorTickSpacing(100);
        sliderX_location.setPaintLabels(true);
        p2.add(jlabelX_location);
        p2.add(sliderX_location);

        JPanel p4 = new JPanel();
        jbnAdd = new JButton("Add");
        jbnAdd.addActionListener(this);
        p4.add(jbnAdd);
        BorderLayout myBorderLayout1 = new BorderLayout();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(myBorderLayout1);
        mainPanel.add(p1,BorderLayout.NORTH);
        mainPanel.add(p2,BorderLayout.CENTER);
        mainPanel.add(p4,BorderLayout.SOUTH);

        add(mainPanel);

        addWindowListener(new WindowAdapter() {
                              public void windowClosing(WindowEvent e)
                              {
                                  Window aboutDialog = e.getWindow();
                                  aboutDialog.dispose();
                              }
                          }
        );
        pack();
    }

    /**
     * A function that fills in the animal's data following a click on the "Add" button.
     * @param e - Represents the push of a button.
     */
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == jbnAdd)	{
            if (radioLaminaria.isSelected())
                plant_Type = "Laminaria";
            else
                plant_Type = "Zostera";
            plantSize = sliderSize.getValue();
            x_location = sliderX_location.getValue();
            this.dispose();
        }
    }
}
