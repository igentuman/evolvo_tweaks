package igentuman.evtweaks.reflection;

import igentuman.evtweaks.ModConfig;
import jeresources.config.ConfigHandler;
import jeresources.profiling.ProfilingBlacklist;
import net.minecraft.block.state.IBlockState;
import scala.actors.threadpool.Arrays;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class ProfilingBlacklistReflector {
    public static List<String> whitelist;
    public static void init()
    {
       whitelist = Arrays.asList(ModConfig.tweaks.jei_resource_scan_white_list);
    }
    public static boolean contains(ProfilingBlacklist instance, IBlockState blockState) {
        if(whitelist.isEmpty()) {
            init();
        }
        String blockString = blockState.toString();
        return !whitelist.contains(blockString);
    }
}
