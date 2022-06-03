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
    public static final RecipeManager<ForgeHammerRecipe> FORGE_HAMMER = new RecipeManager<>();

    private static final List<IRecipeAction> actions = new LinkedList<>();

    public static void init()
    {

        FORGE_HAMMER.add(new ForgeHammerRecipe(
                ItemHelper.getStackFromString("ic2:plate",3),
                ItemHelper.getStackFromString("minecraft:iron_ingot",0)
                ));
        FORGE_HAMMER.add(new ForgeHammerRecipe(
                ItemHelper.getStackFromString("ic2:plate",12),
                ItemHelper.getStackFromString("minecraft:iron_block",0)
                ));
        FORGE_HAMMER.add(new ForgeHammerRecipe(
                ItemHelper.getStackFromString("ic2:plate",10),
                ItemHelper.getStackFromString("ic2:resource",6)
                ));
        FORGE_HAMMER.add(new ForgeHammerRecipe(
                ItemHelper.getStackFromString("ic2:plate",1),
                ItemHelper.getStackFromString("ic2:ingot",2)
                ));
        FORGE_HAMMER.add(new ForgeHammerRecipe(
                ItemHelper.getStackFromString("ic2:plate",8),
                ItemHelper.getStackFromString("ic2:ingot",6)
                ));
        FORGE_HAMMER.add(new ForgeHammerRecipe(
                ItemHelper.getStackFromString("ic2:plate",17),
                ItemHelper.getStackFromString("ic2:resource",9)
                ));
        FORGE_HAMMER.add(new ForgeHammerRecipe(
                ItemHelper.getStackFromString("ic2:plate",14),
                ItemHelper.getStackFromString("ic2:resource",7)
                ));
        FORGE_HAMMER.add(new ForgeHammerRecipe(
                ItemHelper.getStackFromString("ic2:plate",5),
                ItemHelper.getStackFromString("ic2:ingot",3)
                ));
        FORGE_HAMMER.add(new ForgeHammerRecipe(
                ItemHelper.getStackFromString("ic2:casing",1),
                ItemHelper.getStackFromString("ic2:plate",1)
                ));
        FORGE_HAMMER.add(new ForgeHammerRecipe(
                ItemHelper.getStackFromString("ic2:casing",6),
                ItemHelper.getStackFromString("ic2:plate",8)
                ));
        FORGE_HAMMER.add(new ForgeHammerRecipe(
                ItemHelper.getStackFromString("ic2:casing",4),
                ItemHelper.getStackFromString("ic2:plate",5)
                ));
        FORGE_HAMMER.add(new ForgeHammerRecipe(
                ItemHelper.getStackFromString("ic2:casing",3),
                ItemHelper.getStackFromString("ic2:plate",3)
                ));

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
