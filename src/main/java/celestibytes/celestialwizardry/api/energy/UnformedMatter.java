package celestibytes.celestialwizardry.api.energy;


public class UnformedMatter extends EnergyType
{

    public UnformedMatter()
    {
        super(CWEnergyType.UNFORMED_MATTER, CWEnergyCategory.OTHER);
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
        return "Unformed Matter";
    }

    @Override
    public float crossEnergyTypeTransformLoss(EnergyType target)
    {
        return 0;
    }

}
