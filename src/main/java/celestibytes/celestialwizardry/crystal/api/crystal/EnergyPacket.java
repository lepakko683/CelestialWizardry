package celestibytes.celestialwizardry.crystal.api.crystal;

import celestibytes.celestialwizardry.api.energy.EnergyRegistry;
import celestibytes.celestialwizardry.api.energy.EnergyType;
import celestibytes.celestialwizardry.api.util.ApiLogHelper;

public final class EnergyPacket
{
    private final float size;
    private final EnergyType energyType;
    private final ICrystal compiler;

    public EnergyPacket(float size, EnergyType energyType, ICrystal crystal)
    {
        this.size = size;
        this.energyType = energyType;
        compiler = crystal;
    }

    public EnergyPacket(EnergyType energyType, ICrystal crystal)
    {
        this(0F, energyType, crystal);
    }

    public EnergyPacket(String s, ICrystal crystal)
    {
        this(Float.valueOf(s.substring(0, s.indexOf("."))), EnergyRegistry.getEnergyType(s.substring(s.indexOf(".") + 1)), crystal);
        ApiLogHelper.info("Constructing a EnergyPacket from String " + s);
        ApiLogHelper.info("Energy amount " + s.substring(0, s.indexOf(".")));
        ApiLogHelper.info("Energy type " + EnergyRegistry.getEnergyType(s.substring(s.indexOf(".") + 1)));
    }

    public float getSize()
    {
        return size;
    }

    public EnergyType getEnergyType()
    {
        return energyType;
    }

    public ICrystal getCompiler()
    {
        return compiler;
    }

    @Override
    public String toString()
    {
        return String.valueOf(size) + "." + energyType.toString();
    }
}
