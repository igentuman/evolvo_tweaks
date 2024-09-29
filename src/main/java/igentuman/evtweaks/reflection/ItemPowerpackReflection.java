package igentuman.evtweaks.reflection;

import blusunrize.immersiveengineering.common.items.ItemPowerpack;
import net.minecraft.item.ItemStack;

public class ItemPowerpackReflection {
    public static int getMaxEnergyStored(ItemPowerpack instance, ItemStack container) {
        return 2000000;
    }
}
