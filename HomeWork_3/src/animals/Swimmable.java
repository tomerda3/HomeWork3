package animals;

import start.AquaPanel;
import start.CustomHELPDialog;
import start.SeaCreature;

import java.awt.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * An abstract class (it has no instances) that represents objects that can swim.
 * @see Fish
 * @see Jellyfish
 */
public abstract class Swimmable extends Thread  implements SeaCreature, Cloneable, MarineAnimal{
    /**
     * DISTANCE_EAT - Represents how much animal can eat.
     * eatCount - Represents the amount of food the animal ate, start from 0.
     * x_dir - Represents the swimming direction of the animal, with 1 to the right, 0 to the left.
     * waitflag - Represents whether the animal is in pause mode or not.
     * foodFlag - Represents in general (regardless of the instance) whether there is a worm or not.
     */
    protected int horSpeed;
    protected int verSpeed;
    protected final int DISTANCE_EAT = 4;
    protected int size;
    protected int col;
    protected int eatCount;
    protected int x_front;
    protected int y_front;
    protected int x_dir = 1;
    protected boolean waitflag = false;
    public static boolean foodFlag = false;
    protected AquaPanel ap;
    protected CyclicBarrier barrierSync;
    protected int id;
    protected int eatingFreq;
    protected Color c = null;
    protected HungerState currentState;

    /**
     * A constructor who gets the following data of the animal:
     * @param horSpeed - Represents the horizontal speed of the animal.
     * @param verSpeed - Represents the vertical speed of the animal.
     * @param ap - Represents the panel to which the animal belongs.
     * @param size - Represents the size of the animal.
     * @param col - Represents the color of the animal.
     * @param x_front - Represents the position of the animal on the X-axis
     * @param y_front - Represents the position of the animal on the Y-axis
     */
    public Swimmable(int size, int x_front, int y_front, int horSpeed, int verSpeed, int col, AquaPanel ap, int eatingFreq) {
        this.horSpeed = horSpeed;
        this.verSpeed = verSpeed;
        this.ap  = ap;
        this.size = size;
        this.col = col;
        this.x_front = x_front;
        this.y_front = y_front;
        barrierSync = null;
        this.eatingFreq = eatingFreq;
        currentState = new Satiated();
    }
    public Swimmable(Swimmable copy){
        this.horSpeed = copy.horSpeed;
        this.verSpeed = copy.verSpeed;
        this.ap  = copy.ap;
        this.size = copy.size;
        this.col = copy.col;
        this.x_front = copy.x_front;
        this.y_front = copy.y_front;
        barrierSync = null;
        this.eatingFreq = copy.eatingFreq;
        currentState = new Satiated();
    }
    /**
     * Abstract functions that any object that can swim must realize.
     */
    public abstract String getAnimalName();
    public abstract void drawCreature(Graphics g);
    public abstract Swimmable clone();

    /**
     * A function that returns the horizontal velocity of the animal.
     * @return the horSpeed in int.
     */
    public int getHorSpeed() {
        return horSpeed;
    }

    /**
     * A function that returns the vertical velocity of the animal.
     * @return the verSpeed in int.
     */
    public int getVerSpeed() {
        return verSpeed;
    }

    /**
     * A function that returns the size of the animal.
     * @return the size in int.
     */
    public int getSize() {
        return size;
    }

    /**
     * A function that returns the color of the animal according to an array of colors.
     * @return the color in string.
     */
    public String getColor() {
        String[] colors = {"Black", "Red", "Blue", "Green", "Cyan", "Orange", "Yellow", "Magenta", "Pink"};
        return colors[col-1];
    }

    public int getCol() {
        return col;
    }

    /**
     * A function that returns the amount of food the animal ate.
     * @return the amount of food in int.
     */
    public int getEatCount() {
        return eatCount;
    }

    /**
     * A function that increases the amount of food the animal ate,
     * if the animal ate an amount equal to its capacity then it increased by one and the amount of food it ate is zero.
     */
    public void eatInc() {
        eatCount++;
        if (eatCount >= DISTANCE_EAT) {
            eatCount = 0;
            size++;
        }
    }

    /**
     * A function that changes the variable "waitflag" to true.
     */
    public void setSuspend() {
        waitflag = true;
    }

    /**
     * A function that causes the animal to end the pause.
     */
    public void setResume() {
        synchronized (this) {
            waitflag = false;
            notify();
        }
    }

    /**
     * A function that set the barrier.
     */
    public void setBarrierSync(CyclicBarrier toSet){
        barrierSync = toSet;
    }

    public HungerState getCurrentState() {
        return currentState;
    }

    /**
     * A function that causes the animal to start swimming in the aquarium.
     */
    @Override
    public void run() {
        boolean flag_x = true, flag_y = true, didnt_move_X = false;
        int width = 986, height = 640;
        int countfreq =0;
        while (true) {
            if (waitflag) {
                synchronized (this) {
                    try {
                        this.wait();
                    } catch (InterruptedException ex) {
                        return;
                    }
                }
            }

            if (currentState instanceof Hungry && foodFlag) {
                try {
                    if(barrierSync != null)
                        barrierSync.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                if (x_front > width / 2) {
                    if (flag_x) {
                        x_dir = 0;
                        flag_x = false;
                    }
                }
                else {
                    if (!flag_x) {
                        x_dir = 1;
                        flag_x = true;
                    }
                }
                while (foodFlag) {
                    if (waitflag)
                        break;
                    if (Math.pow(x_front - width / 2, 2) + Math.pow(y_front - height / 2, 2) > 25) {
                        if (x_front + 5 <= width / 2)
                            x_front += horSpeed;
                        else if (x_front - 5 >= width / 2)
                            x_front -= horSpeed;
                        else
                            didnt_move_X = true;
                        if (y_front + 5 <= height / 2)
                            y_front += verSpeed;
                        else if (y_front - 5 >= height / 2)
                            y_front -= verSpeed;
                        else
                        if (didnt_move_X) {
                            if (y_front <= height / 2)
                                y_front += verSpeed;
                            else
                                y_front -= verSpeed;
                        }
                        didnt_move_X = false;
                        try {
                            sleep(10);
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                    else {
                        countfreq = 0;
                        setFoodFlag(false);
                        eatInc();
                        setHungeryState(new Satiated());
                        ap.setInfo();
                    }
                }
            }

            if (countfreq == eatingFreq) {
                new CustomHELPDialog("I'm " + getID() + " and I'm hungry");
                countfreq = 0;
                setHungeryState(new Hungry());
            }

            while (flag_x && flag_y && !waitflag && (!foodFlag || currentState instanceof Satiated)) {
                x_dir = 1;
                x_front += horSpeed;
                y_front += verSpeed;
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    return;
                }
                if (x_front + size/10 >= width && y_front + size/10 >= height) {
                    countfreq++;
                    flag_x = false;
                    flag_y = false;
                }
                else if (x_front + size/10 >= width){
                    countfreq++;
                    flag_x = false;
                }
                else if (y_front + size/10 >= height)
                    flag_y = false;
            }

            if (countfreq == eatingFreq) {
                new CustomHELPDialog("I'm " + getID() + " and I'm hungry");
                countfreq = 0;
                setHungeryState(new Hungry());
            }

            while (flag_x && !flag_y && !waitflag && (!foodFlag || currentState instanceof Satiated)) {
                x_dir = 1;
                x_front += horSpeed;
                y_front -= verSpeed;
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    return;
                }
                if (x_front + size/10 >= width && y_front - size/10 <= 0) {
                    countfreq++;
                    flag_x = false;
                    flag_y = true;
                }
                else if (x_front + size/10 >= width){
                    countfreq++;
                    flag_x = false;
                }

                else if (y_front - size/10 <= 0)
                    flag_y = true;
            }

            if (countfreq == eatingFreq) {
                new CustomHELPDialog("I'm " + getID() + " and I'm hungry");
                countfreq = 0;
                setHungeryState(new Hungry());
            }

            while (!flag_x && flag_y && !waitflag && (!foodFlag || currentState instanceof Satiated)) {
                x_dir = 0;
                x_front -= horSpeed;
                y_front += verSpeed;
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    return;
                }
                if (x_front - size/10 <= 0 && y_front + size/10 >= height) {
                    countfreq++;
                    flag_x = true;
                    flag_y = false;
                }
                else if (x_front - size/10 <= 0){
                    countfreq++;
                    flag_x = true;
                }
                else if (y_front + size/4 >= height)
                    flag_y = false;
            }

            if (countfreq == eatingFreq) {
                new CustomHELPDialog("I'm " + getID() + " and I'm hungry");
                countfreq = 0;
                setHungeryState(new Hungry());
            }

            while (!flag_x && !flag_y && !waitflag && (!foodFlag || currentState instanceof Satiated)) {
                x_dir = 0;
                x_front -= horSpeed;
                y_front -= verSpeed;
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    return;
                }
                if (x_front - size/10 <= 0 && y_front - size/10 <= 0) {
                    countfreq++;
                    flag_x = true;
                    flag_y = true;
                }
                else if (x_front - size/10 <= 0){
                    countfreq++;
                    flag_x = true;
                }
                else if (y_front - size/10 <= 0)
                    flag_y = true;
            }
        }
    }

    /**
     * A general (regardless of the instance) function that changes the variable "foodFlag".
     * @param flag - Represents the new value of the variable "foodFlag".
     */
    public static void setFoodFlag(boolean flag) {
        foodFlag = flag;
    }

    public void setHorSpeed(int horSpeed) {
        this.horSpeed = horSpeed;
    }

    public void setVerSpeed(int verSpeed) {
        this.verSpeed = verSpeed;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setX_front(int x_front) {
        this.x_front = x_front;
    }

    public void setY_front(int y_front) {
        this.y_front = y_front;
    }

    public void setAp(AquaPanel ap) {
        this.ap = ap;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setFreq(int freq) {
        this.eatingFreq = freq;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public void PaintFish(Color c) {
        this.c = c;
    }

    public void setHungeryState(HungerState state) {
        currentState=state;
    }

    public int getEatingFreq() {
        return eatingFreq;
    }
}

