package start;

import java.awt.*;
/**
 * This Class is present the Worm and mainly help to drawing it.
 * @see AquaPanel
 */
public class Worm {
    private int x, y;
    private static Worm instance = null;
    private Worm(){ }
    public static Worm getInstance( ) {
        if (instance == null)
            instance = new Worm();
        return instance;
    }
    public void setlocation(int x,int y){
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        ((Graphics2D) g).setStroke(new BasicStroke(3));
        g.setColor(Color.RED);
        g.drawArc(x,y,10,10,30,210);
        g.drawArc(x,y+10,10,10,180,270);
        ((Graphics2D) g).setStroke(new BasicStroke(1));
    }
}