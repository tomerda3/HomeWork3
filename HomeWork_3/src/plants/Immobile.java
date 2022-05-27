package plants;

import start.SeaCreature;

import java.awt.*;

public abstract class Immobile implements SeaCreature {
    protected int x;
    protected int y;
    protected int size;
    protected Color color;
    protected String name;
    public Immobile(){
        x =0;
        y =0;
        this.size=10;
        this.color = Color.green;
    }
    public Immobile(int x, int y, int size){
        this.x =x;
        this.y =y;
        this.size=size;
        this.color = Color.green;
    }
    public abstract void drawCreature(Graphics g);
}
