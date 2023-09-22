package net.smazeee.prehistoric.block.custom.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.smazeee.prehistoric.block.ModBlocks;

import java.util.Random;

public class CycadeoidaGrower extends Block implements BonemealableBlock {
    public CycadeoidaGrower(Properties properties) {
        super(properties);
    }

    public void grow(Level level, BlockPos pos) {
        level.setBlockAndUpdate(pos, ModBlocks.CYCADEOIDA_TRUNK.get().defaultBlockState().setValue(CycadeoidaBlock.FLOWERING, false));
        level.setBlockAndUpdate(pos.above(), ModBlocks.CYCADEOIDA_SHOOT.get().defaultBlockState());
    }

    private int time = 0;

    @Override
    public boolean isRandomlyTicking(BlockState p_49921_) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
        time++;
        if(time == 100) {
            grow(level, pos);
        }
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter getter, BlockPos pos, BlockState state, boolean b) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, Random random, BlockPos pos, BlockState state) {
        grow(level, pos);
    }
}
