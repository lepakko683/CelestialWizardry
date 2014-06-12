package celestialwizardry.api.crystal;

import celestialwizardry.api.energy.EnergyType;

import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * An interface implemented by all crystals.
 */
public interface ICrystal
{
    /**
     * Get the {@link EnergyType}s this {@link ICrystal} can handle.
     *
     * @param world the {@link World} this {@link ICrystal} is
     *
     * @return An array of {@link EnergyType} this {@link ICrystal} can handle
     */
    public EnergyType[] acceptableEnergies(World world);

    /**
     * The {@link Block} instance that implements {@link ICrystal}.
     *
     * @return the {@link Block}
     */
    public Block getBlock();

    /**
     * Can this {@link ICrystal} be bounded to given {@link ICrystal}.
     *
     * @param world   the {@link World} this {@link ICrystal} is
     * @param crystal the {@link ICrystal} this {@link ICrystal} is going to be bounded with
     *
     * @return can this {@link ICrystal} to the {@link ICrystal}
     */
    public boolean canBoundTo(World world, ICrystal crystal);
}
