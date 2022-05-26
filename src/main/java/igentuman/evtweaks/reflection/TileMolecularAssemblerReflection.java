package igentuman.evtweaks.reflection;

import appeng.api.config.Actionable;
import appeng.api.config.PowerMultiplier;
import appeng.me.GridAccessException;
import appeng.tile.crafting.TileMolecularAssembler;

public class TileMolecularAssemblerReflection {

    public static int userPower(TileMolecularAssembler instance, int ticksPassed, int bonusValue, double acceleratorTax) {
        try {
            return (int)(instance.getProxy().getEnergy().extractAEPower((double)(ticksPassed * bonusValue/10) * acceleratorTax, Actionable.MODULATE, PowerMultiplier.CONFIG) / acceleratorTax);
        } catch (GridAccessException var6) {
            return 0;
        }
    }

}
