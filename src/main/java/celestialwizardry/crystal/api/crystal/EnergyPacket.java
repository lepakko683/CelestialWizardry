package celestialwizardry.crystal.api.crystal;

import celestialwizardry.api.CWApi;
import celestialwizardry.api.energy.EnergyRegistry;
import celestialwizardry.api.energy.EnergyType;

public final class EnergyPacket
{
    private final float size;
    private final EnergyType energyType;

    public EnergyPacket(float size, EnergyType energyType)
    {
        this.size = size;
        this.energyType = energyType;
    }

    public EnergyPacket(EnergyType energyType)
    {
        this(0F, energyType);
    }

    public EnergyPacket(String s)
    {
        this(Float.valueOf(s.substring(0, s.indexOf("."))),
             EnergyRegistry.getEnergyType(s.substring(s.indexOf(".") + 1)));
        CWApi.apiLog.info("Constructing a EnergyPacket from String " + s);
        CWApi.apiLog.info("Energy amount " + s.substring(0, s.indexOf(".")));
        CWApi.apiLog.info("Energy type " + EnergyRegistry.getEnergyType(s.substring(s.indexOf(".") + 1)));
    }

    public float getSize()
    {
        return size;
    }

    public EnergyType getEnergyType()
    {
        return energyType;
    }

    @Override
    public String toString()
    {
        return String.valueOf(size) + "." + energyType.toString();
    }
}
