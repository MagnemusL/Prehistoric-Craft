package net.smazeee.prehistoric.block.custom.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.smazeee.prehistoric.PrehistoricCraft;
import net.smazeee.prehistoric.block.ModBlocks;
import org.jetbrains.annotations.Nullable;


import java.util.Random;

public class CycadeoideaBlock extends Block implements BonemealableBlock {
    public CycadeoideaBlock(Properties properties) {
        super(properties);
    }

    public static final BooleanProperty FLOWERING = BooleanProperty.create("flowering");

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
        long time = level.getGameTime();
        if (!level.isAreaLoaded(pos, 1)) return;

        if(time == 1000 && level.getBlockState(pos.above()) == ModBlocks.CYCADEOIDEA_SHOOT.get().defaultBlockState()) {
            level.setBlock(pos, state.setValue(FLOWERING, true), 3);
            PrehistoricCraft.LOGGER.debug("The time at 1000 is: " + time);
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }


    public boolean isFlowering(BlockState state) {
        return state.getValue(FLOWERING);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter getter, BlockPos pos, BlockState state, boolean b) {
        return !this.isFlowering(state);
    }

    public boolean isBonemealSuccess(Level level, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, Random random, BlockPos pos, BlockState state) {
        if(level.getBlockState(pos.above()) == ModBlocks.CYCADEOIDEA_SHOOT.get().defaultBlockState()) {
            level.setBlock(pos, state.setValue(FLOWERING, true), 3);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FLOWERING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FLOWERING, false);
    }
}
