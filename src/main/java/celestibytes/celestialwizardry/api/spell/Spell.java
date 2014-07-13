package celestibytes.celestialwizardry.api.spell;

import celestibytes.celestialwizardry.api.CWApi;
import celestibytes.celestialwizardry.api.MagicType;

import net.minecraft.entity.player.EntityPlayer;

public class Spell
{
    private final String name;
    private final double cost;
    private final MagicType magicType;
    private final int[] runes;
    private final int id;

    public Spell(String name, double defaultCost, int[] runes)
    {
        this(name, defaultCost, runes, MagicType.DEFAULT);
    }

    public Spell(String name, double defaultCost, int[] runes, MagicType magicType)
    {
        this.name = name;
        this.cost = handleCost(defaultCost);
        this.runes = runes;
        this.magicType = magicType;
        this.id = this.hashCode(); // TODO
    }

    public String getName()
    {
        return name;
    }

    public int[] getRunes()
    {
        return runes;
    }

    public double getCost()
    {
        return cost;
    }

    public MagicType getMagicType()
    {
        return magicType;
    }

    /**
     * Called before the spell is casted
     * <p/>
     * You should always call super method first to prevent bugs
     *
     * @param item   the ISpellContainer that holds the spell
     * @param player the player that is casting the spell
     */
    public boolean onPreCasting(ISpellContainer item, EntityPlayer player)
    {
        return true;
    }

    /**
     * Called when the spell is casted
     * <p/>
     * You should always call super method first to prevent bugs
     *
     * @param item   the ISpellContainer that holds the spell
     * @param player the player that is casting the spell
     */
    public boolean onCasting(ISpellContainer item, EntityPlayer player)
    {
        return true;
    }

    /**
     * Called after the spell is casted
     * <p/>
     * You should always call super method first to prevent bugs
     *
     * @param item   the ISpellContainer that holds the spell
     * @param player the player that is casting the spell
     */
    public boolean onPostCasting(ISpellContainer item, EntityPlayer player)
    {
        return true;
    }

    /**
     * Called when spell is casted
     * <p/>
     * You should always call super method first to prevent bugs
     *
     * @param item   the ISpellContainer that holds the spell
     * @param player the player that is casting the spell
     */
    public void cast(ISpellContainer item, EntityPlayer player)
    {
        // TODO Move to next part only if last was success
        onPreCasting(item, player);
        onCasting(item, player);
        onPostCasting(item, player);
    }

    public double handleCost(double defaultCost)
    {
        return CWApi.handleSpellCost(this, defaultCost);
    }
}
