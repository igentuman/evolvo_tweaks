package igentuman.evtweaks.proxy;

import igentuman.evtweaks.recipe.EvTweaksRecipes;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface ISidedProxy {

    default void preInit(FMLPreInitializationEvent event) {
    }

    default void init(FMLInitializationEvent event) {
        EvTweaksRecipes.init();
    }

}