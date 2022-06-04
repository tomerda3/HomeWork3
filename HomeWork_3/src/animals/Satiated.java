package animals;

public class Satiated implements HungerState{

    @Override
    public String toString(){
        return "Satiated";
    }

    @Override
    public void doAction(Swimmable objSwim) {
        objSwim.setHungeryState(this);
    }
}
