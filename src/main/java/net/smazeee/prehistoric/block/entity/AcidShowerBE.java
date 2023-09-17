package net.smazeee.prehistoric.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import net.smazeee.prehistoric.fluids.ModFluids;
import net.smazeee.prehistoric.item.ModItems;
import net.smazeee.prehistoric.recipe.AcidShowerRecipe;
import net.smazeee.prehistoric.screen.AcidShowerMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

public class AcidShowerBE extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case 0 -> stack.getItem() == ModItems.CHARGED_MACHINE_CORE.get();
                case 3 -> stack.getItem() == ModItems.ACID_BUCKET.get();
                default -> super.isItemValid(slot, stack);
            };
        }
    };

    public static final int CORE_SLOT = 0;
    public static final int INPUT = 1;
    public static final int OUTPUT = 2;
    public static final int ACID_SLOT = 3;


    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 1;

    public AcidShowerBE(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ACID_SHOWER_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> AcidShowerBE.this.progress;
                    case 1 -> AcidShowerBE.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> AcidShowerBE.this.progress = pValue;
                    case 1 -> AcidShowerBE.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
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
        return new AcidShowerMenu(pContainerId, pPlayerInventory, this, this.data);
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
        pTag.putInt("acid_shower.progress", progress);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("acid_shower.progress");
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        super.deserializeNBT(nbt);
    }

    @Override
    public CompoundTag serializeNBT() {
        return super.serializeNBT();
    }

    public static void tick(Level level, BlockPos pos, BlockState state, AcidShowerBE blockEntity) {
        if(!level.isClientSide) {
            if (hasRecipe(blockEntity)) {
                blockEntity.progress++;
                setChanged(level, pos, state);
                if (blockEntity.progress > blockEntity.maxProgress) {
                    craftItem(blockEntity);
                }
            }
        }
    }
    
    private static boolean hasRecipe(AcidShowerBE entity) {
        Level level = entity.level;
        RecipeWrapper inventory = new RecipeWrapper(entity.itemHandler);
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<AcidShowerRecipe> match = level.getRecipeManager()
                .getRecipeFor(AcidShowerRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem())
                && hasAcidInAcidSlot(entity) && hasCoreInCoreSlot(entity);
    }

    private static boolean hasAcidInAcidSlot(AcidShowerBE entity) {
        return entity.itemHandler.getStackInSlot(3).getItem() == ModItems.ACID_BUCKET.get();
    }

    private static boolean hasCoreInCoreSlot(AcidShowerBE entity) {
        return entity.itemHandler.getStackInSlot(0).getItem() == ModItems.CHARGED_MACHINE_CORE.get();
    }

    private static void craftItem(AcidShowerBE entity) {
        Level level = entity.level;
        Random random = new Random();
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<AcidShowerRecipe> match = level.getRecipeManager()
                .getRecipeFor(AcidShowerRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            entity.itemHandler.getStackInSlot(0).hurt(5, new Random(), null);
            entity.itemHandler.extractItem(1,1, false);
            entity.itemHandler.setStackInSlot(2, new ItemStack(ModItems.SULFUR.get()));

            entity.resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static boolean canInsertItemIntoOutputSlot(RecipeWrapper inventory, ItemStack output) {
        return inventory.getItem(2).getItem() == output.getItem() || inventory.getItem(1).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(RecipeWrapper inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(1).getCount();
    }
}