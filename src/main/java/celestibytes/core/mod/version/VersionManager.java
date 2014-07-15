package celestibytes.core.mod.version;

import celestibytes.core.mod.IMod;
import celestibytes.core.registry.VersionManagerRegistry;
import celestibytes.core.thread.VersionCheckThread;
import celestibytes.core.util.StringHelper;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public final class VersionManager
{
    private final IMod mod;
    private final VersionCheckThread thread;
    private int lastPoll = 400;
    private boolean notificationDisplayed;

    public VersionManager(IMod mod)
    {
        this(mod, null);
    }

    public VersionManager(IMod mod, String url)
    {
        this.mod = mod;
        thread = new VersionCheckThread(mod, url);
        thread.start();
    }

    @SubscribeEvent
    public void tickStart(TickEvent.PlayerTickEvent event)
    {
        if (event.phase != TickEvent.Phase.START)
        {
            return;
        }

        if (lastPoll > 0)
        {
            --lastPoll;
            return;
        }

        lastPoll = 400;

        if (!notificationDisplayed && thread.checkComplete())
        {
            notificationDisplayed = true;
            VersionManagerRegistry.unregisterVersionManager(this, mod);

            if (thread.newVersionAvailable())
            {
                if (mod.allowVersionNote())
                {
                    ModVersion version = thread.getNewVersion();

                    event.player.addChatMessage(
                            new ChatComponentText(EnumChatFormatting.GOLD + "[" + mod.getName() + "]").appendText(
                                    EnumChatFormatting.WHITE + " " + StringHelper.getMessage("newVersion") + " ")
                                    .appendText(EnumChatFormatting.AQUA + version.toString()));
                    event.player
                            .addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + version.getDescription()));
                }
            }
        }
    }
}
