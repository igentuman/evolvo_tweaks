package igentuman.evtweaks.integration.jei;

import igentuman.evtweaks.ModConfig;
import igentuman.evtweaks.recipe.MultiblockRecipe;
import igentuman.evtweaks.recipe.MultiblockRecipes;
import jeresources.entry.WorldGenEntry;
import jeresources.registry.WorldGenRegistry;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraftforge.fml.common.Loader;

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
        if(ModConfig.tweaks.enable_multiblocks_jei_category) {
            //multiblocks
            registry.handleRecipes(MultiblockRecipe.class, recipe -> new MultiblocksRecipeCategory.Wrapper(recipe), MultiblocksRecipeCategory.UID);
            registry.addRecipes(MultiblockRecipes.getAvaliableRecipes(), MultiblocksRecipeCategory.UID);
        }
        if(Loader.isModLoaded("jeresources") && ModConfig.tweaks.enable_multiblocks_jei_category) {
            registry.handleRecipes(WorldGenEntry.class, recipe -> new WorldGenRecipeCategory.Wrapper(recipe), WorldGenRecipeCategory.UID);
            registry.addRecipes(WorldGenRegistry.getInstance().getWorldGen(), WorldGenRecipeCategory.UID);
        }
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        final IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
        if(ModConfig.tweaks.enable_multiblocks_jei_category) {
            //multiblocks
            registry.addRecipeCategories(new MultiblocksRecipeCategory(guiHelper));
        }
        if(Loader.isModLoaded("jeresources") && ModConfig.tweaks.enable_multiblocks_jei_category) {
            registry.addRecipeCategories(new WorldGenRecipeCategory(guiHelper));
        }
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime runtime) {
        this.runtime = runtime;
    }

    public IJeiRuntime getRuntime() {
        return runtime;
    }
}
