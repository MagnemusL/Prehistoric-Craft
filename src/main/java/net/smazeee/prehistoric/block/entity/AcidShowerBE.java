package net.smazeee.prehistoric.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.smazeee.prehistoric.item.ModItems;
import net.smazeee.prehistoric.screen.AcidShowerMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class AcidShowerBE extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(5) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case 0 -> stack.getItem() == ModItems.CHARGED_MACHINE_CORE.get();
                case 4 -> stack.getItem() == ModItems.ACID_BUCKET.get();
                default -> super.isItemValid(slot, stack);
            };
        }
    };

    public static final int CORE_SLOT = 0;
    public static final int OUTPUT_1 = 1;
    public static final int OUTPUT_2 = 2;
    public static final int OUTPUT_3 = 3;
    public static final int ACID_SLOT = 4;


    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int X;
    private int Y;
    private int Z;

    public AcidShowerBE(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ACID_SHOWER_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> AcidShowerBE.this.X;
                    case 1 -> AcidShowerBE.this.Y;
                    case 2 -> AcidShowerBE.this.Z;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
            }

            @Override
            public int getCount() {
                return 3;
            }
        };
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Block Detector");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new AcidShowerMenu(pContainerId, pPlayerInventory, this);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        super.deserializeNBT(nbt);
    }

    @Override
    public CompoundTag serializeNBT() {
        return super.serializeNBT();
    }

    public static void tick(Level level, BlockPos pos, BlockState state, AcidShowerBE entity) {
        if(hasRecipe(entity) && hasNotReachedStackLimit(entity)) {
            craftItem(entity);
        }
    }

    private static void craftItem(AcidShowerBE entity) {
        entity.itemHandler.extractItem(4, 1, false);
        entity.itemHandler.getStackInSlot(0).hurt(1, new Random(), null);
        int useTimes = 0;

        if(entity.itemHandler.getStackInSlot(1).equals(new ItemStack(ModItems.SULFUR.get()))) {
            entity.itemHandler.setStackInSlot(1, new ItemStack(Items.EMERALD));
        } else if (entity.itemHandler.getStackInSlot(2).equals(new ItemStack(ModItems.SULFUR.get()))) {
            entity.itemHandler.setStackInSlot(2, new ItemStack(Items.EMERALD));
        } else if (entity.itemHandler.getStackInSlot(3).equals(new ItemStack(ModItems.SULFUR.get()))) {
            entity.itemHandler.setStackInSlot(3, new ItemStack(Items.EMERALD));
        }

        useTimes++;

        if(useTimes == 5) {
            entity.itemHandler.setStackInSlot(4, new ItemStack(Items.BUCKET));
        }
    }

    public static boolean hasRecipe(AcidShowerBE entity) {
        boolean hasItemInCoreSlot = entity.itemHandler.getStackInSlot(0).getItem() == ModItems.CHARGED_MACHINE_CORE.get();

        boolean hasOutputSlot1 = entity.itemHandler.getStackInSlot(1).getItem() == ModItems.SULFUR.get();
        boolean hasOutputSlot2 = entity.itemHandler.getStackInSlot(2).getItem() == ModItems.SULFUR.get();
        boolean hasOutputSlot3 = entity.itemHandler.getStackInSlot(3).getItem() == ModItems.SULFUR.get();

        boolean hasItemInAcidSlot = entity.itemHandler.getStackInSlot(4).getItem() == ModItems.ACID_BUCKET.get();

        return hasItemInAcidSlot && hasOutputSlot1 || hasOutputSlot2 || hasOutputSlot3 && hasItemInCoreSlot;
    }

    private static boolean hasNotReachedStackLimit(AcidShowerBE entity) {
        return entity.itemHandler.getStackInSlot(1).getCount() < entity.itemHandler.getStackInSlot(1).getMaxStackSize()
                || entity.itemHandler.getStackInSlot(2).getCount() < entity.itemHandler.getStackInSlot(2).getMaxStackSize()
                || entity.itemHandler.getStackInSlot(3).getCount() < entity.itemHandler.getStackInSlot(3).getMaxStackSize();
    }
}