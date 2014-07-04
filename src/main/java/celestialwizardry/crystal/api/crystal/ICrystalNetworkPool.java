package celestialwizardry.crystal.api.crystal;

import celestialwizardry.api.energy.EnergyType;

public interface ICrystalNetworkPool extends ICrystalNetwork
{
    public EnergyType getEnergyType();

    public float getAmount();

    /**
     * Takes energy from the {@link ICrystalNetworkPool}'s {@link EnergyType} pool
     *
     * @param amount the requested amount of {@link EnergyType}
     *
     * @return returns the amount of {@link EnergyType} the {@link ICrystalNetworkPool} can give
     */
    public float takeEnergy(float amount);
}
