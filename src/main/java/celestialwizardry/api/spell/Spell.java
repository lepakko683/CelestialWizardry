package celestialwizardry.api.spell;

public class Spell
{
    protected final String name;
    protected final float cost;

    public Spell(String name, float cost)
    {
        this.name = name;
        this.cost = cost;
    }

    public String getName()
    {
        return name;
    }

    public float getCost()
    {
        return cost;
    }
}
