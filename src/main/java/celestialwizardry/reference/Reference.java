package celestialwizardry.reference;

/**
 * This class contains common mod related constants that are used often.
 * <p/>
 * Basically, if you want to use mod id for example, you should always refer to constant in this class.
 */
public class Reference
{
    public static final String MOD_ID = "celestialwizardry";
    public static final String MOD_NAME = "Celestial Wizardry";
    public static final String MOD_VERSION = Version.VERSION;

    public static final String SERVER_PROXY_CLASS = "celestialwizardry.proxy.ServerProxy";
    public static final String CLIENT_PROXY_CLASS = "celestialwizardry.proxy.ClientProxy";

    public static final String FINGERPRINT = "@FINGERPRINT@";

    public static final String DEPENDENCIES = "required-after:Forge@[10.12.1.1110,);required-after:Baubles";
}
