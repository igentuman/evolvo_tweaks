package igentuman.evtweaks.integration.jei;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.collect.ImmutableList;
import igentuman.evtweaks.recipe.ForgeHammerRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;

import static igentuman.evtweaks.ModInfo.MODID;

@ParametersAreNonnullByDefault
public class ForgeHammerRecipeCategory implements IRecipeCategory<ForgeHammerRecipeCategory.Wrapper>
{
    private static final ResourceLocation GUI_LOCATION = new ResourceLocation(MODID, "textures/gui/mechanical_forgehammer.png");
    private static final String TRANSLATION_KEY = "jei.category.mechanical_forgehammer";

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated animatedArrow;
    private final IDrawableAnimated animatedFlame;

    public ForgeHammerRecipeCategory(IGuiHelper guiHelper)
    {

        background = guiHelper.createDrawable(GUI_LOCATION, 0, 0, 74, 54);
        icon = guiHelper.createDrawable(GUI_LOCATION, 111, 0, 16, 16);

        IDrawableStatic staticArrow = guiHelper.createDrawable(GUI_LOCATION, 88, 0, 16, 23);
        animatedArrow = guiHelper.createAnimatedDrawable(staticArrow, 200, IDrawableAnimated.StartDirection.LEFT, false);

        IDrawableStatic staticFlame = guiHelper.createDrawable(GUI_LOCATION, 74, 0, 14, 13);
        animatedFlame = guiHelper.createAnimatedDrawable(staticFlame, 300, IDrawableAnimated.StartDirection.TOP, true);

    }

    @Nonnull
    @Override
    public String getUid()
    {
        return MODID+"_forgehammer";
    }

    @Nonnull
    @Override
    public String getTitle()
    {
        return I18n.format(TRANSLATION_KEY);
    }

    @Nonnull
    @Override
    public String getModName()
    {
        return MODID;
    }

    @Nonnull
    @Override
    public IDrawable getBackground()
    {
        return background;
    }

    @Override
    public IDrawable getIcon()
    {
        return icon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void drawExtras(Minecraft minecraft)
    {
        animatedArrow.draw(minecraft, 26, 1);
        animatedFlame.draw(minecraft, 30, 20);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, Wrapper wrapper, IIngredients ingredients)
    {
        int index = 0;
        recipeLayout.getItemStacks().init(index, true, 0, 0);
        recipeLayout.getItemStacks().set(index, ingredients.getInputs(ItemStack.class).get(0));

        index++;
        recipeLayout.getItemStacks().init(index, false, 56, 0);
        recipeLayout.getItemStacks().set(index, ingredients.getOutputs(ItemStack.class).get(0));
    }

    public static class Wrapper implements IRecipeWrapper
    {
        private final List<List<ItemStack>> input;
        private final List<List<ItemStack>> output;

        public Wrapper(ForgeHammerRecipe recipe)
        {
            ImmutableList.Builder<List<ItemStack>> builder = ImmutableList.builder();

            // Add the ingredient
            builder.add(recipe.getInput().getStacks());

            // Set the input
            this.input = builder.build();

            // Reset builder and add output
            builder = ImmutableList.builder();
            builder.add(ImmutableList.of(recipe.getOutput()));

            // Set the output
            output = builder.build();

        }

        @Override
        public void getIngredients(@Nonnull IIngredients ingredients)
        {
            ingredients.setInputLists(ItemStack.class, input);
            ingredients.setOutputLists(ItemStack.class, output);
        }
    }
}