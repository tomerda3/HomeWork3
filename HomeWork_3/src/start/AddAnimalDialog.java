package start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddAnimalDialog extends JDialog implements ActionListener {
    JButton jbnAdd;
    private final JRadioButton radioFish;
    private final JComboBox<String> comboColor;
    String[] colors = {"Black", "Red", "Blue", "Green", "Cyan", "Orange", "Yellow", "Magenta", "Pink"};
    private final JSlider sliderSize;
    private final JSlider sliderHSpeed;
    private final JSlider sliderVESpeed;
    ButtonGroup buttonGroup;
    public String animal_Type;
    private int animalSize, vSpeed, hSpeed, animalColor;

    /**
     * A function that returns the type of the animal.
     * @return the type in String.
     */
    public String getAnimal_Type() {
        return animal_Type;
    }

    /**
     * A function that returns the color of the animal according to an array of colors.
     * @return the color in string.
     */
    public int getAnimalColor() {
        return (animalColor+1);
    }

    /**
     * A function that returns the size of the animal.
     * @return the size in int.
     */
    public int getAnimalSize() {
        return animalSize;
    }

    /**
     * A function that returns the horizontal velocity of the animal.
     * @return the horSpeed in int.
     */
    public int gethSpeed() {
        return hSpeed;
    }

    /**
     * A function that returns the vertical velocity of the animal.
     * @return the verSpeed in int.
     */
    public int getvSpeed() {
        return vSpeed;
    }

    AddAnimalDialog(JFrame parent, String title, boolean modal){
        super(parent, title, modal);
        setLocation(450, 150);

        JPanel p1 = new JPanel();
        JLabel jlabeltext = new JLabel("Select Animal");
        BorderLayout myBorderLayout = new BorderLayout();
        myBorderLayout.setHgap(20);
        myBorderLayout.setVgap(20);
        p1.setLayout(myBorderLayout);
        p1.add(jlabeltext,BorderLayout.NORTH);

        radioFish = new JRadioButton("Fish");
        JRadioButton radioJellyfish = new JRadioButton("Jellyfish");
        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioFish);
        buttonGroup.add(radioJellyfish);
        radioJellyfish.setSelected(true);

        p1.add(radioFish,BorderLayout.EAST);
        p1.add(radioJellyfish,BorderLayout.WEST);

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(6, 1,20,20));
        JLabel jlabelSize = new JLabel("Size");
        JLabel jlabelHspeed = new JLabel("Horizontal Speed");
        JLabel jlabelVspeed = new JLabel("Vertical Speed");

        sliderSize = new JSlider(JSlider.HORIZONTAL,20, 320, 150);
        sliderSize.setPaintTicks(true);
        sliderSize.setMinorTickSpacing(10);
        sliderSize.setPaintTrack(true);
        sliderSize.setMajorTickSpacing(50);
        sliderSize.setPaintLabels(true);
        p2.add(jlabelSize);
        p2.add(sliderSize);

        sliderHSpeed = new JSlider(JSlider.HORIZONTAL,1, 10, 5);
        sliderHSpeed.setPaintTicks(true);
        sliderHSpeed.setMinorTickSpacing(1);
        sliderHSpeed.setPaintTrack(true);
        sliderHSpeed.setMajorTickSpacing(1);
        sliderHSpeed.setPaintLabels(true);
        p2.add(jlabelHspeed);
        p2.add(sliderHSpeed);

        sliderVESpeed = new JSlider(JSlider.HORIZONTAL,1, 10, 5);
        sliderVESpeed.setPaintTicks(true);
        sliderVESpeed.setMinorTickSpacing(1);
        sliderVESpeed.setPaintTrack(true);
        sliderVESpeed.setMajorTickSpacing(1);
        sliderVESpeed.setPaintLabels(true);
        p2.add(jlabelVspeed);
        p2.add(sliderVESpeed);

        comboColor = new JComboBox<>(colors);
        JPanel p3 = new JPanel();
        p3.add(comboColor);

        JPanel p4 = new JPanel();
        jbnAdd = new JButton("Add");
        jbnAdd.addActionListener(this);
        p4.add(jbnAdd);
        BorderLayout myBorderLayout1 = new BorderLayout();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(myBorderLayout1);
        mainPanel.add(p1,BorderLayout.NORTH);
        mainPanel.add(p2,BorderLayout.WEST);
        mainPanel.add(p3,BorderLayout.EAST);
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
            if (radioFish.isSelected())
                animal_Type = "Fish";
            else
                animal_Type = "JellyFish";
            animalSize = sliderSize.getValue();
            vSpeed = sliderVESpeed.getValue();
            hSpeed = sliderHSpeed.getValue();
            animalColor = comboColor.getSelectedIndex();
            this.dispose();
        }
    }
}
