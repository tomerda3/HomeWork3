package animals;

import start.AbstractSeaFactory;

public class AnimalFactory implements AbstractSeaFactory {
    public Object produceSeaCreature(String type) {
        Swimmable animal = null;
        switch (type)
        {
            case "Fish":
                animal = new Fish();
                break;

            case "Jellyfish":
                animal = new Jellyfish();
                break;

            default:
                break;

        }
        return animal;
    }
}
