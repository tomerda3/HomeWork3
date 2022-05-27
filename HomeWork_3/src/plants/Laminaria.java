package plants;

import java.awt.*;

public class Laminaria extends Immobile {
    public Laminaria() {
        super(50, 50,30);
    }

    public Laminaria(int x, int y, int size) {
        super(x, y,size);
    }

    public void drawCreature(Graphics g){
        g.setColor(Color.green);
        g.fillArc(super.x-size/20, y-size, size/10, size*4/5,0,360);
        g.fillArc(x-size*3/20, y-size*13/15, size/10, size*2/3,0,360);
        g.fillArc(x-size/20, y-size*13/15, size/10, size*2/3,0,360);
        g.drawLine(x,y,x,y-size/5);
        g.drawLine(x,y,x-size/10,y-size/5);
        g.drawLine(x,y,x+size/10,y-size/5);
    }
}
