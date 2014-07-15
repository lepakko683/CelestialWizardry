package celestibytes.core.thread;

import celestibytes.core.mod.IMod;
import celestibytes.core.mod.version.ModVersion;
import com.google.common.io.ByteStreams;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class VersionCheckThread implements Runnable
{
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
            this.url = "https://raw.githubusercontent.com/Celestibytes/" + mod.getId().toLowerCase() + "/develop/version.json";
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
    @SuppressWarnings("unchecked")
    public void run()
    {
        try
        {
            URLConnection connection = new URL(url).openConnection();
            connection.setRequestProperty("User-Agent", System.getProperty("java.version"));
            connection.connect();

            InputStream stream = connection.getInputStream();

            String data = new String(ByteStreams.toByteArray(stream));

            stream.close();

            Map<String, Object> groups = new Gson().fromJson(data, Map.class);

            for (Map.Entry<String, Object> entry : groups.entrySet())
            {
                String nodeName = entry.getKey();
                Object node = entry.getValue();

                if (node instanceof Map)
                {
                    if (nodeName.equals(mod.getMinecraftVersion()))
                    {
                        Map<String, Object> group = (Map<String, Object>) node;

                        ModVersion remote = ModVersion.parse((String) group.get("version"));
                        ModVersion local = ModVersion.parse(mod.getVersion());

                        String description = (String) group.get("description");
                        remote.setDescription(description);

                        if (local.compareTo(remote) < 0)
                        {
                            newVersionAvailable = true;
                            newVersion = remote;
                        }
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        checkComplete = true;
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
