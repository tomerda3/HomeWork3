package plants;

import java.awt.*;

public class Zostera extends Immobile {
    public Zostera(){
        super(50, 30,0);
    }
    public Zostera(int x, int y,int size, int id){
        super(x, size,id);
    }

    public void drawCreature(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.green);

        g.drawLine(x,y,x,y-size);
        g.drawLine(x-2,y,x-10,y-size*9/10);
        g.drawLine(x+2,y,x+10,y-size*9/10);
        g.drawLine(x-4,y,x-20,y-size*4/5);
        g.drawLine(x+4,y,x+20,y-size*4/5);
        g.drawLine(x-6,y,x-30,y-size*7/10);
        g.drawLine(x+6,y,x+30,y-size*7/10);
        g.drawLine(x-8,y,x-40,y-size*4/7);
        g.drawLine(x+8,y,x+40,y-size*4/7);
        g2.setStroke(new BasicStroke(1));
    }
}
