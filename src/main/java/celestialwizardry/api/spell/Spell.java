package celestialwizardry.api.spell;

import celestialwizardry.api.MagicType;
import celestialwizardry.api.event.SpellEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;

public class Spell
{
    protected final String name;
    protected final float cost;
    protected final MagicType magicType;

    public Spell(String name, float cost, MagicType magicType)
    {
        this.name = name;
        this.cost = cost;
        this.magicType = magicType;
    }

    public String getName()
    {
        return name;
    }

    public float getCost()
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
}
