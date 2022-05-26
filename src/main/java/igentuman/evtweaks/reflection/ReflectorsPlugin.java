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

        if (transformedName.equals("appeng.tile.crafting.TileMolecularAssembler")) {
            return Reflectors.reflectClass(basicClass, transformedName, TileMolecularAssemblerReflection.class.getName());
        }

        return basicClass;
    }
}