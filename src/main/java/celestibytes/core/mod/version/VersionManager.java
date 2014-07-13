package celestibytes.core.mod.version;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

import celestibytes.core.mod.IMod;
import celestibytes.core.registry.VersionManagerRegistry;
import celestibytes.core.thread.VersionCheckThread;

public class VersionManager
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
            VersionManagerRegistry.unregisterVersionManager(this);

            if (thread.newVersionAvailable())
            {
                if (!mod.allowVersionNote())
                {
                    return;
                }
            }
        }
    }
}
