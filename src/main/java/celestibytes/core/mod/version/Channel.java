package celestibytes.core.mod.version;

public enum Channel
{
    STABLE("stable"),
    RC("rc", STABLE),
    BETA("beta", RC),
    ALPHA("alpha", BETA);

    public static final Channel DEFAULT = STABLE;

    private final String key;
    private final Channel next;
    private final String postfix;

    Channel(String key)
    {
        this(key, null);
    }

    Channel(String key, Channel next)
    {
        this.key = key;
        this.next = next;
        this.postfix = key.equals("stable") ? "" : "-" + key;
    }

    public static Channel getChannelFromString(String s)
    {
        Channel channel = DEFAULT;

        if (s.equals(ALPHA.getKey()))
        {
            channel = ALPHA;
        }

        if (s.equals(BETA.getKey()))
        {
            channel = BETA;
        }

        if (s.equals(RC.getKey()))
        {
            channel = RC;
        }

        if (s.equals(STABLE.getKey()))
        {
            channel = STABLE;
        }

        return channel;
    }

    public String getKey()
    {
        return key;
    }

    public Channel getNext()
    {
        return next;
    }

    public String getPostfix()
    {
        return postfix;
    }
}
