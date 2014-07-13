package celestibytes.core.mod.version;

public class ModVersionCoFH implements Comparable<ModVersionCoFH>
{
    private final ReleaseVersionCoFH _mcVer;
    private final ReleaseVersionCoFH _modVer;
    private final String _desc;

    public ReleaseVersionCoFH minecraftVersion()
    {
        return _mcVer;
    }

    public ReleaseVersionCoFH modVersion()
    {
        return _modVer;
    }

    public String description()
    {
        return _desc;
    }

    public ModVersionCoFH(ReleaseVersionCoFH minecraftVersion, ReleaseVersionCoFH modVersion, String description)
    {
        _mcVer = minecraftVersion;
        _modVer = modVersion;
        _desc = description;
    }

    public static ModVersionCoFH parse(String s)
    {
        if (s == null)
        {
            return null;
        }

        String[] parts = s.split(" ", 2);
        String desc = "";

        if (parts.length > 1)
        {
            desc = parts[1].trim();
        }

        parts = parts[0].split("R", 2);

        return new ModVersionCoFH(ReleaseVersionCoFH.parse(parts[0]), ReleaseVersionCoFH.parse(parts[1]), desc);
    }

    @Override
    public String toString()
    {
        return _mcVer.toString() + "R" + _modVer.toString();
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
    public int compareTo(ModVersionCoFH o)
    {
        if (o == null)
        {
            return 0;
        }

        if (_mcVer.compareTo(o.minecraftVersion()) != 0)
        {
            return _mcVer.compareTo(o.minecraftVersion());
        }

        return _modVer.compareTo(o.modVersion());
    }
}
