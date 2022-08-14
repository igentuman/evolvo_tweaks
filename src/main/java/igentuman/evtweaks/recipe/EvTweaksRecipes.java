package igentuman.evtweaks.recipe;

import crafttweaker.api.recipes.IRecipeAction;
import crafttweaker.api.recipes.IRecipeManager;
import igentuman.evtweaks.util.ItemHelper;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryModifiable;

import java.util.LinkedList;
import java.util.List;

public class EvTweaksRecipes {

    private static final List<IRecipeAction> actions = new LinkedList<>();

    public static void init()
    {


        MultiblockRecipes.init();
    }

    public static void postInit()
    {
       // actions.forEach(IRecipeAction::apply);
    }

    public static void addScheduledAction(IRecipeAction action)
    {
        actions.add(action);
    }


    private static void register(IForgeRegistry<IRecipe> registry, IRecipe recipe)
    {
        recipe.setRegistryName(new ResourceLocation(recipe.getGroup()));
        registry.register(recipe);
    }

    private static void remove(IForgeRegistryModifiable<IRecipe> registry, String name)
    {
        registry.remove(new ResourceLocation("minecraft:" + name));
    }
}
