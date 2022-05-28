package start;

import animals.*;
import plants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.CyclicBarrier;

/**
 * A AquaPanel Class that present the aquarioum panel which show diffrent assigment we can do.
 * we use different components of swing to the GUI to draw the animal.
 * all the component
 * @version 1.0 2 May 2022
 * @author  Tomer Damti, Nave Sfunim
 * @see AquaFrame
 */
public class AquaPanel extends JPanel implements ActionListener {
    public JPanel buttonPanel, drawingPanel;
    JTable infoTable;
    JScrollPane scrollPane;
    private final JButton addAnimalButtons, sleepButtons, wakeupButtons, resetButtons, foodButtons, infoButtons, AddPlantsDialog, duplicateButtons;
    public JButton exitButtons;
    HashSet<Swimmable> animals;
    int sumCountEat = 0;
    public boolean isSetImage = false;
    int animals_count = 0;
    String[] columnNames = {"Animal", "Color", "Size", "Hor. speed", "Ver. speed", "Eat counter"};
    Object[][] data = new Object[6][6];
    private Image image;
    HashSet<Immobile> plants;
    int plants_count = 0;

    /**
     * constructor
     * here we build the panel the present all the graphic and the main info of the program.
     */
    public AquaPanel(){
        addAnimalButtons = new JButton("Add Animal");
        sleepButtons = new JButton("Sleep");
        wakeupButtons = new JButton("Wake up");
        resetButtons = new JButton("Reset ");
        foodButtons = new JButton("Food");
        infoButtons = new JButton("Info");
        exitButtons = new JButton("Exit");
        AddPlantsDialog = new JButton("Add Plant");
        duplicateButtons = new JButton("Duplicate");
        buttonPanel = new JPanel();
        drawingPanel = new JPanel();

        BorderLayout myBorderLayout = new BorderLayout(0,0);
        setLayout(myBorderLayout);
        add(drawingPanel,BorderLayout.NORTH);
        add(buttonPanel,BorderLayout.SOUTH);

        buttonPanel.setLayout(new GridLayout(1,9));
        buttonPanel.add(addAnimalButtons);
        buttonPanel.add(sleepButtons);
        buttonPanel.add(wakeupButtons);
        buttonPanel.add(resetButtons);
        buttonPanel.add(foodButtons);
        buttonPanel.add(infoButtons);
        buttonPanel.add(AddPlantsDialog);
        buttonPanel.add(duplicateButtons);
        buttonPanel.add(exitButtons);

        addAnimalButtons.addActionListener(this);
        sleepButtons.addActionListener(this);
        wakeupButtons.addActionListener(this);
        resetButtons.addActionListener(this);
        foodButtons.addActionListener(this);
        infoButtons.addActionListener(this);
        exitButtons.addActionListener(this);
        AddPlantsDialog.addActionListener(this);
        duplicateButtons.addActionListener(this);

        animals = new HashSet<>();
        plants = new HashSet<>();

        infoTable = new JTable(data, columnNames);
        scrollPane = new JScrollPane(infoTable);
        data[5][0] = "Total";
        data[5][5] = sumCountEat;
        scrollPane.setVisible(false);
        add(scrollPane, BorderLayout.CENTER);

        Swimmable.setFoodFlag(false);
    }
    /**
     * This function is active when someone pres on some button
     * so the event know which button is prest and do action accordingly.
     * @param //addAnimalButtons , sleepButtons , wakeupButtons,sleepButtons, wakeupButtons, resetButtons, foodButtons, infoButtons, exitButtons
     */
    public void actionPerformed(ActionEvent e) {
        /**
         * This Button is add animal to the hashset and activate the thread.
         * it also insert it to the hashset as a Swimmable component.
         * @param  addAnimalButtons
         */
        if (e.getSource() == addAnimalButtons) {
            Window parentWindow = SwingUtilities.windowForComponent(this);
            JFrame parentFrame = null;
            if (parentWindow instanceof JFrame)
                parentFrame = (JFrame)parentWindow;

            AddAnimalDialog dialog = new AddAnimalDialog(parentFrame, "Add Animal", true, false, 0);
            dialog.setVisible(true);

            if (dialog.getAnimalSize() != 0) {
                Swimmable s;
                if (animals_count < 5) {
                    if (Objects.equals(dialog.getAnimal_Type(), "Fish"))
                        //s = new Fish(dialog.getAnimalSize(), getWidth() / 2, getHeight() / 2, dialog.gethSpeed(), dialog.getvSpeed(), dialog.getAnimalColor(), this); // Without factory
                        s = (Fish) AnimalFactory.produceSeaCreature("Fish");
                    else
                        //s = new Jellyfish(dialog.getAnimalSize(), getWidth() / 2, getHeight() / 2, dialog.gethSpeed(), dialog.getvSpeed(), dialog.getAnimalColor(), this); // Without factory
                        s = (Jellyfish) AnimalFactory.produceSeaCreature("Jellyfish");
                    s.setSize(dialog.getAnimalSize());
                    s.setX_front(getWidth() / 2);
                    s.setY_front(getHeight() / 2);
                    s.setHorSpeed(dialog.gethSpeed());
                    s.setVerSpeed(dialog.getvSpeed());
                    s.setCol(dialog.getAnimalColor());
                    s.setAp(this);
                    data[animals_count] = new Object[]{s.getAnimalName(), s.getColor(), s.getSize(), s.getHorSpeed(), s.getVerSpeed(), s.getEatCount()};
                    animals_count++;
                    s.setId(animals_count);
                    animals.add(s);
                    // start the object thread run.
                    s.start();
                }
            }
        }
        /**
         * This button is make all the thread to pause.
         * @param  sleepButtons.
         */
        else if (e.getSource() == sleepButtons) {
            for(Swimmable s:animals)
                s.setSuspend();
        }
        /**
         * This button is make all the thread to countinue.
         * @param  wakeupButtons.
         */
        else if (e.getSource() == wakeupButtons) {
            for(Swimmable s:animals){
                s.setResume();
            }
        }
        /**
         * This button is make all the thread to stop and then delet them or clear them from the Hashset.
         * @param  resetButtons.
        * @param  animals.
         */
        else if (e.getSource() == resetButtons) {
            for(Swimmable s:animals) {
                s.interrupt();
            }

            animals.clear();
            plants.clear();
            animals_count = 0;
            data[0] = new Object[]{"", "", "", "", "", ""};
            data[1] = new Object[]{"", "", "", "", "", ""};
            data[2] = new Object[]{"", "", "", "", "", ""};
            data[3] = new Object[]{"", "", "", "", "", ""};
            data[4] = new Object[]{"", "", "", "", "", ""};
            sumCountEat = 0;
            data[5][5] = sumCountEat;
        }
        /**
         * This button is feed the animal with worm that add to the center of the screen.
         * @param  foodButtons.
         */
        else if (e.getSource() == foodButtons) {
            if (animals.size()>0){
                CyclicBarrier barrier = new CyclicBarrier(animals.size());
                for(Swimmable s:animals) {
                    s.setBarrierSync(barrier);
                }
            }
            Worm.getInstance().setlocation(getWidth()/2,getHeight()/2);
            Swimmable.setFoodFlag(true);
            repaint();
        }
        /**
         * This button is open a scroll panle which present the animals inforamtion.
         * @param  infoButtons.
         */
        else if (e.getSource() == infoButtons) {
            if (scrollPane.isVisible()) {
                scrollPane.setVisible(false);
                drawingPanel.setVisible(true);
            }
            else {
                scrollPane.setVisible(true);
                drawingPanel.setVisible(false);
            }
        }
        /**
         * This button is finish the program.
         * @param  exitButtons.
         */
        else if (e.getSource() == exitButtons) {
            System.exit(0);
        }
        else if (e.getSource() == AddPlantsDialog) {
            Window parentWindow = SwingUtilities.windowForComponent(this);
            JFrame parentFrame = null;
            if (parentWindow instanceof JFrame)
                parentFrame = (JFrame)parentWindow;

            AddPlantsDialog dialog = new AddPlantsDialog(parentFrame, "Add Plant", true);
            dialog.setVisible(true);

            if (dialog.getPlantSize() != 0) {
                Immobile im;
                if (plants_count < 5) {
                    if (Objects.equals(dialog.getPlant_Type(), "Laminaria"))
                        //im = new Laminaria(dialog.getX_location(), dialog.getPlantSize()); // Without factory
                        im = (Laminaria) PlantFactory.produceSeaCreature("Laminaria");
                    else
                        //im = new Zostera(dialog.getX_location(), dialog.getPlantSize()); // Without factory
                        im = (Zostera) PlantFactory.produceSeaCreature("Zostera");
                    im.setX(dialog.getX_location());
                    im.setSize(dialog.getPlantSize());
                    plants.add(im);
                    plants_count++;
                }
            }
        }
        else if (e.getSource() == duplicateButtons) {
            if(animals_count > 0) {
                Window parentWindow = SwingUtilities.windowForComponent(this);
                JFrame parentFrame = null;
                if (parentWindow instanceof JFrame)
                    parentFrame = (JFrame) parentWindow;

                AddAnimalDialog dialog = new AddAnimalDialog(parentFrame, "Duplicate Animal", true, true, animals_count);
                dialog.setVisible(true);

                if (dialog.getAnimalSize() != 0) {
                    Swimmable s = null;
                    if (animals_count < 5) {
                        animals_count++;
                        if(dialog.getSame()) {
                            //Swimmable s_dup;
                            for(Swimmable sw:animals){
                                if (sw.getID() == dialog.getChoose()) {
                                    //s_dup = sw.clone();
                                    if (Objects.equals(sw.getAnimalName(), "Fish"))
                                        s = (Fish) sw.clone();
                                    else
                                        s = (Jellyfish) sw.clone();
                                    data[animals_count] = new Object[]{s.getAnimalName(), s.getColor(), s.getSize(), s.getHorSpeed(), s.getVerSpeed(), s.getEatCount()};
                                    s.setId(animals_count);
                                    animals.add(s);
                                    s.start();
                                    break;
                                }
                            }
                        }
                        else {
                            if (Objects.equals(dialog.getAnimal_Type(), "Fish"))
                                s = (Fish) AnimalFactory.produceSeaCreature("Fish");
                            else
                                s = (Jellyfish) AnimalFactory.produceSeaCreature("Jellyfish");
                            s.setSize(dialog.getAnimalSize());
                            s.setX_front(getWidth() / 2);
                            s.setY_front(getHeight() / 2);
                            s.setHorSpeed(dialog.gethSpeed());
                            s.setVerSpeed(dialog.getvSpeed());
                            s.setCol(dialog.getAnimalColor());
                            s.setAp(this);
                            data[animals_count] = new Object[]{s.getAnimalName(), s.getColor(), s.getSize(), s.getHorSpeed(), s.getVerSpeed(), s.getEatCount()};
                            s.setId(animals_count);
                            animals.add(s);
                            s.start();
                        }
                    }
                }
            }
        }
    }
    /**
     * This function is acount on any graphic interface on the program
     * which include drawing the animal, the background image and the Worm-Food.
     *
     * @param  g , //Graphics
     *
     * @see Fish, Jellfish // draw Fish //
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isSetImage) {
            Dimension d = this.getSize();
            g.drawImage(image, 0, 0, d.width, d.height, this);
        }

        if (animals.size() > 0) {
            for(Swimmable s:animals){
                s.drawCreature(g);
            }
        }

        if (Swimmable.foodFlag)
            Worm.getInstance().draw(g);

        if(plants.size() > 0)
            for(Immobile im:plants){
                im.drawCreature(g);
            }

        getParent().repaint();
    }
    /**
     * This function is draw the image int the background.
     * @param img - the image it get.
     */
    public void setImage(Image img) {
        image = img;
        repaint();
    }
    /**
     * This function is add the inforamtion about the animals to the animal dialog.
     */
    public void setInfo() {
        int i = 0;
        for(Swimmable s:animals) {
            if (s.getEatCount() != (int)data[i][5]) {
                data[i][5] = s.getEatCount();
                data[i][2] =  s.getSize();
                break;
            }
            i++;
        }
        sumCountEat++;
        data[5][5] = sumCountEat;
    }
}
