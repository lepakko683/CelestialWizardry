package celestialwizardry.api.energy;

/**
 * Any TileEntity that implements this can be counted as a "ghost" block of
 * sorts, that won't call the collision code for the energy bursts.
 */
public interface IBurstCollisionGhost
{
    public boolean isGhost();
}
