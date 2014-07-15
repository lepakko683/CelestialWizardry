package celestibytes.core.reference;

import celestibytes.core.thread.VersionCheckThread;

public class Reference
{
    public static final String MOD_ID = "celesticore";
    public static final String MOD_NAME = "CelestiCore";
    public static final String MOD_VERSION = Version.VERSION;

    public static final String SERVER_PROXY_CLASS = "celestibytes.core.proxy.ServerProxy";
    public static final String CLIENT_PROXY_CLASS = "celestibytes.core.proxy.ClientProxy";

    public static final String GUI_FACTORY_CLASS = "celestibytes.core.config.gui.GuiFactory";

    public static final String FINGERPRINT = "@FINGERPRINT@";

    public static final String VERSION_URL = VersionCheckThread.DEFAULT_URL;

    public static final String DEPENDENCIES = "required-after:Forge@[" + Version.FORGE + "," + Version.FORGE_MAX + ")";
}
