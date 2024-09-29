package igentuman.evtweaks.reflection;

import com.rwtema.extrautils2.items.ItemAngelRing;
import com.rwtema.extrautils2.power.player.PlayerPower;
import igentuman.evtweaks.integration.xu2.PlayerPowerAngel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemAngelRingReflection {
    public static PlayerPower createPower(ItemAngelRing instance, EntityPlayer player, ItemStack params) {
        return new PlayerPowerAngel(player, params.getItemDamage());
    }
}
