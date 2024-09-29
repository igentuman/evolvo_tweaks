package igentuman.evtweaks.reflection;

import com.github.mjaroslav.reflectors.v4.Reflectors;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.MCVersion("1.12.2")
@IFMLLoadingPlugin.SortingIndex(1001)
@IFMLLoadingPlugin.Name("ReflectorsPlugin")
public class ReflectorsPlugin extends Reflectors.FMLLoadingPluginAdapter
        implements IFMLLoadingPlugin, IClassTransformer {
    public ReflectorsPlugin() {
        Reflectors.enabledLogs = true;
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[]{getClass().getName()};
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        //rotations
        if (transformedName.equals("mysticalmechanics.tileentity.TileEntityAxle")) {
            return Reflectors.reflectClass(basicClass, transformedName, TileEntityAxleReflection.class.getName());
        }
        //ic2
        if (transformedName.equals("ic2.core.energy.grid.EnergyNetGlobal")) {
            return Reflectors.reflectClass(basicClass, transformedName, EnergyNetGlobalReflection.class.getName());
        }
        //powerpack
        if (transformedName.equals("blusunrize.immersiveengineering.common.items.ItemPowerpack")) {
            return Reflectors.reflectClass(basicClass, transformedName, ItemPowerpackReflection.class.getName());
        }
        //angel ring
        if (transformedName.equals("com.rwtema.extrautils2.items.ItemAngelRing")) {
            return Reflectors.reflectClass(basicClass, transformedName, ItemAngelRingReflection.class.getName());
        }

        //bellows expand
        if (transformedName.equals("decivex.mech_crafting.mechanical_bellows.TileEntityMechanicalBellows")) {
            return Reflectors.reflectClass(basicClass, transformedName, TileEntityMechanicalBellowsReflection.class.getName());
        }

        //whitelist instead
        if (transformedName.equals("jeresources.profiling.ProfilingBlacklist")) {
            return Reflectors.reflectClass(basicClass, transformedName, ProfilingBlacklistReflector.class.getName());
        }

        //assembler slowdown
        if (transformedName.equals("appeng.tile.crafting.TileMolecularAssembler")) {
            return Reflectors.reflectClass(basicClass, transformedName, TileMolecularAssemblerReflection.class.getName());
        }

        //vilager trades
        if (transformedName.equals("jeresources.jei.villager.VillagerCategory")) {
            return Reflectors.reflectClass(basicClass, transformedName, VillagerCategoryReflection.class.getName());
        }
        
        return basicClass;
    }
}