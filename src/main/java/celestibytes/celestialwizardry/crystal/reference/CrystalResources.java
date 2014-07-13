package celestibytes.celestialwizardry.crystal.reference;

import celestibytes.celestialwizardry.reference.Resources;
import celestibytes.celestialwizardry.util.ResourceLocationHelper;

import net.minecraft.util.ResourceLocation;

public class CrystalResources
{
    public static final String RESOURCE_PREFIX = Resources.RESOURCE_PREFIX;

    public static class Textures
    {
        // GUI textures
        public static final String GUI_SHEET_LOCATION = Resources.Textures.GUI_SHEET_LOCATION;
    }

    public static class Models
    {
        // Model textures
        public static final String MODEL_TEXTURE_LOCATION = Resources.Models.MODEL_TEXTURE_LOCATION;
        public static final String MODEL_LOCATION = Resources.Models.MODEL_LOCATION;

        public static final ResourceLocation TEXTURE_CRYSTAL_CONDUCTIVE_WEAK = new ResourceLocation(
                "textures/blocks/lapis_block.png");
        public static final ResourceLocation TEXTURE_CRYSTAL_SOLAR_WEAK = new ResourceLocation(
                "textures/blocks/glowstone.png");

        public static final ResourceLocation MODEL_CRYSTAL_SIMPLE = ResourceLocationHelper.getResourceLocation(
                MODEL_LOCATION + "crystalSimple.obj");
        public static final ResourceLocation MODEL_CRYSTAL_COMPLEX = ResourceLocationHelper.getResourceLocation(
                MODEL_LOCATION + "crystalComplex.obj");
    }

    public static class Particles
    {
        public static final String MISC_LOCATION = Resources.Particles.MISC_LOCATION;
    }

    public static class Misc
    {
        public static final String BLOCK_TEXTURE_LOCATION = Resources.Misc.BLOCK_TEXTURE_LOCATION;
    }
}
