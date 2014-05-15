package celestialwizardry.api.spell;

import celestialwizardry.api.MagicType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

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
     * You should always call super method first to prevent bugs caused by API changes
     *
     * @param item   the ISpellContainer that holds the spell
     * @param player the player that is casting the spell
     */
    public void onPreCasting(ISpellContainer item, EntityPlayer player)
    {

    }

    /**
     * Called when the spell is casted
     * <p/>
     * You should always call super method first to prevent bugs caused by API changes
     *
     * @param item   the ISpellContainer that holds the spell
     * @param player the player that is casting the spell
     */
    public void onCasting(ISpellContainer item, EntityPlayer player)
    {

    }

    /**
     * Called after the spell is casted
     * <p/>
     * You should always call super method first to prevent bugs caused by API changes
     *
     * @param item   the ISpellContainer that holds the spell
     * @param player the player that is casting the spell
     */
    public void onPostCasting(ISpellContainer item, EntityPlayer player)
    {

    }

    /**
     * Called when spell is casted
     *
     * @param item   the ISpellContainer that holds the spell
     * @param player the player that is casting the spell
     */
    public void cast(ISpellContainer item, EntityPlayer player)
    {
        cast(item, player, player.worldObj);
    }

    /**
     * Called when spell is casted
     *
     * @param item   the ISpellContainer that holds the spell
     * @param player the player that is casting the spell
     * @param world  the world where the spell is casted
     */
    public void cast(ISpellContainer item, EntityPlayer player, World world)
    {
        onPreCasting(item, player);
        onCasting(item, player);
        onPostCasting(item, player);
    }
}
