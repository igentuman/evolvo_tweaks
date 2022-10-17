package igentuman.evtweaks;

import net.minecraftforge.common.config.Config;

@Config(modid = ModInfo.MODID)
public class  ModConfig {
    public static Tweaks tweaks = new Tweaks();

    public static class Tweaks {
        @Config.Name("ae2_molecular_transformer_slowdown")
        @Config.Comment({
                "Value Range 1-10"
        })
        public double ae2_molecular_transformer_slowdown = 10;

        @Config.Name("jei_resource_scan_white_list")
        @Config.Comment({
                "Define list of block ids JEI Resources will count while scan command",
                "Format: modid:blockid"
        })
        public String[] jei_resource_scan_white_list = new String[]{
                "minecraft:coal_ore",
                "minecraft:iron_ore",
                "minecraft:gold_ore"
        };


        @Config.Name("ae2_grinder_mechanical_power_required")
        @Config.Comment({
                "Less value - faster processing"
        })
        public int ae2_grinder_mechanical_power_required = 20;
    }
}
