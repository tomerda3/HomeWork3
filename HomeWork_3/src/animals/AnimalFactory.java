package animals;

public class AnimalFactory {
    public static Object produceSeaCreature(String type) {
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
