package net.smazeee.prehistoric.block.custom.machines;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.smazeee.prehistoric.PrehistoricCraft;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class CycadeoidaBlock extends BaseEntityBlock {
    public CycadeoidaBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
        float time = level.getTimeOfDay(0);
        if(time == 1) {
            PrehistoricCraft.LOGGER.info("1, is the same as " + time);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return null;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return super.getTicker(level, state, type);
    }
}
