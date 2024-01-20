package net.smazeee.prehistoric.block.custom.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.smazeee.prehistoric.block.ModBlocks;

public class CycadeoideaShoot extends BushBlock {
    public CycadeoideaShoot(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.is(ModBlocks.CYCADEOIDEA_TRUNK.get());
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
        BlockState blockState = reader.getBlockState(pos.below());
        return blockState == ModBlocks.CYCADEOIDEA_TRUNK.get().defaultBlockState();
    }
}
