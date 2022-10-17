package igentuman.evtweaks.reflection;

import appeng.api.config.Actionable;
import appeng.api.config.PowerMultiplier;
import appeng.me.GridAccessException;
import appeng.tile.crafting.TileMolecularAssembler;
import igentuman.evtweaks.ModConfig;

public class TileMolecularAssemblerReflection {

    public static int userPower(TileMolecularAssembler instance, int ticksPassed, int bonusValue, double acceleratorTax) {
        try {
            return (int)(instance.getProxy().getEnergy().extractAEPower((double)(ticksPassed * bonusValue/ ModConfig.tweaks.ae2_molecular_transformer_slowdown) * acceleratorTax, Actionable.MODULATE, PowerMultiplier.CONFIG) / acceleratorTax);
        } catch (GridAccessException var6) {
            return 0;
        }
    }

}
