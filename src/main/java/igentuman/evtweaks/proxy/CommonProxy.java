package igentuman.evtweaks.proxy;

import igentuman.evtweaks.EvTweaks;
import igentuman.evtweaks.network.PacketUpdateItemStack;
import igentuman.evtweaks.network.TileProcessUpdatePacket;
import igentuman.evtweaks.recipe.EvTweaksRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
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

  @Override
  public void handleProcessUpdatePacket(TileProcessUpdatePacket message, MessageContext ctx) {
    EntityPlayer player = Minecraft.getMinecraft().player;
    EvTweaks.instance.logger.error("Got PacketUpdateItemStack on wrong side!");
  }
}