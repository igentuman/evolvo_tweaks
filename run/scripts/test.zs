#debug

#contenttweaker
import mods.rockytweaks.Anvil;
import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.oredict.IOreDictEntry;
import mods.rockytweaks.Merchant;
import mods.evtweaks.Trade;

Anvil.addRecipe(<immersiveengineering:tool:0>.reuse(), <minecraft:iron_ingot> * 8, <minecraft:stone> * 4, 1);

Trade.remove(<immersiveengineering:bullet>, <minecraft:emerald>);
Trade.remove(<immersiveengineering:bullet:1> , <minecraft:emerald>);
Trade.remove(<minecraft:emerald>, <immersiveengineering:material:13>);
Trade.addTrade("immersiveengineering:engineer",1, <minecraft:iron_ingot>, <minecraft:diamond>);
Trade.addTrade("immersiveengineering:engineer",1, <minecraft:iron_ingot>, <minecraft:dirt>, <minecraft:diamond>);
Trade.addTrade("immersiveengineering:engineer",2, <minecraft:iron_nugget>, <minecraft:diamond>);
Trade.addTrade("immersiveengineering:engineer",3, <minecraft:iron_block>, <minecraft:diamond>);
Trade.addTrade("immersiveengineering:engineer",4, <minecraft:gold_ingot>, <minecraft:diamond>);
Trade.addTrade("immersiveengineering:engineer",5, <minecraft:gold_nugget>, <minecraft:diamond>);
Trade.addTrade("immersiveengineering:engineer",5, <minecraft:iron_ingot>, <minecraft:diamond>);
Trade.addTrade("immersiveengineering:engineer",5, <minecraft:dirt>,<minecraft:stone>, <minecraft:diamond>);

Merchant.addTrade("minecraft:nitwit", "nitwit", <minecraft:emerald>, <minecraft:diamond>, <minecraft:cobblestone>, 1);