package igentuman.evtweaks.proxy;

import igentuman.evtweaks.EvTweaks;
import igentuman.evtweaks.network.PacketUpdateItemStack;
import igentuman.evtweaks.recipe.EvTweaksRecipes;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CommonProxy implements ISidedProxy {

  public void preInit(FMLPreInitializationEvent event) {
  }

  public void init(FMLInitializationEvent event) {

  }

  @Override
  public void handleUpdateItemStack(PacketUpdateItemStack message, MessageContext ctx) {
    EvTweaks.instance.logger.error("Got PacketUpdateItemStack on wrong side!");
  }
}