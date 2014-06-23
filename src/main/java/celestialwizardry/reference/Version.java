package celestialwizardry.reference;

/**
 * TODO Version check in this class
 */
public class Version
{
    // TODO Remove System.getenv() (potential NPE)
    public static final String VERSION = "0.1-DEV.${System.getenv().BUILD_NUMBER}";
    public static final String FORGE = "10.12.2.1121";
    public static final String MINECRAFT = "1.7.2";
    public static final String BAUBLES = "1.0.0.16";
}
