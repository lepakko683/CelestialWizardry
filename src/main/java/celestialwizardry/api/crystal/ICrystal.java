package celestialwizardry.api.crystal;

import celestialwizardry.api.energy.EnergyType;
import celestialwizardry.api.energy.internal.IEnergyBurst;

import net.minecraft.block.Block;
import net.minecraft.world.World;

import java.util.List;

/**
 * An interface implemented by all crystals.
 * <p/>
 * The energy sending and receiving handling should always happen in the output (the {@link ICrystal} that SENDS energy)
 * side to avoid any conflicts.
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
     * The maximum amount of {@link EnergyType} that can be stored in the {@link ICrystal}.
     *
     * @return The maximum buffer size
     */
    public float getMaxBuffer();

    /**
     * The current amount of {@link EnergyType} that is stored in the {@link ICrystal}.
     *
     * @return The current buffer size
     */
    public float getCurrentBuffer();

    /**
     * TODO Docs and energy types
     * @return
     */
    public boolean setBuffer(float amount);

    /**
     * TODO Docs and energy types
     * @return
     */
    public boolean setFull();

    /**
     * Can this {@link ICrystal} receive energy
     *
     * @return is this {@link ICrystal} able to receive energy
     */
    public boolean canReceive();

    /**
     * Can this {@link ICrystal} send energy
     *
     * @return is this {@link ICrystal} able to send energy
     */
    public boolean canSend();

    /**
     * Receives given amount of energy
     *
     * @param amount the amount to receive
     */
    public void receive(float amount);

    /**
     * Sends (decreases) given amount of energy
     *
     * @param amount the amount to send (decrease)
     */
    public void send(float amount);

    /**
     * Get the multiplier of energy to input into the block, 1.0 is the original amount of energy
     * in the burst. 0.9, for example, is 90%, so 10% of the energy in the burst will get
     * dissipated.
     */
    public float getEnergyYieldMultiplier(IEnergyBurst burst);

    public void onBurstCollision(IEnergyBurst burst, World world, int x, int y, int z);

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

    /**
     * Bounds the blockCrystal {@link net.minecraft.tileentity.TileEntity} to other blockCrystal {@link
     * net.minecraft.tileentity.TileEntity} for input.
     *
     * @param x the x coordinate of the other blockCrystal {@link net.minecraft.tileentity.TileEntity}
     * @param y the y coordinate of the other blockCrystal {@link net.minecraft.tileentity.TileEntity}
     * @param z the z coordinate of the other blockCrystal {@link net.minecraft.tileentity.TileEntity}
     *
     * @return true if the bound was success
     */
    public boolean setInputBound(int x, int y, int z);

    /**
     * The bounded crystal for input.
     *
     * @return The bounded crystal for input.
     */
    public ICrystal getInputBound();

    /**
     * Bounds the blockCrystal {@link net.minecraft.tileentity.TileEntity} to other blockCrystal {@link
     * net.minecraft.tileentity.TileEntity} for output.
     *
     * @param x the x coordinate of the other blockCrystal {@link net.minecraft.tileentity.TileEntity}
     * @param y the y coordinate of the other blockCrystal {@link net.minecraft.tileentity.TileEntity}
     * @param z the z coordinate of the other blockCrystal {@link net.minecraft.tileentity.TileEntity}
     *
     * @return true if the bound was success
     */
    public boolean setOutputBound(int x, int y, int z);

    /**
     * The bounded crystal for output.
     *
     * @return The bounded crystal for output.
     */
    public ICrystal getOutputBound();

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
