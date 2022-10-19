package igentuman.evtweaks;

import blusunrize.immersiveengineering.common.util.IEVillagerHandler;
import igentuman.evtweaks.integration.crafttweaker.TradeFilter;
import igentuman.evtweaks.proxy.ISidedProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static igentuman.evtweaks.ModInfo.MODID;

@Mod(
    name = ModInfo.NAME,
    modid = MODID,
    version = ModInfo.VERSION,
    acceptedMinecraftVersions = ModInfo.MC_VERSION,
    dependencies = ModInfo.DEPENDENCIES
)
public class EvTweaks {

  @Mod.Instance(MODID)
  public static EvTweaks instance;

  @SidedProxy(clientSide = "igentuman.evtweaks.proxy.ClientProxy", serverSide = "igentuman.evtweaks.proxy.CommonProxy")
  public static ISidedProxy proxy;

  public static final Logger logger = LogManager.getLogger(MODID);

  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    logger.info("Starting PreInitialization.");
    proxy.preInit(event);

    MinecraftForge.EVENT_BUS.register(new RegistryHandler());
    MinecraftForge.EVENT_BUS.register(this);
    IEVillagerHandler.initIEVillagerTrades();
    new TradeFilter();
  }

  @EventHandler
  public void init(FMLInitializationEvent event) {
    logger.info("Starting Initialization.");
    proxy.init(event);
    ConfigManager.sync(MODID, Config.Type.INSTANCE);

  }

  @SubscribeEvent
  public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
    if(event.getModID().equals(MODID)) {
      ConfigManager.sync(MODID, Config.Type.INSTANCE);
    }
  }
}
