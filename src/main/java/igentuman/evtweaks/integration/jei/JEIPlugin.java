package igentuman.evtweaks.integration.jei;

import igentuman.evtweaks.recipe.MultiblockRecipe;
import igentuman.evtweaks.recipe.MultiblockRecipes;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

@mezz.jei.api.JEIPlugin
public class JEIPlugin implements IModPlugin {
    public static JEIPlugin INSTANCE;

    private IJeiRuntime runtime;

    public JEIPlugin() {
        super();
    }

    @Override
    public void register(IModRegistry registry) {
        INSTANCE = this;
        //multiblocks
        registry.handleRecipes(MultiblockRecipe.class, recipe -> new MultiblocksRecipeCategory.Wrapper(recipe), MultiblocksRecipeCategory.UID);
        registry.addRecipes(MultiblockRecipes.getAvaliableRecipes(), MultiblocksRecipeCategory.UID);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        final IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
        //multiblocks
        registry.addRecipeCategories(new MultiblocksRecipeCategory(guiHelper));
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime runtime) {
        this.runtime = runtime;
    }

    public IJeiRuntime getRuntime() {
        return runtime;
    }
}
