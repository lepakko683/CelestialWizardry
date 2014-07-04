package celestialwizardry.crystal.api.crystal;

import java.util.List;

public interface ICrystalBuffer extends INetworkCrystal
{
    /**
     * The maximum size of a {@link EnergyPacket} that can be stored in the {@link ICrystal}.
     *
     * @return The maximum size
     */
    public float getMaxPacketSize();

    /**
     * The maximum amount of {@link EnergyPacket}s that can be stored in the {@link ICrystal}.
     *
     * @return the maximum amount
     */
    public int getMaxPackets();

    /**
     * The current {@link EnergyPacket} buffer of this {@link ICrystal}
     *
     * @return list of the {@link EnergyPacket}s in the buffer
     */
    public List<EnergyPacket> getBuffer();

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
