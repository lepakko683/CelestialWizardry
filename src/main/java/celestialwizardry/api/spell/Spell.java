package celestialwizardry.api.spell;

import celestialwizardry.api.CWApi;
import celestialwizardry.api.MagicType;
import celestialwizardry.api.event.SpellEvent;
import celestialwizardry.api.rune.Rune;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.FMLCommonHandler;

public class Spell
{
    private final String name;
    private final double defaultCost;
    private double cost;
    private final MagicType magicType;
    private final Rune[] runes;

    public Spell(String name, double defaultCost, Rune[] runes)
    {
        this(name, defaultCost, runes, MagicType.DEFAULT);
    }

    public Spell(String name, double defaultCost, Rune[] runes, MagicType magicType)
    {
        this.name = name;
        this.defaultCost = defaultCost;
        this.runes = runes;
        this.magicType = magicType;

        handleConfiguration();
    }

    public String getName()
    {
        return name;
    }

    public double getDefaultCost()
    {
        return defaultCost;
    }

    public Rune[] getRunes()
    {
        return runes;
    }

    public Spell setCost(double cost)
    {
        this.cost = cost;

        return this;
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
        FMLCommonHandler.instance().bus().post(new SpellEvent.PreCastEvent(this, player, item));
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
        FMLCommonHandler.instance().bus().post(new SpellEvent.CastEvent(this, player, item));
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
        FMLCommonHandler.instance().bus().post(new SpellEvent.PostCastEvent(this, player, item));
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

    public void handleConfiguration()
    {
        CWApi.handleSpellConfiguration(this);
    }
}
