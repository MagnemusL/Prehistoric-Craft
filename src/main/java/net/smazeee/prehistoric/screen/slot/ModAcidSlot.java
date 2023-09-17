package net.smazeee.prehistoric.screen.slot;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.smazeee.prehistoric.item.ModItems;

public class ModAcidSlot extends SlotItemHandler {
    public ModAcidSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean isSameInventory(Slot other) {
        return other.getItem().equals(new ItemStack(ModItems.ACID_BUCKET.get()));
    }
}
