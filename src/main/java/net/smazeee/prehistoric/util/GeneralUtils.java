package net.smazeee.prehistoric.util;

import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Doubles;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public class GeneralUtils {

    /////////////////////////////

    // Weighted Random from: https://stackoverflow.com/a/6737362
    public static <T> T getRandomEntry(List<com.mojang.datafixers.util.Pair<T, Integer>> rlList, Random random) {
        double totalWeight = 0.0;

        // Compute the total weight of all items together.
        for (Pair<T, Integer> pair : rlList) {
            totalWeight += pair.getSecond();
        }

        // Now choose a random item.
        int index = 0;
        for (double randomWeightPicked = random.nextFloat() * totalWeight; index < rlList.size() - 1; ++index) {
            randomWeightPicked -= rlList.get(index).getSecond();
            if (randomWeightPicked <= 0.0) break;
        }

        return rlList.get(index).getFirst();
    }

    ////////////////

    public static BlockPos getRandomBlockposWithinRange(Level world, Entity entity, int maxRadius, int minRadius) {
        BlockPos newBeePos;
        newBeePos = new BlockPos(
                entity.getX() + (world.random.nextInt(maxRadius) + minRadius) * (world.random.nextBoolean() ? 1 : -1),
                Doubles.constrainToRange(entity.getY() + (world.random.nextInt(maxRadius) + minRadius) * (world.random.nextBoolean() ? 1 : -1), 1, 254),
                entity.getZ() + (world.random.nextInt(maxRadius) + minRadius) * (world.random.nextBoolean() ? 1 : -1));
        return newBeePos;
    }

    ////////////////

    // Source: https://dzone.com/articles/be-lazy-with-java-8
    public static final class Lazy<T> {

        private volatile T value;

        public T getOrCompute(Supplier<T> supplier) {
            final T result = value; // Just one volatile read
            return result == null ? maybeCompute(supplier) : result;
        }

        private synchronized T maybeCompute(Supplier<T> supplier) {
            if (value == null) {
                value = requireNonNull(supplier.get());
            }
            return value;
        }
    }


    //////////////////////////////////////////

    /**
     * For doing basic trades.
     * Very short and barebone to what I want
     */
    public static class BasicItemTrade implements VillagerTrades.ItemListing  {
        private final Item itemToTrade;
        private final Item itemToReceive;
        private final int amountToGive;
        private final int amountToReceive;
        protected final int maxUses;
        protected final int experience;
        protected final float multiplier;

        public BasicItemTrade(Item itemToTrade, Item itemToReceive, int amountToGive,  int amountToReceive) {
            this(itemToReceive, itemToTrade, amountToGive, amountToReceive, 20, 2, 0.05F);
        }

        public BasicItemTrade(Item itemToTrade, Item itemToReceive, int amountToGive, int amountToReceive, int maxUses, int experience, float multiplier) {
            this.itemToTrade = itemToTrade;
            this.itemToReceive = itemToReceive;
            this.amountToGive = amountToGive;
            this.amountToReceive = amountToReceive;
            this.maxUses = maxUses;
            this.experience = experience;
            this.multiplier = multiplier;
        }

        @Override
        public MerchantOffer getOffer(Entity entity, Random random) {
            ItemStack in = new ItemStack(this.itemToTrade, this.amountToGive);
            ItemStack out = new ItemStack(this.itemToReceive, this.amountToReceive);
            return new MerchantOffer(in, out, this.maxUses, this.experience, this.multiplier);
        }
    }

    ///////////////////////

    public static final List<BlockState> VANILLA_CANDLES = ImmutableList.of(
            Blocks.CANDLE.defaultBlockState(),
            Blocks.CYAN_CANDLE.defaultBlockState(),
            Blocks.BLACK_CANDLE.defaultBlockState(),
            Blocks.BLUE_CANDLE.defaultBlockState(),
            Blocks.BROWN_CANDLE.defaultBlockState(),
            Blocks.GRAY_CANDLE.defaultBlockState(),
            Blocks.GREEN_CANDLE.defaultBlockState(),
            Blocks.LIGHT_BLUE_CANDLE.defaultBlockState(),
            Blocks.LIGHT_GRAY_CANDLE.defaultBlockState(),
            Blocks.LIME_CANDLE.defaultBlockState(),
            Blocks.MAGENTA_CANDLE.defaultBlockState(),
            Blocks.ORANGE_CANDLE.defaultBlockState(),
            Blocks.PINK_CANDLE.defaultBlockState(),
            Blocks.PURPLE_CANDLE.defaultBlockState(),
            Blocks.RED_CANDLE.defaultBlockState(),
            Blocks.WHITE_CANDLE.defaultBlockState(),
            Blocks.YELLOW_CANDLE.defaultBlockState()
    );

    //////////////////////////////////////////////

    /**
     * For giving the player an item properly into their inventory
     */
    public static void givePlayerItem(Player playerEntity, InteractionHand hand, ItemStack itemstackToGive, boolean giveContainerItem, boolean shrinkCurrentItem) {
        ItemStack playerItem = playerEntity.getItemInHand(hand);
        ItemStack copiedPlayerItem = playerItem.copy();

        if(shrinkCurrentItem) {
            playerItem.shrink(1);
        }

        // Give item itself to users
        if(!itemstackToGive.isEmpty()) {
            if (playerItem.isEmpty()) {
                // places result item in hand
                playerEntity.setItemInHand(hand, itemstackToGive);
            }
            // places result item in inventory
            else if (!playerEntity.getInventory().add(itemstackToGive)) {
                // drops result item if inventory is full
                playerEntity.drop(itemstackToGive, false);
            }
        }

        // give container item of player's item if specified
        if(giveContainerItem && copiedPlayerItem.hasContainerItem()) {
            ItemStack containerItem = copiedPlayerItem.getContainerItem();
            if (playerEntity.getItemInHand(hand).isEmpty()) {
                // places result item in hand
                playerEntity.setItemInHand(hand, containerItem);
            }
            // places result item in inventory
            else if (!playerEntity.getInventory().add(containerItem)) {
                // drops result item if inventory is full
                playerEntity.drop(containerItem, false);
            }
        }
    }
}
