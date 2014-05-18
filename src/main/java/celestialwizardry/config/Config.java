package celestialwizardry.config;

public class Config extends ConfigBase
{
    public Config(String version)
    {
        super(version);
    }

    public double get(String category, String key, double defaultValue)
    {
        return modConfiguration.get(category, key, defaultValue, "Default: " + defaultValue).getDouble(0);
    }

    public double get(String category, String key, double defaultValue, String comment)
    {
        return modConfiguration.get(category, key, defaultValue, comment + " Default: " + defaultValue).getDouble(0);
    }

    public int get(String category, String key, int defaultValue)
    {
        return modConfiguration.get(category, key, defaultValue, "Default: " + defaultValue).getInt();
    }

    public int get(String category, String key, int defaultValue, String comment)
    {
        return modConfiguration.get(category, key, defaultValue, comment + " Default: " + defaultValue).getInt();
    }

    public boolean get(String category, String key, boolean defaultValue)
    {
        return modConfiguration.get(category, key, defaultValue, "Default: " + defaultValue).getBoolean(defaultValue);
    }

    public boolean get(String category, String key, boolean defaultValue, String comment)
    {
        return modConfiguration.get(category, key, defaultValue, comment + " Default: " + defaultValue).getBoolean(defaultValue);
    }

    public String get(String category, String key, String defaultValue)
    {
        return modConfiguration.get(category, key, defaultValue, "Default: " + defaultValue).getString();
    }

    public String get(String category, String key, String defaultValue, String comment)
    {
        return modConfiguration.get(category, key, defaultValue, comment + " Default: " + defaultValue).getString();
    }
}
