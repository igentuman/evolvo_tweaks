package igentuman.evtweaks.integration.jei;

import igentuman.evtweaks.recipe.EvTweaksRecipes;
import igentuman.evtweaks.recipe.ForgeHammerRecipe;
import igentuman.evtweaks.recipe.MultiblockRecipe;
import igentuman.evtweaks.recipe.MultiblockRecipes;
import igentuman.evtweaks.util.ItemHelper;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

import static igentuman.evtweaks.ModInfo.MODID;

@mezz.jei.api.JEIPlugin
public class JEIPlugin implements IModPlugin {
    public JEIPlugin() {
        super();
    }

    @Override
    public void register(IModRegistry registry) {
        registry.handleRecipes(ForgeHammerRecipe.class, ForgeHammerRecipeCategory.Wrapper::new, MODID+"_forgehammer");
        registry.addRecipeCatalyst(ItemHelper.getStackFromString("evtweaks:mechanical_forgehammer",0),MODID+"_forgehammer");
        registry.addRecipes(EvTweaksRecipes.FORGE_HAMMER.getAll(), MODID+"_forgehammer");

        //multiblocks
        registry.handleRecipes(MultiblockRecipe.class, recipe -> new MultiblocksRecipeCategory.Wrapper(recipe), MultiblocksRecipeCategory.UID);
        registry.addRecipes(MultiblockRecipes.getRecipes(), MultiblocksRecipeCategory.UID);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        final IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
        registry.addRecipeCategories(new ForgeHammerRecipeCategory(guiHelper));
        //multiblocks
        registry.addRecipeCategories(new MultiblocksRecipeCategory(guiHelper));
    }

}
