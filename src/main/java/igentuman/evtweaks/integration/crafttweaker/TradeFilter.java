package igentuman.evtweaks.integration.crafttweaker;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.NpcMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.village.MerchantTradeOffersEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import rocks.gameonthe.rockytweaks.ModInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = ModInfo.MODID)
public class TradeFilter {

    public TradeFilter() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static Map<String, List<MerchantRecipe>> TRADES = new HashMap<>();

    public static String getMerchId(IMerchant merchant)
    {
        if(merchant instanceof EntityVillager) {
            EntityVillager villager = (EntityVillager) merchant;
            return villager.getProfessionForge().getRegistryName().toString()+":"+villager.careerId;
        }
        if(merchant instanceof NpcMerchant) {
            NpcMerchant npc = (NpcMerchant) merchant;
            if(!(npc.merchantInventory.merchant instanceof EntityVillager)) {
                return "dummy";
            }
            EntityVillager villager = (EntityVillager) npc.merchantInventory.merchant;
            return villager.getProfessionForge().getRegistryName().toString() + ":" + villager.careerId;
        }
        return "dummy";
    }

    public static boolean validateRecipe(MerchantRecipe recipe)
    {
        for (MerchantRecipe toRemove : MerchantTrade.removeTraids) {
            if (recipe.getItemToSell().getTranslationKey().equals(toRemove.getItemToSell().getTranslationKey()) &&
                    recipe.getItemToBuy().getTranslationKey().equals(toRemove.getItemToBuy().getTranslationKey()) &&
                    (recipe.getSecondItemToBuy().getTranslationKey().equals(toRemove.getSecondItemToBuy().getTranslationKey()) ||
                            recipe.getSecondItemToBuy().getTranslationKey().equals(ItemStack.EMPTY.getTranslationKey()))) {
                return false;
            }
        }
        return true;
    }

    @SubscribeEvent
    public void MerchantTradeOffersEvent(MerchantTradeOffersEvent event) {

        if(event.getMerchant().getWorld().isRemote) return;
        if(!TRADES.keySet().contains(getMerchId(event.getMerchant()))) {
            return;
        }
        MerchantRecipeList newList = new MerchantRecipeList();
        if(event.getList().size() == 0) return;
        for (MerchantRecipe recipe : event.getList()) {
            if(validateRecipe(recipe)) {
                newList.add(recipe);
            }
        }
        newList.addAll(TRADES.get(getMerchId(event.getMerchant())));
        event.setList(newList);
    }

}
