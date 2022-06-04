package igentuman.evtweaks.reflection;

import blusunrize.immersiveengineering.common.blocks.stone.TileEntityAlloySmelter;
import blusunrize.immersiveengineering.common.blocks.stone.TileEntityBlastFurnace;
import blusunrize.immersiveengineering.common.blocks.stone.TileEntityBlastFurnaceAdvanced;
import blusunrize.immersiveengineering.common.blocks.stone.TileEntityCokeOven;
import decivex.mech_crafting.ModConfig;
import decivex.mech_crafting.mechanical_bellows.TileEntityMechanicalBellows;
import ic2.core.block.machine.tileentity.TileEntityIronFurnace;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.smeltery.tileentity.TileCasting;
import slimeknights.tconstruct.smeltery.tileentity.TileCastingBasin;

public class TileEntityMechanicalBellowsReflection {

    public static void update(TileEntityMechanicalBellows instance) {
        double addRotation = instance.getRotationSpeed();
        instance.currentRotation = (instance.currentRotation + addRotation) % 360.0D;
        if (!instance.getWorld().isRemote) {
            instance.totalRotation += addRotation;
            EnumFacing direction = instance.findDirection();
            BlockPos target = instance.getPos().offset(direction);
            TileEntity te = instance.getWorld().getTileEntity(target);

            //vanilla furnace
            if (te instanceof TileEntityFurnace) {
                double rpbt = ModConfig.mechanicalBellows.rotationPerBurnTick;
                if (instance.totalRotation >= rpbt) {
                    double newTotal = instance.totalRotation % rpbt;
                    int ticksToAdd = (int)((instance.totalRotation - newTotal) / rpbt);
                    instance.heatFurnace((TileEntityFurnace)te, ticksToAdd);
                    instance.totalRotation = newTotal;
                }
                return;
            }

            //ic2 iron furnace
            if (te instanceof TileEntityIronFurnace) {
                double rpbt = ModConfig.mechanicalBellows.rotationPerBurnTick;
                if (instance.totalRotation >= rpbt) {
                    double newTotal = instance.totalRotation % rpbt;
                    int ticksToAdd = (int)(((instance.totalRotation - newTotal) / rpbt)*1.5f);
                    heatIc2Furnace((TileEntityIronFurnace)te, ticksToAdd);
                    instance.totalRotation = newTotal;
                }
                return;
            }

            //tc cast
            if (te instanceof TileCasting) {
                double rpbt = ModConfig.mechanicalBellows.rotationPerBurnTick;
                if (instance.totalRotation >= rpbt) {
                    double newTotal = instance.totalRotation % rpbt;
                    int ticksToAdd = (int)(((instance.totalRotation - newTotal) / rpbt)*3f);
                    coolCasting((TileCasting)te, ticksToAdd);
                    instance.totalRotation = newTotal;
                }
                return;
            }


            //blast furnace
            if (te instanceof TileEntityBlastFurnace) {
                double rpbt = ModConfig.mechanicalBellows.rotationPerBurnTick;
                if (instance.totalRotation >= rpbt) {
                    double newTotal = instance.totalRotation % rpbt;
                    int ticksToAdd = (int)(((instance.totalRotation - newTotal) / rpbt));
                    heatBlastFurnace((TileEntityBlastFurnace)te, ticksToAdd);
                    instance.totalRotation = newTotal;
                }
                return;
            }

            //advanced blast furnace
            if (te instanceof TileEntityBlastFurnaceAdvanced) {
                double rpbt = ModConfig.mechanicalBellows.rotationPerBurnTick;
                if (instance.totalRotation >= rpbt) {
                    double newTotal = instance.totalRotation % rpbt;
                    int ticksToAdd = (int)(((instance.totalRotation - newTotal) / rpbt)*1.5f);
                    heatBlastFurnaceAdv((TileEntityBlastFurnaceAdvanced)te, ticksToAdd);
                    instance.totalRotation = newTotal;
                }
                return;
            }

            //coke oven
            if (te instanceof TileEntityCokeOven) {
                double rpbt = ModConfig.mechanicalBellows.rotationPerBurnTick;
                if (instance.totalRotation >= rpbt) {
                    double newTotal = instance.totalRotation % rpbt;
                    int ticksToAdd = (int)(((instance.totalRotation - newTotal) / rpbt));
                    heatCokeOven((TileEntityCokeOven)te, ticksToAdd);
                    instance.totalRotation = newTotal;
                }
                return;
            }

            //kiln
            if (te instanceof TileEntityAlloySmelter) {
                double rpbt = ModConfig.mechanicalBellows.rotationPerBurnTick;
                if (instance.totalRotation >= rpbt) {
                    double newTotal = instance.totalRotation % rpbt;
                    int ticksToAdd = (int)(((instance.totalRotation - newTotal) / rpbt));
                    heatKiln((TileEntityAlloySmelter)te, ticksToAdd);
                    instance.totalRotation = newTotal;
                }
                return;
            }
        }
    }

    public static void coolCasting(TileCasting casting, int progress) {
        for (int i = 0; i < progress; i++) {
            casting.update();
        }
    }

    public static void heatIc2Furnace(TileEntityIronFurnace furnace, int progress) {
        if (furnace.getActive()) {
            furnace.progress += progress;
        }
    }
    public static void heatKiln(TileEntityAlloySmelter furnace, int progress) {
        //finding main tile block
        TileEntityAlloySmelter mainTe = (TileEntityAlloySmelter)furnace.getWorld().getTileEntity(furnace.getBlockPosForPos(13));
        if (mainTe != null && mainTe.active && mainTe.processMax > 0) {
            mainTe.process -= progress;
        }
    }

    public static void heatCokeOven(TileEntityCokeOven furnace, int progress) {
        //finding main tile block
        TileEntityCokeOven mainTe = (TileEntityCokeOven)furnace.getWorld().getTileEntity(furnace.getBlockPosForPos(13));
        if (mainTe != null && mainTe.active && mainTe.processMax > 0) {
            mainTe.process -= progress;
        }
    }

    public static void heatBlastFurnaceAdv(TileEntityBlastFurnaceAdvanced furnace, int progress) {
        //finding main tile block
        TileEntityBlastFurnaceAdvanced mainTe = (TileEntityBlastFurnaceAdvanced)furnace.getWorld().getTileEntity(furnace.getBlockPosForPos(13));
        if (mainTe != null && mainTe.active && mainTe.processMax > 0) {
            mainTe.process -= progress;
        }
    }

    public static void heatBlastFurnace(TileEntityBlastFurnace furnace, int progress) {
        //finding main tile block
        TileEntityBlastFurnace mainTe = (TileEntityBlastFurnace)furnace.getWorld().getTileEntity(furnace.getBlockPosForPos(13));
        if (mainTe != null && mainTe.active && mainTe.processMax > 0) {
            mainTe.process -= progress;
        }
    }
}
