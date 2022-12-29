package plants;

import start.AbstractSeaFactory;

public class PlantFactory implements AbstractSeaFactory {
    public Object produceSeaCreature(String type) {
        Immobile plant = null;
        switch (type)
        {
            case "Laminaria":
                plant = new Laminaria();
                break;

            case "Zostera":
                plant = new Zostera();
                break;

            default:
                break;

        }
        return plant;
    }
}
