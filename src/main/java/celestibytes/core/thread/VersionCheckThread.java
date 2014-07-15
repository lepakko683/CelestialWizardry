package celestibytes.core.thread;

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
    public static final String DEFAULT_URL = "https://raw.githubusercontent.com/Celestibytes/VersionCheck/master/version.json";

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
        finally
        {
            checkComplete = true;
        }
    }

    // TODO Users will be able to configure which channel the mod looks for updates
    @SuppressWarnings("unchecked")
    public void check() throws IOException
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

                if (modNode.containsKey(mod.getMinecraftVersion()) && modNode.get(mod.getMinecraftVersion()) instanceof Map)
                {
                    Channel channel = mod.getUpdateChannel();
                    Map<String, Object> channels = (Map<String, Object>) ((Map<String, Object>) modNode.get(mod.getMinecraftVersion())).get("channels");
                    // Map<String, Object> map = (Map<String, Object>) channels.get(mod.getUpdateChannel().getKey());

                    String remoteS = null;
                    String description = "";

                    for (int i = 0; i < Channel.values().length; i++)
                    {
                        Map<String, Object> map = (Map<String, Object>) channels.get(channel.getKey());

                        if (channel.getNext() != null && map.get("version").equals(channel.getNext().getKey()))
                        {
                            channel = channel.getNext();
                        }
                        else
                        {
                            remoteS = (String) map.get("version");
                            description = (String) map.get("description");
                            break;
                        }
                    }

                    ModVersion local = ModVersion.parse(mod.getVersion(), mod.getChannel());
                    ModVersion remote = ModVersion.parse(remoteS, channel);

                    remote.setDescription(description);

                    if (local.compareTo(remote) < 0)
                    {
                        newVersionAvailable = true;
                        newVersion = remote;

                        FMLLog.log(mod.getTargetLog(), Level.INFO, "A new version (" + newVersion +  ") of " + mod.getName() + " for Minecraft " + mod.getMinecraftVersion() + " is available.");
                    }
                }
            }
        }
    }

    public void start()
    {
        thread.start();
    }

    public Thread getThread()
    {
        return thread;
    }

    public boolean checkComplete() {

        return checkComplete;
    }

    public boolean newVersionAvailable() {

        return newVersionAvailable;
    }

    public ModVersion getNewVersion()
    {
        return newVersion;
    }
}
