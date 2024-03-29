package start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddAnimalDialog extends JDialog implements ActionListener {
    JButton jbnAdd, jbnDup;
    private final JRadioButton radioFish;
    private final JComboBox<String> comboColor;
    String[] colors = {"Black", "Red", "Blue", "Green", "Cyan", "Orange", "Yellow", "Magenta", "Pink"};
    private final JSlider sliderSize;
    private final JSlider sliderHSpeed;
    private final JSlider sliderVESpeed;
    private final JSlider sliderFreq;
    ButtonGroup buttonGroup;
    public String animal_Type;
    private int animalSize, vSpeed, hSpeed, animalColor;
    private boolean is_Duplicate;
    private int amount_Animal;
    private final JComboBox<String> comboAmount;
    private int choose;
    private boolean same = false;
    private int eatingFreq;

    AddAnimalDialog(JFrame parent, String title, boolean modal, boolean is_Duplicate, int amount_Animal) {
        super(parent, title, modal);
        setLocation(450, 150);

        JPanel p1 = new JPanel();
        JLabel jlabeltext = new JLabel("Select Animal:");
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
        p2.setLayout(new GridLayout(8, 1,20,20));
        JLabel jlabelSize = new JLabel("Size");
        JLabel jlabelHspeed = new JLabel("Horizontal Speed");
        JLabel jlabelVspeed = new JLabel("Vertical Speed");
        JLabel jlabelFreq = new JLabel("Eating Frequency");


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

        sliderFreq = new JSlider(JSlider.HORIZONTAL,5, 15, 10);
        sliderFreq.setPaintTicks(true);
        sliderFreq.setMinorTickSpacing(1);
        sliderFreq.setPaintTrack(true);
        sliderFreq.setMajorTickSpacing(5);
        sliderFreq.setPaintLabels(true);
        p2.add(jlabelFreq);
        p2.add(sliderFreq);

        JPanel p3 = new JPanel();
        comboColor = new JComboBox<>(colors);
        if(is_Duplicate && amount_Animal > 0) {
            p3.setLayout(new GridLayout(3, 1,20,20));
            p3.add(comboColor);
            String[] count = new String[amount_Animal];
            for (int i = 1; i <= amount_Animal; i++)
                count[i-1] = String.valueOf(i);
            comboAmount = new JComboBox<>(count);
            p3.add(comboAmount);
        }
        else {
            comboAmount = new JComboBox<>();
            p3.add(comboColor);
        }

        JPanel p4 = new JPanel();
        if(is_Duplicate && amount_Animal > 0) {
            p4.setLayout(new GridLayout(1, 2,20,20));
            jbnAdd = new JButton("Duplicate By vales");
            jbnDup = new JButton("Duplicate Same");
            p4.add(jbnAdd);
            p4.add(jbnDup);
            jbnDup.addActionListener(this);
        }
        else {
            jbnAdd = new JButton("Add");
            p4.add(jbnAdd);
        }
        jbnAdd.addActionListener(this);

        BorderLayout myBorderLayout1 = new BorderLayout();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(myBorderLayout1);
        mainPanel.add(p1,BorderLayout.NORTH);
        mainPanel.add(p2,BorderLayout.WEST);
        mainPanel.add(p3,BorderLayout.EAST);
        mainPanel.add(p4,BorderLayout.SOUTH);

        add(mainPanel);

        addWindowListener(new WindowAdapter() {
                              public void windowClosing(WindowEvent e) {
                                  Window aboutDialog = e.getWindow();
                                  aboutDialog.dispose();
                              }
                          }
        );
        pack();
    }

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

    public int getChoose() {
        return choose;
    }

    public boolean getSame() {
        return same;
    }

    public int getEatingFreq() {
        return eatingFreq;
    }

    /**
     * A function that fills in the animal's data following a click on the "Add" button.
     * @param e - Represents the push of a button.
     */
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == jbnAdd)	{
            same = false;
            if (radioFish.isSelected())
                animal_Type = "Fish";
            else
                animal_Type = "JellyFish";
            animalSize = sliderSize.getValue();
            vSpeed = sliderVESpeed.getValue();
            hSpeed = sliderHSpeed.getValue();
            eatingFreq = sliderFreq.getValue();
            animalColor = comboColor.getSelectedIndex();
            if(is_Duplicate && amount_Animal > 0)
                choose = comboAmount.getSelectedIndex();
            this.dispose();
        }

        if(e.getSource() == jbnDup) {
            choose = (comboAmount.getSelectedIndex()+1);
            animalSize = 1;
            same = true;
            this.dispose();
        }
    }
}
