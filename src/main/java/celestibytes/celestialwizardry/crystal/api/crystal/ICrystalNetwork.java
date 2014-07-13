package celestibytes.celestialwizardry.crystal.api.crystal;

/**
 * An interface implemented by all crystals that can be added to the crystal network.
 * <p/>
 * It is probably more efficient to use this instead of {@link ICrystal}
 */
public interface ICrystalNetwork extends ICrystal
{
    /**
     * Called when {@link ICrystalNetwork} is added to the network
     */
    public void onAdded();

    /**
     * Called when {@link ICrystalNetwork} is removed from the network
     */
    public void onRemoved();

    /**
     * Sends a {@link EnergyPacket} to the target {@link ICrystal}
     */
    public void sendPacket();

    /**
     * Called when this {@link ICrystal} sends a {@link EnergyPacket}.
     *
     * @param packet the sent {@link EnergyPacket}
     */
    public void onPacketSent(EnergyPacket packet);

    /**
     * Called when this {@link ICrystal} receives a {@link EnergyPacket}.
     *
     * @param packet the received {@link EnergyPacket}
     */
    public void onPacketReceived(EnergyPacket packet);
}
