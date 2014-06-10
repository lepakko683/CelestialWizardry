package celestialwizardry.util;

import celestialwizardry.reference.Resources;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.EnumRarity;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class StringHelper
{
    /**
     * When formatting a string, always apply color before font modification.
     */
    public static final String BLACK = (char) 167 + "0";
    public static final String BLUE = (char) 167 + "1";
    public static final String GREEN = (char) 167 + "2";
    public static final String TEAL = (char) 167 + "3";
    public static final String RED = (char) 167 + "4";
    public static final String PURPLE = (char) 167 + "5";
    public static final String ORANGE = (char) 167 + "6";
    public static final String LIGHT_GRAY = (char) 167 + "7";
    public static final String GRAY = (char) 167 + "8";
    public static final String LIGHT_BLUE = (char) 167 + "9";
    public static final String BRIGHT_GREEN = (char) 167 + "a";
    public static final String BRIGHT_BLUE = (char) 167 + "b";
    public static final String LIGHT_RED = (char) 167 + "c";
    public static final String PINK = (char) 167 + "d";
    public static final String YELLOW = (char) 167 + "e";
    public static final String WHITE = (char) 167 + "f";

    public static final String OBFUSCATED = (char) 167 + "k";
    public static final String BOLD = (char) 167 + "l";
    public static final String STRIKETHROUGH = (char) 167 + "m";
    public static final String UNDERLINE = (char) 167 + "n";
    public static final String ITALIC = (char) 167 + "o";
    public static final String END = (char) 167 + "r";

    private static String shiftForInfo = LIGHT_GRAY + getTooltip("holdShift1") + " " + YELLOW + ITALIC + getTooltip(
            "holdShift2") + " " + END + LIGHT_GRAY + getTooltip("holdShift3") + END;
    private static String controlForInfo = LIGHT_GRAY + getTooltip(
            "holdControl1") + " " + BRIGHT_GREEN + ITALIC + getTooltip(
            "holdControl2") + " " + END + LIGHT_GRAY + getTooltip("holdControl3") + END;

    private StringHelper()
    {

    }

    public static String getTooltip(String tooltip)
    {
        return localize("tooltip." + Resources.RESOURCE_PREFIX + tooltip);
    }

    public static String getMessage(String message)
    {
        return localize("message." + Resources.RESOURCE_PREFIX + message);
    }

    public static int getSplitStringHeight(FontRenderer fontRenderer, String input, int width)
    {
        List stringRows = fontRenderer.listFormattedStringToWidth(input, width);

        return stringRows.size() * fontRenderer.FONT_HEIGHT;
    }

    public static String camelCase(String input)
    {
        return input.substring(0, 1).toLowerCase() + input.substring(1);
    }

    public static String titleCase(String input)
    {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    public static String localize(String key)
    {
        return StatCollector.translateToLocal(key);
    }

    public static String getFluidName(FluidStack fluid)
    {
        return getFluidName(fluid.getFluid());
    }

    public static String getFluidName(Fluid fluid)
    {
        String fluidName = "";

        if (fluid.getRarity() == EnumRarity.uncommon)
        {
            fluidName += YELLOW;
        }
        else if (fluid.getRarity() == EnumRarity.rare)
        {
            fluidName += BRIGHT_BLUE;
        }
        else if (fluid.getRarity() == EnumRarity.epic)
        {
            fluidName += PINK;
        }

        fluidName += fluid.getLocalizedName() + END;

        return fluidName;
    }

    public static String getScaledNumber(int number)
    {
        return getScaledNumber(number, 2);
    }

    public static String getScaledNumber(int number, int minDigits)
    {
        String numString = "";

        int numMod = 10 * minDigits;

        if (number > 100000 * numMod)
        {
            numString += number / 1000000 + "M";
        }
        else if (number > 100 * numMod)
        {
            numString += number / 1000 + "k";
        }
        else
        {
            numString += number;
        }

        return numString;
    }

    public static String getShiftText()
    {
        return shiftForInfo;
    }

    public static String getControlText()
    {
        return controlForInfo;
    }

    public static String getActivationText(String key)
    {
        return BRIGHT_BLUE + ITALIC + localize(key) + END;
    }

    public static String getDeactivationText(String key)
    {
        return YELLOW + ITALIC + localize(key) + END;
    }

    public static String getInfoText(String key)
    {
        return BRIGHT_GREEN + localize(key) + END;
    }

    public static String getFlavorText(String key)
    {
        return WHITE + ITALIC + localize(key) + END;
    }
}
