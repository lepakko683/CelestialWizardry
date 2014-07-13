package celestibytes.celestialwizardry.api.energy;

public class EnergyMagic extends EnergyType
{

    private String name = null;

    public EnergyMagic(Object energyType)
    {
        super(energyType, CWEnergyCategory.MAGIC);

        if (energyType == CWEnergyType.MAGIC_LUNAR)
        {
            name = "Magic Lunar";
        }

        if (energyType == CWEnergyType.MAGIC_SOLAR)
        {
            name = "Magic Solar";
        }
    }

    @Override
    public boolean canBeTransformedInto(EnergyType energy)
    {
        return false;
    }

    @Override
    public float getEnergyValue()
    {
        return 0;
    }

    @Override
    public String getEnergyName()
    {
        return name;
    }

    @Override
    public float crossEnergyTypeTransformLoss(EnergyType target)
    {
        return 0;
    }

}
