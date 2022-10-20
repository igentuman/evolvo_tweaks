package igentuman.evtweaks.integration.jei;

import igentuman.evtweaks.ModInfo;
import jeresources.entry.WorldGenEntry;
import jeresources.util.Font;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.*;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.*;

public class WorldGenRecipeCategory implements IRecipeCategory<WorldGenRecipeCategory.Wrapper>, ITooltipCallback<ItemStack> {
    public static final String UID = ModInfo.MODID + "_worldgen";
    private final String localizedName;
    private final IDrawableStatic background;
    private final IDrawableStatic slotDrawable;

    public WorldGenRecipeCategory(IGuiHelper guiHelper) {
        localizedName = I18n.format(ModInfo.MODID+".jei.category.worldgen");
        background = guiHelper.createBlankDrawable(100, 35);
        slotDrawable = guiHelper.getSlotDrawable();
    }


    @Override
    public String getUid() {
        return UID;
    }

    @Override
    public String getTitle() {
        return localizedName;
    }

    /**
     * Return the name of the mod associated with this recipe category.
     * Used for the recipe category tab's tooltip.
     *
     * @since JEI 4.5.0
     */
    @Override
    public String getModName() {
        return ModInfo.MODID;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }


    @Nullable
    @Override
    public IDrawable getIcon() {
        return null;
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        slotDrawable.draw(minecraft, 5, 5);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, Wrapper recipeWrapper, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, false, 5, 5);
        recipeLayout.getItemStacks().set(0, recipeWrapper.recipe.getBlock());
    }


    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        return Collections.emptyList();
    }

    @Override
    public void onTooltip(int slotIndex, boolean input, ItemStack ingredient, List<String> tooltip) {
         tooltip.add(TextFormatting.YELLOW + I18n.format("tooltip.compactmachines3.jei.shape"));
    }

    public static class Wrapper implements IRecipeWrapper {
        public final WorldGenEntry recipe;
        private final List<ItemStack> input = new ArrayList<>();
        public IIngredients ingredients;

        public Wrapper(WorldGenEntry recipe) {
            this.recipe = recipe;
            this.input.add(this.recipe.getBlock());
        }

        public boolean handleClick(Minecraft minecraft, int mouseX, int mouseY, int mouseButton) {
            return false;
        }


        @Override
        public void getIngredients(IIngredients ingredients) {
            this.ingredients = ingredients;
            ingredients.setInputs(ItemStack.class, input);
            ingredients.setOutput(ItemStack.class, this.recipe.getBlock());
        }

        public int getBestHeight()
        {
            int h = 0;
            int i = 0;
            float best = 0;
            for (float chance: recipe.getChances()) {
                if(chance>best) {
                    best = chance;
                    h = i;
                }
                i++;
            }
            return h;
        }

        @Override
        public void drawInfo(Minecraft mc, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
            int bestHeight = getBestHeight();
            Font.small.print(I18n.format("evtweaks.wg.minY", bestHeight), 28, 5);
            String worlds = recipe.getDimension();
            Font.small.print(I18n.format("evtweaks.wg.world", worlds), 5, 25);
        }
    }
}
