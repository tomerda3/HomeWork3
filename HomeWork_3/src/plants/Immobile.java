package plants;

import start.SeaCreature;

import java.awt.*;

public abstract class Immobile implements SeaCreature {
    protected int x;
    protected int y;
    protected int size;

    public Immobile() {
        x = 0;
        y = 0;
        this.size = 10;
    }

    public Immobile(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public abstract void drawCreature(Graphics g);
}
