package celestialwizardry.config;

import celestialwizardry.reference.Reference;
import cpw.mods.fml.common.FMLLog;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ClientConfig
{
    public static final String CATEGORY_KEYBIND = "keybindings";
    private static Configuration configuration;

    protected static void init(File configFile)
    {
        configuration = new Configuration(configFile);

        try
        {
            configuration.load();

            /* KeyBindings */
            configuration.addCustomCategoryComment(CATEGORY_KEYBIND,
                                                   "See http://www.minecraftwiki.net/wiki/Key_codes for mapping of " +
                                                           "key codes to keyboard keys");
        }
        catch (Exception e)
        {
            FMLLog.severe(Reference.MOD_NAME + " has had a problem loading its general configuration");
        }
        finally
        {
            configuration.save();
        }
    }
}
