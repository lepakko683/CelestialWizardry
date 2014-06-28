package celestialwizardry.api.crystal;

import celestialwizardry.api.energy.EnergyType;

public interface ICrystalPool extends ICrystal
{
    public EnergyType getEnergyType();

    public float getAmount();

    /**
     * Takes energy from the {@link ICrystalPool}'s {@link EnergyType} pool
     *
     * @param amount the requested amount of {@link EnergyType}
     *
     * @return returns the amount of {@link EnergyType} the {@link ICrystalPool} can give
     */
    public float takeEnergy(float amount);
}
