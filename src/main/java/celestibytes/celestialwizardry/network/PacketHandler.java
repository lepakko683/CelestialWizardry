package celestibytes.celestialwizardry.network;

import celestibytes.celestialwizardry.network.message.MessageRuneConfig;
import celestibytes.celestialwizardry.network.message.MessageRuneConfigReply;
import celestibytes.celestialwizardry.network.message.MessageTileEntityCW;
import celestibytes.celestialwizardry.network.message.MessageTileEntityWritingTable;
import celestibytes.celestialwizardry.network.message.MessageUpdateTileEntityStack;
import celestibytes.celestialwizardry.reference.Reference;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE
            .newSimpleChannel(Reference.MOD_ID.toLowerCase());

    public static void init()
    {
        INSTANCE.registerMessage(MessageTileEntityCW.class, MessageTileEntityCW.class, 0, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileEntityWritingTable.class, MessageTileEntityWritingTable.class, 1,
                                 Side.CLIENT);
        INSTANCE.registerMessage(MessageRuneConfig.class, MessageRuneConfig.class, 2, Side.CLIENT);
        INSTANCE.registerMessage(MessageRuneConfigReply.class, MessageRuneConfigReply.class, 3, Side.SERVER);
        INSTANCE.registerMessage(MessageUpdateTileEntityStack.class, MessageUpdateTileEntityStack.class, 4,
                                 Side.CLIENT);
    }
}
