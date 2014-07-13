package celestibytes.celestialwizardry.api.spellbook;

/**
 * Have a SpellBookEntry implement this to signify it's an "Addon entry", as in, one provided by an Addon. This allows
 * it to draw a subtitle of sorts, to prevent the [Mod tag here] nonsense that happened with thaumcraft addons. It can
 * also be used for other purposes, such as stating an entry is WIP.
 */
public interface IAddonEntry
{
    /**
     * Returns the <b>unlocalized</b> subtitle to show below the title. Here you'd return something like "(This Entry is
     * provided by the *insert name here* addon)".
     */
    public String getSubtitle();
}
