package igentuman.evtweaks.container;

import igentuman.evtweaks.EvTweaks;
import igentuman.evtweaks.network.ModPacketHandler;
import igentuman.evtweaks.network.PacketUpdateItemStack;
import igentuman.evtweaks.tile.TileEntityMechanicalForgeHammer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

public class ContainerMechanicalForgehammer extends Container implements PacketUpdateItemStack.IUpdateNonSlotItemStack {
    private final TileEntityMechanicalForgeHammer forgehammer;
    public static final int FORGEHAMMER_SIZE = 9;

    public int progress;
    public int requiredProgress;
    public ItemStack result;

    public List<Slot> craftingSlots = new ArrayList<>();
    public List<Slot> playerSlots = new ArrayList<>();

    public ContainerMechanicalForgehammer(IInventory playerInventory, TileEntityMechanicalForgeHammer forgehammer) {
        this.forgehammer = forgehammer;
        this.result = forgehammer.getResult();

        addMachineSlots();
        addPlayerSlots(playerInventory);
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendWindowProperty(this, 0, forgehammer.getAdjustedProgress());
        listener.sendWindowProperty(this, 1, forgehammer.requiredProgress);
        sendResultStack(listener, forgehammer.getResult());
    }

    public void sendResultStack(IContainerListener listener, ItemStack stack) {
        if(!(listener instanceof EntityPlayerMP)) return;

        ModPacketHandler.instance.sendTo(
                new PacketUpdateItemStack(this, 0, forgehammer.getResult()),
                (EntityPlayerMP) listener
        );
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        boolean stackChanged = !ItemStack.areItemStackTagsEqual(this.result, this.forgehammer.getResult());

        for (IContainerListener listener : listeners) {
            if (stackChanged) {
                sendResultStack(listener, forgehammer.getResult());
            }

            if (this.progress != forgehammer.getAdjustedProgress()) {
                listener.sendWindowProperty(this, 0, forgehammer.getAdjustedProgress());
            }

            if (this.requiredProgress != forgehammer.requiredProgress) {
                listener.sendWindowProperty(this, 1, forgehammer.requiredProgress);
            }
        }

        this.progress = forgehammer.getAdjustedProgress();
        this.requiredProgress = forgehammer.requiredProgress;
        this.result = forgehammer.getResult();
    }


    @Override
    public void updateProgressBar(int id, int data) {
        switch (id) {
            case 0:
                this.progress = data;
                break;
            case 1:
                this.requiredProgress = data;
                break;
            default:
                EvTweaks.instance.logger.log(Level.ERROR, "Unknown property: "+id);
                break;
        }
    }

    private void addPlayerSlots(IInventory playerInventory) {
        // Slots for the main inventory
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                int x = 8 + col * 18;
                int y = row * 18 + 84;
                playerSlots.add(this.addSlotToContainer(new Slot(playerInventory, col + row * 9 + 9, x, y)));
            }
        }

        // Slots for the hotbar
        for (int idx = 0; idx < 9; ++idx) {
            int x = 8 + idx * 18;
            int y = 142;
            playerSlots.add(this.addSlotToContainer(new Slot(playerInventory, idx, x, y)));
        }
    }

    private void addMachineSlots() {
        IItemHandler itemHandler = this.forgehammer.getCapability(ITEM_HANDLER_CAPABILITY, null);
        Slot s = this.addSlotToContainer(new SlotItemHandler(
                itemHandler,
                0,
                48,
                35
        ));
        craftingSlots.add(s);
    }

    @NotNull
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < FORGEHAMMER_SIZE) {
                // Item is in FORGEHAMMER, move to inventory
                if (!this.mergeItemStack(itemstack1, FORGEHAMMER_SIZE, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, FORGEHAMMER_SIZE, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    @Override
    public boolean canInteractWith(@NotNull EntityPlayer playerIn) {
        return true;
    }

    @Override
    public void updateItem(int idx, ItemStack stack) {
        this.result = stack;
    }
}
