package celestibytes.celestialwizardry.reference;

import celestibytes.core.thread.VersionCheckThread;

/**
 * This class contains common mod related constants that are used often.
 * <p/>
 * Basically, if you want to use mod id for example, you should always refer to constant in this class.
 */
public class Reference
{
    public static final String MOD_ID = "celestialwizardry";
    public static final String MOD_NAME = "Celestial Wizardry";
    public static final String MOD_VERSION = Versions.VERSION_NUMBER;

    public static final String SERVER_PROXY_CLASS = "celestibytes.celestialwizardry.proxy.ServerProxy";
    public static final String CLIENT_PROXY_CLASS = "celestibytes.celestialwizardry.proxy.ClientProxy";

    public static final String GUI_FACTORY_CLASS = "celestibytes.celestialwizardry.config.gui.CWGuiFactory";

    public static final String FINGERPRINT = "@FINGERPRINT@";

    public static final String DEPENDENCIES = "required-after:celesticore@[" + celestibytes.core.reference.Versions.VERSION_NUMBER
            + ",);after:Baubles";

    public static final String VERSION_URL = VersionCheckThread.DEFAULT_URL;

    public static final String BAUBLES_ROOT = "https://dl.dropboxusercontent.com/u/47135879/";
    public static final String BAUBLES_ID = "Baubles";
    public static final String BAUBLES_IFACE = "IBauble";
}
