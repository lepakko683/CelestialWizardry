package celestialwizardry.api.energy;

public interface IEnergyContainer
{
    public boolean acceptsEnergyType(EnergyType type);

    public double getEnergyStored(EnergyType type);

    public double getMaxSpace(EnergyType type);
}
