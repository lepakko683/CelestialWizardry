package celestialwizardry.api.spell;

/**
 * Interface implemented in items that contain spells. Used to check if item is valid for various inventories
 */
public interface ISpellContainer
{
    public Spell getSpell();
}
