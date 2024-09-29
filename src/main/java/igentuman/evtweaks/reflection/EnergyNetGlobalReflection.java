package igentuman.evtweaks.reflection;

import ic2.core.energy.grid.EnergyNetGlobal;

public class EnergyNetGlobalReflection {
    public static double getPowerFromTier(EnergyNetGlobal instance, int tier) {
        tier++;
        if (tier < 14) {
            return (double)(8 << tier * 2);
        } else {
            return tier < 30 ? 8.0 * Math.pow(4.0, (double)tier) : 9.223372036854776E18;
        }
    }
}
