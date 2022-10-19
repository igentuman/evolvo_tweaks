package igentuman.evtweaks.integration.crafttweaker;

import com.google.common.collect.Lists;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.blamejared.mtlib.helpers.InputHelper.toStack;

@ZenClass("mods.evtweaks.Trade")
@ZenRegister
public class MerchantTrade {

  protected static final String name = "Trade";

  public static final List<MerchantRecipe> removeTraids = Lists.newArrayList();

  @ZenMethod
  public static void remove(IItemStack buy1, IItemStack buy2, IItemStack sell) {
    removeTraids.add(new MerchantRecipe(toStack(buy1),toStack(buy2),toStack(sell)));
  }

  @ZenMethod
  public static void remove(IItemStack buy1, IItemStack sell) {
    removeTraids.add(new MerchantRecipe(toStack(buy1), ItemStack.EMPTY,toStack(sell)));
  }

  @ZenMethod
  public static void addTrade(String profession, Integer careerId, IItemStack buy1, IItemStack buy2, IItemStack sell) {
    MerchantRecipe mr = new MerchantRecipe(toStack(buy1), toStack(buy2), toStack(sell));
    String merchant = profession+":"+careerId;
    if(!TradeFilter.TRADES.keySet().contains(merchant)) {
      TradeFilter.TRADES.put(merchant, new ArrayList<>());
    }
    TradeFilter.TRADES.get(merchant).add(mr);
  }

  @ZenMethod
  public static void addTrade(String profession, Integer careerId, IItemStack buy1, IItemStack sell) {
    MerchantRecipe mr = new MerchantRecipe(toStack(buy1), toStack(sell));
    String merchant = profession+":"+careerId;
    if(!TradeFilter.TRADES.keySet().contains(merchant)) {
      TradeFilter.TRADES.put(merchant, new ArrayList<>());
    }
    TradeFilter.TRADES.get(merchant).add(mr);
  }

  public static class TradeList implements ITradeList {
    public ItemStack buyingItem;
    public ItemStack selling;
    public EntityVillager.PriceInfo buyAmounts;

    public TradeList(@Nonnull ItemStack item,@Nonnull ItemStack sell) {
      this.buyingItem = item;
      this.selling = sell;
      this.buyAmounts = new EntityVillager.PriceInfo(4, 7);
    }

    public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
      MerchantRecipe mr = new MerchantRecipe(this.buyingItem, selling);
      recipeList.add(mr);
      if(!TradeFilter.TRADES.keySet().contains(TradeFilter.getMerchId(merchant))) {
        TradeFilter.TRADES.put(TradeFilter.getMerchId(merchant), new ArrayList<>());
      }
      TradeFilter.TRADES.get(TradeFilter.getMerchId(merchant)).add(mr);
    }
  }
}
