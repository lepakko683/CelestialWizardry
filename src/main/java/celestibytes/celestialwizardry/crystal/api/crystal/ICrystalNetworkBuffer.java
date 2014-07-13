package celestibytes.celestialwizardry.crystal.api.crystal;

import java.util.List;

public interface ICrystalNetworkBuffer extends ICrystalNetwork
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
}
