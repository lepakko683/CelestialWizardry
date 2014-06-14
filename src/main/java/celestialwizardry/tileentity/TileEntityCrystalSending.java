package celestialwizardry.tileentity;

import celestialwizardry.block.BlockCrystal;

public abstract class TileEntityCrystalSending extends TileEntityCrystal
{
    public boolean canShootBurst;
    public int burstParticleTick;
    public int lastBurstDeathTick = -1;

    public TileEntityCrystalSending(BlockCrystal blockCrystal)
    {
        super(blockCrystal);
    }
}
