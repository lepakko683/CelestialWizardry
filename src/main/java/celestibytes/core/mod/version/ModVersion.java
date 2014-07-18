package celestibytes.core.mod.version;

public final class ModVersion implements Comparable<ModVersion>
{
    private final int major;
    private final int minor;
    private final int patch;
    private final Channel channel;
    private final int number;
    private String description = "";

    public ModVersion(int major, int minor, int patch)
    {
        this(major, minor, patch, Channel.STABLE, 0);
    }

    public ModVersion(int major, int minor, int patch, Channel channel, int number)
    {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.channel = channel;
        this.number = number;
    }

    public boolean isStable()
    {
        return channel == Channel.STABLE;
    }

    public boolean isBeta()
    {
        return channel == Channel.BETA;
    }

    public boolean isAlpha()
    {
        return channel == Channel.ALPHA;
    }

    public Channel getChannel()
    {
        return channel;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public String toString()
    {
        return isStable() ? major + "." + minor + "." + patch
                : major + "." + minor + "." + patch + channel.getPostfix() + "." + number;
    }

    /**
     * Compares this object with the specified object for order.  Returns a negative integer, zero, or a positive
     * integer as this object is less than, equal to, or greater than the specified object.
     * <p/>
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) == -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and
     * <tt>y</tt>.  (This implies that <tt>x.compareTo(y)</tt> must throw an exception iff <tt>y.compareTo(x)</tt>
     * throws an exception.)
     * <p/>
     * <p>The implementor must also ensure that the relation is transitive: <tt>(x.compareTo(y)&gt;0 &amp;&amp;
     * y.compareTo(z)&gt;0)</tt> implies <tt>x.compareTo(z)&gt;0</tt>.
     * <p/>
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt> implies that <tt>sgn(x.compareTo(z)) ==
     * sgn(y.compareTo(z))</tt>, for all <tt>z</tt>.
     * <p/>
     * <p>It is strongly recommended, but <i>not</i> strictly required that <tt>(x.compareTo(y)==0) ==
     * (x.equals(y))</tt>.  Generally speaking, any class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended language is "Note: this class has a natural
     * ordering that is inconsistent with equals."
     * <p/>
     * <p>In the foregoing description, the notation <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the
     * mathematical <i>signum</i> function, which is defined to return one of <tt>-1</tt>, <tt>0</tt>, or <tt>1</tt>
     * according to whether the value of <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     *
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than
     * the specified object.
     *
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it from being compared to this object.
     */
    @Override
    public int compareTo(ModVersion o)
    {
        if (major != o.major)
        {
            return major < o.major ? -1 : 1;
        }

        if (minor != o.minor)
        {
            return minor < o.minor ? -1 : 1;
        }

        if (patch != o.patch)
        {
            return patch < o.patch ? -1 : 1;
        }

        if (isStable() && !o.isStable())
        {
            return 1;
        }

        if (isAlpha() && !o.isAlpha())
        {
            return -1;
        }

        if (isBeta() && o.isAlpha())
        {
            return 1;
        }

        if (isBeta() && !o.isBeta())
        {
            return -1;
        }

        if (!isStable() && o.isStable())
        {
            return -1;
        }

        if (channel == o.channel)
        {
            if (number != o.number)
            {
                return number < o.number ? -1 : 1;
            }
        }

        return 0;
    }
}
