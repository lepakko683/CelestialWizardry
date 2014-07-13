package celestibytes.celestialwizardry.api.energy;

public class EnergyHeat extends EnergyType
{

    public EnergyHeat()
    {
        super(CWEnergyType.MUNDANE_HEAT, CWEnergyCategory.MUNDANE);
    }

    @Override
    public boolean canBeTransformedInto(EnergyType energy)
    {
//		return energy.getEnergyName().equalsIgnoreCase("Elemental Fire");
        return energy.energyType != null && energy.energyType instanceof CWEnergyType
                && energy.energyType == CWEnergyType.ELEMENTAL_FIRE;
    }

    @Override
    public float getEnergyValue()
    {
        return 0;
    }

    @Override
    public String getEnergyName()
    {
        return "Heat";
    }

    @Override
    public float crossEnergyTypeTransformLoss(EnergyType target)
    {
        return 0f;
    }

}
