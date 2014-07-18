package celestibytes.core.thread;

import celestibytes.core.derp.DerpException;
import celestibytes.core.mod.IMod;
import celestibytes.core.mod.version.Channel;
import celestibytes.core.mod.version.ModVersion;

import cpw.mods.fml.common.FMLLog;

import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import org.apache.logging.log4j.Level;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class VersionCheckThread implements Runnable
{
    public static final String DEFAULT_URL
            = "https://raw.githubusercontent.com/Celestibytes/VersionCheck/master/data.json";

    private static int threadNumber = 0;

    private final Thread thread;
    private final IMod mod;
    private final String url;
    private boolean checkComplete = false;
    private boolean newVersionAvailable = false;
    private ModVersion newVersion;

    public VersionCheckThread(IMod mod)
    {
        this(mod, null);
    }

    public VersionCheckThread(IMod mod, String url)
    {
        thread = new Thread(this, "Cbs Version Check Thread - " + threadNumber());
        this.mod = mod;

        if (url == null)
        {
            this.url = DEFAULT_URL;
        }
        else
        {
            this.url = url;
        }
    }

    public static int threadNumber()
    {
        return threadNumber++;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used to create a thread, starting the thread
     * causes the object's <code>run</code> method to be called in that separately executing thread.
     * <p/>
     * The general contract of the method <code>run</code> is that it may take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run()
    {
        try
        {
            check();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (DerpException e)
        {
            e.printStackTrace();
        }
        finally
        {
            checkComplete = true;
        }
    }

    @SuppressWarnings("unchecked")
    public void check() throws IOException, DerpException
    {
        URLConnection urlConnection = new URL(url).openConnection();

        urlConnection.setRequestProperty("User-Agent", System.getProperty("java.version"));
        urlConnection.connect();

        InputStream inputStream = urlConnection.getInputStream();

        String json = new String(ByteStreams.toByteArray(inputStream));

        inputStream.close();

        Map<String, Object> entries = new Gson().fromJson(json, Map.class);

        for (Map.Entry<String, Object> entry : entries.entrySet())
        {
            if (entry.getKey().equals(mod.getId()) && entry.getValue() instanceof Map)
            {
                Map<String, Object> modNode = (Map<String, Object>) entry.getValue();

                if (modNode.containsKey(mod.getMinecraftVersion()) && modNode
                        .get(mod.getMinecraftVersion()) instanceof Map)
                {
                    ModVersion remote = readFromMinecraftMap(
                            (Map<String, Object>) modNode.get(mod.getMinecraftVersion()));
                    ModVersion local = mod.getModVersion();

                    if (local.compareTo(remote) < 0)
                    {
                        newVersionAvailable = true;
                        newVersion = remote;

                        FMLLog.log(mod.getTargetLog(), Level.INFO,
                                   "A new version (" + newVersion + ") of " + mod.getName() + " for Minecraft " + mod
                                           .getMinecraftVersion() + " is available.");
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public ModVersion readFromMinecraftMap(Map<String, Object> minecraftMap) throws DerpException
    {
        if (minecraftMap.containsKey("channels"))
        {
            if (minecraftMap.get("channels") instanceof Map)
            {
                return readFromChannelMap((Map<String, Object>) minecraftMap.get("channels"), mod.getUpdateChannel());
            }
            else
            {
                throw new DerpException("Minecraft version " + mod.getMinecraftVersion()
                                                + "'s channel map's value isn't an instance of Map");
            }
        }
        else
        {
            throw new DerpException("Minecraft version " + mod.getMinecraftVersion() + " doesn't have channel map");
        }
    }

    @SuppressWarnings("unchecked")
    public ModVersion readFromChannelMap(Map<String, Object> channelMap, Channel channel) throws DerpException
    {
        String targetChannel = channel.getKey();
        Object ret;

        if (channelMap.containsKey(targetChannel))
        {
            if (channelMap.get(targetChannel) instanceof Map)
            {
                ret = readFromChannel((Map<String, Object>) channelMap.get(targetChannel), channel);
            }
            else
            {
                throw new DerpException("Channel map's channel " + targetChannel + "'s value isn't an instance of Map");
            }
        }
        else
        {
            throw new DerpException("Channel map doesn't have channel " + targetChannel);
        }

        if (!(ret == null || ret.equals("next")))
        {
            if (ret instanceof ModVersion)
            {
                return (ModVersion) ret;
            }
            else
            {
                throw new DerpException();
            }
        }
        else
        {
            return readFromChannelMap(channelMap, channel.getNext());
        }
    }

    public Object readFromChannel(Map<String, Object> channelMap, Channel channel) throws DerpException
    {
        if (channelMap.containsKey("useNext"))
        {
            if (channelMap.get("useNext") instanceof Boolean)
            {
                if ((Boolean) channelMap.get("useNext"))
                {
                    return "next";
                }
            }
        }

        ModVersion modVersion;

        int major;
        int minor;
        int patch;
        int number;
        String description;

        if (channelMap.containsKey("major"))
        {
            if (channelMap.get("major") instanceof Number)
            {
                major = ((Number) channelMap.get("major")).intValue();
            }
            else
            {
                throw new DerpException("Channel " + channel.getKey() + "'s major value isn't an instance of Number");
            }
        }
        else
        {
            throw new DerpException("Channel " + channel.getKey() + " doesn't have value major");
        }

        if (channelMap.containsKey("minor"))
        {
            if (channelMap.get("minor") instanceof Number)
            {
                minor = ((Number) channelMap.get("minor")).intValue();
            }
            else
            {
                throw new DerpException("Channel " + channel.getKey() + "'s minor value isn't an instance of Number");
            }
        }
        else
        {
            throw new DerpException("Channel " + channel.getKey() + " doesn't have value minor");
        }

        if (channelMap.containsKey("patch"))
        {
            if (channelMap.get("patch") instanceof Number)
            {
                patch = ((Number) channelMap.get("patch")).intValue();
            }
            else
            {
                throw new DerpException("Channel " + channel.getKey() + "'s patch value isn't an instance of Number");
            }
        }
        else
        {
            throw new DerpException("Channel " + channel.getKey() + " doesn't have value patch");
        }

        if (channel == Channel.STABLE)
        {
            modVersion = new ModVersion(major, minor, patch);
        }
        else
        {
            if (channelMap.containsKey("number"))
            {
                if (channelMap.get("number") instanceof Number)
                {
                    number = ((Number) channelMap.get("number")).intValue();
                }
                else
                {
                    throw new DerpException(
                            "Channel " + channel.getKey() + "'s number value isn't an instance of Number");
                }
            }
            else
            {
                throw new DerpException("Channel " + channel.getKey() + " doesn't have value number");
            }

            modVersion = new ModVersion(major, minor, patch, channel, number);
        }

        if (channelMap.containsKey("description"))
        {
            if (channelMap.get("description") instanceof String)
            {
                description = (String) channelMap.get("description");
            }
            else
            {
                throw new DerpException(
                        "Channel " + channel.getKey() + "'s description value isn't an instance of String");
            }
        }
        else
        {
            throw new DerpException("Channel " + channel.getKey() + " doesn't have value description");
        }

        modVersion.setDescription(description);

        return modVersion;
    }

    public void start()
    {
        thread.start();
    }

    public Thread getThread()
    {
        return thread;
    }

    public boolean checkComplete()
    {

        return checkComplete;
    }

    public boolean newVersionAvailable()
    {

        return newVersionAvailable;
    }

    public ModVersion getNewVersion()
    {
        return newVersion;
    }
}
