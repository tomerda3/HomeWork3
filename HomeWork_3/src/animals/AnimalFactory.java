package animals;

public class AnimalFactory {

    public Object produceSeaCreature(String type)
    {
        Swimmable animal = null;
        switch (type)
        {
            case "fish":
                animal = new Fish();
                break;

            case "jellyfish":
                animal = new Jellyfish();
                break;

            default:
                break;

        }
        return animal;
    }
}
