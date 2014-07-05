package celestialwizardry.crystal.util;

import celestialwizardry.api.energy.EnergyType;
import celestialwizardry.crystal.api.crystal.EnergyPacket;
import celestialwizardry.util.MathHelper;

public final class PacketBuilder
{
    private float energy = 0;
    private EnergyType type = null;
    public final float max;

    public PacketBuilder()
    {
        this(Float.MAX_VALUE);
    }

    public PacketBuilder(float max)
    {
        this.max = max;
    }

    public PacketBuilder append(float amount)
    {
        energy = MathHelper.clampZero_float(energy + amount, max);
        return this;
    }

    public PacketBuilder setEnergyType(EnergyType type)
    {
        this.type = type;
        return this;
    }

    public EnergyPacket toPacket()
    {
        return new EnergyPacket(energy, type == null ? EnergyType.DEFAULT_ENERGY : type);
    }

    @Override
    public String toString()
    {
        return toPacket().toString() + ":" + String.valueOf(max);
    }
}
