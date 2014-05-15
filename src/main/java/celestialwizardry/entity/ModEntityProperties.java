package celestialwizardry.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import java.lang.ref.WeakReference;

public class ModEntityProperties implements IExtendedEntityProperties
{
    public static final String NAME = "CelestialWizardry";
    public static final String SPELL_BOOK = "spellBook";

    public WeakReference<EntityPlayer> playerWeakReference;
    public boolean spellBook;

    public ModEntityProperties()
    {

    }

    public ModEntityProperties(EntityPlayer player)
    {
        playerWeakReference = new WeakReference<EntityPlayer>(player);
    }

    /**
     * Called when the entity that this class is attached to is saved.
     * Any custom entity data that needs saving should be saved here.
     *
     * @param compound The compound to save to.
     */
    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setBoolean(SPELL_BOOK, spellBook);
        compound.setTag(NAME, nbtTagCompound);
    }

    /**
     * Called when the entity that this class is attached to is loaded.
     * In order to hook into this, you will need to subscribe to the EntityConstructing event.
     * Otherwise, you will need to initialize manually.
     *
     * @param compound The compound to load from.
     */
    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagCompound nbtTagCompound = (NBTTagCompound) compound.getTag(NAME);
        spellBook = nbtTagCompound.getBoolean(SPELL_BOOK);
    }

    /**
     * Used to initialize the extended properties with the entity that this is attached to, as well
     * as the world object.
     * Called automatically if you register with the EntityConstructing event.
     *
     * @param entity The entity that this extended properties is attached to
     * @param world  The world in which the entity exists
     */
    @Override
    public void init(Entity entity, World world)
    {

    }

    public void copyFrom(ModEntityProperties modEntityProperties)
    {
        spellBook = modEntityProperties.spellBook;
    }

    public static void register(EntityPlayer player)
    {
        player.registerExtendedProperties(NAME, new ModEntityProperties(player));
    }

    public static ModEntityProperties get(EntityPlayer player)
    {
        return (ModEntityProperties) player.getExtendedProperties(NAME);
    }
}
