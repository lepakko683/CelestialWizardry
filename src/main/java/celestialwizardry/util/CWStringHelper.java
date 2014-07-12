package celestialwizardry.util;

import celestialwizardry.reference.Resources;

import celestibytes.core.util.StringHelper;

public final class CWStringHelper extends StringHelper
{
    public static String getTooltip(String tooltip)
    {
        return localize("tooltip." + Resources.RESOURCE_PREFIX + tooltip);
    }

    public static String getMessage(String message)
    {
        return localize("message." + Resources.RESOURCE_PREFIX + message);
    }

    public static String getConfig(String config)
    {
        return localize("configgui." + Resources.RESOURCE_PREFIX + config);
    }
}
