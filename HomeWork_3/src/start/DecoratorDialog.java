package start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DecoratorDialog extends JDialog implements ActionListener{

    Object[][] data = new Object[6][6];
    JTable infoTable;
    JScrollPane scrollPane;
    String[] columnNames = {"Animal", "Color", "Size", "Hor. speed", "Ver. speed", "Eat counter"};
    private final JComboBox<String> comboAmount;
    private JColorChooser jColorChooser;
    private int animalChosen;
    private Color colorChosen;


    DecoratorDialog(JFrame parent, String title, boolean modal, int amount_Animal,Object[][] data) {
        super(parent, title, modal);
        setLocation(450, 150);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setBounds(dimension.width / 2 - 400, dimension.height / 2 - 400, 100, 100);

        this.data = data;
        infoTable = new JTable(data, columnNames);
        scrollPane = new JScrollPane(infoTable);
        scrollPane.setVisible(true);

        JPanel p1 = new JPanel();
        p1.add(scrollPane);

        JPanel p2 = new JPanel();
        JLabel jlabeltext = new JLabel("Select Animal to change color:");
        p2.setLayout(new GridLayout(1, 3,20,20));
        String[] count = new String[amount_Animal];
        for (int i = 1; i <= amount_Animal; i++)
            count[i-1] = String.valueOf(i);
        comboAmount = new JComboBox<>(count);
        comboAmount.setPreferredSize(new Dimension(10, 20));

        p2.add(jlabeltext);
        p2.add(comboAmount);

        JLabel banner = new JLabel("Welcome to the Tutorial Zone!",
                JLabel.CENTER);
        banner.setForeground(Color.yellow);
        jColorChooser = new JColorChooser(banner.getForeground());
        p2.add(jColorChooser, BorderLayout.PAGE_END);


        BorderLayout myBorderLayout1 = new BorderLayout();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(myBorderLayout1);
        mainPanel.add(p1,BorderLayout.NORTH);
        mainPanel.add(p2,BorderLayout.SOUTH);

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

    public void actionPerformed(ActionEvent e) {

    }

}
