package igentuman.evtweaks.proxy;

import igentuman.evtweaks.network.PacketUpdateItemStack;
import igentuman.evtweaks.network.TileProcessUpdatePacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;

public class ClientProxy implements ISidedProxy {
    @Override
    public void handleUpdateItemStack(PacketUpdateItemStack message, MessageContext ctx) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        if (message.getWindowID() == player.openContainer.windowId
                && message.getWindowID() != 0
                && player.openContainer instanceof PacketUpdateItemStack.IUpdateNonSlotItemStack) {
            ((PacketUpdateItemStack.IUpdateNonSlotItemStack) player.openContainer).updateItem(message.getStackIndex(), message.getStack());
        }
    }

    @Override
    public void handleProcessUpdatePacket(TileProcessUpdatePacket message, MessageContext ctx) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        TileEntity te = Minecraft.getMinecraft().world.getTileEntity(message.pos);
    }
}
