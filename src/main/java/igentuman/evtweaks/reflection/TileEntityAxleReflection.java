package igentuman.evtweaks.reflection;
import mysticalmechanics.api.MysticalMechanicsAPI;
import mysticalmechanics.tileentity.TileEntityAxle;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import appeng.tile.grindstone.TileGrinder;

public class TileEntityAxleReflection {

    public static int slowdown = 20;

    public static boolean aeGrinder(TileEntityAxle instance, EnumFacing dir)
    {
        BlockPos connectPos = instance.getPos().offset(dir);
        TileEntity tile = instance.getWorld().getTileEntity(connectPos);
        double rotation = instance.getCapability(MysticalMechanicsAPI.MECH_CAPABILITY, (EnumFacing)null).getPower((EnumFacing)null);
        if(tile instanceof  TileGrinder) {
            if(rotation > 0) {
                slowdown -= rotation/20;
                if(slowdown < 1) {
                    if (((TileGrinder) tile).canTurn()) {
                        ((TileGrinder) tile).applyTurn();
                    }
                    slowdown =20;
                }
            }
            return true;
        }
        return false;
    }

    public static void update(TileEntityAxle instance) {
        if (instance.getWorld().isRemote) {
            instance.lastAngle = instance.angle;
            instance.angle += instance.getCapability(MysticalMechanicsAPI.MECH_CAPABILITY, (EnumFacing)null).getPower((EnumFacing)null);
        } else {
            if (!aeGrinder(instance, instance.getBackward())) {
                aeGrinder(instance, instance.getForward());
            }
        }
    }

}
