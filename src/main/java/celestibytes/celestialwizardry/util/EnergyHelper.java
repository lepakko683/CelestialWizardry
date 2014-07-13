package celestibytes.celestialwizardry.util;

import celestibytes.celestialwizardry.api.energy.EnergyType;
import celestibytes.celestialwizardry.api.energy.IEnergyContainer;

public class EnergyHelper
{
    /**
     * @return the amount of target energy gained from transforming source energy
     */
    public static float getTransformRatio(EnergyType source, EnergyType target)
    {
        if (source.getEnergyCategory() != null && target.getEnergyCategory() != null)
        {
            if (source.getEnergyCategory() instanceof EnergyType.CWEnergyCategory && source.getEnergyCategory()
                    .equals(EnergyType.CWEnergyCategory.MAGIC))
            {
                return (source.getEnergyValue() / target.getEnergyValue()) * 0.5f;
            }

            return (source.getEnergyValue() / target.getEnergyValue()) * (1 - (
                    (source.crossEnergyTypeTransformLoss(null) + target.crossEnergyTypeTransformLoss(null)) / 2));
        }
        return 0f;
    }

    public static boolean isEnergyOfType(EnergyType type, Object cwType)
    {
        return cwType != null && type.getEnergyType().equals(cwType);
    }

    public static boolean isEnergyOfCategory(EnergyType type, Object cwCategory)
    {
        return cwCategory != null && type.getEnergyType().equals(cwCategory);
    }

    public static boolean canTransformInto(EnergyType source, EnergyType target)
    {
        return (isEnergyOfCategory(source, EnergyType.CWEnergyCategory.MAGIC) && isEnergyOfCategory(target,
                                                                                                    EnergyType
                                                                                                            .CWEnergyCategory.MAGIC))
                ||
                (isEnergyOfCategory(source, EnergyType.CWEnergyCategory.MAGIC) && isEnergyOfCategory(target,
                                                                                                     EnergyType
                                                                                                             .CWEnergyCategory.ELEMENTAL))
                ||
                (isEnergyOfCategory(source, EnergyType.CWEnergyCategory.ELEMENTAL) && isEnergyOfCategory(target,
                                                                                                         EnergyType
                                                                                                                 .CWEnergyCategory.MAGIC))
                ||
                source.canBeTransformedInto(target);
//        return (source.isMagicalEnergy() && target.isMagicalEnergy()) || (source.isMagicalEnergy() && target
//                .isElementalEnergy()) || (source.isElementalEnergy() && target.isMagicalEnergy()) || source
// .canBeTransformedInto(target);
    }

    public static double spaceAvailableInContainer(IEnergyContainer container, EnergyType type)
    {
        return container.getMaxSpace(type) - container.getEnergyStored(type);
    }
}
