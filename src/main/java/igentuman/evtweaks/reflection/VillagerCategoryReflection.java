package igentuman.evtweaks.reflection;

import igentuman.evtweaks.integration.crafttweaker.TradeFilter;
import jeresources.collection.TradeList;
import jeresources.jei.villager.VillagerCategory;
import jeresources.jei.villager.VillagerWrapper;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IFocus;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;

import javax.annotation.Nonnull;
import java.util.Iterator;

public class VillagerCategoryReflection {

    public static void setRecipe(VillagerCategory instance, @Nonnull IRecipeLayout recipeLayout, @Nonnull VillagerWrapper recipeWrapper, @Nonnull IIngredients ingredients) {
        IFocus<ItemStack> focus = (IFocus<ItemStack>) recipeLayout.getFocus();
        recipeWrapper.setFocus(focus);
        int y = 22 * (6 - recipeWrapper.getPossibleLevels(focus).size()) / 2;

        int i;
        for(i = 0; i < recipeWrapper.getPossibleLevels(focus).size(); ++i) {
            recipeLayout.getItemStacks().init(3 * i, true, 95, y + i * 22);
            recipeLayout.getItemStacks().init(3 * i + 1, true, 113, y + i * 22);
            recipeLayout.getItemStacks().init(3 * i + 2, false, 150, y + i * 22);
        }

        i = 0;

        for(Iterator var7 = recipeWrapper.getPossibleLevels(focus).iterator(); var7.hasNext(); ++i) {
            int level = (Integer)var7.next();
            TradeList tradeList = recipeWrapper.getTrades(level).getFocusedList(focus);
            try {
                if(TradeFilter.validateRecipe(new MerchantRecipe(tradeList.getFirstBuyStacks().get(i),tradeList.getSecondBuyStacks().get(i),tradeList.getSellStacks().get(i)))) {
                    recipeLayout.getItemStacks().set(3 * i, tradeList.getFirstBuyStacks());
                    recipeLayout.getItemStacks().set(3 * i + 1, tradeList.getSecondBuyStacks());
                    recipeLayout.getItemStacks().set(3 * i + 2, tradeList.getSellStacks());
                }
            } catch (IndexOutOfBoundsException | NullPointerException ignored) {

            }

        }
    }
}
