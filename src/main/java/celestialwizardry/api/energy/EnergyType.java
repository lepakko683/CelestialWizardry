package celestialwizardry.api.energy;

public abstract class EnergyType
{
    //Solar energy, Lunar energy, earth energy, fire energy, air energy, water energy
    //Mundane energy types: heat, ...

    public static final EnergyType DEFAULT_ENERGY = new EnergyMagic(EnergyType.CWEnergyType.MAGIC_LUNAR);

    protected Object energyType;

    protected Object energyCategory;

    /**
     * Transportation cost over one block
     */
    private double transportationCost = 0;
    private boolean canTransport = false;

    public EnergyType(Object energyType, Object energyCategory)
    {
        this.energyType = energyType;
        this.energyCategory = energyCategory;
    }

    public EnergyType(Object energyType, Object energyCategory, double transportationCost)
    {
        this(energyType, energyCategory);
        this.canTransport = true;
        this.transportationCost = transportationCost;
    }

    public abstract boolean canBeTransformedInto(EnergyType energy);

    /**
     * Used for the transform ratio
     */
    public abstract float getEnergyValue();

    public abstract String getEnergyName();

    public Object getEnergyType()
    {
        return this.energyType;
    }

    public Object getEnergyCategory()
    {
        return this.energyCategory;
    }

    public boolean canTransport()
    {
        return canTransport;
    }

    /**
     * Not sure if this is needed
     */
    public EnergyType transformInto(EnergyType target)
    {
        return canBeTransformedInto(target) ? target : null;
    }

    public double calculateTransportationCost(double distance)
    {
        return (distance >= 0d && transportationCost >= 0d) ? distance * transportationCost : -1d;
    }

    public abstract float crossEnergyTypeTransformLoss(EnergyType target);

    @Override
    public String toString()
    {
        return getEnergyName();
    }

    public static enum CWEnergyType
    {
        MAGIC_LUNAR,
        MAGIC_SOLAR,
        ELEMENTAL_EARTH,
        ELEMENTAL_FIRE,
        ELEMENTAL_AIR,
        ELEMENTAL_WATER,
        MUNDANE_HEAT,
        UNFORMED_MATTER;
    }

    public static enum CWEnergyCategory
    {
        MAGIC,
        ELEMENTAL,
        MUNDANE,
        OTHER;
    }
}
