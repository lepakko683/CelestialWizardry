package celestialwizardry.crystal.api.crystal;

/**
 * An interface implemented by all crystals that can be added to the crystal network.
 * <p/>
 * It is more efficient to use this instead of {@link ICrystal}
 */
public interface ICrystalNetwork extends ICrystal
{
    public void onAdded();

    public void onRemoved();
}
