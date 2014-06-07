package celestialwizardry.util;

import celestialwizardry.api.energy.EnergyType;

public class EnergyHelper
{

    /**
     * @return the amount of target energy gained from transforming source energy
     */
    public static float getTransfromRatio(EnergyType source, EnergyType target)
    {
        if (source.isMagicalEnergy() && target.isMagicalEnergy())
        {
            return source.getEnergyValue() / target.getEnergyValue();
        }
        return (source.getEnergyValue() / target.getEnergyValue()) * (1 - (
                (source.crossEnergyTypeTransformLoss(null) + target.crossEnergyTypeTransformLoss(null)) / 2));
    }

    public static boolean canTransform(EnergyType source, EnergyType target)
    {
        return (source.isMagicalEnergy() && target.isMagicalEnergy()) || (source.isMagicalEnergy() && target
                .isElementalEnergy()) || (source.isElementalEnergy() && target.isMagicalEnergy());
    }
}
