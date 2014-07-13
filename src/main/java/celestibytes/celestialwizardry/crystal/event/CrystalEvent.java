package celestibytes.celestialwizardry.crystal.event;

import celestibytes.celestialwizardry.crystal.api.crystal.ICrystal;
import celestibytes.celestialwizardry.crystal.api.crystal.ICrystalNetwork;

import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.Event;

public class CrystalEvent extends Event
{
    public final ICrystal crystal;
    public final World world;

    public CrystalEvent(ICrystal crystal, World world)
    {
        this.crystal = crystal;
        this.world = world;
    }

    public static class CrystalNetworkEvent extends CrystalEvent
    {
        // public final BlockCrystal blockCrystal;

        public CrystalNetworkEvent(ICrystalNetwork crystal, World world)
        {
            super(crystal, world);
            // this.blockCrystal = blockCrystal;
        }
    }

    public static class CrystalBreakEvent extends CrystalNetworkEvent
    {
        public CrystalBreakEvent(ICrystalNetwork crystal, World world)
        {
            super(crystal, world);
        }
    }

    public static class CrystalPlacedEvent extends CrystalNetworkEvent
    {
        public CrystalPlacedEvent(ICrystalNetwork crystal, World world)
        {
            super(crystal, world);
        }
    }
}
