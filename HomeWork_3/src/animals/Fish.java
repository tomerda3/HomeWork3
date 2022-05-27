package animals;

import start.AquaPanel;
import java.awt.*;

/**
 * A class showing Fish with the option of feeding and changing color.
 * @see Swimmable
 */
public class Fish extends Swimmable {
    /**
     * A constructor who gets the following data of the fish (like his super class) and send them to his super class.
     */
    public Fish(int size, int x_front, int y_front, int horSpeed, int verSpeed, int col, AquaPanel ap) {
        super(size, x_front, y_front, horSpeed, verSpeed, col, ap);
    }
    public Fish() {
        super(30, 30, 30, 10, 10, 2, null);
    }

    /**
     * A function that returns the class name "Fish".
     * @return the name "Fish" in String.
     */
    public String getAnimalName() {
        return "Fish";
    }

    /**
     * A function that draws the fish on the screen.
     */
    public void drawCreature(Graphics g) {
        Color[] colors = {Color.black, Color.red, Color.blue, Color.green, Color.cyan, Color.orange, Color.yellow, Color.magenta, Color.pink};
        Color col = colors[this.col-1];

        g.setColor(col);
        if(x_dir==1) // fish swims to right side
        {
            // Body of fish
            g.fillOval(x_front - size, y_front - size/4, size, size/2);

            // Tail of fish
            int[] x_t={x_front-size-size/4,x_front-size-size/4,x_front-size};
            int [] y_t = {y_front - size/4, y_front + size/4, y_front};
            Polygon t = new Polygon(x_t,y_t,3);
            g.fillPolygon(t);

            // Eye of fish
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(255-col.getRed(),255-col.getGreen(),255- col.getBlue()));
            g2.fillOval(x_front-size/5, y_front-size/10, size/10, size/10);

            // Mouth of fish
            if(size>70)
                g2.setStroke(new BasicStroke(3));
            else if(size>30)
                g2.setStroke(new BasicStroke(2));
            else
                g2.setStroke(new BasicStroke(1));
            g2.drawLine(x_front, y_front, x_front-size/10, y_front+size/10);
            g2.setStroke(new BasicStroke(1));
        }
        else // fish swims to left side
        {
            // Body of fish
            g.fillOval(x_front, y_front - size/4, size, size/2);

            // Tail of fish
            int[] x_t={x_front+size+size/4,x_front+size+size/4,x_front+size};
            int [] y_t = {y_front - size/4, y_front + size/4, y_front};
            Polygon t = new Polygon(x_t,y_t,3);
            g.fillPolygon(t);

            // Eye of fish
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(255-col.getRed(),255-col.getGreen(),255-col.getBlue()));
            g2.fillOval(x_front+size/10, y_front-size/10, size/10, size/10);

            // Mouth of fish
            if(size>70)
                g2.setStroke(new BasicStroke(3));
            else if(size>30)
                g2.setStroke(new BasicStroke(2));
            else
                g2.setStroke(new BasicStroke(1));
            g2.drawLine(x_front, y_front, x_front+size/10, y_front+size/10);
            g2.setStroke(new BasicStroke(1));
        }
    }
}
