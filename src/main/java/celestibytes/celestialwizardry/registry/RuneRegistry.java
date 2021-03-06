package celestibytes.celestialwizardry.registry;

import celestibytes.celestialwizardry.api.spellgrammar.Rune;
import celestibytes.celestialwizardry.config.RuneConfig;
import celestibytes.celestialwizardry.util.LogHelper;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RuneRegistry
{
	// STATIC BEGIN
	
	public static RuneRegistry clientSide = null;
    public static RuneRegistry serverSide = null;
	
    // Rune id 0 is intentionally unused!!!
    public static Map<String, ResourceLocation[]> runeTexLocs = new HashMap<String, ResourceLocation[]>();
    public static Map<String, Rune> runeMap = new HashMap<String, Rune>();
    
    @SideOnly(Side.CLIENT)
    public static void initClientSide() {
    	clientSide = new RuneRegistry(Side.CLIENT);
    }
    
    public static void initServerSide() {
    	serverSide = new RuneRegistry(Side.SERVER);
    }
    
    // STATIC END
    
    private String[] runeIdsv = null;
    public final Side side;

    /**
     * Are the numIds set up
     */
    private boolean configLoaded = false;
    
    public RuneRegistry(Side side) {
    	this.side = side;
    }

    public static void registerRune(Rune rune)
    {
        String name = Rune.getFullIdOf(rune);

        if (!runeMap.containsKey(name))
        {
            LogHelper.info("Registering rune " + name);
            runeMap.put(name, rune);
            return;
        }

        LogHelper.warn("Trying to register duplicate rune, skipping!");
    }

    /**
     * Called from *RuneConfigurationHandler. Sets numeric ids for runes from config
     */
    public void setupNumIds(RuneConfig config)
    {
        if (config == null)
        {
            return;
        }
        int runeCount = config.getRuneCount();
        if (runeCount == 0)
        {
            LogHelper.error("Attempted to setup runeconfig but config's runecount is zero.");
            return; // TODO throw exception
        }
        if (runeIdsv != null)
        {
            LogHelper
                    .error("Attempted to setup runeconfig but runeIdsv array wasn't null (runeconfig might have " +
                                   "already been set up. If not you will most likely crash.).");
            return; // TODO throw exception
        }
        runeIdsv = new String[runeMap.size()];
        if (!configLoaded)
        {
            Iterator<String> rNames = runeMap.keySet().iterator();
            runeIdsv = new String[runeMap.size() + 1];
            String cName = null;
            int nid = -1;
            while (rNames.hasNext())
            {
                cName = rNames.next();
                nid = config.getNumId(cName);

                if (nid == 0)
                {
                    nid = config.setNumIdAutoFor(cName);
                    System.out.println("setNumIdAutoFor: " + cName);
                }
                if (nid == -1)
                {
                    nid = config.addEntryAuto(cName);
                    System.out.println("addEntryAuto: " + cName);
                }
                if (nid != -1 && nid != 0)
                {
                    if (nid < runeIdsv.length)
                    {
                        if (runeIdsv[nid] == null)
                        {
                            runeIdsv[nid] = cName;
                        }
                        else
                        {
                            LogHelper.error("Attempted to overwrite rune: " + cName);
                        }
                    }
                }
                if (nid == -1 || nid == 0)
                {
                    LogHelper.error("Unable to register rune: " + cName);
                }
            }
            configLoaded = true;
        }
        else
        {
            LogHelper.error("Trying to setup numberic ids and runeIds isn't empty!"); // TODO throw exception
        }
    }

    public void registerRuneTextureLocations(String modid, ResourceLocation[] locs)
    {
        if (!runeTexLocs.containsKey(modid))
        {
            runeTexLocs.put(modid, locs);
        }
    }

    /**
     * Called at world creation from *RuneConfigurationHandler to "set" the numeric ids without the config file.
     */
    public void onCreateConfig()
    {
        Set<String> runeNames = runeMap.keySet();
        if (!runeNames.isEmpty())
        {
            runeIdsv = new String[runeNames.size() + 1];
            Iterator<String> iter = runeNames.iterator();
            int i = 1;
            while (iter.hasNext())
            {
                if (i >= runeIdsv.length)
                {
                    LogHelper.error("Ran out of space in runeIdsv!");
                    break;
                }
                runeIdsv[i] = iter.next();
                i++;
            }
            configLoaded = true;
        }
        else
        {
            LogHelper.error("Can't set ids for no runes (no runes are registered)!"); // TODO throw exception
        }
    }

    /**
     * Are the numIds set up
     */
    public boolean isConfigLoaded()
    {
        return configLoaded;
    }

    public static Rune getRuneByName(String name)
    {
        if (runeMap.containsKey(name))
        {
            return runeMap.get(name);
        }

        LogHelper.warn("Trying to get null rune, skipping!");

        return null;
    }

    public Rune getRuneByNumId(int id)
    {

        if (!configLoaded)
        {
            LogHelper.warn("Trying to get rune by it's numberic id before config is loaded");
        }

        if (id < runeIdsv.length && id > 0 && runeMap.containsKey(runeIdsv[id]))
        {
            return runeMap.get(runeIdsv[id]);
        }

        LogHelper.warn("Trying to get null rune, skipping!");

        return null;
    }

    public void reset()
    {
        runeIdsv = null;
        configLoaded = false;
    }

    /**
     * Don't use this for iterating through the array! Use "getRuneIdsvLength()" instead. Only for visual purposes.
     */
    public int getRuneCount()
    {
        return runeIdsv == null ? -1 : runeIdsv.length - 1;
    }

    /**
     * This should be used when iterating through the registered runes instead of getRuneCount()
     */
    public int getRuneIdsvLength()
    {
        return runeIdsv == null ? -1 : runeIdsv.length;
    }

    public String getRuneNameForId(int id)
    {
        return runeIdsv == null ? null : id < runeIdsv.length ? runeIdsv[id] : null;
    }
}
