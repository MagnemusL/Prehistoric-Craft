package net.smazeee.prehistoric.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.smazeee.prehistoric.block.ModBlocks;
import net.smazeee.prehistoric.block.custom.plants.CycadeoideaBlock;
import net.smazeee.prehistoric.item.ModItems;

import java.util.Random;

public class JarItem extends Item {

    public JarItem(Properties properties) {
        super(properties);
    }

    Random random = new Random();

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        BlockPos pos = context.getClickedPos();
        Level level = player.getLevel();
        ItemStack itemStack = new ItemStack(ModItems.EMPTY_JAR.get());
        if (this == ModItems.EMPTY_JAR.get()) {
            if (level.getBlockState(pos).is(ModBlocks.BELEMNOPTERIS.get())) {
                if (random.nextBoolean()) {
                    level.removeBlock(pos, false);
                }
                player.getInventory().add(new ItemStack(ModItems.BELEMNOPTERIS_SPORE_JAR.get()));
                itemStack.shrink(1);
                return InteractionResult.SUCCESS;
            }
            if (level.getBlockState(pos).is(ModBlocks.CYCADEOIDEA_TRUNK.get()) && level.getBlockState(pos).getValue(CycadeoideaBlock.FLOWERING)) {
                level.setBlock(pos, level.getBlockState(pos).setValue(CycadeoideaBlock.FLOWERING, false), 1);
                player.getInventory().add(new ItemStack(ModItems.CYCADEOIDA_SPORE_JAR.get()));
                itemStack.shrink(1);
                return InteractionResult.SUCCESS;
            }

        }
        if (this == ModItems.BELEMNOPTERIS_SPORE_JAR.get()) {
            if (level.getBlockState(pos).is(Blocks.GRASS_BLOCK)) {
                if (context.getClickedFace() == Direction.UP) {
                    level.setBlock(pos.above(), ModBlocks.BELEMNOPTERIS.get().defaultBlockState(), 1);
                    if (new ItemStack(ModItems.CYCADEOIDA_SPORE_JAR.get()).getCount() == 1) {
                        player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(ModItems.EMPTY_JAR.get()));
                    } else {
                        new ItemStack(ModItems.CYCADEOIDA_SPORE_JAR.get()).shrink(1);
                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }

        if (this == ModItems.CYCADEOIDA_SPORE_JAR.get()) {
            if (level.getBlockState(pos).is(Blocks.GRASS_BLOCK)) {
                if (context.getClickedFace() == Direction.UP) {
                    level.setBlock(pos.above(), ModBlocks.CYCADEOIDEA_SAPLING.get().defaultBlockState(), 1);
                    if (new ItemStack(ModItems.CYCADEOIDA_SPORE_JAR.get()).getCount() == 1) {
                        player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(ModItems.EMPTY_JAR.get()));
                    } else {
                        new ItemStack(ModItems.CYCADEOIDA_SPORE_JAR.get()).shrink(1);
                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }
        return InteractionResult.FAIL;
    }
}