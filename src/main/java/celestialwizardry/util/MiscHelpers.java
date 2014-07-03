package celestialwizardry.util;


public class MiscHelpers
{
    /**
     * Get the coordinates of a adjacent block based on side
     *
     * @return the same coordinates if side is invalid
     */
    public static Triple<Integer, Integer, Integer> getAdjacenantBlockCrdsFromSide(int x, int y, int z, int side)
    {
        switch (side)
        {
            case 0:
                return new Triple<Integer, Integer, Integer>(x, y - 1, z);
            case 1:
                return new Triple<Integer, Integer, Integer>(x, y + 1, z);
            case 2:
                return new Triple<Integer, Integer, Integer>(x, y, z - 1);
            case 3:
                return new Triple<Integer, Integer, Integer>(x, y, z + 1);
            case 4:
                return new Triple<Integer, Integer, Integer>(x - 1, y, z);
            case 5:
                return new Triple<Integer, Integer, Integer>(x + 1, y, z);
        }
        return new Triple<Integer, Integer, Integer>(x, y, z);
    }
}
