package celestibytes.celestialwizardry.crystal.tileentity;

import celestibytes.celestialwizardry.api.energy.EnergyElemental;
import celestibytes.celestialwizardry.api.energy.EnergyType;
import celestibytes.celestialwizardry.crystal.block.BlockCrystalSolarWeak;
import celestibytes.celestialwizardry.crystal.util.PacketBuilder;

import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class TileEntityCrystalNetworkSolarWeak extends TileEntityCrystalNetworkPool
{
    private static final float MAX_POOL_SIZE = 10000F;
    private static final float DEFAULT_PACKET_SIZE = 10F;
    private static final EnergyType FIRE = new EnergyElemental(EnergyType.CWEnergyType.ELEMENTAL_FIRE);

    /**
     * Get the {@link celestibytes.celestialwizardry.api.energy.EnergyType}s this {@link celestibytes.celestialwizardry.crystal.api.crystal
     * .ICrystal} can handle.
     *
     * @param world the {@link net.minecraft.world.World} this {@link celestibytes.celestialwizardry.crystal.api.crystal.ICrystal}
     *              is
     *
     * @return A list of {@link celestibytes.celestialwizardry.api.energy.EnergyType} this {@link celestibytes.celestialwizardry.crystal.api
     * .crystal.ICrystal} can handle
     */
    @Override
    public List<EnergyType> acceptableEnergies(World world)
    {
        List<EnergyType> list = new ArrayList<EnergyType>();
        list.add(FIRE);
        return list;
    }

    @Override
    public EnergyType getEnergyType()
    {
        currentEnergy = FIRE;
        return super.getEnergyType();
    }

    /**
     * Gives the maximum size of the pool
     *
     * @return the maximum size of the pool
     */
    @Override
    public float getMaxPoolSize()
    {
        return MAX_POOL_SIZE;
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        if (worldObj != null && !worldObj.isRemote && worldObj.getTotalWorldTime() % 20L == 0L)
        {
            blockType = getBlockType();

            if (blockType instanceof BlockCrystalSolarWeak)
            {
                if (!worldObj.provider.hasNoSky)
                {
                    int light = worldObj.getSavedLightValue(EnumSkyBlock.Sky, xCoord, yCoord, zCoord)
                            - worldObj.skylightSubtracted;
                    float celestialAngleRadians = worldObj.getCelestialAngleRadians(1.0F);

                    if (celestialAngleRadians < (float) Math.PI)
                    {
                        celestialAngleRadians = celestialAngleRadians + ((0.0F - celestialAngleRadians) * 0.2F);
                    }
                    else
                    {
                        celestialAngleRadians = celestialAngleRadians + (
                                (((float) Math.PI * 2F) - celestialAngleRadians) * 0.2F);
                    }

                    light = Math.round((float) light * MathHelper.cos(celestialAngleRadians));

                    if (light < 0)
                    {
                        light = 0;
                    }

                    if (light > 15)
                    {
                        light = 15;
                    }

                    addEnergy(1F * light, FIRE);
                }
            }
        }
    }

    @Override
    public PacketBuilder getBuilder()
    {
        return new PacketBuilder(100f, this);
    }

    @Override
    public float defaultPacketSize()
    {
        return DEFAULT_PACKET_SIZE;
    }
}
