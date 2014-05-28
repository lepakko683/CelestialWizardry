package celestialwizardry.config;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class Config extends ConfigBase
{
    public static File configDir;
    public static String domain;

    public Config(String version)
    {
        super(version);
    }

    public void setConfigDir(FMLPreInitializationEvent event, String domain)
    {
        setConfigDir(event.getModConfigurationDirectory(), domain);
    }

    public void setConfigDir(File configDir, String domain)
    {
        Config.configDir = configDir;
        Config.domain = domain;
    }

    public void setConfiguration(String file)
    {
        super.setConfiguration(
                new Configuration(new File(configDir, File.separator + domain + File.separator + file + ".cfg")));
    }

    public double get(String category, String key, double defaultValue)
    {
        return super.get(category, key, defaultValue, "Default value: " + defaultValue);
    }

    public double get(String category, String key, double defaultValue, String comment)
    {
        return super.get(category, key, defaultValue, comment + " Default value: " + defaultValue);
    }

    public int get(String category, String key, int defaultValue)
    {
        return super.get(category, key, defaultValue, "Default value: " + defaultValue);
    }

    public int get(String category, String key, int defaultValue, String comment)
    {
        return super.get(category, key, defaultValue, comment + " Default value: " + defaultValue);
    }

    public boolean get(String category, String key, boolean defaultValue)
    {
        return super.get(category, key, defaultValue, "Default value: " + defaultValue);
    }

    public boolean get(String category, String key, boolean defaultValue, String comment)
    {
        return super.get(category, key, defaultValue, comment + " Default value: " + defaultValue);
    }

    public String get(String category, String key, String defaultValue)
    {
        return super.get(category, key, defaultValue, "Default value: " + defaultValue);
    }

    public String get(String category, String key, String defaultValue, String comment)
    {
        return super.get(category, key, defaultValue, comment + " Default value: " + defaultValue);
    }
}
