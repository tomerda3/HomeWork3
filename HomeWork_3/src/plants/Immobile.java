package plants;

import start.SeaCreature;

import java.awt.*;

public abstract class Immobile implements SeaCreature {
    protected int x;
    protected final int y = 600;
    protected int size;
    protected int id;

    public Immobile() {
        x = 0;
        this.size = 10;
        this.id =0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Immobile(int x, int size, int id) {
        this.x = x;
        this.size = size;
        this.id = id;
    }

    public abstract void drawCreature(Graphics g);

    public void setX(int x) {
        this.x = x;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
