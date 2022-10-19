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
                "Format: modid:blockid:meta"
        })
        public String[] jei_resource_scan_white_list = new String[]{
                "minecraft:coal_ore",
                "minecraft:iron_ore",
                "minecraft:gold_ore",
                "minecraft:redstone_ore",
                "minecraft:lapis_ore",
                "minecraft:quartz_ore",
                "minecraft:emerald_ore",
                "minecraft:diamond_ore",
                "immersiveengineering:ore",
                "immersiveengineering:ore:1",
                "immersiveengineering:ore:2",
                "immersiveengineering:ore:3",
                "immersiveengineering:ore:4",
                "immersiveengineering:ore:5",
                "mekamism:oreblock",
                "mekamism:oreblock:1",
                "mekamism:oreblock:2",
                "mekamism:oreblock:3",
                "ic2:resource",
                "ic2:resource:1",
                "ic2:resource:2",
                "ic2:resource:3",
                "ic2:resource:4"
        };


        @Config.Name("ae2_grinder_mechanical_power_required")
        @Config.Comment({
                "Less value - faster processing"
        })
        public int ae2_grinder_mechanical_power_required = 20;
    }
}
