package celestibytes.core.mod.version;

public class ModVersion implements Comparable<ModVersion>
{
    private final int major;
    private final int minor;
    private final int patch;
    private final int rc;
    private final int beta;
    private String description;

    public ModVersion(int major, int minor, int patch)
    {
        this(major, minor, patch, 0, 0);
    }

    public ModVersion(int major, int minor, int patch, int rc, int beta)
    {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.rc = rc;
        this.beta = beta;
    }

    public static ModVersion parse(String s)
    {
        int major = 0;
        int minor = 0;
        int patch = 0;
        int rc = 0;
        int beta = 0;
        String main = s;
        String[] parts;

        parts = main.split("-rc.");

        if (parts.length > 1)
        {
            rc = Integer.parseInt(parts[1]);
            main = parts[0];
        }

        parts = main.split("-beta.");

        if (parts.length > 1)
        {
            beta = Integer.parseInt(parts[1]);
            main = parts[0];
        }

        parts = main.split("\\.");
        major = Integer.parseInt(parts[0]);
        minor = Integer.parseInt(parts[1]);
        patch = Integer.parseInt(parts[2]);

        return new ModVersion(major, minor, patch, rc, beta);
    }

    public int major()
    {
        return major;
    }

    public int minor()
    {
        return minor;
    }

    public int patch()
    {
        return patch;
    }

    public int rc()
    {
        return rc;
    }

    public int beta()
    {
        return beta;
    }

    public boolean isStable()
    {
        return rc == 0 & beta == 0;
    }

    public boolean isRC()
    {
        return rc > 0;
    }

    public boolean isBeta()
    {
        return beta > 0;
    }

    @Override
    public String toString()
    {
        String v = major + "." + minor + "." + patch;

        return v;
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
     * @param anotherModVersion the object to be compared.
     *
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than
     * the specified object.
     *
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it from being compared to this object.
     */
    @Override
    public int compareTo(ModVersion anotherModVersion)
    {
        if (this.major() != anotherModVersion.major())
        {
            return this.major() < anotherModVersion.major() ? -1 : 1;
        }

        if (this.minor() != anotherModVersion.minor())
        {
            return this.minor() < anotherModVersion.minor() ? -1 : 1;
        }

        if (this.patch() != anotherModVersion.patch())
        {
            return this.patch() < anotherModVersion.patch() ? -1 : 1;
        }

        if (this.isStable() && !anotherModVersion.isStable())
        {
            return 1;
        }

        if (this.isRC() && anotherModVersion.isBeta())
        {
            return 1;
        }

        if (!this.isStable() && anotherModVersion.isStable())
        {
            return -1;
        }

        if (this.isBeta() && anotherModVersion.isRC())
        {
            return -1;
        }

        if (this.rc() != anotherModVersion.rc())
        {
            return this.rc() < anotherModVersion.rc() ? -1 : 1;
        }

        if (this.beta() != anotherModVersion.beta())
        {
            return this.beta() < anotherModVersion.beta() ? -1 : 1;
        }

        return 0;
    }

    public String description()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
