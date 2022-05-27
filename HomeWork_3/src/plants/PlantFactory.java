package plants;

public class PlantFactory {

    public static int count =0;
    public static Object produceSeaCreature(String type)
    {
        if (count > 5)
            return null;
        count++;
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
