package celestialwizardry.crystal;

public final class CrystalSession
{
    private final CrystalNetwork network;

    public CrystalSession(CrystalNetwork network)
    {
        this.network = network;
    }

    public CrystalNetwork getNetwork()
    {
        return network;
    }
}
