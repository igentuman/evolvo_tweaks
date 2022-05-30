package igentuman.evtweaks.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ModPacketHandler {
    private static int packetId = 0;
    public static SimpleNetworkWrapper INSTANCE = null;

    public static void registerMessages(String channelName) {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
        INSTANCE.registerMessage(
                PacketUpdateItemStack.Handler.class,
                PacketUpdateItemStack.class,
                packetId++,
                Side.CLIENT
        );
    }
}