package celestialwizardry.crystal.api.crystal;

import celestialwizardry.api.energy.EnergyType;

public interface ICrystalNetworkPool extends ICrystalNetwork
{
    /**
     * The current {@link EnergyType} stored inside this pool
     *
     * @return the current {@link EnergyType} stored inside this pool
     */
    public EnergyType getEnergyType();

    /**
     * Gives the current size of the pool
     *
     * @return the current size of the pool
     */
    public float getPoolSize();

    /**
     * Gives the maximum size of the pool
     *
     * @return the maximum size of the pool
     */
    public float getMaxPoolSize();

    /**
     * Adds {@link EnergyType} to the pool
     *
     * @param amount the amount that will be added
     * @param type   the {@link EnergyType} to be added
     *
     * @return true if the operation was successful
     */
    public boolean addEnergy(float amount, EnergyType type);

    public void setDest(int x, int y, int z);
}
