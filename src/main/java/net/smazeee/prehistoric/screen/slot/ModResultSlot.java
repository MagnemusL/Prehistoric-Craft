package net.smazeee.prehistoric.screen.slot;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.smazeee.prehistoric.item.ModItems;
import org.jetbrains.annotations.NotNull;

public class ModResultSlot extends SlotItemHandler {

    public ModResultSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return true;
    }
}
