package animals;

import start.AquaPanel;
import java.awt.*;

/**
 * A class showing Jellyfish with the option of feeding and changing color.
 * @see Swimmable
 */
public class Jellyfish extends Swimmable {
    /**
     * A constructor who gets the following data of the jellyfish (like his super class) and send them to his super class.
     */
    public Jellyfish(int size, int x_front, int y_front, int horSpeed, int verSpeed, int col, AquaPanel ap) {
        super(size, x_front, y_front, horSpeed, verSpeed, col, ap);
    }

    /**
     * A function that returns the class name "Jellyfish".
     * @return the name "Jellyfish" in String.
     */
    public String getAnimalName() {
        return "Jellyfish";
    }

    /**
     * A function that draws the jellyfish on the screen.
     */
    public void drawAnimal(Graphics g)
    {
        int numLegs;
        if(size<40)
            numLegs = 5;
        else if(size<80)
            numLegs = 9;
        else
            numLegs = 12;

        Color[] colors = {Color.black, Color.red, Color.blue, Color.green, Color.cyan, Color.orange, Color.yellow, Color.magenta, Color.pink};
        Color col = colors[this.col-1];

        g.setColor(col);
        g.fillArc(x_front - size/2, y_front - size/4, size, size/2, 0, 180);

        for(int i=0; i<numLegs; i++)
            g.drawLine(x_front - size/2 + size/numLegs + size*i/(numLegs+1), y_front, x_front - size/2 + size/numLegs + size*i/(numLegs+1), y_front+size/3);
    }
}
