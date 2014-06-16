package celestialwizardry.tileentity;

import celestialwizardry.api.crystal.ICrystal;
import celestialwizardry.api.energy.BurstProperties;
import celestialwizardry.api.energy.EnergyType;
import celestialwizardry.api.energy.ILensEffect;
import celestialwizardry.block.BlockCrystal;
import celestialwizardry.entity.EntityEnergyBurst;
import celestialwizardry.registry.EnergyRegistry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class TileEntityCrystalConductive extends TileEntityCrystal
{
    private static final int MAX_DISTANCE = 5;
    private static final float MAX_BUFFER = 100f;

    protected boolean hasReceivedInitialPacket = false;
    boolean redstoneLastTick = true;

    public boolean canShootBurst;
    public int burstParticleTick;
    public int lastBurstDeathTick = -1;

    List<EntityEnergyBurst.PositionProperties> lastTentativeBurst;

    public TileEntityCrystalConductive(BlockCrystal crystal)
    {
        super(crystal);
    }

    /* ======================================== ICrystal START ===================================== */

    /**
     * Get the {@link celestialwizardry.api.energy.EnergyType}s this {@link ICrystal} can handle.
     *
     * @param world the {@link net.minecraft.world.World} this {@link ICrystal} is
     *
     * @return A list of {@link celestialwizardry.api.energy.EnergyType} this {@link ICrystal} can handle
     */
    @Override
    public List<EnergyType> acceptableEnergies(World world)
    {
        List<EnergyType> list = new ArrayList<EnergyType>();

        for (EnergyType energyType : EnergyRegistry.energyMap.values())
        {
            list.add(energyType); // aka every energy type
        }

        return list;
    }

    /**
     * The maximum amount of {@link celestialwizardry.api.energy.EnergyType} that can be stored in the {@link
     * celestialwizardry.api.crystal.ICrystal}.
     *
     * @return The maximum buffer size
     */
    @Override
    public float getMaxBuffer()
    {
        return MAX_BUFFER;
    }

    /**
     * Can this {@link celestialwizardry.api.crystal.ICrystal} be bounded to given {@link
     * celestialwizardry.api.crystal.ICrystal}.
     *
     * @param world   the {@link net.minecraft.world.World} this {@link celestialwizardry.api.crystal.ICrystal} is
     * @param crystal the {@link celestialwizardry.api.crystal.ICrystal} this {@link celestialwizardry.api.crystal
     *                .ICrystal} is going to be bounded with
     *
     * @return can this {@link celestialwizardry.api.crystal.ICrystal} to the {@link celestialwizardry.api.crystal
     * .ICrystal}
     */
    @Override
    public boolean canBoundTo(World world, ICrystal crystal)
    {
        if (crystal.getXPos() == xCoord)
        {
            if (crystal.getZPos() == zCoord)
            {
                if (crystal.getYPos() - yCoord >= -MAX_DISTANCE && crystal.getYPos() - yCoord <= MAX_DISTANCE)
                {
                    return true;
                }
            }
            else
            {
                if (crystal.getZPos() - zCoord >= -MAX_DISTANCE && crystal.getZPos() - zCoord <= MAX_DISTANCE)
                {
                    if (crystal.getYPos() == yCoord)
                    {
                        return true;
                    }
                }
            }
        }
        else
        {
            if (crystal.getZPos() == zCoord)
            {
                if (crystal.getXPos() - xCoord >= -MAX_DISTANCE && crystal.getXPos() - xCoord <= MAX_DISTANCE)
                {
                    if (crystal.getYPos() == yCoord)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * The {@link net.minecraft.block.Block} instance that implements {@link ICrystal}.
     *
     * @return the {@link net.minecraft.block.Block}
     */
    @Override
    public Block getBlock()
    {
        return blockCrystal;
    }

    /* ======================================== ICrystal END ===================================== */

    /* ======================================== TileEntity START ===================================== */

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        if (worldObj != null && worldObj.isRemote)
        {
            hasReceivedInitialPacket = true;
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        tryShootBurst();
    }

    /* ======================================== TileEntity END ===================================== */

    public void tryShootBurst()
    {
        if (getOutputBound() != null)
        {
            if (canSend() && getOutputBound().canReceive())
            {
                EntityEnergyBurst burst = getBurst(false);

                if (burst != null)
                {
                    if (!worldObj.isRemote)
                    {
                        send(burst.getStartingEnergy());
                        worldObj.spawnEntityInWorld(burst);
                    }

                    worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                }
            }
        }
    }

    public EntityEnergyBurst getBurst(boolean fake)
    {
        EntityEnergyBurst burst = new EntityEnergyBurst(this, fake);

        float maxEnergy = 160;
        int color = 0x20FF20; // TODO
        int ticksBeforeEnergyLoss = 60;
        float energyLossPerTick = 4F;
        float motionModifier = 1F;
        float gravity = 0F;
        BurstProperties props = new BurstProperties(maxEnergy, ticksBeforeEnergyLoss, energyLossPerTick, gravity,
                                                    motionModifier, color);

        ItemStack lens = new ItemStack(Blocks.stone); // getStackInSlot(0);
        if (lens != null && lens.getItem() instanceof ILensEffect)
        {
            ((ILensEffect) lens.getItem()).apply(lens, props);
        }

        burst.setSourceLens(lens);

        if (getCurrentBuffer() >= props.maxEnergy || fake)
        {
            burst.setColor(props.color);
            burst.setEnergy(props.maxEnergy);
            burst.setStartingEnergy(props.maxEnergy);
            burst.setMinEnergyLoss(props.ticksBeforeEnergyLoss);
            burst.setEnergyLossPerTick(props.energyLossPerTick);
            burst.setGravity(props.gravity);
            burst.setMotion(burst.motionX * props.motionModifier, burst.motionY * props.motionModifier, burst.motionZ * props.motionModifier);

            return burst;
        }

        return null;
    }
}
