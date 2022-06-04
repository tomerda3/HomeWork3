package start;

import animals.Swimmable;
import plants.Immobile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;

public class MementoDialog extends JDialog implements ActionListener {

    private final JComboBox<String> comboAmount,comboPlanet;
    private JButton animalButtons,planetButtons;
    private AquaPanel aquaPanel;
    private static int amount_Animal=0, amount_Planet=0;
    private boolean saveMemento;
    public static HashSet<Swimmable> animalsMemento = new HashSet<>();;
    public static HashSet<Immobile> plantsMemento = new HashSet<>();;


    MementoDialog(JFrame parent, String title, boolean modal, AquaPanel a,boolean saveMemento) {
        super(parent, title, modal);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setBounds(dimension.width / 2 , dimension.height / 2 , 100, 100);

        aquaPanel = a;
        amount_Animal += a.getAnimals_count();
        amount_Planet += a.getPlants_count();
        this.saveMemento=saveMemento;

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(2, 2, 20, 20));
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(1, 2, 20, 20));

        JLabel jlabeltextAnimal;
        JLabel jlabeltextPlanet;

        if (saveMemento == true){
            jlabeltextAnimal = new JLabel("Select Animal to save:");
            jlabeltextPlanet = new JLabel("Select Planet to save:");

            String[] count = new String[amount_Animal];
            for (int i = 1; i <= amount_Animal; i++) {
                count[i - 1] = String.valueOf(i);
            }
            comboAmount = new JComboBox<>(count);

            String[] countPlan = new String[amount_Planet];
            for (int i = 1; i <= amount_Planet; i++) {
                countPlan[i - 1] = String.valueOf(i);
            }
            comboPlanet = new JComboBox<>(countPlan);
            animalButtons = new JButton("Save Animal");
            planetButtons = new JButton("Save Planet");
        }
        else{
            jlabeltextAnimal = new JLabel("Select Animal to restore:");
            jlabeltextPlanet = new JLabel("Select Planet to restore:");
            int i=0;
            String[] count;
            if (animalsMemento != null){
                System.out.println("-----here-----");
                count = new String[animalsMemento.size()];
                for (Swimmable s: animalsMemento) {
                    count[i++]= String.valueOf(s.getID());
                    System.out.println("-s.getId():"+s.getID());
                }
            }
            else {
                count = new String[1];
                count[0]= "There is no animals to restore";
            }
            comboAmount = new JComboBox<>(count);
            i=0;
            String[] countPlan;
            if (plantsMemento != null){
                countPlan = new String[plantsMemento.size()];
                for (Immobile s:  plantsMemento) {
                    countPlan[i++]= String.valueOf(s.getId());
                }
            }
            else {
                countPlan = new String[1];
                countPlan[0]= "There is no plants to restore";
            }
            comboPlanet = new JComboBox<>(countPlan);
            animalButtons = new JButton("Restore Animal");
            planetButtons = new JButton("Restore Planet");
        }

        p1.add(jlabeltextAnimal);
        p1.add(comboAmount);
        p1.add(jlabeltextPlanet);
        p1.add(comboPlanet);

        p2.add(animalButtons);
        p2.add(planetButtons);
        animalButtons.addActionListener(this);
        planetButtons.addActionListener(this);

        BorderLayout myBorderLayout1 = new BorderLayout();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(myBorderLayout1);
        mainPanel.add(p1, BorderLayout.NORTH);
        mainPanel.add(p2, BorderLayout.SOUTH);

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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == animalButtons) {
            if (saveMemento){
                if (aquaPanel.animals != null){
                    int id = comboAmount.getSelectedIndex()+1;
                    System.out.println("id"+comboAmount.getSelectedIndex()+1);
                    for (Swimmable s:  aquaPanel.animals) {
                        System.out.println("  id: "+id + "  s.getId(): "+s.getID());
                        if (id == s.getID())
                            animalsMemento.add(s);
                    }
                }
            }
            else{
                if (animalsMemento !=null){
                    int id = comboAmount.getSelectedIndex()+1;
                    for (Swimmable s:  animalsMemento) {
                        if (id == s.getID()){
                            aquaPanel.animals.add(s);
                        }
                    }
                }
            }
            this.dispose();
        }
        else if (e.getSource() == planetButtons) {
            if (saveMemento){
                if (aquaPanel.plants != null){
                    int id = comboPlanet.getSelectedIndex()+1;
                    for (Immobile s:  aquaPanel.plants) {
                        if (id == s.getId())
                            plantsMemento.add(s);
                    }
                }
            }
            else{
                if (plantsMemento != null){
                    int id = comboPlanet.getSelectedIndex()+1;
                    for (Immobile s:  plantsMemento) {
                        if (id == s.getId())
                            aquaPanel.plants.add(s);
                    }
                }
            }
            this.dispose();
        }
    }

}

