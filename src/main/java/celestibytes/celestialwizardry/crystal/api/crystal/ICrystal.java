package celestibytes.celestialwizardry.crystal.api.crystal;

import celestibytes.celestialwizardry.api.energy.EnergyType;

import net.minecraft.world.World;

import java.util.List;

/**
 * An interface implemented by all crystals.
 * <p/>
 * The energy sending and receiving handling should always happen in the outputting (the {@link ICrystal} that SENDS
 * energy) side to avoid any conflicts.
 */
public interface ICrystal
{
    /**
     * Get the {@link EnergyType}s this {@link ICrystal} can handle.
     *
     * @param world the {@link World} this {@link ICrystal} is
     *
     * @return A list of {@link EnergyType} this {@link ICrystal} can handle
     */
    public List<EnergyType> acceptableEnergies(World world);

    /**
     * Get the multiplier of energy to input into the block, 1.0 is the original amount of energy in the packet. 0.9,
     * for example, is 90%, so 10% of the energy in the packet will get dissipated.
     *
     * @return the multiplier of energy
     */
    public float getEnergyYieldMultiplier();

    /**
     * The x position of the crystal tile.
     *
     * @return x position
     */
    public int getXPos();

    /**
     * The y position of the crystal tile.
     *
     * @return y position
     */
    public int getYPos();

    /**
     * The z position of the crystal tile.
     *
     * @return z position
     */
    public int getZPos();
}
