package igentuman.evtweaks.reflection;

import igentuman.evtweaks.ModConfig;
import jeresources.config.ConfigHandler;
import jeresources.profiling.ProfilingBlacklist;
import net.minecraft.block.state.IBlockState;
import org.apache.logging.log4j.Level;
import rocks.gameonthe.rockytweaks.RockyTweaks;
import scala.actors.threadpool.Arrays;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class ProfilingBlacklistReflector {

    public static List<String> whitelist = new ArrayList<>();
    public static void init()
    {
       whitelist = Arrays.asList(ModConfig.tweaks.jei_resource_scan_white_list);
    }

    public static boolean contains(ProfilingBlacklist instance, IBlockState blockState) {
        if(whitelist.isEmpty()) {
            init();
        }
        int meta = blockState.getBlock().getMetaFromState(blockState);
        String name = blockState.getBlock().getRegistryName().toString();
        //if(!name.startsWith("minecraft"))
        //RockyTweaks.logger.log(Level.INFO, name+":"+meta);
        if(meta == 0) {
            return isWhitelisted(name);
        }
        return isWhitelisted(name+":"+meta);
    }

    public static boolean isWhitelisted(String name)
    {
        return !whitelist.contains(name);
    }
}
