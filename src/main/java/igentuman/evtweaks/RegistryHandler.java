package igentuman.evtweaks;

import igentuman.evtweaks.block.BlockMechanicalForgeHammer;
import igentuman.evtweaks.tile.TileEntityMechanicalForgeHammer;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber
public class RegistryHandler {

    @ObjectHolder("evtweaks:mechanical_forgehammer")
    public static Block MECHANICAL_FORGEHAMMER = new BlockMechanicalForgeHammer();

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(MECHANICAL_FORGEHAMMER);

        GameRegistry.registerTileEntity(
                TileEntityMechanicalForgeHammer.class,
                MECHANICAL_FORGEHAMMER.getRegistryName()
        );
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(MECHANICAL_FORGEHAMMER).setRegistryName(MECHANICAL_FORGEHAMMER.getRegistryName()));
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        registerItemModel(Item.getItemFromBlock(MECHANICAL_FORGEHAMMER), 0, "inventory");
    }

    @SideOnly(Side.CLIENT)
    public void registerItemModel(@Nonnull Item item, int meta, String variant) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), variant));
    }
}