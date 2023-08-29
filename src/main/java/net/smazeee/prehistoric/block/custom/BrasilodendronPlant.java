package net.smazeee.prehistoric.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.smazeee.prehistoric.block.ModBlocks;

import java.util.Random;

public class BrasilodendronPlant extends Block {

    public BrasilodendronPlant(Properties properties) {
        super(properties);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState state1, boolean b) {
        if (this == ModBlocks.BRASILODENDRON_BOTTOM.get()) {
                level.setBlock(pos.above(), ModBlocks.BRASILODENDRON_MIDDLE.get().defaultBlockState(), 0);

            if(level.getBlockState(pos.below()).isAir()) {
                level.removeBlock(pos, false);
            }
        }

        if (this == ModBlocks.BRASILODENDRON_MIDDLE.get()) {
                level.setBlock(pos.above(), ModBlocks.BRASILODENDRON_TOP.get().defaultBlockState(), 0);

            if(!level.getBlockState(pos.below()).is(ModBlocks.BRASILODENDRON_BOTTOM.get())) {
                level.removeBlock(pos, false);
            }
        }

        if (this == ModBlocks.BRASILODENDRON_TOP.get()) {
            if(!level.getBlockState(pos.below()).is(ModBlocks.BRASILODENDRON_MIDDLE.get())) {

            }
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        if (this == ModBlocks.BRASILODENDRON_BOTTOM.get()) {
            if(level.getBlockState(pos.below()).isAir()) {
                return false;
            }
        }

        else if (this == ModBlocks.BRASILODENDRON_MIDDLE.get()) {
            if(!level.getBlockState(pos.below()).is(ModBlocks.BRASILODENDRON_BOTTOM.get())) {
                return false;
            }
        }

        else if (this == ModBlocks.BRASILODENDRON_TOP.get()) {
            if(!level.getBlockState(pos.below()).is(ModBlocks.BRASILODENDRON_MIDDLE.get())) {
                return false;
            }
        }
        return true;
    }
}