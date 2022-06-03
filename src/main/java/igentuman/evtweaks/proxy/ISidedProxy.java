package igentuman.evtweaks.proxy;

import igentuman.evtweaks.network.PacketUpdateItemStack;
import igentuman.evtweaks.network.TileProcessUpdatePacket;
import igentuman.evtweaks.recipe.EvTweaksRecipes;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public interface ISidedProxy {

    default void preInit(FMLPreInitializationEvent event) {
    }

    default void init(FMLInitializationEvent event) {
        EvTweaksRecipes.init();
    }

    void handleUpdateItemStack(PacketUpdateItemStack message, MessageContext context);

    void handleProcessUpdatePacket(TileProcessUpdatePacket message, MessageContext context);

}