package animals;

public class Hungry implements HungerState{

    @Override
    public String toString(){
        return "Hungry";
    }

    @Override
    public void doAction(Swimmable objSwim) {
        objSwim.setHungeryState(this);
    }

}
