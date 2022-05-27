package plants;

import start.SeaCreature;

import java.awt.*;

public abstract class Immobile implements SeaCreature {
    protected int x;
    protected final int y = 600;
    protected int size;

    public Immobile() {
        x = 0;
        this.size = 10;
    }

    public Immobile(int x, int size) {
        this.x = x;
        this.size = size;
    }

    public abstract void drawCreature(Graphics g);

    public void setX(int x) {
        this.x = x;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
