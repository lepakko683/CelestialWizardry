package celestialwizardry.config;

import java.io.File;

public class ConfigHandler
{
    public static void init(String configPath)
    {
        ClientConfig.init(new File(configPath + "client.cfg"));
    }
}
