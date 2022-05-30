package igentuman.evtweaks.reflection;

import jeresources.config.ConfigHandler;
import jeresources.profiling.ProfilingBlacklist;
import net.minecraft.block.state.IBlockState;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class ProfilingBlacklistReflector {
    public static List<String> whitelist = new LinkedList();
    public static void init()
    {
        File scanBlacklistFile = new File("config/jeresources", "scan-whitelist.txt");
        if (scanBlacklistFile.exists()) {
            try {
                whitelist = Files.readAllLines(scanBlacklistFile.toPath());
            } catch (IOException var3) {
            }
        }
    }
    public static boolean contains(ProfilingBlacklist instance, IBlockState blockState) {
        if(whitelist.isEmpty()) {
            init();
        }
        String blockString = blockState.toString();
        return !whitelist.contains(blockString);
    }
}
